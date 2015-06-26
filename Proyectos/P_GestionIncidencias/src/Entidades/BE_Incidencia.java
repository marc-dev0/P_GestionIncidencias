package Entidades;
import java.util.Date;

import Entidades.*;
public class BE_Incidencia {
	int codigoincidencia, codigoUsuario, codigoEspecialista, codigoTipoIncidencia, tiempoEstimado, tiempoReal,
		estado;
	String descripcion, observacion;
	Date fechaRegistro, fechaInicio, fechaFin;
	//JTable
	String nombreUsuario, nombreEspecialista, nombreTipoIncidencia;
	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param nombreCorto
	 * @param nombreLargo
	 * @param estado
	 * @param codigoincidencia
	 * @param codigoUsuario
	 * @param codigoEspecialista
	 * @param codigoTipoIncidencia
	 * @param observacion
	 * @param tiempoEstimado
	 * @param tiempoReal
	 * @param fechaRegistro
	 * @param fechaInicio
	 * @param fechaFin
	 * @param nombreUsuario
	 * @param nombreEspecialista
	 * @param nombreTipoIncidencia
	 */
	public BE_Incidencia(int codigoIncidencia, int codigoUsuario, int codigoEspecialista,
			int codigoTipoIncidencia, String descripcion, String observacion, int tiempoEstimado, 
			int tiempoReal, Date fechaRegistro, Date fechaInicio, Date fechaFin, String nombreUsuario, 
			String nombreEspecialista, String nombreTipoIncidencia) {
		this.codigoincidencia = codigoIncidencia;
		this.codigoUsuario = codigoUsuario;
		this.codigoEspecialista = codigoEspecialista;
		this.codigoTipoIncidencia = codigoTipoIncidencia;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.tiempoEstimado = tiempoEstimado;
		this.tiempoReal = tiempoReal;
		this.fechaRegistro = fechaRegistro;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombreUsuario = nombreUsuario;
		this.nombreEspecialista = nombreEspecialista;
		this.nombreTipoIncidencia = nombreTipoIncidencia;
	}
	
	
	public BE_Incidencia(){
		
	}
	
	public int getCodigoincidencia() {
		return codigoincidencia;
	}
	public void setCodigoincidencia(int codigoincidencia) {
		this.codigoincidencia = codigoincidencia;
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public int getCodigoEspecialista() {
		return codigoEspecialista;
	}
	public void setCodigoEspecialista(int codigoEspecialista) {
		this.codigoEspecialista = codigoEspecialista;
	}
	public int getCodigoTipoIncidencia() {
		return codigoTipoIncidencia;
	}
	public void setCodigoTipoIncidencia(int codigoTipoIncidencia) {
		this.codigoTipoIncidencia = codigoTipoIncidencia;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public int getTiempoReal() {
		return tiempoReal;
	}
	public void setTiempoReal(int tiempoReal) {
		this.tiempoReal = tiempoReal;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreEspecialista() {
		return nombreEspecialista;
	}
	public void setNombreEspecialista(String nombreEspecialista) {
		this.nombreEspecialista = nombreEspecialista;
	}
	public String getNombreTipoIncidencia() {
		return nombreTipoIncidencia;
	}
	public void setNombreTipoIncidencia(String nombreTipoIncidencia) {
		this.nombreTipoIncidencia = nombreTipoIncidencia;
	}
	
}
