package org.osate.pluginsample.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	private JPanel headerContentPanel = new JPanel(); 
	private JLabel headerLabel = new JLabel();
	private JLabel warningLabel = new JLabel();
	
	public HeaderPanel() {
		setLayout(new FlowLayout());
		headerContentPanel.setLayout(new BorderLayout());
		
		headerLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 32));
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		warningLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
		warningLabel.setText("NOTE: AGREE Statement descriptions must be unique");
		warningLabel.setHorizontalAlignment(JLabel.CENTER);
		
		headerContentPanel.add(headerLabel, BorderLayout.PAGE_START);
		headerContentPanel.add(warningLabel, BorderLayout.PAGE_END);
		
		add(headerContentPanel);
	}
	
	public void setHeaderLabel(String newLabel) {
		headerLabel.setText(newLabel.substring(0, 1).toUpperCase() + newLabel.substring(1));
	}
}
