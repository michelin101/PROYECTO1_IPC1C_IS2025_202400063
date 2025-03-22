import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

public class Generar extends JFrame {
    private static final Logger logger = LogManager.getLogger(Generar.class);
    public static void main(String[] args) {
        new Generar();
    }


    public Generar() {
        setTitle("Generer Reportes");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lcui = new JLabel("Ingrese CUI");
        lcui.setBounds(200, 30, 100, 25);

        JTextField fcui = new JTextField();
        fcui.setBounds(90, 70, 300, 25);

        JButton transaccion = new JButton("Reporte Transaccion");
        transaccion.setBounds(90, 130, 300, 30);

        JButton deposito = new JButton("Reporte Deposito");
        deposito.setBounds(90, 190, 300, 30);

        JButton retiro = new JButton("Reporte Retiro");
        retiro.setBounds(90, 250, 300, 30);

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(0, 345, 90, 15);
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
                    generarTransaccion(fcui.getText().trim());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarDeposito(fcui.getText().trim());
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        retiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarRetiro(fcui.getText().trim());
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
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

    private static void generarTransaccion(String fcui) throws IOException{
        String nombre = generarNombre("Transacciones");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        ThreadContext.put("usuario", "Sistema");
        ThreadContext.put("resultado", "Éxito");
        ThreadContext.put("detalles", "Reporte de transacciones generado para CUI: " + fcui);
        logger.info("Generar reporte de transacciones");
        ThreadContext.clearAll();

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,14);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Historial de Transacciones");
        contentStream.endText();;

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(25,730);

        float margin = 50;
        float yStart = 700;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        float rowHeight = 20;
        float colWidth = tableWidth / 6;

        String[] headers = { "ID", "Fecha", "Detalles","Debito", "Acreditado","Saldo disponible"};
        float xPosition = margin;
        yPosition -= rowHeight;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        for (String header : headers) {
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(header);
            xPosition += colWidth;
        }
        contentStream.endText();
        List<Registro.Transaccion> transacciones = obtenerT(fcui);
        for (Registro.Transaccion transaccion : transacciones) {
            xPosition = margin;
            yPosition -= rowHeight;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(transaccion.getId());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(transaccion.getFecha());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(transaccion.getDetalle());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(String.valueOf(transaccion.getDebito()));
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(String.valueOf(transaccion.getAcreditado()));
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(String.valueOf(transaccion.getSaldo()));
            contentStream.endText();
        }

        contentStream.close();
        document.save(nombre);
        document.close();

    }

    private static void generarDeposito(String fcui) throws IOException{
        String nombre = generarNombre("Deposito");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        ThreadContext.put("usuario", "Sistema");
        ThreadContext.put("resultado", "Éxito");
        ThreadContext.put("detalles", "Reporte de deposito generado para CUI: " + fcui);
        logger.info("Generar reporte de Deposito");
        ThreadContext.clearAll();

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,13);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Depositos Realizados");



        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(25,730);

        float margin = 50;
        float yStart = 700;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        float rowHeight = 20;
        float colWidth = tableWidth / 3;

        String[] headers = { "ID","Fecha","Monto"};
        float xPosition = margin;
        yPosition -= rowHeight;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        for (String header : headers) {
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(header);
            xPosition += colWidth;
        }
        contentStream.endText();
        List<Registro.Transaccion> depositos = obtenerD(fcui);
        for (Registro.Transaccion deposito : depositos) {
            xPosition = margin;
            yPosition -= rowHeight;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(deposito.getId());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(deposito.getFecha());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText("Q"+ deposito.getAcreditado());
            xPosition += colWidth;

        }

        contentStream.close();
        document.save(nombre);
        document.close();


    }

    private static void generarRetiro(String fcui) throws IOException{
        String nombre = generarNombre("Retiros");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        ThreadContext.put("usuario", "Sistema"); // O el usuario actual
        ThreadContext.put("resultado", "Éxito");
        ThreadContext.put("detalles", "Reporte de retiro generado para CUI: " + fcui);
        logger.info("Generar reporte de Retiro");
        ThreadContext.clearAll();

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA,13);
        contentStream.newLineAtOffset(25,750);
        contentStream.showText("Reporte de Retiros Realizados");

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(25,730);

        float margin = 50;
        float yStart = 700;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        float rowHeight = 20;
        float colWidth = tableWidth / 3;

        String[] headers = { "ID", "Fecha", "Monto"};
        float xPosition = margin;
        yPosition -= rowHeight;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        for (String header : headers) {
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(header);
            xPosition += colWidth;
        }
        contentStream.endText();
        List<Registro.Transaccion> retiros = obtenerR(fcui);
        for (Registro.Transaccion retiro : retiros) {
            xPosition = margin;
            yPosition -= rowHeight;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(retiro.getId());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText(retiro.getFecha());
            xPosition += colWidth;
            contentStream.newLineAtOffset(xPosition, 0);
            contentStream.showText("Q"+retiro.getDebito());
            xPosition += colWidth;
            contentStream.endText();
        }

        contentStream.close();
        document.save(nombre);
        document.close();

    }

    private static List<Registro.Transaccion> obtenerT(String fcui) {
        for (Registro.Cliente cliente : Registro.getClientes()) {
            if (cliente.getCui().equals(fcui)) {
                return cliente.getTransaccion();
            }
        }
        return new ArrayList<>();
    }

    private static List<Registro.Transaccion> obtenerD(String fcui) {
        List<Registro.Transaccion> transacciones = obtenerT(fcui);
        return transacciones.stream()
                .filter(transaccion -> transaccion.getAcreditado() > 0)
                .collect(Collectors.toList());
    }

    private static List<Registro.Transaccion> obtenerR(String fcui) {
        List<Registro.Transaccion> transacciones = obtenerT(fcui);
        return transacciones.stream()
                .filter(transaccion -> transaccion.getDebito() > 0)
                .collect(Collectors.toList());
    }

    private static String generarNombre(String tipo){
         SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd_HHmmss");
         String time = sim.format(new Date());
         return "reporte"+tipo+"_"+ time +".pdf";
    }

}