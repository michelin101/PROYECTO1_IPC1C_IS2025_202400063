import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Historial extends JFrame{
    public static void main(String[] args){
        new Historial();
    }
    private JTable tablaTransacciones;
    private DefaultTableModel Tabla;
    private JTextField cId;
    private JLabel lcui,lnombre,lapellido;

    public Historial(){
        setTitle("Historial de transacciones");
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lId = new JLabel("ID usuario");
        lId.setBounds(50,30,150,25);

        cId = new JTextField();
        cId.setBounds(150, 30, 200,25);

        JButton mostrar = new JButton("Mostrar Transacciones");
        mostrar.setBounds(400,30,200,25);

        mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTransacciones();
            }
        });

        lcui = new JLabel();
        lcui.setBounds(80,80,100,25);

        lnombre = new JLabel();
        lnombre.setBounds(250,80,100,25);

        lapellido = new JLabel();
        lapellido.setBounds(450,80,100,25);

        String[] columnas = {"ID","Fecha","Detalles", "Debito","Acreditado","Sueldo disponible"};
        Tabla = new DefaultTableModel(columnas,0);
        tablaTransacciones = new JTable(Tabla);

        JScrollPane scrollPane = new JScrollPane(tablaTransacciones);
        scrollPane.setBounds(50,150,700,350);

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(0,540,200,20);
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla pantalla = new Pantalla();
                pantalla.setVisible(true);
                dispose();
            }
        });

        add(regresar);
        add(scrollPane);
        add(cId);
        add(lId);
        add(mostrar);
        add(lcui);
        add(lnombre);
        add(lapellido);
        setVisible(true);
    }

    private void buscarTransacciones(){
        String idIngresado = cId.getText().trim();
        Tabla.setRowCount(0);
        boolean transaccionesEncontradas = false;

        for (Registro.Cliente cliente : Registro.getClientes()) {
            for (Registro.Cuenta cuenta : cliente.getCuentas()){
                if (cuenta.getId().equals(idIngresado)){
                    for (Registro.Transaccion transaccion : cliente.getTransaccion()) {
                        Tabla.addRow(new Object[]{
                                transaccion.getId(),
                                transaccion.getFecha(),
                                transaccion.getDetalle(),
                                transaccion.getDebito(),
                                transaccion.getAcreditado(),
                                transaccion.getSaldo()
                        });

                    }
                    lcui.setText("CUI: " + cliente.getCui());
                    lnombre.setText("Nombre: "+ cliente.getNombre());
                    lapellido.setText("Apellido: "+ cliente.getApellido());

                    transaccionesEncontradas=true;
                    break;
                }
            }
        }
        if (!transaccionesEncontradas){
            JOptionPane.showMessageDialog(this,"No se encontraton transacciones para este ID");

        }
    }
}

