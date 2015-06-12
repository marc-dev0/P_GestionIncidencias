package Controlador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Entidades.BE_Especialista;
public class EspecialistaController {
	
	ArrayList<BE_Especialista> arrayEspecialista;
	
	public EspecialistaController(){
		arrayEspecialista = new ArrayList<BE_Especialista>();
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = "15-04-1999";
		Date fechar = null;
		try {
			fechar = fmt.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registrarEspecialista(new BE_Especialista(1, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(2, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(3, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(4, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(5, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(6, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
		registrarEspecialista(new BE_Especialista(7, "Miguel", "Rojas Coraje", "Servicio Técnico", "(01) 531-4123", fechar, 0));
	}
	
	public void registrarEspecialista(BE_Especialista objEspecialista){
		arrayEspecialista.add(objEspecialista);
	}

	public void modificarEspecialista(int index, BE_Especialista objEspecialistaBE){
		arrayEspecialista.set(index, objEspecialistaBE);
		
	}
	
	public void eliminarEspecialista(BE_Especialista objEspecialistaBE){
		arrayEspecialista.remove(objEspecialistaBE);
	}
	
	public int getPosEspecialista(BE_Especialista objEspecialistaBE){
		int pos = arrayEspecialista.indexOf(objEspecialistaBE);
		return pos;
	}
	
	public ArrayList<BE_Especialista> listAllEspecialistas(){
		ArrayList<BE_Especialista> data = new ArrayList<BE_Especialista>();
		//data = dataPrueba();
		for(BE_Especialista u : arrayEspecialista){
			data.add(u);
		}
		return data;
	}
	public BE_Especialista getEspecialistaxCodigo(int codigo){
		for(BE_Especialista u : arrayEspecialista){
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}
	
	public int generarCodigo(){
			if(arrayEspecialista.size()==0)
				return 1;
			else
				return arrayEspecialista.get(sizeEspecialista()-1).getCodigo()+1;
	}
	
	public int sizeEspecialista(){
		return arrayEspecialista.size();
	}
	
	public BE_Especialista getEspecialista(int index){
		return arrayEspecialista.get(index);
	}
	
	public int size(){
		return arrayEspecialista.size();
	}
	//Region Listados
	
	public ArrayList<BE_Especialista> listarEspecialista(){
		ArrayList<BE_Especialista> aux = new ArrayList<BE_Especialista>();
		for(BE_Especialista u : arrayEspecialista){
			aux.add(u);
		}
		return aux;
	}
	
	public BE_Especialista listarEspecialistaxCodigo(int codigo){
		for(BE_Especialista u : arrayEspecialista)
			if(u.getCodigo() == codigo)
				return u;
		return null;
	}
	//EndRegion
}
