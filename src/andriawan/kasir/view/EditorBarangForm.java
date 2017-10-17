/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.view;

import andriawan.kasir.controller.BarangController;
import andriawan.kasir.controller.UserLoginController;
import andriawan.kasir.model.Barang;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilities.Formater;

/**
 *
 * @author andriawan
 */
public class EditorBarangForm extends javax.swing.JFrame {

    /**
     * Creates new form EditorBarangForm
     */
    public EditorBarangForm() {
        initComponents();
        
        txtIdBarang.setVisible(false);
        
        // SET ICON RESOURCE
        ImageIcon iconBtnUpdateBarang = new ImageIcon(
                new ImageIcon("resources/insert.png").getImage().
        getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        btnUpdateBarangFrame.setIcon(iconBtnUpdateBarang);
        
        ImageIcon iconBtnTambahBarang = new ImageIcon(
                new ImageIcon("resources/check.png").getImage().
        getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        btnTambahBarangFrame.setIcon(iconBtnTambahBarang);
        
        ImageIcon iconBatal = new ImageIcon(
                new ImageIcon("resources/delete.png").getImage().
        getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        btnBatalTambahBarang.setIcon(iconBatal);
        
    }
    
    public void setTxtFieldIdBarang(String set){
        this.txtIdBarang.setText(set);
    }
    
    public void setDisableCheck(){
        this.jCheckTanggal.setSelected(false);
        this.dateTglMasuk.setEnabled(false);
    }
    
    public void setTxtFieldNamaBarang(String set){
        this.txtFieldNamaBarang.setText(set);
    
    }
    
    public void setTxtFieldKodeBarang(String set){
        this.txtFieldKodeBarang.setText(set);
    
    }
    
    public void setTxtFieldHarga(String set){
        this.txtFieldHarga.setText(set);
    }
    
    public void setTxtFieldStok(String set){
        this.txtFieldStok.setText(set);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTambahBarangFrame = new javax.swing.JPanel();
        txtFieldStok = new javax.swing.JTextField();
        labelStok = new javax.swing.JLabel();
        btnBatalTambahBarang = new javax.swing.JButton();
        btnTambahBarangFrame = new javax.swing.JButton();
        labelHarga = new javax.swing.JLabel();
        txtFieldNamaBarang = new javax.swing.JTextField();
        labelNamaBarang = new javax.swing.JLabel();
        txtFieldHarga = new javax.swing.JTextField();
        labelHeader = new javax.swing.JLabel();
        btnUpdateBarangFrame = new javax.swing.JButton();
        txtIdBarang = new javax.swing.JTextField();
        txtFieldKodeBarang = new javax.swing.JTextField();
        labelKodeBarang = new javax.swing.JLabel();
        dateTglMasuk = new datechooser.beans.DateChooserCombo();
        labelKodeBarang1 = new javax.swing.JLabel();
        jCheckTanggal = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor Barang");
        setUndecorated(true);

        panelTambahBarangFrame.setBackground(new java.awt.Color(255, 255, 255));
        panelTambahBarangFrame.setForeground(new java.awt.Color(255, 255, 255));

        txtFieldStok.setMargin(new java.awt.Insets(0, 5, 0, 0));
        txtFieldStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldStokKeyReleased(evt);
            }
        });

        labelStok.setText("Stok");

        btnBatalTambahBarang.setBackground(new java.awt.Color(255, 51, 51));
        btnBatalTambahBarang.setForeground(new java.awt.Color(255, 255, 255));
        btnBatalTambahBarang.setText("Batal");
        btnBatalTambahBarang.setBorderPainted(false);
        btnBatalTambahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalTambahBarangActionPerformed(evt);
            }
        });

        btnTambahBarangFrame.setBackground(new java.awt.Color(0, 153, 255));
        btnTambahBarangFrame.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahBarangFrame.setText("Tambah");
        btnTambahBarangFrame.setBorderPainted(false);
        btnTambahBarangFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBarangFrameActionPerformed(evt);
            }
        });
        btnTambahBarangFrame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTambahBarangFrameKeyPressed(evt);
            }
        });

        labelHarga.setText("Harga");

        txtFieldNamaBarang.setMargin(new java.awt.Insets(0, 5, 0, 0));

        labelNamaBarang.setText("Nama Barang");

        txtFieldHarga.setMargin(new java.awt.Insets(0, 5, 0, 0));
        txtFieldHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldHargaKeyReleased(evt);
            }
        });

        labelHeader.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelHeader.setText("Tambah Barang");

        btnUpdateBarangFrame.setBackground(new java.awt.Color(0, 255, 0));
        btnUpdateBarangFrame.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateBarangFrame.setText("Update");
        btnUpdateBarangFrame.setBorderPainted(false);
        btnUpdateBarangFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBarangFrameActionPerformed(evt);
            }
        });

        txtIdBarang.setEditable(false);

        txtFieldKodeBarang.setMargin(new java.awt.Insets(0, 5, 0, 0));
        txtFieldKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldKodeBarangKeyReleased(evt);
            }
        });

        labelKodeBarang.setText("Kode Barang");

        labelKodeBarang1.setText("Tanggal Masuk");

        jCheckTanggal.setBackground(new java.awt.Color(255, 255, 255));
        jCheckTanggal.setText("<html>Centang jika ingin <br>mengubah tanggal</html>");
        jCheckTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckTanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTambahBarangFrameLayout = new javax.swing.GroupLayout(panelTambahBarangFrame);
        panelTambahBarangFrame.setLayout(panelTambahBarangFrameLayout);
        panelTambahBarangFrameLayout.setHorizontalGroup(
            panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTambahBarangFrameLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateTglMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFieldNamaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTambahBarangFrameLayout.createSequentialGroup()
                        .addGroup(panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUpdateBarangFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTambahBarangFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(btnBatalTambahBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtFieldKodeBarang)
                    .addComponent(txtFieldHarga, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldStok, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKodeBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTambahBarangFrameLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labelStok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelKodeBarang1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        panelTambahBarangFrameLayout.setVerticalGroup(
            panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTambahBarangFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelHeader)
                .addGap(38, 38, 38)
                .addComponent(labelNamaBarang)
                .addGap(12, 12, 12)
                .addComponent(txtFieldNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelKodeBarang)
                .addGap(11, 11, 11)
                .addComponent(txtFieldKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHarga)
                .addGap(12, 12, 12)
                .addComponent(txtFieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelStok)
                .addGap(12, 12, 12)
                .addComponent(txtFieldStok, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelKodeBarang1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateBarangFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahBarangFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambahBarangFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBatalTambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(panelTambahBarangFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(panelTambahBarangFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalTambahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalTambahBarangActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnBatalTambahBarangActionPerformed

    private void btnUpdateBarangFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBarangFrameActionPerformed
        BarangController bc = new BarangController();
        
        int id = Integer.parseInt(txtIdBarang.getText());
        String nmBarang = txtFieldNamaBarang.getText();
        String kodeBarang = txtFieldKodeBarang.getText();
        int harga = Formater.setRupiahToInteger(txtFieldHarga.getText());
        long date = dateTglMasuk.getSelectedDate().getTimeInMillis();
        int total = Integer.parseInt(txtFieldStok.getText());
        
        try {
            
            if (dateTglMasuk.isEnabled()) {
                
                bc.updateBarang(new Barang(id, kodeBarang, nmBarang, 
                    harga, total, date));                
            }else{
                
                bc.updateBarangNoDate(new Barang(id, kodeBarang, nmBarang, 
                    harga, total));                
            }
            
            JOptionPane.showMessageDialog(null, "Barang berhasil diupdate");
            this.setVisible(false);
            UserLoginController.getMainFormInstance().reloadTableBarang(evt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: periksa inputan anda");
        } catch(NumberFormatException b){
            JOptionPane.showMessageDialog(null, "Mohon masukan input dengan benar");
        }
    }//GEN-LAST:event_btnUpdateBarangFrameActionPerformed

    private void btnTambahBarangFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBarangFrameActionPerformed
        BarangController bc = new BarangController();
        String nmBarang = txtFieldNamaBarang.getText();
        String kodeBarang = txtFieldKodeBarang.getText();
        int harga = Formater.setRupiahToInteger(txtFieldHarga.getText());
        int total = Integer.parseInt(txtFieldStok.getText());
        long date = dateTglMasuk.getSelectedDate().getTimeInMillis();
        
        try {
            bc.insertBarang(new Barang(nmBarang, kodeBarang, 
                    harga, total, date, total));
            JOptionPane.showMessageDialog(null, "Barang berhasil ditambahkan");
            UserLoginController.getMainFormInstance().reloadTableBarang(evt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: periksa inputan anda");
        } catch(NumberFormatException b){
            JOptionPane.showMessageDialog(null, "Mohon masukan input dengan benar");
        }
        
        txtFieldNamaBarang.setText("");
        txtFieldHarga.setText("");
        txtFieldStok.setText("");
        txtFieldKodeBarang.setText("");
    }//GEN-LAST:event_btnTambahBarangFrameActionPerformed

    private void txtFieldHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldHargaKeyReleased
        try {
            txtFieldHarga.setText(Formater.setRupiahFormat(Formater.setRupiahToInteger(txtFieldHarga.getText())));            
        } catch (Exception e) {
            txtFieldHarga.setText("");
        }
    }//GEN-LAST:event_txtFieldHargaKeyReleased

    private void txtFieldStokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldStokKeyReleased

        txtFieldStok.setText(Formater.filterOnlyNumber(txtFieldStok.getText()));
    }//GEN-LAST:event_txtFieldStokKeyReleased

    private void txtFieldKodeBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldKodeBarangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldKodeBarangKeyReleased

    private void jCheckTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckTanggalActionPerformed
        
        if(jCheckTanggal.isSelected()){
            dateTglMasuk.setEnabled(true);
        }else{
            dateTglMasuk.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckTanggalActionPerformed

    private void btnTambahBarangFrameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTambahBarangFrameKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent et = null;
                btnTambahBarangFrameActionPerformed(et);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnTambahBarangFrameKeyPressed

    public void setVisibilityBtnUpdate(){
        btnUpdateBarangFrame.setVisible(true);
        btnTambahBarangFrame.setVisible(false);
        labelHeader.setText("Update Barang");
    }
    public void setVisibilityBtnTambah(){
        btnUpdateBarangFrame.setVisible(false);
        btnTambahBarangFrame.setVisible(true);
        labelHeader.setText("Tambah Barang");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalTambahBarang;
    private javax.swing.JButton btnTambahBarangFrame;
    private javax.swing.JButton btnUpdateBarangFrame;
    private datechooser.beans.DateChooserCombo dateTglMasuk;
    private javax.swing.JCheckBox jCheckTanggal;
    private javax.swing.JLabel labelHarga;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelKodeBarang;
    private javax.swing.JLabel labelKodeBarang1;
    private javax.swing.JLabel labelNamaBarang;
    private javax.swing.JLabel labelStok;
    private javax.swing.JPanel panelTambahBarangFrame;
    private javax.swing.JTextField txtFieldHarga;
    private javax.swing.JTextField txtFieldKodeBarang;
    private javax.swing.JTextField txtFieldNamaBarang;
    private javax.swing.JTextField txtFieldStok;
    private javax.swing.JTextField txtIdBarang;
    // End of variables declaration//GEN-END:variables
}
