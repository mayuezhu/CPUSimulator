package com.gwu.architecture;

public class Cpu {

	class Register { // the class of register, such as PC, IR
		int value;
		int bits;

		Register(int v, int b) {
			value = v;
			bits = b;
		}
		
		public int TrueValue() {
			if(bits<20)
				return value;
			else {
				if( (value&Power[19])==Power[19] ) {
					value|=Mark[31]^Mark[19];
				}
				return value;
			}
		}
	}

	int[] Mark;
	int[] Power;

	/*************************************************************************************/
	// the built-in register in our computer simulator
	Register PC;
	Register IR;
	Register EA;
	Register TEMP;
	Register COUNT;
	Register CONTRL;

	Register MAR;
	Register MBR;
	Register MSR;
	Register MFR;

	Register[] X;
	Register[] R;
	Register[] OP;
	Register[] RES;
	Register[] BOP;

	Register I;
	Register T;
	Register XI;
	Register RI;
	Register CC;
	Register OPCODE;
	Register ADDRESS;
	
	Register CCI;
	Register RX;
	Register RY;
	Register AL;
	Register LR;
	Register DEV;

	/*****************************************************************************/

	public Cpu() {
		
		AL = new Register(0, 1);
		LR = new Register(0, 1);
		CCI = new Register(0, 2);
		RX = new Register(0, 2);
		RY = new Register(0, 2);
		BOP = new Register[2];
		DEV = new Register(0, 4);
		for(int i=0; i<2; i++)
			BOP[i] = new Register(0, 20);

		PC = new Register(0, 13);
		IR = new Register(0, 20);
		EA = new Register(0, 13);
		TEMP = new Register(0, 20);
		COUNT = new Register(0, 5);
		CONTRL = new Register(0, 6);
		MAR = new Register(0, 13);
		MBR = new Register(0, 20);
		MSR = new Register(0, 20);
		MFR = new Register(0, 20);
		OPCODE = new Register(0, 6);
		RI = new Register(0, 2);
		XI = new Register(0, 2);
		I = new Register(0, 1);
		T = new Register(0, 1);
		CC = new Register(0, 4);
		ADDRESS = new Register(0, 8);

		X = new Register[3];
		for (int i = 0; i < 3; i++)
			X[i] = new Register(0, 13);

		R = new Register[4];
		for (int i = 0; i < 4; i++)
			R[i] = new Register(0, 20);

		OP = new Register[2];
		for (int i = 0; i < 2; i++)
			OP[i] = new Register(0, 20);

		RES = new Register[2];
		for (int i = 0; i < 2; i++)
			RES[i] = new Register(0, 20);

		Mark = new int[40];
		Power = new int[40];
		Power[0] = 1;
		for (int i = 1; i < 32; i++)
			Power[i] = Power[i - 1] << 1;
		for (int i = 0; i < 31; i++)
			Mark[i] = Power[i + 1] - 1;
		Mark[31]=-1;

	}

	public void powerdown() { // shutdown the computer

	}

	public void powerup() { // start up the computer, all the register cleared
				
		CCI.value=0; 
		CCI.bits=2;
		RX.value=0;
		RX.bits=2;
		RY.value=0;
		RY.bits=2;
		BOP[0].bits = 20;
		BOP[0].value = 0;
		BOP[1].bits = 20;
		BOP[1].value = 0;
		DEV.value=0;
		DEV.bits=4;
		
		CC.value=0;
		CC.bits=4;
		PC.value = 0;
		PC.bits = 13;
		IR.value = 0;
		IR.bits = 20;
		EA.value = 0;
		EA.bits = 13;
		TEMP.value = 0;
		TEMP.bits = 20;
		COUNT.value = 0;
		COUNT.bits = 5;
		CONTRL.value = 0;
		CONTRL.bits = 6;
		MAR.value = 0;
		MAR.bits = 13;
		MBR.value = 0;
		MBR.bits = 20;
		MFR.value = 0;
		MFR.bits = 20;
		MSR.value = 0;
		MSR.bits = 20;
		OPCODE.value = 0;
		OPCODE.bits = 6;
		RI.value = 0;
		RI.bits = 2;
		XI.value = 0;
		XI.bits = 2;
		I.value = 0;
		I.bits = 1;
		T.value = 0;
		T.bits = 1;
		ADDRESS.value = 0;
		ADDRESS.bits = 8;
		X[0].bits = 13;
		X[0].value = 0;
		X[1].bits = 13;
		X[1].value = 0;
		X[2].bits = 13;
		X[2].value = 0;
		R[0].bits = 20;
		R[0].value = 0;
		R[1].bits = 20;
		R[1].value = 0;
		R[2].bits = 20;
		R[2].value = 0;
		OP[0].bits = 20;
		OP[0].value = 0;
		OP[1].bits = 20;
		OP[1].value = 0;
		
		RES[0].bits = 20;
		RES[0].value = 0;
		RES[1].bits = 20;
		RES[1].value = 0;


	}

