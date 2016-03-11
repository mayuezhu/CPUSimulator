package com.gwu.architecture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CardReader {
	
	/*
	 * 0:read next line
	 * 1:write next line		//currently, not support
	 * 2:change current line
	 * 3:check current line
	 * 4:check the length of visual card
	 * 5:check last operation
	 * 6: change command
	 */
	
	int command;
	int curLine;
	int fileLength;
	File cardFile;
	InputStreamReader fileStreamReader;
	InputStream fileStream;
	char[] cardChar;
	String logString;
	String cardString;
	String[] cardStrings;
	int flag;
	
	public CardReader() {
		fileStream = this.getClass().getResourceAsStream("Card1");
		fileStreamReader = new InputStreamReader(fileStream);
		logString = new String();
		
		
	}
	
	public void powerup() {
		
		logString = "";
		flag=1;
		init();
	}

	public void powerdown() {
		
	}

	public void destroy() {
		
	}

	public void init() {
		try {
			cardChar = new char[fileStream.available()];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logString+=e;
		}
		try {
			fileStreamReader.read(cardChar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logString+=e;
		}
		cardString = new String(cardChar);
		
		command = 6;
		
		cardStrings = cardString.split("\n");
		fileLength = cardStrings.length;
		for(int i=0; i<fileLength; i++) {
			cardStrings[i]=cardStrings[i].replaceAll("[^0-9]", "");;
		}
		curLine=0;
	}
	
	/***************************************operation for other class
	 * @throws IOException *******************************************/
	public void loadCard(String string) throws IOException {
		String[] tStrings;
		String tString;
		InputStream inputStream = new FileInputStream(string);
		char[] tchar;
		
		//read assembly file to memory
		InputStreamReader reader = new InputStreamReader(inputStream);
		tchar = new char[inputStream.available()];
		reader.read(tchar);
		tString = new String(tchar);
		tStrings = tString.split("\n");
		fileLength = tStrings.length;
		cardStrings = new String[fileLength+1];
		reader.close();
		for(int i=0; i<fileLength; i++) {
			tStrings[i]=tStrings[i].replaceAll("\r", "\n");
			cardStrings[i] = new String(Compiler.AssemblyToInteger(tStrings[i]));
		}
	}
	
	public void loadCard1(String string) throws IOException {
		String[] tStrings;
		String tString;
		InputStream inputStream = new FileInputStream(string);
		char[] tchar;
		
		//read assembly file to memory
		InputStreamReader reader = new InputStreamReader(inputStream);
		tchar = new char[inputStream.available()];
		reader.read(tchar);
		tString = new String(tchar);
		tStrings = tString.split("\n");
		fileLength = tStrings.length;
		cardStrings = new String[fileLength+1];
		reader.close();
		for(int i=0; i<fileLength; i++) {
			tStrings[i].replaceAll("\r", "");
			cardStrings[i] = tStrings[i];
		}
	}
	
	/***************************************operation for in/out/chk command*************************************/
	
	public int in() {
		int res=0;
		if(command==0) {
			if(curLine>=fileLength) {
				flag=0;
				res=0;
				logString+="(command="+command+")in: cannot read " + curLine + " line!!!\n";
				System.out.println("(command="+command+")in: cannot read " + curLine + " line!!!");
			}
			else {
				flag=1;
				res = Integer.parseInt(cardStrings[curLine++]);
			}
		}
		else {
			flag=0;
			logString+="(command="+command+")in: command is not valid!!!\n";
			System.out.println("(command="+command+")in: command is not valid!!!");
			res=0;
		}
		return res;
	}
	
	public void out(int c) {
		if(command==6) {
			command=c;
		}
		else if(command==1){
			logString+="(command="+command+")out " + c + ": Currently, not support!!!\n";
			System.out.println("(command="+command+")out " + c + ": Currently, not support!!!");
			flag=0;
		}
		else if(command==2) {
			curLine=c;
			flag=1;
		}
		else {
			logString+="(command="+command+")out " + c +": command is not valid!!!\n";
			System.out.println("(command="+command+")out " + c +": command is not valid!!!");
			flag=0;
		}
	}
	
	public int chk() {
		int res=-1;
		if(command==3) {
			res = curLine;
			flag=1;
		}
		else if(command==4) {
			res=fileLength;
			flag=1;
		}
		else if(command==5) {
			res=flag;
			flag=1;
		}
		else {
			logString+="(command="+command+")chk: command is not valid!!!\n";
			System.out.println("(command="+command+")chk: command is not valid!!!");
			flag=0;
		}
		command=6;
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		CardReader cardReader = new CardReader();
		cardReader.powerup();
		cardReader.loadCard("D:/wzhmmq/workspace/architecture/card");
		System.out.println(cardReader.cardStrings[0]);
	}
	
}
