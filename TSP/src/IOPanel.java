
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

 public class IOPanel extends JPanel {

	 private CityCoordTable model;
	 private JLabel distLabel, totalTime;
	 private JTextField totalNodes;
	 private int numOfNodes;
	 private final int total_length = 10;
	 private GuiPanel gui;
	 private class AlgorithmThread extends Thread {
		 
		 private int algorithmNum;
		 
		 public AlgorithmThread(int alg) {
			 super();
			 algorithmNum = alg;
		 }
		 
		 public void run() {
			
			 
			 long startTime = System.nanoTime();
			 
			 switch(algorithmNum) {	
			 
				case 0: gui.getNodeSet().minSpanningTree();
						break;
				case 1:	gui.getNodeSet().hillClimbing();
						break;
			
			 }
			 
			 long stopTime = System.nanoTime();
			 double runTime = Math.round((stopTime - startTime)*1000.0/Math.pow(10, 9))/1000.0;
			 totalTime.setText("Time taken: " +String.valueOf(runTime) + " seconds");
			 distLabel.setText("Cost: " +gui.getNodeSet().getFinalCost());
		 }
		 
	 }
	 
	 public IOPanel(GuiPanel g, CityCoordTable m) {
		 
		 setLayout(new MigLayout());
		 setPreferredSize(new Dimension(280,0));
		 
		 gui = g;
		 model = m;
		 String[] algs = {"MST", "Hill Climbing"};
		 final JComboBox<String> algChoices = new JComboBox<String>(algs);

		 JButton getNodesButton = new JButton("Generate Nodes");
		 getNodesButton.addActionListener(new ActionListener() {
				 
			 public void actionPerformed(ActionEvent e) {
				 
				 try 
				 {
					 numOfNodes = Integer.parseInt(totalNodes.getText());
					 if(numOfNodes <= 0) 
						 throw new NumNotPositive("Must be positive!");
					 
					 JFrame frame = (JFrame)getTopLevelAncestor();
					 gui = new GuiPanel(numOfNodes, total_length/2);
					 frame.setContentPane(gui);
					 SwingUtilities.updateComponentTreeUI(frame);
					 gui.getIOPanel().totalNodes.setText(Integer.toString(numOfNodes));
					 
				 }
				 catch(NumberFormatException nfe) {
					 nfe.printStackTrace();
					 JOptionPane.showMessageDialog(IOPanel.this, "Enter an integer!");
				 }
				 catch(NumNotPositive npe) {
					 npe.printStackTrace();
					 JOptionPane.showMessageDialog(IOPanel.this, "Enter a POSITIVE integer!");
				 }
			 }
		 });
		 
		 JButton start = new JButton("Start!");
		 start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				gui.setNodeSet(new CitySet(model.getAllCity(), gui));
				resetLabels();
				new AlgorithmThread(algChoices.getSelectedIndex()).start();
			}
		 });

		 JButton clearTableButton = new JButton("Reset");
		 clearTableButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 gui.clearTable();
				 gui.getNodeSet().reset();
				 gui.getScatterPlot().repaint();
			 }	 
		});
		
		
		 
		 JLabel enterText = new JLabel("Number of nodes:");
		 enterText.setHorizontalAlignment(JLabel.CENTER);
		 
		 totalNodes = new JTextField(40);
		 
		 distLabel = new JLabel();
		 totalTime = new JLabel();
		 distLabel.setHorizontalAlignment(JLabel.CENTER);
		 totalTime.setHorizontalAlignment(JLabel.CENTER);


		 add(enterText, "align left");
		 add(totalNodes, "wrap,  align center");
		 add(getNodesButton, "wrap, span, align center");
		 add(algChoices, "align left");
		 add(start, "wrap, align right");
		 add(new JScrollPane(new PanelTable(model)), "wrap, span, align center");
		 add(clearTableButton, "wrap, span, align center");
		 add(new JSeparator(), "wrap, span, align center");
		 add(new JSeparator(), "wrap, span, align center");
		 add(new JSeparator(), "wrap, span, align center");
		 add(distLabel, "wrap, span, align center");
		 add(totalTime, "wrap, span, align center");
	 }
	
	public void setDistanceLabel(String s) {
		distLabel.setText(s);
	}
	public void resetLabels() {
		distLabel.setText("");
		totalTime.setText("");
	}
 }
 