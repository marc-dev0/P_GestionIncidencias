package internalFrame.Mantenimientos;

import Controlador.UsuarioController;
import Entidades.BE_Usuario;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.jdatepicker.impl.UtilDateModel;

import Utilitarios.DateLabelFormatter;
import Utilitarios.General;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class Usuario extends JInternalFrame implements ActionListener {
	//Region variables de controles
	private DefaultTableModel modelo;
	private JTextField textField_7;
	private JTabbedPane tabbedPane;
	private JPanel panelListado;
	private JPanel panelMantenimiento;
	private JLabel lblD;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel lblTipoDocumento;
	private JLabel lblDni;
	private JTextField txtDNI;
	private JLabel lblarea;
	private JComboBox cboTipoDocumento;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblTelfono;
	private JTextField txtTelefono;
	private JLabel lblFechaDeIngreso;
	private JComboBox cboArea;
	private JButton btnRegistrar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnVolver;
	private JPanel panel;
	private JLabel lblCdigo;
	private JTextField txtBusquedaCodigo;
	private JButton btnBuscar;
	private JButton btnSalir;
	private JDateChooser dateChooser;

	//EndRegion
	private String estado="";
	private UsuarioController c_Usuario = new UsuarioController();
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Usuario() {
		setClosable(true);
		setTitle("Usuarios");
		setBounds(100, 100, 1300, 373);
		getContentPane().setLayout(null);
		
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 1158, 340);
			getContentPane().add(tabbedPane);
		
			{
				panelListado = new JPanel();
				tabbedPane.addTab("Listado", null, panelListado, null);
				panelListado.setLayout(null);
				{
					scrollPane = new JScrollPane();
					scrollPane.setBounds(12, 73, 1129, 203);
					panelListado.add(scrollPane);
					{
						table = new JTable();
						table.setAutoCreateRowSorter(true);
						table.getTableHeader().setToolTipText("Click para alinear");
						
						table.setModel(modelo = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Codigo", "Nombre", "Apellidos", "idTipoDocumento", "Doc. de Identidad", "Area", "Correo", "Tel\u00E9fono", "Fecha de Ingreso"
							}
						));
						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						table.getColumnModel().getColumn(3).setMinWidth(0);
						table.getColumnModel().getColumn(3).setMaxWidth(0);
						scrollPane.setViewportView(table);
					    
					}
				}
				{
					panel = new JPanel();
					panel.setBounds(12, 12, 355, 57);
					panel.setBorder(BorderFactory.createTitledBorder("Filtro de búsqueda"));
					panelListado.add(panel);
					panel.setLayout(null);
					{
						lblCdigo = new JLabel("Código:");
						lblCdigo.setBounds(12, 32, 70, 15);
						panel.add(lblCdigo);
					}
					{
						txtBusquedaCodigo = new JTextField();
						txtBusquedaCodigo.setBounds(88, 30, 131, 17);
						panel.add(txtBusquedaCodigo);
						txtBusquedaCodigo.setColumns(10);
					}
					{
						btnBuscar = new JButton("Buscar");
						btnBuscar.addActionListener(this);
						btnBuscar.setBounds(226, 22, 117, 25);
						panel.add(btnBuscar);
					}
				}
			}
			{
				panelMantenimiento = new JPanel();
				tabbedPane.addTab("Mantenimiento", null, panelMantenimiento, null);
				tabbedPane.setEnabledAt(1, false);
				panelMantenimiento.setLayout(null);
				{
					lblCodigo = new JLabel("Código:");
					lblCodigo.setBounds(12, 12, 60, 20);
					panelMantenimiento.add(lblCodigo);
				}
				{
					lblNombre = new JLabel("Nombre:");
					lblNombre.setBounds(12, 42, 60, 15);
					panelMantenimiento.add(lblNombre);
				}
				{
					lblApellidos = new JLabel("Apellidos:");
					lblApellidos.setBounds(12, 75, 70, 19);
					panelMantenimiento.add(lblApellidos);
				}
				{
					txtCodigo = new JTextField();
					txtCodigo.setBounds(78, 8, 168, 24);
					panelMantenimiento.add(txtCodigo);
					
				}
				{
					txtNombre = new JTextField();
					txtNombre.setColumns(10);
					txtNombre.setBounds(78, 40, 168, 24);
					panelMantenimiento.add(txtNombre);
				}
				{
					txtApellidos = new JTextField();
					txtApellidos.setColumns(10);
					txtApellidos.setBounds(90, 70, 168, 24);
					panelMantenimiento.add(txtApellidos);
				}
				{
					lblTipoDocumento = new JLabel("Tipo Documento:");
					lblTipoDocumento.setBounds(12, 110, 124, 15);
					panelMantenimiento.add(lblTipoDocumento);
				}
				{
					lblDni = new JLabel("DNI:");
					lblDni.setBounds(12, 140, 36, 15);
					panelMantenimiento.add(lblDni);
				}
				{
					txtDNI = new JTextField();
					txtDNI.setColumns(10);
					txtDNI.setBounds(54, 132, 168, 24);
					panelMantenimiento.add(txtDNI);
				}
				{
					lblarea = new JLabel("Área:");
					lblarea.setBounds(12, 170, 38, 15);
					panelMantenimiento.add(lblarea);
				}
				{
					cboTipoDocumento = new JComboBox();
					cboTipoDocumento.setModel(new DefaultComboBoxModel(new String[] {"DNI", "PASAPORTE", "CARNET DE EXTRANJERIA"}));
					cboTipoDocumento.setBounds(136, 101, 201, 24);
					panelMantenimiento.add(cboTipoDocumento);
				}
				{
					lblCorreo = new JLabel("Correo:");
					lblCorreo.setBounds(12, 200, 60, 15);
					panelMantenimiento.add(lblCorreo);
				}
				{
					txtCorreo = new JTextField();
					txtCorreo.setBounds(69, 194, 168, 24);
					panelMantenimiento.add(txtCorreo);
				}
				{
					lblTelfono = new JLabel("Teléfono:");
					lblTelfono.setBounds(12, 230, 78, 15);
					panelMantenimiento.add(lblTelfono);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setBounds(88, 225, 168, 24);
					panelMantenimiento.add(txtTelefono);
				}
				{
					lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
					lblFechaDeIngreso.setBounds(12, 260, 130, 15);
					panelMantenimiento.add(lblFechaDeIngreso);
				}
				{
					cboArea = new JComboBox();
					cboArea.setModel(new DefaultComboBoxModel(new String[] {"RRHHH", "SISTEMAS", "MARKETING", "VENTAS", "COMPRAS"}));
					cboArea.setBounds(61, 163, 196, 24);
					panelMantenimiento.add(cboArea);
				}
				{
					dateChooser = new JDateChooser();
					dateChooser.setBounds(146, 256, 155, 19);
					panelMantenimiento.add(dateChooser);
				}
			}
		}
		{
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(this);
			btnRegistrar.setBounds(1170, 12, 108, 47);
			getContentPane().add(btnRegistrar);
		}
		{
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(this);
			btnEditar.setBounds(1170, 65, 108, 47);
			getContentPane().add(btnEditar);
		}
		{
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(1170, 118, 108, 47);
			getContentPane().add(btnEliminar);
		}
		{
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setEnabled(false);
			btnLimpiar.addActionListener(this);
			btnLimpiar.setBounds(1170, 171, 108, 47);
			getContentPane().add(btnLimpiar);
		}
		{
			btnVolver = new JButton("Volver");
			btnVolver.setEnabled(false);
			btnVolver.addActionListener(this);
			btnVolver.setBounds(1170, 224, 108, 47);
			getContentPane().add(btnVolver);
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setBounds(1170, 277, 108, 47);
			getContentPane().add(btnSalir);
		}

	}
	

	private static Usuario instance =  null;
	public static Usuario getInstance(){
		if(instance==null){
			instance = new Usuario();
		}
		return instance;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnEditar) {
			do_btnEditar_actionPerformed(e);
		}
		if (e.getSource() == btnVolver) {
			do_btnVolver_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	private void do_btnVolver_actionPerformed(ActionEvent e) {
		tabbedPane.setSelectedIndex(0);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(0, true);
		habiltarBotones(!false);
		txtBusquedaCodigo.requestFocus();
	}
	
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		
		try {
			if(tabbedPane.getSelectedIndex() == 0){
				tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(0, false);
				habiltarBotones(true);
				txtCodigo.requestFocus();
			}
			else if(tabbedPane.getSelectedIndex() == 1){
				if(estado == ""){
				BE_Usuario objUsuario = new BE_Usuario();
				//txtCodigo.setText(""+objUsuario.getCodigo());
				objUsuario.setCodigo(Integer.parseInt(txtCodigo.getText()));
				objUsuario.setNombre(txtNombre.getText());
				objUsuario.setApellidos(txtApellidos.getText());
				objUsuario.setIdTipoDocumento(cboTipoDocumento.getSelectedIndex());
				objUsuario.setDocumento(txtDNI.getText());
				objUsuario.setCorreo(txtCorreo.getText());
				objUsuario.setArea(cboArea.getSelectedIndex());
				objUsuario.setTelefono(txtTelefono.getText());
				objUsuario.setEstado(1);
				int año= dateChooser.getCalendar().get(Calendar.YEAR);
				int mes = dateChooser.getCalendar().get(Calendar.MONTH);
				int dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
				String fecha = dia + "-" + mes + "-" + año;
				objUsuario.setFechaIngreso(General.parseStringtoDate(fecha));
				c_Usuario.registrarUsuario(objUsuario);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setEnabledAt(0, true);
				tabbedPane.setSelectedIndex(0);
				modelo.addRow(listDataToJtable(c_Usuario));
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				}
				else if(estado == "Editar"){
					int codigo = Integer.parseInt(txtCodigo.getText());
					BE_Usuario u = c_Usuario.getUsuarioxCodigo(codigo);
					int pos = c_Usuario.getPosUsuario(u);
					u.setCodigo(Integer.parseInt(txtCodigo.getText()));
					u.setNombre(txtNombre.getText());
					u.setApellidos(txtApellidos.getText());
					u.setIdTipoDocumento(cboTipoDocumento.getSelectedIndex());
					u.setDocumento(txtDNI.getText());
					u.setCorreo(txtCorreo.getText());
					u.setArea(cboArea.getSelectedIndex());
					u.setTelefono(txtTelefono.getText());
					u.setEstado(1);
					int año= dateChooser.getCalendar().get(Calendar.YEAR);
					int mes = dateChooser.getCalendar().get(Calendar.MONTH);
					int dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
					String fecha = dia + "-" + mes + "-" + año;
					u.setFechaIngreso(General.parseStringtoDate(fecha));
					c_Usuario.modificarUsuario(pos, u);
					tabbedPane.setEnabledAt(1, false);
					tabbedPane.setEnabledAt(0, true);
					tabbedPane.setSelectedIndex(0);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setValueAt(Integer.toString(u.getCodigo()), table.getSelectedRow(), 0);
					model.setValueAt(u.getNombre(), table.getSelectedRow(), 1);
					model.setValueAt(u.getApellido(), table.getSelectedRow(), 2);
					model.setValueAt(Integer.toString(u.getIdTipoDocumento()), table.getSelectedRow(), 3);
					model.setValueAt(u.getDocumento(), table.getSelectedRow(), 4);
					model.setValueAt(Integer.toString(u.getArea()), table.getSelectedRow(), 5);
					model.setValueAt(u.getCorreo(), table.getSelectedRow(), 6);
					
					model.setValueAt(u.getTelefono(), table.getSelectedRow(), 7);
					model.setValueAt(General.parsearDatetoString(u.getFechaIngreso()), table.getSelectedRow(), 8);
					//modelo.addRow(listDataToJtable(c_Usuario));
					habiltarBotones(true);
				
				}
			}
			
			//JButton[] botones = {btnRegistrar,btnVolver};
			//General.cambiarTitulo(botones);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage().toString());
		}
		
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		c_Usuario.generarCodigo();
		BE_Usuario objUsuario = new BE_Usuario();
		JOptionPane.showMessageDialog(null, "" + objUsuario.getCodigo());
		General.limpiar(panelMantenimiento);
		txtCodigo.requestFocus();
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	protected void habiltarBotones(boolean estado){
		JButton[] botones = {btnLimpiar,btnEditar,btnEliminar,btnVolver};
		General.habilitar(botones, estado);
	}
	protected void do_btnEditar_actionPerformed(ActionEvent e) {
		estado = "Editar";
		int r = table.getSelectedRow();
		if(General.validateJTableisEmpty(r))
			return;
		else{
			try {
				txtCodigo.setText((String) table.getValueAt(r, 0));
				txtNombre.setText((String) table.getValueAt(r, 1));
				txtApellidos.setText((String) table.getValueAt(r,2));
				cboTipoDocumento.setSelectedIndex(Integer.parseInt((String) table.getValueAt(r,3)));
				txtDNI.setText((String) table.getValueAt(r, 4));
				cboArea.setSelectedIndex(Integer.parseInt((String) table.getValueAt(r, 5)));
				txtCorreo.setText((String) table.getValueAt(r,6));
				txtTelefono.setText((String) table.getValueAt(r, 7));
				dateChooser.setDate(General.parseStringtoDate((String) table.getValueAt(r, 8)));
				tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabledAt(0,false);
				tabbedPane.setEnabledAt(1,true);
				habiltarBotones(true);
				txtCodigo.requestFocus();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	String[] listDataToJtable(UsuarioController UsuarioC){
		String [] data = null;
		//probar en otra clase si se puede retornar y guardar en el modelo del Jtable un arreglo de 
		//objetos en ves de un arreglo de String;
		//Object [] datos;
		for(int i = 0; i < c_Usuario.sizeUsuario(); i++){
			data = new String[9];
			//datos = new Object[]{c_Usuario.getUsuario(i).getCodigo()};
			data[0] = Integer.toString(c_Usuario.getUsuario(i).getCodigo());
			data[1] = c_Usuario.getUsuario(i).getNombre();
			data[2] = c_Usuario.getUsuario(i).getApellido();
			data[3] = Integer.toString(c_Usuario.getUsuario(i).getIdTipoDocumento());
			data[4] = c_Usuario.getUsuario(i).getDocumento();
			data[5] = Integer.toString(c_Usuario.getUsuario(i).getArea());
			data[6] = c_Usuario.getUsuario(i).getCorreo();
			data[7] = c_Usuario.getUsuario(i).getTelefono();
			data[8] = General.parsearDatetoString((c_Usuario.getUsuario(i).getFechaIngreso()));
		}
		return data;
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			int r = table.getSelectedRow();
			if(General.validateJTableisEmpty(r))
				return;
			else{
				int codigo = Integer.parseInt((String) table.getValueAt(r, 0));
				BE_Usuario objUsuarioBE = c_Usuario.getUsuarioxCodigo(codigo);
				c_Usuario.eliminarUsuario(objUsuarioBE);
				modelo.removeRow(r);
			}
			/*String [] datos;
			for(int i = 0 ; i < c_Usuario.sizeUsuario(); i++ ){
				datos = new String[9];
				datos[0] = Integer.toString(c_Usuario.getUsuario(i).getCodigo());
				datos[1] = c_Usuario.getUsuario(i).getNombre();
				datos[2] = c_Usuario.getUsuario(i).getApellido();
				datos[3] = Integer.toString(c_Usuario.getUsuario(i).getIdTipoDocumento());
				datos[4] = c_Usuario.getUsuario(i).getDocumento();
				datos[5] = Integer.toString(c_Usuario.getUsuario(i).getArea());
				datos[6] = c_Usuario.getUsuario(i).getCorreo();
				datos[7] = c_Usuario.getUsuario(i).getTelefono();
				datos[8] = General.parsearDatetoString((c_Usuario.getUsuario(i).getFechaIngreso()));
				modelo.addRow(datos);
			}
			TableColumn column1 = null;
			column1 = table.getColumnModel().getColumn(0);
			column1.setPreferredWidth(60);
			TableColumn column2 = null;
			column2 = table.getColumnModel().getColumn(1);
			column2.setPreferredWidth(80);
			TableColumn column3 = null;
			column3 = table.getColumnModel().getColumn(2);
			column3.setPreferredWidth(60);
			TableColumn column4 = null;
			column4 = table.getColumnModel().getColumn(3);
			column4.setPreferredWidth(80);
			TableColumn column5 = null;
			column5 = table.getColumnModel().getColumn(4);
			column5.setPreferredWidth(60);
			TableColumn column6 = null;
			column6 = table.getColumnModel().getColumn(5);
			column6.setPreferredWidth(80);
			TableColumn column7 = null;
			column7 = table.getColumnModel().getColumn(6);
			column7.setPreferredWidth(200);
			TableColumn column8 = null;
			column8 = table.getColumnModel().getColumn(7);
			column8.setPreferredWidth(80);*/
		} catch (NullPointerException e2) {
			String mensaje = "";
			mensaje = e2.getMessage();
			JOptionPane.showMessageDialog(this, mensaje,"ERROR", JOptionPane.WARNING_MESSAGE);
		}
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e){ 
		
	}
}
