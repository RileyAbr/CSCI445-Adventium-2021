package org.osate.pluginsample.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUMBOInterface extends JFrame {
	
	public GUMBOInterface() {
	    super("AGREE Creator");
	 
	    initComponents();
	    
	    setSize(750, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initComponents() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
//		Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		
		JLabel headerLabel = new JLabel("Assumptions");
		headerLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 32));
		headerPanel.add(headerLabel);
		
		contentPanel.add(headerPanel);
		
//		List Panel
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 4; i++) {
        	listModel.addElement(
        			String.format("assume \"Sample assumption\" : (%s < %d)", AGREEComponentFactory.createParameter(), 300)
					);
		}
        JList<String> countryList = new JList<>(listModel); 
        listPanel.add(countryList);
        
        JButton removeAssumptionButton = new JButton("-");
        listPanel.add(removeAssumptionButton);
        
		contentPanel.add(listPanel);
		
//		Inputs Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllParameters());
        inputsPanel.add(assumptionOperandList);
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        inputsPanel.add(assumptionComparatorList);
        
        JTextField assumptionValueTextField = new JTextField();
        inputsPanel.add(assumptionValueTextField);
        
        JButton addAssumptionButton = new JButton("+");
        inputsPanel.add(addAssumptionButton);
        
        contentPanel.add(inputsPanel);
        
//		Pagination Panel
        JPanel paginationPanel = new JPanel();
        paginationPanel.setLayout(new FlowLayout());
        
        JButton backButton = new JButton("Back");
        paginationPanel.add(backButton);
        
        JButton nextButton = new JButton("Next");
        paginationPanel.add(nextButton);
        
        contentPanel.add(paginationPanel);
        
        add(contentPanel);     
	}
	
	public static void main(String[] args) {
        new GUMBOInterface().setVisible(true);
    }
}
