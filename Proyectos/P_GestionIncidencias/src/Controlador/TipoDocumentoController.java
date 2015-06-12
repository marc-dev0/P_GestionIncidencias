package Controlador;

import java.util.ArrayList;

import Entidades.BE_TipoDocumento;

public class TipoDocumentoController {

	private ArrayList<BE_TipoDocumento> arrayTipoDoc;

	//Region Mantenimientos
	
		public void agregarTipoDocumento(BE_TipoDocumento a){
			arrayTipoDoc.add(a);
		}
		
		public void eliminarTipoDocumento(BE_TipoDocumento a){
			arrayTipoDoc.remove(a);
		}
		
		public void modificarTipoDocumento(int i, BE_TipoDocumento a){
			arrayTipoDoc.set(i, a);
		}
		
		public int generarCodigo(){
			if(arrayTipoDoc.size() ==0)
				return 1;
			else 
				return arrayTipoDoc.get(arrayTipoDoc.size()-1).getCodigo()+1;
		}
		//EndRegion Mantenimientos
		
		public TipoDocumentoController(){
			arrayTipoDoc = new ArrayList<BE_TipoDocumento>();
			
			agregarTipoDocumento(new BE_TipoDocumento(1,"Documento Nacional de Identidad","DNI",0));
			agregarTipoDocumento(new BE_TipoDocumento(2, "CARNET DE EXTRANJERIA", "CE", 0));
		}
		public int size(){
			return arrayTipoDoc.size();
		}
		
		public int getPosTipoDocumento(BE_TipoDocumento a){
			int pos = arrayTipoDoc.indexOf(a);
			return pos;
		}
		
		public BE_TipoDocumento getTipoDocumento(int i){
			return arrayTipoDoc.get(i);
		}

		public BE_TipoDocumento buscarTipoDocumentoxCodigo(int codigo){
			for(BE_TipoDocumento a : arrayTipoDoc){
				if(a.getCodigo() == codigo)
					return a;
			}
			return null;
			
		}
		
		public String[] lista(){
			String[] data = new String[size()];

			for(int i = 0; i < size(); i++){
				BE_TipoDocumento T = arrayTipoDoc.get(i);
				data[i] = T.getDescripcion();
			}
			return data;
		}
}
