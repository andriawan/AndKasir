/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.app;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author andriawan
 */
public class TesterClassic {
    
    public static void main(String[] args) throws IOException{
        
//        JFileChooser jf = new JFileChooser();
//        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        jf.showSaveDialog(null);
//        JOptionPane.showMessageDialog(null, jf.getSelectedFile().getAbsoluteFile());
            test();
        
    }
    
    public static void test() throws FileNotFoundException, IOException{
        
        String s;
        s = "laporan";
        
        File file = new File(s);
        
        JFileChooser jf = new JFileChooser();
        
        jf.setFileFilter(new FileNameExtensionFilter("*.pdf", "pdf"));
        jf.setSelectedFile(file);
        jf.showSaveDialog(null);
        
        PdfWriter writer = new PdfWriter(jf.getSelectedFile().toString() + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        try (Document document = new Document(pdf)) {
            document.add(
                    new Paragraph("hello andriawan this is awsome").setFont(font));
        }
        
        Desktop desk = Desktop.getDesktop();
        File fileOutput = new File(jf.getSelectedFile().toString() + ".pdf");
        desk.open(fileOutput);
    }
    
}