	public void destroy() { // collect memory
	}

	public void init() { // init register

	}
	
	/*************************************set flag bit***************************************************/
	
	
	public void setFlowFlag(int a) {
		CC.value&=Mark[1];
		if( ((OP[0].value&Power[19])==Power[19]) && ((OP[1].value&Power[19])==Power[19]) ) {		//set overflow
			if( (a&Power[19])==0 ) {
				transfer(CC, CC.value|Power[3]);
			}
		}
		if( ((OP[0].value&Power[19])==0) && ((OP[1].value&Power[19])==0)) {		//set underflow
			if( (a&Power[19])==Power[19] ) {
				transfer(CC, CC.value|Power[2]);
			}
		}
	}
	
	
	
	/*************************************set flag bit***************************************************/

	/****************************************************************************************************/
	/*
	 * routine for debug
	 */

	public String show() { // show some important register value
		String a = new String();

		a += "PC:  " + Integer.toString(PC.value) + "\n";
		a += "X[0] X[1] X[2]:  " + Integer.toString(X[0].value) + " "
				+ Integer.toString(X[1].value) + " "
				+ Integer.toString(X[2].value) + "\n";
		a += "R[0] R[1] R[2] R[3]:  " + Integer.toString(R[0].value) + " "
				+ Integer.toString(R[1].value) + " "
				+ Integer.toString(R[2].value) + " "
				+ Integer.toString(R[3].value) + "\n";
		return a;
	}

	public void changeRegister(Register reg, int value) { // change register
															// value
		transfer(reg, value);
	}

	/****************************************************************************************************/

	/********************************************transfer data to register*******************************/
	/*
	 * routine for transfer value to register
	 */

	public int transferValue(int value, int bits) { // return the value align
													// with bits
		return value & Mark[bits - 1];
	}

	public int transferValue(int value, int sbit, int ebit, int bits) { // return
																		// the
																		// bits
																		// from
																		// sbit
																		// from
																		// ebit
																		// in
																		// value
		bits--;
		sbit = bits - sbit;
		ebit = bits - ebit;
		ebit--;
		if (ebit != -1)
			return (value & (Mark[sbit] ^ Mark[ebit])) >> (ebit + 1);
		else
			return value & Mark[sbit];
	}

	public void transfer(Register reg1, Register reg2) { // transfer the value
															// of reg1 to reg2
		reg1.value = transferValue(reg2.value, reg1.bits);
	}

	public void transfer(Register reg1, Register reg2, int sbit, int ebit) { // transfer
																				// reg2's
																				// the
																				// bits
																				// from
																				// sbit
																				// to
																				// ebit
																				// to
																				// reg1
		transfer(reg1, transferValue(reg2.value, sbit, ebit, reg2.bits));
	}

	public void transfer(Register reg, int value) { // transfer value to reg,
													// value is int not class
													// Register
		reg.value = transferValue(value, reg.bits);
	}

	public void fetchData(Register add, Register reg, Memory gwuMemory) { // transfer
																			// [add]
																			// to
																			// reg
		transfer(MAR, add);
		transfer(MBR, gwuMemory.fetchData(MAR.value));
		transfer(reg, MBR);
	}

	public void storeData(Register add, Register reg, Memory gwuMemory) { // store
																			// reg
																			// in
																			// [add]
		transfer(MAR, add);
		transfer(MBR, reg);
		gwuMemory.storeData(MAR.value, MBR.value);
	}
	

	/**************************************micro-instruction*******************************/
	/*
	 * routine for computer
	 */
	public void nextInstruction() { // get next instruction address
		if(BOP[0].value==1) {
			transfer(PC, BOP[1]);
		}
		else {
			transfer(PC, PC.value+1);
		}
		transfer(BOP[0], 0);
	}

	public void fetchInstruction(Memory gwuMemory) { // get next instruction
		fetchData(PC, IR, gwuMemory);
	}

	public void decode() { // decode the instruction
		transfer(OPCODE, IR, 0, 5);
		transfer(XI, IR, 6, 7);
		transfer(RI, IR, 8, 9);
		transfer(I, IR, 10, 10);
		transfer(T, IR, 11, 11);
		transfer(ADDRESS, IR, 12, 19);
	}

