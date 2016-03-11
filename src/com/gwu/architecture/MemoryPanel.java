package com.gwu.architecture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class MemoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Computer gwuComputer;
	GUI gwugGui;
	Memory gwuMemory;
	GridBagConstraints constraints;
	GridBagLayout layout;
	JTextArea textArea;
	JScrollPane scrollPane;
	int flag;

	public MemoryPanel(GUI gui) {
		gwugGui = gui;
		constraints = new GridBagConstraints();
		layout = new GridBagLayout();
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
	}

	public void init(Computer computer) {
		gwuComputer = computer;
		gwuMemory = gwuComputer.gwuMemory;
		flag = 0;
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(layout);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		setConstraints(0, 0, 1, 1, 1, 1);
		this.add(new JScrollPane(scrollPane), constraints);
	}

	public void powerup() {
		flag = 1;
		clear();
		for (int i = 0; i < gwuMemory.MEMORY_SIZE; i++) {
			int t = gwuMemory.fetchData(i);
			String tt = "<" + Integer.toString(i) + ">" + ": "
					+ convertToBinary(t, 20) + "\n";
			append(tt);
		}
	}

	public void powerdown() {
		flag = 0;
		clear();

	}

	public void destroy() {

	}

	public void refresh() {
		if (flag == 1) {
			clear();
			for (int i = 0; i < gwuMemory.MEMORY_SIZE; i++) {
				int t = gwuMemory.fetchData(i);
				String tt = "<" + Integer.toString(i) + ">" + ": "
						+ convertToBinary(t, 20) + "\n";
				append(tt);
			}
		} 
		else {

		}
	}

	/************************************** operation for panel *******************************************/
	public void clear() {
		textArea.setText("");
	}

	public void append(String str) {
		textArea.append(str);
		try {
			int t = textArea.getLineCount() - 1;
			t = textArea.getLineEndOffset(t);
			textArea.setCaretPosition(t);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	public void setConstraints(int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty) {
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridheight = gridheight;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	public String convertToBinary(int value, int bits) {
		String t = Integer.toBinaryString(value);
		int len = t.length();
		for (int i = len; i < bits; i++)
			t = "0" + t;
		return t;
	}

}
