package internalFrame.Reportes;

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import reportes.AbstractJasperReports;
import Controlador.AreaController;
import Controlador.IncidenciaController;
import Controlador.TipoIncidenciaController;
import Entidades.BE_Area;
import Entidades.BE_Incidencia;
import Entidades.BE_TipoIncidencia;
import Utilitarios.General;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

public class rpt_IncidenciasXArea extends JInternalFrame implements
		ActionListener {

	private JLabel lblRango;
	private JButton btnReporte;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBox cboArea;
	private DefaultTableModel model;
	private static rpt_IncidenciasXArea instance = null;
	private JButton btnSalir;
	private IncidenciaController IncidenciaC = new IncidenciaController();
	private AreaController AreaC = new AreaController();
	private JButton btnA;
	private JButton btnVistaPreliminar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rpt_IncidenciasXArea frame = new rpt_IncidenciasXArea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public rpt_IncidenciasXArea() {
		setFrameIcon(new ImageIcon(
				rpt_IncidenciasXArea.class
						.getResource("/imagenesx16/area1_x16.png")));
		setTitle("Reporte de Incidencias por Área");
		setBounds(10, 10, 1287, 686);
		setClosable(true);
		getContentPane().setLayout(null);

		{
			lblRango = new JLabel("\u00C1rea: ");
			lblRango.setBounds(10, 11, 36, 24);
			getContentPane().add(lblRango);

			btnReporte = new JButton("Mostrar");
			btnReporte.addActionListener(this);
			btnReporte.setBounds(197, 11, 89, 24);
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

			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setBounds(1172, 611, 89, 24);
			getContentPane().add(btnSalir);

		}
		{
			cboArea = new JComboBox();
			if (AreaC.lista() != null)
				cboArea.setModel(new DefaultComboBoxModel(AreaC.lista()));
			cboArea.setBounds(52, 11, 135, 24);
			getContentPane().add(cboArea);

		}

		// btnA = new JButton("a");
		// btnA.addActionListener(this);
		// btnA.setBounds(326, 12, 89, 23);
		// getContentPane().add(btnA);
		{
			btnVistaPreliminar = new JButton("Vista Preliminar");
			btnVistaPreliminar.addActionListener(this);
			btnVistaPreliminar.setBounds(1156, 12, 105, 24);
			getContentPane().add(btnVistaPreliminar);
		}
	}

	public static rpt_IncidenciasXArea getInstance() {
		if (instance == null) {
			instance = new rpt_IncidenciasXArea();
		}
		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVistaPreliminar) {
			do_btnB_actionPerformed(e);
		}
		if (e.getSource() == btnA) {
			// do_btnA_actionPerformed(e);
		}

		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnReporte) {
			do_btnNewButton_actionPerformed(e);
		}
	}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		cargar();
	}

	void cargar() {

		model.setRowCount(0);
		IncidenciaController arrayIncidencia = new IncidenciaController();
		int codigoArea = AreaC.getCodigoAreaxNombre(cboArea.getSelectedItem()
				.toString());

		ArrayList<BE_Incidencia> aux = arrayIncidencia
				.getListaIncidenciaxArea(codigoArea);
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

	// protected void do_btnA_actionPerformed(ActionEvent e) {
	// final Map<String, Object> map = new HashMap<String, Object>();
	// // List<String> listado = new ArrayList<String>();
	// // for(int i = 0; i < table.getRowCount(); i++){
	// // listado.add("C�digo : " + table.getValueAt(i, 0) +
	// // "\nNombre : " + table.getValueAt(i, 1));
	// // }
	// // String inci = "";
	// // for(int i = 0 ; i < listado.size(); i++){
	// // inci = inci + "\n" +listado.get(i);
	// // }
	// //
	// // map.put("pa1", inci);
	//
	//
	// // AbstractJasperReports.createReport(path, map);
	// // AbstractJasperReports.showViewer();
	//
	// //String path = System.getProperty("user.dir") +
	// "\\Reportes\\Flower_Landscape.jasper";
	// //String path =
	// "F:\\Workspace\\P_GestionIncidencias\\src\\reportes\\reporte.jasper";
	// try {
	// FileInputStream inputStream = new
	// FileInputStream("F:/Workspace/P_GestionIncidencias/src/reportes/reporte.jrxml");
	// try {
	// TipoIncidenciaController tipo = new TipoIncidenciaController();
	// ArrayList<BE_TipoIncidencia> aux = tipo.getListaTipoIncidencia();
	// JRBeanCollectionDataSource beanColDataSource = new
	// JRBeanCollectionDataSource(aux);
	// JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
	// Map parameteres = new HashMap();
	// JasperReport jasperReport =
	// JasperCompileManager.compileReport(jasperDesign);
	// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	// parameteres, beanColDataSource);
	// //JasperPrint informe = JasperFillManager.fillReport(path, parameteres,
	// beanColDataSource);
	// JasperViewer.viewReport(jasperPrint, false);
	// } catch (JRException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// } catch (FileNotFoundException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }
	protected void do_btnB_actionPerformed(ActionEvent e) {
		// final Map<String, Object> map = new HashMap<String, Object>();
		// List<String> listado = new ArrayList<String>();
		try {
			String path = System.getProperty("user.dir");
			FileInputStream inputStream = new FileInputStream(
					path + "\\src\\reportes\\rpt_IncidenciaXArea.jrxml");
			try {

				int codigoArea = AreaC.getCodigoAreaxNombre(cboArea
						.getSelectedItem().toString());
				ArrayList<BE_Incidencia> temp = IncidenciaC
						.getListaIncidenciaxArea(codigoArea);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						temp);
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
