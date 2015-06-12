package internalFrame.Mantenimientos;


import Controlador.AreaController; 
import Controlador.EspecialistaController;
import Entidades.BE_Especialista;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Date;

import Utilitarios.General;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;

public class Especialista extends JInternalFrame implements ActionListener{
	private static Especialista instance =  null;
	private AreaController areaController = new AreaController();
	public static Especialista getInstance(){
		if(instance==null){
			instance = new Especialista();
		}
		return instance;
		
	}
	//Region variables de controles
	
	private JRadioButton rdbTodos;
	private JLabel lblEstado,lblArea, lblCodigo, lblNombre, lblApellidos, lblEspecialidad, lblAnexo, lblFechaDeIngreso, lblBCodigo;
	private JTextField txtCodigo,txtNombre, txtApellidos, txtEspecialidad, txtAnexo, txtBusquedaCodigo;
	private JComboBox cboEstado,cboBArea;
	private JButton btnRegistrar, btnEditar, btnEliminar, btnLimpiar,btnVolver, btnSalir;
	private DefaultTableModel modelo;
	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento, panelFiltro;
	private JScrollPane scrollPane;
	private JTable table;
	private JDateChooser dateChooser;

	//EndRegion
	
	
	private String estado="";
	private EspecialistaController EspecialistaController = new EspecialistaController();
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Especialista frame = new Especialista();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Especialista() {
		
		setFrameIcon(new ImageIcon(Especialista.class.getResource("/imagenesx16/especialista_16x16.png")));
		setClosable(true);
		setTitle("Especialista");
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
						table = new JTable();
						//table.setAutoCreateRowSorter(true);
						table.getTableHeader().setToolTipText("Click para alinear");
						table.setRowSelectionAllowed(false);
						table.setModel(modelo = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Codigo", "Nombre", "Apellidos", "Especialidad", "Anexo", "Fecha de Ingreso", "Estado"
							}
						));
						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						/*table.getColumnModel().getColumn(3).setMinWidth(0);
						table.getColumnModel().getColumn(3).setMaxWidth(0);*/
						table.getColumnModel().getColumn(6).setMinWidth(0);
						table.getColumnModel().getColumn(6).setMaxWidth(0);
						scrollPane.setViewportView(table);
					    
					}
				}
				{
					panelFiltro = new JPanel();
					panelFiltro.setBounds(12, 12, 261, 57);
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
						txtBusquedaCodigo.setBounds(75, 25, 105, 24);
						panelFiltro.add(txtBusquedaCodigo);
						txtBusquedaCodigo.setColumns(10);
						txtBusquedaCodigo.addActionListener(action);
						txtBusquedaCodigo.getDocument().addDocumentListener(new DocumentListener(){
							@Override
							public void removeUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(txtBusquedaCodigo.getText().isEmpty()){
									rdbTodos.setEnabled(true);
									//cboBArea.setEnabled(true);
								}
							}
							
							@Override
							public void insertUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(!rdbTodos.isSelected() || rdbTodos.isSelected()){
									rdbTodos.setEnabled(false);
									rdbTodos.setSelected(false);
									//cboBArea.setEnabled(false);
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
						rdbTodos.setBounds(188, 24, 70, 23);
						panelFiltro.add(rdbTodos);
						
					}
					{
						/*
						lblArea = new JLabel("Area:");
						lblArea.setBounds(204, 32, 44, 13);
						panelFiltro.add(lblArea);
					}
					{
						cboBArea = new JComboBox();
						cboBArea.setBounds(254, 27, 130, 24);
						cboBArea.setModel(new DefaultComboBoxModel(new String[]{
								"Sistemas","Tu vieja"
						}));
						panelFiltro.add(cboBArea);
						cboBArea.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								fillTablexArea();
							}
						});
						*/
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
					lblEspecialidad = new JLabel("Especialidad:");
					lblEspecialidad.setBounds(12, 114, 107, 15);
					panelMantenimiento.add(lblEspecialidad);
				}
				{
					txtEspecialidad = new JTextField();
					txtEspecialidad.setColumns(10);
					txtEspecialidad.setBounds(124, 106, 168, 24);
					panelMantenimiento.add(txtEspecialidad);
				}
				{
					lblAnexo = new JLabel("Anexo:");
					lblAnexo.setBounds(12, 146, 60, 15);
					panelMantenimiento.add(lblAnexo);
				}
				{
					txtAnexo = new JTextField();
					txtAnexo.setBounds(88, 141, 168, 24);
					panelMantenimiento.add(txtAnexo);
				}
				{
					lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
					lblFechaDeIngreso.setBounds(12, 176, 130, 15);
					panelMantenimiento.add(lblFechaDeIngreso);
				}
				{
					dateChooser = new JDateChooser();
					dateChooser.setBounds(146, 172, 155, 24);
					panelMantenimiento.add(dateChooser);
				}
				{
					lblEstado = new JLabel("Estado:");
					lblEstado.setBounds(12, 212, 70, 15);
					panelMantenimiento.add(lblEstado);
				}
				{
					cboEstado = new JComboBox();
					cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "No Activo"}));
					cboEstado.setBounds(124, 207, 155, 24);
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
				int codigo = EspecialistaController.generarCodigo();
				txtCodigo.setText(""+ codigo);
				estado = "Guardar";
				/*for(String s : areaController.lista()){
					cboArea.addItem(s);
				}*/
				
			}
			else if(tabbedPane.getSelectedIndex() == 1){
				
				if (estado == "Guardar"){
				BE_Especialista objEspecialista = new BE_Especialista();
				EspecialistaController.registrarEspecialista(objEspecialista);
				setDataEspecialista(objEspecialista);
				addRowInserted();
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado ="";
				}
				else if(estado == "Editar"){
					
					int codigo = Integer.parseInt(txtCodigo.getText());
					BE_Especialista u = EspecialistaController.getEspecialistaxCodigo(codigo);
					int pos = EspecialistaController.getPosEspecialista(u);
					setDataEspecialista(u);
					EspecialistaController.modificarEspecialista(pos, u);
					editRowSelected(u);
					General.changePanel(tabbedPane, false);
					General.limpiar(panelMantenimiento);
					habiltarBotones(true);
					estado ="";
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
					BE_Especialista objEspecialistaBE = EspecialistaController.getEspecialistaxCodigo(codigo);
					EspecialistaController.eliminarEspecialista(objEspecialistaBE);
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
			if(EspecialistaController.listAllEspecialistas().isEmpty()){
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
				BE_Especialista u = EspecialistaController.listarEspecialistaxCodigo(codigo);
				Object[] data = new Object[]{
						u.getCodigo(), u.getNombres(),u.getApellidos(),
						u.getEspecialidad(), u.getAnexo(), 
						General.parsearDatetoString(u.getFechaIngreso()), u.getEstado()
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
		txtEspecialidad.setText(""+ table.getValueAt(r, 3));
		txtAnexo.setText(""+ table.getValueAt(r, 4));
		dateChooser.setDate(General.parseStringtoDate((String) table.getValueAt(r, 5)));
		cboEstado.setSelectedIndex((int) table.getValueAt(r, 6));
	}
	void addRowInserted(){
		modelo.addRow(new Object[]{
				leerCodigo(), leerNombre(), leerApellidos(), leerEspecialidad(), leerAnexo(), leerFecha(), leerEstado()
			});
	}
	
	void editRowSelected(BE_Especialista u){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setValueAt(u.getCodigo(), table.getSelectedRow(),0);
		model.setValueAt(u.getNombres(), table.getSelectedRow(), 1);
		model.setValueAt(u.getApellidos(), table.getSelectedRow(), 2);
		model.setValueAt(u.getEspecialidad(), table.getSelectedRow(), 3);
		model.setValueAt(u.getAnexo(), table.getSelectedRow(), 4);
		model.setValueAt(General.parsearDatetoString(u.getFechaIngreso()), table.getSelectedRow(), 5);
		model.setValueAt(u.getEstado(), table.getSelectedRow(), 6);
	}
	
	void fillTable(){
		modelo.setRowCount(0);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(int i = 0; i < EspecialistaController.size(); i++){
			BE_Especialista u = EspecialistaController.getEspecialista(i);
			Object[] data = new Object[]{
					u.getCodigo(), u.getNombres(),u.getApellidos(),
					u.getEspecialidad(), u.getAnexo(), 
					General.parsearDatetoString(u.getFechaIngreso()), u.getEstado()
			};
			model.addRow(data);
		}
	}
	
	/*void fillTablexArea(){
		modelo.setRowCount(0);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int area = cboBArea.getSelectedIndex();
		ArrayList<BE_Especialista> aux = EspecialistaController.listarEspecialistaxArea(area);
		for(int i=0; i< aux.size(); i++){
			BE_Especialista u = aux.get(i);
			Object[] data = new Object[]{
					u.getCodigo(), u.getNombre(), u.getApellido(),
					u.getIdTipoDocumento(), u.getDocumento(), u.getArea(),
					u.getCorreo(), u.getTelefono(), General.parsearDatetoString(u.getFechaIngreso()),
					u.getEstado()
			};
			model.addRow(data);
		}
		
	}*/
	
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
	
	String leerEspecialidad(){
		return txtEspecialidad.getText();
	}
	
	String leerAnexo(){
		return txtAnexo.getText();
	}
	
	int leerEstado(){
		return cboEstado.getSelectedIndex();
	}
	
	String leerFecha(){
		Date fecha = dateChooser.getDate();
		return General.parsearDatetoString(fecha);
	}
	
	protected void habiltarBotones(boolean estado){
		JButton[] botones = {btnLimpiar,btnEditar,btnEliminar,btnVolver};
		General.habilitar(botones, estado);
	}
	
	void setDataEspecialista(BE_Especialista objEspecialista){
		objEspecialista.setCodigo(leerCodigo());
		objEspecialista.setNombres(leerNombre());
		objEspecialista.setApellidos(leerApellidos());
		objEspecialista.setEspecialidad(leerEspecialidad());
		objEspecialista.setAnexo(leerAnexo());
		objEspecialista.setFechaIngreso(General.parseStringtoDate(leerFecha()));
		objEspecialista.setEstado(leerEstado());
		
	}
	//EndRegion
	
}

