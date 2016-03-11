package com.gwu.architecture;

public class Keyboard {
	
	GUI gwuGui;
	String logString;
	
	public Keyboard() {
		logString=new String();
	}
	
	public void powerup() {
		logString="";
	}

	public void powerdown() {

	}

	public void destroy() {

	}

	public void init(GUI Gui) {
		gwuGui = Gui;
	}
	
	public int in() {
		int res=0;
		while(true) {		//wait for keyboard input from consolepanel
			synchronized (ConsolePanel.class) {		//make sure this thread can read immediately read the change of keycode in gwuconsolepanel
				res=gwuGui.gwuConsolePanel.keycode;
				gwuGui.gwuConsolePanel.keycode=-1;
			}
			if(res!=-1)
				break;
		}
		return res;
	}
	
	public void out(int c) {		//keyboard don't support out
		logString+="Do not support out!!!\n";
		System.out.println("Do not support out!!!");
	}
	
	public int chk() {		//keyboard don't support chk
		logString+="Do not support chk!!!\n";
		System.out.println("Do not support chk!!!");
		return 0;
	}
	
}
