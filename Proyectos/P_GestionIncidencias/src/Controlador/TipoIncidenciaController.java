package Controlador;

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
		
		public int generarCodigo(){
			if(arrayTipoIncidencia.size() ==0)
				return 1;
			else 
				return arrayTipoIncidencia.get(arrayTipoIncidencia.size()-1).getCodigo()+1;
		}
		//EndRegion Mantenimientos
		
		public TipoIncidenciaController(){
			arrayTipoIncidencia = new ArrayList<BE_TipoIncidencia>();
			
			agregarTipoIncidencia(new BE_TipoIncidencia(1,"Documento Nacional de Identidad","DNI",0));
			agregarTipoIncidencia(new BE_TipoIncidencia(2, "CARNET DE EXTRANJERIA", "CE", 0));
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
}
