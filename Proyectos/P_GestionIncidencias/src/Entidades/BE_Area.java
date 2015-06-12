package Entidades;

public class BE_Area {
	private int codigo;
	private String nombre;
	private String descripcion;
	private String nombreCorto;
	private String nombreLargo;
	private int estado;

	public BE_Area(int codigo, String nombre, String descripcion, String nombreCorto, String nombreLargo, int estado)
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombreCorto = nombreCorto;
		this.nombreLargo = nombreLargo;
		this.estado = estado;
	}
	
	public BE_Area(){
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
