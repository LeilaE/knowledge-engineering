package windows;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controls.Patients;
import models.Patient;

public class HomePage {

	private JFrame frmExamination;
	DefaultTableModel tableModel = new DefaultTableModel();
	private JTable table;

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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Examination", null, panel_1, null);
		panel_1.setLayout(null);
		
		/* Initialize functions */
		
		loadPatients();
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
