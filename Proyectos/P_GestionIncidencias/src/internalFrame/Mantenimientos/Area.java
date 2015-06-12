package internalFrame.Mantenimientos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.LimitExceededException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box.Filler;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Utilitarios.General;

import com.toedter.calendar.JDateChooser;

import Controlador.AreaController;
import Entidades.BE_Area;
import Entidades.BE_Usuario;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Area extends JInternalFrame implements ActionListener{

	private static Area instance = null;
	private String estado ="";
	private AreaController AreaController = new AreaController();
	public static Area getInstance(){

		if(instance == null)
			instance = new Area();
		return instance;
	}
	
	//Region variables de controles
	private JTextArea txtDescripcion;
	private JRadioButton rdbTodos;
	private JLabel lblEstado, lblCodigo, lblNombre, lblDescripcion,lblNombreCorto, lblNombreLargo, lblBCodigo;
	private JTextField txtCodigo,txtNombre, txtNombreLargo, txtNombreCorto, txtBusquedaCodigo;
	private JComboBox cboEstado;
	private JButton btnRegistrar, btnEditar, btnEliminar, btnLimpiar,btnVolver, btnSalir;
	private DefaultTableModel modelo;
	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento, panelFiltro;
	private JScrollPane scrollPane, scrollPane_1;
	private JTable table;

	//EndRegion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Area frame = new Area();
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
	public Area() {
		setFrameIcon(new ImageIcon(Area.class.getResource("/imagenes_x16/area_16.png")));
		setClosable(true);
		setTitle("Area");
		setBounds(100, 100, 1300, 372);
		getContentPane().setLayout(null);
		
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 1158, 335);
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
								"Codigo", "Nombre", "Descripción", "Nombre Corto", "Nombre Largo", "Estado"
							}
						));
						
						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						table.getColumnModel().getColumn(5).setMinWidth(0);
						table.getColumnModel().getColumn(5).setMaxWidth(0);
						/*table.getColumnModel().getColumn(9).setMinWidth(0);
						table.getColumnModel().getColumn(9).setMaxWidth(0);*/
						scrollPane.setViewportView(table);
					    
					}
				}
				{
					panelFiltro = new JPanel();
					panelFiltro.setBounds(12, 12, 934, 57);
					panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtro de búsqueda"));
					panelListado.add(panelFiltro);
					panelFiltro.setLayout(null);
					{
						lblBCodigo = new JLabel("Código:");
						lblBCodigo.setBounds(12, 32, 70, 15);
						panelFiltro.add(lblBCodigo);
					}
					{
						txtBusquedaCodigo = new JTextField();
						txtBusquedaCodigo.setBounds(84, 31, 105, 17);
						panelFiltro.add(txtBusquedaCodigo);
						txtBusquedaCodigo.setColumns(10);
						txtBusquedaCodigo.addActionListener(action);
						txtBusquedaCodigo.getDocument().addDocumentListener(new DocumentListener(){

							@Override
							public void insertUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(!rdbTodos.isSelected() || rdbTodos.isSelected()){
									rdbTodos.setEnabled(false);
									rdbTodos.setSelected(false);
								}
							}

							@Override
							public void removeUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								if(txtBusquedaCodigo.getText().isEmpty()){
									rdbTodos.setEnabled(true);
								}
							}

							@Override
							public void changedUpdate(DocumentEvent e) {
								// TODO Auto-generated method stub
								
							}
							
					});
					{
						rdbTodos = new JRadioButton("Todos");
						rdbTodos.addActionListener(this);
						rdbTodos.setBounds(197, 28, 70, 23);
						panelFiltro.add(rdbTodos);
						
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
					lblNombre.setBounds(12, 42, 60, 20);
					panelMantenimiento.add(lblNombre);
				}
				{
					lblDescripcion = new JLabel("Descripción:");
					lblDescripcion.setBounds(12, 72, 100, 20);
					panelMantenimiento.add(lblDescripcion);
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
					lblNombreCorto = new JLabel("Nombre Corto:");
					lblNombreCorto.setBounds(12, 185, 111, 20);
					panelMantenimiento.add(lblNombreCorto);
				}
				{
					lblNombreLargo = new JLabel("Nombre Largo:");
					lblNombreLargo.setBounds(12, 215, 124, 20);
					panelMantenimiento.add(lblNombreLargo);
				}
				{
					txtNombreCorto = new JTextField();
					txtNombreCorto.setColumns(10);
					txtNombreCorto.setBounds(136, 183, 201, 24);
					panelMantenimiento.add(txtNombreCorto);
				}
				{
					lblEstado = new JLabel("Estado:");
					lblEstado.setBounds(12, 245, 70, 20);
					panelMantenimiento.add(lblEstado);
				}
				{
					cboEstado = new JComboBox();
					cboEstado.setModel(new DefaultComboBoxModel(new String[]{
							"Activo","Inactivo"
					}));
					cboEstado.setBounds(80, 243, 155, 24);
					panelMantenimiento.add(cboEstado);
				}
				{
					txtNombreLargo = new JTextField();
					txtNombreLargo.setColumns(10);
					txtNombreLargo.setBounds(136, 213, 201, 24);
					panelMantenimiento.add(txtNombreLargo);
				}
				{
					scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(110, 72, 227, 101);
					panelMantenimiento.add(scrollPane_1);
					{
						txtDescripcion = new JTextArea();
						scrollPane_1.setViewportView(txtDescripcion);
					}
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
		General.limpiar(panelMantenimiento);
		habiltarBotones(true);
		General.changePanel(tabbedPane, false);
		
	}
	
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		if(tabbedPane.getSelectedIndex()==0){
			General.changePanel(tabbedPane,true);
			habiltarBotones(true);
			txtCodigo.setText("" + AreaController.generarCodigo());
			txtNombre.requestFocus();
			estado = "Guardar";
			//JOptionPane.showMessageDialog(null, "" + AreaController.size());
		}else if(tabbedPane.getSelectedIndex() ==1){
			if(estado == "Guardar"){
				BE_Area a = new BE_Area();
				setDataArea(a);
				addRowInserted(a);
				AreaController.agregarArea(a);
				//JOptionPane.showMessageDialog(null, "" + AreaController.size());
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado ="";
			}else if(estado =="Editar"){
				int codigo = Integer.parseInt(txtCodigo.getText());
				BE_Area a = AreaController.buscarAreaxCodigo(codigo);
				int pos = AreaController.getPosArea(a);
				setDataArea(a);
				AreaController.modificarArea(pos, a);
				editRowSelected(a);
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado = "";
			}
		}
	}
	
	private void editRowSelected(BE_Area a) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int row = table.getSelectedRow();
		model.setValueAt(a.getCodigo(), row, 0);
		model.setValueAt(a.getNombre(), row, 1);
		model.setValueAt(a.getDescripcion(), row, 2);
		model.setValueAt(a.getNombreCorto(), row, 3);
		model.setValueAt(a.getNombreLargo(), row, 4);
		model.setValueAt(a.getEstado(), row, 5);
	}

	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		General.limpiar(panelMantenimiento);
	}
		
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
		
	}
		
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			int rowcount = table.getModel().getRowCount(), rowSelected = table.getSelectedRow();
			if(General.validateJTableisEmpty(rowcount, rowSelected))
				return;
			else{
				if(JOptionPane.showConfirmDialog(null, "Desea eliminar el registro","Adveretencia",
												 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 
												 JOptionPane.YES_OPTION)
				{
					BE_Area a = new BE_Area();
					int codigo = (int) table.getValueAt(rowSelected, 0);
					a = AreaController.buscarAreaxCodigo(codigo);
					AreaController.eliminarArea(a);
					modelo.removeRow(rowSelected);
				}else
					return;
			}

		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}
	
	protected void do_btnEditar_actionPerformed(ActionEvent e) {
		estado = "Editar";
		int rowSelected = table.getSelectedRow(), rowCount = table.getModel().getRowCount();
		if(General.validateJTableisEmpty(rowCount, rowSelected)){
			return;
		}else{
			General.changePanel(tabbedPane, true);
			habiltarBotones(true);
			carryDataToControls(rowSelected);
			txtNombre.requestFocus();
		}
	}
		
	protected void do_rdbTodos_actionPerformed(ActionEvent e) {
		if(rdbTodos.isSelected()){
			if(AreaController.size()== 0){
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
				if(txtBusquedaCodigo.getText().isEmpty()){
					fillTable();
					return;
				}else{
					int codigo = Integer.parseInt(txtBusquedaCodigo.getText());
					BE_Area a = new BE_Area();
					a = AreaController.buscarAreaxCodigo(codigo);
					Object[] data = new Object[]{
						a.getCodigo(), a.getNombre(), a.getDescripcion(), a.getNombreCorto(), a.getNombreLargo(),a.getEstado()
					};
					modelo.addRow(data);
				}
				
			}
		};
	
	
	protected void habiltarBotones(boolean estado){
		JButton[] botones = {btnLimpiar,btnEditar,btnEliminar,btnVolver};
		General.habilitar(botones, estado);
	}
	
	//Region JTable()
	void addRowInserted(BE_Area a){
		Object[] data = new Object[]{
			a.getCodigo(), a.getNombre(), a.getDescripcion(), a.getNombreCorto(),
			a.getNombreLargo(), a.getEstado()
		};
		modelo.addRow(data);
	}
	
	void carryDataToControls(int r){
		txtCodigo.setText(""+ modelo.getValueAt(r, 0));
		txtNombre.setText(""+ modelo.getValueAt(r, 1));
		txtDescripcion.setText("" + modelo.getValueAt(r, 2));
		txtNombreCorto.setText("" + modelo.getValueAt(r, 3));
		txtNombreLargo.setText("" + modelo.getValueAt(r, 4));
		cboEstado.setSelectedIndex((int) modelo.getValueAt(r, 5));
	}
	
	void fillTable(){
		modelo.setRowCount(0);
		for(int i=0; i < AreaController.size(); i++){
			BE_Area a = AreaController.getArea(i);
			Object[] data = new Object[]{
				a.getCodigo(), a.getNombre(), a.getDescripcion(), a.getNombreCorto(), a.getNombreLargo(),
				a.getEstado()
			};
			modelo.addRow(data);
		}
	}
	//EndRegion
	
	//Region Lectura de controles
	public int leerCodigo(){
		return Integer.parseInt(txtCodigo.getText());
	}
	
	public String leerNombre(){
		return txtNombre.getText();
	}
	
	public String leerDescripcion(){
		return txtDescripcion.getText();
	}
	
	public String leerNombreCorto(){
		return txtNombreCorto.getText();
	}
	
	public String leerNombreLargo(){
		return txtNombreLargo.getText();
	}
	
	public int leerEstado(){
		return cboEstado.getSelectedIndex();
	}
	
	void setDataArea(BE_Area a){
		a.setCodigo(Integer.parseInt(txtCodigo.getText()));
		a.setNombre(txtNombre.getText());
		a.setDescripcion(txtDescripcion.getText());
		a.setNombreCorto(txtNombreCorto.getText());
		a.setNombreLargo(txtNombreLargo.getText());
		a.setEstado(cboEstado.getSelectedIndex());
		
	}
	
	//EndRegion
}
		
