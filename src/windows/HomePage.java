package windows;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import javax.swing.DefaultCellEditor;
import controls.Patients;
import controls.Query;
import controls.Symptoms;
import controls.Tests;
import logic.FilesUtils;
import logic.PrologLogic;
import models.Patient;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class HomePage {

	//Jframe
	private JFrame frmExamination;
	private JFrame parent = new JFrame();

	//Patients Tab
	private JTable table_1;
	private JTextField textFieldName;
	private JTextField textFieldAge;
	JCheckBox chckbxActive;
	JCheckBox chckbxSmoker;
	JCheckBox chckbxPregnant;
	JButton btnAddPatient;

	private JTable table;
	private JPanel panel_1;
	private JPanel panel_2;

	private JIPEngine engine = new JIPEngine();

	private JLabel lblPatient;
	private JComboBox comboBoxPatient;

	private JLabel lblAddSymptoms;
	private JPanel panelSymptoms = new JPanel();
	private JScrollPane scrollPaneSymptoms = new JScrollPane();
	private JList symptomsList;

	private String person;
	private ArrayList<String> simptomi = new ArrayList<String>();

	private JPanel panelDeseases = new JPanel();
	private JScrollPane scrollPaneDeseases = new JScrollPane();
	private JList deseasesList;

	private JPanel panelAdditionalTests = new JPanel();
	private JScrollPane scrollPaneTests = new JScrollPane();
	private JList testsList = new JList();


	private JButton btnAdditionalTests= new JButton();
	private JButton btnGetInitalDiagnosis;

	private JLabel lblAdditionalTests= new JLabel();
	private JLabel lblInitialDiagnosis ;

	private JTable testsTable;
	private int flag;


	private ArrayList<String> combos = new ArrayList<String>();
	private ArrayList<String> lines = new ArrayList<String>();


	private JLabel lblConfirmedDiagnosis= new JLabel();
	private JList confirmedDiagnosisList;
	private JPanel panelConfirmedDiagnosis = new JPanel();
	private JScrollPane scrollPaneConfirmDiagnosis = new JScrollPane();
	private JButton btnConfirmedDiagnosis = new JButton();

	private JButton btnShowTreatments = new JButton();
	private JLabel lblTreatments= new JLabel();
	private JList treatmentsList;
	private JPanel panelTreatments = new JPanel();
	private JScrollPane scrollPaneTreatments = new JScrollPane();

	DefaultTableModel tableModel = new DefaultTableModel() {

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frmExamination.setVisible(true);
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
		frmExamination = new JFrame();
		frmExamination.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmExamination.setTitle("Expert System");
		frmExamination.setBounds(100, 100, 920, 500);
		frmExamination.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamination.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 900, 450);
		frmExamination.getContentPane().add(tabbedPane);

		//Tabs
		JPanel panel = new JPanel();
		tabbedPane.addTab("Patients", null, panel, null);
		panel.setLayout(null);

		panel_1 = new JPanel();
		tabbedPane.addTab("Examination", null, panel_1, null);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		tabbedPane.addTab("Additional testing", null, panel_2, null);
		panel_2.setLayout(null);

		engine.consultFile("ruleBased/prolog.pl");
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 900, 450);
		frmExamination.getContentPane().add(tabbedPane);

		//Patients table
		table_1 = new JTable(tableModel);
		JScrollPane scrollpane = new JScrollPane(table_1);
		scrollpane.setBounds(12, 13, 871, 281);
		panel.add(scrollpane);

		//New patient
		JLabel lblNewLabel = new JLabel("New patient");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 317, 95, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(194, 318, 56, 16);
		panel.add(lblNewLabel_1);

		textFieldName = new JTextField();
		textFieldName.setBounds(283, 317, 116, 22);
		panel.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Age:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(466, 318, 56, 16);
		panel.add(lblNewLabel_1_1);

		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(555, 317, 116, 22);
		panel.add(textFieldAge);

		chckbxActive = new JCheckBox("Active?");
		chckbxActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxActive.setBounds(12, 364, 113, 25);
		panel.add(chckbxActive);

		chckbxSmoker = new JCheckBox("Smoker?");
		chckbxSmoker.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxSmoker.setBounds(194, 365, 113, 25);
		panel.add(chckbxSmoker);

		chckbxPregnant = new JCheckBox("Pregnant?");
		chckbxPregnant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxPregnant.setBounds(466, 365, 113, 25);
		panel.add(chckbxPregnant);

		btnAddPatient = new JButton("Add");
		btnAddPatient.setBounds(786, 382, 97, 25);
		panel.add(btnAddPatient);

		//Examination Tab
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Examination", null, panel_1, null);
		panel_1.setLayout(null);

		/* Initialize functions */

		loadPatients();

		addTableListener();

		addbtnNewPatientListener();

		showPatients();

		addSymptoms();

		initialDiagnosis();

		//showTestsTable();
	}

	//Selected patient
	private Patient selectedPatient = null;

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	private void setSelectedPatient(ArrayList<String> userData) {
		String name = userData.get(0);
		String age = userData.get(1);
		String activity = userData.get(2);
		boolean smoker = userData.get(3).equals("yes");
		boolean pregnant = userData.get(4).equals("yes");
		Patient selectedPatient = new Patient(name, age, activity, smoker, pregnant, null);
		System.out.println(selectedPatient.getName());
		this.selectedPatient = selectedPatient;
	}

	private void addTableListener() {
		table_1.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON1) {
	            	ArrayList<String> patientData = new ArrayList<String>();
	            	int count = table_1.getColumnCount();
	            	for (int i = 0; i < count; i++) {
	            		String data = (String) table_1.getValueAt(table_1.getSelectedRow(), i).toString();
		                patientData.add(data);
	            	}
	            	setSelectedPatient(patientData);
	            }
	        }

	    });

	}

	private void addbtnNewPatientListener() {
		btnAddPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = textFieldName.getText();
				String age = textFieldAge.getText();
				String activity = chckbxActive.isSelected() ? "active" : "inactive";
				boolean smoker = chckbxSmoker.isSelected();
				boolean pregnant = chckbxPregnant.isSelected();
				Patients.addNewPatient(name, age, activity, smoker, pregnant);
				addPatientToTable(name, age, activity, smoker, pregnant);
			}
		});
	}

	private void addPatientToTable(String name, String age, String activity, boolean smoker, boolean pregnant) {
		tableModel.addRow(new Object[]{name, age, activity, smoker, pregnant, ""});
	}

	private void loadPatients() {
		ArrayList<Patient> patients = Patients.getPatients();

		tableModel.addColumn("Name");
		tableModel.addColumn("Age");
		tableModel.addColumn("Activity");
		tableModel.addColumn("Smoker");
		tableModel.addColumn("Pregnant");
		tableModel.addColumn("Genetics");

		for (Patient patient : patients) {
			tableModel.addRow(new Object[]{patient.getName(), patient.getAge(), patient.getActivity(), patient.isSmoker(), patient.isPregnant(), patient.getGenetics()});
		}
	}

