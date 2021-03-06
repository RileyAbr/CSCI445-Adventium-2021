package org.osate.pluginsample.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * The UI for this plugin takes place in a single JFrame, in which three sub-JPanels or "pages" are maintained. These pages are the Assumption, Guarantee, & Output Panels.
 * The Assumption and Guarantee panels are nested in this class. The OutputPanel can be found in a a stand-alone file titled `OutputPanel.java`.
 * The state of the application is all handled in the highest level, using private fields on the GUMBOInterface. Modifications to this state is performed in the panels themselves 
 * via event-listener like functions. The individual JPanels then must also re-render themselves to update with the modificates made to the internal state.
 */ 
public class GUMBOInterface extends JFrame {
	private int currentPage = 0;
	private String[] pages = { "assumptions", "guarantees", "output" };
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

	/**
	 * The constructor creates a GUMBOInterface with the supplied parameters, which instantly launches from Eclipse once the construction is complete.
	 * @param incomingInputFeatures 		String[] of the input feature variable names
	 * @param incomingInputFeaturesTypes	String[] of the input feature variable types
	 * @param incomingOutputFeatures		String[] of the output feature variable names
	 * @param incomingOutputFeaturesTypes	String[] of the output feature variable types
	 * @param incomingAssumptions			ArrayList<String> of the set of assumptions matching an AADL component, typically loaded from an external file
	 * @param incomingGuarantees			ArrayList<String> of the set of guarantees matching an AADL component, typically loaded from an external file
	 * @param incomingSystemName			The name of the system selected when the GUMBOInterface is created
	 * @param incomingPath					The path specified by the user to store/read the assumption and guarantee data locally
	 */
	public GUMBOInterface(String[] incomingInputFeatures, String[] incomingInputFeaturesTypes,
			String[] incomingOutputFeatures, String[] incomingOutputFeaturesTypes,
			ArrayList<String> incomingAssumptions, ArrayList<String> incomingGuarantees, String incomingSystemName,
			String incomingPath) {
		super("AGREE Creator");

		inputFeatures = incomingInputFeatures;
		inputFeaturesTypes = incomingInputFeaturesTypes;
		outputFeatures = incomingOutputFeatures;
		outputFeaturesTypes = incomingOutputFeaturesTypes;
		assumptions = incomingAssumptions;
		guarantees = incomingGuarantees;
		systemName = incomingSystemName;
		directoryPath = incomingPath;

		JPanel mainPanel = new JPanel();
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

		setSize(575, 520);
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

		String path = directoryPath + "\\StoredAssumGuarants_" + systemName + ".txt";
		try {
			File myObj = new File(path);
			if (myObj.createNewFile()) {
				System.out.println("File Created " + myObj.getName() + " in " + myObj.getAbsolutePath() + "\n");
			} else {
				System.out.println("File already exists.\n");
				System.out.println("Absolute Path: " + myObj.getAbsolutePath() + "\n");
			}
		} catch (IOException e) {
			System.out.println("An error occurred with your default path.\n");
			e.printStackTrace();
		}

		System.out.println("OutputValue is \n" + outputValue);

//		Write to text file
		try {
			FileWriter myWriter = new FileWriter(path);
			myWriter.write(outputValue);
			myWriter.close();
			System.out.println("Successfully wrote to the file.\n");

			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Successfuly wrote to the file: " + "StoredAssumGuarants_" + systemName
					+ ".txt" + "\nPath to file: " + path);
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
			if (currentPage <= 0) {
				currentPage = 0;
			} else if (currentPage >= pages.length - 1) {
				currentPage = pages.length - 2;
			} else {
				currentPage--;
			}

//          Set Button Statuses
			if (currentPage <= 0) {
				backButton.setEnabled(false);
			} else {
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
			if (currentPage >= pages.length - 1) {
				endGUMBOInterface();
			} else if (currentPage < 0) {
				currentPage = 1;
			} else {
				currentPage++;
			}

//          Set Button Statuses
			if (currentPage >= pages.length - 1) {
				updateOutputPanel();
				nextButton.setText("Finish");
			} else {
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
		private JPanel assumptionValueTextFieldPanel;
		private JFormattedTextField assumptionValueTextField;
		private int selectedAssumptionOperandIndex;
		private String[] assumptionComparatorListValues;
		private JComboBox<String> assumptionComparatorList;
		

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
			assumptionOperandList.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						selectedAssumptionOperandIndex = assumptionOperandList.getSelectedIndex();
						updateAssumptionComparatorList();
						updateAssumptionValueTextField();
					}
				}
			});
			inputsPanel.add(assumptionOperandList);

