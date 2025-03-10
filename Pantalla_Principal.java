import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Principal {
    public static void main(String[] args) {
        new Pantalla().setVisible(true);
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

            JLabel bienvenido = new JLabel("Bienvenido Administrador");
            bienvenido.setFont(new Font("Arial", Font.BOLD,20));
            bienvenido.setBounds(120, 40, 250, 25);

            JButton registro = new JButton ("<html><center>Registro de Usuario</center></html>");
            registro.setBounds(30, 100, 125, 45);

            JButton crear = new JButton("Crear cuenta");
            crear.setBounds(180, 100, 125, 45);

            JButton retiro = new JButton("Retiros");
            retiro.setBounds(330, 100, 125, 45);

            JButton deposito = new JButton("Depositos");
            deposito.setBounds(30, 180, 125, 45);

            JButton buscar = new JButton("<html><center>Buscar Cuentas </center><center>Asociadas</center></html>");
            buscar.setBounds(180, 180, 125, 45);

            JButton historial = new JButton("<html><center>Historial de Transacciones<center/></html>");
            historial.setBounds(330, 180, 125, 45);

            JButton generar = new JButton("<html><center>Generación de Reportes</center></html>");
            generar.setBounds(180, 260, 125, 45);

            registro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Registro registro1 = new Registro();
                    registro1.setVisible(true);
                }
            });
            crear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            retiro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            deposito.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            buscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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

