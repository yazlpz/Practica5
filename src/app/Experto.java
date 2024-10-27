package app;

public class Experto {
    private String nombre;
    private String especialdad;
    private String areaInteres;
    private String ubicacionFija;
    private String contacto;
    private String telefonoContacto;
    private ListaDinamica<Agenda> agenda;

    public Experto (String nombre, String especialdad, String areaInteres,
                    String ubicacionFija, String contacto, String telefonoContacto /*Agenga[] agenda*/){
        this.nombre = nombre;
        this.especialdad = especialdad;
        this.areaInteres = areaInteres;
        this.ubicacionFija = ubicacionFija;
        this.contacto = contacto;
        this.telefonoContacto = telefonoContacto;
        this.agenda = new ListaDinamica<>();
    }

    public boolean agregarCompromiso(Agenda compromiso){
        try{
            for(int i=1; i<= agenda.size(); i++){
                Agenda c = agenda.getItem(i);
                if (c.choca(agenda)){
                    System.out.println("No se puede  agregar el compromiso");
                    return false;
                }
            }
            agenda.add(compromiso);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarCompromiso(int index, String nuevasActividades, String nuevoEncargado, String nuevoTelefono) {
        try {
            Agenda compromiso = agenda.getItem(index);
            compromiso.setActividades(nuevasActividades);
            compromiso.setContacto(nuevoEncargado);
            compromiso.setTelefonoEncargado(nuevoTelefono);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCompromiso(int index) {
        try {
            agenda.delete(index);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listarAgenda() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            try {
                for (int i = 1; i <= agenda.size(); i++) {
                    System.out.println(agenda.getItem(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
}
