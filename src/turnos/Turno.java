package turnos;

import java.util.Date;

public class Turno {
	
	private int idTurno;
	private int idUsuario;
	private String nombreApellido;
	private String nombreMascota;
	private String tipoMascota;
	private String telefono;
	private boolean consulta;
	private boolean urgencia;
	private Date fecha;
	private Date hora;
	private String diaYHora;
	private boolean vigente;
	
	public Turno(int idUsuario, String nombreApellido, String telefono, Date fecha) {
		this.idUsuario = idUsuario;
		this.nombreApellido = nombreApellido;
		this.telefono = telefono;
		this.fecha = fecha;
	}
	
	public Turno(int idTurno,int idUsuario,String nombreApellido,String nombreMascota,String tipoMascota,String telefono,Date fecha,Date hora) {
		this.idTurno = idTurno;
		this.idUsuario = idUsuario;
		this.nombreApellido = nombreApellido;
		this.nombreMascota = nombreMascota;
		this.tipoMascota = tipoMascota;
		this.telefono = telefono;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public String getNombreApellido() {
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}
	public String getTipoMascota() {
		return tipoMascota;
	}
	public void setTipoMascota(String tipoMascota) {
		this.tipoMascota = tipoMascota;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isConsulta() {
		return consulta;
	}
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}
	public boolean isUrgencia() {
		return urgencia;
	}
	public void setUrgencia(boolean urgencia) {
		this.urgencia = urgencia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDiaYHora() {
		return diaYHora;
	}

	public void setDiaYHora(String diaYHora) {
		this.diaYHora = diaYHora;
	}

	public boolean getVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	
//	public String toString() {
//		return "";
//	}
	
}
