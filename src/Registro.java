import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Registro extends JFrame{
    private static final int MAX = 4;
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public Registro(){
        setTitle("crear cliente");
        setSize(500,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lCUI = new JLabel("CUI:");
        JTextField fCUI = new JTextField();
        lCUI.setBounds(230,40,100,30);
        fCUI.setBounds(70,70,350,30);

        JLabel lnombre = new JLabel("Nombre:");
        JTextField fnombre = new JTextField();
        lnombre.setBounds(220,120,100,30);
        fnombre.setBounds(70,150,350,30);

        JLabel lapellido = new JLabel("Apellido:");
        JTextField fapellido = new JTextField();
        lapellido.setBounds(220,200,100,30);
        fapellido.setBounds(70,230,350,30);

        JButton button = new JButton("Crear");
        button.setBounds(70,300,350,30);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fCUI.getText().isEmpty() || fnombre.getText().isEmpty() || fapellido.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese primero los datos");
                }else {
                    crear(fCUI.getText(), fnombre.getText(), fapellido.getText());
                }
            }
        });

        add(lCUI);
        add(fCUI);
        add(lnombre);
        add(fnombre);
        add(lapellido);
        add(fapellido);
        add(new JLabel());
        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        public Cliente(String cui, String nombre, String apellido){
            this.cui = cui;
            this.nombre = nombre;
            this.apellido = apellido;
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

    }
}
