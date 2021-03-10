package org.osate.pluginsample.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;

public class GUMBOInterface extends JFrame {
	private int currentPage = 0;
	private String[] pages = {"assumptions", "guarantees", "output"};
	private JPanel[] pagePanels = new JPanel[pages.length];
	
	private HeaderPanel headerPanel = new HeaderPanel();
	
	private ContentPanel contentPanel;
	private AssumptionPanel assumptionPanel;
	private GuaranteePanel guaranteePanel;
	private OutputPanel outputPanel;
	
	private ArrayList<String> assumptions;
	private ArrayList<String> guarantees;
	
	private JButton backButton = new JButton(new BackAction("Back"));
	private JButton nextButton = new JButton(new NextAction("Next"));
	
	public GUMBOInterface() {
	    super("AGREE Creator");
	 
//	    Assumptions and Guarantees are currently done via mocks, but will be read from an input file/the iteration eventually
	    assumptions = AGREEComponentFactory.getMockAssumptionStatements();
	    guarantees = AGREEComponentFactory.getMockGuaranteeStatements();
	    
	    JPanel mainPanel = new JPanel();
//	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    mainPanel.setLayout(new BorderLayout());
	  	
//		Header Panel
		headerPanel.setHeaderLabel(pages[currentPage]);
		mainPanel.add(headerPanel, BorderLayout.PAGE_START);
		
//		Content Panel
	    contentPanel = new ContentPanel();
		   
		assumptionPanel = new AssumptionPanel();
		pagePanels[0] = assumptionPanel;
		
		guaranteePanel = new GuaranteePanel();
		pagePanels[1] = guaranteePanel;
		
		outputPanel = new OutputPanel();
		pagePanels[2] = outputPanel;
		
		contentPanel.setInternalPanel(pagePanels[0]);
		
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
//		Pagination Panel
        JPanel paginationPanel = new JPanel();
        paginationPanel.setLayout(new FlowLayout());
        
        backButton.setEnabled(false);
        paginationPanel.add(backButton);
        paginationPanel.add(nextButton);
        
        mainPanel.add(paginationPanel, BorderLayout.PAGE_END);
        
//      Add Entire Content Panel
        add(mainPanel);     
	    
	    setSize(575, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void updatePage(int currentPage) {
		headerPanel.setHeaderLabel(pages[currentPage]);
		contentPanel.setInternalPanel(pagePanels[currentPage]);
	}
	
	private class BackAction extends AbstractAction {
		public BackAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//        	Check Page Number
            if(currentPage <= 0) {
            	currentPage = 0;
            }
            else if(currentPage >= pages.length - 1) {
            	currentPage = pages.length - 2;
            }
            else {
            	currentPage--;
            }
            
//          Set Button Statuses
            if(currentPage <= 0) {
            	backButton.setEnabled(false);
            }
            else {
            	backButton.setEnabled(true);
            }
            nextButton.setText("Next");
            nextButton.setEnabled(true);
           
//        	Update Page
        	updatePage(currentPage);
        }
	}
	
    private class NextAction extends AbstractAction {

        public NextAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//        	Check Page Number
            if(currentPage >= pages.length - 1) {
            	setVisible(false);
            	dispose();
            }
            else if(currentPage < 0) {
            	currentPage = 1;
            }
            else {
            	currentPage++;
            }
            
//          Set Button Statuses
            if(currentPage >= pages.length - 1) {
            	nextButton.setText("Finish");
            }
            else {
            	nextButton.setEnabled(true);
            }
            backButton.setEnabled(true);
            
//        	Update Page
        	updatePage(currentPage);
        }
    }
	
    private class AssumptionPanel extends JPanel {
    	private JPanel listPanel;
    	private JList assumptionList;
    	private JScrollPane assumptionListScrollPane;
    	private JButton removeAssumptionButton;
    	private JTextField agreeDescriptionTextField;
    	private JComboBox<String> assumptionOperandList;
    	private JComboBox<String> assumptionComparatorList;
    	private JFormattedTextField assumptionValueTextField;
    	
