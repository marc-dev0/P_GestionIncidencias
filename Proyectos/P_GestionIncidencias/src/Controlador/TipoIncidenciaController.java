package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.BE_TipoIncidencia;

public class TipoIncidenciaController {

	private ArrayList<BE_TipoIncidencia> arrayTipoIncidencia;

	//Region Mantenimientos
	
		public void agregarTipoIncidencia(BE_TipoIncidencia a){
			arrayTipoIncidencia.add(a);
		}
		
		public void eliminarTipoIncidencia(BE_TipoIncidencia a){
			arrayTipoIncidencia.remove(a);
		}
		
		public void modificarTipoIncidencia(int i, BE_TipoIncidencia a){
			arrayTipoIncidencia.set(i, a);
		}
		
		public void guardarTipoIncidencia(BE_TipoIncidencia ti){
			try {
				File fTIncidencia = new File("TipoIncidencia.txt");
				if(!fTIncidencia.exists()){
					PrintWriter pw = new PrintWriter(new FileWriter("TipoIncidencia.txt"));
					pw.println(getDataToLine(ti));
					pw.close();
				}else{
					FileWriter fw = new FileWriter("TipoIncidencia.txt");
					fw.write(getDataToLine(ti));
					fw.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void guardarTipoIncidencia(){
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("TipoIncidencia.txt"));
				BE_TipoIncidencia ti;
				for(int i = 0; i < size(); i++){
					ti = arrayTipoIncidencia.get(i);
					pw.println(getDataToLine(ti));
				}
				pw.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		public int generarCodigo(){
			if(arrayTipoIncidencia.size() ==0)
				return 1;
			else 
				return arrayTipoIncidencia.get(arrayTipoIncidencia.size()-1).getCodigo()+1;
		}
		
		String getDataToLine(BE_TipoIncidencia ti){
			return ti.getCodigo() + "," + 
				   ti.getDescripcion() + "," + 
				   ti.getAbreviacion() + "," + 
				   ti.getEstado();
		}
		//EndRegion Mantenimientos
		
		public TipoIncidenciaController(){
			arrayTipoIncidencia = new ArrayList<BE_TipoIncidencia>();
			cargar();
		}
	
		public int size(){
			return arrayTipoIncidencia.size();
		}
		
		public int getPosTipoIncidencia(BE_TipoIncidencia a){
			int pos = arrayTipoIncidencia.indexOf(a);
			return pos;
		}
		
		public BE_TipoIncidencia getTipoIncidencia(int i){
			return arrayTipoIncidencia.get(i);
		}

		public BE_TipoIncidencia buscarTipoIncidenciaxCodigo(int codigo){
			for(BE_TipoIncidencia a : arrayTipoIncidencia){
				if(a.getCodigo() == codigo)
					return a;
			}
			return null;
			
		}
		
		public String[] lista(){
			String[] data = new String[size()];

			for(int i = 0; i < size(); i++){
				BE_TipoIncidencia T = arrayTipoIncidencia.get(i);
				data[i] = T.getDescripcion();
			}
			return data;
		}
		
		public void cargar(){
			try {
				File fTIncidencia = new File("TipoIncidencia.txt");
				if(fTIncidencia.exists()){
				BufferedReader br = new BufferedReader(new FileReader("TipoIncidencia.txt"));
				String linea, data[], descripcion, abreviacion;
				int codigo, estado;
				
				while((linea = br.readLine()) != null){
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					descripcion = data[1];
					abreviacion = data[2];
					estado = Integer.parseInt(data[3]);
					arrayTipoIncidencia.add(new BE_TipoIncidencia(codigo, descripcion, abreviacion,
																	estado));
				}
				br.close();
				}else
					return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
