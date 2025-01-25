/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ahmaddzulfauzilazhim.pkg2210010389.tb;

/**
 *
 * @author Ahmad Dzul Fauzil Azhim
 */
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class FramePegawai extends javax.swing.JFrame {
     private DefaultTableModel modelTabel;
    /**
     * Creates new form FramePegawai
     */
    public FramePegawai() {
        initComponents();
        initCustomComponents();
        this.setLocationRelativeTo(null);
    }

       private void initCustomComponents() {

        String[] kolom = {"ID", "NIP", "Nama", "Departemen", "Jabatan", "Email", "Telepon", "Tanggal Masuk", "Status"};
        modelTabel = new DefaultTableModel(kolom, 0);
        TabelPegawai.setModel(modelTabel);

        muatDepartemen();

        muatData();

        TabelPegawai.getSelectionModel().addListSelectionListener(e -> {
            int baris = TabelPegawai.getSelectedRow();
            if (baris != -1) {
                txtIDPegawai.setText(modelTabel.getValueAt(baris, 0).toString());
                txtnip.setText(modelTabel.getValueAt(baris, 1).toString());
                txtnamalengkap.setText(modelTabel.getValueAt(baris, 2).toString());
                cmbdepartemen.setSelectedItem(modelTabel.getValueAt(baris, 3).toString());
                cmbjabatan.setSelectedItem(modelTabel.getValueAt(baris, 4).toString());
                txtemail.setText(modelTabel.getValueAt(baris, 5).toString());
                txtnomortelepon.setText(modelTabel.getValueAt(baris, 6).toString());
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    jdatetanggalmasuk.setDate(sdf.parse(modelTabel.getValueAt(baris, 7).toString()));
                } catch (Exception ex) {}
                cmbstatus.setSelectedItem(modelTabel.getValueAt(baris, 8).toString());
            }
        });
    }

    private void muatData() {
        modelTabel.setRowCount(0); 
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT p.*, d.nama_departemen FROM pegawai p JOIN departemen d ON p.id_departemen = d.id_departemen")) {
            
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_pegawai"),
                    rs.getString("nip"),
                    rs.getString("nama_lengkap"),
                    rs.getString("nama_departemen"),
                    rs.getString("jabatan"),
                    rs.getString("email"),
                    rs.getString("nomor_telepon"),
                    rs.getDate("tanggal_masuk"),
                    rs.getString("status_kepegawaian")
                };
                modelTabel.addRow(data);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Data: " + ex.getMessage());
        }
    }

    private void muatDepartemen() {
        cmbdepartemen.removeAllItems();
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT nama_departemen FROM departemen")) {
            
            while (rs.next()) {
                cmbdepartemen.addItem(rs.getString("nama_departemen"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Departemen: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIDPegawai = new javax.swing.JTextField();
        txtnip = new javax.swing.JTextField();
        txtnamalengkap = new javax.swing.JTextField();
        cmbdepartemen = new javax.swing.JComboBox<>();
        txtemail = new javax.swing.JTextField();
        cmbjabatan = new javax.swing.JComboBox<>();
        txtnomortelepon = new javax.swing.JTextField();
        jdatetanggalmasuk = new com.toedter.calendar.JDateChooser();
        cmbstatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPegawai = new javax.swing.JTable();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbersihkan = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Pegawai:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("NIP:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nama Lengkap:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Departemen:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Jabatan:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Nomor Telepon:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tanggal Masuk:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Status Kepegawaian:");

        cmbjabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff", "Supervisor", "Manager", "Direktur" }));

        cmbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif ", "Non-Aktif", "Cuti" }));

        TabelPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TabelPegawai);

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnbersihkan.setText("Bersihkan");
        btnbersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkanActionPerformed(evt);
            }
        });

        btncetak.setText("Cetak CSV");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDPegawai))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnip, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnamalengkap, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbdepartemen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbjabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnomortelepon, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdatetanggalmasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnbersihkan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtIDPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtnip, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtnamalengkap, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cmbdepartemen, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cmbjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jdatetanggalmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtnomortelepon))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnubah)
                    .addComponent(btnhapus)
                    .addComponent(btnbersihkan)
                    .addComponent(btncetak))
                .addGap(101, 101, 101))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
     try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlDepartemen = "SELECT id_departemen FROM departemen WHERE nama_departemen=?";
            PreparedStatement psDepartemen = kon.prepareStatement(sqlDepartemen);
            psDepartemen.setString(1, cmbdepartemen.getSelectedItem().toString());
            ResultSet rsDepartemen = psDepartemen.executeQuery();
            
            int idDepartemen = 0;
            if (rsDepartemen.next()) {
                idDepartemen = rsDepartemen.getInt("id_departemen");
            }

            String sql = "UPDATE pegawai SET nip=?, nama_lengkap=?, id_departemen=?, jabatan=?, email=?, nomor_telepon=?, tanggal_masuk=?, status_kepegawaian=? WHERE id_pegawai=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setString(1, txtnip.getText());
            ps.setString(2, txtnamalengkap.getText());
            ps.setInt(3, idDepartemen);
            ps.setString(4, cmbjabatan.getSelectedItem().toString());
            ps.setString(5, txtemail.getText());
            ps.setString(6, txtnomortelepon.getText());
            ps.setDate(7, new java.sql.Date(jdatetanggalmasuk.getDate().getTime()));
            ps.setString(8, cmbstatus.getSelectedItem().toString());
            ps.setInt(9, Integer.parseInt(txtIDPegawai.getText()));
            ps.executeUpdate();

            int baris = TabelPegawai.getSelectedRow();
            modelTabel.setValueAt(txtnip.getText(), baris, 1);
            modelTabel.setValueAt(txtnamalengkap.getText(), baris, 2);
            modelTabel.setValueAt(cmbdepartemen.getSelectedItem(), baris, 3);
            modelTabel.setValueAt(cmbjabatan.getSelectedItem(), baris, 4);
            modelTabel.setValueAt(txtemail.getText(), baris, 5);
            modelTabel.setValueAt(txtnomortelepon.getText(), baris, 6);
            modelTabel.setValueAt(new java.sql.Date(jdatetanggalmasuk.getDate().getTime()), baris, 7);
            modelTabel.setValueAt(cmbstatus.getSelectedItem(), baris, 8);

           btnbersihkanActionPerformed(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Ubah: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "DELETE FROM pegawai WHERE id_pegawai=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIDPegawai.getText()));
            ps.executeUpdate();

            int baris = TabelPegawai.getSelectedRow();
            modelTabel.removeRow(baris);
           btnbersihkanActionPerformed(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkanActionPerformed
        txtIDPegawai.setText("");
        txtnip.setText("");
        txtnamalengkap.setText("");
        cmbdepartemen.setSelectedIndex(0);
        cmbjabatan.setSelectedIndex(0);
        txtemail.setText("");
        txtnomortelepon.setText("");
        jdatetanggalmasuk.setDate(null);
        cmbstatus.setSelectedIndex(0);

    }//GEN-LAST:event_btnbersihkanActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
         try (FileWriter csv = new FileWriter("pegawai.csv")) {
            csv.append("ID,NIP,Nama,Departemen,Jabatan,Email,Telepon,Tanggal Masuk,Status\n");
            for (int i = 0; i < modelTabel.getRowCount(); i++) {
                for (int j = 0; j < modelTabel.getColumnCount(); j++) {
                    csv.append(modelTabel.getValueAt(i, j).toString());
                    if (j < modelTabel.getColumnCount() - 1) csv.append(",");
                }
                csv.append("\n");
            }
            JOptionPane.showMessageDialog(this, "CSV Berhasil Dibuat");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Buat CSV: " + e.getMessage());
        }
    }//GEN-LAST:event_btncetakActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
     try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlDepartemen = "SELECT id_departemen FROM departemen WHERE nama_departemen=?";
            PreparedStatement psDepartemen = kon.prepareStatement(sqlDepartemen);
            psDepartemen.setString(1, cmbdepartemen.getSelectedItem().toString());
            ResultSet rsDepartemen = psDepartemen.executeQuery();
            
            int idDepartemen = 0;
            if (rsDepartemen.next()) {
                idDepartemen = rsDepartemen.getInt("id_departemen");
            }

            String sql = "INSERT INTO pegawai (nip, nama_lengkap, id_departemen, jabatan, email, nomor_telepon, tanggal_masuk, status_kepegawaian) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = kon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, txtnip.getText());
            ps.setString(2, txtnamalengkap.getText());
            ps.setInt(3, idDepartemen);
            ps.setString(4, cmbjabatan.getSelectedItem().toString());
            ps.setString(5, txtemail.getText());
            ps.setString(6, txtnomortelepon.getText());
            ps.setDate(7, new java.sql.Date(jdatetanggalmasuk.getDate().getTime()));
            ps.setString(8, cmbstatus.getSelectedItem().toString());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Object[] data = {
                    rs.getInt(1),
                    txtnip.getText(),
                    txtnamalengkap.getText(),
                    cmbdepartemen.getSelectedItem(),
                    cmbjabatan.getSelectedItem(),
                    txtemail.getText(),
                    txtnomortelepon.getText(),
                    new java.sql.Date(jdatetanggalmasuk.getDate().getTime()),
                    cmbstatus.getSelectedItem()
                };
                modelTabel.addRow(data);
            }
           btnbersihkanActionPerformed(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelPegawai;
    private javax.swing.JButton btnbersihkan;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnubah;
    private javax.swing.JComboBox<String> cmbdepartemen;
    private javax.swing.JComboBox<String> cmbjabatan;
    private javax.swing.JComboBox<String> cmbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdatetanggalmasuk;
    private javax.swing.JTextField txtIDPegawai;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnamalengkap;
    private javax.swing.JTextField txtnip;
    private javax.swing.JTextField txtnomortelepon;
    // End of variables declaration//GEN-END:variables
}
