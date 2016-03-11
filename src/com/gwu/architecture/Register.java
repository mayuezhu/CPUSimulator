package com.gwu.architecture;

public class Register {

	class structure { // the class of register, such as PC, IR
		int value;
		int bits;

		structure(int v, int b) {
			value = v;
			bits = b;
		}
	}

	int[] Mark;

	/*************************************************************************************/
	// the built-in register in our computer simulator
	structure PC;
	structure IR;
	structure EA;
	structure TEMP;
	structure COUNT;
	structure CONTRL;

	structure MAR;
	structure MBR;
	structure MSR;
	structure MFR;

	structure[] X;
	structure[] R;
	structure[] OP;
	structure[] RES;

	structure I;
	structure T;
	structure XI;
	structure RI;
	structure CC;
	structure OPCODE;
	structure ADDRESS;

	/*****************************************************************************/

	public Register() {

		PC = new structure(0, 13);
		IR = new structure(0, 20);
		EA = new structure(0, 8);

		TEMP = new structure(0, 20);
		COUNT = new structure(0, 5);
		CONTRL = new structure(0, 6);
		MAR = new structure(0, 13);
		MBR = new structure(0, 20);
		MSR = new structure(0, 20);
		MFR = new structure(0, 20);
		OPCODE = new structure(0, 6);
		RI = new structure(0, 2);
		XI = new structure(0, 2);
		I = new structure(0, 1);
		T = new structure(0, 1);
		ADDRESS = new structure(0, 6);

		X = new structure[3];
		for (int i = 0; i < 3; i++)
			X[i] = new structure(0, 13);

		R = new structure[4];
		for (int i = 0; i < 4; i++)
			R[i] = new structure(0, 20);

		OP = new structure[2];
		for (int i = 0; i < 2; i++)
			OP[i] = new structure(0, 20);

		RES = new structure[2];
		for (int i = 0; i < 2; i++)
			RES[i] = new structure(0, 20);

		Mark = new int[21];
		Mark[0] = 1;
		for (int i = 1; i < 21; i++)
			Mark[i] = Mark[i - 1] << 1;
		for (int i = 0; i < 20; i++)
			Mark[i] = Mark[i + 1] - 1;

	}

	public void powerdown() { // shutdown the computer
	}

	public void powerup() { // start up the computer, all the register cleared
							// to 0

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
		ADDRESS.bits = 6;
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
		CC = new structure(0, 4);
		RES[0].bits = 20;
		RES[0].value = 0;
		RES[1].bits = 20;
		RES[1].value = 0;

	}

	public void destroy() { // collect memory

	}

	public void init() { // init register

	}

	/****************************************************************************************************/
	/*
	 * routine for debug
	 */

	public void show() { // show some important register value
		System.out.printf("PC:  %d\n", PC.value);
		System.out.printf("X[0] X[1] X[2]:  %d %d %d\n", X[0].value,
				X[1].value, X[2].value);
		System.out.printf("R[0] R[1] R[2] R[3]:  %d %d %d\n", R[0].value,
				R[1].value, R[2].value, R[3].value);
	}

	public void changeRegister(structure reg, int value) { // change register
															// value
		transfer(reg, value);
	}

	/****************************************************************************************************/

	/****************************************************************************************************/
	/*
	 * routine for tranfer value to register
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

	public void transfer(structure reg1, structure reg2) { // transfer the value
															// of reg1 to reg2
		reg1.value = transferValue(reg2.value, reg1.bits);
	}

	public void transfer(structure reg1, structure reg2, int sbit, int ebit) { // transfer
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

	public void transfer(structure reg, int value) { // transfer value to reg,
														// value is int not
														// class structure
		reg.value = transferValue(value, reg.bits);
	}

	public void fetchData(structure add, structure reg, Memory gwuMemory) { // transfer
																			// [add]
																			// to
																			// reg
		transfer(MAR, add);
		transfer(MBR, gwuMemory.fetchData(MAR.value));
		transfer(reg, MBR);
	}

	public void storeData(structure add, structure reg, Memory gwuMemory) { // store
																			// reg
																			// in
																			// [add]
		transfer(MAR, add);
		transfer(MBR, reg);
		gwuMemory.storeData(MAR.value, MBR.value);
	}

	/********************************************************************************/

	public void nextInstruction() { // get next instruction address
		transfer(PC, PC.value + 1);
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
		if (I.value == 0) { // if I is 0
			if (XI.value == 0) {
				transfer(EA, ADDRESS);
			} else {
				transfer(EA, ADDRESS.value + X[XI.value].value);
			}
		} else { // else if I is not 0
			if (XI.value == 0) {
				transfer(EA, ADDRESS);
			} else {
				transfer(EA, ADDRESS.value + X[XI.value].value);
			}
			fetchData(EA, EA, gwuMemory);
		}
	}

	public void execute(Memory gwuMemory, IO gwuIO) { // do execute

		// LDR I,r,x:address
		if (OPCODE.value == 1) {
			if (I.value == 1) {
				fetchData(EA, EA, gwuMemory);
				fetchData(EA, R[RI.value], gwuMemory);
			} else {
				fetchData(EA, R[RI.value], gwuMemory);
			}
		}// LDR I,r,x:address

		// STR I,r,x:address
		else if (OPCODE.value == 2) {
			if (I.value == 1) {
				fetchData(EA, EA, gwuMemory);
				storeData(EA, R[RI.value], gwuMemory);
			} else {
				storeData(EA, R[RI.value], gwuMemory);
			}
		}// STR I,r,x:address

		else if (OPCODE.value == 4) {
			fetchData(EA, OP[1], gwuMemory);
			transfer(R[RI.value], OP[2]);
			transfer(OPCODE, CONTRL);
			transfer(RES[0], OP[1].value + OP[2].value);
		}// AMR I, r, x:address

	}

	public void storeResult(Memory gwuMemory, IO gwuIO) {

		// LDR I,r,x:address
		if (OPCODE.value == 1) {

		}// LDR I,r,x:address

		// STR I,r,x:address
		else if (OPCODE.value == 2) {

		}// STR I,r,x:address

		// AMR I, r, x:address
		else if (OPCODE.value == 4) {
			transfer(R[RI.value], RES[0]);
		}// AMR I, r, x:address
	}

	/************************************************************************************************/

}
