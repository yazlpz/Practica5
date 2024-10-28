import javax.swing.*;
import app.TJOption;
import exc.ExcepcionDeElementoNoEncontrado;
import exc.ExcepcionDeListaLlena;
import exc.ExcepcionDeListaVacia;

public class Main {
    static String[] opciones = {"Agregar nuevo experto", "Actualizar datos de experto", "Eliminar experto", "Mostrar la lista de expertos", "Agregar compromiso a la agenda de un experto", "Actualizar compromiso agendado", "Eliminar comprimiso", "Listar agenda de un experto", "Salir"};

    static void run() throws ExcepcionDeListaLlena, ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String opcion;
        MyApp app = new MyApp();
        app.cargarDatos();
        do {
            opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opcion", "Agendas de expertos en temas ambientales", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (opcion) {
                case "Agregar nuevo experto":
                    app.agregarNuevoExperto();
                    break;
                case "Actualizar datos de experto":
                    app.actualizarDatosExperto();
                    break;
                case "Eliminar experto":
                    app.eliminarExperto();
                    break;
                case "Mostrar la lista de expertos":
                String listaExpertos = app.mostrarListaExpertos();
                TJOption.imprimePantalla(listaExpertos);
                    break;    
                case "Agregar compromiso a la agenda de un experto":
                app.agregarCompromisoAExperto();
                    break;
                case "Actualizar compromiso agendado":
                app.actualizarCompromiso();
                    break;
                case "Eliminar comprimiso":
                app.eliminarCompromiso();
                    break;
                case "Listar agenda de un experto":
                String agendaListada = app.listarAgenda();
                TJOption.imprimePantalla(agendaListada);
                    break;
                default:
                    app.guardarDatos();
                    TJOption.imprimePantalla("Datos guardados con éxito. Cerrando la aplicación.");
                    TJOption.imprimePantalla("Hasta la próxima");
                    System.exit(0);
            }
        } while(!opcion.equals("Salir"));
    }
    public static void main(String[] args) throws ExcepcionDeListaLlena, ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        run();
    }
    
}