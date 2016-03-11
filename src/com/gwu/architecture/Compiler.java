package com.gwu.architecture;

public class Compiler {

	public static String DecimalToBinary(String a) {		//used for converting decimal to binary

		a=a.replaceAll("\n", "");
		a=a.replaceAll("[^a-zA-Z0-9- ]", "");
		int t, i;
		int l = a.length();
		if (l > 11)
			return "";
		for (i = 0; i < l; i++) {
			if(a.charAt(i)=='-')
				continue;
			if ( (a.charAt(i) > '9') || (a.charAt(i) < '0') ) {
				return "";
			}
		}
		t = Integer.parseInt(a);
		return Integer.toBinaryString(t);

	}

	public static int BinaryToDecimal(String a) {		//used for converting binary to decimal
		a=a.replaceAll("\n", "");
		a=a.replaceAll("[^a-zA-Z0-9 ]", "");
		int instruction = 0, t, i;
		int l = a.length();
		if ((a.charAt(l - 1) > '9') || (a.charAt(l - 1) < '0')) {
			l--;
		}
		char temp;
		if (l > 20)
			return -1;
		for (i = 0; i < l; i++) {
			instruction <<= 1;
			temp = a.charAt(i);
			t = temp - '0';
			if ((t < 0) || (t > 1))
				break;
			instruction += t;
		}
		if (i == l)
			return instruction;
		else
			return -1;
	}
	
	public static String AssemblyToBinary(String a) {		//used for converting assembly to integer
		return DecimalToBinary(AssemblyToInteger(a));
	}

	public static String AssemblyToInteger(String a) {		//used for converting assembly to integer
		String tt=new String(a);
		a=a.replaceAll("\n", "");
		a=a.replaceAll("[^a-zA-Z0-9 ]", "");
		String[] tString = a.split(" +");
		int l = tString.length;
		int instruction = 0;
		int i, temp = 0, opcode = 0;
		for (i = 0; i < l; i++) {
			
			if (i == 0) { // the first assembly
				if (tString[i].equals("LDR")) {
					instruction += 1 << 14;
				}
				else if (tString[i].equals("STR")) {
					instruction += 2 << 14;
				}
				else if (tString[i].equals("LDA")) {
					instruction += 3 << 14;
				}
				else if (tString[i].equals("AMR")) {
					instruction += 4 << 14;
				}
				else if (tString[i].equals("SMR")) {
					instruction += 5 << 14;
				}
				else if (tString[i].equals("AIR")) {
					instruction += 6 << 14;
				}
				else if (tString[i].equals("SIR")) {
					instruction += 7 << 14;
				}
				else if (tString[i].equals("JZ")) {
					instruction += 8 << 14;
				}
				else if (tString[i].equals("JNE")) {
					instruction += 9 << 14;
				}
				else if (tString[i].equals("JCC")) {
					instruction += 10 << 14;
				}
				else if (tString[i].equals("JMP")) {
					instruction += 11 << 14;
				}
				else if (tString[i].equals("JSR")) {
					instruction += 12 << 14;
				}
				else if (tString[i].equals("RFS")) {
					instruction += 13 << 14;
				}
				else if (tString[i].equals("SOB")) {
					instruction += 14 << 14;
				}
				else if (tString[i].equals("JGE")) {
					instruction += 15 << 14;
				}
				else if (tString[i].equals("MLT")) {
					instruction += 16 << 14;
				}
				else if (tString[i].equals("DVD")) {
					instruction += 17 << 14;
				}
				else if (tString[i].equals("TRR")) {
					instruction += 18 << 14;
				}
				else if (tString[i].equals("AND")) {
					instruction += 19 << 14;
				}
				else if (tString[i].equals("ORR")) {
					instruction += 20 << 14;
				}
				else if (tString[i].equals("SRC")) {
					instruction += 25 << 14;
				}
				
				else if (tString[i].equals("RRC")) {
					instruction += 26 << 14;
				}
				
				else if (tString[i].equals("NOT")) {
					instruction += 21 << 14;
				}
				
				else if (tString[i].equals("LDX")) {
					instruction += 33 << 14;
					System.out.println("Case "+i+":");
					System.out.println(instruction);
				}
				else if (tString[i].equals("STX")) {
					instruction += 34 << 14;
				}
				
				else if (tString[i].equals("IN")) {
					instruction += 49 << 14;
				}
				
				else if (tString[i].equals("OUT")) {
					instruction += 50 << 14;
				}
				
				else if (tString[i].equals("CHK")) {
					instruction += 51 << 14;
				}
				
				else { // other instructions waited to be added
					break;
				}
				opcode = instruction >> 14;
			}

			else if (i == 1) { // the second assembly
				temp = Integer.parseInt(tString[i]);
				if (opcode == 1) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				} else if (opcode == 2) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}

				else if (opcode == 3) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}

