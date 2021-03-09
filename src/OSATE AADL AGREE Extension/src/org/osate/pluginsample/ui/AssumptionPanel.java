package org.osate.pluginsample.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class AssumptionPanel extends JPanel {
	public AssumptionPanel() {
		setLayout(new BorderLayout());
		
//		List Panel
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 3; i++) {
        	listModel.addElement(
        			String.format("assume \"Sample assumption\" : (%s %s %d)", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue())
					);
		}
        JScrollPane assumptionListScrollPane = new JScrollPane(new JList<>(listModel));
        listPanel.add(assumptionListScrollPane);
        
        JButton removeAssumptionButton = new JButton("-");
        listPanel.add(removeAssumptionButton);
        
		add(listPanel, BorderLayout.PAGE_START);
        
//		Inputs Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
        inputsPanel.add(assumptionOperandList);
        
//      Comparator Panel
        JPanel assumptionComparatorPanel = new JPanel();
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
        assumptionComparatorPanel.add(assumptionComparatorList);
        
        inputsPanel.add(assumptionComparatorPanel);
        
//      + Button Panel
        JPanel addButtonPanel = new JPanel();
        
        JTextField assumptionValueTextField = new JTextField("", 20);
        addButtonPanel.add(assumptionValueTextField);
        
        JButton addAssumptionButton = new JButton("+");
        addButtonPanel.add(addAssumptionButton);
        
        inputsPanel.add(addButtonPanel);
        
        add(inputsPanel);
	}
}
