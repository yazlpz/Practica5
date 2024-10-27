import javax.swing.*;
import app.TJOption;
import exc.ExcepcionDeElementoNoEncontrado;
import exc.ExcepcionDeListaLlena;
import exc.ExcepcionDeListaVacia;

public class Main {
    static String[] opciones = {"Agregar nuevo experto", "Actualizar datos de experto", "Eliminar experto", "Mostrar la lista de expertos", "Agregar compromiso a la agenda de un experto", "SALIR"};

    static void run() throws ExcepcionDeListaLlena, ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String opcion;
        MyApp app = new MyApp();
        do {
            opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opcion", "Agendas de expertos en temas ambientales", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (opcion) {
                case "Agregar nuevo experto":
                    app.agregarNuevoExperto();
                    break;
                case "Actualizar datos de experto":
                    break;
                case "Eliminar experto":
                    break;
                case "Mostrar la lista de expertos":
                String listaExpertos = app.mostrarListaExpertos();  // Obtener la lista de directivos
                TJOption.imprimePantalla(listaExpertos);
                    break;    
                case "Agregar compromiso a la agenda de un experto":
                    break;
                default:
                    TJOption.imprimePantalla("Hasta la próxima");
                    System.exit(0);
            }
        } while(!opcion.equals("Salir"));
    }
    public static void main(String[] args) throws ExcepcionDeListaLlena, ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        run();
    }
    
}