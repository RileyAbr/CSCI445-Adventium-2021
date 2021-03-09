package org.osate.pluginsample.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class AssumptionPanel extends JPanel {
	private ArrayList<String> assumptionStatements;
	private JPanel listPanel;
	private JScrollPane assumptionListScrollPane;
	private JButton removeAssumptionButton;
	
	public AssumptionPanel(ArrayList<String> inputAssumptionStatements) {
		setLayout(new BorderLayout());
		
//		List Panel
		listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
		assumptionStatements = inputAssumptionStatements;
//        DefaultListModel<String> listModel = new DefaultListModel<>();
//        for (String statement : assumptionStatements) {
//        	listModel.addElement(statement);
//		}
//        
//        assumptionListScrollPane = new JScrollPane(new JList<>(listModel));
		
//        listPanel.add(assumptionListScrollPane);
//        
        removeAssumptionButton = new JButton(new RemoveAssumptionAction("-"));
//        listPanel.add(removeAssumptionButton);
        
        updateListPane();
        
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
	
	private void updateListPane() {
		if(assumptionListScrollPane != null) {
			listPanel.remove(assumptionListScrollPane);
		}
		if(removeAssumptionButton != null) {
			listPanel.remove(removeAssumptionButton);
		}
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String statement : assumptionStatements) {
        	listModel.addElement(statement);
		}
        
        assumptionListScrollPane = new JScrollPane(new JList<>(listModel));
        
        listPanel.add(assumptionListScrollPane);
        listPanel.add(removeAssumptionButton);
        
        revalidate();
		repaint();
	}
	
	private class RemoveAssumptionAction extends AbstractAction {

        public RemoveAssumptionAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	assumptionStatements.remove(0);
        	System.out.println(assumptionStatements);
        	updateListPane();
        }
    }
	
	
}
