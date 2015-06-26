package Controlador;

import Entidades.BE_Incidencia;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import Utilitarios.General;
public class IncidenciaController {
	public ArrayList<BE_Incidencia> arrayIncidencia;
	
	public IncidenciaController(){
		arrayIncidencia = new ArrayList<BE_Incidencia>();
		cargar();
	}
	
	//Métodos propios del controlador
	public int getSize(){
		return arrayIncidencia.size();
	}
	
	public void agregarIncidencia(BE_Incidencia i){
		arrayIncidencia.add(i);
	}
	
	public void modificarIncidencia(int index, BE_Incidencia i){
		arrayIncidencia.set(index, i);
	}
	
	public int generarCodigo(){
		if(arrayIncidencia.size() == 0)
			return 1;
		else{
			return arrayIncidencia.get(getSize()-1).getCodigoincidencia()+1;
		}
	}
	//Ficheros
	public void guardarIncidencia(BE_Incidencia i){
		try {
			File fIncidencia = new File("Incidencia.txt");
			if(!fIncidencia.exists()){
				PrintWriter pw = new PrintWriter(new FileWriter("Incidencia.txt"));
				pw.println(getDataToLine(i));
				pw.close();
			}
			else{
				FileWriter fw = new FileWriter("Incidencia.txt", true);
				fw.write(getDataToLine(i) + "\n");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void guardarIncidencia(){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Incidencia.txt"));
			String linea = "";
			for(int i = 0; i < getSize(); i++){
				BE_Incidencia in = arrayIncidencia.get(i);
				linea = getDataToLine(in);
				pw.println(linea);
			}
			pw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String getDataToLine(BE_Incidencia i){
		return i.getCodigoincidencia() + "," + 
			   i.getCodigoUsuario() + "," +
			   i.getCodigoEspecialista() + "," +
			   i.getCodigoTipoIncidencia() + "," +
			   i.getDescripcion() + "," + 
			   i.getObservacion() + "," +
			   i.getTiempoEstimado() + "," + 
			   i.getTiempoReal() + "," +
			   General.parsearDatetoString(i.getFechaRegistro()) + "," +
			   General.parsearDatetoString(i.getFechaInicio()) + "," +
			   General.parsearDatetoString(i.getFechaFin()) + "," +
			   i.getEstado() + "," + 
			   i.getNombreUsuario() + "," +
			   i.getNombreEspecialista() + "," +
			   i.getNombreTipoIncidencia();
	}
	
	//Region Listados()
	
	public ArrayList<BE_Incidencia> getListaIncidencia(){
		ArrayList<BE_Incidencia> aux = new ArrayList<BE_Incidencia>();
		for(BE_Incidencia i : arrayIncidencia){
			aux.add(i);
		}
		return aux;
	}
	
	public int getIndex(BE_Incidencia i){
		return arrayIncidencia.indexOf(i);
	}
	
	public BE_Incidencia getIncidencia(int index){
		return arrayIncidencia.get(index);
	}
	
	public BE_Incidencia getIncidenciaxCodigo(int codigo){
		for(BE_Incidencia i : arrayIncidencia){
			if(i.getCodigoincidencia() == codigo){
				return i;
			}
		}
		return null;
	}
	
	void cargar(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("Incidencia.txt"));
			String linea, data[], descripcion, observacion, nombreUsuario, nombreEspecialista, nombreTipoIncidencia;
			int codigoIncidencia, codigoUsuario, codigoEspecialista, codigoTipoIncidencia, tiempoEstimado, tiempoReal, estado;
			Date fechaRegistro, fechaInicio, fechaFin;
			while((linea = br.readLine()) != null){
				data = linea.split(",");
				codigoIncidencia = Integer.parseInt(data[0]);
				codigoUsuario = Integer.parseInt(data[1]);
				codigoEspecialista = Integer.parseInt(data[2]);
				codigoTipoIncidencia = Integer.parseInt(data[3]);
				descripcion = data[4];
				observacion = data[5];
				tiempoEstimado = Integer.parseInt(data[6]);
				tiempoReal = Integer.parseInt(data[7]);
				fechaRegistro = General.parseStringtoDate(data[8]);
				fechaInicio = General.parseStringtoDate(data[9]);
				fechaFin = General.parseStringtoDate(data[10]);
				estado = Integer.parseInt(data[11]);
				nombreUsuario = data[12];
				nombreEspecialista = data[13];
				nombreTipoIncidencia = data[14];
				arrayIncidencia.add(new BE_Incidencia(codigoIncidencia, codigoUsuario, codigoEspecialista,
						codigoTipoIncidencia, descripcion, observacion, tiempoEstimado, tiempoReal, fechaRegistro, 
						fechaInicio, fechaFin, nombreUsuario, nombreEspecialista, nombreTipoIncidencia));
				
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//EndRegion
}
