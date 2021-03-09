package org.osate.pluginsample.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class GuaranteePanel extends JPanel {
	public GuaranteePanel() {
		setLayout(new BorderLayout());
		
//		List Panel
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 4; i++) {
        	listModel.addElement(
        			String.format("guarntee \"Example guarantee\" : (%s %s %d) %s %s", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue(), AGREEComponentFactory.getMockGuaranteeComparator(), AGREEComponentFactory.getMockGuaranteeParameter())
					);
		}
        JList<String> guaranteeList = new JList<>(listModel); 
        JScrollPane guaranteeListScrollPane = new JScrollPane(new JList<>(listModel));
        listPanel.add(guaranteeListScrollPane);
        
        JButton removeGuaranteeButton = new JButton("-");
        listPanel.add(removeGuaranteeButton);
        
		add(listPanel, BorderLayout.PAGE_START);
        
//		Inputs Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> conditionalOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
        inputsPanel.add(conditionalOperandList);
        
//      1st Comparator Panel
        JPanel assumptionComparatorPanel = new JPanel();
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
        assumptionComparatorPanel.add(assumptionComparatorList);
        
        inputsPanel.add(assumptionComparatorPanel);
        
        JTextField assumptionValueTextField = new JTextField();
        inputsPanel.add(assumptionValueTextField);
        
//      2nd Comparator Panel
        JPanel guaranteeComparatorPanel = new JPanel();
        
        JComboBox<String> guaranteeComparatorList = new JComboBox<>(AGREEComponentFactory.getAllGuaranteeComparators());
        guaranteeComparatorList.setMaximumSize( guaranteeComparatorList.getPreferredSize());
        guaranteeComparatorPanel.add(guaranteeComparatorList);
        
        inputsPanel.add(guaranteeComparatorPanel);
        
//      + Button Panel
        JPanel addButtonPanel = new JPanel();
        
        JComboBox<String> guaranteeOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockGuaranteeParameters());
        addButtonPanel.add(guaranteeOperandList);
        
        JButton addGuaranteeButton = new JButton("+");
        addButtonPanel.add(addGuaranteeButton);
        
        inputsPanel.add(addButtonPanel);
        
        add(inputsPanel);
	}
}
