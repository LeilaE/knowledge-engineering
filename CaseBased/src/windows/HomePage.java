package windows;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import base.Dictionary;
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
	DefaultListModel<String> model_4 = new DefaultListModel<String>();

	private JTextField textField_2;
	
	private JTable testsTable;
	private ArrayList<String> combos = new ArrayList<String>();
	ArrayList<String> tests;
	
	ArrayList<String> results;
	ArrayList<String> resultsConfirmedDiagnosis;
	ArrayList<String> resultsTreatments;
	private JTextField textField_3;



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
		btnNewButton.setBounds(425, 229, 97, 25);
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JList<String> list = new JList<>(model);
		list.setBounds(12, 45, 340, 272);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setFixedCellWidth(200);
		list.setFixedCellHeight(20);
		panel.add(list);
		
		JList<String> list_1 = new JList<>(model_1);
		list_1.setBounds(615, 45, 340, 272);
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_1.setFixedCellWidth(200);
		list_1.setFixedCellHeight(20);
		panel.add(list_1);
		
		JButton btnNewButton_3 = new JButton("Save case");
		btnNewButton_3.setBounds(425, 282, 97, 25);
		btnNewButton_3.setVisible(false);
		panel.add(btnNewButton_3);
		
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSymptoms.setBounds(131, 10, 76, 27);
		panel.add(lblSymptoms);
		
		JLabel lblGeneticsList = new JLabel("Genetics list:");
		lblGeneticsList.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblGeneticsList.setBounds(733, 13, 92, 20);
		panel.add(lblGeneticsList);
		
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
			
		    results = cbr.additionalTests.CbrTestApplication.doCbrApplication(symptomsList, geneticsList);
		    System.out.println(results.get(0));
			
			textArea.setText("");
			assert results != null;
			results.stream().map(i -> i+"\n").forEach(textArea::append);
			
			btnNewButton_3.setVisible(true);
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
			
			ArrayList<String> parsedInitialDiagnosis = parseInitialDiagnosis(results);
			ArrayList<String> parsedTests = parseTests(results);
			
	
			System.out.println(CaseWriter.testsToCsv(symptomsList, geneticsList, parsedInitialDiagnosis, parsedTests));
		});
		
	}
	
	ArrayList<String> parseInitialDiagnosis(ArrayList<String> results){
		
		
	String[] parseTwo = results.get(0).split("initialDiagnosisList");
		
		String[] parseThree = parseTwo[1].split("\\[");
		
		String[] parseFour = parseThree[1].split("\\]");
		
		String[] parseFive = parseFour[0].split(",");
		
		ArrayList<String> parsedInitialDiagnosis = new ArrayList();
		
		for(String s: parseFive) {
			parsedInitialDiagnosis.add(s);
		}
			
		return parsedInitialDiagnosis;
	}
	
	ArrayList<String> parseTests(ArrayList<String> results){
		
	
		String[] parseTwo = results.get(0).split("additionalTestsList");
		
		String[] parseThree = parseTwo[1].split("\\[");
		
		String[] parseFour = parseThree[1].split("\\]");
		
		String[] parseFive = parseFour[0].split(",");
		
		ArrayList<String> parsedTests = new ArrayList();
		
		for(String s: parseFive) {
			parsedTests.add(s);
		}
			
		return parsedTests;
	}
	
	
	private void initPanel1() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 330, 943, 240);
		panel_1.add(scrollPane);

		JButton btnNewButton = new JButton("Do CBR");
		btnNewButton.setBounds(863, 276, 82, 21);
		panel_1.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JList<String> list_2 = new JList<String>(model_2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setFixedCellWidth(200);
		list_2.setFixedCellHeight(20);
		
		JScrollPane scrollPane1 = new JScrollPane(list_2);
		scrollPane1.setBounds(12, 25, 200, 290);
		panel_1.add(scrollPane1);

		JPanel panelGenetics = new JPanel();
		JScrollPane scrollPane1 = new JScrollPane();
		panel_1.add(scrollPane1);
		
		
		JList<String> list_1 = new JList<String>(model_1);
		
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_1.setFixedCellWidth(180);
		list_1.setFixedCellHeight(36);
		
		panelGenetics.setBounds(250, 25, 200, 500);
		scrollPane1.setViewportView(list_1);
		list_1.setLayoutOrientation(JList.VERTICAL);
		panelGenetics.add(scrollPane1);
		panel_1.add(panelGenetics);
		
		
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
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(831, 235, 32, 21);
		panel_1.add(lblAge);
		
		JButton btnSaveCase = new JButton("Save case");
		btnSaveCase.setBounds(863, 307, 95, 21);
		btnSaveCase.setVisible(false);
		panel_1.add(btnSaveCase);
		
		textField_3 = new JTextField();
		textField_3.setBounds(863, 236, 60, 19);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
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
			age=Integer.parseInt(textField_3.getText());
			
			resultsConfirmedDiagnosis = cbr.diagnosis.CbrDiagnosisApplication.doCbrDiagnosisApplication(initialDiagnosis, geneticsList, smoker, pregnant, active, gender, age, tests);
			
			textArea.setText("");
			resultsConfirmedDiagnosis.stream().map(i -> i+"\n").forEach(textArea::append);
			
			btnSaveCase.setVisible(true);
		});
		
		btnSaveCase.addActionListener(e ->
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
			age=Integer.parseInt(textField_3.getText());
			
			ArrayList<String> parsedConfirmedDiagnosis = parseConfirmedDiagnosis(resultsConfirmedDiagnosis);
		
			System.out.println(CaseWriter.diagnosisToCsv(initialDiagnosis, geneticsList, smoker, pregnant, active, gender, age, tests, parsedConfirmedDiagnosis));
		});
		
		
	}
	
	ArrayList<String> parseConfirmedDiagnosis(ArrayList<String> results){
		
		
		String[] parseTwo = results.get(0).split("confirmedDiagnosisList");
		
		String[] parseThree = parseTwo[1].split("\\[");
		
		String[] parseFour = parseThree[1].split("\\]");
		
		String[] parseFive = parseFour[0].split(",");
		
		ArrayList<String> parsedConfirmedDiagnosis = new ArrayList();
		
		for(String s: parseFive) {
			parsedConfirmedDiagnosis.add(s);
		}
			
		return parsedConfirmedDiagnosis;
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
		JComboBox comboBoxTest = new JComboBox(items);
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 330, 943, 240);
		panel_2.add(scrollPane);

		JButton btnNewButton = new JButton("Do CBR");
		btnNewButton.setBounds(516, 241, 97, 25);
		panel_2.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JList<String> list_3 = new JList<String>(model_4);
		list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_3.setFixedCellWidth(200);
		list_3.setFixedCellHeight(20);
		
		JScrollPane scrollPane1 = new JScrollPane(list_3);
		scrollPane1.setBounds(250, 25, 200, 290);
		panel_2.add(scrollPane1);
		
		JLabel lblDiagnosis = new JLabel("Confirmed Diagnosis");
		lblDiagnosis.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDiagnosis.setForeground(Color.BLACK);
		lblDiagnosis.setBounds(300, 10, 120, 13);
		panel_2.add(lblDiagnosis);
		

		JRadioButton rdbtnMale = new JRadioButton("male");
		rdbtnMale.setBounds(530, 98, 60, 21);
		panel_2.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("female");
		rdbtnFemale.setBounds(530, 75, 103, 21);
		panel_2.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		

		textField_2 = new JTextField();
		textField_2.setBounds(530, 150, 60, 19);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(500, 151, 32, 21);
		panel_2.add(lblAge);
		
		JButton btnSaveCase_1 = new JButton("Save case");
		btnSaveCase_1.setBounds(516, 276, 97, 25);
		btnSaveCase_1.setVisible(false);
		panel_2.add(btnSaveCase_1);
		
		btnNewButton.addActionListener(e ->
		{
			ArrayList<String> diagnosis = new ArrayList<String>();
			int[] selectedIx = list_3.getSelectedIndices();
			for (int i = 0; i < selectedIx.length; i++) {
			      Object sel = list_3.getModel().getElementAt(selectedIx[i]);
			      diagnosis.add(sel.toString());
			}
			
			
			String gender;
	
			if(rdbtnMale.isSelected()) {
				gender="male";
			}else{
				gender="female";
			}
			
			
			int age;
			age=Integer.parseInt(textField_2.getText());
			
			resultsTreatments = cbr.treatments.CbrTreatmentApplication.doCbrTreatmentsApplication(diagnosis, gender, age);
			
			textArea.setText("");
			resultsTreatments.stream().map(i -> i+"\n").forEach(textArea::append);
			
			btnSaveCase_1.setVisible(true);
		});
		
		btnSaveCase_1.addActionListener(e ->
		{
			ArrayList<String> diagnosis = new ArrayList<String>();
			int[] selectedIx = list_3.getSelectedIndices();
			for (int i = 0; i < selectedIx.length; i++) {
			      Object sel = list_3.getModel().getElementAt(selectedIx[i]);
			      diagnosis.add(sel.toString());
			}
			
			
			String gender;
	
			if(rdbtnMale.isSelected()) {
				gender="male";
			}else{
				gender="female";
			}
			
			
			int age;
			age=Integer.parseInt(textField_2.getText());
			
			ArrayList<String> parsedTreatments = parseTreatments(resultsTreatments);
			
			System.out.println(CaseWriter.treatmentsToCsv(diagnosis, gender, age, parsedTreatments));
		});
		
	}
	
	ArrayList<String> parseTreatments(ArrayList<String> results){
		
		
		String[] parseTwo = results.get(0).split("treatment");
		
		String[] parseThree = parseTwo[1].split("\\[");
		
		String[] parseFour = parseThree[1].split("\\]");
		
		String[] parseFive = parseFour[0].split(",");
		
		ArrayList<String> parsedTreatments = new ArrayList();
		
		for(String s: parseFive) {
			parsedTreatments.add(s);
		}
			
		return parsedTreatments;
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
			Dictionary.addGenetics(textField_1.getText());
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
		
		model_4.removeAllElements();
		Dictionary.getDiagnosis().forEach(model_4::addElement);
	}
}
