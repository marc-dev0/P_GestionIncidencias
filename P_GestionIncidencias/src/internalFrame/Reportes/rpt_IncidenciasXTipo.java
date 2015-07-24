package internalFrame.Reportes;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.omg.CORBA.portable.IndirectionException;

import Utilitarios.General;
import Controlador.IncidenciaController;
import Controlador.TipoIncidenciaController;
import Entidades.BE_Incidencia;
import Entidades.BE_TipoIncidencia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class rpt_IncidenciasXTipo extends JInternalFrame implements
		ActionListener {
	TipoIncidenciaController TipoIncidenciaC = new TipoIncidenciaController();
	IncidenciaController IncidenciaC = new IncidenciaController();
	private static rpt_IncidenciasXTipo instance = null;
	private JLabel lblSeleccioneElTipo;
	private JComboBox cboTipoIncidencia;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnSalir;
	private JButton btnVistaPreliminar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				rpt_IncidenciasXTipo frame = new rpt_IncidenciasXTipo();
				frame.setVisible(true);
			}
		});
	}

	public rpt_IncidenciasXTipo() {
		setFrameIcon(new ImageIcon(
				rpt_IncidenciasXTipo.class
						.getResource("/imagenesx16/incidencia_16.png")));
		setTitle("Reporte de Incidencias por Tipo");
		setClosable(true);
		setBounds(10, 10, 1287, 686);
		getContentPane().setLayout(null);

		lblSeleccioneElTipo = new JLabel("Seleccione el tipo:");
		lblSeleccioneElTipo.setBounds(10, 11, 89, 24);
		getContentPane().add(lblSeleccioneElTipo);

		cboTipoIncidencia = new JComboBox();
		cboTipoIncidencia.addActionListener(this);
		cboTipoIncidencia.setModel(new DefaultComboBoxModel(TipoIncidenciaC.lista()));
		cboTipoIncidencia.setBounds(100, 11, 200, 24);
		getContentPane().add(cboTipoIncidencia);

		btnNewButton = new JButton("Reporte");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(310, 11, 89, 24);
		getContentPane().add(btnNewButton);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 48, 1251, 549);
			getContentPane().add(scrollPane);

			table = new JTable();
			table.setRowSelectionAllowed(true);
			table.setAutoCreateRowSorter(true);

			table.getTableHeader().setResizingAllowed(false);
			table.getTableHeader().setReorderingAllowed(false);
			table.setModel(model = new DefaultTableModel(new Object[][] {},
					new String[] { "C.Incidencia", "NombreUsuario",
							"NombreEspecialista", "TipoIncidencia",
							"Descripción", "Observación", "T.Estimado",
							"T.Real", "FechaRegistro", "InicioAtención",
							"FinAtención", "Estado" }));
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

		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setBounds(1172, 611, 89, 23);
			getContentPane().add(btnSalir);
		}
		{
			btnVistaPreliminar = new JButton("Vista Preliminar");
			btnVistaPreliminar.addActionListener(this);
			btnVistaPreliminar.setBounds(1156, 12, 105, 24);
			getContentPane().add(btnVistaPreliminar);	
		}
	}

	public static rpt_IncidenciasXTipo getInstance() {
		if (instance == null)
			instance = new rpt_IncidenciasXTipo();
		return instance;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == cboTipoIncidencia) {
			do_cboTipoIncidencia_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnVistaPreliminar)
			do_btnVistaPreliminar_actionPerformed(arg0);
	}

	private void do_btnVistaPreliminar_actionPerformed(ActionEvent arg0) {
		try {
			
			String path = System.getProperty("user.dir");
			FileInputStream inputStream = new FileInputStream(path + "\\src\\reportes\\rpt_IncidenciasXTipo.jrxml");
			try {

				int codigoTIncidencia = TipoIncidenciaC.getCodigoTIncidenciaxDescripcion(
															cboTipoIncidencia.getSelectedItem().toString());
				ArrayList<BE_Incidencia> temp = IncidenciaC.getListaIncidenciaXTIncidencia(codigoTIncidencia);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(temp);
				JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
				Map parameteres = new HashMap();
				JasperReport jasperReport = JasperCompileManager
						.compileReport(jasperDesign);
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parameteres, beanColDataSource);
				// JasperPrint informe = JasperFillManager.fillReport(path,
				// parameteres, beanColDataSource);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void do_cboTipoIncidencia_actionPerformed(ActionEvent arg0) {

	}

	void cargar(int codigoTipoIncidencia) {
		IncidenciaController arrayIncidencia = new IncidenciaController();
		ArrayList<BE_Incidencia> aux = arrayIncidencia
				.getIncidenciasxTipo(codigoTipoIncidencia);
		// ArrayList<BE_Incidencia> aux =
		// IncidenciaC.getIncidenciasxTipo(codigoTipoIncidencia);
		model.setRowCount(0);
		for (int i = 0; i < aux.size(); i++) {
			BE_Incidencia in = aux.get(i);
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

	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
		int codigoTipoIncidencia = cboTipoIncidencia.getSelectedIndex();
		cargar(codigoTipoIncidencia);
	}

	protected void do_btnSalir_actionPerformed(ActionEvent arg0) {
		this.dispose();
	}
}
