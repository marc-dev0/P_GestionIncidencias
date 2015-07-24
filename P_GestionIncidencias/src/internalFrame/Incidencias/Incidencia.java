package internalFrame.Incidencias;

import internalFrame.Mantenimientos.Usuario;

import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

import Controlador.EspecialistaController;
import Controlador.IncidenciaController;
import Controlador.TipoIncidenciaController;
import Controlador.UsuarioController;
import Entidades.BE_Especialista;
import Entidades.BE_Incidencia;
import Entidades.BE_Usuario;
import Frames.Menu_Principal;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

import Utilitarios.*;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jfree.chart.renderer.category.LineRenderer3D;

import java.awt.Font;

public class Incidencia extends JInternalFrame implements ActionListener {

	private static Incidencia instance = null;
	private String estado = "";
	private String tempDescri = "", tempObs = "";
	// Region Controladores
	private UsuarioController UsuarioC = new UsuarioController();
	private EspecialistaController EspecialistaC = new EspecialistaController();
	private TipoIncidenciaController TipoIncidenciaC = new TipoIncidenciaController();
	private IncidenciaController IncidenciaC = new IncidenciaController();
	// EndRegion

	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblCodigo;
	private JLabel lblMUsuario;
	public static JComboBox cboMUsuario;
	private JLabel lblMEspecialista;
	private JComboBox cboMEspecialista;
	private JLabel lblMTipoIncidencia;
	private JComboBox cboMTipoIncidencia;
	private JScrollPane spDescripcion;
	private JLabel lblDescripcin;
	private JTextArea txtDescripcion;
	private JScrollPane spObservacion;
	private JLabel lblObservacion;
	private JTextArea txtObservacion;
	private JLabel lblTiempoEstimado;
	private JTextField txtEstimadoSolucion;
	private JLabel lblmin;
	private JLabel lblInicioAtencion;
	private JDateChooser dcInicioAtencion;
	private JDateChooser dcFinAtencion;
	private JLabel lblFinAtencin;
	private JDateChooser dcFechaRegistro;
	private JLabel lblFechaRegistro;
	private JLabel lblEstado;
	private JComboBox cboEstado;
	private JMenuBar menuBar;
	private JMenuItem mniNuevo;
	private JMenuItem mniEditar;
	private JMenuItem mniAtras;
	private JMenuItem mniGuardar;
	private JMenuItem mniLimpiar;
	private JMenuItem mniSalir;
	private DefaultTableModel model;
	private JButton btnBuscarUsuario;
	public int hola;
	private JLabel txtCodigo;

