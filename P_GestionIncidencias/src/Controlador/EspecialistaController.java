package Controlador;

import internalFrame.Mantenimientos.Especialista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Utilitarios.General;
import Entidades.BE_Especialista;
import Entidades.BE_Incidencia;

public class EspecialistaController {

	ArrayList<BE_Especialista> arrayEspecialista = new ArrayList<BE_Especialista>();

	private IncidenciaController IncidenciaC = new IncidenciaController();

	public EspecialistaController() {
		arrayEspecialista = new ArrayList<BE_Especialista>();
		cargar();
		/*
		 * SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy"); String
		 * fecha = "15-04-1999"; Date fechar = null; try { fechar =
		 * fmt.parse(fecha); } catch (ParseException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } registrarEspecialista(new
		 * BE_Especialista(1, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(2, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(3, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(4, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(5, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(6, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0)); registrarEspecialista(new
		 * BE_Especialista(7, "Miguel", "Rojas Coraje", "Servicio Técnico",
		 * "(01) 531-4123", fechar, 0));
		 */
	}

	// Region Mantenimientos

	public void registrarEspecialista(BE_Especialista objEspecialista) {
		arrayEspecialista.add(objEspecialista);
	}

	public void modificarEspecialista(int index,
			BE_Especialista objEspecialistaBE) {
		arrayEspecialista.set(index, objEspecialistaBE);

	}

	public void eliminarEspecialista(BE_Especialista objEspecialistaBE) {
		arrayEspecialista.remove(objEspecialistaBE);
	}

	public String getDataToLine(BE_Especialista e) {
		return e.getCodigo() + "," + e.getNombres() + "," + e.getApellidos()
				+ "," + e.getEspecialidad() + "," + e.getAnexo() + ","
				+ General.parsearDatetoString(e.getFechaIngreso()) + ","
				+ e.getEstado();
	}

	public void guardarEspecialista(BE_Especialista e) {
		try {
			File fichero = new File("Especialista.txt");

			if (!fichero.exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter(
						"Especialista.txt"));
				pw.println(getDataToLine(e));
				pw.close();
			} else {
				FileWriter fw = new FileWriter("Especialista.txt", true);
				fw.write("\n" + getDataToLine(e));
				fw.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	public void guardarEspecialista() {
		try {
			BE_Especialista e;
			PrintWriter pw = new PrintWriter(new FileWriter("Especialista.txt"));
			for (int i = 0; i < size(); i++) {
				e = arrayEspecialista.get(i);
				pw.println(getDataToLine(e));
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void eliminaEspecialista(int codigoEspecialista) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"));

			BufferedReader br = new BufferedReader(new FileReader("Especialista.txt"));
			
			String path = System.getProperty("user.dir");
			
			String linea, s[];
			while ((linea = br.readLine()) != null) {
				s = linea.split(",");
				if (Integer.parseInt(s[0]) != codigoEspecialista) {
					pw.println(linea);
				}
			}
			pw.close();
			br.close();
			File file = new File(path + "\\temp.txt");
			File temp = new File(path + "\\Especialista.txt");
			temp.delete();
			if (file.renameTo(new File(path + "\\Especialista.txt")));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// EndRegion

	public int getPosEspecialista(BE_Especialista objEspecialistaBE) {
		int pos = arrayEspecialista.indexOf(objEspecialistaBE);
		return pos;
	}

	public ArrayList<BE_Especialista> listAllEspecialistas() {
		ArrayList<BE_Especialista> data = new ArrayList<BE_Especialista>();
		// data = dataPrueba();
		for (BE_Especialista u : arrayEspecialista) {
			data.add(u);
		}
		return data;
	}

	public BE_Especialista getEspecialistaxCodigo(int codigo) {
		for (BE_Especialista u : arrayEspecialista) {
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}

	public int generarCodigo() {
		if (arrayEspecialista.size() == 0)
			return 0;
		else
			return arrayEspecialista.get(sizeEspecialista() - 1).getCodigo() + 1;
	}

	public int sizeEspecialista() {
		return arrayEspecialista.size();
	}

	public BE_Especialista getEspecialista(int index) {
		return arrayEspecialista.get(index);
	}

	public int size() {
		return arrayEspecialista.size();
	}

	// Region Listados

	public void cargar() {
		try {
			File fEspecialista = new File("Especialista.txt");
			if (fEspecialista.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(
						"Especialista.txt"));
				String linea, data[], nombres, apellidos, especialidad, anexo;
				Date fechaIngreso;
				int codigo, estado;
				while ((linea = br.readLine()) != null) {
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					nombres = data[1];
					apellidos = data[2];
					especialidad = data[3];
					anexo = data[4];
					fechaIngreso = General.parseStringtoDate(data[5]);
					estado = Integer.parseInt(data[6]);
					arrayEspecialista.add(new BE_Especialista(codigo, nombres,
							apellidos, especialidad, anexo, fechaIngreso,
							estado));
				}
				br.close();
			} else
				return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<BE_Especialista> listarEspecialista() {
		ArrayList<BE_Especialista> aux = new ArrayList<BE_Especialista>();
		for (BE_Especialista u : arrayEspecialista) {
			aux.add(u);
		}
		return aux;
	}

	public BE_Especialista listarEspecialistaxCodigo(int codigo) {
		for (BE_Especialista u : arrayEspecialista)
			if (u.getCodigo() == codigo)
				return u;
		return null;
	}

	public String[] lista() {
		String[] data = new String[totalActivos()];
		int i = 0;
		for(BE_Especialista e : arrayEspecialista)
			if(e.getEstado() == 0){
				data[i] = e.getNombres();
				i++;
			}
		return data;
	}

	// EndRegion

	private int totalActivos() {
		int conta = 0;
		for (BE_Especialista e : arrayEspecialista)
			if (e.getEstado() == 0)
				conta++;
		return conta;
	}

	public boolean isEspecialistaReferencedToIncidencia(int codigoEspecialista) {

		for (BE_Incidencia i : IncidenciaC.getListaIncidencia())
			if (i.getCodigoEspecialista() == codigoEspecialista)
				return true;

		return false;

	}
}
