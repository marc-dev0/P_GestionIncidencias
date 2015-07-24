package Controlador;

import java.util.ArrayList;

import Entidades.BE_Area;
import Entidades.BE_Usuario;

import java.io.*;

import javax.swing.JOptionPane;

public class AreaController {

	ArrayList<BE_Area> arrayArea;
	UsuarioController UsuarioC = new UsuarioController();

	// Region Mantenimientos

	public void agregarArea(BE_Area a) {
		arrayArea.add(a);
	}

	private String getDataToLine(BE_Area a) {
		String linea = "";
		linea = a.getCodigo() + "," + a.getNombre().toUpperCase() + ","
				+ a.getDescripcion() + "," + a.getNombreCorto() + ","
				+ a.getNombreLargo() + "," + a.getEstado();
		return linea;
	}

	public void guardarArea(BE_Area a) {
		try {
			File farea = new File("Areas.txt");
			// Verifica si el archivo no existe, es decir, si no hay ningun
			// registro
			if (!farea.exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter("Areas.txt"));
				pw.println(getDataToLine(a));
				pw.close();
			} else {
				// Si es que existe el archivo, quiere decir que ya hay data,
				// entonces solamente va agregando la
				// linea del objeto que recibe de la GUI
				FileWriter fw = new FileWriter("Areas.txt", true);
				fw.write(getDataToLine(a) + "\n");
				fw.close();
			}
		} catch (Exception e) {

		}
	}

	public void eliminaArea(int codigoArea) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"));

			BufferedReader br = new BufferedReader(new FileReader("Areas.txt"));
			
			String linea, s[];
			
			String path = System.getProperty("user.dir");
			
			while ((linea = br.readLine()) != null) {
				s = linea.split(",");
				if (Integer.parseInt(s[0]) != codigoArea) {
					pw.println(linea);
				}
			}
			pw.close();
			br.close();
			File file = new File(path + "\\temp.txt");
			File temp = new File(path + "\\Areas.txt");
			temp.delete();
			if (file.renameTo(new File(path+ "\\Areas.txt")));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void guardarArea() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("Areas.txt"));
			BE_Area a;
			String linea;
			for (int i = 0; i < size(); i++) {
				a = arrayArea.get(i);
				linea = getDataToLine(a);
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void eliminarArea(BE_Area a) {
		arrayArea.remove(a);
	}

	public void modificarArea(int i, BE_Area a) {
		arrayArea.set(i, a);
	}

	// EndRegion Mantenimientos

	public AreaController() {
		arrayArea = new ArrayList<BE_Area>();
		cargar();

		/*
		 * agregarArea(new BE_Area(1, "Sistemas",
		 * "Area encargada del mantenimiento de las computadoras", "Sist",
		 * "NombreLargo1", 0)); agregarArea(new BE_Area(2, "Contabilidad",
		 * "Area encargada del mantenimiento de las computadoras", "Sist",
		 * "NombreLargo1", 0)); agregarArea(new BE_Area(3, "Marketing",
		 * "Area encargada del mantenimiento de las computadoras", "Sist",
		 * "NombreLargo1", 0)); agregarArea(new BE_Area(4, "Adminstración",
		 * "Area encargada del mantenimiento de las computadoras", "Sist",
		 * "NombreLargo1", 0)); agregarArea(new BE_Area(5, "Logistica",
		 * "Area encargada del mantenimiento de las computadoras", "Sist",
		 * "NombreLargo1", 0));
		 */
	}

	public int generarCodigo() {
		if (arrayArea.size() == 0)
			return 0;
		else
			return arrayArea.get(arrayArea.size() - 1).getCodigo() + 1;
	}

	public int size() {
		return arrayArea.size();
	}

	public int getPosArea(BE_Area a) {
		int pos = arrayArea.indexOf(a);
		return pos;
	}

	public BE_Area getArea(int i) {
		return arrayArea.get(i);
	}

	public BE_Area buscarAreaxCodigo(int codigo) {
		for (BE_Area a : arrayArea) {
			if (a.getCodigo() == codigo)
				return a;
		}
		return null;
	}

	public String[] lista() {
		int i = 0;
		String[] data = new String[totalAreaActivos()];
		for (BE_Area a : arrayArea) {
			if (a.getEstado() == 0) {
				data[i] = a.getNombre();
				i++;
			}
		}
		return data;
	}

	public int getCodigoAreaxNombre(String nombre) {
		for (BE_Area a : arrayArea)
			if (a.getNombre() == nombre)
				return a.getCodigo();

		return 0;
	}
	
	public String getNombreAreaxCodigo(int codigoArea){
		for(BE_Area a : arrayArea)
			if(a.getCodigo() == codigoArea)
				return a.getNombre();
		return null;
	}
	public int totalAreaActivos() {
		int cantidad = 0;
		for (BE_Area a : arrayArea)
			if (a.getEstado() == 0)
				cantidad++;
		return cantidad;
	}

	public void cargar() {
		try {
			File fAreas = new File("Areas.txt");
			if (fAreas.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(
						"Areas.txt"));
				int codigo, estado;
				String linea, data[], nombre, descripcion, nombreCorto, nombreLargo;
				BE_Area a;
				while ((linea = br.readLine()) != null) {
					a = new BE_Area();
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					nombre = data[1];
					descripcion = data[2];
					nombreCorto = data[3];
					nombreLargo = data[4];
					estado = Integer.parseInt(data[5]);
					arrayArea.add(new BE_Area(codigo, nombre, descripcion,
							nombreCorto, nombreLargo, estado));
				}
				br.close();
			} else
				return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean existsArea(String area) {
		for (BE_Area a : arrayArea) {
			if (a.getNombre().equals(area))
				return true;
		}
		return false;
	}

	public boolean isAreaReferencedToUsuario(int codigoArea) {
		ArrayList<BE_Usuario> arrayUsuario = UsuarioC.listarUsuario();
		for (BE_Usuario u : arrayUsuario)
			if (u.getCodigoArea() == codigoArea)
				return true;
		return false;
	}
}
