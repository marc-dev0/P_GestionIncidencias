package Entidades;

import java.util.Date;
public class BE_Especialista {
	private int codigo;
	private String nombres;
	private String apellidos;
	private String especialidad;
	private String anexo;
	private Date fechaIngreso;
	private int estado;
	/**
	 * @param codigo
	 * @param nombres
	 * @param apellidos
	 * @param especialidad
	 * @param anexo
	 * @param fechaIngreso
	 * @param estado
	 */
	public BE_Especialista(int codigo, String nombres, String apellidos,
			String especialidad, String anexo, Date fechaIngreso, int estado) {
		this.codigo = codigo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.anexo = anexo;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
	}
	
	public BE_Especialista(){
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
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
