package internalFrame.Mantenimientos;

import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;


public class Area extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Area frame = new Area();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JComboBox cboTipoDocumento;
	private JLabel lblCodigo;
	private JLabel lblNombre;

	/**
	 * Create the frame.
	 */
	public Area() {
		setBounds(100, 100, 450, 351);
		getContentPane().setLayout(null);
		
	}
}