//////////////////////////////////////////////////////////////////IZLISTAVA PACIJENTE U COMBOBOX////////////////////////////////////////////////////////

	private void showPatients() {

		//uzmi osobe iz prologa
		ArrayList<Patient> patients = Patients.getPatients();

		//labela pacijent
		lblPatient = new JLabel("Patient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPatient.setBounds(25, 10, 105, 57);
		panel_1.add(lblPatient);

		//ComboBox sa pacijentima
		comboBoxPatient = new JComboBox();
		comboBoxPatient.setBounds(131, 25, 127, 34);
		panel_1.add(comboBoxPatient);

		for( Patient p : patients) {
			comboBoxPatient.addItem(p.getName());
		}

	}

////////////////////////////////////////////////////////////DODAJE SIMPTOME U JLISTU/////////////////////////////////////////////////////////////////////////

	private void addSymptoms() {

		//labela AddSymptoms
		lblAddSymptoms = new JLabel("Symptoms");
		lblAddSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddSymptoms.setBounds(25, 75, 140, 57);
		panel_1.add(lblAddSymptoms);

		//uzima simptome iz prologa
		ArrayList<String> symptoms = Symptoms.getSymptoms();

		//JLista
		Vector items = new Vector(symptoms);
	    symptomsList = new JList(items);
	    symptomsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    symptomsList.setFixedCellWidth(200);
        symptomsList.setFixedCellHeight(20);

	    //Panel i skroler
		panelSymptoms.setBounds(96, 77, 289, 184);
	    scrollPaneSymptoms.setViewportView(symptomsList);
	    symptomsList.setLayoutOrientation(JList.VERTICAL);
	    panelSymptoms.add(scrollPaneSymptoms);
	    panel_1.add(panelSymptoms);

	  //Confirm simptoms button, kada se potvrde simptomi, pojavljuje se dugme za odredjivanje inicijalne dijagnoze
	  		JButton btnConfirmSimptoms = new JButton("Confirm symptoms");
	  		btnConfirmSimptoms.addActionListener(new ActionListener() {
	  			@Override
	  			public void actionPerformed(ActionEvent e) {

	  				//uzmi selektovanu osobu
	  				person = comboBoxPatient.getSelectedItem().toString();

	  				 //uzmi selektovane simptome
	  				 int[] selectedIx = symptomsList.getSelectedIndices();

	  				 simptomi.clear();
	  				 for (int i = 0; i < selectedIx.length; i++) {
	  				      Object sel = symptomsList.getModel().getElementAt(selectedIx[i]);
	  				      simptomi.add(sel.toString());
	  				  }

	  				 //dugme za odredjivanje inicijalne dijagnoze
	  				 btnGetInitalDiagnosis.setVisible(true);

	  				 //kad se opet klikne na dugme za potvrdu simptoma, dalji paneli opet postaju nevidljivi
	  				 panelAdditionalTests.setVisible(false);
	  				 btnAdditionalTests.setVisible(false);
	  				 lblAdditionalTests.setVisible(false);
	  				 panelDeseases.setVisible(false);
	  				 btnConfirmedDiagnosis.setVisible(false);

					 lblTreatments.setVisible(false);
		 			 panelTreatments.setVisible(false);
	  			}
	  		});

		btnConfirmSimptoms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirmSimptoms.setBounds(131, 271, 195, 40);

		panel_1.add(btnConfirmSimptoms);
	}

//////////////////////////////////////////////////////KADA SE KLIKNE NA OVO DUGME POZIVA SE FUNKCIJA ZA IZLISTAVANJE BOLESTI//////////////////////////////////////////
    private void initialDiagnosis() {

  		//Button
		btnGetInitalDiagnosis = new JButton("Get Inital Diagnosis");

		btnGetInitalDiagnosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelDeseases.setVisible(true);

				 //funkcija za izlistavanje bolesti koje spadaju u pocetnu dijagnozu
				 showDeseases();

				 //funkcija za izlistavanje dodatnih testova, jer se i oni izlistavaju na osnovu osobe i simptoma
				 additionalTests();

			}
		});

		btnGetInitalDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGetInitalDiagnosis.setBounds(131, 320, 194, 40);
		panel_1.add(btnGetInitalDiagnosis);
		btnGetInitalDiagnosis.setVisible(false);
    }