	// EndRegion
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incidencia frame = new Incidencia();
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
	public Incidencia() {
		setFrameIcon(new ImageIcon(
				Incidencia.class
						.getResource("/imagenesx16/Search_for_Incidents_16.png")));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("INCIDENCIAS");
		setClosable(true);
		setBounds(100, 100, 866, 653);
		getContentPane().setLayout(null);
		{
			/*
			 * { if(UsuarioC.lista() != null){ cboUsuario.setModel(new
			 * DefaultComboBoxModel(UsuarioC.lista())); } } {
			 * if(TipoIncidenciaC.lista() != null)
			 * cboTipoIncidencia.setModel(new
			 * DefaultComboBoxModel(TipoIncidenciaC.lista())); } {
			 * if(EspecialistaC.lista() != null) cboEspecialista.setModel(new
			 * DefaultComboBoxModel(EspecialistaC.lista())); }
			 */
		}
		JPanel panelTabs = new JPanel();
		panelTabs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelTabs.setBounds(6, 11, 840, 606);
		getContentPane().add(panelTabs);
		panelTabs.setLayout(null);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(12, 47, 816, 558);
			panelTabs.add(tabbedPane);
		}
		{
			panelListado = new JPanel();
			tabbedPane.addTab("Listado", null, panelListado, null);
			panelListado.setLayout(null);
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 12, 787, 510);
				panelListado.add(scrollPane);
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
			}

		}
		panelMantenimiento = new JPanel();
		tabbedPane.addTab("Mantenimiento", null, panelMantenimiento, null);
		tabbedPane.setEnabledAt(1, false);
		panelMantenimiento.setLayout(null);
		{
			lblCodigo = new JLabel("Código: ");
			lblCodigo.setBounds(12, 12, 70, 24);
			panelMantenimiento.add(lblCodigo);
		}
		{
			lblMUsuario = new JLabel("Usuario:");
			lblMUsuario.setBounds(12, 52, 70, 24);
			panelMantenimiento.add(lblMUsuario);
		}
		cboMUsuario = new JComboBox();
		if (UsuarioC.lista() != null)
			cboMUsuario.setModel(new DefaultComboBoxModel(UsuarioC.lista()));
		cboMUsuario.setBounds(90, 52, 165, 24);
		panelMantenimiento.add(cboMUsuario);
		{
			lblMEspecialista = new JLabel("Especialista:");
			lblMEspecialista.setBounds(12, 92, 102, 24);
			panelMantenimiento.add(lblMEspecialista);
		}
		cboMEspecialista = new JComboBox();
		if (EspecialistaC.lista() != null)
			cboMEspecialista.setModel(new DefaultComboBoxModel(EspecialistaC
					.lista()));
		cboMEspecialista.setBounds(121, 92, 179, 24);
		panelMantenimiento.add(cboMEspecialista);
		{
			lblMTipoIncidencia = new JLabel("Tipo Incidencia:");
			lblMTipoIncidencia.setBounds(12, 132, 118, 24);
			panelMantenimiento.add(lblMTipoIncidencia);
		}
		cboMTipoIncidencia = new JComboBox();
		if (TipoIncidenciaC.lista() != null)
			cboMTipoIncidencia.setModel(new DefaultComboBoxModel(
					TipoIncidenciaC.lista()));
		cboMTipoIncidencia.setBounds(131, 132, 169, 24);
		panelMantenimiento.add(cboMTipoIncidencia);
		{
			spDescripcion = new JScrollPane();
			spDescripcion.setBounds(12, 202, 390, 205);
			panelMantenimiento.add(spDescripcion);
			{
				txtDescripcion = new JTextArea();
				spDescripcion.setViewportView(txtDescripcion);
			}
		}
		{
			lblDescripcin = new JLabel("Descripción:");
			lblDescripcin.setBounds(12, 172, 118, 24);
			panelMantenimiento.add(lblDescripcin);
		}
		{
			spObservacion = new JScrollPane();
			spObservacion.setBounds(414, 202, 390, 205);
			panelMantenimiento.add(spObservacion);
			{
				txtObservacion = new JTextArea();
				spObservacion.setViewportView(txtObservacion);
			}
		}
		{
			lblObservacion = new JLabel("Observación:");
			lblObservacion.setBounds(414, 172, 118, 24);
			panelMantenimiento.add(lblObservacion);
		}
		{
			lblTiempoEstimado = new JLabel("Tiempo Estimado Solución:");
			lblTiempoEstimado.setBounds(12, 419, 200, 24);
			panelMantenimiento.add(lblTiempoEstimado);
		}
		{
			txtEstimadoSolucion = new JTextField();
			txtEstimadoSolucion.setColumns(10);
			txtEstimadoSolucion.setBounds(215, 420, 40, 24);
			panelMantenimiento.add(txtEstimadoSolucion);
		}
		{
			lblmin = new JLabel("m.");
			lblmin.setBounds(260, 424, 40, 15);
			panelMantenimiento.add(lblmin);
		}
		{
			lblInicioAtencion = new JLabel("Inicio de Atención:");
			lblInicioAtencion.setBounds(12, 459, 139, 24);
			panelMantenimiento.add(lblInicioAtencion);
		}
		{
			dcInicioAtencion = new JDateChooser();
			dcInicioAtencion.setBounds(150, 459, 130, 24);
			panelMantenimiento.add(dcInicioAtencion);
		}
		{
			dcFinAtencion = new JDateChooser();
			dcFinAtencion.setBounds(510, 459, 130, 24);
			panelMantenimiento.add(dcFinAtencion);
		}
		{
			lblFinAtencin = new JLabel("Fin Atención:");
			lblFinAtencin.setBounds(414, 459, 102, 24);
			panelMantenimiento.add(lblFinAtencin);
		}
		{
			dcFechaRegistro = new JDateChooser();
			dcFechaRegistro.setBounds(537, 12, 130, 24);
			panelMantenimiento.add(dcFechaRegistro);
		}
		{
			lblFechaRegistro = new JLabel("Fecha Registro:");
			lblFechaRegistro.setBounds(414, 12, 118, 24);
			panelMantenimiento.add(lblFechaRegistro);
		}
		{
			lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(12, 499, 59, 24);
			panelMantenimiento.add(lblEstado);
		}
		{
			cboEstado = new JComboBox();
			cboEstado.setModel(new DefaultComboBoxModel(new String[] {
					"Registrada", "Iniciada", "Cancelada", "Cerrada" }));
			cboEstado.setBounds(78, 499, 177, 24);
			panelMantenimiento.add(cboEstado);
		}
		{
			btnBuscarUsuario = new JButton("...");
			btnBuscarUsuario.addActionListener(this);
			btnBuscarUsuario.setBounds(267, 52, 30, 25);
			panelMantenimiento.add(btnBuscarUsuario);
		}
		{
			txtCodigo = new JLabel("");
			txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtCodigo.setBounds(90, 12, 46, 24);
			txtCodigo.setBorder(LineBorder.createGrayLineBorder());
			panelMantenimiento.add(txtCodigo);
		}

		{
			menuBar = new JMenuBar();
			menuBar.setBorder(BorderFactory.createTitledBorder(""));
			menuBar.setBounds(12, 11, 145, 33);
			panelTabs.add(menuBar);

			{
				mniNuevo = new JMenuItem("");
				mniNuevo.addActionListener(this);
				mniNuevo.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/nuevo_x16.png")));

				mniNuevo.setToolTipText("Nueva Incidencia");
				menuBar.add(mniNuevo);
			}
			{
				mniEditar = new JMenuItem("");
				mniEditar.addActionListener(this);
				mniEditar.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/edit_x16.png")));
				mniEditar.setToolTipText("Editar Incidencia");
				menuBar.add(mniEditar);
			}
			{
				mniGuardar = new JMenuItem("");
				mniGuardar.addActionListener(this);
				mniGuardar.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/guardar_x16.png")));
				mniGuardar.setToolTipText("Guardar");
				mniGuardar.setEnabled(false);
				menuBar.add(mniGuardar);
			}
			{
				mniLimpiar = new JMenuItem("");
				mniLimpiar.addActionListener(this);
				mniLimpiar.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/limpiar_x16.png")));
				mniLimpiar.setToolTipText("Limpiar");
				mniLimpiar.setEnabled(false);
				menuBar.add(mniLimpiar);
			}
			{
				mniAtras = new JMenuItem("");
				mniAtras.addActionListener(this);
				mniAtras.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/atras_x16.png")));
				mniAtras.setToolTipText("Volver");
				mniAtras.setEnabled(false);
				menuBar.add(mniAtras);
			}
			{
				mniSalir = new JMenuItem("");
				mniSalir.addActionListener(this);
				mniSalir.setIcon(new ImageIcon(Incidencia.class
						.getResource("/imagenesx16/exit_x16.png")));
				mniSalir.setToolTipText("Salir");
				menuBar.add(mniSalir);
			}

		}
		filLJTable();
	}

	public static Incidencia getInstance() {
		if (instance == null) {
			instance = new Incidencia();
		}
		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mniLimpiar) {
			do_mniLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscarUsuario) {
			do_btnBuscarUsuario_actionPerformed(e);
		}
		if (e.getSource() == mniGuardar) {
			do_mniGuardar_actionPerformed(e);
		}
		if (e.getSource() == mniEditar) {
			do_mniEditar_actionPerformed(e);
		}
		if (e.getSource() == mniSalir) {
			do_mniSalir_actionPerformed(e);
		}
		if (e.getSource() == mniAtras) {
			do_mniAtras_actionPerformed(e);
		}
		if (e.getSource() == mniNuevo) {
			do_mniNuevo_actionPerformed(e);
		}
	}

	protected void do_mniNuevo_actionPerformed(ActionEvent e) {
		if (tabbedPane.getSelectedIndex() == 0) {
			dcFechaRegistro.setEnabled(true);
			estado = "Guardar";
			habilitarMenuItems(true);
			General.changePanel(tabbedPane, true);
			JDateChooser[] jdc = { dcFechaRegistro, dcFinAtencion,
					dcInicioAtencion };
			setFechaToControls(jdc);
			txtCodigo.setText("" + IncidenciaC.generarCodigo());
		}
	}

	protected void do_mniAtras_actionPerformed(ActionEvent e) {
		habilitarMenuItems(true);

		General.changePanel(tabbedPane, false);

		General.limpiar(panelMantenimiento);
	}

	protected void do_mniEditar_actionPerformed(ActionEvent e) {
		if (General.validateJTableisEmpty(table.getRowCount(),
				table.getSelectedRow())) {
			return;
		}
		estado = "Editar";

		dcFechaRegistro.setEnabled(false);

		habilitarMenuItems(true);

		General.changePanel(tabbedPane, true);

		carryDataToControls();

		JDateChooser[] jdc = { dcFechaRegistro, dcFinAtencion, dcInicioAtencion };
		// setFechaToControls(jdc);
	}

	protected void do_mniSalir_actionPerformed(ActionEvent e) {
		instance = null;
		this.dispose();

	}

	protected void do_mniGuardar_actionPerformed(ActionEvent e) {

		String today = General.parsearDatetoString(dcInicioAtencion.getDate()), fin = General
				.parsearDatetoString(dcFinAtencion.getDate());

		Date todayDate = General.parseStringtoDate(today), finDate = General
				.parseStringtoDate(fin);

		if (todayDate.after(finDate)) {
			JOptionPane.showMessageDialog(null,
					"El inicio de la incidencia no puede ser mayor que la "
							+ "finalización");
			return;

		} else {
			if (verificaFecha())
				return;
			else {
				if (estado.equals("Guardar")) {
					BE_Incidencia i = new BE_Incidencia();
					setDataIncidencia(i);
					if (i.getTiempoEstimado() == 0)
						return;
					if (i.getDescripcion().isEmpty())
						return;
					IncidenciaC.agregarIncidencia(i);
					IncidenciaC.guardarIncidencia(i);
					addRowInserted();
				} else if (estado.equals("Editar")) {
					int codigo = leerCodigo();
					BE_Incidencia i = IncidenciaC.getIncidenciaxCodigo(codigo);
					int index = IncidenciaC.getIndex(i);
					setDataIncidencia(i);
					IncidenciaC.modificarIncidencia(index, i);
					IncidenciaC.guardarIncidencia();
					editRowSelected(i);
				}
				estado = "";
				habilitarMenuItems(true);
				General.changePanel(tabbedPane, false);
				General.limpiar(panelMantenimiento);
			}
		}
	}

	private boolean verificaFecha() {
		String fechaI = General.parsearDatetoString(dcInicioAtencion.getDate()), fechaF = General
				.parsearDatetoString(dcFinAtencion.getDate());

		Date fechaInicioChooser = General.parseStringtoDate(fechaI), fechaFinChooser = General
				.parseStringtoDate(fechaF);

		if (estado.equals("Editar")) {
			Date fechaInicioTable = General.parseStringtoDate(table.getValueAt(
					table.getSelectedRow(), 12).toString()), fechaFinTable = General
					.parseStringtoDate(table.getValueAt(table.getSelectedRow(),
							13).toString());
			if (fechaInicioChooser.equals(fechaInicioTable)
					&& fechaFinChooser.equals(fechaFinTable))
				return false;

		} else {
			int codigo = cboMEspecialista.getSelectedIndex();
			for (BE_Incidencia in : IncidenciaC.getListaIncidencia()) {
				Date fechainicio = in.getFechaInicio(), fechaFin = in
						.getFechaFin();

				if (in.getCodigoEspecialista() == codigo) {
					if ((fechaInicioChooser.after(fechainicio) || fechaInicioChooser.equals(fechainicio))
							&& (fechaInicioChooser.before(fechaFin) || fechaInicioChooser.equals(fechaFin))) {
						JOptionPane.showMessageDialog(null, "Lo sentimos, el Especialista seleccionado no se "
														 + "encuentra disponible para esas fechas");
						return true;
					}
				}
			}
		}

		return false;

	}

	// Region JTable

	private void editRowSelected(BE_Incidencia i) {
		int row = table.getSelectedRow();
		model.setValueAt(i.getCodigoincidencia(), row, 0);
		model.setValueAt(i.getCodigoUsuario(), row, 1);
		// model.setValueAt(i.getNombreUsuario(), row, 2);
		model.setValueAt(i.getCodigoEspecialista(), row, 3);
		// model.setValueAt(i.getNombreEspecialista(), row, 4);
		model.setValueAt(i.getCodigoTipoIncidencia(), row, 5);
		// model.setValueAt(i.getNombreTipoIncidencia(), row, 6);
		model.setValueAt(i.getDescripcion(), row, 7);
		model.setValueAt(i.getObservacion(), row, 8);
		model.setValueAt(i.getTiempoEstimado(), row, 9);
		model.setValueAt(i.getTiempoReal(), row, 10);
		model.setValueAt(General.parsearDatetoString(i.getFechaRegistro()),
				row, 11);
		model.setValueAt(General.parsearDatetoString(i.getFechaInicio()), row,
				12);
		model.setValueAt(General.parsearDatetoString(i.getFechaFin()), row, 13);
		model.setValueAt(i.getEstado(), row, 14);
		model.setValueAt(cboEstado.getSelectedItem(), row, 15);

	}

	private void filLJTable() {
		model.setRowCount(0);
		ArrayList<BE_Incidencia> aux = IncidenciaC.getListaIncidencia();

		for (int j = 0; j < aux.size(); j++) {
			BE_Incidencia i = aux.get(j);
			Object[] data = new Object[] {
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
					i.getEstado(), i.getNombreEstado() };
			model.addRow(data);
		}
	}

	private void addRowInserted() {
		Object[] data = new Object[] { leerCodigo(), leerCodigoUsuario(),
				leerNombreUsuario(), leerCodigoEspecialista(),
				leerNombreEspecialista(), leerCodigoTipoIncidencia(),
				leerNombreTipoIncidencia(), leerDescripcion(),
				leerObservacion(), leerTiempoEstimado(), leerTiempoReal(),
				General.parsearDatetoString(leerFechaRegistro()),
				General.parsearDatetoString(leerFechaInicio()),
				General.parsearDatetoString(leerFechaFin()), leerEstado(),
				leerNombreEstado() };
		model.addRow(data);
	}

	private void carryDataToControls() {
		int row = table.getSelectedRow();
		txtCodigo.setText(table.getValueAt(row, 0).toString());
		cboMUsuario.setSelectedIndex((int) table.getValueAt(row, 1));
		cboMEspecialista.setSelectedIndex((int) table.getValueAt(row, 3));
		cboMTipoIncidencia.setSelectedIndex((int) table.getValueAt(row, 5));
		tempDescri = table.getValueAt(row, 7).toString();
		txtDescripcion.setText(table.getValueAt(row, 7).toString());
		tempObs = table.getValueAt(row, 8).toString();
		txtObservacion.setText(table.getValueAt(row, 8).toString());
		txtEstimadoSolucion.setText(table.getValueAt(row, 9).toString());
		// txtRealSolucion.setText(table.getValueAt(row, 10).toString());
		dcFechaRegistro.setDate(General.parseStringtoDate(table.getValueAt(row,
				11).toString()));
		dcInicioAtencion.setDate(General.parseStringtoDate(table.getValueAt(
				row, 12).toString()));
		dcFinAtencion.setDate(General.parseStringtoDate(table.getValueAt(row,
				13).toString()));
		int estado = (int) table.getValueAt(row, 14);
		cboEstado.setSelectedIndex(estado);
	}

	// EndRegion

	// Region Lectura de controles
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	private int leerCodigoUsuario() {
		return cboMUsuario.getSelectedIndex();
	}

	private int leerCodigoEspecialista() {
		return cboMEspecialista.getSelectedIndex();
	}

	private int leerCodigoTipoIncidencia() {
		return cboMTipoIncidencia.getSelectedIndex();
	}

	private String leerDescripcion() {
		if (General.isEmpty(txtDescripcion.getText().trim())) {
			JOptionPane
					.showMessageDialog(
							null,
							"La Descripción de la incidencia no puede estar vacóa. Usted debe ingresar una descripción del caso");
			return "";
		} else {
			String texto = txtDescripcion.getText(), descripcion = "";
			String[] tokens = texto.split("\n");
			for (String s : tokens) {
				if (texto.equals(tempDescri)) {
					descripcion += s;
				} else
					descripcion += s + " ";
			}
			return descripcion;
		}

	}

	private String leerObservacion() {
		String texto = txtObservacion.getText(), observacion = "";
		String[] tokens = texto.split("\n");
		for (String s : tokens) {
			if (texto.equals(tempObs))
				observacion += s;
			else
				observacion += s + " ";
		}
		return observacion;
	}

	private int leerTiempoEstimado() {
		if (!General.isNumeric(txtEstimadoSolucion.getText())
				|| General.isEmpty(txtEstimadoSolucion.getText())) {
			JOptionPane.showMessageDialog(null,
							"El tiempo estimado solución tiene caractáres inválidos. Ingrese sólo valores nómericos");
			return -1;
		} else
			return Integer.parseInt(txtEstimadoSolucion.getText());
	}

	private long leerTiempoReal() {
		// long TimeInicio =
		// dcInicioAtencion.getCalendar().get(Calendar.MINUTE), TimeFin =
		// dcFinAtencion.getCalendar().get(Calendar.MINUTE);
		long i = dcInicioAtencion.getCalendar().getTimeInMillis(), f = dcFinAtencion
				.getCalendar().getTimeInMillis();
		long diff = f - i;
		//JOptionPane.showMessageDialog(null, diff);
		long difMinutos = diff / (60 * 1000);
		// JOptionPane.showMessageDialog(null, difMinutos);

		return difMinutos;
	}

	private Date leerFechaRegistro() {
		return dcFechaRegistro.getDate();
	}

	private Date leerFechaInicio() {
		return dcInicioAtencion.getDate();
	}

	private Date leerFechaFin() {
		return dcFinAtencion.getDate();
	}

	private int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

	private String leerNombreUsuario() {
		return cboMUsuario.getSelectedItem().toString();
	}

	private String leerNombreEspecialista() {
		return cboMEspecialista.getSelectedItem().toString();
	}

	private String leerNombreTipoIncidencia() {
		return cboMTipoIncidencia.getSelectedItem().toString();
	}

	private String leerNombreEstado() {
		return cboEstado.getSelectedItem().toString();
	}

	private void setDataIncidencia(BE_Incidencia i) {

		i.setCodigoincidencia(leerCodigo());
		i.setCodigoUsuario(leerCodigoUsuario());
		i.setCodigoEspecialista(leerCodigoEspecialista());
		i.setCodigoTipoIncidencia(leerCodigoTipoIncidencia());
		if (leerDescripcion() == "") {
			return;
		} else
			i.setDescripcion(leerDescripcion());
		i.setDescripcion(leerDescripcion());
		i.setObservacion(leerObservacion());
		if (leerTiempoEstimado() == -1) {
			return;
		} else
			i.setTiempoEstimado(leerTiempoEstimado());

		i.setTiempoReal(leerTiempoReal());
		i.setFechaRegistro(leerFechaRegistro());
		i.setFechaInicio(leerFechaInicio());
		i.setFechaFin(leerFechaFin());
		i.setEstado(leerEstado());
		// i.setNombreUsuario(leerNombreUsuario());
		// i.setNombreEspecialista(leerNombreEspecialista());
		// i.setNombreTipoIncidencia(leerNombreTipoIncidencia());
		i.setNombreEstado(leerNombreEstado());
	}

	// EndRegion

	protected void do_btnBuscarUsuario_actionPerformed(ActionEvent e) {
		Usuario frame = Usuario.getInstance();
		frame.setListener("Incidencia");
		General.addIFrame(Menu_Principal.desktopPane, frame);

	}

	protected void do_mniLimpiar_actionPerformed(ActionEvent e) {
		General.limpiar(panelMantenimiento);
		JDateChooser[] jdc = { dcFechaRegistro, dcFinAtencion, dcInicioAtencion };
		setFechaToControls(jdc);
	}

	void setFechaToControls(JDateChooser[] jdc) {
		for (JDateChooser dc : jdc) {
			dc.setDate(new Date());
		}
	}

	void habilitarMenuItems(boolean estado) {
		JMenuItem[] menuItem = { mniEditar, mniGuardar, mniAtras, mniLimpiar };

		General.habilitar(menuItem, estado);

	}
}
