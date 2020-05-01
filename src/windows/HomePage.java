package windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class HomePage {

	private JFrame frmExamination;
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
		frmExamination.setTitle("Examination");
		frmExamination.setBounds(100, 100, 773, 574);
		frmExamination.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamination.getContentPane().setLayout(null);
		
		
		engine.consultFile("ruleBased/prolog.pl");
		
		showPatients();
		
		addSymptoms();
	
	}
	
	private void showPatients() {
		
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
		frmExamination.getContentPane().add(lblPatient);
		
		//ComboBox sa pacijentima
		comboBoxPatient = new JComboBox();
		comboBoxPatient.setBounds(131, 25, 127, 34);
		frmExamination.getContentPane().add(comboBoxPatient);
				
		for( String ime: niz) {
			comboBoxPatient.addItem(ime);
		}
		
	}
	
	private void addSymptoms() {
		
		//labela AddSymptoms
		lblAddSymptoms = new JLabel("Symptoms");
		lblAddSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddSymptoms.setBounds(25, 75, 140, 57);
		frmExamination.getContentPane().add(lblAddSymptoms);
	
		
		//JLista 
		JIPQuery query = engine.openSynchronousQuery("symptom(X)");
		ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution;
		
		
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				niz.add(var.getValue().toString());
			}
		}
		
		Vector items = new Vector(niz);
	    symptomsList = new JList(items);
	    symptomsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    symptomsList.setFixedCellWidth(200);
        symptomsList.setFixedCellHeight(20);
	
	    //Panel i skroler
		panelSymptoms.setBounds(92, 77, 281, 184);
	    scrollPaneSymptoms.setViewportView(symptomsList);
	    symptomsList.setLayoutOrientation(JList.VERTICAL);
	    panelSymptoms.add(scrollPaneSymptoms);
		frmExamination.getContentPane().add(panelSymptoms);
		
		
		//Confirm simptoms button
		JButton btnConfirmSimptoms = new JButton("Confirm simptoms");
		
		btnConfirmSimptoms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				person = comboBoxPatient.getSelectedItem().toString();
				System.out.println(person);
								
				 int[] selectedIx = symptomsList.getSelectedIndices();
				
				 simptomi.clear(); 
				 for (int i = 0; i < selectedIx.length; i++) {
				      Object sel = symptomsList.getModel().getElementAt(selectedIx[i]);
				      simptomi.add(sel.toString());
				      System.out.println(simptomi);
				    }		
				 
				 initialDiagnosis();
			}
		});
		
		btnConfirmSimptoms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirmSimptoms.setBounds(125, 265, 200, 40);
		frmExamination.getContentPane().add(btnConfirmSimptoms);
				
	
	}
	
	private void initialDiagnosis() {
		
	
		//Button 
		JButton btnGetInitalDiagnosis = new JButton("Get Inital Diagnosis");
		
		btnGetInitalDiagnosis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ja2");
				panelDeseases.setVisible(true);
			}
		});
		
		btnGetInitalDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGetInitalDiagnosis.setBounds(125, 320, 200, 40);
		frmExamination.getContentPane().add(btnGetInitalDiagnosis);
		
		System.out.println("ovo je " + person);
		System.out.println("ovo su simptomi " + simptomi);
		//JLista 
		//suggest_diagnosis( symptoms(" + person + ", " + simptomi + "), B)"
		JIPQuery query = engine.openSynchronousQuery("suggest_diagnosis(symptoms(" + person + "," + simptomi + "), B)");
	    ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution;
		
	
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				niz.add(var.getValue().toString());
				System.out.println(var.getValue().toString());
			}
		}
		
		Vector items = new Vector(niz);
	    deseasesList = new JList(items);
	    deseasesList.setEnabled(false);
	    deseasesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    deseasesList.setFixedCellWidth(200);
	    deseasesList.setFixedCellHeight(20);
	    
	  	
		//Panel i skroler
        panelDeseases.setBounds(70, 370, 310, 100);
		scrollPaneDeseases.setViewportView(deseasesList);
		deseasesList.setLayoutOrientation(JList.VERTICAL);
		panelDeseases.add(scrollPaneDeseases);
	    frmExamination.getContentPane().add(panelDeseases);
	    panelDeseases.setVisible(false);
	    System.out.println("ja1");
	   
	}
}
