/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.model.Struk;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import simple.escp.SimpleEscp;
import simple.escp.Template;
import simple.escp.data.DataSources;
import simple.escp.json.JsonTemplate;
import simple.escp.swing.PrintPreviewPane;

/**
 *
 * @author andriawan
 * 
 * Class ini merupakan controller dari Model Struk. Menangani prosedur
 * printing menggunakan printer dot matrix.
 * 
 * Library yang digunakan
 * - simple-escp-0.5.3
 * - javax.json-1.0.4
 * - javax.json-api-1.0
 * 
 * 
 */
public class StrukController {
    
    // field variable instance digunakan untuk menampung singleton object
    // StrukController
    private static StrukController instance = new StrukController();

    /**
     *
     * melakukan printing secara langsung jika sistem menemukan printer aktif
     * perhatikan, jika tidak ada printer yang terdeteksi, maka akan melempar
     * nullpointer exception yang akan menginformasikan ke user melalui Message dialog
     * 
     * @param struk Objek dari Struk class
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void printStruk(Struk struk) throws FileNotFoundException, IOException{
        
        new SimpleEscp().print(
                new JsonTemplate(
                        new FileInputStream("template.json")),
                            DataSources.from(struk));
    }

    /**
     *
     * melakukan preview printing sebelum dicetak
     * 
     * @param struk
     * @throws IOException
     */
    public static void previewCetakStruk(Struk struk) throws IOException{
        try {
            Template template = new JsonTemplate(new FileInputStream("template.json"));
            PrintPreviewPane preview;
            preview = new PrintPreviewPane(template, null, struk);
            JFrame framePreview = new JFrame("Struk");
            framePreview.setLayout(new BorderLayout());
            framePreview.add(preview, BorderLayout.CENTER);

            framePreview.pack();
            framePreview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            framePreview.setVisible(true);
            framePreview.setLocationRelativeTo(null);
        } catch (HeadlessException | IOException ex) {
        } catch (NullPointerException n){
            JOptionPane.showMessageDialog(null, 
                    "Kesalahan: Pastikan Printer aktif dan bekerja dengan baik", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * Singleton sebagai langkah penghematan memori
     * 
     * @return instance of StrukController
     */
    public static StrukController getInstance(){
        try {
            if(instance == null){
            instance = new StrukController();
        }
            return instance;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}