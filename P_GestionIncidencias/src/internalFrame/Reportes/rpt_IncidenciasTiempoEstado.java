package internalFrame.Reportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Utilitarios.General;
import Controlador.IncidenciaController;
import Entidades.BE_Incidencia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class rpt_IncidenciasTiempoEstado extends JInternalFrame implements ActionListener {

	private static rpt_IncidenciasTiempoEstado instance = null;
	private JLabel lblCantidadDeIncidencias;
	private JTextField txtIncidenciasCanceladas;
	private JLabel lblCantidadDeIncidencias_1;
	private JTextField txtIncidenciasCerradas;
	private JLabel lblIncidenciaQueMs;
	private JScrollPane scrollPane;
	private JButton btnNewButton;

	private IncidenciaController IncidenciaC = new IncidenciaController();
	private JButton btnSalir;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblIncidenciaQueMenos;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private DefaultTableModel model1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				rpt_IncidenciasTiempoEstado frame = new rpt_IncidenciasTiempoEstado();
				frame.setVisible(true);
			}
		});
	}

	public rpt_IncidenciasTiempoEstado() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				rpt_IncidenciasTiempoEstado.class
						.getResource("/imagenesx16/tiempoAtencion_x16.png")));
		setTitle("Reporte de Incidencias por Tiempo y Estado");
		setBounds(10, 10, 818, 307);
		setClosable(true);
		getContentPane().setLayout(null);
		{
			lblCantidadDeIncidencias = new JLabel("Cantidad de Incidencias cancelados:");
			lblCantidadDeIncidencias.setBounds(10, 11, 200, 14);
			getContentPane().add(lblCantidadDeIncidencias);
		}
		{
			txtIncidenciasCanceladas = new JTextField();
			txtIncidenciasCanceladas.setBounds(223, 8, 86, 20);
			getContentPane().add(txtIncidenciasCanceladas);
			txtIncidenciasCanceladas.setColumns(10);
		}
		{
			lblCantidadDeIncidencias_1 = new JLabel("Cantidad de Incidencias cerradas:");
			lblCantidadDeIncidencias_1.setBounds(10, 48, 200, 14);
			getContentPane().add(lblCantidadDeIncidencias_1);
		}
		{
			txtIncidenciasCerradas = new JTextField();
			txtIncidenciasCerradas.setColumns(10);
			txtIncidenciasCerradas.setBounds(223, 45, 86, 20);
			getContentPane().add(txtIncidenciasCerradas);
		}
		{
			lblIncidenciaQueMs = new JLabel("Incidencia que más demoro en atender:");
			lblIncidenciaQueMs.setBounds(10, 85, 200, 14);
			getContentPane().add(lblIncidenciaQueMs);
		}
		{
			btnNewButton = new JButton("Mostrar");
			btnNewButton.addActionListener(this);
			btnNewButton.setBounds(320, 7, 89, 23);
			getContentPane().add(btnNewButton);
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setBounds(708, 247, 89, 23);
			getContentPane().add(btnSalir);
		}
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 787, 50);
		getContentPane().add(scrollPane);
		{
			table = new JTable();
			table.setRowSelectionAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.getTableHeader().setReorderingAllowed(false);
			table.setModel(model = new DefaultTableModel(
					new Object[][] {}, new String[] { "Código",
							"CodigoUsuario", "NombreUsuario",
							"CodigoEspecialista", "NombreEspecialista",
							"CodigoIncidencia", "NombreIncidencia",
							"Descripcion", "Observaciones",
							"TiempoEstimado", "TiempoReal",
							"FechaRegistro", "FechaIncio", "FechaFin",
							"CodigoEstado", "Estado" }));

			table.getColumnModel().getColumn(0).setMinWidth(50);
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMinWidth(0);
			table.getColumnModel().getColumn(1).setMaxWidth(0);
			table.getColumnModel().getColumn(3).setMinWidth(0);
			table.getColumnModel().getColumn(3).setMaxWidth(0);
			table.getColumnModel().getColumn(5).setMinWidth(0);
			table.getColumnModel().getColumn(5).setMaxWidth(0);
			table.getColumnModel().getColumn(7).setMinWidth(100);
			table.getColumnModel().getColumn(7).setMaxWidth(100);
			table.getColumnModel().getColumn(8).setMinWidth(0);
			table.getColumnModel().getColumn(8).setMaxWidth(0);
			table.getColumnModel().getColumn(9).setMinWidth(0);
			table.getColumnModel().getColumn(9).setMaxWidth(0);
			table.getColumnModel().getColumn(10).setMinWidth(0);
			table.getColumnModel().getColumn(10).setMaxWidth(0);
			table.getColumnModel().getColumn(11).setMinWidth(0);
			table.getColumnModel().getColumn(11).setMaxWidth(0);
			table.getColumnModel().getColumn(14).setMinWidth(0);
			table.getColumnModel().getColumn(14).setMaxWidth(0);
			scrollPane.setViewportView(table);
		}
		{
			lblIncidenciaQueMenos = new JLabel("Incidencia que menos demoro en atender:");
			lblIncidenciaQueMenos.setBounds(10, 166, 216, 14);
			getContentPane().add(lblIncidenciaQueMenos);
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 186, 787, 50);
			getContentPane().add(scrollPane_1);
		}
		{
			table_1 = new JTable();
			table_1.setRowSelectionAllowed(false);
			table_1.setBounds(0, 0, 785, 1);
			
			table_1 = new JTable();
			table_1.setRowSelectionAllowed(false);
			table_1.getTableHeader().setResizingAllowed(false);
			table_1.getTableHeader().setReorderingAllowed(false);
			table_1.setModel(model1 = new DefaultTableModel(
					new Object[][] {}, new String[] { "Código",
							"CodigoUsuario", "NombreUsuario",
							"CodigoEspecialista", "NombreEspecialista",
							"CodigoIncidencia", "NombreIncidencia",
							"Descripcion", "Observaciones",
							"TiempoEstimado", "TiempoReal",
							"FechaRegistro", "FechaIncio", "FechaFin",
							"CodigoEstado", "Estado" }));

			table_1.getColumnModel().getColumn(0).setMinWidth(50);
			table_1.getColumnModel().getColumn(0).setMaxWidth(50);
			table_1.getColumnModel().getColumn(1).setMinWidth(0);
			table_1.getColumnModel().getColumn(1).setMaxWidth(0);
			table_1.getColumnModel().getColumn(3).setMinWidth(0);
			table_1.getColumnModel().getColumn(3).setMaxWidth(0);
			table_1.getColumnModel().getColumn(5).setMinWidth(0);
			table_1.getColumnModel().getColumn(5).setMaxWidth(0);
			table_1.getColumnModel().getColumn(7).setMinWidth(100);
			table_1.getColumnModel().getColumn(7).setMaxWidth(100);
			table_1.getColumnModel().getColumn(8).setMinWidth(0);
			table_1.getColumnModel().getColumn(8).setMaxWidth(0);
			table_1.getColumnModel().getColumn(9).setMinWidth(0);
			table_1.getColumnModel().getColumn(9).setMaxWidth(0);
			table_1.getColumnModel().getColumn(10).setMinWidth(0);
			table_1.getColumnModel().getColumn(10).setMaxWidth(0);
			table_1.getColumnModel().getColumn(11).setMinWidth(0);
			table_1.getColumnModel().getColumn(11).setMaxWidth(0);
			table_1.getColumnModel().getColumn(14).setMinWidth(0);
			table_1.getColumnModel().getColumn(14).setMaxWidth(0);
			scrollPane_1.setViewportView(table_1);
		}
	}


	public static rpt_IncidenciasTiempoEstado getInstance() {
		if (instance == null) {
			instance = new rpt_IncidenciasTiempoEstado();
		}
		return instance;
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(arg0);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
		txtIncidenciasCanceladas.setText(""+ IncidenciaC.getCantidadIncidenciaCancelada());
		txtIncidenciasCerradas.setText(""+ IncidenciaC.getCantidadIncidenciaCerrada());
		int posMayor = IncidenciaC.getIncidenciaTarde();
		int posMenor = IncidenciaC.getIncidenciaMenor();
		if(posMayor != -1){
			BE_Incidencia incidenciaMayor = IncidenciaC.getIncidencia(posMayor);
			fillIncidenciaMayor(incidenciaMayor);
		}
		if(posMenor != -1){
			BE_Incidencia incidenciaMenor = IncidenciaC.getIncidencia(posMenor);
			fillIncidenciaMenor(incidenciaMenor);
		}
		
		
//		txtS.setText("" + i);
//		txtS.append("" + menor);
	}
	
	void fillIncidenciaMayor(BE_Incidencia i){
		model.setRowCount(0);
		Object[] data = new Object[]{
				i.getCodigoincidencia(),
				i.getCodigoUsuario(),
				IncidenciaC.getUsuarioxCodigo(i.getCodigoUsuario()),
				i.getCodigoEspecialista(),
				IncidenciaC.getEspecialistaxCodigo(i
						.getCodigoEspecialista()),
				i.getCodigoTipoIncidencia(),
				IncidenciaC.getTipoIncidenciaxCodigo(i
						.getCodigoTipoIncidencia()), i.getDescripcion(),
				i.getObservacion(), i.getTiempoEstimado(),
				i.getTiempoReal(),
				General.parsearDatetoString(i.getFechaRegistro()),
				General.parsearDatetoString(i.getFechaInicio()),
				General.parsearDatetoString(i.getFechaFin()),
				i.getEstado(), i.getNombreEstado()
				
		};
		model.addRow(data);
	}
	
	void fillIncidenciaMenor(BE_Incidencia i){
		model1.setRowCount(0);
		Object[] data = new Object[]{
				i.getCodigoincidencia(),
				i.getCodigoUsuario(),
				IncidenciaC.getUsuarioxCodigo(i.getCodigoUsuario()),
				i.getCodigoEspecialista(),
				IncidenciaC.getEspecialistaxCodigo(i
						.getCodigoEspecialista()),
				i.getCodigoTipoIncidencia(),
				IncidenciaC.getTipoIncidenciaxCodigo(i
						.getCodigoTipoIncidencia()), i.getDescripcion(),
				i.getObservacion(), i.getTiempoEstimado(),
				i.getTiempoReal(),
				General.parsearDatetoString(i.getFechaRegistro()),
				General.parsearDatetoString(i.getFechaInicio()),
				General.parsearDatetoString(i.getFechaFin()),
				i.getEstado(), i.getNombreEstado()
				
		};
		model1.addRow(data);
	}
	protected void do_btnSalir_actionPerformed(ActionEvent arg0) {
		this.dispose();
		instance=null;
	}
}
