import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pantalla_Principal {
    static final Logger logger1 = LogManager.getLogger(Pantalla.class);
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

        JButton deposito = new JButton("Deposito");
        deposito.setBounds(150, 150, 200, 25);

        JButton retiro = new JButton("Retiro");
        retiro.setBounds(150, 190, 200, 25);

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
                ThreadContext.put("usuario", "Sistema");                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo Crear cuenta de usuario.");
                Pantalla_Principal.logger1.info("Crear cuenta de usuario");
                ThreadContext.clearAll();
            }
        });
        retiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Retiros retiros = new Retiros(Registro.getClientes());
                retiros.setVisible(true);
                dispose();
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo registro de usuario.");
                Pantalla_Principal.logger1.info("Registro de usuario");
                ThreadContext.clearAll();
            }
        });
        deposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deposito deposito1 = new Deposito(Registro.getClientes());
                deposito1.setVisible(true);
                dispose();
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo Deposito de usuario.");
                Pantalla_Principal.logger1.info("Deposito de usuario");
                ThreadContext.clearAll();
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar buscar1 = new Buscar();
                buscar1.setVisible(true);
                dispose();
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo buscar de usuario.");
                Pantalla_Principal.logger1.info("Buscar usuario");
                ThreadContext.clearAll();
            }
        });
        historial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Historial historial1 = new Historial();
                historial1.setVisible(true);
                dispose();
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo historial de usuario.");
                Pantalla_Principal.logger1.info("Historial de usuario");
                ThreadContext.clearAll();
            }
        });
        generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Generar generar1 = new Generar();
                generar1.setVisible(true);
                dispose();
                ThreadContext.put("usuario", "Sistema"); // O el usuario actual
                ThreadContext.put("resultado", "Éxito");
                ThreadContext.put("detalles", "Abriendo genera de usuario.");
                Pantalla_Principal.logger1.info("Generar de usuario");
                ThreadContext.clearAll();
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
