import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Deposito extends JFrame {
    private static final Logger logger = LogManager.getLogger(Deposito.class);
    public static void main(String[] args) {
        new Deposito(Registro.getClientes());
    }

    private JComboBox<String> cCombo;
    private JTextField monto;
    private ArrayList<Registro.Cliente> clientes;

    public Deposito(ArrayList<Registro.Cliente> clientes) {
        this.clientes = clientes;

        setTitle("Depositos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        intcomoponentes();
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

    private void intcomoponentes() {
        setLayout(null);

        JLabel lCuenta = new JLabel("Cuenta: ");
        lCuenta.setBounds(225, 30, 300, 25);
        cCombo = new JComboBox<>();
        cCombo.setBounds(70, 60, 350, 25);
        cargarcuentas();

        JLabel lMonto = new JLabel("Monto: ");
        lMonto.setBounds(225, 100, 300, 25);
        monto = new JTextField();
        monto.setBounds(70, 130, 350, 25);

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(70, 200, 350, 25);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDepostio();
            }
        });
        add(lCuenta);
        add(cCombo);
        add(lMonto);
        add(monto);
        add(aceptar);
    }

    private void cargarcuentas() {
        for (Registro.Cliente cliente : clientes) {
            for (Registro.Cuenta cuenta : cliente.getCuentas()) {
                cCombo.addItem(cuenta.getId() + "-" + cliente.getNombre() + "-" + cliente.getApellido());
            }
        }
    }

    private void realizarDepostio() {
        String seleccionar = (String) cCombo.getSelectedItem();
        if (seleccionar == null) return;

        String cuentaId = seleccionar.split("-")[0].trim();
        String montoT = monto.getText();



        try {
            double monto1 = Double.parseDouble(montoT);
            if (monto1 <= 0) {
                JOptionPane.showMessageDialog(this, "El monto del deposito debe ser mayor a 0", "Advertensia", JOptionPane.WARNING_MESSAGE);
                ThreadContext.put("usuario", "Sistema");
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Depósito realizado exitosamente en la cuenta: " + cuentaId);
                logger.info("Realizar depósito");
                ThreadContext.clearAll();
                return;
            }
            for (Registro.Cliente cliente : clientes) {
                for (Registro.Cuenta cuenta : cliente.getCuentas()) {
                    if (cuenta.getId().equals(cuentaId)) {
                        double nuevosaldo = cuenta.getSaldo() + monto1;
                        cuenta.setSaldo(nuevosaldo);

                        String idTransaccion ="T"+ System.currentTimeMillis();
                        String fecha = java.time.LocalDateTime.now().toString();
                        Registro.Transaccion transaccion = new Registro.Transaccion(idTransaccion,fecha, "Deposito", 0,monto1,nuevosaldo);
                        cliente.getTransaccion().add(transaccion);
                        JOptionPane.showMessageDialog(this, "Deposito realizado exitosamente", "Inforamcion", JOptionPane.INFORMATION_MESSAGE);
                        ThreadContext.put("usuario", "Sistema");
                        ThreadContext.put("resultado", "Error");
                        ThreadContext.put("detalles", "Error al realizar depósito: ");
                        logger.error("Realizar depósito");
                        ThreadContext.clearAll();
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this,"no se encontro la cuenta seleccionada","Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}