package org.osate.pluginsample.ui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuaranteePanel extends JPanel {
	public GuaranteePanel() {
		setLayout(new FlowLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 4; i++) {
        	listModel.addElement(
        			String.format("guarntee \"Example guarantee\" : (%s %s %d) %s %s", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue(), AGREEComponentFactory.getMockGuaranteeComparator(), AGREEComponentFactory.getMockGuaranteeParameter())
					);
		}
        JList<String> guaranteeList = new JList<>(listModel); 
        listPanel.add(guaranteeList);
        
        JButton removeGuaranteeButton = new JButton("-");
        listPanel.add(removeGuaranteeButton);
        
		add(listPanel);
        
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
        
        JTextField agreeDescriptionTextField = new JTextField();
        inputsPanel.add(agreeDescriptionTextField);
        
        JComboBox<String> conditionalOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
        inputsPanel.add(conditionalOperandList);
        
        JComboBox<String> assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
        inputsPanel.add(assumptionComparatorList);
        
        JTextField assumptionValueTextField = new JTextField();
        inputsPanel.add(assumptionValueTextField);
        
        JComboBox<String> guaranteeComparatorList = new JComboBox<>(AGREEComponentFactory.getAllGuaranteeComparators());
        inputsPanel.add(guaranteeComparatorList);
        
        JComboBox<String> guaranteeOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockGuaranteeParameters());
        inputsPanel.add(guaranteeOperandList);
        
        JButton addGuaranteeButton = new JButton("+");
        inputsPanel.add(addGuaranteeButton);
        
        add(inputsPanel);
	}
}
