package internalFrame.Mantenimientos;


import Controlador.AreaController; 
import Controlador.TipoDocumentoController;
import Controlador.UsuarioController;
import Entidades.BE_Usuario;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import Utilitarios.General;
import Utilitarios.ToolTipWrapper;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.beans.FeatureDescriptor;

import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import java.awt.event.MouseListener;

public class Usuario extends JInternalFrame implements ActionListener, MouseListener{
	private static Usuario instance =  null;
	private String estado="", sender="";
	private AreaController areaController = new AreaController();
	private TipoDocumentoController TDocumentoController = new TipoDocumentoController(); 
	public static Usuario getInstance(){
		if(instance==null){
			instance = new Usuario();
		}
		return instance;
		
	}
	//Region variables de controles
	
	private JRadioButton rdbTodos;
	private JLabel lblEstado,lblArea, lblCodigo, lblNombre, lblApellidos,lblTipoDocumento, lblDni, lblarea,
				   lblCorreo, lblTelefono, lblFechaDeIngreso, lblBCodigo;
	private JTextField txtCodigo,txtNombre, txtApellidos, txtDNI, txtCorreo, txtTelefono, txtBusquedaCodigo;
	private JComboBox<String> cboEstado, cboTipoDocumento, cboArea,cboBArea;
	private JButton btnRegistrar, btnEditar, btnEliminar, btnLimpiar,btnVolver, btnSalir;
	private DefaultTableModel modelo;
	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento, panelFiltro;
	private JScrollPane scrollPane;
	private JTable table;
	private JDateChooser dateChooser;

	//EndRegion
	
	private UsuarioController usuarioController = new UsuarioController();
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
		setFrameIcon(new ImageIcon(Usuario.class.getResource("/imagenesx16/user_16.png")));
		
