import java.util.Random;

/**
 * Recurso compartido que representa el dispensador de oxígeno.
 * utiliza la palabra clave 'synchronized' para garantizar acceso exclusivo
 * (Monitor).
 * Ahora actualiza también la interfaz gráfica.
 */
public class DispensadorOxigeno {

    private final InterfazSimulador interfaz;

    public DispensadorOxigeno(InterfazSimulador interfaz) {
        this.interfaz = interfaz;
    }

    public synchronized void dispensar(String astronauta) {
        // Actualizar GUI: Ocupado
        interfaz.agregarLog(astronauta + " >>> Ha obtenido acceso exclusivo.");
        interfaz.actualizarEstadoDispensador(true, astronauta);
        interfaz.agregarLog(astronauta + " ... Cargando oxígeno (ESTADO: CRÍTICO) ...");

        try {
            int tiempoCarga = new Random().nextInt(2000) + 1000;
            Thread.sleep(tiempoCarga);
        } catch (InterruptedException e) {
            interfaz.agregarLog(astronauta + " ! INTERRUMPIDO.");
            Thread.currentThread().interrupt();
        }

        // Actualizar GUI: Libre
        interfaz.agregarLog(astronauta + " <<< Fin de carga. Liberando dispensador.");
        interfaz.actualizarEstadoDispensador(false, "");
    }
}
