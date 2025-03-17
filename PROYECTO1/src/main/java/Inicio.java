
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio{
    private static final String USERNAME = "AdminIPC1C";
    private static final String PASSWORD = "ipc1c1s2025";

    public static void main(String[] args) {
        JFrame marco = new JFrame("Inicion de Sesión");
        marco.setSize(500, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        marco.add(panel);
        lugarcomponentes(panel, marco);
        marco.setVisible(true);

    }

    private static void lugarcomponentes(JPanel panel, JFrame marco) {
        panel.setLayout(null);

        JLabel log = new JLabel("Login");
        log.setBounds(220, 35, 80, 25);
        log.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(log);
        JLabel userl = new JLabel("Username");
        userl.setBounds(220, 100, 80, 20);
        panel.add(userl);

        JTextField usert = new JTextField(20);
        usert.setBounds(175, 120, 150, 20);
        panel.add(usert);

        JLabel contral = new JLabel("Password");
        contral.setBounds(220, 180, 80, 10);
        panel.add(contral);

        JPasswordField contrat = new JPasswordField(20);
        contrat.setBounds(175, 200, 150, 20);
        panel.add(contrat);
        JButton boton = new JButton("Login");
        boton.setBounds(210, 300, 80, 20);
        panel.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usert.getText();
                String password = new String(contrat.getPassword());
                if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                    JOptionPane.showMessageDialog(panel, "Bienvenido" + username);
                    Pantalla pantallap = new Pantalla();
                    pantallap.setVisible(true);
                    marco.dispose();

                } else {
                    JOptionPane.showMessageDialog(panel, "Error: usuario o contraseña incorrecta");
                }}
        });
    }
}