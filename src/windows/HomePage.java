package windows;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
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

	private JFrame frmExamination;

	//DefaultTableModel tableModelTests = new DefaultTableModel();
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
	
	private JTable testsTable;
	
	private int flag;

	private ArrayList<String> combos = new ArrayList<String>();
	private ArrayList<String> lines = new ArrayList<String>();
 
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
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Patients", null, panel, null);
		panel.setLayout(null);
		
		//table = new JTable(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(12, 13, 871, 281);
		panel.add(scrollpane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Examination", null, panel_1, null);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Additional testing", null, panel_2, null);
		panel_2.setLayout(null);
		
		/* Initialize functions */
		
		engine.consultFile("ruleBased/prolog.pl");
		
		showPatients();
		
		addSymptoms();
		
		initialDiagnosis();
		
		//showTestsTable();
		
	}
	
//////////////////////////////////////////////////////////////////IZLISTAVA PACIJENTE U COMBOBOX////////////////////////////////////////////////////////
	private void showPatients() {
		
		//uzmi osobe iz prologa
		JIPQuery query = engine.openSynchronousQuery("person(X)");
		ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution;
				
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				niz.add(var.getValue().toString());
			}
		}
		
		//labela pacijent
		lblPatient = new JLabel("Patient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPatient.setBounds(25, 10, 105, 57);
		panel_1.add(lblPatient);
		
		//ComboBox sa pacijentima
		comboBoxPatient = new JComboBox();
		comboBoxPatient.setBounds(131, 25, 127, 34);
		panel_1.add(comboBoxPatient);
				
		for( String ime: niz) {
			comboBoxPatient.addItem(ime);
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
		JIPQuery query = engine.openSynchronousQuery("symptom(X)");
		ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution;
		
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				niz.add(var.getValue().toString());
			}
		}
		
		//JLista 
		Vector items = new Vector(niz);
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
		JButton btnConfirmSimptoms = new JButton("Confirm simptoms");
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
		JIPQuery query = engine.openSynchronousQuery("suggest_diagnosis(symptoms(" + person + "," + simptomi + "), B)");
	    ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution;
			
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				niz.add(var.getValue().toString());
			
			}
		}
		
		//JLabel initial diagnosis
		JLabel lblInitialDiagnosis = new JLabel("Initial Diagnosis");
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
		JIPQuery query = engine.openSynchronousQuery("additional_test(symptoms(" + person + "," + simptomi + "), B)");
		ArrayList<String> nizTests= new ArrayList<String>();
		JIPTerm solution;
				
			
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				nizTests.add(var.getValue().toString());
				
			}
		}
				
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
		
    	System.out.println("uso");
    	DefaultTableModel tableModelTests = new DefaultTableModel();
    	//inicijalizovanje tabele i ispisivanje testova u prvu kolonu, a change u drugu gde treba da dodje combo box
    	tableModelTests.setDataVector(new Object[][]
    			{
    			},
    			new Object[] {"Test","Result"});
    	 
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
		System.out.println(col1);
			
		JScrollPane scrollpane = new JScrollPane(testsTable);
		//92, 77, 281, 184
		scrollpane.setBounds(12, 13, 350, 320);
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
	            	System.out.println("uso2");
	            	String combo = rowS + (model.getValueAt(row, 1)).toString();
	            	combos.add(combo);
				}
	            
			}
		});
		
		//na ovo dugme treba da se dodaju testovi i rezultati u prolog fajl, ne moze da se doda ako nisu svi testovi odradjeni
		JButton btnGetDiagnosis = new JButton("Get Diagnosis");
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
				
				if(flag == 0) {
		
					ArrayList<String> term = new ArrayList<String>();
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
					   
					   //ako postoji test za tu osobu onda ga menjamo novim rezultatom
					   if(readAllValues((test + "(" + person + ","), termin) == false){
							term.add(termin);
					   }
			
					}					
					
					//ispisujemo nove testove ako ih ima
					writeNewTests(term);
				}
			}
		});			
    }   
    
//////////////////////////////////////////////////////////////////ISPISIVANJE NOVIH TESTOVA////////////////////////////////////////////
    public void writeNewTests(ArrayList<String> term) {
    	FileWriter file = null;
    	PrintWriter pw = null;
    	
    	try {
    		
    		file = new FileWriter("ruleBased/prolog.pl", true);
    		pw = new PrintWriter(file);
    		
    		for(String t: term) {
    			pw.println(t);
    		}  		
    	}catch (Exception e){
    		e.printStackTrace();
    	} finally {
    		try {
    			if (null != file)
    				file.close();
    		}catch (Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	
    }  
    
  
/////////////////////////////////////////////////CITAMO SVE IZ FAJLA I AKO NADJEMO NA TEST KOJI HOCEMO DA UPISEMO PRESKOCIMO GA, AKO NE NADJEMO VRACAMO FALSE//////////////////    
    private boolean readAllValues(String termReplace, String termNew){
    	FileReader fr = null;
    	BufferedReader br = null;
    	String line = null;
    	
    	boolean retVal = false;
    	

    	try {

    		fr = new FileReader("ruleBased/prolog.pl");
            br = new BufferedReader(fr);
            
            lines.clear();
            while ((line = br.readLine()) != null) {
            	if(!line.contains(termReplace)) {
            		lines.add(line);
            	}else{
            		retVal = true;
            	}
             }
            
            //pisemo ceo fajl ponovo, , ako test nije postojao napisacemo ga gore, ako je postojao nismo ga dodali u lines nego cemo napisati novu vrednost
            writeAllValues(lines, termNew, retVal);
            
    	}catch (Exception e){
    		e.printStackTrace();
    	} finally {
    		try {
    			if (null != fr)
    				fr.close();
    		}catch (Exception e2) {
    			e2.printStackTrace();
    		}
    	} 
    	
    	return retVal;
    }  
 
/////////////////////////////////////////////////ISPISUJEMO CEO FAJL PONOVO/////////////////////////////////////////////////////////////////////
    private void writeAllValues(ArrayList<String> lines, String termNew, boolean retVal) {
    	FileWriter file = null;
    	PrintWriter pw = null;
    	
    	try {
    		
    		file = new FileWriter("ruleBased/prolog.pl");
    		pw = new PrintWriter(file);
    	
    		for(String l: lines) {
    			pw.write(l + "\n");
    		}
    	
    	//nova vrednost za postojeci test
    	if(retVal == true) {
    		pw.write(termNew);
    	}
    		
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	} finally {
    		try {
    			if (null != file)
    				file.close();
    		}catch (Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    
    }
}
