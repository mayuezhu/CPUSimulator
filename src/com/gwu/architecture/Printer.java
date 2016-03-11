package com.gwu.architecture;

public class Printer {
	
	GUI gwuGui;
	String logString;
	
	public Printer() {
		logString = new String();
	}
	
	public void powerup() {
		logString="";
	}

	public void powerdown() {

	}

	public void destroy() {

	}

	public void init(GUI gui) {
		gwuGui=gui;
	}
	
	public int in() {
		logString+="Do not support chk!!!\n";
		System.out.println("Do not support chk!!!");
		return 0;
	}
	
	public void out(int c) {
		synchronized (ConsolePanel.class) {
			while(gwuGui.gwuConsolePanel.consoleLock == 1);	//lock, serialize the write
			gwuGui.gwuConsolePanel.consoleLock = 1;
			gwuGui.gwuConsolePanel.append(c);
			gwuGui.gwuConsolePanel.consoleLock = 0;
		}
	}
	
	public int chk() {
		logString+="Do not support chk!!!\n";
		System.out.println("Do not support chk!!!");
		return 0;
	}
}
