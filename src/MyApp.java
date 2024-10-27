import app.*;
import exc.*;

public class MyApp {
    private app.ListaDinamica<Experto> expertos;
    private app.ListaDinamica<Agenda> agenda;

    public MyApp(){
        expertos = new ListaDinamica<>();
        agenda = new ListaDinamica<>();
    }

    public static String formatearTelefono(String numero) {
        numero = numero.replaceAll("\\D+", "");

        String codigoPais = "+52";
        String parte1 = numero.substring(0, 3);  // Primeros 3 dígitos
        String parte2 = numero.substring(3, 6);  // Siguientes 3 dígitos
        String parte3 = numero.substring(6);     // Últimos 4 dígitos

        return String.format("%s %s %s %s", codigoPais, parte1, parte2, parte3);
    }

    public void agregarNuevoExperto() throws ExcepcionDeListaLlena{
        Experto experto = new Experto();
        experto.setNombre(TJOption.leerString("Ingrese su nombre"));
        experto.setEspecialdad(TJOption.leerString("Ingrese su especialidad"));
        experto.setAreaInteres(TJOption.leerString("Ingrese sus áreas de interés"));
        experto.setUbicacionFija(TJOption.leerString("Ingrese su ubicación fija en coordenadas geográficas"));
        experto.setContacto(TJOption.leerString("Ingrese el nombre del contacto del experto"));
        
        String numeroTelefonico;
        boolean esNumeroValido = false;

        do {
            numeroTelefonico = TJOption.leerString("Ingrese el número telefónico del contacto");
            if (numeroTelefonico.matches("\\d{10}")) {
                esNumeroValido = true;
            }
        } while (!esNumeroValido);

        experto.setTelefonoContacto(formatearTelefono(numeroTelefonico));
        expertos.add(experto);
        TJOption.imprimePantalla("Experto agregado con éxito.");
    }

    public void eliminarExperto(){
        
    }

    public String mostrarListaExpertos() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado{
        StringBuilder sb = new StringBuilder("Listado de expertos registrados:\n");
        for (int i=1; i<=expertos.size(); i++) {
            sb.append(expertos.getItem(i)).append("\n");
        }
        return sb.toString();
    }
}


