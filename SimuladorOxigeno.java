import javax.swing.SwingUtilities;

/**
 * Clase principal para lanzar la simulación de concurrencia con GUI.
 */
public class SimuladorOxigeno {

    public static void main(String[] args) {
        // Crear la GUI en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            InterfazSimulador gui = new InterfazSimulador();
            gui.setVisible(true);

            // Iniciar la lógica de simulación en un hilo separado para no congelar la GUI
            new Thread(() -> iniciarSimulacion(gui)).start();
        });
    }

    private static void iniciarSimulacion(InterfazSimulador gui) {
        gui.agregarLog("=== INICIO DE SIMULACIÓN ===");

        // 1. Crear el recurso compartido (Monitor) conectado a la GUI
        DispensadorOxigeno dispensadorCentral = new DispensadorOxigeno(gui);

        // 2. Crear múltiples hilos (Astronautas)
        Astronauta a1 = new Astronauta("Comandante Shepard", dispensadorCentral);
        Astronauta a2 = new Astronauta("Teniente Ripley", dispensadorCentral);
        Astronauta a3 = new Astronauta("Ingeniero Isaac", dispensadorCentral);
        Astronauta a4 = new Astronauta("Oficial Cooper", dispensadorCentral);
        Astronauta a5 = new Astronauta("Cadete Skywalker", dispensadorCentral);

        // 3. Iniciar los hilos
        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
    }
}
