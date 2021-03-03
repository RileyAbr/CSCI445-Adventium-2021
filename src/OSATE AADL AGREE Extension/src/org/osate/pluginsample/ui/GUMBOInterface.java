package org.osate.pluginsample.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUMBOInterface extends JFrame {
//	public static void createGUMBOWindow() {
//		JFrame frame = new JFrame("AGREE Creator");
//		frame.setSize(750, 600);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.setVisible(true);
//		
//        frame.setLayout(new FlowLayout());
//        
//        frame.add(labelQuestion);
//        frame.add(labelWeight);
//        frame.add(fieldWeight);
//        frame.add(buttonTellMe);
//	}
	
	public GUMBOInterface() {
	    super("AGREE Creator");
	 
	    initComponents();
	    
	    setSize(750, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initComponents() {
		setLayout(new FlowLayout());
	}
	
	public static void main(String[] args) {
        new GUMBOInterface().setVisible(true);
    }
}
