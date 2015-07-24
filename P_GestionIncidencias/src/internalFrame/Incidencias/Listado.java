package internalFrame.Incidencias;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Utilitarios.General;
import Controlador.IncidenciaController;
import Entidades.BE_Incidencia;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Listado extends JInternalFrame implements ActionListener,
		MouseListener {

	private IncidenciaController IncidenciaC = new IncidenciaController();
	private static Listado instance = null;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listado frame = new Listado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Listado() {
		setFrameIcon(new ImageIcon(
				Listado.class.getResource("/imagenesx16/Check_List_16.png")));
		setClosable(true);
		setBounds(10, 10, 1287, 686);
		setTitle("Lista de Incidencias Registradas");
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 1251, 549);
		getContentPane().add(scrollPane);

		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(this);

		table.setRowSelectionAllowed(true);
		table.setAutoCreateRowSorter(true);

		table.getTableHeader().setResizingAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(model = new DefaultTableModel(new Object[][] {},
				new String[] { "CodigoIncidencia", "NombreUsuario",
						"NombreEspecialista", "TipoIncidencia", "Descripción",
						"Observación", "TiempoEstimado", "TiempoReal",
						"FechaRegistro", "InicioAtención", "FinAtención",
						"Estado" }));
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(0).setMaxWidth(70);
		table.getColumnModel().getColumn(6).setMinWidth(70);
		table.getColumnModel().getColumn(6).setMaxWidth(70);
		table.getColumnModel().getColumn(7).setMinWidth(70);
		table.getColumnModel().getColumn(7).setMaxWidth(70);
		table.getColumnModel().getColumn(8).setMinWidth(80);
		table.getColumnModel().getColumn(8).setMaxWidth(80);
		table.getColumnModel().getColumn(9).setMinWidth(80);
		table.getColumnModel().getColumn(9).setMaxWidth(80);
		table.getColumnModel().getColumn(10).setMinWidth(80);
		table.getColumnModel().getColumn(10).setMaxWidth(80);
		table.getColumnModel().getColumn(11).setMinWidth(100);
		table.getColumnModel().getColumn(11).setMaxWidth(100);
		scrollPane.setViewportView(table);
		{
			cargar();
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setBounds(1172, 611, 89, 24);
			getContentPane().add(btnSalir);
		}
	}

	public static Listado getInstance() {
		if (instance == null) {
			instance = new Listado();

		}
		return instance;
	}

	private void cargar() {
		// for (int i = 0; i < IncidenciaC.getSize(); i++){
		// BE_Incidencia in = IncidenciaC.getIncidencia(i);
		// Object[] data = new Object[]{
		// in.getCodigoincidencia(),
		// IncidenciaC.getUsuarioxCodigo(in.getCodigoUsuario()),
		// IncidenciaC.getEspecialistaxCodigo(in.getCodigoEspecialista()),
		// IncidenciaC.getTipoIncidenciaxCodigo(in.getCodigoTipoIncidencia()),
		// in.getDescripcion(), in.getObservacion(), in.getTiempoEstimado(),
		// in.getTiempoReal(),
		// General.parsearDatetoString(in.getFechaRegistro()),
		// General.parsearDatetoString(in.getFechaInicio()),
		// General.parsearDatetoString(in.getFechaFin()), in.getNombreEstado()
		// };
		//
		// model.addRow(data);
		// }
		ArrayList<BE_Incidencia> aux = IncidenciaC
				.getListaIncidenciaRegistradas();
		for (BE_Incidencia in : aux) {
			Object[] data = new Object[] {
					in.getCodigoincidencia(),
					IncidenciaC.getUsuarioxCodigo(in.getCodigoUsuario()),
					IncidenciaC.getEspecialistaxCodigo(in
							.getCodigoEspecialista()),
					IncidenciaC.getTipoIncidenciaxCodigo(in
							.getCodigoTipoIncidencia()), in.getDescripcion(),
					in.getObservacion(), in.getTiempoEstimado(),
					in.getTiempoReal(),
					General.parsearDatetoString(in.getFechaRegistro()),
					General.parsearDatetoString(in.getFechaInicio()),
					General.parsearDatetoString(in.getFechaFin()),
					in.getNombreEstado() };
			model.addRow(data);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
	}

	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		instance = null;
		this.dispose();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			do_table_mouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void do_table_mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2)
			JOptionPane.showConfirmDialog(null, "HOLA");
	}
}
