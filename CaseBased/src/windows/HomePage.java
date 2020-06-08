package windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JTextField;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import base.Dictionary;


import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import utils.CaseWriter;


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
	DefaultListModel<String> model_2 = new DefaultListModel<String>();
	DefaultListModel<String> model_3 = new DefaultListModel<String>();
	private JTextField textField_2;
	
	private JTable testsTable;
	private ArrayList<String> combos = new ArrayList<String>();
	ArrayList<String> tests;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HomePage window = new HomePage();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		JList<String> list = new JList<>(model);
		list.setBounds(12, 13, 340, 304);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setFixedCellWidth(200);
		list.setFixedCellHeight(20);
		panel.add(list);
		
		JList<String> list_1 = new JList<>(model_1);
		list_1.setBounds(615, 13, 340, 304);
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_1.setFixedCellWidth(200);
		list_1.setFixedCellHeight(20);
		panel.add(list_1);
		
		JButton btnNewButton_3 = new JButton("Save case");
		btnNewButton_3.setBounds(438, 218, 97, 25);
		panel.add(btnNewButton_3);
		
		refreshLists();
	
		
		btnNewButton.addActionListener(e ->
		{
			ArrayList<String> symptomsList = new ArrayList<>();
			int[] selectedIx = list.getSelectedIndices();
			for (int ix : selectedIx) {
				Object sel = list.getModel().getElementAt(ix);
				symptomsList.add(sel.toString());
			}
			

			ArrayList<String> geneticsList = new ArrayList<String>();
			int[] selectedIxG = list_1.getSelectedIndices();
			for (int i = 0; i < selectedIxG.length; i++) {
			      Object sel = list_1.getModel().getElementAt(selectedIxG[i]);
			      geneticsList.add(sel.toString());	

			}
			
			ArrayList<String> results = cbr.additionalTests.CbrTestApplication.doCbrApplication(symptomsList, geneticsList);
			
			textArea.setText("");
			assert results != null;
			results.stream().map(i -> i+"\n").forEach(textArea::append);
		});
		
		btnNewButton_3.addActionListener(e ->
		{
			ArrayList<String> symptomsList = new ArrayList<>();
			int[] selectedIx = list.getSelectedIndices();
			for (int ix : selectedIx) {
				Object sel = list.getModel().getElementAt(ix);
				symptomsList.add(sel.toString());
			}
			
			ArrayList<String> geneticsList = new ArrayList<>();
			int[] selectedIxG = list_1.getSelectedIndices();
			for (int value : selectedIxG) {
				Object sel = list_1.getModel().getElementAt(value);
				geneticsList.add(sel.toString());
			}
			
			List<String> initialDiagnosisList = Arrays.asList("anemia"); // TODO Real values 
			
			List<String> additionalTestsList = Arrays.asList("iron_check", "b12_check"); // TODO Real values

			System.out.println(CaseWriter.testsToCsv(symptomsList, geneticsList, initialDiagnosisList, additionalTestsList));
		});
		
	}
	
	
	private void initPanel1() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 330, 943, 240);
		panel_1.add(scrollPane);

		JButton btnNewButton = new JButton("Do CBR");
		btnNewButton.setBounds(820, 292, 97, 25);
		panel_1.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JList<String> list_2 = new JList<String>(model_2);
		list_2.setBounds(12, 25, 200, 290);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setFixedCellWidth(200);
		list_2.setFixedCellHeight(20);
		panel_1.add(list_2);

		JList<String> list_1 = new JList<String>(model_1);
		list_1.setBounds(250, 25, 200, 290);
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_1.setFixedCellWidth(200);
		list_1.setFixedCellHeight(20);
		panel_1.add(list_1);
		
		JLabel lblInitalDiagnosis = new JLabel("Inital diagnosis");
		lblInitalDiagnosis.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblInitalDiagnosis.setForeground(Color.BLACK);
		lblInitalDiagnosis.setBounds(57, 10, 97, 13);
		panel_1.add(lblInitalDiagnosis);
		
		JLabel lblAdditionalTests = new JLabel("Genetics list");
		lblAdditionalTests.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAdditionalTests.setBounds(296, 10, 97, 13);
		panel_1.add(lblAdditionalTests);
		
		JRadioButton rdbtnMale = new JRadioButton("male");
		rdbtnMale.setBounds(852, 58, 60, 21);
		panel_1.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("female");
		rdbtnFemale.setBounds(852, 25, 103, 21);
		panel_1.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		JCheckBox chckbxSmoker = new JCheckBox("smoker");
		chckbxSmoker.setBounds(852, 111, 93, 21);
		panel_1.add(chckbxSmoker);

		
		JCheckBox chckbxPregnant = new JCheckBox("pregnant");
		chckbxPregnant.setBounds(852, 154, 93, 21);
		panel_1.add(chckbxPregnant);
		
		JCheckBox chckbxActive = new JCheckBox("active");
		chckbxActive.setBounds(852, 199, 93, 21);
		panel_1.add(chckbxActive);
		
		textField_2 = new JTextField();
		textField_2.setBounds(873, 236, 60, 19);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(831, 235, 32, 21);
		panel_1.add(lblAge);
		
		tests = getTests();
		
		btnNewButton.addActionListener(e ->
		{
			ArrayList<String> initialDiagnosis = new ArrayList<String>();
			int[] selectedIx = list_2.getSelectedIndices();
			for (int i = 0; i < selectedIx.length; i++) {
			      Object sel = list_2.getModel().getElementAt(selectedIx[i]);
			      initialDiagnosis.add(sel.toString());
			}
			
			ArrayList<String> geneticsList = new ArrayList<String>();
			int[] selectedIxG = list_1.getSelectedIndices();
			for (int i = 0; i < selectedIxG.length; i++) {
			      Object sel = list_1.getModel().getElementAt(selectedIxG[i]);
			      geneticsList.add(sel.toString());
			}
			
			String smoker;
			if(chckbxSmoker.isSelected()) {
				smoker="smoker";
			}else
			{
				smoker="non-smoker";
			}
			
			String pregnant;
			if(chckbxPregnant.isSelected()) {
				pregnant="pregnant";
			}else
			{
				pregnant="non-pregnant";
			}
			
			String active;
			if(chckbxActive.isSelected()) {
				active="active";
			}else
			{
				active="non-active";
			}
			
			String gender;
	
			if(rdbtnMale.isSelected()) {
				gender="male";
			}else{
				gender="female";
			}
			
			
			int age;
			age=Integer.parseInt(textField_2.getText());
			
			ArrayList<String> results = cbr.diagnosis.CbrDiagnosisApplication.doCbrDiagnosisApplication(initialDiagnosis, geneticsList, smoker, pregnant, active, gender, age, tests);
			
			textArea.setText("");
			results.stream().map(i -> i+"\n").forEach(textArea::append);
		});
		
		
	}
	
	ArrayList<String> getTests(){
		DefaultTableModel tableModelTests = new DefaultTableModel();
		JList<String> tests = new JList<String>(model_3);
		//inicijalizovanje tabele i ispisivanje testova u prvu kolonu, a change u drugu gde treba da dodje combo box
		tableModelTests.setDataVector(new Object[][]{}, new Object[] {"Test","Result"});
	
		for(int i=0; i < tests.getModel().getSize(); i++){
			String o =  tests.getModel().getElementAt(i).toString();
			 tableModelTests.addRow(new Object[] {o, "Change..."});
		}
	
	
		testsTable = new JTable(tableModelTests){
			public boolean isCellEditable(int row,int column){
				return column != 0;//the 0th column is not editable
			}
		};
	
		testsTable.setRowHeight(30);
	
		//postavljanje combo boxa u celu prvu kolonu
		String[] items = {"low","normal","high"};
		JComboBox comboBoxTest = new JComboBox<String>(items);
		TableColumn col1 = testsTable.getColumnModel().getColumn(1);
		col1.setCellEditor(new DefaultCellEditor(comboBoxTest));
	
		JScrollPane scrollpane = new JScrollPane(testsTable);
	
		scrollpane.setBounds(490, 25, 270, 290);
	
		panel_1.add(scrollpane);
	
		//listener koji slusa sta je u kom combo boxu izabrano za rezultat testa
		tableModelTests.addTableModelListener(e -> {
	
			int row = e.getFirstRow();
			String rowS = Integer.toString(row);
	
			//dodajemo izabranu vrednost u listu ali ispred nje nalepimo broj reda kom ona pripada da bismo posle mogli da namapiramo na odgovarajuci test odgovarajui rez
			TableModel model = (TableModel)e.getSource();
			if(testsTable.getRowCount() != 0 &&  (model.getValueAt(row, 1)).toString() != "Change..."){
				String combo = (model.getValueAt(row, 0)).toString() + ":" + (model.getValueAt(row, 1)).toString();
				combos.add(combo);
			}
	
		});
	
		return combos;
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
		
		model_2.removeAllElements();
		Dictionary.getInitialDiagnosis().forEach(model_2::addElement);
		
		model_3.removeAllElements();
		Dictionary.getTests().forEach(model_3::addElement);
	}
}
