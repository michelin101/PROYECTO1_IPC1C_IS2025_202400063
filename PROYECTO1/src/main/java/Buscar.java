import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Buscar extends JFrame {
    private JTable tablaclientes;
    private DefaultListModel<String> modelocuentas;
    private JList<String> listacuentas;
    private JTextField campoCUI;

    public static void main(String[] args) {
        new Buscar();
    }

    public Buscar() {
        setTitle("Ver cliente");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        String[] columnas = {"CUI", "Nombre", "Apellido"};
        ArrayList<Registro.Cliente> clientes = Registro.getClientes();
        String[][] datos = new String[clientes.size()][3];
        for (int i = 0; i < clientes.size(); i++) {
            Registro.Cliente cliente = clientes.get(i);
            datos[i][0] = cliente.getCui();
            datos[i][1] = cliente.getNombre();
            datos[i][2] = cliente.getApellido();
        }

        tablaclientes = new JTable(datos, columnas);

        JScrollPane sclientes = new JScrollPane(tablaclientes);
        sclientes.setBounds(40, 20, 700, 150);

        JLabel lCui = new JLabel("CUI:");
        lCui.setBounds(50, 200, 50, 25);

        campoCUI = new JTextField();
        campoCUI.setBounds(100, 200, 250, 25);

        JLabel asociadas = new JLabel("Cuentas asociadas");
        asociadas.setBounds(180,250,150,25);

        JButton buscar = new JButton("Buscar cuentas asociadas");
        buscar.setBounds(400, 200, 200, 25);

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(0, 445, 100, 15);

        modelocuentas = new DefaultListModel<>();
        listacuentas = new JList<>(modelocuentas);
        JScrollPane scuentas = new JScrollPane(listacuentas);
        scuentas.setBounds(40, 290, 400, 80);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarcuentas();
            }
        });

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla pantalla = new Pantalla();
                pantalla.setVisible(true);
                dispose();
            }
        });


        add(sclientes);
        add(scuentas);
        add(lCui);
        add(buscar);
        add(campoCUI);
        add(regresar);
        add(asociadas);

        setVisible(true);

    }

    public void buscarcuentas(){
        String cuiIngresado = campoCUI.getText().trim();
        modelocuentas.clear();

        for (Registro.Cliente cliente : Registro.getClientes()){
            if (cliente.getCui().equals(cuiIngresado)){
                for (Registro.Cuenta cuenta : cliente.getCuentas()){
                    modelocuentas.addElement(cuenta.getId());
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No se encontraron cuesntas para el CUI ingresado");
    }
}
