/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.LaporanDaoImpl;
import andriawan.kasir.model.Laporan;
import andriawan.kasir.view.LaporanForm;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author andriawan
 */
public class LaporanController {

    /**
     *
     * instance of LaporanController
     * singelton pattern
     */
    public static LaporanController lc = new LaporanController();

    /**
     *
    *  instance of LaporanForm
     * singelton pattern
     * 
     */
    public static LaporanForm lf = new LaporanForm();

    /**
     *
     *  instance of JFileChooser
     * singelton pattern
     * 
     */
    public static JFileChooser jfc = new JFileChooser();

    /**
     *
     * mendapatkan list laporan dari kelas Laporan dengan rentang tanggal tertentu
     * 
     * @param tgl1
     * @param tgl2
     * @return
     */
    public List<Laporan> getReport(String tgl1, String tgl2) {
        return new LaporanDaoImpl().getReport(tgl1, tgl2);
    }

    /**
     *
     * mendapatkan list laporan dari kelas Laporan dengan rentang 1 hari
     * @param sql
     * @return
     */
    public List<Laporan> getDailyReport(String sql) {
        return new LaporanDaoImpl().getDailyReport(sql);
    }

    /**
     *
     * * mendapatkan list laporan dari kelas Laporan dengan rentang 1 bulan
     * @param sql
     * @return
     */
    public List<Laporan> getMonthlyReport(String sql) {
        return new LaporanDaoImpl().getMonthlyReport(sql);
    }

    /**
     *
     * * mendapatkan list laporan dari kelas Laporan dengan rentang 1 tahun
     * @param sql
     * @return
     */
    public List<Laporan> getYearlyReport(String sql) {
        return new LaporanDaoImpl().getYearlyReport(sql);
    }

