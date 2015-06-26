package Controlador;

import internalFrame.Mantenimientos.TipoDocumento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
		
		public void guardarTipoDocumento(BE_TipoDocumento td){
			try {
				File fTDocumento = new File("TipoDocumento.txt");
				if(!fTDocumento.exists()){
					PrintWriter pw = new PrintWriter(new FileWriter("TipoDocumento.txt"));
					pw.println(getDataToLine(td));
					pw.close();
				}else{
					FileWriter fw = new FileWriter("TipoDocumento.txt",true);
					fw.write(getDataToLine(td));
					fw.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void guardarTipoDocumento(){
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("TipoDocumento.txt"));
				BE_TipoDocumento td;
				String linea;
				for(int i = 0; i < size(); i++){
					td = arrayTipoDoc.get(i);
					linea = getDataToLine(td);
					pw.println(linea);
				}
				pw.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		String getDataToLine(BE_TipoDocumento td){
			return td.getCodigo() + "," + 
				   td.getDescripcion() + "," +
				   td.getAbreviacion() + "," + 
				   td.getEstado() + ",";
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
			cargar();
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
			int i = 0;
			for(BE_TipoDocumento t : arrayTipoDoc){
				data[i] = t.getDescripcion();
				i++;
			}
			
			//for(int i = 0; i < size(); i++){
				//BE_TipoDocumento T = arrayTipoDoc.get(i);
				//data[i] = T.getDescripcion();
			//}
			return data;
		}
		
		public void cargar(){
			try {
				File fTDocumento = new File("TipoDocumento.txt");
				if(fTDocumento.exists()){
				BE_TipoDocumento td;
				BufferedReader br = new BufferedReader(new FileReader("TipoDocumento.txt"));
				String linea, data[], descripcion, abreviacion;
				int codigo, estado;
				while((linea = br.readLine()) != null){
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					descripcion = data[1];
					abreviacion = data[2];
					estado = Integer.parseInt(data[3]);
					arrayTipoDoc.add(new BE_TipoDocumento(codigo, descripcion, abreviacion, estado));
				}
				br.close();
				}else
					return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
