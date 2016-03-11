package com.gwu.architecture;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SwitchPanel extends JPanel {

	class switchThread extends Thread {		//thread class used for exchange information between computer and gui
		
		public int step;
		
		public void run() {
			synchronized (SwitchPanel.class) {
				while(threadFlag==1);		//lock, serialize the thread
				threadFlag=1;
				if(step==1)
					gwuComputer.step();
				else 
					gwuComputer.run();
				gwuGui.refresh();
				threadFlag=0;
			}
		}
	}
	
	private static final long serialVersionUID = 1L;

	Computer gwuComputer;
	GUI gwuGui;
	GridBagConstraints constraints;
	GridBagLayout layout;
	JButton[] button;
	ImageIcon[] icon;
	JRadioButton[] radioButton;
	JFileChooser fileChooser;
	// claim a flag used to indicate if the computer is powerup or powerdown
	int flag;
	switchThread thread;
	int threadFlag;

	// SwitchPanel generator function
	public SwitchPanel(GUI gui) {
		gwuGui = gui;
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		// set the icon for start button
		icon = new ImageIcon[2];
		icon[0] = new ImageIcon(getClass().getResource("start0.jpg"));
		icon[1] = new ImageIcon(getClass().getResource("start1.jpg"));
		//icon[0] = new ImageIcon("start0.jpg");
		//icon[1] = new ImageIcon("start1.jpg");

		// button object claim
		button = new JButton[6];
		button[0] = new JButton("step");
		button[1] = new JButton("run");
		button[2] = new JButton("stop");
		button[3] = new JButton("choose an assembly file");
		button[4] = new JButton("choose a decimal file");
		
		fileChooser = new JFileChooser();

		radioButton = new JRadioButton[6];
		radioButton[0] = new JRadioButton(icon[0]);

		flag = 0;

	}

	// init function of SwitchPanel
	public void init(Computer computer) {
		
		gwuComputer = computer;
		
		/***********************init swithc panel's ui******************************/
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(layout);
		constraints.insets = new Insets(1, 1, 1, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		
		setConstraints(0, 0, 1, 1, 4, 3);
		add(button[0], constraints);
		setConstraints(1, 0, 1, 1, 4, 3);
		add(button[1], constraints);
		setConstraints(2, 0, 1, 1, 4, 3);
		add(button[2], constraints);
		setConstraints(0, 1, 1, 1, 4, 3);
		add(button[3], constraints);
		setConstraints(1, 1, 1, 1, 4, 3);
		add(button[4], constraints);
		setConstraints(2, 1, 1, 1, 4, 3);
		add(radioButton[0], constraints);
		
		/***********************init swithc panel's ui******************************/
		
		//when step was pressed
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thread = new switchThread();
				thread.step=1;
				thread.start();
				while(thread.isAlive()==false)
					gwuGui.refresh();
			}
		});
		
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thread = new switchThread();
				thread.step=0;
				thread.start();
			}
		});
		
		button[2].addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thread.suspend();
				gwuGui.refresh();
			}
		});
		
		button[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int res=fileChooser.showOpenDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					String tString = fileChooser.getSelectedFile().toString();
					tString = tString.replace('\\', '/');
					CardReader cardReader = gwuComputer.gwuIO.gwuCardReader;
					try {
						cardReader.loadCard(tString);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i=0; i<cardReader.fileLength; i++)	//did not write in rom using assembly, should be written in future
						gwuComputer.gwuMemory.storeData(i, Integer.parseInt(cardReader.cardStrings[i]));
					gwuGui.refresh();
					
				}
			}
		});
		
		button[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int res=fileChooser.showOpenDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					String tString = fileChooser.getSelectedFile().toString();
					tString = tString.replace('\\', '/');
					CardReader cardReader = gwuComputer.gwuIO.gwuCardReader;
					try {
						cardReader.loadCard1(tString);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i=0; i<cardReader.fileLength; i++)	//did not write in rom using assembly, should be written in future
						gwuComputer.gwuMemory.storeData(i, Integer.parseInt(cardReader.cardStrings[i]));
					gwuGui.refresh();
				}
			}
		});
		
		
		// ActionListener of radioButton, which implements the action of
		// computer powerup and powerdowm
		radioButton[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (flag == 0) {
					gwuComputer.powerup();
					gwuGui.powerup();
				} else {
					gwuComputer.powerdown();
					gwuGui.powerdown();
				}
				radioButton[0].setIcon(icon[flag]);
			}
		});
		
		

	}

	// powerup function
	public void powerup() {
		flag = 1;
		threadFlag=0;
	}

	// powerdown function
	public void powerdown() {
		flag = 0;
	}

	// degenerator of SwithcPanel
	public void destroy() {

	}

	// refresh function
	public void refresh() {
		if (flag == 0) {

		} else {

		}
	}
	
	/******************************operation for panel*******************************************/
	public void setConstraints(int gridx, int gridy, int gridwidth,
			int gridheight, double weightx, double weighty) {
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridheight = gridheight;
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

}
