package historia;

import java.util.Date;

public class Historia {
	private int idHistoria;
	private int idMascota;
	private String patologia;
	private String vacunas;
	private String descripcion;
	private Date fecha;
	
	

	public Historia(int idMascota, String patologia, String vacunas, String descripcion) {
		this.idMascota = idMascota;
		this.patologia = patologia;
		this.vacunas = vacunas;
		this.descripcion = descripcion;
	}
	
	public Historia(int idHistoria,int idMascota, String patologia, String vacunas, String descripcion) {
		this.idHistoria = idHistoria;
		this.idMascota = idMascota;
		this.patologia = patologia;
		this.vacunas = vacunas;
		this.descripcion = descripcion;
	}
	
	public int getIdHistoria() {
		return idHistoria;
	}
	public void setIdHistoria(int idHistoria) {
		this.idHistoria = idHistoria;
	}
	public int getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}
	public String getPatologia() {
		return patologia;
	}
	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}
	public String getVacunas() {
		return vacunas;
	}
	public void setVacunas(String vacunas) {
		this.vacunas = vacunas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
}
