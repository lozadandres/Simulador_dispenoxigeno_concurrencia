/**
 * Representa a un astronauta como un hilo de ejecución independiente.
 */
public class Astronauta extends Thread {
    
    private final DispensadorOxigeno dispensador;

    public Astronauta(String nombre, DispensadorOxigeno dispensador) {
        super(nombre); // Asigna el nombre al hilo
        this.dispensador = dispensador;
    }

    @Override
    public void run() {
        System.out.println("Miembro de la tripulación " + getName() + " listo para recargar.");
        
        // Intentar acceder al recurso compartido
        // Si el dispensador está ocupado, este hilo se bloqueará aquí automáticamente
        dispensador.dispensar(getName());
        
        System.out.println("Miembro de la tripulación " + getName() + " retorna a sus labores.");
    }
}
