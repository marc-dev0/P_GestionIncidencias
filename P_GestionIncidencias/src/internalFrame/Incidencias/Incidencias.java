package internalFrame.Incidencias;

import java.awt.EventQueue;
import java.awt.MenuItem;
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
import Controlador.TipoIncidenciaController;
import Controlador.UsuarioController;

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
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

import Utilitarios.*;
public class Incidencias extends JFrame implements ActionListener {
	
	
	//Region Controladores
	private UsuarioController UsuarioC = new UsuarioController();
	private EspecialistaController EspecialistaC = new EspecialistaController();
	private TipoIncidenciaController TipoIncidenciaC = new TipoIncidenciaController();
	//EndRegion
	
	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblMUsuario;
	private JComboBox cboMUsuario;
	private JLabel lblMEspecialista;
	private JComboBox cboMEspecialista;
	private JLabel lblMTipoIncidencia;
	private JComboBox cboMTipoIncidencia;
	private JScrollPane spDescripcion;
	private JLabel lblDescripcin;
	private JTextPane txtDescripcion;
	private JScrollPane spObservacion;
	private JLabel lblObservacion;
	private JTextArea txtObservacion;
	private JLabel lblTiempoEstimado;
	private JTextField txtEstimadoSolucion;
	private JLabel lblmin;
	private JTextField txtRealSolucion;
	private JLabel lblTiempoRealSolucin;
	private JLabel lblmin1;
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
	