	public void effAddress(Memory gwuMemory) { // compute effective address
		transfer(EA, ADDRESS);
		if(XI.value!=0) {
			transfer(OP[0], EA);
			transfer(OP[1], X[XI.value-1]);
			transfer(CONTRL, OPCODE);
			transfer(RES[0], OP[0].value+OP[1].value);
			transfer(EA, RES[0]);
		}
		if (I.value == 1) {
			fetchData(EA, EA, gwuMemory);
		}
		
	}

	public void execute(Memory gwuMemory, IO gwuIO) { // do execute

		/**************************************load/store******************************************************/
		// LDR I,r,x:address
		if (OPCODE.value == 1) {
			fetchData(EA, R[RI.value], gwuMemory);
		}// LDR I,r,x:address

		// STR I,r,x:address
		else if (OPCODE.value == 2) {
			storeData(EA, R[RI.value], gwuMemory);
		}// STR I,r,x:address

		// LDA I,r,x:address
		else if (OPCODE.value == 3) {
			transfer(R[RI.value], EA);
		}// LDA I,r,x:address
		
		// LDX I, x:address
		else if (OPCODE.value == 33) {
				fetchData(EA, X[XI.value - 1], gwuMemory);
		}// LDX I, x:address

		// STX I, x:address
		else if (OPCODE.value == 34) {
				storeData(EA, X[XI.value - 1], gwuMemory);
		}// STX I, x:address
		/**************************************load/store******************************************************/
		
		
		
		/*********************************arithmetic/logical***************************************************/
		// AMR I, r, x:address
		else if (OPCODE.value == 4) {
			fetchData(EA, OP[0], gwuMemory);
			transfer(OP[1], R[RI.value]);
			transfer(CONTRL, OPCODE);
			transfer(RES[0], OP[0].value + OP[1].value);
			setFlowFlag(RES[0].value);
			
		}// AMR I, r, x:address

		// SMR I, r, x:address
		else if (OPCODE.value == 5) {
			fetchData(EA, OP[0], gwuMemory);
			transfer(OP[1], R[RI.value]);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], -OP[0].value);
			transfer(RES[0], OP[1].value + OP[0].value);
			transfer(OP[0], -OP[0].value);
			setFlowFlag(RES[0].value);
		}// SMR I, r, x:address

		// AIR r, immed
		else if (OPCODE.value == 6) {
			transfer(CONTRL, OPCODE);
			if (ADDRESS.value != 0) {
				if (R[RI.value].value == 0) {
					transfer(R[RI.value], ADDRESS);
				} else {
					transfer(OP[0], ADDRESS);
					transfer(OP[1], R[RI.value]);
					transfer(RES[0], OP[0].value + OP[1].value);
					setFlowFlag(RES[0].value);
					transfer(R[RI.value], RES[0]);
				}
			}
		}// AIR r, immed

		// SIR r, immed
		else if (OPCODE.value == 7) {
			transfer(CONTRL, OPCODE);
			if (ADDRESS.value != 0) {
				if (R[RI.value].value == 0) {
					transfer(R[RI.value], -ADDRESS.value);
				} else {
					transfer(OP[0], ADDRESS);
					transfer(OP[1], R[RI.value]);
					transfer(CONTRL, OPCODE);
					transfer(OP[0], -OP[0].value);
					transfer(RES[0], OP[1].value + OP[0].value);
					transfer(OP[0], -OP[0].value);
					setFlowFlag(RES[0].value);
					transfer(R[RI.value], RES[0]);
				}
			}
		}// SIR r, immed
		
		//MLT rx, ry
		else if(OPCODE.value==16) {
			transfer(RX, XI);
			transfer(RY, RI);
			if( (RX.value!=0)&&(RX.value!=2) ) {
				System.out.println("RX must be 0 or 2");
				return ;
			}
			if( (RY.value!=0)&&(RY.value!=2) ) {
				System.out.println("RY must be 0 or 2");
				return ;
			}
			transfer(OP[0], R[RX.value]);
			transfer(OP[1], R[RY.value]);
			transfer(CONTRL, OPCODE);
			long a=OP[0].value;
			long b=OP[1].value;
			long t=-1^Mark[19];
			if( (a&Power[19])==Power[19] ) {
				a|=t;
			}
			if( (b&Power[19])==Power[19] ) {
				b|=t;
			}
			a=a*b;
			int tt=new Long(a&Mark[19]).intValue();
			transfer(RES[1], tt);
			tt = new Long( (a>>20)&Mark[19] ).intValue();
			transfer(RES[0], tt);
			transfer(R[RX.value], RES[0]);
			transfer(R[RX.value+1], RES[1]);
			setFlowFlag(RES[0].value);
		}//MLT rx, ry
		
		//DVD rx, ry
		else if(OPCODE.value==17) {
			CC.value&=Mark[3]^Power[1];
			transfer(RX, XI);
			transfer(RY, RI);
			if( (RX.value!=0)&&(RX.value!=2) ) {
				System.out.println("RX must be 0 or 2");
				return ;
			}
			if( (RY.value!=0)&&(RY.value!=2) ) {
				System.out.println("RY must be 0 or 2");
				return ;
			}
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RX.value]);
			transfer(OP[1], R[RY.value]);
			if(OP[1].value==0) {
				CC.value|=Power[1];
			}
			else {
				transfer(RES[0], OP[0].TrueValue()/OP[1].TrueValue());
				transfer(RES[1], OP[0].TrueValue()%OP[1].TrueValue());
				transfer(R[RX.value], RES[0]);
				transfer(R[RX.value+1], RES[1]);
			}
			
		}//DVD rx, ry

		//TRR rx, ry
		else if(OPCODE.value==18) {
			CC.value&=Mark[3]^Power[0];
			transfer(RX, XI);
			transfer(RY, RI);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RX.value]);
			transfer(OP[1], R[RY.value]);
			if(OP[0].value==OP[1].value) {
				CC.value|=Power[0];
			}
		}//TRR rx, ry
		
		//AND rx, ry
		else if(OPCODE.value==19) {
			transfer(RX, XI);
			transfer(RY, RI);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RX.value]);
			transfer(OP[1], R[RY.value]);
			transfer(R[RX.value], OP[0].value&OP[1].value);
		}//AND rx, ry

		//ORR rx, ry
		else if(OPCODE.value==20) {
			transfer(RX, XI);
			transfer(RY, RI);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RX.value]);
			transfer(OP[1], R[RY.value]);
			transfer(R[RX.value], OP[0].value|OP[1].value);
		}//ORR rx, ry
		
		//NOT rx
		else if(OPCODE.value==21) {
			transfer(RX, XI);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RX.value]);
			transfer(R[RX.value], ~OP[0].value);
		}//NOT rx
		
		//SRC r, A/L, L/R, count
		else if(OPCODE.value==25) {
			transfer(COUNT, ADDRESS);
			transfer(AL, I);
			transfer(LR, T);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RI.value]);
			if(COUNT.value==0)
				return;
			COUNT.value=COUNT.value%20;
			int t=OP[0].value&Power[19];
			if(LR.value==0) {
				transfer(RES[0], OP[0].value>>COUNT.value);
			}
			else {
				transfer(RES[0], OP[0].value<<COUNT.value);
			}
			if(AL.value==0)
				transfer(RES[0], RES[0].value&Mark[18]|t);
			transfer(R[RI.value], RES[0]);
		}//SRC A/L, L/R, r, count
		
		//RRC A/L, L/R, r, count
		else if(OPCODE.value==26) {
			transfer(COUNT, ADDRESS);
			transfer(AL, I);
			transfer(LR, T);
			transfer(CONTRL, OPCODE);
			transfer(OP[0], R[RI.value]);
			if(COUNT.value==0)
				return;
			int t=OP[0].value&Power[19], tt;
			COUNT.value=COUNT.value%20;
			if(AL.value==0) {
				OP[0].value=OP[0].value&Mark[18];
				if(LR.value==0) {
					tt=OP[0].value&Mark[COUNT.value-1];
					tt=tt<<(19-COUNT.value);
					OP[0].value=OP[0].value>>COUNT.value;
					transfer(OP[0], OP[0].value|tt);
				}
				else {
					tt=OP[0].value&(Mark[18]^Mark[18-COUNT.value]);
					tt=tt>>(19-COUNT.value);
					OP[0].value=OP[0].value<<COUNT.value;
					OP[0].value=OP[0].value|tt;
					transfer(OP[0], OP[0].value|tt);
				}
				transfer(RES[0], OP[0].value|t);
			}
			else {
				if(LR.value==0) {
					tt=OP[0].value&Mark[COUNT.value-1];
					tt=tt<<(20-COUNT.value);
					OP[0].value=OP[0].value>>COUNT.value;
					transfer(RES[0], OP[0].value|tt);
				}
				else {
					tt=OP[0].value&(Mark[18]^Mark[19-COUNT.value]);
					tt=tt>>(20-COUNT.value);
					OP[0].value=OP[0].value<<COUNT.value;
					OP[0].value=OP[0].value|tt;
					transfer(RES[0], OP[0].value|tt);
				}
			}
			transfer(R[RI.value], RES[0]);
			
		}//RRC A/L, L/R, r, count
		/*********************************arithmetic/logical***************************************************/
		
		
		
		
		/*********************************transfer*************************************************************/
		//JZ I, r, x, address
		else if(OPCODE.value == 8) {
			if(R[RI.value].value==0)
				transfer(BOP[0], 1);
			else 
				transfer(BOP[0], 0);
			transfer(BOP[1], EA);
		}//JZ I, r, x, address
		
		//JNE I, r, x, address
		else if(OPCODE.value == 9) {
			if(R[RI.value].value!=0)
				transfer(BOP[0], 1);
			else 
				transfer(BOP[0], 0);
			transfer(BOP[1], EA);
		}//JNE I, r, x, address
		
		//JCC I, cc, x, address
		else if(OPCODE.value == 10) {
			transfer(CCI, 3-RI.value);
			if( (CC.value&Power[CCI.value])==Power[CCI.value] )
				transfer( BOP[0], 1);
			else transfer(BOP[0], 0);
			transfer(BOP[1], EA);
		}//JCC I, cc, x, address
		
		//JMP I, x, address
		else if(OPCODE.value == 11) {
			transfer(BOP[0], 1);
			transfer(BOP[1], EA);
		}//JMP I, x, address
		
		//JSR I, x, address
		else if(OPCODE.value == 12) {
			transfer(R[3], PC.value+1);
			transfer(BOP[0], 1);
			transfer(BOP[1], EA);
		}//JSR I, x, address
		
		//RFS immed
		else if(OPCODE.value == 13) {
			transfer(BOP[0], 1);
			transfer(BOP[1], EA);
		}//RFS immed
		
		//SOB I, r, x, address
		else if(OPCODE.value == 14) {
			transfer(OP[0], 1);
			transfer(OP[1], R[RI.value]);
			transfer(CONTRL, OPCODE);
			transfer(RES[0], OP[1].value - OP[0].value);
			transfer(R[RI.value], RES[0]);
			if(R[RI.value].value>0) {
				transfer(BOP[0], 1);
			}
			else {
				transfer(BOP[0], 0);
			}
			transfer(BOP[1], EA);
		}//SOB I, r, x, address
		
		//JGE I, r, x, address
		else if(OPCODE.value == 15) {
			if(R[RI.value].TrueValue()>=0) {
				transfer(BOP[0], 1);
			}
			else {
				transfer(BOP[0], 0);
			}
			transfer(BOP[1], EA);
		}//JGE I, r, x, address
		/*********************************transfer*************************************************************/
		
		
		/*********************************IO******************************************************************/
		//IN r, devid
		else if(OPCODE.value==49) {
			transfer(DEV, ADDRESS);
			transfer(R[RI.value], gwuIO.in(DEV.value));
		}//IN r, devid
		
		//OUT r, devid
		else if(OPCODE.value==50) {
			transfer(DEV, ADDRESS);
			gwuIO.out(R[RI.value].value, DEV.value);
		}//OUT r, devid
		
		//CHK r, devid
		else if(OPCODE.value==51) {
			transfer(DEV, ADDRESS);
			transfer(R[RI.value], gwuIO.chk(DEV.value));
		}//CHK r, devid
		

	}

	public void storeResult(Memory gwuMemory, IO gwuIO) {


		// AMR I, r, x:address
		if (OPCODE.value == 4) {
			transfer(R[RI.value], RES[0]);
		}// AMR I, r, x:address

		// SMR I, r, x:address
		else if (OPCODE.value == 5) {
			transfer(R[RI.value], RES[0]);
		}// SMR I, r, x:address

	}

	/**************************************micro-instruction*******************************/
	
	public static void main(String[] args) {		//used for debug
		Cpu aCpu = new Cpu();
		aCpu.powerup();
		Memory gwuMemory = new Memory();
		IO gwuIO = new IO();
		gwuMemory.powerup();
		gwuIO.powerup();
		aCpu.transfer(aCpu.R[0], -131073);
		System.out.println(Compiler.DecimalToBinary(Integer.toString(-1)));
		aCpu.OPCODE.value=26;
		aCpu.I.value=1;
		aCpu.T.value=1;
		aCpu.ADDRESS.value=2;
		aCpu.execute(gwuMemory, gwuIO);
	}

}
