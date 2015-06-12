package Controlador;

import java.util.ArrayList;
import Entidades.BE_Area;


public class AreaController {
	
	ArrayList<BE_Area> arrayArea;
	
	//Region Mantenimientos
	
	public void agregarArea(BE_Area a){
		arrayArea.add(a);
	}
	
	public void eliminarArea(BE_Area a){
		arrayArea.remove(a);
	}
	
	public void modificarArea(int i, BE_Area a){
		arrayArea.set(i, a);
	}
	
	public int generarCodigo(){
		if(arrayArea.size() ==0)
			return 1;
		else 
			return arrayArea.get(arrayArea.size()-1).getCodigo()+1;
	}
	//EndRegion Mantenimientos
	
	public AreaController(){
		arrayArea = new ArrayList<BE_Area>();
		
		agregarArea(new BE_Area(1, "Sistemas", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(2, "Contabilidad", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(3, "Marketing", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(4, "Adminstración", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(5, "Logistica", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
	}
	public int size(){
		return arrayArea.size();
	}
	
	public int getPosArea(BE_Area a){
		int pos = arrayArea.indexOf(a);
		return pos;
	}
	
	public BE_Area getArea(int i){
		return arrayArea.get(i);
	}

	public BE_Area buscarAreaxCodigo(int codigo){
		for(BE_Area a : arrayArea){
			if(a.getCodigo() == codigo)
				return a;
		}
		return null;
		
	}
	
	public String[] lista(){
		String[] data = new String[size()];

		for(int i = 0; i < size(); i++){
			BE_Area a = arrayArea.get(i);
			data[i] = a.getNombre();
		}
		return data;
	}
	
	//Region Listas de Areas
	//EndRegion
	
	
}
