package org.osate.pluginsample.ui;

import org.osate.aadl2.impl.SystemTypeImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.PortConnectionImpl;
import org.osate.aadl2.impl.SystemSubcomponentImpl;
import org.osate.aadl2.impl.SystemImplementationImpl;
import org.osate.aadl2.impl.DefaultAnnexSubclauseImpl;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import org.osate.pluginsample.actions.DoCheckModel;
import org.osate.pluginsample.actions.IterationResultObject;

import org.osate.ui.dialogs.Dialog;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	
	private String[] inputFeatures;
	private String[] inputFeaturesTypes;
	private String[] outputFeatures;
	private String[] outputFeaturesTypes;
	private ArrayList<String> assumptions;
	private ArrayList<String> guarantees;
	private String systemName;
	private String directoryPath;
	
	private String outputValue = "";
	
	private JButton backButton = new JButton(new BackAction("Back"));
	private JButton nextButton = new JButton(new NextAction("Next"));
	
	private IterationResultObject iro;
	public String iro_name;
	
	public GUMBOInterface(String[] incomingInputFeatures, String[] incomingInputFeaturesTypes,
						String[] incomingOutputFeatures, String[] incomingOutputFeaturesTypes,
						ArrayList<String> incomingAssumptions, ArrayList<String> incomingGuarantees, String incomingSystemName) {
	    super("AGREE Creator");
	 
	    inputFeatures = incomingInputFeatures;
	    inputFeaturesTypes = incomingInputFeaturesTypes;
	    outputFeatures = incomingOutputFeatures;
	    outputFeaturesTypes = incomingOutputFeaturesTypes;
	    assumptions = incomingAssumptions;
	    guarantees = incomingGuarantees;
	    systemName = incomingSystemName;
	    
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
		
		outputPanel = new OutputPanel(assumptions, guarantees);
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
		
		revalidate();
		repaint();
	}
	
	private void updateOutputPanel() {
		outputPanel = new OutputPanel(assumptions, guarantees);
		pagePanels[2] = outputPanel;
	}
	
