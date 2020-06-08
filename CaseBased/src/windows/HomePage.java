package windows;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import base.Dictionary;
import javax.swing.JTextField;

public class HomePage {

	private JFrame frame;
	
	//Tabbed panels
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
	//Lists
	DefaultListModel<String> model = new DefaultListModel<String>();
	DefaultListModel<String> model_1 = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CaseBased Examination");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 900, 450);

		//Tabs
		panel = new JPanel();
		tabbedPane.addTab("Tests", null, panel, null);
		panel.setLayout(null);

		panel_1 = new JPanel();
		tabbedPane.addTab("Diagnosis", null, panel_1, null);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		tabbedPane.addTab("Treatments", null, panel_2, null);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Cases", null, panel_3, null);
		panel_3.setLayout(null);

		frame.getContentPane().add(tabbedPane);
		
		initPanel();
		initPanel1();
		initPanel2();
		initPanel3();
	}

	private void initPanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 330, 943, 240);
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Do CBR");
		btnNewButton.setBounds(438, 292, 97, 25);
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JList<String> list = new JList<String>(model);
		list.setBounds(12, 13, 340, 304);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setFixedCellWidth(200);
		list.setFixedCellHeight(20);
		panel.add(list);
		
		JList<String> list_1 = new JList<String>(model_1);
		list_1.setBounds(615, 13, 340, 304);
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_1.setFixedCellWidth(200);
		list_1.setFixedCellHeight(20);
		panel.add(list_1);
		
		refreshLists();
		
		btnNewButton.addActionListener(e ->
		{
			ArrayList<String> symptomsList = new ArrayList<String>();
			int[] selectedIx = list.getSelectedIndices();
			for (int i = 0; i < selectedIx.length; i++) {
			      Object sel = list.getModel().getElementAt(selectedIx[i]);
			      symptomsList.add(sel.toString());
			}
			
			ArrayList<String> geneticsList = new ArrayList<String>();
			int[] selectedIxG = list.getSelectedIndices();
			for (int i = 0; i < selectedIxG.length; i++) {
			      Object sel = list.getModel().getElementAt(selectedIxG[i]);
			      geneticsList.add(sel.toString());
			}
			
			ArrayList<String> results = cbr.additionalTests.CbrTestApplication.doCbrApplication(symptomsList, geneticsList);
			
			textArea.setText("");
			results.stream().map(i -> i+"\n").forEach(textArea::append);
		});
		
	}
	
	private void initPanel1() {
		// TODO Auto-generated method stub
		
	}
	
	private void initPanel2() {
		// TODO Auto-generated method stub
		
	}
	
	private void initPanel3() {

		JTextField textField = new JTextField();
		textField.setBounds(12, 13, 116, 22);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(12, 70, 116, 22);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Add symptom");
		btnNewButton_1.addActionListener(e -> {
			Dictionary.addSymptom(textField.getText());
			refreshLists();
		});
		btnNewButton_1.setBounds(140, 12, 116, 25);
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add genetics");
		btnNewButton_2.addActionListener(e -> {
			Dictionary.addGenetics(textField.getText());
			refreshLists();
		});
		btnNewButton_2.setBounds(140, 69, 116, 25);
		panel_3.add(btnNewButton_2);
		
	}
	
	private void refreshLists() {
		model.removeAllElements();
		Dictionary.getSymptoms().forEach(model::addElement);
		
		model_1.removeAllElements();
		Dictionary.getGenetics().forEach(model_1::addElement);
	}
}
