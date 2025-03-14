import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pantalla_Principal {
    public static void main(String[] args) {
        new Pantalla().setVisible(true);
        Registro.getClientes();
    }
}

    class Pantalla extends JFrame {
        public Pantalla() {
            setTitle("Pantalla Principal");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            initComponentes();
        }

        private void initComponentes() {
            JPanel panel = new JPanel();
            panel.setLayout(null);

            JLabel bienvenido = new JLabel("Bienvenido");
            bienvenido.setFont(new Font("Arial", Font.BOLD,20));
            bienvenido.setBounds(200, 20, 250, 25);

            JButton registro = new JButton ("Registro de Usuario");
            registro.setBounds(150, 70, 200, 25);

            JButton crear = new JButton("Crear cuenta");
            crear.setBounds(150, 110, 200, 25);

            JButton retiro = new JButton("Retiros");
            retiro.setBounds(150, 150, 200, 25);

            JButton deposito = new JButton("Depositos");
            deposito.setBounds(150, 190, 200, 25);

            JButton buscar = new JButton("Buscar Cuentas Asociadas");
            buscar.setBounds(150, 230, 200, 25);

            JButton historial = new JButton("Historial de Transacciones");
            historial.setBounds(150, 270, 200, 25);

            JButton generar = new JButton("Generación de Reportes");
            generar.setBounds(150, 310, 200, 25);

            registro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Registro registro1 = new Registro();
                    registro1.setVisible(true);
                    dispose();
                }
            });
            crear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Crear_Cuenta crearCuenta1 = new Crear_Cuenta(Registro.getClientes());
                    crearCuenta1.setVisible(true);
                    dispose();
                }
            });
            retiro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Retiros retiros = new Retiros(Registro.getClientes());
                    retiros.setVisible(true);
                    dispose();
                }
            });
            deposito.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Deposito deposito1 = new Deposito(Registro.getClientes());
                    deposito1.setVisible(true);
                    dispose();
                }
            });
            buscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Buscar buscar1 = new Buscar();
                    buscar1.setVisible(true);
                    dispose();
                }
            });
            historial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            generar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            panel.add(bienvenido);
            panel.add(registro);
            panel.add(crear);
            panel.add(retiro);
            panel.add(deposito);
            panel.add(buscar);
            panel.add(historial);
            panel.add(generar);

            add(panel);
        }
    }