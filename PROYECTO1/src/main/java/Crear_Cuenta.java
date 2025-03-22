import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Crear_Cuenta extends JFrame {
    private static final Logger logger = LogManager.getLogger(Crear_Cuenta.class);
    public static void main(String[] args) {
        clientes = Registro.getClientes();
        new Crear_Cuenta(clientes);
    }

    private JComboBox<String> clienteB;
    private static final int MAXCUENTAS = 5;
    private static ArrayList<Registro.Cliente> clientes;
    private static int idCounter = 1;

    public Crear_Cuenta(ArrayList<Registro.Cliente> clientes) {
        this.clientes = clientes;
        setTitle("Crear Cuenta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        intComponentes();
        setVisible(true);


        JButton regresar = new JButton("Regresar");
        regresar.setBounds(0,345,90,15);
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla pantalla = new Pantalla();
                pantalla.setVisible(true);
                dispose();
            }
        });
        add(regresar);
    }
    private void intComponentes(){
        setLayout(null);

        JLabel lcliente = new JLabel("Cliente");
        lcliente.setBounds(220, 40, 100, 30);

        clienteB = new JComboBox<>();
        for (Registro.Cliente cliente : clientes) {
            clienteB.addItem(cliente.getCui() + "-" + cliente.getNombre() + cliente.getApellido());
        }
        clienteB.setBounds(100, 80, 300, 30);

        JButton cButton = new JButton("Crear");
        cButton.setBounds(100, 160, 300, 30);

        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearcuenta();
            }
        });

        add(lcliente);
        add(clienteB);
        add(cButton);
    }

    private void crearcuenta() {
        String seleccion = (String) clienteB.getSelectedItem();
        if (seleccion == null) return;
        String cui = seleccion.split("-")[0].trim();

        for (Registro.Cliente cliente : clientes) {
            if (cliente.getCui().equals(cui)) {
                if (cliente.getCuentas().size() >= MAXCUENTAS) {
                    JOptionPane.showMessageDialog(this, "No se pueden crear mas cuentas par este cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    ThreadContext.put("usuario", "Sistema");
                    ThreadContext.put("resultado", "Exito");
                    ThreadContext.put("detalles", "Cuenta creada para cliente.");
                    logger.warn("Crear cuenta");
                    ThreadContext.clearAll();
                    return;
                }
                String idCuenta = "C20C25" + idCounter++;
                cliente.agregarCuenta(new Registro.Cuenta(idCuenta, 0));
                JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente" + idCuenta, "Informacio", JOptionPane.INFORMATION_MESSAGE);
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Error");
                ThreadContext.put("detalles", "No se pudo crear la cuenta.");
                logger.warn("Crear cuenta");
                ThreadContext.clearAll();
                return;
            }
        }
    }
}