    	public AssumptionPanel() {
    		setLayout(new BorderLayout());
    		
//    		List Panel
    		listPanel = new JPanel();
    		listPanel.setLayout(new FlowLayout());
    		
            removeAssumptionButton = new JButton(new RemoveAssumptionAction("-"));
            
            updateListPane();
            
    		add(listPanel, BorderLayout.PAGE_START);
            
//    		Inputs Panel
            JPanel inputsPanel = new JPanel();
            inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
            
            agreeDescriptionTextField = new JTextField();
            inputsPanel.add(agreeDescriptionTextField);
            
            assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
            inputsPanel.add(assumptionOperandList);
            
//          Comparator Panel
            JPanel assumptionComparatorPanel = new JPanel();
            
            assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
            assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
            assumptionComparatorPanel.add(assumptionComparatorList);
            
            inputsPanel.add(assumptionComparatorPanel);
            
//          + Button Panel
            JPanel addButtonPanel = new JPanel();
            
            NumberFormat longFormat = new DecimalFormat("#0.00"); ;

            NumberFormatter numberFormatter = new NumberFormatter(longFormat);
            numberFormatter.setValueClass(Long.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0l);

            assumptionValueTextField = new JFormattedTextField(numberFormatter);
            assumptionValueTextField.setColumns(20);
            assumptionValueTextField.setText("0");
            
            addButtonPanel.add(assumptionValueTextField);
            
            JButton addAssumptionButton = new JButton(new AddAssumptionAction("+"));
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
            for (String statement : assumptions) {
            	listModel.addElement(statement);
    		}
            
            assumptionList = new JList<>(listModel);
            assumptionListScrollPane = new JScrollPane(assumptionList);
            
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
            	int currentSelection = assumptionList.getSelectedIndex();
            	
            	if(assumptionList.getModel().getSize() > 0
            		&& currentSelection > -1) {
	            	assumptions.remove(currentSelection);
	            	updateListPane();
            	}
            }
        }
    	
    	private class AddAssumptionAction extends AbstractAction {
    		public AddAssumptionAction(String name) {
    			super(name);
                int mnemonic = (int) name.charAt(0);
                putValue(MNEMONIC_KEY, mnemonic);
    		}
    		
