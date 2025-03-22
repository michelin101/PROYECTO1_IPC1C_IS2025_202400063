import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Registro extends JFrame{
    public static void main(String[] args){
        new Registro();
    }
    private static final int MAX = 4;
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public Registro(){
        setTitle("crear cliente");
        setSize(500,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lCUI = new JLabel("CUI:");
        JTextField fCUI = new JTextField(13);

        lCUI.setBounds(230,30,100,30);
        fCUI.setBounds(70,60,350,30);

        JLabel lnombre = new JLabel("Nombre:");
        JTextField fnombre = new JTextField(0);

        lnombre.setBounds(220,110,100,30);
        fnombre.setBounds(70,140,350,30);

        JLabel lapellido = new JLabel("Apellido:");
        JTextField fapellido = new JTextField();
        lapellido.setBounds(220,190,100,30);
        fapellido.setBounds(70,220,350,30);

        JButton buttonC = new JButton("Crear");
        buttonC.setBounds(70,290,350,30);

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(0,345,90,15);

        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fCUI.getText().isEmpty() || fnombre.getText().isEmpty() || fapellido.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese primero los datos");
                }else {
                    crear(fCUI.getText(), fnombre.getText(), fapellido.getText());
                }
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

        add(lCUI);
        add(fCUI);
        add(lnombre);
        add(fnombre);
        add(lapellido);
        add(fapellido);
        add(new JLabel());
        add(buttonC);
        add(regresar);

        setVisible(true);
    }

    private static void crear(String cui, String nombre, String apellido){
        if (clientes.size() >= MAX){
            JOptionPane.showMessageDialog(null, "No se puede crear mas clientes", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Cliente cliente : clientes){
            if (cliente.getCui().equals(cui)){
                JOptionPane.showMessageDialog(null, "No se puede crear clientes con CUI duplicado. El CUI ingresado ya existe en el sistema", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        clientes.add(new Cliente(cui, nombre, apellido));
        JOptionPane.showMessageDialog(null, "Cliente creado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    static class Cliente {
        private String cui;
        private String nombre;
        private String apellido;
        private ArrayList<Cuenta> cuentas;
        private String id;
        private ArrayList<Transaccion> transacciones;

        public Cliente(String cui, String nombre, String apellido){
            this.cui = cui;
            this.nombre = nombre;
            this.apellido = apellido;
            this.cuentas = new ArrayList<>();
            this.transacciones = new ArrayList<>();
            this.id = "C"+(clientes.size() +1);
        }
        public String getId(){
            return id;
        }
        public String getCui(){
            return cui;
        }
        public String getNombre(){
            return nombre;
        }
        public String getApellido(){
            return apellido;
        }

        public ArrayList<Cuenta> getCuentas() {
            return cuentas;
        }

        public void agregarCuenta(Cuenta cuenta) {
            cuentas.add(cuenta);
        }
        public ArrayList<Transaccion> getTransaccion(){
            return transacciones;
        }
        public String getIdCuenta(){
            if (!cuentas.isEmpty()){
                return cuentas.get(0).getId();
            }
            return null;
        }
    }

    static class Cuenta {
        private String id;
        private double saldo;


        public Cuenta(String id, double saldo) {
            this.id = id;
            this.saldo = saldo;
        }

        public String getId() {
            return id;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
    }
    public static ArrayList<Cliente> getClientes(){
        return clientes;
    }
    public static class Transaccion{
        private String id;
        private String fecha;
        private String detalle;
        private double debito;
        private double acreditado;
        private double saldo;

        public Transaccion(String id, String fecha, String detalle, double debito, double acreditado, double saldo){
            this.id = id;
            this.fecha = fecha;
            this.detalle = detalle;
            this.debito = debito;
            this.acreditado = acreditado;
            this.saldo = saldo;

        }

        public String getId() {
            return id;
        }

        public String getFecha() {
            return fecha;
        }

        public String getDetalle() {
            return detalle;
        }

        public double getDebito() {
            return debito;
        }

        public double getAcreditado() {
            return acreditado;
        }

        public double getSaldo() {
            return saldo;
        }
    }
}

