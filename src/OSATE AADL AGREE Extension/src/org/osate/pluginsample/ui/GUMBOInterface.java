package org.osate.pluginsample.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

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