    		@Override
            public void actionPerformed(ActionEvent e) {
    			String descValue = agreeDescriptionTextField.getText();
    			String parameterValue = assumptionOperandList.getSelectedItem().toString();
    			String comparatorValue = assumptionComparatorList.getSelectedItem().toString();
    			String assumptionValue = assumptionValueTextField.getText();
    			if(!descValue.isEmpty()
    				&& !parameterValue.isEmpty()
    				&& !comparatorValue.isEmpty()
					&& !assumptionValue.isEmpty()) {
	            	assumptions.add(String.format("assume \"%s\" : (%s %s %s)", descValue, parameterValue, comparatorValue, assumptionValue));
	            	updateListPane();
	            	
	            	agreeDescriptionTextField.setText("");
	            	assumptionValueTextField.setText("0");
    			} else {
    				
    			}
            }
    	}
    }
    
    private class GuaranteePanel extends JPanel {
    	private JPanel listPanel;
    	private JList guaranteeList;
    	private JScrollPane guaranteeListScrollPane;
    	private JButton removeGuaranteeButton;
    	private JTextField agreeDescriptionTextField;
    	private JComboBox<String> conditionalOperandList;
    	private JComboBox<String> assumptionComparatorList;
    	private JFormattedTextField conditionalValueTextField;
    	private JComboBox<String> guaranteeComparatorList;
    	private JComboBox<String> guaranteeOperandList;
    	private JButton addGuaranteeButton;
    	
    	public GuaranteePanel() {
    		setLayout(new BorderLayout());
    		
//    		List Panel
    		listPanel = new JPanel();
    		listPanel.setLayout(new FlowLayout());
    		
    		removeGuaranteeButton = new JButton(new RemoveGuaranteeAction("-"));
            
            updateListPane();
            
    		add(listPanel, BorderLayout.PAGE_START);
    		
//    		Inputs Panel
            JPanel inputsPanel = new JPanel();
            inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
            
            agreeDescriptionTextField = new JTextField();
            inputsPanel.add(agreeDescriptionTextField);
            
            conditionalOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockAssumptionParameters());
            inputsPanel.add(conditionalOperandList);
            
//          1st Comparator Panel
            JPanel assumptionComparatorPanel = new JPanel();
            
            assumptionComparatorList = new JComboBox<>(AGREEComponentFactory.getAllAssumptionComparators());
            assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
            assumptionComparatorPanel.add(assumptionComparatorList);
            
            inputsPanel.add(assumptionComparatorPanel);
            
            NumberFormat longFormat = new DecimalFormat("#0.00"); ;

            NumberFormatter numberFormatter = new NumberFormatter(longFormat);
            numberFormatter.setValueClass(Long.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0l);

            conditionalValueTextField = new JFormattedTextField(numberFormatter);
            conditionalValueTextField.setColumns(20);
            conditionalValueTextField.setText("0");
            
            inputsPanel.add(conditionalValueTextField);
            
//          2nd Comparator Panel
            JPanel guaranteeComparatorPanel = new JPanel();
            
            guaranteeComparatorList = new JComboBox<>(AGREEComponentFactory.getAllGuaranteeComparators());
            guaranteeComparatorList.setMaximumSize( guaranteeComparatorList.getPreferredSize());
            guaranteeComparatorPanel.add(guaranteeComparatorList);
            
            inputsPanel.add(guaranteeComparatorPanel);
            
//          + Button Panel
            JPanel addButtonPanel = new JPanel();
            
            guaranteeOperandList = new JComboBox<>(AGREEComponentFactory.getAllMockGuaranteeParameters());
            addButtonPanel.add(guaranteeOperandList);
            
            addGuaranteeButton = new JButton(new AddGuaranteeAction("+"));
            addButtonPanel.add(addGuaranteeButton);
            
            inputsPanel.add(addButtonPanel);
            
            add(inputsPanel);
    	}
    	
    	private void updateListPane() {
    		if(guaranteeListScrollPane != null) {
    			listPanel.remove(guaranteeListScrollPane);
    		}
    		if(removeGuaranteeButton != null) {
    			listPanel.remove(removeGuaranteeButton);
    		}
    		
    		DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String statement : guarantees) {
            	listModel.addElement(statement);
    		}
            
            guaranteeList = new JList<>(listModel);
            guaranteeListScrollPane = new JScrollPane(guaranteeList);
            
            listPanel.add(guaranteeListScrollPane);
            listPanel.add(removeGuaranteeButton);
            
            revalidate();
    		repaint();
    	}
    	
    	private class RemoveGuaranteeAction extends AbstractAction {
            public RemoveGuaranteeAction(String name) {
                super(name);
                int mnemonic = (int) name.charAt(0);
                putValue(MNEMONIC_KEY, mnemonic);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
            	int currentSelection = guaranteeList.getSelectedIndex();
            	
            	if(guaranteeList.getModel().getSize() > 0
            		&& currentSelection > -1) {
	            	guarantees.remove(currentSelection);
	            	updateListPane();
            	}
            }
        }
    	
    	private class AddGuaranteeAction extends AbstractAction {
    		public AddGuaranteeAction(String name) {
    			super(name);
                int mnemonic = (int) name.charAt(0);
                putValue(MNEMONIC_KEY, mnemonic);
    		}
    		
    		@Override
            public void actionPerformed(ActionEvent e) {
    			/*
    	    	private JComboBox<String> assumptionComparatorList;
    	    	private JFormattedTextField assumptionValueTextField;
    	    	private JComboBox<String> guaranteeComparatorList;
    	    	private JComboBox<String> guaranteeOperandList;
    			*/
    			
    			String descValue = agreeDescriptionTextField.getText();
    			String conditionalParameterValue = conditionalOperandList.getSelectedItem().toString();
    			String conditionalComparatorValue = assumptionComparatorList.getSelectedItem().toString();
    			String parameterValue = guaranteeOperandList.getSelectedItem().toString();
    			String comparatorValue = guaranteeComparatorList.getSelectedItem().toString();
    			String assumptionValue = conditionalValueTextField.getText();
    			if(!descValue.isEmpty()
    				&& !conditionalParameterValue.isEmpty()
    				&& !conditionalComparatorValue.isEmpty()
    				&& !parameterValue.isEmpty()
    				&& !comparatorValue.isEmpty()
					&& !assumptionValue.isEmpty()) {
	            	guarantees.add(String.format("guarantee \"%s\" : (%s %s %s) %s %s", descValue, conditionalParameterValue, conditionalComparatorValue, parameterValue, comparatorValue, assumptionValue));
	            	updateListPane();
	            	
	            	agreeDescriptionTextField.setText("");
	            	conditionalValueTextField.setText("0");
    			} else {
    				
    			}
            }
    	}
    }
    
	public static void main(String[] args) {
        new GUMBOInterface().setVisible(true);
    }
}

class ContentPanel extends JPanel {
	public ContentPanel() {
	}
	
	public void setInternalPanel(JPanel newJPanel) {
		removeAll();
		revalidate();
		repaint();
		add(newJPanel);
	}
}
