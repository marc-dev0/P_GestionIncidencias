package Frames;

import internalFrame.Incidencias.Incidencia;
import internalFrame.Incidencias.Listado;
import internalFrame.Mantenimientos.Area;
import internalFrame.Mantenimientos.Especialista;
import internalFrame.Mantenimientos.TipoDocumento;
import internalFrame.Mantenimientos.TipoIncidencia;
import internalFrame.Mantenimientos.Usuario;
import internalFrame.Reportes.rpt_IncidenciasTiempoEstado;
import internalFrame.Reportes.rpt_IncidenciasXArea;
import internalFrame.Reportes.rpt_IncidenciasXRangoFecha;
import internalFrame.Reportes.rpt_IncidenciasXTipo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Utilitarios.General;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu_Principal extends JFrame implements ActionListener {

	// Region Controles
	private JButton btnIngresar, btnSalir;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JPanel contentPane;
	private JMenuBar menuBar;
	public static JDesktopPane desktopPane;
	private JMenu mnNewMenu, mnReportes, mnMantenimientos;
	private JSeparator separator, separator_1, separator_2, separator_3;
	private JDialog dialogo;
	private JMenuItem mntmIngreso, mntmListado, mntmActualizacin,
			mntmIncidenciasPor, mntmIncidenciasPorTipo,
			mntmIncidenciasPorRango, mntmIncidenciasPorDemora,
			mntmEspecialista, mntmTipoDocumentotem, mntmTipoIncidencia,
			mntmNewMenuItem, mntmUsuarios;
	private JSeparator separator_4;
	private JSeparator separator_5;

	// EndRegion

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new SplashScreen(8000);
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Menu_Principal frame = new Menu_Principal();
					frame.setVisible(false);

					//frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu_Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Menu_Principal.class.getResource("/imagenes_x32/logo.png")));

		setForeground(Color.DARK_GRAY);
		setTitle("Sistema de Gestion de Incidencias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				mnMantenimientos = new JMenu("Mantenimientos");
				mnMantenimientos.setIcon(new ImageIcon(Menu_Principal.class
						.getResource("/imagenes_x24/manteinance_x24.png")));
				menuBar.add(mnMantenimientos);
				{
					mntmUsuarios = new JMenuItem("Usuarios");
					mntmUsuarios.setIcon(new ImageIcon(Menu_Principal.class
							.getResource("/imagenes_x24/usuario_x24.png")));
					mntmUsuarios.addActionListener(this);
					mnMantenimientos.add(mntmUsuarios);
				}
				{
					separator = new JSeparator();
					mnMantenimientos.add(separator);
				}
				{
					mntmNewMenuItem = new JMenuItem("Áreas");
					mntmNewMenuItem.setIcon(new ImageIcon(Menu_Principal.class
							.getResource("/imagenes_x24/area1_x24.png")));
					mntmNewMenuItem.addActionListener(this);
					mnMantenimientos.add(mntmNewMenuItem);
				}
				{
					separator_1 = new JSeparator();
					mnMantenimientos.add(separator_1);
				}
				{
					mntmTipoIncidencia = new JMenuItem("Tipo Incidencia");
					mntmTipoIncidencia
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/incidencia_x24.png")));
					mntmTipoIncidencia.addActionListener(this);
					mnMantenimientos.add(mntmTipoIncidencia);
				}
				{
					separator_2 = new JSeparator();
					mnMantenimientos.add(separator_2);
				}
				{
					mntmTipoDocumentotem = new JMenuItem("Tipo Documento");
					mntmTipoDocumentotem
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/documento_x24.png")));
					mntmTipoDocumentotem.addActionListener(this);
					mnMantenimientos.add(mntmTipoDocumentotem);
				}
				{
					separator_3 = new JSeparator();
					mnMantenimientos.add(separator_3);
				}
				{
					mntmEspecialista = new JMenuItem("Especialista");
					mntmEspecialista
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/especialista_x24.png")));
					mntmEspecialista.addActionListener(this);
					mnMantenimientos.add(mntmEspecialista);
				}
			}
			{
				mnNewMenu = new JMenu("Atención de Incidencias");
				mnNewMenu.addActionListener(this);
				mnNewMenu
						.setIcon(new ImageIcon(
								Menu_Principal.class
										.getResource("/imagenes_x24/atencion_incidencias_x24.png")));
				menuBar.add(mnNewMenu);
				{
					mntmIngreso = new JMenuItem("Ingreso");
					mntmIngreso
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/nuevo_documentox24.png")));
					mntmIngreso.addActionListener(this);
					mnNewMenu.add(mntmIngreso);
				}
				{
					separator_4 = new JSeparator();
					mnNewMenu.add(separator_4);
				}
				{
					mntmListado = new JMenuItem("Listado");
					mntmListado.addActionListener(this);
					mntmListado.setIcon(new ImageIcon(Menu_Principal.class
							.getResource("/imagenes_x24/lista_x24.png")));
					mnNewMenu.add(mntmListado);
				}
				{
					separator_5 = new JSeparator();
					mnNewMenu.add(separator_5);
				}

			}
			{
				mnReportes = new JMenu("Reportes");
				mnReportes.setIcon(new ImageIcon(Menu_Principal.class
						.getResource("/imagenes_x24/report2_x24.png")));
				menuBar.add(mnReportes);
				{
					mntmIncidenciasPor = new JMenuItem("Incidencias por Area");
					mntmIncidenciasPor
							.setIcon(new ImageIcon(Menu_Principal.class
									.getResource("/imagenes_x24/area1_x24.png")));
					mntmIncidenciasPor.addActionListener(this);
					mnReportes.add(mntmIncidenciasPor);
				}
				{
					mntmIncidenciasPorTipo = new JMenuItem(
							"Incidencias por Tipo de Incidencia");
					mntmIncidenciasPorTipo
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/incidencia_x24.png")));
					mntmIncidenciasPorTipo.addActionListener(this);
					mnReportes.add(mntmIncidenciasPorTipo);
				}
				{
					mntmIncidenciasPorRango = new JMenuItem(
							"Incidencias por Rango de Fechas");
					mntmIncidenciasPorRango
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/fechas_x24.png")));
					mntmIncidenciasPorRango.addActionListener(this);
					mnReportes.add(mntmIncidenciasPorRango);
				}
				{
					mntmIncidenciasPorDemora = new JMenuItem(
							"Incidencias por demora en atención y estado");
					mntmIncidenciasPorDemora
							.setIcon(new ImageIcon(
									Menu_Principal.class
											.getResource("/imagenes_x24/tiempoAtencion_x24.png")));
					mntmIncidenciasPorDemora.addActionListener(this);
					mnReportes.add(mntmIncidenciasPorDemora);
				}
			}
		}
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		{
			desktopPane = new JDesktopPane();
			desktopPane.setBackground(Color.gray);
			desktopPane.setBounds(0, 0, 0, 0);
			contentPane.add(desktopPane);
		}
		// Region Dialogo para Logeo
		 dialogo = new JDialog();
		 JPanel contentPane = new JPanel();
		 contentPane.setBounds(100,100, 400,108);
		 contentPane.setLayout(null);
		 dialogo.setContentPane(contentPane);
		 {
	JLabel lblUsuario = new JLabel("Usuario:");
		 lblUsuario.setBounds(20,14,70,15);
		 contentPane.add(lblUsuario);
		 }
		 {
		 txtUsuario = new JTextField("");
		 txtUsuario.setBounds(103, 12, 150, 24);
		 contentPane.add(txtUsuario);
	 }
		 {
		 JLabel lblContraseña = new JLabel("Contraseña:");
		 lblContraseña.setBounds(20,48, 98,15);
		 contentPane.add(lblContraseña);
		 }
		 {
		 txtContraseña = new JPasswordField();
		 txtContraseña.setBounds(120,44, 150,24);
		 contentPane.add(txtContraseña);
		 }
		 {
		 btnIngresar = new JButton("Ingresar");
		 btnIngresar.setBounds(276,11,112,24);
		 btnIngresar.addActionListener(this);
		 contentPane.add(btnIngresar);
		 }
		 {
		 btnSalir = new JButton("Salir");
		 btnSalir.setBounds(304, 48, 75, 24);
		 btnSalir.addActionListener(this);
		 contentPane.add(btnSalir);
		 }
		 dialogo.setVisible(true);
		 dialogo.setTitle("Login");
		 dialogo.setBounds(100,100, 430,120);
		 try {
		 dialogo.getContentPane().add(new JLabel(new
		 ImageIcon(ImageIO.read(getClass().getResourceAsStream("/imagenesx16/Loqin_x16.png")))));
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 dialogo.setLocationRelativeTo(null);
		
		// EndRegion

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmIncidenciasPor) {
			do_mntmIncidenciasPor_actionPerformed(e);
		}
		if (e.getSource() == mntmIncidenciasPorDemora) {
			do_mntmIncidenciasPorDemora_actionPerformed(e);
		}
		if (e.getSource() == mntmIncidenciasPorRango) {
			do_mntmIncidenciasPorRango_actionPerformed(e);
		}
		if (e.getSource() == mntmIncidenciasPorTipo) {
			do_mntmIncidenciasPorTipo_actionPerformed(e);
		}
		if (e.getSource() == mntmListado) {
			do_mntmListado_actionPerformed(e);
		}
		if (e.getSource() == mntmIngreso) {
			do_mntmIngreso_actionPerformed(e);
		}
		if (e.getSource() == mntmEspecialista) {
			do_mntmEspecialista_actionPerformed(e);
		}
		if (e.getSource() == mntmTipoIncidencia) {
			do_mntmTipoIncidencia_actionPerformed(e);
		}
		if (e.getSource() == mntmTipoDocumentotem) {
			do_mntmTipoDocumentotem_actionPerformed(e);
		}
		if (e.getSource() == mntmNewMenuItem) {
			do_mntmNewMenuItem_actionPerformed(e);
		}
		if (e.getSource() == mntmUsuarios) {
			do_mntmUsuarios_actionPerformed(e);
		}
		if (e.getSource() == btnIngresar) {
			do_btnIngresar_performed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
	}

	private void do_btnSalir_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	protected void do_mntmUsuarios_actionPerformed(ActionEvent e) {
		Usuario objUsuario = Usuario.getInstance();
		General.addIFrame(desktopPane, objUsuario);
	}

	protected void do_mntmNewMenuItem_actionPerformed(ActionEvent e) {
		Area objArea = Area.getInstance();
		General.addIFrame(desktopPane, objArea);
	}

	protected void do_mntmTipoDocumentotem_actionPerformed(ActionEvent e) {
		TipoDocumento objTipoDocumento = TipoDocumento.getInstance();
		General.addIFrame(desktopPane, objTipoDocumento);
	}

	protected void do_mntmTipoIncidencia_actionPerformed(ActionEvent e) {
		TipoIncidencia objTipoIncidencia = TipoIncidencia.getInstance();
		General.addIFrame(desktopPane, objTipoIncidencia);
	}

	protected void do_mntmEspecialista_actionPerformed(ActionEvent e) {
		Especialista objEspecialista = Especialista.getInstance();
		General.addIFrame(desktopPane, objEspecialista);
	}

	private void do_btnIngresar_performed(ActionEvent e) {

		try {
			BufferedReader br = new BufferedReader(
					new FileReader("Usuario.txt"));
			String user, linea, s[], nombreCompleto;
			char[] pass;
			try {
				while ((linea = br.readLine()) != null) {
					s = linea.split(",");
					user = s[4];
					pass = s[10].toCharArray();
					nombreCompleto = s[1] + " " + s[2];
					if (user.equals(txtUsuario.getText())
							&& Arrays.equals(pass, txtContraseña.getPassword())) {
						JOptionPane.showMessageDialog(null, "Bienvenido "
								+ nombreCompleto);
						dialogo.setVisible(false);
						this.setVisible(true);
						this.setExtendedState(MAXIMIZED_BOTH);
						return;
					} else
						JOptionPane.showMessageDialog(null,
								"El usuario o contraseña son incorrectos");

				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	protected void do_mntmIngreso_actionPerformed(ActionEvent e) {
		Incidencia inci = Incidencia.getInstance();
		General.addIFrame(desktopPane, inci);
	}

	protected void do_mntmListado_actionPerformed(ActionEvent e) {
		Listado iListado = Listado.getInstance();
		General.addIFrame(desktopPane, iListado);
	}

	protected void do_mntmIncidenciasPorTipo_actionPerformed(ActionEvent e) {
		rpt_IncidenciasXTipo iRptTipo = rpt_IncidenciasXTipo.getInstance();
		General.addIFrame(desktopPane, iRptTipo);
	}

	protected void do_mntmIncidenciasPorRango_actionPerformed(ActionEvent e) {
		rpt_IncidenciasXRangoFecha iRptFecha = rpt_IncidenciasXRangoFecha
				.getInstance();
		General.addIFrame(desktopPane, iRptFecha);
	}

	protected void do_mntmIncidenciasPorDemora_actionPerformed(ActionEvent e) {
		rpt_IncidenciasTiempoEstado iRptTiempoEstado = rpt_IncidenciasTiempoEstado
				.getInstance();
		General.addIFrame(desktopPane, iRptTiempoEstado);
	}

	protected void do_mntmIncidenciasPor_actionPerformed(ActionEvent e) {
		rpt_IncidenciasXArea iRptArea = rpt_IncidenciasXArea.getInstance();
		General.addIFrame(desktopPane, iRptArea);
	}
}
