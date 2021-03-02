package org.osate.pluginsample.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserInterface {
	public static void showSampleModal() {
		JFrame frame = new JFrame("JoptionPane Test");
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame, "Tempo");
	}
}
