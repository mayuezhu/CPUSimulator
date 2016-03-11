package com.gwu.architecture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

//ConsolePanel class if defined 
public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextArea consoleTextArea;
	GridBagLayout layout;
	GridBagConstraints constraints;
	Computer gwuComputer;
	GUI gwuGui;
	int flag;
	int keycode;
	int consoleLock;

	// ConsolePanel generator function
	public ConsolePanel(GUI gui) {
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		gwuGui = gui;
	}

	// ConsolePanel init function
	public void init(Computer computer) {
		
		gwuComputer = computer;
		consoleLock = 0;
		
		/********************************console panel ui init************************************/
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;

		setLayout(layout);
		consoleTextArea = new JTextArea();

		setConstraints(0, 0, 1, 1, 4, 3);
		this.add(new JScrollPane(consoleTextArea), constraints);
		consoleTextArea.setLineWrap(true);
		consoleTextArea.setWrapStyleWord(true);
		setVisible(true);
		setBorder(BorderFactory.createEtchedBorder());
		/********************************console panel ui init************************************/
		
		flag = 0;
		keycode=-1;
		// Keylistener is added to detect the input from the consolepanel
		consoleTextArea.addKeyListener(new KeyAdapter() {		//when type some letters in console
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==16)
					return ;
				synchronized (ConsolePanel.class) {
					keycode = e.getKeyCode();
				}
			}
		});

		

	}

	public void powerup() {
		flag = 1;
		consoleTextArea.setText("");
	}

	public void powerdown() {
		flag = 0;
	}

	public void destroy() {

	}

	public void refresh() {
		if (flag == 0) {

		} else {

		}
	}

	/************************************** operation for panel *******************************************/
	public void append(String str) {		//add letters in console
		consoleTextArea.append(str);
		try {
			int t = consoleTextArea.getLineCount() - 1;
			t = consoleTextArea.getLineEndOffset(t);
			consoleTextArea.setCaretPosition(t);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public void append(int a) {
		char[] t1 = new char[1];
		t1[0]=(char)a;
		String tString=new String(t1);
		consoleTextArea.append(tString);
		try {
			int t = consoleTextArea.getLineCount() - 1;
			t = consoleTextArea.getLineEndOffset(t);
			consoleTextArea.setCaretPosition(t);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	public void setConstraints(int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty) {		//used for set panel's ui
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridheight = gridheight;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

}
