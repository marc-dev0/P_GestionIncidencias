package Controlador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.BE_Usuario;
public class UsuarioController {
	
	ArrayList<BE_Usuario> listUsuario = new ArrayList<BE_Usuario>();
	
	public UsuarioController(){
		listUsuario = new ArrayList<BE_Usuario>();
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = "15-04-1999";
		Date fechar = null;
		try {
			fechar = fmt.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registrarUsuario(new BE_Usuario(1, "Miguel", "Rojas Coraje", 1, "DNI", 1, "m.rojascoraje@outlook.com", "930632387", fechar,1));
	}
	
	public void registrarUsuario(BE_Usuario objUsuario){
		listUsuario.add(objUsuario);
	}

	public void modificarUsuario(int index, BE_Usuario objUsuarioBE){
		listUsuario.set(index, objUsuarioBE);
		
	}
	
	public void eliminarUsuario(BE_Usuario objUsuarioBE){
		listUsuario.remove(objUsuarioBE);
	}
	
	public int getPosUsuario(BE_Usuario objUsuarioBE){
		int pos = listUsuario.indexOf(objUsuarioBE);
		return pos;
	}
	
	public ArrayList<BE_Usuario> listAllUSuarios(){
		ArrayList<BE_Usuario> data = new ArrayList<BE_Usuario>();
		//data = dataPrueba();
		for(BE_Usuario u : listUsuario){
			data.add(u);
		}
		return data;
	}
	
 	public ArrayList<BE_Usuario> dataPrueba(){
 		ArrayList<BE_Usuario> aux = new ArrayList<BE_Usuario>();
 		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = "10-05-2015";
		Date fechaReal = null;
		try {
			fechaReal = fmt.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		BE_Usuario u= null;
 		int idTipoDocumento,codigo,area,estado;
		String apellidos,nombre,telefono,correo;
		String documento;
		Date fechaIngreso;
		Object[][] DATA = new Object[][]{
				{codigo = 1, nombre = "Miguel", apellidos = "Rojas Coraje", idTipoDocumento = 1, documento = "47413254", area = 1,
						correo = "m.rojascoraje@outlook.cok", telefono = "930632333", fechaIngreso = fechaReal, estado = 1},
				{codigo = 2, nombre = "Amy", apellidos = "Mejia Navarro", idTipoDocumento = 1, documento = "444212", area = 1,
						correo = "madadase@outlook.cok", telefono = "213213", fechaIngreso = fechaReal, estado = 0},
		};
		for(int i = 0; i < DATA.length; i++){
				JOptionPane.showMessageDialog(null, "" + DATA[i][0]);
				//aux.add((BE_Usuario) DATA[i][j]);
				u = new BE_Usuario();
				u.setCodigo((int) DATA[i][0]);
				u.setNombre((String) DATA[i][1]);
				u.setApellidos((String) DATA[i][2]);
				u.setIdTipoDocumento((int) DATA[i][3]);
				u.setDocumento((String) DATA[i][4]);
				u.setArea((int) DATA[i][5]);
				u.setCorreo((String) DATA[i][6]);
				u.setTelefono((String) DATA[i][7]);
				u.setFechaIngreso((Date) DATA[i][8]);
				u.setEstado((int) DATA[i][9]);
				
				aux.add(u);
		}
		return aux;
 	}
	public BE_Usuario getUsuarioxCodigo(int codigo){
		for(BE_Usuario u : listUsuario){
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}
	public void generarCodigo(){
		BE_Usuario u = new BE_Usuario();
			if(listUsuario.size()==0)
				u.setCodigo(u.getCodigo() + 1);
		JOptionPane.showMessageDialog(null, ""+ u.getCodigo());
	}
	
	public int sizeUsuario(){
		return listUsuario.size();
	}
	
	public BE_Usuario getUsuario(int index){
		return listUsuario.get(index);
	}

	
}
