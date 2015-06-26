package Controlador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import Utilitarios.General;
import Entidades.BE_Usuario;

public class UsuarioController {
	
	ArrayList<BE_Usuario> arrayUsuario = new ArrayList<BE_Usuario>();
	
	public UsuarioController(){
		arrayUsuario = new ArrayList<BE_Usuario>();
		
		cargar();
		/*SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
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
		registrarUsuario(new BE_Usuario(7, "Miguel", "Rojas Coraje", 1, "DNI", 1, "m.rojascoraje@outlook.com", "930632387", fechar,1));*/
	}
	
	//Region Mantenimientos
	
	public void registrarUsuario(BE_Usuario objUsuario){
		arrayUsuario.add(objUsuario);
	}

	public void modificarUsuario(int index, BE_Usuario objUsuarioBE){
		arrayUsuario.set(index, objUsuarioBE);
		
	}
	
	public void eliminarUsuario(BE_Usuario objUsuarioBE){
		arrayUsuario.remove(objUsuarioBE);
	}
	
	public int generarCodigo(){
		if(arrayUsuario.size()==0)
			return 1;
		else
			return arrayUsuario.get(sizeUsuario()-1).getCodigo()+1;
	}
	
	public void guardarUsuario(BE_Usuario u){
		try {
			File fUsuario = new File("Usuario.txt");
			if(!fUsuario.exists()){
				PrintWriter pw = new PrintWriter(new FileWriter("Usuario.txt"));
				pw.println(getDataToLine(u));
				pw.close();
			}else{
				FileWriter fw = new FileWriter("Usuario.txt", true);
				fw.write(getDataToLine(u) + "\n");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void guardarUsuario(){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Usuario.txt"));
			BE_Usuario u;
			for(int i = 0; i < size(); i++){
				u = arrayUsuario.get(i);
				pw.println(getDataToLine(u));
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//EndRegion
	
	String getDataToLine(BE_Usuario u){
		return u.getCodigo() + "," + 
			   u.getNombre() + "," + 
			   u.getApellido() + "," +
			   u.getIdTipoDocumento() + "," + 
			   u.getDocumento() + "," + 
			   u.getCodigoArea() + "," +
			   u.getArea() + "," +
			   u.getCorreo() + "," +
			   u.getTelefono() + "," + 
			   General.parsearDatetoString(u.getFechaIngreso()) + "," +
			   u.getEstado();
	}
	
	public int getPosUsuario(BE_Usuario objUsuarioBE){
		int pos = arrayUsuario.indexOf(objUsuarioBE);
		return pos;
	}
	
	public ArrayList<BE_Usuario> listAllUSuarios(){
		ArrayList<BE_Usuario> data = new ArrayList<BE_Usuario>();
		//data = dataPrueba();
		for(BE_Usuario u : arrayUsuario){
			data.add(u);
		}
		return data;
	}
	
	public BE_Usuario getUsuarioxCodigo(int codigo){
		for(BE_Usuario u : arrayUsuario){
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}
	
	public int sizeUsuario(){
		return arrayUsuario.size();
	}
	
	public BE_Usuario getUsuario(int index){
		return arrayUsuario.get(index);
	}
	
	public int size(){
		return arrayUsuario.size();
	}
	
	public boolean verificaExiste(int codigo, String nombre){
		for(BE_Usuario u : arrayUsuario)
		{
			if(u.getCodigo() == codigo && u.getNombre().equals(nombre)){
				return true;
			}
				
		}
		return false;
	}
	//Region Listados
	
	public ArrayList<BE_Usuario> listarUsuario(){
		ArrayList<BE_Usuario> aux = new ArrayList<BE_Usuario>();
		for(BE_Usuario u : arrayUsuario){
			aux.add(u);
		}
		return aux;
	}
	
	public ArrayList<BE_Usuario> listarUsuarioxArea(int area){
		ArrayList<BE_Usuario> aux = new ArrayList<BE_Usuario>();
		for(BE_Usuario u : arrayUsuario)
			if(u.getCodigoArea() == area)
				aux.add(u);
		return aux;
	}
	
	public BE_Usuario listarUsuarioxCodigo(int codigo){
		for(BE_Usuario u : arrayUsuario)
			if(u.getCodigo() == codigo)
				return u;
		return null;
	}
	
	public void cargar(){
		try {
			File fUsuario = new File("Usuario.txt");
			if(fUsuario.exists()){
				BufferedReader br = new BufferedReader(new FileReader("Usuario.txt"));
				String linea, data[], nombres, apellidos, documento, correo, telefono,area;
				int codigo, idTipoDocumento, codigo_area, estado;
				Date fechaIngreso;
				while((linea = br.readLine())!= null){
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					nombres = data[1];
					apellidos = data[2];
					idTipoDocumento = Integer.parseInt(data[3]);
					documento = data[4];
					codigo_area = Integer.parseInt(data[5]);
					area = data[6];
					correo = data[7];
					telefono = data[8];
					fechaIngreso = General.parseStringtoDate(data[9]);
					estado = Integer.parseInt(data[10]);
					arrayUsuario.add(new BE_Usuario(codigo, nombres, apellidos, idTipoDocumento, documento,
													codigo_area, area, correo, telefono, fechaIngreso, estado));
				}
				br.close();
			}else
				return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String[] lista(){
		String nombres[] = new String[size()];
		for(int i = 0; i < size(); i++){
			BE_Usuario u = arrayUsuario.get(i);
			nombres[i] = u.getNombre();
		}
		return nombres;
	}
	//EndRegion
}