//////////////////////////////////////////////////////////IZLISTAVANJE BOLESTI KOJE SPADAJU U POCETNU DIJAGNOZU///////////////////////////////////////////

    private void showDeseases() {
    	//JLista

    	//na osnovu osobe i simptoma salje se prolog upit za bolestima
	    ArrayList<String> niz= Query.genericArrayQuery("suggest_diagnosis(symptoms(" + person + "," + simptomi + "), B)");

		//JLabel initial diagnosis

		lblInitialDiagnosis = new JLabel("Initial Diagnosis");

		lblInitialDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInitialDiagnosis.setBounds(440, 20, 200, 57);
		panel_1.add(lblInitialDiagnosis);

		//JList bolesti
		Vector items = new Vector(niz);
	    deseasesList = new JList(items);
	    deseasesList.setEnabled(false);
	    deseasesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    deseasesList.setFixedCellWidth(200);
	    deseasesList.setFixedCellHeight(20);

		//Panel i skroler
    	panelDeseases.setBounds(541, 20, 310, 100);
		scrollPaneDeseases.setViewportView(deseasesList);
		deseasesList.setLayoutOrientation(JList.VERTICAL);
		panelDeseases.add(scrollPaneDeseases);
	    panel_1.add(panelDeseases);
    }

////////////////////////////////////////////////IZLISTAVANJE DODATNIH TESTOVA i POZIV TABELE SA TESTOVIMA///////////////////////////////////////////////////////////

	private void additionalTests() {

		panelAdditionalTests.setVisible(true);

		//Button
		btnAdditionalTests = new JButton("Suggest additional tests");
		btnAdditionalTests.setVisible(true);

		//Kada se klikne na dugme additional tests, tada se na osnovu osobe i simptoma izlistavaju dodatni testovi koje bi osoba mogla da uradi
		btnAdditionalTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panelAdditionalTests.setVisible(true);
					lblAdditionalTests.setVisible(true);
			}
		});

		btnAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdditionalTests.setBounds(593, 171, 201, 40);
		panel_1.add(btnAdditionalTests);

		//JLista
		ArrayList<String> nizTests= Query.genericArrayQuery("additional_test(symptoms(" + person + "," + simptomi + "), B)");

		//Jlista testova
		Vector items = new Vector(nizTests);
		testsList = new JList(items);
		testsList.setEnabled(false);
		testsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		testsList.setFixedCellWidth(200);
		testsList.setFixedCellHeight(20);

		//JLabel additional tests
		lblAdditionalTests = new JLabel("Additional Tests");
		lblAdditionalTests.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdditionalTests.setBounds(440, 231, 200, 57);
		panel_1.add(lblAdditionalTests);

		//Panel i skroler
		panelAdditionalTests.setBounds(562,229, 268,184);
		scrollPaneTests.setViewportView(testsList);
		testsList.setLayoutOrientation(JList.VERTICAL);
		panelAdditionalTests.add(scrollPaneTests);
		panel_1.add(panelAdditionalTests);

		panelAdditionalTests.setVisible(false);
		lblAdditionalTests.setVisible(false);

		//izlistaj testove u tabeli
		showTestsTable();
    }