		setClosable(true);
		setTitle("Usuario");
		setBounds(100, 100, 1300, 404);
		getContentPane().setLayout(null);
		
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 1158, 372);
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
						table = new JTable(){
							private static final long serialVersionUID= 1L;
							public boolean isCellEditable(int row, int column){
								return false;
							};
						};
						table.addMouseListener(this);
						//table.setAutoCreateRowSorter(true);
						table.getTableHeader().setToolTipText("Click para alinear");
						table.setRowSelectionAllowed(false);
						table.setModel(modelo = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Codigo", "Nombre", "Apellidos", "idTipoDocumento", "Doc. de Identidad", 
								"codigo_Area", "Area", "Correo", "Tel\u00E9fono", "Fecha de Ingreso", 
								"Estado"
							}
						));
						
						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						table.getColumnModel().getColumn(3).setMinWidth(0);
						table.getColumnModel().getColumn(3).setMaxWidth(0);
						table.getColumnModel().getColumn(5).setMinWidth(0);
						table.getColumnModel().getColumn(5).setMaxWidth(0);
						table.getColumnModel().getColumn(10).setMinWidth(0);
						table.getColumnModel().getColumn(10).setMaxWidth(0);
						table.getTableHeader().setResizingAllowed(false);
						table.getTableHeader().setReorderingAllowed(false);
						scrollPane.setViewportView(table);
					    
					}
				}
				{
					panelFiltro = new JPanel();
					panelFiltro.setBounds(12, 12, 470, 57);
					panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtro de búsqueda"));
					panelListado.add(panelFiltro);
					panelFiltro.setLayout(null);
					{
						lblBCodigo = new JLabel("Código:");
						lblBCodigo.setBounds(12, 32, 63, 15);
						panelFiltro.add(lblBCodigo);
					}
					{
						txtBusquedaCodigo = new JTextField();
						txtBusquedaCodigo.setBounds(84, 25, 105, 24);
						panelFiltro.add(txtBusquedaCodigo);
						txtBusquedaCodigo.setColumns(10);
						txtBusquedaCodigo.addActionListener(action);
						txtBusquedaCodigo.getDocument().addDocumentListener(new DocumentListener(){
							@Override
							public void removeUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(txtBusquedaCodigo.getText().isEmpty()){
									rdbTodos.setEnabled(true);
									cboBArea.setEnabled(true);
								}
							}
							
							@Override
							public void insertUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(!rdbTodos.isSelected() || rdbTodos.isSelected()){
									rdbTodos.setEnabled(false);
									rdbTodos.setSelected(false);
									cboBArea.setEnabled(false);
								}
							}
							
							@Override
							public void changedUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
					}
					{
						rdbTodos = new JRadioButton("Todos");
						rdbTodos.addActionListener(this);
						rdbTodos.setBounds(395, 28, 70, 23);
						panelFiltro.add(rdbTodos);
						
					}
					{
						lblArea = new JLabel("Area:");
						lblArea.setBounds(204, 32, 44, 13);
						panelFiltro.add(lblArea);
					}
					{
						cboBArea = new JComboBox();
					
						cboBArea.setBounds(254, 27, 130, 24);
						cboBArea.setModel(new DefaultComboBoxModel(areaController.lista()));
						panelFiltro.add(cboBArea);
						
						cboBArea.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								fillTablexArea();
							}
						});
						
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
					txtCodigo.setEditable(false);
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
					cboTipoDocumento.setModel(new DefaultComboBoxModel(TDocumentoController.lista()));
					cboTipoDocumento.setBounds(136, 101, 300, 24);
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
					lblTelefono = new JLabel("Teléfono:");
					lblTelefono.setBounds(12, 230, 78, 15);
					panelMantenimiento.add(lblTelefono);
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
					cboArea.setModel(new DefaultComboBoxModel(areaController.lista()));
					cboArea.setBounds(61, 163, 196, 24);
					panelMantenimiento.add(cboArea);
				}
				{
					dateChooser = new JDateChooser(new Date());
					dateChooser.setBounds(146, 256, 155, 24);
					panelMantenimiento.add(dateChooser);
				}
				{
					lblEstado = new JLabel("Estado:");
					lblEstado.setBounds(12, 296, 70, 15);
					panelMantenimiento.add(lblEstado);
				}
				{
					cboEstado = new JComboBox();
					cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "No Activo"}));
					cboEstado.setBounds(124, 291, 155, 24);
					panelMantenimiento.add(cboEstado);
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbTodos) {
			do_rdbTodos_actionPerformed(e);
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
		General.limpiar(panelMantenimiento);
		txtBusquedaCodigo.requestFocus();
		estado = "";
	}
	
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
	
		try {
			
			if(tabbedPane.getSelectedIndex() == 0){
				
				General.changePanel(tabbedPane, true);
				habiltarBotones(true);
				txtNombre.requestFocus();
				int codigo = usuarioController.generarCodigo();
				txtCodigo.setText(""+ codigo);
				estado = "Guardar";
				
				if(cboTipoDocumento.getItemCount() == 0)
					for(String s : TDocumentoController.lista()){
						cboTipoDocumento.addItem(s);
					}	
		
				if(cboArea.getItemCount() == 0)
					for(String s : areaController.lista()){
					cboArea.addItem(s);
					}
				
				dateChooser.setDate(new Date());
			}
			
			else if(tabbedPane.getSelectedIndex() == 1){
				
				if (estado == "Guardar"){
				BE_Usuario objUsuario = new BE_Usuario();
				setDataUsuario(objUsuario);
				usuarioController.registrarUsuario(objUsuario);
				usuarioController.guardarUsuario(objUsuario);
				addRowInserted();
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado ="";
				sender = "Guardando";
				}
				else if(estado == "Editar"){
					
					int codigo = Integer.parseInt(txtCodigo.getText());
					BE_Usuario u = usuarioController.getUsuarioxCodigo(codigo);
					int pos = usuarioController.getPosUsuario(u);
					setDataUsuario(u);
					usuarioController.modificarUsuario(pos, u);
					usuarioController.guardarUsuario();
					editRowSelected(u);
					General.changePanel(tabbedPane, false);
					General.limpiar(panelMantenimiento);
					habiltarBotones(true);
					estado ="";
					sender = "Editando";
				}
			}
			//JButton[] botones = {btnRegistrar,btnVolver};
			//General.cambiarTitulo(botones);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage().toString());
		}
		
	}

	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		General.limpiar(panelMantenimiento);
		txtCodigo.requestFocus();
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
		instance = null;
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			int r = table.getModel().getRowCount();
			if(General.validateJTableisEmpty(r,r))
				return;
			else{
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar?", "advertencia", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
				if(respuesta == JOptionPane.YES_OPTION){
					int row = table.getSelectedRow();
					int codigo = (int) table.getValueAt(row, 0);
					BE_Usuario objUsuarioBE = usuarioController.getUsuarioxCodigo(codigo);
					usuarioController.eliminarUsuario(objUsuarioBE);
					modelo.removeRow(row);
				}else
					return;
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		
		}
	}
	
	protected void do_btnEditar_actionPerformed(ActionEvent e) {
		estado = "Editar";
		int r = table.getSelectedRow(), row = table.getModel().getRowCount();
		
		if(General.validateJTableisEmpty(row,r))
			return;
		else{
			try {
				carryDataToControls(r);
				General.changePanel(tabbedPane, true);
				habiltarBotones(true);
				txtNombre.requestFocus();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	protected void do_rdbTodos_actionPerformed(ActionEvent e) {
		if(rdbTodos.isSelected()){
			if(usuarioController.listAllUSuarios().isEmpty()){
				JOptionPane.showMessageDialog(null, "No hay data");
				return;
			}else{
			
				fillTable();
				return;
			}
		}
	}
	
	Action action = new AbstractAction(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			modelo.setRowCount(0);
			if(txtBusquedaCodigo.getText().isEmpty())
			{
				fillTable();
				return;
			}else{
			DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int codigo = Integer.parseInt(txtBusquedaCodigo.getText());
				BE_Usuario u = usuarioController.listarUsuarioxCodigo(codigo);
				Object[] data = new Object[]{
						u.getCodigo(), u.getNombre(),u.getApellido(),
						u.getIdTipoDocumento(), u.getDocumento(), u.getCodigoArea(),u.getArea(),
						u.getCorreo(), u.getTelefono(), General.parsearDatetoString(u.getFechaIngreso()),
						u.getEstado()
				};
					model.addRow(data);
			}
		}
	};
	
	//Region Metodos para JTable
	
	void carryDataToControls(int r) {
		txtCodigo.setText(""+ table.getValueAt(r, 0));
		txtNombre.setText(""+ table.getValueAt(r, 1));
		txtApellidos.setText(""+ table.getValueAt(r,2));
		int codigo = (int) table.getValueAt(r, 3);
		cboTipoDocumento.setSelectedIndex(codigo);
		txtDNI.setText(""+ table.getValueAt(r, 4));
		cboArea.setSelectedIndex((int) table.getValueAt(r, 5));
		txtCorreo.setText("" + table.getValueAt(r,7));
		txtTelefono.setText(""+ table.getValueAt(r, 8));
		dateChooser.setDate(General.parseStringtoDate((String) table.getValueAt(r, 9)));
		cboEstado.setSelectedIndex((int) table.getValueAt(r, 10));
	}
	
	void addRowInserted(){
		modelo.addRow(new Object[]{
				leerCodigo(), leerNombre(), leerApellidos(), leerComboTipoDocumento(), leerDocumento(),
				leerCodigoArea(),leerArea(), leerCorreo(), leerTelefono(), leerFecha(), leerEstado()
			});
	}
	
	void editRowSelected(BE_Usuario u){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	
		model.setValueAt(u.getCodigo(), table.getSelectedRow(),0);
		model.setValueAt(u.getNombre(), table.getSelectedRow(), 1);
		model.setValueAt(u.getApellido(), table.getSelectedRow(), 2);
		model.setValueAt(u.getIdTipoDocumento(), table.getSelectedRow(), 3);
		model.setValueAt(u.getDocumento(), table.getSelectedRow(), 4);
		model.setValueAt(u.getCodigoArea(), table.getSelectedRow(), 5);
		model.setValueAt(u.getArea(), table.getSelectedRow(), 6);
		model.setValueAt(u.getCorreo(), table.getSelectedRow(), 7);
		model.setValueAt(u.getTelefono(), table.getSelectedRow(), 8);
		model.setValueAt(General.parsearDatetoString(u.getFechaIngreso()), table.getSelectedRow(), 9);
		model.setValueAt(u.getEstado(), table.getSelectedRow(), 10);
	}
	
	void fillTable(){
		modelo.setRowCount(0);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(int i = 0; i < usuarioController.size(); i++){
			BE_Usuario u = usuarioController.getUsuario(i);
			Object[] data = new Object[]{
					u.getCodigo(), u.getNombre(), u.getApellido(),
					u.getIdTipoDocumento(), u.getDocumento(), u.getCodigoArea(), u.getArea(),
					u.getCorreo(), u.getTelefono(), General.parsearDatetoString(u.getFechaIngreso()),
					u.getEstado()
			};
			model.addRow(data);
		}
	}
	
	void fillTablexArea(){
		modelo.setRowCount(0);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int area = cboBArea.getSelectedIndex();
		ArrayList<BE_Usuario> aux = usuarioController.listarUsuarioxArea(area);
		for(int i=0; i< aux.size(); i++){
			BE_Usuario u = aux.get(i);
			Object[] data = new Object[]{
					u.getCodigo(), u.getNombre(), u.getApellido(),
					u.getIdTipoDocumento(), u.getDocumento(), u.getCodigoArea(), u.getArea(),
					u.getCorreo(), u.getTelefono(), General.parsearDatetoString(u.getFechaIngreso()),
					u.getEstado()
			};
			model.addRow(data);
		}
		
	}
	
	//EndRegion
    
	//Region Metodos para obtener data de los controles
	int leerCodigo(){
		return Integer.parseInt(txtCodigo.getText());
	}
	
	String leerNombre(){
		return txtNombre.getText();
	}
	
	String leerApellidos(){
		return txtApellidos.getText();
	}
	
	int leerComboTipoDocumento(){
		return cboTipoDocumento.getSelectedIndex();
	}
	
	String leerDocumento(){
		return txtDNI.getText();
	}
	
	String leerCorreo(){
		return txtCorreo.getText();
	}
	
	int leerCodigoArea(){
		return cboArea.getSelectedIndex();
	}
	
	String leerArea(){
		return cboArea.getSelectedItem().toString();
	}
	
	String leerTelefono(){
		return txtTelefono.getText();
	}
	
	int leerEstado(){
		return cboEstado.getSelectedIndex();
	}
	
	String leerFecha(){
		Date fecha = dateChooser.getDate();
		return General.parsearDatetoString(fecha);
	}
	
	void setDataUsuario(BE_Usuario objUsuario){
		objUsuario.setCodigo(leerCodigo());
		objUsuario.setNombre(leerNombre());
		objUsuario.setApellidos(leerApellidos());
		objUsuario.setIdTipoDocumento(leerComboTipoDocumento());
		objUsuario.setDocumento(leerDocumento());
		objUsuario.setCorreo(leerCorreo());
		objUsuario.setCodigoArea(leerCodigoArea());
		objUsuario.setArea(leerArea());
		objUsuario.setTelefono(leerTelefono());
		objUsuario.setEstado(leerEstado());
		objUsuario.setFechaIngreso(General.parseStringtoDate(leerFecha()));
	}
	
	//EndRegion
	protected void habiltarBotones(boolean estado){
		JButton[] botones = {btnLimpiar,btnEditar,btnEliminar,btnVolver};
		General.habilitar(botones, estado);
	}
	
	
//	protected void do_btnSend_actionPerformed(ActionEvent e) {
//		String nombre = (String) table.getValueAt(table.getSelectedRow(), 1);
//		int codigo = (int) table.getValueAt(table.getSelectedRow(), 0);
//		
//		
//		if(sender.equals("Editando")){
//			Incidencia.cboMUsuario.removeAllItems();
//			for(String s : usuarioController.lista()){
//				Incidencia.cboMUsuario.addItem(s);
//			}
//			sender = "";
//		}else{
//			
//			verifica(codigo, nombre);
//			if(verifica(codigo, nombre))
//			{
//				if(JOptionPane.showConfirmDialog(null, "El usuario seleccionado ya existe. ¿Desea volver al formulario"
//						+ "de incidencias?",  "Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
//					this.dispose();
//					return;
//				}else{
//					return;
//				}
//				
//			}else{
//				Incidencia.cboMUsuario.addItem(nombre);
//				Incidencia.cboMUsuario.setSelectedItem(nombre);
//				Incidencia.txtPrueba.setText(txtBusquedaCodigo.getText());
//				this.dispose();
//			}
//		}
//	}
	
	private boolean verifica(int codigo, String nombre){
		for(int i = 0; i < Incidencia.cboMUsuario.getItemCount(); i++){
			Object item = Incidencia.cboMUsuario.getItemAt(i);
			if(item.equals(nombre)){
				
				int cod = i +1;
				JOptionPane.showMessageDialog(null, "" + cod);
				if(cod == codigo){
					return true;
				}
			}
		}
		return false;
	}
	public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount() ==2)
		{
			String nombre = (String) table.getValueAt(table.getSelectedRow(), 1);
			int codigo = (int) table.getValueAt(table.getSelectedRow(), 0);
			
			if(sender.equals("Editando")){
				Incidencia.cboMUsuario.removeAllItems();
				for(String s : usuarioController.lista()){
					Incidencia.cboMUsuario.addItem(s);
				}
				sender = "";
				
			}else{
				
				verifica(codigo, nombre);
				if(verifica(codigo, nombre))
				{
					if(JOptionPane.showConfirmDialog(null, "El usuario seleccionado ya existe. ¿Desea volver al formulario"
							+ "de incidencias?",  "Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
						this.dispose();
						return;
					}else{
						return;
					}
					
				}else{
					Incidencia.cboMUsuario.addItem(nombre);
					Incidencia.cboMUsuario.setSelectedItem(nombre);
					//Incidencia.txtPrueba.setText(txtBusquedaCodigo.getText());
					this.dispose();
				}
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == table) {
			do_table_mousePressed(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void do_table_mousePressed(MouseEvent e) {
	}
}