//	This method is called when the UI is closed. All end-of-life code should be processed here.
	private void endGUMBOInterface() {
//		Establishing path for all users
//		Should be the users current directory that they are running the project
//		Example:  C:\Users\ansle\eclipse\StoredAssumGuarants_Radiation_Ctrl.txt
		directoryPath = System.getProperty("user.dir");
		String path = directoryPath + "\\StoredAssumGuarants_" + systemName + ".txt";
		
//		Assumptions
		for (String assumption : assumptions) {
			outputValue += assumption + "\n";
		}

//		Guarantees
		for (String guarantee : guarantees) {
			outputValue += guarantee + "\n";
		}

		System.out.println("Output is ");
		System.out.println(outputValue);
		
//		Creating text file
		try {

			File myObj = new File(path);
			if (myObj.createNewFile()) {
				System.out.println("File Created " + myObj.getName() + " in " + myObj.getAbsolutePath() + "\n");
			} else {
				System.out.println("File already exists.\n");
				System.out.println("Absolute Path: " + myObj.getAbsolutePath() + "\n");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.\n");
			e.printStackTrace();
		}

		System.out.println("OutputValue is ");
		System.out.println(outputValue);

//		Write to text file
		try {
			FileWriter myWriter = new FileWriter(path);
			myWriter.write(outputValue);
			myWriter.close();
			System.out.println("Successfully wrote to the file.\n");
		} catch (IOException e) {
			System.out.println("An error occurred.\n");
			e.printStackTrace();
		}

		System.out.println(assumptions);
		System.out.println(guarantees);
		setVisible(false);
    	dispose();
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
            	endGUMBOInterface();
            }
            else if(currentPage < 0) {
            	currentPage = 1;
            }
            else {
            	currentPage++;
            }
            
//          Set Button Statuses
            if(currentPage >= pages.length - 1) {
            	updateOutputPanel();
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
    	private JPanel assumptionComparatorPanel;
    	private int selectedAssumptionOperandIndex;
    	private String[] assumptionComparatorListValues;
    	private JComboBox<String> assumptionComparatorList;
    	private JFormattedTextField assumptionValueTextField;
    	
    	public AssumptionPanel() {
    		setLayout(new BorderLayout());    		
    		
    		selectedAssumptionOperandIndex = 0;
    		
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
            
            assumptionOperandList = new JComboBox<>(inputFeatures);
            assumptionOperandList.addItemListener(new ItemListener(){
            	public void itemStateChanged(ItemEvent ie)
            	{
            	   if(ie.getStateChange() == ItemEvent.SELECTED)
            	   {
            	      selectedAssumptionOperandIndex = assumptionOperandList.getSelectedIndex();
            	      updateAssumptionComparatorList();
            	   }
            	}});
            inputsPanel.add(assumptionOperandList);
            
//          Comparator Panel
            assumptionComparatorPanel = new JPanel();
            
            updateAssumptionComparatorList();
            
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
    	
    	private void updateAssumptionComparatorList() {
    		if(assumptionComparatorList != null) {
    			assumptionComparatorPanel.remove(assumptionComparatorList);
    		}
            
            switch (inputFeaturesTypes[selectedAssumptionOperandIndex]) {
			case "Integer":
			case "Float":
				assumptionComparatorListValues = AGREEComponentFactory.getInputIntegerComparators();
				break;
			case "Boolean":	
			default:
				assumptionComparatorListValues = AGREEComponentFactory.getInputBooleanComparators();
				break;
			}
            assumptionComparatorList = new JComboBox<>(assumptionComparatorListValues);
            assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
            assumptionComparatorPanel.add(assumptionComparatorList);
            
            revalidate();
    		repaint();
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
    	private int selectedConditionalOperandIndex;
    	private JPanel assumptionComparatorPanel;
    	private JComboBox<String> conditionalOperandList;
    	private String[] assumptionComparatorListValues;
    	private JComboBox<String> assumptionComparatorList;
    	private JFormattedTextField conditionalValueTextField;
    	private JComboBox<String> guaranteeComparatorList;
    	private JComboBox<String> guaranteeOperandList;
    	private JButton addGuaranteeButton;
    	
    	public GuaranteePanel() {
    		setLayout(new BorderLayout());
    		
    		selectedConditionalOperandIndex = 0;
    		
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
            
            conditionalOperandList = new JComboBox<>(inputFeatures);
            conditionalOperandList.addItemListener(new ItemListener(){
            	public void itemStateChanged(ItemEvent ie)
            	{
            	   if(ie.getStateChange() == ItemEvent.SELECTED)
            	   {
            	      selectedConditionalOperandIndex = conditionalOperandList.getSelectedIndex();
            	      updateConditionalComparatorList();
            	   }
            	}});
            inputsPanel.add(conditionalOperandList);
            
//          1st Comparator Panel
            assumptionComparatorPanel = new JPanel();
            
            updateConditionalComparatorList();
            
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
            
            guaranteeOperandList = new JComboBox<>(outputFeatures);
            addButtonPanel.add(guaranteeOperandList);
            
            addGuaranteeButton = new JButton(new AddGuaranteeAction("+"));
            addButtonPanel.add(addGuaranteeButton);
            
            inputsPanel.add(addButtonPanel);
            
            add(inputsPanel);
    	}
    	
    	private void updateConditionalComparatorList() {
    		if(assumptionComparatorList != null) {
    			assumptionComparatorPanel.remove(assumptionComparatorList);
    		}
            
            switch (inputFeaturesTypes[selectedConditionalOperandIndex]) {
			case "Integer":
			case "Float":
				assumptionComparatorListValues = AGREEComponentFactory.getInputIntegerComparators();
				break;
			case "Boolean":	
			default:
				assumptionComparatorListValues = AGREEComponentFactory.getInputBooleanComparators();
				break;
			}
            assumptionComparatorList = new JComboBox<>(assumptionComparatorListValues);
            assumptionComparatorList.setMaximumSize( assumptionComparatorList.getPreferredSize());
            assumptionComparatorPanel.add(assumptionComparatorList);
            
            revalidate();
    		repaint();
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
