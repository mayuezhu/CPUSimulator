package com.gwu.architecture;

public class IO {
	
	Keyboard gwuKeyboard;
	Printer gwuPrinter;
	CardReader gwuCardReader;

	public IO() {
		gwuCardReader = new CardReader();
		gwuPrinter = new Printer();
		gwuKeyboard = new Keyboard();
	}

	public void powerup() {
		gwuCardReader.powerup();
		gwuPrinter.powerup();
		gwuKeyboard.powerup();
	}

	public void powerdown() {
		gwuCardReader.powerdown();
		gwuPrinter.powerdown();
		gwuKeyboard.powerdown();
	}

	public void destroy() {
		gwuCardReader.destroy();
		gwuKeyboard.destroy();
		gwuPrinter.destroy();
	}

	public void init(GUI gwuGui) {
		gwuCardReader.init();
		gwuKeyboard.init(gwuGui);
		gwuPrinter.init(gwuGui);
	}
	
	public int in(int devid) {
		if(devid==0) {
			return gwuKeyboard.in();
		}
		else if(devid==1) {
			return gwuPrinter.in();
		}
		else if(devid==2) {
			return gwuCardReader.in();
		}
		else {
			return -1;
		}
	}
	
	public void out(int c, int devid) {
		if(devid==0) {
			gwuKeyboard.out(c);
		}
		else if(devid==1) {
			gwuPrinter.out(c);
		}
		else if(devid==2) {
			gwuCardReader.out(c);
		}
		else {
		}
	}
	
	public int chk(int devid) {
		if(devid==0) {
			return gwuKeyboard.chk();
		}
		else if(devid==1) {
			return gwuPrinter.chk();
		}
		else if(devid==2) {
			return gwuCardReader.chk();
		}
		else {
			return -1;
		}
	}

	/****************************************************************************************************/

}