    /**
     *
     * prosedur mencetak PDF dari tabel laporan
     * 
     * @param tgl digunakan untuk penamaan file
     * @param tl Jtabel referensi yang akan dijadikan konten tabel
     * @param labelTanggal sebagai heading dokumen
     * @param pendapatan jumlah pendapatan selamar rentang tanggal tertentu
     * @param barangMasuk jumlah barang masuk
     * @param barangKeluar jumlah barang keluar
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void printReportToPDF
        (String tgl, 
                JTable tl,
                String labelTanggal, 
                String pendapatan,
                String barangMasuk,
                String barangKeluar) throws FileNotFoundException, IOException {
            
        // membuat Object file sebagai PDF file
        File file = new File("laporan_windy_collection_" + tgl);

        // memanggil swing object JFile chooser untuk memilih direktori/folder
        // penyimpanan
        JFileChooser jf = getInstanceJFileChooser();
        // Setting agar hanya mendukung ekstensi .pdf
        jf.setFileFilter(new FileNameExtensionFilter("*.pdf", "pdf"));
        // secara default, nama file akan automatis terisi dengan nama toko
        // dan tanggal laporan dibuat serta rentang tanggal laporan
        jf.setSelectedFile(file);
        // memunculkan dialog file chooser
        int result = jf.showSaveDialog(null);

        if (JFileChooser.APPROVE_OPTION == result) {

            // implementasi pemakaian library itext7
            // membuat object PdfWriter dengan parameter String dari path nama 
            // pdf file ditambah ekstensi .pdf
            PdfWriter writer = new PdfWriter(jf.getSelectedFile().toString() + ".pdf");
            // Membuat Object PdfDocument sebagai implementasi lebih lanjut
            // penggunaan library itext
            PdfDocument pdf = new PdfDocument(writer);

            // pemilihan font
            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            // try-catch
            try (Document document = new Document(pdf, PageSize.A4.rotate())) {

                document.setMargins(30, 30, 30, 30);

                float[] columnCount = {0.6f, 1f, 0.5f, 0.5f, 0.5f, 0.5f, 1f, 1f, 0.5f};
                UnitValue[] uv = UnitValue.createPercentArray(columnCount);
                Table table = new Table(uv);
                
                table.setPaddings(2f, 1f, 1f, 1f);
                table.setWidthPercent(100);
                table.addStyle(
                        new Style()
                                .setFontSize(9)
                                .setTextAlignment(TextAlignment.LEFT)
                                .setPadding(20)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setHorizontalAlignment(HorizontalAlignment.CENTER));
                
                // HEADER COLUMN 1
                table.addHeaderCell(
                        new Cell()
                                .add("ID Transaksi")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 2
                table.addHeaderCell(
                        new Cell()
                                .add("Tanggal Transaksi")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 3
                table.addHeaderCell(
                        new Cell()
                                .add("Nama Petugas")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 4
                table.addHeaderCell(
                        new Cell()
                                .add("ID Petugas")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 5
                table.addHeaderCell(
                        new Cell()
                                .add("Jabatan")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 6
                table.addHeaderCell(
                        new Cell()
                                .add("Kode Barang")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 7
                table.addHeaderCell(
                        new Cell()
                                .add("Nama Barang")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 8
                table.addHeaderCell(
                        new Cell()
                                .add("Harga")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                
                // HEADER COLUMN 9
                table.addHeaderCell(
                        new Cell()
                                .add("Jumlah")
                                .setFont(font)
                                .setFontSize(11)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));

                for (int i = 0; i < tl.getRowCount(); i++) {
                    
                    // CELL COLUMN 1
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 0).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 2
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 1).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 3
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 7).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                                                
                    // CELL COLUMN 4
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 2).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 5
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 8).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 6
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 3).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 7
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 4).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 8
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 5).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                    
                    // CELL COLUMN 9
                    table.addCell(
                            new Cell()
                                .add(tl.getValueAt(i, 6).toString())
                                .setFontSize(10)
                                .setFont(font)
                                .setPaddings(5, 5, 5, 5)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                .setTextAlignment(TextAlignment.CENTER));
                }

                // element dalam dokumen diatur di sini
                document.add(
                        new Paragraph("Laporan Posisi Keuangan dan Barang Windy Collection")
                                .setFont(font)
                                .setFontSize(20)
                                .setTextAlignment(TextAlignment.CENTER));
                
                document.add(
                        new Paragraph("Per Tanggal:")
                                .setFont(font)
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.CENTER));
                
                document.add(
                        new Paragraph(labelTanggal)
                                .setFont(font)
                                .setBold()
                                .setFontSize(12)
                                .setTextAlignment(TextAlignment.CENTER)
                                .setMarginBottom(10));
                
                document.add(table);
                
                document.add(
                        new Paragraph("Total Pendapatan")
                                .setMarginTop(20)
                                .setFont(font)
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));
                
                document.add(
                        new Paragraph(pendapatan)
                                .setFont(font)
                                .setBold()
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));
                
                document.add(
                        new Paragraph("Total Barang Masuk")
                                .setFont(font)
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));
                
                document.add(
                        new Paragraph(barangMasuk)
                                .setFont(font)
                                .setBold()
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));
                
                document.add(
                        new Paragraph("Total Barang Keluar")
                                .setFont(font)
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));
                
                document.add(
                        new Paragraph(barangKeluar)
                                .setFont(font)
                                .setBold()
                                .setFontSize(14)
                                .setTextAlignment(TextAlignment.RIGHT));

                // selalu tutup object document untuk membebaskan sumber daya (memori)
                document.close();

                // sebagai tambahan, setelah object pdf dibuat, jika terinstall aplikasi
                // pembaca pdf, sistem akan menampilkannya
                Desktop desk = Desktop.getDesktop();
                File fileOutput = new File(jf.getSelectedFile().toString() + ".pdf");
                desk.open(fileOutput);

            }
        } else {
            jf.setVisible(true);
        }
    }

    /**
     *
     * @return object dari JFileChooser menggunakan teknik singleton
     */
    public static JFileChooser getInstanceJFileChooser() {

        try {
            if (jfc == null) {
                jfc = new JFileChooser();
            }
            return jfc;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     *
     * @return object dari LaporanController menggunakan teknik singleton
     */
    public static LaporanController getInstanceLaporanController() {

        try {
            if (lc == null) {
                lc = new LaporanController();
            }
            return lc;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     *
     * @return object dari LaporanForm menggunakan teknik singleton
     */
    public static LaporanForm getInstanceLaporanForm() {
        try {
            if (lf == null) {
                lf = new LaporanForm();
            }
            return lf;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}