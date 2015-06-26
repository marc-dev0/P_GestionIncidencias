package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BE_Usuario {
	private int codigo;
	private String nombre;
	private String apellidos;
	private int idTipoDocumento;
	private String documento;
	private int codigo_area;
	private String area;
	private String correo;
	private String telefono;
	private Date fechaIngreso;
	private int estado;
	
	public BE_Usuario(int codigo, String nombre, String apellidos, int idDocumento, String documento, 
					  int codigo_area, String area, String correo, String telefono, Date fecha, int estado){
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.idTipoDocumento = idDocumento;
		this.documento = documento;
		this.codigo_area = codigo_area;
		this.area = area;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaIngreso = fecha;
		this.estado = estado;
	}
	
	public BE_Usuario(){
		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public int getCodigoArea() {
		return codigo_area;
	}

	public void setCodigoArea(int area) {
		this.codigo_area = area;
	}

	public String getArea(){
		return area;
	}
	
	public void setArea(String area){
		this.area = area;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}