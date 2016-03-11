package com.gwu.architecture;

public class Computer {

	Cpu gwuCpu;
	IO gwuIO;
	Memory gwuMemory;

	public Computer() {
		gwuCpu = new Cpu();
		gwuIO = new IO();
		gwuMemory = new Memory();
	}

	public void init(GUI gwuGui) {

		gwuCpu.init();
		gwuIO.init(gwuGui);
		gwuMemory.init();
		// System.out.println(gwuCpu.IR.value);

	}

	public void debug() {

	}

	public void powerup() { // start up the computer
		gwuCpu.powerup();
		gwuMemory.powerup();
		gwuIO.powerup();
		//fetchInstruction();
		//CardReader cardReader = gwuIO.gwuCardReader;
	//	int len = cardReader.fileLength;
	//	for(int i=0; i<len; i++) {
		//	gwuMemory.storeData(i, Integer.parseInt(cardReader.cardStrings[i]));
	//	}
	}

	public void powerdown() { // shutdown the computer
		gwuIO.powerdown();
		gwuMemory.powerdown();
		gwuCpu.powerdown();
	}

	public void destory() {
		gwuCpu.destroy();
		gwuIO.destroy();
		gwuMemory.destroy();
	}

	/*********************************** computer operation **********************************************************/

	public void fetchInstruction() { // get instruction
		this.gwuCpu.fetchInstruction(this.gwuMemory);
	}

	public void decode() { // decode instruction
		this.gwuCpu.decode();
	}

	public void effAddress() { // compute effective address
		this.gwuCpu.effAddress(gwuMemory);
	}

	public void execute() { // do execute
		this.gwuCpu.execute(gwuMemory, gwuIO);
	}

	public void storeResult() { // store result
		this.gwuCpu.storeResult(gwuMemory, gwuIO);
	}

	public void nextInstruction() { // get next instruction address
		this.gwuCpu.nextInstruction();
	}

	/*********************************** main operation **********************************************************/
	/*
	 * routine for Main
	 */
	public void step() {		//fuction for switch step
		fetchInstruction();
		decode();
		effAddress();
		execute();
		storeResult();
		nextInstruction();
	}
	
	public void run() {		//fuction for switch run
		while(true) {
			//if(gwuCpu.PC.value==265)
				//break;
			fetchInstruction();	
			decode();
			if(gwuCpu.IR.value==0)
				break;
			effAddress();
			execute();
			storeResult();
			nextInstruction();
			
		}
	}

}
