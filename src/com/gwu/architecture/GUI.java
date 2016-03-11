package com.gwu.architecture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

//the class GUI is defined in this file 
public class GUI {
	// claim the objects: ConsolePanle MemoryPanel SwitchPanel InputPanel
	ConsolePanel gwuConsolePanel;
	MemoryPanel gwuMemoryPanel;
	SwitchPanel gwuSwitchPanel;
	InputPanel gwuInputPanel;

	JFrame gwuJFrame;
	GridBagLayout layout;
	GridBagConstraints constraints;
	JPanel gwuJPanel;
	Computer gwuComputer;

	// GUI generate function
	public GUI() {
		gwuConsolePanel = new ConsolePanel(this);
		gwuMemoryPanel = new MemoryPanel(this);
		gwuSwitchPanel = new SwitchPanel(this);
		gwuInputPanel = new InputPanel(this);

		gwuJFrame = new JFrame();
		gwuJPanel = new JPanel();
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
	}

	// GUI initialize function
	public void init(Computer computer) {
		gwuComputer = computer;
		gwuConsolePanel.init(gwuComputer);
		gwuInputPanel.init(gwuComputer);
		gwuMemoryPanel.init(gwuComputer);
		gwuSwitchPanel.init(gwuComputer);

		gwuJFrame.setContentPane(gwuJPanel);
		gwuJPanel.setLayout(layout);
		gwuJFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gwuComputer.destory();
				destroy();
				System.exit(0);
			}
		});

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;

		constraints.ipadx = 200;
		constraints.ipady = 200;
		setConstraints(0, 0, 2, 4, 4, 3);
		gwuJPanel.add(gwuInputPanel, constraints);

		setConstraints(2, 0, 3, 4, 4, 3);
		gwuJPanel.add(gwuMemoryPanel, constraints);

		setConstraints(5, 0, 3, 3, 4, 2);
		gwuJPanel.add(gwuConsolePanel, constraints);

		setConstraints(5, 3, 3, 1, 4, 0.5);
		gwuJPanel.add(gwuSwitchPanel, constraints);

		gwuJFrame.setVisible(true);
		gwuJFrame.setSize(1200, 800);

	}

	// powerup function
	public void powerup() {
		gwuConsolePanel.powerup();
		gwuSwitchPanel.powerup();
		gwuInputPanel.powerup();
		gwuMemoryPanel.powerup();
	}

	// powerdown function
	public void powerdown() {
		gwuConsolePanel.powerdown();
		gwuSwitchPanel.powerdown();
		gwuMemoryPanel.powerdown();
		gwuInputPanel.powerdown();
	}

	// de-generate function
	public void destroy() {
		gwuConsolePanel.destroy();
		gwuSwitchPanel.destroy();
		gwuMemoryPanel.destroy();
		gwuInputPanel.destroy();
	}

	// refresh function
	public void refresh() {
		gwuConsolePanel.refresh();
		gwuInputPanel.refresh();
		gwuMemoryPanel.refresh();
		gwuSwitchPanel.refresh();
	}

	/******************************************** operation for gui **************************************************/
	public void setConstraints(int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty) {
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridheight = gridheight;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	/******************************************** operation for Main **************************************************/
	public void run() {
		refresh();
	}

}
