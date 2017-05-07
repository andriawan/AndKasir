/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.model.ItemStruk;
import andriawan.kasir.model.Struk;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import simple.escp.SimpleEscp;
import simple.escp.Template;
import simple.escp.data.DataSources;
import simple.escp.json.JsonTemplate;
import simple.escp.swing.PrintPreviewPane;

/**
 *
 * @author andriawan
 */
public class StrukController {
    
    private static StrukController instance = new StrukController();
    
    public void printStruk(Struk struk) throws FileNotFoundException, IOException{
        Template template = new JsonTemplate(new FileInputStream("template.json"));
        SimpleEscp escp = new SimpleEscp();
        escp.print(template, DataSources.from(struk));
    }
    
    public static void previewCetakStruk(Struk struk) throws IOException{
        try {
            Template template = new JsonTemplate(new FileInputStream("./template.json"));
            PrintPreviewPane preview;
            preview = new PrintPreviewPane(template, null, struk);
            JFrame framePriview = new JFrame("Struk");
            framePriview.setLayout(new BorderLayout());
            framePriview.add(preview, BorderLayout.CENTER);

            framePriview.pack();
            framePriview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            framePriview.setVisible(true);
            framePriview.setLocationRelativeTo(null);
        } catch (HeadlessException | IOException ex) {
        }
    }
    
    //Singleton
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
    
    public static void main(String[] args) throws IOException {
//        ItemStruk is = new ItemStruk("udang", 3, new java.lang.Integer("100000"), new Integer(2000));
//        ItemStruk is2 = new ItemStruk("udang", 3, new java.lang.Integer("100000"), new Integer(2000));
//        ArrayList<ItemStruk> ar = new ArrayList<>();
//        ar.add(is);
//        ar.add(is2);
//        Struk st = new Struk(20, 20000, 20000, 29, ar, 30, "Andriawan", "xxxx");
//        previewCetakStruk(st);
    }
    
}