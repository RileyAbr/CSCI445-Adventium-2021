package org.osate.pluginsample.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUMBOInterface extends JFrame {
	private int currentPage = 0;
	private String[] pages = {"assumptions", "guarantees", "output"};
	private JPanel[] pagePanels = new JPanel[pages.length];
	
	private HeaderPanel headerPanel = new HeaderPanel();
	
	private ContentPanel contentPanel;
	private AssumptionPanel assumptionPanel;
	private GuaranteePanel guaranteePanel;
	private OutputPanel outputPanel;
	
	private JButton backButton = new JButton(new BackAction("Back"));
	private JButton nextButton = new JButton(new NextAction("Next"));
	
	public GUMBOInterface() {
	    super("AGREE Creator");
	 
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	  	
//		Header Panel
		headerPanel.setHeaderLabel(pages[currentPage]);
		mainPanel.add(headerPanel);
		
//		Content Panel
	    contentPanel = new ContentPanel();
		   
		assumptionPanel = new AssumptionPanel();
		pagePanels[0] = assumptionPanel;
		
		guaranteePanel = new GuaranteePanel();
		pagePanels[1] = guaranteePanel;
		
		outputPanel = new OutputPanel();
		pagePanels[2] = outputPanel;
		
		contentPanel.setInternalPanel(pagePanels[0]);
		
        mainPanel.add(contentPanel);
        
//		Pagination Panel
        JPanel paginationPanel = new JPanel();
        paginationPanel.setLayout(new FlowLayout());
        
        backButton.setEnabled(false);
        paginationPanel.add(backButton);
        paginationPanel.add(nextButton);
        
        mainPanel.add(paginationPanel);
        
//      Add Entire Content Panel
        
        add(mainPanel);     
	    
	    setSize(750, 600);
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
	
	public static void main(String[] args) {
        new GUMBOInterface().setVisible(true);
    }
}

class HeaderPanel extends JPanel {
	private JLabel headerLabel = new JLabel();
	
	public HeaderPanel() {
		headerLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 32));
		add(headerLabel);
	}
	
	public void setHeaderLabel(String newLabel) {
		headerLabel.setText(newLabel.substring(0, 1).toUpperCase() + newLabel.substring(1));
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

class AssumptionPanel extends JPanel {
	public AssumptionPanel() {
		setLayout(new FlowLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 4; i++) {
        	listModel.addElement(
        			String.format("assume \"Sample assumption\" : (%s < %d)", AGREEComponentFactory.createParameter(), 300)
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
        
        JComboBox<String> assumptionOperandList = new JComboBox<>(AGREEComponentFactory.getAllParameters());
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

class GuaranteePanel extends JPanel {
	public GuaranteePanel() {
		JLabel guaranteeTest = new JLabel("guarantee test");
		
		add(guaranteeTest);
	}
}

class OutputPanel extends JPanel {
	public OutputPanel() {
		JLabel outputTest = new JLabel("output test");
		
		add(outputTest);
	}
}