//          Comparator Panel
			assumptionComparatorPanel = new JPanel();

			updateAssumptionComparatorList();

			inputsPanel.add(assumptionComparatorPanel);

//          + Button Panel
			JPanel addButtonPanel = new JPanel();

//			Assumption Text Field Panel
			assumptionValueTextFieldPanel = new JPanel();
			
			updateAssumptionValueTextField();

			addButtonPanel.add(assumptionValueTextFieldPanel);

			JButton addAssumptionButton = new JButton(new AddAssumptionAction("+"));
			addButtonPanel.add(addAssumptionButton);

			inputsPanel.add(addButtonPanel);

			add(inputsPanel);

//          Custom Panel
			JPanel customPanel = new JPanel();
			customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.PAGE_AXIS));

			JPanel customButtonPanel = new JPanel();
			JButton customButton = new JButton(new LaunchCustomAssumptionModalAction("Custom Statement"));

			customButtonPanel.add(customButton);

			customPanel.add(new JSeparator(JSeparator.HORIZONTAL));
			customPanel.add(Box.createVerticalStrut(5));
			customPanel.add(customButtonPanel);

			add(customPanel, BorderLayout.PAGE_END);
		}

		private void updateAssumptionComparatorList() {
			if (assumptionComparatorList != null) {
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
			assumptionComparatorList.setMaximumSize(assumptionComparatorList.getPreferredSize());
			assumptionComparatorPanel.add(assumptionComparatorList);

			revalidate();
			repaint();
		}
		
		private void updateAssumptionValueTextField() {
			if (assumptionValueTextField != null) {
				assumptionValueTextFieldPanel.remove(assumptionValueTextField);
			}
			
			switch (inputFeaturesTypes[selectedAssumptionOperandIndex]) {
			case "Integer":
				NumberFormat integerFormat = NumberFormat.getIntegerInstance();
				;

				NumberFormatter intNumberFormatter = new NumberFormatter(integerFormat);
//				intNumberFormatter.setAllowsInvalid(false);
				
				intNumberFormatter.setMinimum(0);

				assumptionValueTextField = new JFormattedTextField(intNumberFormatter);
				assumptionValueTextField.setText("0");
				break;
			case "Float":
				NumberFormat longFormat = new DecimalFormat("#0.00");
				;

				NumberFormatter floatNumberFormatter = new NumberFormatter(longFormat);
				floatNumberFormatter.setValueClass(Long.class);
//				floatNumberFormatter.setAllowsInvalid(false);
				floatNumberFormatter.setMinimum(0l);

				assumptionValueTextField = new JFormattedTextField(floatNumberFormatter);
				assumptionValueTextField.setText("0.0");
				break;
			case "Boolean":
			default:
				assumptionValueTextField = new JFormattedTextField();
				assumptionValueTextField.setText("true");
				break;
			}
			
			assumptionValueTextField.setColumns(20);

			assumptionValueTextFieldPanel.add(assumptionValueTextField);
		}

		private void updateListPane() {
			if (assumptionListScrollPane != null) {
				listPanel.remove(assumptionListScrollPane);
			}
			if (removeAssumptionButton != null) {
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

				if (assumptionList.getModel().getSize() > 0 && currentSelection > -1) {
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
				if (!descValue.isEmpty() && !parameterValue.isEmpty() && !comparatorValue.isEmpty()
						&& !assumptionValue.isEmpty()) {
					assumptions.add(String.format("assume \"%s\" : (%s %s %s)", descValue, parameterValue,
							comparatorValue, assumptionValue));
					updateListPane();

					agreeDescriptionTextField.setText("");
					assumptionValueTextField.setText("0");
				} else {

				}
			}
		}

		private class LaunchCustomAssumptionModalAction extends AbstractAction {
			public LaunchCustomAssumptionModalAction(String name) {
				super(name);
				int mnemonic = (int) name.charAt(0);
				putValue(MNEMONIC_KEY, mnemonic);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomAssumptionModal();
			}
		}

		private class CustomAssumptionModal extends JDialog {
			private JTextField customAgreeDescriptionTextField = new JTextField("", 20);
			private JTextField customAssumptionLogicTextField = new JTextField("", 20);
			private JButton addButton = new JButton(new AddCustomAssumptionAction("Add"));
			private JButton cancelButton = new JButton(new CancelCustomAssumptionDialog("Cancel"));

			private CustomAssumptionModal() {
				super(GUMBOInterface.this, "Custom Assumption");

				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());

//    		    Statement Panel
				JPanel statementPanel = new JPanel();
				statementPanel.setLayout(new FlowLayout());

				statementPanel.add(new JLabel("assume"));

				statementPanel.add(customAgreeDescriptionTextField);

				statementPanel.add(new JLabel(": ("));

				statementPanel.add(customAssumptionLogicTextField);

				statementPanel.add(new JLabel(")"));

				mainPanel.add(statementPanel, BorderLayout.PAGE_START);

//    			Pagination Panel
				JPanel paginationPanel = new JPanel();
				paginationPanel.setLayout(new FlowLayout());

				paginationPanel.add(addButton);
				paginationPanel.add(cancelButton);

				mainPanel.add(paginationPanel, BorderLayout.PAGE_END);

				add(mainPanel);

				setSize(540, 110);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setVisible(true);
			}

			private void endCustomAssumptionDialog() {
				setVisible(false);
				dispose();
			}

			private class AddCustomAssumptionAction extends AbstractAction {
				public AddCustomAssumptionAction(String name) {
					super(name);
					int mnemonic = (int) name.charAt(0);
					putValue(MNEMONIC_KEY, mnemonic);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					String descValue = customAgreeDescriptionTextField.getText();
					String logicValue = customAssumptionLogicTextField.getText();
					if (!descValue.isEmpty() && !logicValue.isEmpty()) {
						assumptions.add(String.format("assume \"%s\" : ( %s )", descValue, logicValue));
						updateListPane();
						endCustomAssumptionDialog();
					} else {

					}
				}
			}

			private class CancelCustomAssumptionDialog extends AbstractAction {
				public CancelCustomAssumptionDialog(String name) {
					super(name);
					int mnemonic = (int) name.charAt(0);
					putValue(MNEMONIC_KEY, mnemonic);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					endCustomAssumptionDialog();
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
		private JPanel conditionalValueTextFieldPanel;
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
			conditionalOperandList.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ie) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						selectedConditionalOperandIndex = conditionalOperandList.getSelectedIndex();
						updateConditionalComparatorList();
						updateConditionalValueTextField();
					}
				}
			});
			inputsPanel.add(conditionalOperandList);

