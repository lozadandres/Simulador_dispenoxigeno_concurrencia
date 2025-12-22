import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterfazSimulador extends JFrame {

    private final JPanel panelEstado;
    private final JLabel lblEstadoTexto;
    private final JTextArea areaLog;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public InterfazSimulador() {
        super("Simulador de Dispensador de Oxígeno");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior: Estado del Dispensador ---
        panelEstado = new JPanel();
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado del Dispensador"));
        panelEstado.setBackground(new Color(144, 238, 144)); // Verde claro (Libre)

        lblEstadoTexto = new JLabel("DISPONIBLE");
        lblEstadoTexto.setFont(new Font("Arial", Font.BOLD, 24));
        lblEstadoTexto.setForeground(Color.DARK_GRAY);
        panelEstado.add(lblEstadoTexto);

        add(panelEstado, BorderLayout.NORTH);

        // --- Panel Central: Log de Eventos ---
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Registro de Eventos"));

        add(scrollLog, BorderLayout.CENTER);
    }

    /**
     * Actualiza el color y texto del panel superior.
     * Thread-safety: Se asegura de correr en el EDT.
     */
    public void actualizarEstadoDispensador(boolean ocupado, String nombreAstronauta) {
        SwingUtilities.invokeLater(() -> {
            if (ocupado) {
                panelEstado.setBackground(new Color(255, 99, 71)); // Rojo tomate
                lblEstadoTexto.setText("EN USO POR: " + nombreAstronauta);
            } else {
                panelEstado.setBackground(new Color(144, 238, 144)); // Verde claro
                lblEstadoTexto.setText("DISPONIBLE");
            }
        });
    }

    /**
     * Agrega una línea al log.
     */
    public void agregarLog(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            String hora = timeFormat.format(new Date());
            areaLog.append("[" + hora + "] " + mensaje + "\n");
            // Auto-scroll al final
            areaLog.setCaretPosition(areaLog.getDocument().getLength());
        });
    }
}
