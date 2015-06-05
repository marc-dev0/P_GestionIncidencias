package Frames;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Utilitarios.General;

import java.awt.Color;
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
				menuBar.add(mnMantenimientos);
				{
					mntmUsuarios = new JMenuItem("Usuarios");
					mntmUsuarios.addActionListener(this);
					mnMantenimientos.add(mntmUsuarios);
				}
				{
					separator = new JSeparator();
					mnMantenimientos.add(separator);
				}
				{
					mntmNewMenuItem = new JMenuItem("Áreas");
					mnMantenimientos.add(mntmNewMenuItem);
				}
				{
					separator_1 = new JSeparator();
					mnMantenimientos.add(separator_1);
				}
				{
					mntmTipoIncidencia = new JMenuItem("Tipo Incidencia");
					mnMantenimientos.add(mntmTipoIncidencia);
				}
				{
					separator_2 = new JSeparator();
					mnMantenimientos.add(separator_2);
				}
				{
					mntmTipoDocumentotem = new JMenuItem("Tipo Documento");
					mnMantenimientos.add(mntmTipoDocumentotem);
				}
				{
					separator_3 = new JSeparator();
					mnMantenimientos.add(separator_3);
				}
				{
					mntmEspecialista = new JMenuItem("Especialista");
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
		if (e.getSource() == mntmUsuarios) {
			do_mntmUsuarios_actionPerformed(e);
		}
	}
	protected void do_mntmUsuarios_actionPerformed(ActionEvent e) {
		Usuario objUsuario = Usuario.getInstance();
		General.addIFrame(desktopPane, objUsuario);
	}
}