//          1st Comparator Panel
			assumptionComparatorPanel = new JPanel();

			updateConditionalComparatorList();

			inputsPanel.add(assumptionComparatorPanel);
			
//			Conditional Text Field Panel
			conditionalValueTextFieldPanel = new JPanel();
			
			updateConditionalValueTextField();
			
			inputsPanel.add(conditionalValueTextFieldPanel);

//          2nd Comparator Panel
			JPanel guaranteeComparatorPanel = new JPanel();

			guaranteeComparatorList = new JComboBox<>(AGREEComponentFactory.getAllGuaranteeComparators());
			guaranteeComparatorList.setMaximumSize(guaranteeComparatorList.getPreferredSize());
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

//          Custom Panel
			JPanel customPanel = new JPanel();
			customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.PAGE_AXIS));

			JPanel customButtonPanel = new JPanel();
			JButton customButton = new JButton(new LaunchCustomGuaranteeModalAction("Custom Statement"));

			customButtonPanel.add(customButton);

			customPanel.add(new JSeparator(JSeparator.HORIZONTAL));
			customPanel.add(Box.createVerticalStrut(1));
			customPanel.add(customButtonPanel);

			add(customPanel, BorderLayout.PAGE_END);
		}

		private void updateConditionalComparatorList() {
			if (assumptionComparatorList != null) {
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
			assumptionComparatorList.setMaximumSize(assumptionComparatorList.getPreferredSize());
			assumptionComparatorPanel.add(assumptionComparatorList);

			revalidate();
			repaint();
		}
		
		private void updateConditionalValueTextField() {
			if (conditionalValueTextField != null) {
				conditionalValueTextFieldPanel.remove(conditionalValueTextField);
			}
			
			switch (inputFeaturesTypes[selectedConditionalOperandIndex]) {
			case "Integer":
				NumberFormat integerFormat = NumberFormat.getIntegerInstance();
				;

				NumberFormatter intNumberFormatter = new NumberFormatter(integerFormat);
//				intNumberFormatter.setAllowsInvalid(false);
				
				intNumberFormatter.setMinimum(0);

				conditionalValueTextField = new JFormattedTextField(intNumberFormatter);
				conditionalValueTextField.setText("0");
				break;
			case "Float":
				NumberFormat longFormat = new DecimalFormat("#0.00");
				;

				NumberFormatter floatNumberFormatter = new NumberFormatter(longFormat);
				floatNumberFormatter.setValueClass(Long.class);
//				floatNumberFormatter.setAllowsInvalid(false);
				floatNumberFormatter.setMinimum(0l);

				conditionalValueTextField = new JFormattedTextField(floatNumberFormatter);
				conditionalValueTextField.setText("0.0");
				break;
			case "Boolean":
			default:
				conditionalValueTextField = new JFormattedTextField();
				conditionalValueTextField.setText("true");
				break;
			}
			
			conditionalValueTextField.setColumns(20);

			conditionalValueTextFieldPanel.add(conditionalValueTextField);
		}

		private void updateListPane() {
			if (guaranteeListScrollPane != null) {
				listPanel.remove(guaranteeListScrollPane);
			}
			if (removeGuaranteeButton != null) {
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

				if (guaranteeList.getModel().getSize() > 0 && currentSelection > -1) {
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
				String assumptionValue = conditionalValueTextField.getText();
				String comparatorValue = guaranteeComparatorList.getSelectedItem().toString();
				String parameterValue = guaranteeOperandList.getSelectedItem().toString();
				if (!descValue.isEmpty() && !conditionalParameterValue.isEmpty()
						&& !conditionalComparatorValue.isEmpty() && !parameterValue.isEmpty()
						&& !comparatorValue.isEmpty() && !assumptionValue.isEmpty()) {
					guarantees.add(
							String.format("guarantee \"%s\" : (%s %s %s) %s %s", descValue, conditionalParameterValue,
									conditionalComparatorValue, assumptionValue, comparatorValue, parameterValue));
					updateListPane();

					agreeDescriptionTextField.setText("");
					conditionalValueTextField.setText("0");
				} else {

				}
			}
		}

		private class LaunchCustomGuaranteeModalAction extends AbstractAction {
			public LaunchCustomGuaranteeModalAction(String name) {
				super(name);
				int mnemonic = (int) name.charAt(0);
				putValue(MNEMONIC_KEY, mnemonic);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomGuaranteeModal();
			}
		}

		private class CustomGuaranteeModal extends JDialog {
			private JTextField customAgreeDescriptionTextField = new JTextField("", 20);
			private JTextField customGuaranteeLogicTextField = new JTextField("", 20);
			private JButton addButton = new JButton(new AddCustomGuaranteeAction("Add"));
			private JButton cancelButton = new JButton(new CancelCustomGuaranteeDialog("Cancel"));

			private CustomGuaranteeModal() {
				super(GUMBOInterface.this, "Custom Guarantee");

				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());

//    		    Statement Panel
				JPanel statementPanel = new JPanel();
				statementPanel.setLayout(new FlowLayout());

				statementPanel.add(new JLabel("guarantee"));

				statementPanel.add(customAgreeDescriptionTextField);

				statementPanel.add(new JLabel(": ("));

				statementPanel.add(customGuaranteeLogicTextField);

				statementPanel.add(new JLabel(")"));

				mainPanel.add(statementPanel, BorderLayout.PAGE_START);

//    			Pagination Panel
				JPanel paginationPanel = new JPanel();
				paginationPanel.setLayout(new FlowLayout());

				paginationPanel.add(addButton);
				paginationPanel.add(cancelButton);

				mainPanel.add(paginationPanel, BorderLayout.PAGE_END);

				add(mainPanel);

				setSize(540, 110);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setVisible(true);
			}

			private void endCustomGuaranteeDialog() {
				setVisible(false);
				dispose();
			}

			private class AddCustomGuaranteeAction extends AbstractAction {
				public AddCustomGuaranteeAction(String name) {
					super(name);
					int mnemonic = (int) name.charAt(0);
					putValue(MNEMONIC_KEY, mnemonic);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					String descValue = customAgreeDescriptionTextField.getText();
					String logicValue = customGuaranteeLogicTextField.getText();
					if (!descValue.isEmpty() && !logicValue.isEmpty()) {
						guarantees.add(String.format("guarantee \"%s\" : ( %s )", descValue, logicValue));
						updateListPane();
						endCustomGuaranteeDialog();
					} else {

					}
				}
			}

			private class CancelCustomGuaranteeDialog extends AbstractAction {
				public CancelCustomGuaranteeDialog(String name) {
					super(name);
					int mnemonic = (int) name.charAt(0);
					putValue(MNEMONIC_KEY, mnemonic);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					endCustomGuaranteeDialog();
				}
			}
		}
	}
}

/**
 * Wrapper Panel class that assists with changed with of the 3 internal pages the GUI is currently displaying. Functionally identical to a JPanel with a single extra function.
 */
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
