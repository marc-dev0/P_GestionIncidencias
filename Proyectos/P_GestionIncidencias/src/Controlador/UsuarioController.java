package Controlador;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.BE_Usuario;
public class UsuarioController {
	
	ArrayList<BE_Usuario> listUsuario = new ArrayList<BE_Usuario>();
	
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
