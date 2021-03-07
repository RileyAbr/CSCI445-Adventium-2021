package org.osate.pluginsample.ui;

import java.awt.BorderLayout;
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
//		setLayout(new FlowLayout());
		setLayout(new BorderLayout());
		
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
        
		add(listPanel, BorderLayout.PAGE_START);
        
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
        inputsPanel.add(assumptionOperandList);
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        inputsPanel.add(assumptionComparatorList);
        
        assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize() );
        
        JPanel addButtonPanel = new JPanel();
        
        JTextField assumptionValueTextField = new JTextField("", 20);
//        inputsPanel.add(assumptionValueTextField);
        addButtonPanel.add(assumptionValueTextField);
        
        JButton addAssumptionButton = new JButton("+");
        addButtonPanel.add(addAssumptionButton);
        
        inputsPanel.add(addButtonPanel);
        
        add(inputsPanel);
	}
}
