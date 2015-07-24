package Entidades;

public class BE_TipoDocumento {
	private int codigo;
	private String descripcion;
	private String abreviacion;
	private int estado;

	public BE_TipoDocumento(int codigo, String descripcion, String abreviacion,
			int estado) {

		this.codigo = codigo;
		this.descripcion = descripcion;
		this.abreviacion = abreviacion;
		this.estado = estado;
	}

	public BE_TipoDocumento() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
