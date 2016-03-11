package com.gwu.architecture;

public class Memory {

	int[] Mark;
	int[] Power;
	
	int[][] queue;
	int[] cacheTag;
	int[][] cache;
	
	int[] rom;
	int[][][][] ram;		//the four coordinates of ram represent the number of memory chip, the number of
						//bank in memory chip, the row and the line in bank
	final int ROM = 1024; // this is rom size, cannot be change when computer
							// run, and it is not cleared when computer restart
	final int RAM = 4 * 8 * 16 * 16; // this ram size, can be change, and it is cleared
								// to 0 when computer restart
	final int ROW = 16;
	final int COLUMN = 16;
	final int BANK = 8;
	final int ANUM = 4;
	final int MEMORY_SIZE = RAM;
	
	final int WAY = 2;
	final int BLOCK = 8;
	final int WORD = 8;
	final int CACHE = WAY*BLOCK;
	
	public Memory() {
		Mark = new int[43];
		Power = new int[43];
		Power[0] = 1;
		for (int i = 1; i < 32; i++)
			Power[i] = Power[i - 1] << 1;
		for (int i = 0; i < 31; i++)
			Mark[i] = Power[i + 1] - 1;
		Mark[31]=-1;
		rom = new int[ROM];
		ram = new int[ANUM][BANK][ROW][COLUMN];
		cache = new int[CACHE][WORD];
		cacheTag = new int[CACHE];
		queue = new int[BLOCK][WAY];
	}

	public void powerup() {
		for (int i = 0; i < RAM; i++)
			write(i, 0);
		for(int i=0; i<CACHE; i++) {
			cacheTag[i]=0;
			for(int j=0; j<WORD; j++)
				cache[i][j]=0;
		}
		for(int i=0; i<BLOCK; i++) {
			for(int j=0; j<WAY; j++) {
				queue[i][j]=j;
			}
		}
		init();
		
	}

	public void powerdown() {

	}

	public void destroy() {

	}

	public void init() {

	}

	/****************************************************************************************************/
	/*
	 * routine for cache
	 */
	
	public void update(int t, int block) {		//push the recently used block number in one set at last
		int l=0;
		for(int i=0; i<WAY; i++) {
			if(queue[block][i]==t)
				continue;
			queue[block][l++]=queue[block][i];
		}
		//System.out.println(t);
		queue[block][l++]=t;
	}
	
	public int lRU(int block) {		//select the one has not been used longest
		return queue[block][0];
	}
	
	public int replace(int tag, int block) {		//exchange one block out of cache
		int i=lRU(block);
		update(i, block);
		i=(block<<1)+i;
		int tag1=cacheTag[i]&Mark[6], tag2=tag&Mark[6];
		int s=(tag1<<6)|(block<<3);
		int e=s+WORD;
		if( (cacheTag[i]&Power[7])==Power[7] ) {
			for(int a=s, b=0; a<e; a++, b++) {
				write(a, cache[i][b]);
			}
		}
		s=(tag2<<6)|(block<<3);
		e=s+WORD;
		for(int a=s, b=0; a<e; a++, b++) {
			cache[i][b]=read(a);
		}
		cacheTag[i]=tag;
		return i;
	}
	
	
	/****************************************************************************************************/
	/*
	 * routine for read and write
	 */
	
	public int read(int address) {		//actually read the memory
		int row, column, bank, anum;
		column=address&Mark[3];
		address=address>>4;
		row=address&Mark[3];
		address=address>>4;
		bank=address&Mark[2];
		address=address>>3;
		anum=address&Mark[1];
		return ram[anum][bank][row][column];
	}
	
	public void write(int address, int value) {		//actually write the memory
		int row, column, bank, anum;
		column=address&Mark[3];
		address=address>>4;
		row=address&Mark[3];
		address=address>>4;
		bank=address&Mark[2];
		address=address>>3;
		anum=address&Mark[1];
		ram[anum][bank][row][column]=value;
	}
	
	public int fetchData(int address) { // use the address from MAR, return the
											// address' context
		if (address < 0) {
			System.out.printf("Address cannot be smaller than 0!!!\n");
			return 0;
		}
		else if (address >= RAM) {
			System.out.printf("Address cannot be larger than MEMORY_SIZE!!!\n");
			return 0;
		}
		int tag=((address&(Mark[12]^Mark[5]))>>6)|Power[7];
		int block=(address&(Mark[5]^Mark[2]))>>3;
		int word=address&Mark[2];
		int i, s=block<<1, e=(block<<1)+WAY;
		for(i=s; i<e; i++) {
			if(cacheTag[i]==tag) {	//cache hit
				//System.out.println("Cache hit!!!");
				update(i-s, block);
				return cache[i][word];
			}
		}
		//cache miss
		//System.out.println("Cache miss!!!");
		i=replace(tag, block);
		return cache[i][word];
	}

	public void storeData(int address, int value) { // use the address from MAR
													// and the value from MBR,
													// store the value in
													// address' context
		value &= Mark[19];
		if (address < 0) {
			System.out.printf("Address cannot be smaller than 0!!!\n");
		} else if(address>=RAM){
			System.out.printf("Address cannot be larger than MEMORY_SIZE!!!\n");
		}
		int tag=((address&(Mark[12]^Mark[5]))>>6)|Power[7];
		int block=(address&(Mark[5]^Mark[2]))>>3;
		int word=address&Mark[2];
		int i, s=block<<1, e=(block<<1)+WAY;
		for(i=s; i<e; i++) {
			if(cacheTag[i]==tag) {	//cache hit
				//System.out.println("Cache hit!!!");
				update(i-s, block);
				cache[i][word]=value;
				return ;
			}
		}
		//cache miss
		//System.out.println("Cache miss!!!");
		i=replace(tag, block);
		cache[i][word]=value;
	}
	/************************************************************************************************/
	
	//used for debug
	public static void main(String[] args) {
		Memory testMemory=new Memory();
		testMemory.powerup();
		
		System.out.println(testMemory.fetchData(8));
		System.out.println(testMemory.fetchData(9));
	}
}
