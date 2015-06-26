package Controlador;

import java.util.ArrayList;

import Entidades.BE_Area;

import java.io.*;
public class AreaController {
	
	ArrayList<BE_Area> arrayArea;
	
	//Region Mantenimientos
	
	public void agregarArea(BE_Area a){
		arrayArea.add(a);
	}
	
	private String getDataToLine(BE_Area a){
		String linea = "";
		linea = a.getCodigo() + "," +
				a.getNombre() + "," + 
				a.getDescripcion() + "," +
				a.getNombreCorto() + "," +
				a.getNombreLargo() + "," +
				a.getEstado();
		return linea;
	}
	
	public void guardarArea(BE_Area a){
		try {
			File farea = new File("Areas.txt");
			//Verifica si el archivo no existe, es decir, si no hay ningun registro
			if(!farea.exists()){
				PrintWriter pw = new PrintWriter(new FileWriter("Areas.txt"));
				pw.println(getDataToLine(a));
				pw.close();
			}else{
				//Si es que existe el archivo, quiere decir que ya hay data, entonces solamente va agregando la
				//linea del objeto que recibe de la GUI
				FileWriter fw = new FileWriter("Areas.txt", true);
				fw.write(getDataToLine(a) + "\n");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
                                                                                                                                                                                       		}
	}                                          
	
	public void guardarArea(){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Areas.txt"));
			BE_Area a;
			String linea;
			for(int i = 0; i < size(); i++){
				a = arrayArea.get(i);
				linea = getDataToLine(a);
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void eliminarArea(BE_Area a){
		arrayArea.remove(a);
	}
	
	public void modificarArea(int i, BE_Area a){
		arrayArea.set(i, a);
	}
	
	//EndRegion Mantenimientos
	
	public AreaController(){
		arrayArea = new ArrayList<BE_Area>();
		cargar();
		
		/*agregarArea(new BE_Area(1, "Sistemas", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(2, "Contabilidad", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(3, "Marketing", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(4, "Adminstración", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));
		agregarArea(new BE_Area(5, "Logistica", "Area encargada del mantenimiento de las computadoras", "Sist",
				"NombreLargo1", 0));*/
	}
	
	public int generarCodigo(){
		if(arrayArea.size() ==0)
			return 1;
		else 
			return arrayArea.get(arrayArea.size()-1).getCodigo()+1;
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
	
	public void cargar(){
		try {
			File fAreas = new File("Areas.txt");
			if(fAreas.exists()){
				BufferedReader br = new BufferedReader(new FileReader("Areas.txt"));
				int codigo, estado;
				String linea, data[], nombre, descripcion, nombreCorto, nombreLargo;
				BE_Area a;
				while((linea = br.readLine()) != null){
					a = new BE_Area();
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					nombre = data[1];
					descripcion = data[2];
					nombreCorto = data[3];
					nombreLargo = data[4];
					estado = Integer.parseInt(data[5]);
					arrayArea.add(new BE_Area(codigo, nombre, descripcion, nombreCorto, nombreLargo, estado));
				}
				br.close();
			}else
				return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
