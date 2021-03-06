package org.osate.pluginsample.ui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OutputPanel extends JPanel {
	private JTextArea outputTextArea = new JTextArea(20, 45);
	private String outputValue = "";

	public OutputPanel(ArrayList<String> assumptions, ArrayList<String> guarantees) {
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
//		Create new text file called PrevStoredAssumGuarants

//		Transfer data from StoredAssumGuarants to PrevStoredAssumGuarants

//		Output Box Panel
		JPanel outputBoxPanel = new JPanel();
		outputTextArea.setEditable(false);

		outputValue += "annex agree{** \n";

//		Assumptions
		for (String assumption : assumptions) {
			outputValue += "\t" + assumption + "\n";
		}

//		Guarantees
		for (String guarantee : guarantees) {
			outputValue += "\t" + guarantee + "\n";
		}

		outputValue += "\"**};";

		outputTextArea.setText(outputValue);
		outputBoxPanel.add(outputTextArea);
		outputPanel.add(outputBoxPanel);
		
//		Copy Button Panel
		JPanel copyButtonPanel = new JPanel();

		JButton copyToClipboardButton = new JButton(new CopyToClipboardAction("Copy to Clipboard"));
		copyButtonPanel.add(copyToClipboardButton);

		outputPanel.add(copyButtonPanel);

		add(outputPanel);

	}

	private class CopyToClipboardAction extends AbstractAction {

		public CopyToClipboardAction(String name) {
			super(name);
			int mnemonic = (int) name.charAt(0);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection stringSelection = new StringSelection(outputValue);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}
	}
}
