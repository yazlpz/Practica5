import app.*;
import exc.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MyApp {
    private app.ListaDinamica<Experto> expertos;
    private app.ListaDinamica<Agenda> agenda;

    public MyApp(){
        expertos = new ListaDinamica<>();
        agenda = new ListaDinamica<>();
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("expertos.dat"))) {
            oos.writeObject(expertos); // Suponiendo que 'expertos' es una lista de expertos
        } catch (IOException e) {
            TJOption.imprimePantalla("Error al guardar datos: " + e.getMessage());
        }
    
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("agendas.dat"))) {
            oos.writeObject(agenda); // Suponiendo que 'agenda' es una lista de compromisos
        } catch (IOException e) {
            TJOption.imprimePantalla("Error al guardar datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("expertos.dat"))) {
            expertos = (ListaDinamica<Experto>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo no existe, inicializamos la lista
            TJOption.imprimePantalla("No se encontraron datos de expertos, se iniciará con una lista vacía.");
            expertos = new ListaDinamica<>(); // Inicializa una nueva lista
        } catch (IOException | ClassNotFoundException e) {
            TJOption.imprimePantalla("Error al cargar datos de expertos: " + e.getMessage());
            expertos = new ListaDinamica<>(); // Inicializa una nueva lista si hay un error
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("agendas.dat"))) {
            agenda = (ListaDinamica<Agenda>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo no existe, inicializamos la lista
            TJOption.imprimePantalla("No se encontraron datos de agendas, se iniciará con una lista vacía.");
            agenda = new ListaDinamica<>(); // Inicializa una nueva lista
        } catch (IOException | ClassNotFoundException e) {
            TJOption.imprimePantalla("Error al cargar datos de agendas: " + e.getMessage());
            agenda = new ListaDinamica<>(); // Inicializa una nueva lista si hay un error
        }
    }

    public static String formatearTelefono(String numero) {
        numero = numero.replaceAll("\\D+", "");

        String codigoPais = "+52";
        String parte1 = numero.substring(0, 3);  // Primeros 3 dígitos
        String parte2 = numero.substring(3, 6);  // Siguientes 3 dígitos
        String parte3 = numero.substring(6);     // Últimos 4 dígitos

        return String.format("%s %s %s %s", codigoPais, parte1, parte2, parte3);
    }

    public Date leerFecha(String mensaje) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);  // Para que valide correctamente el formato
        Date fecha = null;
        boolean fechaValida = false;
        
        while (!fechaValida) {
            try {
                String fechaStr = TJOption.leerString(mensaje + " (Formato: dd/MM/yyyy):");
                fecha = formatter.parse(fechaStr);
                fechaValida = true;
            } catch (ParseException e) {
                TJOption.imprimePantalla("Fecha inválida. Por favor, ingrese una fecha en el formato dd/MM/yyyy.");
            }
        }
        return fecha;
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

    public void actualizarDatosExperto() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String nombre = TJOption.leerString("Ingrese el nombre del experto a actualizar");
        
        for (int i = 1; i <= expertos.size(); i++) {
            Experto experto = expertos.getItem(i);
            
            if (experto.getNombre().equalsIgnoreCase(nombre)) {
                experto.setEspecialdad(TJOption.leerString("Ingrese la nueva especialidad del experto (actual: " + experto.getEspecialdad() + ")"));
                experto.setAreaInteres(TJOption.leerString("Ingrese las nuevas áreas de interés del experto (actual: " + experto.getAreaInteres() + ")"));
                experto.setUbicacionFija(TJOption.leerString("Ingrese la nueva ubicación fija en coordenadas (actual: " + experto.getUbicacionFija() + ")"));
                experto.setContacto(TJOption.leerString("Ingrese el nuevo nombre de contacto (actual: " + experto.getContacto() + ")"));
    
                String numeroTelefonico;
                boolean esNumeroValido = false;
                do {
                    numeroTelefonico = TJOption.leerString("Ingrese el nuevo número telefónico del contacto (actual: " + experto.getTelefonoContacto() + ")");
                    if (numeroTelefonico.matches("\\d{10}")) {
                        esNumeroValido = true;
                    } else {
                        TJOption.imprimePantalla("El número debe tener 10 dígitos.");
                    }
                } while (!esNumeroValido);
    
                experto.setTelefonoContacto(formatearTelefono(numeroTelefonico));
                
                TJOption.imprimePantalla("Datos del experto actualizados con éxito.");
                break;
            }
            TJOption.imprimePantalla("Experto no encontrado.");
        }
        
    }

    public void eliminarExperto()throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String nombre = TJOption.leerString("Ingrese el nombre del experto a eliminar");
        for (int i = 1; i <= expertos.size(); i++) {
            if (expertos.getItem(i).getNombre().equalsIgnoreCase(nombre)) {
                expertos.delete(i);
                TJOption.imprimePantalla("Experto eliminado con éxito.");
                break;
            }
            TJOption.imprimePantalla("Experto no encontrado.");
        }
    }

    public String mostrarListaExpertos() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado{
        StringBuilder sb = new StringBuilder("Listado de expertos registrados\n");
        for (int i=1; i<=expertos.size(); i++) {
            sb.append(expertos.getItem(i)).append("\n");
        }
        return sb.toString();
    }

    public void agregarCompromisoAExperto() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado, ExcepcionDeListaLlena {
        String nombre = TJOption.leerString("Ingrese el nombre del experto para asignar el compromiso:");
        for (int i = 1; i <= expertos.size(); i++) {
            Experto experto = expertos.getItem(i);
    
            if (experto.getNombre().equalsIgnoreCase(nombre)) {
                Agenda nuevoCompromiso = new Agenda();
                Date fechaInicio = leerFecha("Ingrese la fecha de inicio del compromiso");
                Date fechaFin = leerFecha("Ingrese la fecha de fin del compromiso");

                if (fechaFin.before(fechaInicio)) {
                    TJOption.imprimePantalla("La fecha de fin no puede ser anterior a la fecha de inicio.");
                    break;
                }
    
                // Verificar si el nuevo compromiso entra en conflicto con compromisos existentes del mismo experto
                for (int j = 1; j <= agenda.size(); j++) {
                    Agenda compromisoExistente = agenda.getItem(j);
                    Date inicioExistente = compromisoExistente.getFechaInicio();
                    Date finExistente = compromisoExistente.getFechaFin();
    
                        if (fechaInicio.equals(inicioExistente) || fechaFin.equals(finExistente)) {
                            TJOption.imprimePantalla("Error: Ya tienes un compromiso agendado en esa fecha");
                            break;
                        }

                        if (compromisoExistente.getUbicacion() == nuevoCompromiso.getUbicacion() &&
                        (fechaInicio.equals(inicioExistente) || fechaFin.equals(finExistente))) {
                        TJOption.imprimePantalla("Error: Otro experto ya tiene un compromiso agendado en esa ubicación");
                        return;
                    }
                    
                }
                nuevoCompromiso.setFechaInicio(fechaInicio);
                nuevoCompromiso.setFechaFin(fechaFin);
                nuevoCompromiso.setUbicacion(TJOption.leerString("Ingrese la ubicación del lugar del compromiso"));
                nuevoCompromiso.setActividades(TJOption.leerString("Ingrese la descripción de la actividad"));

    
                // Agregar el compromiso a la lista de agenda
                agenda.add(nuevoCompromiso);
                TJOption.imprimePantalla("Compromiso agregado exitosamente al experto: " + experto.getNombre());
                return;
            }
        }
    
        TJOption.imprimePantalla("Experto no encontrado.");
    }

    public void actualizarCompromiso() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String nombreExperto = TJOption.leerString("Ingrese el nombre del experto para actualizar su compromiso:");
        Date fechaInicio = leerFecha("Ingrese la fecha de inicio del compromiso a actualizar");
    
        // Buscar el compromiso en la agenda
        for (int i = 1; i <= agenda.size(); i++) {
            Agenda compromisoExistente = agenda.getItem(i);
            Experto experto = expertos.getItem(i);
    
            // Verificar si el compromiso pertenece al experto y coincide la fecha de inicio
            if (experto.getNombre().equalsIgnoreCase(nombreExperto) &&
                compromisoExistente.getFechaInicio().equals(fechaInicio)) {
    
                // Permitir actualizar los detalles del compromiso
                TJOption.imprimePantalla("Compromiso encontrado. Actualice los detalles o presione Enter para mantener los actuales.");
    
                // Leer nueva fecha de fin
                Date nuevaFechaFin = leerFecha("Ingrese la nueva fecha de fin del compromiso (dejar vacío para mantener):");
                if (nuevaFechaFin != null) {
                    // Validar que la nueva fecha de fin no sea anterior a la de inicio
                    if (nuevaFechaFin.before(compromisoExistente.getFechaInicio())) {
                        TJOption.imprimePantalla("La nueva fecha de fin no puede ser anterior a la fecha de inicio.");
                        return;
                    }
                    compromisoExistente.setFechaFin(nuevaFechaFin);
                }
    
                // Leer nueva ubicación
                String nuevaUbicacion = TJOption.leerString("Ingrese la nueva ubicación del compromiso (dejar vacío para mantener):");
                if (!nuevaUbicacion.trim().isEmpty()) {
                    // Verificar conflictos de ubicación
                    for (int j = 1; j <= agenda.size(); j++) {
                        Agenda otroCompromiso = agenda.getItem(j);
                        
                        // Comprobar si el compromiso existente no es el mismo y si hay solapamiento de fechas
                        if (!otroCompromiso.equals(compromisoExistente) &&
                            otroCompromiso.getUbicacion().equalsIgnoreCase(nuevaUbicacion) &&
                            (compromisoExistente.getFechaInicio().before(otroCompromiso.getFechaFin()) &&
                             compromisoExistente.getFechaFin().after(otroCompromiso.getFechaInicio()))) {
                            TJOption.imprimePantalla("Conflicto de ubicación: la nueva ubicación ya está ocupada por otro compromiso.");
                            return;
                        }
                    }
                    compromisoExistente.setUbicacion(nuevaUbicacion);
                }
    
                // Leer nueva actividad
                String nuevaActividad = TJOption.leerString("Ingrese la nueva descripción de la actividad (dejar vacío para mantener):");
                if (!nuevaActividad.trim().isEmpty()) {
                    compromisoExistente.setActividades(nuevaActividad);
                }

                TJOption.imprimePantalla("Compromiso actualizado exitosamente.");
                return;
            }
        }
    
        TJOption.imprimePantalla("No se encontró ningún compromiso para el experto especificado en la fecha indicada.");
    }

    public void eliminarCompromiso() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String nombreExperto = TJOption.leerString("Ingrese el nombre del experto del compromiso a eliminar:");
        Date fechaInicio = leerFecha("Ingrese la fecha de inicio del compromiso a eliminar");
    
        // Buscar el compromiso en la agenda
        for (int i = 1; i <= agenda.size(); i++) {
            Agenda compromisoExistente = agenda.getItem(i);
            Experto experto = expertos.getItem(i);
    
            // Verificar si el compromiso pertenece al experto y coincide la fecha de inicio
            if (experto.getNombre().equalsIgnoreCase(nombreExperto) &&
                compromisoExistente.getFechaInicio().equals(fechaInicio)) {
    
                // Eliminar el compromiso
                agenda.delete(i);
                TJOption.imprimePantalla("Compromiso eliminado exitosamente.");
                return;
            }
        }
    
        TJOption.imprimePantalla("No se encontró ningún compromiso para el experto especificado en la fecha indicada.");
    }
    
    public String listarAgenda() throws ExcepcionDeListaVacia, ExcepcionDeElementoNoEncontrado {
        String nombreExperto = TJOption.leerString("Ingrese el nombre del experto para listar su agenda:");
    
    StringBuilder sb = new StringBuilder("Agenda de " + nombreExperto + ":");
    boolean hayCompromisos = false;

    // Buscar los compromisos en la agenda
        for (int i = 1; i <= agenda.size(); i++) {
            Agenda compromiso = agenda.getItem(i);
            Experto experto = expertos.getItem(i);

            // Verificar si el compromiso pertenece al experto
            if (experto.getNombre().equalsIgnoreCase(nombreExperto)) {
                hayCompromisos = true;
                sb.append(compromiso.toString()).append("\n");
            }
        }

        if (!hayCompromisos) {
            return "No hay compromisos agendados para " + nombreExperto + ".";
        }
        return sb.toString();
    }
}
    




