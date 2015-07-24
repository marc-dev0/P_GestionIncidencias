package internalFrame.Reportes;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.IncidenciaController;
import Entidades.BE_Incidencia;
import Utilitarios.General;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class rpt_IncidenciasXRangoFecha extends JInternalFrame implements
		ActionListener {
	private JLabel lblRango;
	private JButton btnReporte;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JDateChooser dcFechaInicio;
	private static rpt_IncidenciasXRangoFecha instance = null;
	private JLabel lblA;
	private JDateChooser dcFechaFin;
	private JButton btnSalir;
	private IncidenciaController IncidenciaC = new IncidenciaController();
	private JButton btnVistaPreliminar;
	IncidenciaController arrayIncidencia = new IncidenciaController();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				rpt_IncidenciasXRangoFecha frame = new rpt_IncidenciasXRangoFecha();
				frame.setVisible(true);

			}
		});
	}

	public rpt_IncidenciasXRangoFecha() {
		setFrameIcon(new ImageIcon(
				rpt_IncidenciasXRangoFecha.class
						.getResource("/imagenesx16/fechas_x16.png")));
		setTitle("Reporte de Incidencias por Rango de fecha");
		setClosable(true);
		setBounds(10, 10, 1287, 686);
		getContentPane().setLayout(null);

		lblRango = new JLabel("Rango:");
		lblRango.setBounds(10, 11, 41, 24);
		getContentPane().add(lblRango);

		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(this);
		btnReporte.setBounds(266, 11, 89, 24);
		getContentPane().add(btnReporte);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 48, 1251, 549);
			getContentPane().add(scrollPane);

			table = new JTable() {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};
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
			dcFechaInicio = new JDateChooser(new Date());
			getContentPane().add(dcFechaInicio);
			dcFechaInicio.setBounds(49, 11, 99, 24);
		}
		{
			lblA = new JLabel("a");
			lblA.setBounds(153, 11, 12, 24);
			getContentPane().add(lblA);
		}
		{
			dcFechaFin = new JDateChooser(new Date());
			dcFechaFin.setBounds(164, 11, 99, 24);
			getContentPane().add(dcFechaFin);
		}
		{
			btnVistaPreliminar = new JButton("Vista Preliminar");
			btnVistaPreliminar.addActionListener(this);
			btnVistaPreliminar.setBounds(1156, 12, 105, 24);
			getContentPane().add(btnVistaPreliminar);	
		}

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(1172, 611, 89, 23);
		getContentPane().add(btnSalir);

	}

	public static rpt_IncidenciasXRangoFecha getInstance() {
		if (instance == null) {
			instance = new rpt_IncidenciasXRangoFecha();
		}
		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnReporte) {
			do_btnNewButton_actionPerformed(e);
		}
		if (e.getSource() == btnVistaPreliminar)
			do_btnVistaPreliminar(e);
	}

	private void do_btnVistaPreliminar(ActionEvent e) {
		try {
			
			String path = System.getProperty("user.dir");
			
			FileInputStream inputStream = new FileInputStream(path + "\\src\\reportes\\rpt_IncidenciasXRangoFecha.jrxml");
			
			try {

				String fTempInicio = General.parsearDatetoString(dcFechaInicio
						.getDate()), fTempFin = General.parsearDatetoString(dcFechaFin
						.getDate());

				Date fechaInicio = General.parseStringtoDate(fTempInicio), fechaFin = General
						.parseStringtoDate(fTempFin);
			
				ArrayList<BE_Incidencia> aux = arrayIncidencia.getIncidenciasxRangoFecha(fechaInicio, fechaFin);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(aux);
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

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		cargar();
	}

	void cargar() {
		
		String fTempInicio = General.parsearDatetoString(dcFechaInicio
				.getDate()), fTempFin = General.parsearDatetoString(dcFechaFin
				.getDate());

		Date fechaInicio = General.parseStringtoDate(fTempInicio), fechaFin = General
				.parseStringtoDate(fTempFin);

		ArrayList<BE_Incidencia> aux = arrayIncidencia
				.getIncidenciasxRangoFecha(fechaInicio, fechaFin);
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

	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
