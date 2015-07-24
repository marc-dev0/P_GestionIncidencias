package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.BE_Incidencia;
import Entidades.BE_TipoIncidencia;

public class TipoIncidenciaController {

	private ArrayList<BE_TipoIncidencia> arrayTipoIncidencia;
	private IncidenciaController IncidenciaC = new IncidenciaController();

	// Region Mantenimientos

	public void agregarTipoIncidencia(BE_TipoIncidencia a) {
		arrayTipoIncidencia.add(a);
	}

	public void eliminarTipoIncidencia(BE_TipoIncidencia a) {
		arrayTipoIncidencia.remove(a);
	}

	public void modificarTipoIncidencia(int i, BE_TipoIncidencia a) {
		arrayTipoIncidencia.set(i, a);
	}

	public void guardarTipoIncidencia(BE_TipoIncidencia ti) {
		try {
			File fTIncidencia = new File("TipoIncidencia.txt");
			if (!fTIncidencia.exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter(
						"TipoIncidencia.txt"));
				pw.println(getDataToLine(ti));
				pw.close();
			} else {
				FileWriter fw = new FileWriter("TipoIncidencia.txt", true);
				fw.write("\n" +getDataToLine(ti));
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void guardarTipoIncidencia() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(
					"TipoIncidencia.txt"));
			BE_TipoIncidencia ti;
			for (int i = 0; i < size(); i++) {
				ti = arrayTipoIncidencia.get(i);
				pw.println(getDataToLine(ti));
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void eliminaTipoIncidencia(int codigoTIncidenci){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"));
			
			BufferedReader br = new BufferedReader(new FileReader("TipoIncidencia.txt"));
			
			String linea, s[];
			
			String path = System.getProperty("user.dir");
			
			while((linea =  br.readLine()) != null){
				s = linea.split(",");
				if(Integer.parseInt(s[0]) != codigoTIncidenci)
					pw.println(linea);
			}
			pw.close();
			br.close();
			
			File file = new File(path + "\\temp.txt");
			File temp = new File(path + "\\TipoIncidencia.txt");
			
			temp.delete();
			
			file.renameTo(new File(path + "\\TipoIncidencia.txt"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int generarCodigo() {
		if (arrayTipoIncidencia.size() == 0)
			return 0;
		else
			return arrayTipoIncidencia.get(arrayTipoIncidencia.size() - 1)
					.getCodigo() + 1;
	}

	String getDataToLine(BE_TipoIncidencia ti) {
		return ti.getCodigo() + "," + ti.getDescripcion() + ","
				+ ti.getAbreviacion() + "," + ti.getEstado();
	}

	// EndRegion Mantenimientos

	public TipoIncidenciaController() {
		arrayTipoIncidencia = new ArrayList<BE_TipoIncidencia>();
		cargar();
	}

	public int size() {
		return arrayTipoIncidencia.size();
	}

	public int getPosTipoIncidencia(BE_TipoIncidencia a) {
		int pos = arrayTipoIncidencia.indexOf(a);
		return pos;
	}

	public BE_TipoIncidencia getTipoIncidencia(int i) {
		return arrayTipoIncidencia.get(i);
	}

	public BE_TipoIncidencia buscarTipoIncidenciaxCodigo(int codigo) {
		for (BE_TipoIncidencia a : arrayTipoIncidencia) {
			if (a.getCodigo() == codigo)
				return a;
		}
		return null;

	}

	public String[] lista() {
		String[] data = new String[totalActivos()];
		int i = 0;
		for(BE_TipoIncidencia ti : arrayTipoIncidencia)
			if(ti.getEstado() == 0){
				data[i] = ti.getDescripcion();
				i++;
			}
		return data;
	}

	private int totalActivos() {
		int conta = 0;
		for (BE_TipoIncidencia ti : arrayTipoIncidencia)
			if (ti.getEstado() == 0)
				conta++;
		return conta;
	}

	public ArrayList<BE_TipoIncidencia> getListaTipoIncidencia() {
		ArrayList<BE_TipoIncidencia> aux = new ArrayList<BE_TipoIncidencia>();
		for (BE_TipoIncidencia ti : arrayTipoIncidencia)
			aux.add(ti);
		return aux;

	}

	public void cargar() {
		try {
			File fTIncidencia = new File("TipoIncidencia.txt");
			if (fTIncidencia.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(
						"TipoIncidencia.txt"));
				String linea, data[], descripcion, abreviacion;
				int codigo, estado;

				while ((linea = br.readLine()) != null) {
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					descripcion = data[1];
					abreviacion = data[2];
					estado = Integer.parseInt(data[3]);
					arrayTipoIncidencia.add(new BE_TipoIncidencia(codigo,
							descripcion, abreviacion, estado));
				}
				br.close();
			} else
				return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isTIncidenciaReferencedToIncidencia(int codigoTIncidencia) {
		for (BE_Incidencia i : IncidenciaC.getListaIncidencia())
			if (i.getCodigoTipoIncidencia() == codigoTIncidencia)
				return true;
		return false;
	}
	
	public int getCodigoTIncidenciaxDescripcion(String descripcion){
		for(BE_TipoIncidencia ti : arrayTipoIncidencia)
		{
			if(ti.getDescripcion().equals(descripcion))
				return ti.getCodigo();
		}
		return 0;
	}
}
