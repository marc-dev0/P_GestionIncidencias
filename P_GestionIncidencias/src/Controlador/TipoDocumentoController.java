package Controlador;

import internalFrame.Mantenimientos.TipoDocumento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.BE_TipoDocumento;
import Entidades.BE_TipoIncidencia;
import Entidades.BE_Usuario;

public class TipoDocumentoController {

	private ArrayList<BE_TipoDocumento> arrayTipoDoc;
	private UsuarioController UsuarioC = new UsuarioController();

	// Region Mantenimientos

	public void agregarTipoDocumento(BE_TipoDocumento a) {
		arrayTipoDoc.add(a);
	}

	public void eliminarTipoDocumento(BE_TipoDocumento a) {
		arrayTipoDoc.remove(a);
	}

	public void modificarTipoDocumento(int i, BE_TipoDocumento a) {
		arrayTipoDoc.set(i, a);
	}

	public void guardarTipoDocumento(BE_TipoDocumento td) {
		try {
			File fTDocumento = new File("TipoDocumento.txt");
			if (!fTDocumento.exists()) {
				PrintWriter pw = new PrintWriter(new FileWriter("TipoDocumento.txt"));
				pw.println(getDataToLine(td));
				pw.close();
			} else {
				FileWriter fw = new FileWriter("TipoDocumento.txt", true);
				fw.write(getDataToLine(td) + "\n");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void guardarTipoDocumento() {
		try {
			PrintWriter pw = new PrintWriter(
					new FileWriter("TipoDocumento.txt"));
			BE_TipoDocumento td;
			String linea;
			for (int i = 0; i < size(); i++) {
				td = arrayTipoDoc.get(i);
				linea = getDataToLine(td);
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void eliminarTipoDocumento(int codigoTDocumento){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"));
			
			BufferedReader br = new BufferedReader(new FileReader("TipoDocumento.txt"));
			
			String path = System.getProperty("user.dir");
			
			String linea, s[];
			
			while((linea = br.readLine()) != null){
				s = linea.split(",");
				if(Integer.parseInt(s[0]) != codigoTDocumento)
					pw.println(linea);
			}
			pw.close();
			br.close();
			
			File file = new File(path + "\\temp.txt");
			File temp = new File(path + "\\TipoDocumento.txt");
			temp.delete();
			if (file.renameTo(new File(path + "\\TipoDocumento.txt")));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	String getDataToLine(BE_TipoDocumento td) {
		return td.getCodigo() + "," + td.getDescripcion() + ","
				+ td.getAbreviacion() + "," + td.getEstado() ;
	}

	public int generarCodigo() {
		if (arrayTipoDoc.size() == 0)
			return 0;
		else
			return arrayTipoDoc.get(arrayTipoDoc.size() - 1).getCodigo() + 1;
	}

	// EndRegion Mantenimientos

	public TipoDocumentoController() {
		arrayTipoDoc = new ArrayList<BE_TipoDocumento>();
		cargar();
	}

	public int size() {
		return arrayTipoDoc.size();
	}

	public int getPosTipoDocumento(BE_TipoDocumento a) {
		int pos = arrayTipoDoc.indexOf(a);
		return pos;
	}

	public BE_TipoDocumento getTipoDocumento(int i) {
		return arrayTipoDoc.get(i);
	}

	public BE_TipoDocumento buscarTipoDocumentoxCodigo(int codigo) {
		for (BE_TipoDocumento a : arrayTipoDoc) {
			if (a.getCodigo() == codigo)
				return a;
		}
		return null;

	}

	public String[] lista() {
		String[] data = new String[totalActivos()];
		int i = 0;
		for(BE_TipoDocumento ti : arrayTipoDoc){
			if(ti.getEstado() ==0){
				data[i] = ti.getDescripcion();
				i++;
			}
		}
		return data;
	}

	private int totalActivos() {
		int conta = 0;
		for (BE_TipoDocumento td : arrayTipoDoc)
			if (td.getEstado() == 0)
				conta++;
		return conta;
	}

	public void cargar() {
		try {
			File fTDocumento = new File("TipoDocumento.txt");
			if (fTDocumento.exists()) {
				BE_TipoDocumento td;
				BufferedReader br = new BufferedReader(new FileReader(
						"TipoDocumento.txt"));
				String linea, data[], descripcion, abreviacion;
				int codigo, estado;
				while ((linea = br.readLine()) != null) {
					data = linea.split(",");
					codigo = Integer.parseInt(data[0]);
					descripcion = data[1];
					abreviacion = data[2];
					estado = Integer.parseInt(data[3]);
					arrayTipoDoc.add(new BE_TipoDocumento(codigo, descripcion,
							abreviacion, estado));
				}
				br.close();
			} else
				return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean isTDocumentoReferencedToUsuario(int codigoTDocumento) {
		for (BE_Usuario u : UsuarioC.listarUsuario())
			if (u.getIdTipoDocumento() == codigoTDocumento)
				return true;
		return false;
	}
}
