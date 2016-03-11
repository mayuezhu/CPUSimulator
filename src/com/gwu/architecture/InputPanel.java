package com.gwu.architecture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Computer gwuComputer;
	Cpu gwuCpu;
	GUI gwuGui;
	GridBagConstraints constraints;
	GridBagLayout layout;

	JLabel[] label;
	JTextField[] textField;
	JButton[] button;

	int flag;

	public InputPanel(GUI gui) {
		gwuGui = gui;
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();

		label = new JLabel[17];
		label[0] = new JLabel("PC:");
		label[1] = new JLabel("IR(decimal):");
		label[2] = new JLabel("XI:");
		label[3] = new JLabel("RI:");
		label[4] = new JLabel("EA:");
		label[5] = new JLabel("MAR:");
		label[6] = new JLabel("MBR:");
		label[7] = new JLabel("X[1]:");
		label[8] = new JLabel("X[2]:");
		label[9] = new JLabel("X[3]:");
		label[10] = new JLabel("R[0]:");
		label[11] = new JLabel("R[1]:");
		label[12] = new JLabel("R[2]:");
		label[13] = new JLabel("R[3]:");
		label[14] = new JLabel("IR(binary):");
		label[15] = new JLabel("IR(assembly):");
		label[16]= new JLabel("CC:");

		textField = new JTextField[17];
		textField[0] = new JTextField(10);
		textField[1] = new JTextField(10);
		textField[2] = new JTextField(10);
		textField[3] = new JTextField(10);
		textField[4] = new JTextField(10);
		textField[5] = new JTextField(10);
		textField[6] = new JTextField(10);
		textField[7] = new JTextField(10);
		textField[8] = new JTextField(10);
		textField[9] = new JTextField(10);
		textField[10] = new JTextField(10);
		textField[11] = new JTextField(10);
		textField[12] = new JTextField(10);
		textField[13] = new JTextField(10);
		textField[14] = new JTextField(10);
		textField[15] = new JTextField(10);
		textField[16] = new JTextField(10);

		button = new JButton[3];
		button[0] = new JButton("save");
		button[1] = new JButton("save");
		button[2] = new JButton("save");

	}

	public void init(Computer computer) {

		gwuComputer = computer;
		gwuCpu = computer.gwuCpu;
		
		/******************************init input panel's ui**********************************/
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(layout);

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;

		// constraints.ipadx=30;
		// constraints.ipady=30;

		// PC
		setConstraints(0, 0, 1, 1, 4, 3);
		add(label[0], constraints);
		setConstraints(1, 0, 1, 1, 4, 3);
		add(textField[0], constraints);
		// IR(decimal)
		setConstraints(0, 1, 1, 1, 4, 3);
		add(label[1], constraints);
		setConstraints(1, 1, 1, 1, 4, 3);
		add(textField[1], constraints);
		// XI
		setConstraints(0, 2, 1, 1, 4, 3);
		add(label[2], constraints);
		setConstraints(1, 2, 1, 1, 4, 3);
		add(textField[2], constraints);
		// RI
		setConstraints(0, 3, 1, 1, 4, 3);
		add(label[3], constraints);
		setConstraints(1, 3, 1, 1, 4, 3);
		add(textField[3], constraints);
		// EA
		setConstraints(0, 4, 1, 1, 4, 3);
		add(label[4], constraints);
		setConstraints(1, 4, 1, 1, 4, 3);
		add(textField[4], constraints);
		// MAR
		setConstraints(0, 5, 1, 1, 4, 3);
		add(label[5], constraints);
		setConstraints(1, 5, 1, 1, 4, 3);
		add(textField[5], constraints);
		// MBR
		setConstraints(0, 6, 1, 1, 4, 3);
		add(label[6], constraints);
		setConstraints(1, 6, 1, 1, 4, 3);
		add(textField[6], constraints);
		// X[0]
		setConstraints(0, 7, 1, 1, 4, 3);
		add(label[7], constraints);
		setConstraints(1, 7, 1, 1, 4, 3);
		add(textField[7], constraints);
		// X[1]
		setConstraints(0, 8, 1, 1, 4, 3);
		add(label[8], constraints);
		setConstraints(1, 8, 1, 1, 4, 3);
		add(textField[8], constraints);
		// X[2]
		setConstraints(0, 9, 1, 1, 4, 3);
		add(label[9], constraints);
		setConstraints(1, 9, 1, 1, 4, 3);
		add(textField[9], constraints);
		// R[0]
		setConstraints(0, 10, 1, 1, 4, 3);
		add(label[10], constraints);
		setConstraints(1, 10, 1, 1, 4, 3);
		add(textField[10], constraints);
		// R[1]
		setConstraints(0, 11, 1, 1, 4, 3);
		add(label[11], constraints);
		setConstraints(1, 11, 1, 1, 4, 3);
		add(textField[11], constraints);
		// R[2]
		setConstraints(0, 12, 1, 1, 4, 3);
		add(label[12], constraints);
		setConstraints(1, 12, 1, 1, 4, 3);
		add(textField[12], constraints);
		// R[3]
		setConstraints(0, 13, 1, 1, 4, 3);
		add(label[13], constraints);
		setConstraints(1, 13, 1, 1, 4, 3);
		add(textField[13], constraints);
		// CC
		setConstraints(0, 14, 1, 1, 4, 3);
		add(label[16], constraints);
		setConstraints(1, 14, 1, 1, 4, 3);
		add(textField[16], constraints);

		// save button
		setConstraints(0, 15, 1, 1, 4, 3);
		add(button[0], constraints);

		// IR(binary)
		setConstraints(0, 16, 1, 1, 4, 3);
		add(label[14], constraints);
		setConstraints(1, 16, 1, 1, 4, 3);
		add(textField[14], constraints);
		setConstraints(2, 16, 1, 1, 4, 3);
		add(button[1], constraints);
		// IR(assembly)
		setConstraints(0, 17, 1, 1, 4, 3);
		add(label[15], constraints);
		setConstraints(1, 17, 1, 1, 4, 3);
		add(textField[15], constraints);
		setConstraints(2, 17, 1, 1, 4, 3);
		add(button[2], constraints);
		
		
		/******************************init input panel's ui**********************************/
		
		flag = 0;
		
		/*****************************when press the three save button***********************/
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					gwuCpu.transfer(gwuCpu.PC,
							Integer.parseInt(textField[0].getText()));
					gwuCpu.transfer(gwuCpu.IR,
							Integer.parseInt(textField[1].getText()));
					gwuCpu.transfer(gwuCpu.XI,
							Integer.parseInt(textField[2].getText()));
					gwuCpu.transfer(gwuCpu.RI,
							Integer.parseInt(textField[3].getText()));
					gwuCpu.transfer(gwuCpu.EA,
							Integer.parseInt(textField[4].getText()));
					gwuCpu.transfer(gwuCpu.MAR,
							Integer.parseInt(textField[5].getText()));
					gwuCpu.transfer(gwuCpu.MBR,
							Integer.parseInt(textField[6].getText()));
					gwuCpu.transfer(gwuCpu.X[0],
							Integer.parseInt(textField[7].getText()));
					gwuCpu.transfer(gwuCpu.X[1],
							Integer.parseInt(textField[8].getText()));
					gwuCpu.transfer(gwuCpu.X[2],
							Integer.parseInt(textField[9].getText()));
					gwuCpu.transfer(gwuCpu.R[0],
							Integer.parseInt(textField[10].getText()));
					gwuCpu.transfer(gwuCpu.R[1],
							Integer.parseInt(textField[11].getText()));
					gwuCpu.transfer(gwuCpu.R[2],
							Integer.parseInt(textField[12].getText()));
					gwuCpu.transfer(gwuCpu.R[3],
							Integer.parseInt(textField[13].getText()));
					gwuCpu.transfer(gwuCpu.CC,
							Integer.parseInt(textField[16].getText()));
					refresh();
				} else {

				}
			}
		});

		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (flag == 1) {
					int temp = Compiler.BinaryToDecimal(textField[14].getText());
					if (temp != -1) {
						gwuCpu.transfer(gwuCpu.IR, temp);
						textField[1].setText(Integer.toString(temp));
						refresh();
					} else {
						String a = "<" + textField[14].getText() + ">: "
								+ "Your IR(binary) is wrong!!!\n";
						System.out.print(a);
						gwuGui.gwuConsolePanel.append(a);
					}
				} else {

				}
			}
		});

		button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (flag == 1) {
					String temp=Compiler.AssemblyToInteger(textField[15].getText());
					if (temp != "") {
						gwuCpu.transfer(gwuCpu.IR, Integer.parseInt(temp));
						textField[1].setText(temp);
						refresh();
					} else {
						String a = "<" + textField[15].getText() + ">: "
								+ "Your assembly is wrong!!!\n";
						System.out.print(a);
						gwuGui.gwuConsolePanel.append(a);
					}

				} else {

				}
			}
		});
		/*****************************when press the three save button***********************/

	}

	public void powerup() {
		flag = 1;
		refresh();
	}

	public void powerdown() {
		flag = 0;
		for (int i = 0; i < 14; i++)
			append(textField[i], "");
	}

	public void destroy() {

	}

	public void refresh() {
		if (flag == 1) {
			append(textField[0], Integer.toString(gwuCpu.PC.TrueValue()));
			append(textField[1], Integer.toString(gwuCpu.IR.TrueValue()));
			append(textField[2], Integer.toString(gwuCpu.XI.TrueValue()));
			append(textField[3], Integer.toString(gwuCpu.RI.TrueValue()));
			append(textField[4], Integer.toString(gwuCpu.EA.TrueValue()));
			append(textField[5], Integer.toString(gwuCpu.MAR.TrueValue()));
			append(textField[6], Integer.toString(gwuCpu.MBR.TrueValue()));
			append(textField[7], Integer.toString(gwuCpu.X[0].TrueValue()));
			append(textField[8], Integer.toString(gwuCpu.X[1].TrueValue()));
			append(textField[9], Integer.toString(gwuCpu.X[2].TrueValue()));
			append(textField[10], Integer.toString(gwuCpu.R[0].TrueValue()));
			append(textField[11], Integer.toString(gwuCpu.R[1].TrueValue()));
			append(textField[12], Integer.toString(gwuCpu.R[2].TrueValue()));
			append(textField[13], Integer.toString(gwuCpu.R[3].TrueValue()));
			append(textField[16], Integer.toString(gwuCpu.CC.TrueValue()));

		} else {

		}
	}

	/********************************** operation for panel ***************************************************************/
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

	public void append(JTextField t, String str) {
		t.setText(str);
		t.setCaretPosition(str.length());
	}

}
