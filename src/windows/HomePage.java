package windows;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class HomePage {

	private JFrame frmExamination;

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
		frmExamination.setBounds(100, 100, 773, 483);
		frmExamination.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamination.getContentPane().setLayout(null);
		
		JIPEngine engine = new JIPEngine();
		engine.consultFile("ruleBased/prolog.pl");
		
		JIPQuery query = engine.openSynchronousQuery("person(X)");
		ArrayList<String> niz= new ArrayList<String>();
		JIPTerm solution ;
		
		while ( (solution = query.nextSolution()) != null  ) {
			//System.out.println("solution: " + solution);
		
			for (JIPVariable var: solution.getVariables()) {

				niz.add(var.getValue().toString());
				
	}

}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(108, 130, 127, 34);
		frmExamination.getContentPane().add(comboBox);
		
		for( String ime: niz) {
		comboBox.addItem(ime);
		}
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPatient.setBounds(30, 115, 105, 57);
		frmExamination.getContentPane().add(lblPatient);
	}
}
