package Controlador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		registrarUsuario(new BE_Usuario(1, "Miguel", "Rojas Coraje", 1, "DNI", 0, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(2, "Miguel", "Rojas Coraje", 1, "DNI", 2, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(3, "Miguel", "Rojas Coraje", 1, "DNI", 3, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(4, "Miguel", "Rojas Coraje", 1, "DNI", 4, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(5, "Miguel", "Rojas Coraje", 1, "DNI", 4, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(6, "Miguel", "Rojas Coraje", 1, "DNI", 2, "m.rojascoraje@outlook.com", "930632387", fechar,1));
		registrarUsuario(new BE_Usuario(7, "Miguel", "Rojas Coraje", 1, "DNI", 1, "m.rojascoraje@outlook.com", "930632387", fechar,1));
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
	public BE_Usuario getUsuarioxCodigo(int codigo){
		for(BE_Usuario u : listUsuario){
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}
	
	public int generarCodigo(){
			if(listUsuario.size()==0)
				return 1;
			else
				return listUsuario.get(sizeUsuario()-1).getCodigo()+1;
	}
	
	public int sizeUsuario(){
		return listUsuario.size();
	}
	
	public BE_Usuario getUsuario(int index){
		return listUsuario.get(index);
	}
	
	public int size(){
		return listUsuario.size();
	}
	//Region Listados
	
	public ArrayList<BE_Usuario> listarUsuario(){
		ArrayList<BE_Usuario> aux = new ArrayList<BE_Usuario>();
		for(BE_Usuario u : listUsuario){
			aux.add(u);
		}
		return aux;
	}
	
	
	public ArrayList<BE_Usuario> listarUsuarioxArea(int area){
		ArrayList<BE_Usuario> aux = new ArrayList<BE_Usuario>();
		for(BE_Usuario u : listUsuario)
			if(u.getArea() == area)
				aux.add(u);
		return aux;
	}
	
	public BE_Usuario listarUsuarioxCodigo(int codigo){
		for(BE_Usuario u : listUsuario)
			if(u.getCodigo() == codigo)
				return u;
		return null;
	}
	//EndRegion
}
