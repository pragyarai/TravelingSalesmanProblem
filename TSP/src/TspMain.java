import java.awt.Frame;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class TspMain {
	
	
	public static void main(String[] args) {
		GuiPanel content = new GuiPanel(1,10);
		JFrame frame = new JFrame("The Traveling Salesman");
		frame.pack();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
		frame.setVisible(true);	
		frame.setContentPane(content);
	}
}
