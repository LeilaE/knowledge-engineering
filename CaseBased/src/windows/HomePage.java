package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HomePage {

	private JFrame frame;
	
	//Tabbed panels
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

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

		frame.getContentPane().add(tabbedPane);
		
		initPanel();
		initPanel1();
		initPanel2();
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		
	}
	
	private void initPanel1() {
		// TODO Auto-generated method stub
		
	}
	
	private void initPanel2() {
		// TODO Auto-generated method stub
		
	}

}
