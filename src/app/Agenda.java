package app;
import java.util.Date;
import java.io.Serializable;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fechaInicio;
    private Date fechaFin;
    private String ubicacion;
    private String actividades;
    private Experto contacto;
    private Experto telefonoContacto;

    public Agenda(){
        super();
    }
    public Agenda(Date fechaInicio, Date fechaFin, String ubicacion, String actividades, Experto contacto,
                 Experto telefonoContacto){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ubicacion = ubicacion;
        this.actividades = actividades;
        this.contacto = contacto;
        this.telefonoContacto = telefonoContacto;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getActividades() {
        return this.actividades;
    }
    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public Experto getContacto() {
        return this.contacto;
    }

    public Experto getTelefonoContacto() {
        return this.telefonoContacto;
    }

    @Override
	public String toString() {
		return super.toString() + "\nActividad: "+ actividades +
        "\nFecha de inicio: " + fechaInicio +
        "\nFecha de Fin: " + fechaFin +
        "\nUbicación: "+ ubicacion;
    }
}