///////////////////////////////////////////////IZLISTAVANJE TABELE TESTOVA U NOVOM TABU////////////////////////////////////////////////////////////

	private void showTestsTable(){

    	DefaultTableModel tableModelTests = new DefaultTableModel();
    	//inicijalizovanje tabele i ispisivanje testova u prvu kolonu, a change u drugu gde treba da dodje combo box
    	tableModelTests.setDataVector(new Object[][]{}, new Object[] {"Test","Result"});

    	for(int i=0; i < testsList.getModel().getSize(); i++){
			String o =  testsList.getModel().getElementAt(i).toString();
			 tableModelTests.addRow(new Object[] {o, "Change..."});
		}


		testsTable = new JTable(tableModelTests){
			public boolean isCellEditable(int row,int column){
				    if(column == 0) return false;//the 0th column is not editable
				    return true;
			}
		};

		testsTable.setRowHeight(30);

		//postavljanje combo boxa u celu prvu kolonu
		String[] items = {"low","normal","high"};
		JComboBox comboBoxTest = new JComboBox<String>(items);
		TableColumn col1 = testsTable.getColumnModel().getColumn(1);
		col1.setCellEditor(new DefaultCellEditor(comboBoxTest));


		JScrollPane scrollpane = new JScrollPane(testsTable);

		scrollpane.setBounds(12, 13, 875, 320);

		panel_2.removeAll();
		panel_2.add(scrollpane);

		//listener koji slusa sta je u kom combo boxu izabrano za rezultat testa
		tableModelTests.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {

	            int row = e.getFirstRow();
	            String rowS = Integer.toString(row);

	            //dodajemo izabranu vrednost u listu ali ispred nje nalepimo broj reda kom ona pripada da bismo posle mogli da namapiramo na odgovarajuci test odgovarajui rez
	            TableModel model = (TableModel)e.getSource();
	            if(testsTable.getRowCount() != 0){
	            	String combo = rowS + (model.getValueAt(row, 1)).toString();
	            	combos.add(combo);
				}

			}
		});

		//na ovo dugme treba da se dodaju testovi i rezultati u prolog fajl, ne moze da se doda ako nisu svi testovi odradjeni

		JButton btnGetDiagnosis = new JButton("Do tests");

		btnGetDiagnosis.setBounds(35, 350, 300, 40);
		panel_2.add(btnGetDiagnosis);

		btnGetDiagnosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 0;
				//ako negde nije dodeljen rezultat ne moze(to mozemo promeniti)
				for(int i = 0; i < testsTable.getRowCount(); i ++) {
					 Object o = testsTable.getValueAt(i, 1);
					 if(o.equals("Change...")) {
						 flag = 1;
					 	 break;
					 }
				}

				String template = null;

				if(flag == 0) {
					ArrayList<String> newTestResults = new ArrayList<String>();
					//za svaki red uzmi test i njegov rezultat
					for(int i = 0; i < testsTable.getRowCount(); i ++) {
						Object test = testsTable.getValueAt(i, 0);
						Object result = new Object();

						//mapiranje rezultata na test
						for(String c: combos) {
							String sub = c.substring(0, 1);

							if(Integer.parseInt(sub) == i) {
								result = c.substring(1);
							}
						}

					   //ovo hocemo da upisemo ako ne postoji test za tu osobu
					   String termin = (test + "(" + person + "," + result + ").");
						template = test + "(" + person + "," + result + ").";
					   //ako postoji test za tu osobu onda ga menjamo novim rezultatom
					   newTestResults.add(termin);
					}

					//ispisujemo nove testove ako ih ima
					Tests.updateTests(template, newTestResults);
					confirmedDiagnosis();
				}
			}
		});
    }

    private void confirmedDiagnosis() {
	    //button
	    btnConfirmedDiagnosis = new JButton("Show confirmed diagnosis");
	    btnConfirmedDiagnosis.setVisible(true);
        panel_2.add(btnConfirmedDiagnosis);
        btnConfirmedDiagnosis.setBounds(555, 350, 300, 40);


		//otvaranje novog prozora klikom na dugme
        btnConfirmedDiagnosis.addActionListener(new java.awt.event.ActionListener() {
		@Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
			popupWindow();
         }
     });
 }

	private void popupWindow() {
		//new pop up window
		parent.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		parent.setTitle("Confirmed Diagnosis");
		parent.setBounds(400, 200,450,400);
	    // parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.getContentPane().setLayout(null);

		ArrayList<String> nizConfirmedDiagnosis = Query.genericArrayQuery("confirmed_diagnosis(symptoms(" + person + "," + simptomi + "), B)");

		Vector itemsCD = new Vector(nizConfirmedDiagnosis);
		confirmedDiagnosisList = new JList(itemsCD);
		confirmedDiagnosisList.setEnabled(false);
		confirmedDiagnosisList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		confirmedDiagnosisList.setFixedCellWidth(200);
		confirmedDiagnosisList.setFixedCellHeight(20);

		//Panel i skroler
		panelConfirmedDiagnosis.setBounds(80,20, 268,100);
		scrollPaneConfirmDiagnosis.setViewportView(confirmedDiagnosisList);
		confirmedDiagnosisList.setLayoutOrientation(JList.VERTICAL);
		panelConfirmedDiagnosis.add(scrollPaneConfirmDiagnosis);
		parent.add(panelConfirmedDiagnosis);

		//labela
		lblConfirmedDiagnosis = new JLabel("");
		lblConfirmedDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmedDiagnosis.setBounds(130, 10, 200, 57);
		parent.add(lblConfirmedDiagnosis);

	    //button
	    btnShowTreatments = new JButton("Show treatments");
	    parent.add(btnShowTreatments);
	    btnShowTreatments.setBounds(140,140, 150,30);

	    //labela
	    lblTreatments = new JLabel("Treatments");
	    lblTreatments.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblTreatments.setVisible(false);
	    lblTreatments.setBounds(10,180, 150,30);
	    parent.add(lblTreatments);

	    parent.setVisible(true);
		panelConfirmedDiagnosis.setVisible(true);
		lblConfirmedDiagnosis.setVisible(true);
		btnShowTreatments.setVisible(true);

		//prikaz treatments-a klikom na dugme
	    btnShowTreatments.addActionListener(new java.awt.event.ActionListener() {
			@Override
		  	public void actionPerformed(java.awt.event.ActionEvent evt) {
				lblTreatments.setVisible(true);
				panelTreatments.setVisible(true);
				treatments(nizConfirmedDiagnosis);
		  }
	  });

	}

	private void treatments(ArrayList<String> nizConfirmedDiagnosis) {

		//JLista

		ArrayList<String> nizTreatments = new ArrayList<String>();

		for(String diagnosis : nizConfirmedDiagnosis) {
			nizTreatments.addAll(Query.genericArrayQuery("treatment_for((" + nizConfirmedDiagnosis + "), B)"));
		}

		Vector items = new Vector(nizTreatments);
		treatmentsList = new JList(items);
		treatmentsList.setEnabled(false);
		treatmentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		treatmentsList.setFixedCellWidth(200);
		treatmentsList.setFixedCellHeight(20);

		//Panel i skroler
		panelTreatments.setBounds(80,180, 268,150);
		scrollPaneTreatments.setViewportView(treatmentsList);
		treatmentsList.setLayoutOrientation(JList.VERTICAL);
		panelTreatments.add(scrollPaneTreatments);
		parent.add(panelTreatments);
	}
}