	//EndRegion
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incidencias frame = new Incidencias();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Incidencias() {
		setTitle("INCIDENCIAS");
		//setClosable(true);
		setBounds(100, 100, 860, 625);
		getContentPane().setLayout(null);
		{
			/*{
				if(UsuarioC.lista() != null){
					cboUsuario.setModel(new DefaultComboBoxModel(UsuarioC.lista()));	
				}
			}
			{
				if(TipoIncidenciaC.lista() != null)
					cboTipoIncidencia.setModel(new DefaultComboBoxModel(TipoIncidenciaC.lista()));
			}
			{
				if(EspecialistaC.lista() != null)
					cboEspecialista.setModel(new DefaultComboBoxModel(EspecialistaC.lista()));
			}*/
		}
		JPanel panelTabs = new JPanel();
		panelTabs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabs.setBounds(12,12, 840, 605);
		getContentPane().add(panelTabs);
		panelTabs.setLayout(null);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(12, 39, 816, 558);
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
						table.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Código", "CodigoUsuario", "NombreUsuario", "CodigoEspecialista",
								"NombreEspecialista","CodigoIncidencia", "NombreIncidencia", 
								"Descripcion", "Observaciones", "TiempoEstimado", "TiempoReal",
								"FechaRegistro", "FechaIncio", "FechaFin", "Estado"
							}
						));
						
						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						table.getColumnModel().getColumn(1).setMinWidth(0);
						table.getColumnModel().getColumn(1).setMaxWidth(0);
						table.getColumnModel().getColumn(3).setMinWidth(0);
						table.getColumnModel().getColumn(3).setMaxWidth(0);
						table.getColumnModel().getColumn(5).setMinWidth(0);
						table.getColumnModel().getColumn(5).setMaxWidth(0);
						table.getColumnModel().getColumn(7).setMinWidth(0);
						table.getColumnModel().getColumn(7).setMaxWidth(0);
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
			panelMantenimiento.setLayout(null);
			{
				lblCodigo = new JLabel("Código: ");
				lblCodigo.setBounds(12, 12, 70, 24);
				panelMantenimiento.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setBounds(90, 13, 114, 24);
				panelMantenimiento.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				lblMUsuario = new JLabel("Usuario:");
				lblMUsuario.setBounds(12, 52, 70, 24);
				panelMantenimiento.add(lblMUsuario);
			}
			cboMUsuario = new JComboBox();
			if(UsuarioC.lista() != null)
				cboMUsuario.setModel(new DefaultComboBoxModel(UsuarioC.lista()));
			cboMUsuario.setBounds(90, 52, 40, 24);
			panelMantenimiento.add(cboMUsuario);
			{
				lblMEspecialista = new JLabel("Especialista:");
				lblMEspecialista.setBounds(12, 92, 102, 24);
				panelMantenimiento.add(lblMEspecialista);
			}
			cboMEspecialista = new JComboBox();
			if(EspecialistaC.lista() != null)
				cboMEspecialista.setModel(new DefaultComboBoxModel(EspecialistaC.lista()));
			cboMEspecialista.setBounds(121, 92, 40, 24);
			panelMantenimiento.add(cboMEspecialista);
			{
				lblMTipoIncidencia = new JLabel("Tipo Incidencia:");
				lblMTipoIncidencia.setBounds(12, 132, 118, 24);
				panelMantenimiento.add(lblMTipoIncidencia);
			}
			cboMTipoIncidencia = new JComboBox();
			cboMTipoIncidencia.setBounds(131, 132, 40, 24);
			panelMantenimiento.add(cboMTipoIncidencia);
			{
				spDescripcion = new JScrollPane();
				spDescripcion.setBounds(12, 202, 390, 205);
				panelMantenimiento.add(spDescripcion);
				{
					txtDescripcion = new JTextPane();
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
				txtRealSolucion = new JTextField();
				txtRealSolucion.setColumns(10);
				txtRealSolucion.setBounds(582, 415, 40, 24);
				panelMantenimiento.add(txtRealSolucion);
			}
			{
				lblTiempoRealSolucin = new JLabel("Tiempo Real Solución:");
				lblTiempoRealSolucin.setBounds(414, 414, 164, 24);
				panelMantenimiento.add(lblTiempoRealSolucin);
			}
			{
				lblmin1 = new JLabel("m.");
				lblmin1.setBounds(625, 419, 40, 15);
				panelMantenimiento.add(lblmin1);
			}
			{
				lblInicioAtencion = new JLabel("Inicio de Atención:");
				lblInicioAtencion.setBounds(12, 459, 139, 24);
				panelMantenimiento.add(lblInicioAtencion);
			}
			{
				dcInicioAtencion = new JDateChooser(new Date());
				dcInicioAtencion.setBounds(150, 459, 130, 24);
				panelMantenimiento.add(dcInicioAtencion);
			}
			{
				dcFinAtencion = new JDateChooser(new Date());
				dcFinAtencion.setBounds(510, 459, 130, 24);
				panelMantenimiento.add(dcFinAtencion);
			}
			{
				lblFinAtencin = new JLabel("Fin Atención:");
				lblFinAtencin.setBounds(414, 459, 102, 24);
				panelMantenimiento.add(lblFinAtencin);
			}
			{
				dcFechaRegistro = new JDateChooser(new Date());
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
				cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Registrada", "Iniciada", "Cancelada", "Cerrada"}));
				cboEstado.setBounds(78, 499, 177, 24);
				panelMantenimiento.add(cboEstado);
			}
			
			{
				menuBar = new JMenuBar();
				menuBar.setBorder(BorderFactory.createTitledBorder(""));
				menuBar.setBounds(12, 12, 190, 21);
				panelTabs.add(menuBar);
				
				{
					mniNuevo = new JMenuItem("");
					mniNuevo.addActionListener(this);
					mniNuevo.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/nuevo_x16.png")));
					
					mniNuevo.setToolTipText("Nueva Incidencia");
					menuBar.add(mniNuevo);
				}
				{
					mniEditar = new JMenuItem("");
					mniEditar.addActionListener(this);
					mniEditar.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/edit_x16.png")));
					mniEditar.setToolTipText("Editar Incidencia");
					menuBar.add(mniEditar);
				}
				{
					mniGuardar = new JMenuItem("");
					mniGuardar.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/guardar_x16.png")));
					mniGuardar.setToolTipText("Guardar");
					mniGuardar.setEnabled(false);
					menuBar.add(mniGuardar);
				}
				{
					mniLimpiar = new JMenuItem("");
					mniLimpiar.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/limpiar_x16.png")));
					mniLimpiar.setToolTipText("Limpiar");
					mniLimpiar.setEnabled(false);
					menuBar.add(mniLimpiar);
				}
				{
					mniAtras = new JMenuItem("");
					mniAtras.addActionListener(this);
					mniAtras.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/atras_x16.png")));
					mniAtras.setToolTipText("Volver");
					mniAtras.setEnabled(false);
					menuBar.add(mniAtras);
				}
				{
					mniSalir = new JMenuItem("");
					mniSalir.addActionListener(this);
					mniSalir.setIcon(new ImageIcon(Incidencias.class.getResource("/imagenesx16/exit_x16.png")));
					mniSalir.setToolTipText("Salir");
					menuBar.add(mniSalir);
				}
				
			}
		
	}

	public void actionPerformed(ActionEvent e) {
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
		if(tabbedPane.getSelectedIndex() == 0)
		{
			habilitarMenuItems(true);
			General.changePanel(tabbedPane, true);
		}
	}
	
	protected void do_mniAtras_actionPerformed(ActionEvent e) {
		habilitarMenuItems(true);
		General.changePanel(tabbedPane, false);
	}

	protected void do_mniEditar_actionPerformed(ActionEvent e) {
		habilitarMenuItems(true);
		General.changePanel(tabbedPane, true);
	}
	
	protected void do_mniSalir_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	void habilitarMenuItems(boolean estado){
		JMenuItem[] menuItem = {mniEditar, mniGuardar, mniAtras,mniLimpiar};
		General.habilitar(menuItem, estado);
		
	}
}
