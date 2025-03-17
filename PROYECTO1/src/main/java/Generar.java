import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;

public class Generar extends JFrame {
    public static void main(String[] args){
        new Generar();
    }
    public Generar(){
        setTitle("Generer Reportes");
        setSize(500,400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lcui = new JLabel("Ingrese CUI");
        lcui.setBounds(200,30,100,25);

        JTextField fcui = new JTextField();
        fcui.setBounds(90,70,300,25);

        JButton transaccion = new JButton("Reporte Transaccion");
        transaccion.setBounds(90,130,300,30);

        JButton deposito = new JButton("Reporte Deposito");
        deposito.setBounds(90,190,300,30);

        JButton retiro = new JButton("Reporte Retiro");
        retiro.setBounds(90,250,300,30);

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

        transaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarTransaccion();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(retiro);
        add(deposito);
        add(transaccion);
        add(lcui);
        add(fcui);
        add(regresar);
        setVisible(true);


    }
    private static void generarTransaccion() throws IOException{
        String nombre = generarNombre("Transacciones");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,13);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Historial de Transacciones");
        contentStream.endText();
        contentStream.close();

        document.save(nombre);
        document.close();
    }

    private static void generarDeposito() throws IOException{
        String nombre = generarNombre("Deposito");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,13);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Depositos Realizados");
        contentStream.endText();
        contentStream.close();

        document.save(nombre);
        document.close();
    }

    private static void generarRetiro() throws IOException{
        String nombre = generarNombre("Retiros");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,13);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Retiros Realizados");
        contentStream.endText();
        contentStream.close();

        document.save(nombre);
        document.close();
    }

    private static String generarNombre(String tipo){
         SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd_HHmmss");
         String time = sim.format(new Date());
         return "reporte"+tipo+"_"+ time +".pdf";
    }

}