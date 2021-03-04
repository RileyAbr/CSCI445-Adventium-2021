package org.osate.pluginsample.ui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AssumptionPanel extends JPanel {
	public AssumptionPanel() {
		setLayout(new FlowLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 4; i++) {
        	listModel.addElement(
        			String.format("assume \"Sample assumption\" : (%s %s %d)", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue())
					);
		}
        JList<String> assumptionList = new JList<>(listModel); 
        listPanel.add(assumptionList);
        
        JButton removeAssumptionButton = new JButton("-");
        listPanel.add(removeAssumptionButton);
        
		add(listPanel);
        
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
        inputsPanel.add(assumptionOperandList);
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        inputsPanel.add(assumptionComparatorList);
        
        JTextField assumptionValueTextField = new JTextField();
        inputsPanel.add(assumptionValueTextField);
        
        JButton addAssumptionButton = new JButton("+");
        inputsPanel.add(addAssumptionButton);
        
        add(inputsPanel);
	}
}
