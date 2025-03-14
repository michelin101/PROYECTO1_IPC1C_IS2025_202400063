import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Retiros extends JFrame {
    private JComboBox<String> cCombo;
    private JTextField monto;
    private ArrayList<Registro.Cliente> clientes;

    public static void main(String[] args) {
        new Retiros(Registro.getClientes());
    }

    public Retiros(ArrayList<Registro.Cliente> clientes) {
        this.clientes = clientes;
        setTitle("Retiros");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        intcomponentes();
        setVisible(true);
    }

    private void intcomponentes() {
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
                realizarRetiro();
            }
        });
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

    private void realizarRetiro() {
        String seleccionar = (String) cCombo.getSelectedItem();
        if (seleccionar == null) return;
        String cuentaId = seleccionar.split("-")[0].trim();
        String montoT = monto.getText();
        try {
            double monto1 = Double.parseDouble(montoT);
            if (monto1 <= 0) {
                JOptionPane.showMessageDialog(this, "El monto del retiro debe se mayor a 0", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Registro.Cliente cliente : clientes) {
                for (Registro.Cuenta cuenta : cliente.getCuentas()) {
                    if (cuenta.getId().equals(cuentaId)) {
                        if (cuenta.getSaldo() < monto1) {
                            JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        cuenta.setSaldo(cuenta.getSaldo() - monto1);
                        JOptionPane.showMessageDialog(this, "Retiro realizado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontro la cuenta seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