				else if (opcode == 4) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}

				else if (opcode == 5) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}

				else if (opcode == 6) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 7) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 8) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 9) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 10) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 11) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 12) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 13) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 14) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 15) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 16) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 17) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 18) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 19) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 20) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 21) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 25) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 26) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 33) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}

				else if (opcode == 34) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 9;
				}
				
				else if (opcode == 49) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 50) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 51) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else { // other instruction waited to be added
					break;
				}
			}

			else if (i == 2) { // the third assembly
				temp = Integer.parseInt(tString[i]);
				if (opcode == 1) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 2) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 3) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 4) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 5) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 6) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 7) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 8) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 9) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 10) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 11) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 12) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 13) {
					break;
				}
				
				else if (opcode == 14) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 15) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 16) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 17) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 18) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 19) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 20) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 21) {
					break;
				}
				
				else if (opcode == 25) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 8;
				}
				
				else if (opcode == 26) {
					if (temp > 1 || (temp < 0)) {
						break;
					}
					instruction += temp << 8;
				}

				else if (opcode == 33) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}

				else if (opcode == 34) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 49) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 50) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 51) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else { // other instruction waited to be added
					break;
				}
			}

			else if (i == 3) {		//the forth assemble
				temp = Integer.parseInt(tString[i]);
				if (opcode == 1) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				} else if (opcode == 2) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}

				else if (opcode == 3) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}

				else if (opcode == 4) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}

				else if (opcode == 5) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}

				else if (opcode == 6) {
					break;
				}

				else if (opcode == 7) {
					break;
				}
				
				else if (opcode == 8) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 9) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 10) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 11) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 12) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 13) {
					break;
				}
				
				else if (opcode == 14) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 15) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 12;
				}
				
				else if (opcode == 16) {
					break;
				}
				
				else if (opcode == 17) {
					break;
				}
				
				else if (opcode == 18) {
					break;
				}
				
				else if (opcode == 19) {
					break;
				}
				
				else if (opcode == 20) {
					break;
				}
				
				else if (opcode == 21) {
					break;
				}
				
				else if (opcode == 25) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}
				
				else if (opcode == 26) {
					if ((temp > 3) || (temp < 0)) {
						break;
					}
					instruction += temp << 10;
				}

				else if (opcode == 33) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 34) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 49) {
					break;
				}
				
				else if (opcode == 50) {
					break;
				}
				
				else if (opcode == 51) {
					break;
				}
				
				else { // other instruction waited to be added
					break;
				}
			}

			else if (i == 4) {	//the fifth assemble
				temp = Integer.parseInt(tString[i]);
				if (opcode == 1) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				} else if (opcode == 2) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 3) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 4) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 5) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 6) {
					break;
				}

				else if (opcode == 7) {
					break;
				}
				
				else if (opcode == 8) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 9) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 10) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 11) {
					break;
				}
				
				else if (opcode == 12) {
					break;
				}
				
				else if (opcode == 13) {
					break;
				}
				
				else if (opcode == 14) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 15) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 16) {
					break;
				}
				
				else if (opcode == 17) {
					break;
				}
				
				else if (opcode == 18) {
					break;
				}
				
				else if (opcode == 19) {
					break;
				}
				
				else if (opcode == 20) {
					break;
				}
				
				else if (opcode == 21) {
					break;
				}
				
				else if (opcode == 25) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}
				
				else if (opcode == 26) {
					if ((temp < 0) || (temp > 255)) {
						break;
					}
					instruction += temp;
				}

				else if (opcode == 33) {
					break;
				}

				else if (opcode == 34) {
					break;
				}
				
				else if (opcode == 49) {
					break;
				}

				else if (opcode == 50) {
					break;
				}
				
				else if (opcode == 51) {
					break;
				}
				
				else { // other instruction waited to be added
					break;
				}
			}

			else if (i == 5) { // the sixth assemble other instruction waited to be added
				temp = Integer.parseInt(tString[i]);
				break;
			}

			else {
				break;
			}

		}
		if (i == l) {
			//System.out.println(Integer.toString(instruction));
			return Integer.toString(instruction);
		}
		else {
			char[] tchar;
			int j;
			tchar = tt.toCharArray();
			int tl=tchar.length;
			if(tl==1) {
			//	System.out.println(Integer.toString(tchar[0]));
				return Integer.toString(tchar[0]);
			}
			tt=tt.replace("\n", "");
			tchar = tt.toCharArray();
			tl=tchar.length;
			if(tl==1) {
			//	System.out.println(tchar[0]);
				return Integer.toString(tchar[0]);
			}
			for(j=0; j<tl; j++) {
				if( (tchar[j]>'9') || (tchar[j]<'0') )
					break;
			}
			if(j==tl) {
			//	System.out.println(Integer.toString(Integer.parseInt(tt)));
				return Integer.toString(Integer.parseInt(tt));
			}
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(AssemblyToBinary("LDX 0 0 0"));
	}

}
