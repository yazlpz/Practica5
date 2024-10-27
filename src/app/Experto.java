package app;

public class Experto {
    private String nombre;
    private String especialdad;
    private String areaInteres;
    private String ubicacionFija;
    private String contacto;
    private String telefonoContacto;
    private ListaDinamica<Agenda> agenda;

    public Experto() {
		super();
	}
    public Experto (String nombre, String especialdad, String areaInteres,
                    String ubicacionFija, String contacto, String telefonoContacto /*Agenga[] agenda*/){
        this.nombre = nombre;
        this.especialdad = especialdad;
        this.areaInteres = areaInteres;
        this.ubicacionFija = ubicacionFija;
        this.contacto = contacto;
        this.telefonoContacto = telefonoContacto;   
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialdad() {
        return especialdad;
    }
    public void setEspecialdad(String especialdad) {
        this.especialdad = especialdad;
    }

    public String getAreaInteres() {
        return areaInteres;
    }
    public void setAreaInteres(String areaInteres) {
        this.areaInteres = areaInteres;
    }

    public String getUbicacionFija() {
        return ubicacionFija;
    }
    public void setUbicacionFija(String ubicacionFija) {
        this.ubicacionFija = ubicacionFija;
    }

    public String getContacto() {
        return contacto;
    }
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public ListaDinamica<Agenda> getAgenda() {
        return this.agenda;
    }

    @Override
	public String toString() {
		return super.toString() + "\nNombre del experto: " + nombre +
        "\nEspecialidad: " + especialdad +
        "\nÁrea de interés: "+ areaInteres +
        "\nUbicación fija: "+ ubicacionFija +
        "\nContacto: "+ contacto +
        "\nTel. de su contacto: "+ telefonoContacto;
	}
}
