package windows;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controls.Patients;
import models.Patient;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class HomePage {

	private JFrame frmExamination;
	DefaultTableModel tableModel = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	
	private Patient selectedPatient = null;

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	private void setSelectedPatient(ArrayList<String> userData) {
		String name = userData.get(0);
		String age = userData.get(1);
		String activity = userData.get(2);
		boolean smoker = userData.get(3).equals("yes") ? true : false;
		boolean pregnant = userData.get(4).equals("yes") ? true : false;
		Patient selectedPatient = new Patient(name, age, activity, smoker, pregnant, null);
		System.out.println(selectedPatient.getName());
		this.selectedPatient = selectedPatient;
	}

	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	JCheckBox chckbxActive;
	JCheckBox chckbxSmoker;
	JCheckBox chckbxPregnant;

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
		
		table = new JTable(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(12, 13, 871, 281);
		panel.add(scrollpane);
		
		JLabel lblNewLabel = new JLabel("New patient");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 317, 95, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(194, 318, 56, 16);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(283, 317, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Age:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(466, 318, 56, 16);
		panel.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(555, 317, 116, 22);
		panel.add(textField_1);
		
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
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = textField.getText();
				String age = textField_1.getText();
				String activity = chckbxActive.isSelected() ? "active" : "inactive";
				boolean smoker = chckbxSmoker.isSelected() ? true : false;
				boolean pregnant = chckbxPregnant.isSelected() ? true : false;
				Patients.addNewPatient(name, age, activity, smoker, pregnant);
				addPatientToTable(name, age, activity, smoker, pregnant);
			}
		});
		btnNewButton.setBounds(786, 382, 97, 25);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Examination", null, panel_1, null);
		panel_1.setLayout(null);
		
		/* Initialize functions */
		
		loadPatients();
		
		addTableListener();
	}

	private void addTableListener() {
		table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.BUTTON1) {
	            	ArrayList<String> patientData = new ArrayList<String>();
	            	int count = table.getColumnCount();
	            	for (int i = 0; i < count; i++) {
	            		String data = (String) table.getValueAt(table.getSelectedRow(), i).toString();
		                patientData.add(data);
	            	}
	            	setSelectedPatient(patientData);
	            }
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
}
