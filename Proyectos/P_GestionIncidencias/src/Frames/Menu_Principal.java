package Frames;

import internalFrame.Mantenimientos.Area;
import internalFrame.Mantenimientos.Especialista;
import internalFrame.Mantenimientos.TipoDocumento;
import internalFrame.Mantenimientos.TipoIncidencia;
import internalFrame.Mantenimientos.Usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Utilitarios.General;

import java.awt.Color;
import javax.swing.ImageIcon;
public class Menu_Principal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnMantenimientos;
	private JDesktopPane desktopPane;
	private JMenuItem mntmUsuarios;
	private JMenu mnNewMenu;
	private JMenu mnReportes;
	private JMenuItem mntmNewMenuItem;
	private JSeparator separator;
	private JMenuItem mntmTipoIncidencia;
	private JMenuItem mntmTipoDocumentotem;
	private JMenuItem mntmEspecialista;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Menu_Principal frame = new Menu_Principal();
					frame.setVisible(true);
					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu_Principal() {
		setForeground(Color.DARK_GRAY);
		setTitle("Sistema de Gestion de Incidencias");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				mnMantenimientos = new JMenu("Mantenimientos");
				mnMantenimientos.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x32/maintenance_32.png")));
				menuBar.add(mnMantenimientos);
				{
					mntmUsuarios = new JMenuItem("Usuarios");
					mntmUsuarios.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x24/usuario_x24.png")));
					mntmUsuarios.addActionListener(this);
					mnMantenimientos.add(mntmUsuarios);
				}
				{
					separator = new JSeparator();
					mnMantenimientos.add(separator);
				}
				{
					mntmNewMenuItem = new JMenuItem("Áreas");
					mntmNewMenuItem.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x24/area_x24.png")));
					mntmNewMenuItem.addActionListener(this);
					mnMantenimientos.add(mntmNewMenuItem);
				}
				{
					separator_1 = new JSeparator();
					mnMantenimientos.add(separator_1);
				}
				{
					mntmTipoIncidencia = new JMenuItem("Tipo Incidencia");
					mntmTipoIncidencia.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x24/incidencia_x24.png")));
					mntmTipoIncidencia.addActionListener(this);
					mnMantenimientos.add(mntmTipoIncidencia);
				}
				{
					separator_2 = new JSeparator();
					mnMantenimientos.add(separator_2);
				}
				{
					mntmTipoDocumentotem = new JMenuItem("Tipo Documento");
					mntmTipoDocumentotem.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x24/documento_x24.png")));
					mntmTipoDocumentotem.addActionListener(this);
					mnMantenimientos.add(mntmTipoDocumentotem);
				}
				{
					separator_3 = new JSeparator();
					mnMantenimientos.add(separator_3);
				}
				{
					mntmEspecialista = new JMenuItem("Especialista");
					mntmEspecialista.setIcon(new ImageIcon(Menu_Principal.class.getResource("/imagenes_x24/especialista_x24.png")));
					mntmEspecialista.addActionListener(this);
					mnMantenimientos.add(mntmEspecialista);
				}
			}
			{
				mnNewMenu = new JMenu("Atención de Incidencias");
				menuBar.add(mnNewMenu);
			}
			{
				mnReportes = new JMenu("Reportes");
				menuBar.add(mnReportes);
			}
		}
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		{
			desktopPane = new JDesktopPane();
			desktopPane.setForeground(Color.DARK_GRAY);
			desktopPane.setBounds(0, 0, 0, 0);
			contentPane.add(desktopPane);
		}
	}
	public void actionPerformed(ActionEvent e) {
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
}
