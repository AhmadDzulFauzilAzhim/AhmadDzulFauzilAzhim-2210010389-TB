/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

public class FormPegawai extends JFrame {
    private JTextField txtIdPegawai, txtNIP, txtNamaLengkap, txtEmail, txtNomorTelepon;
    private JComboBox<String> cbDepartemen, cbJabatan, cbStatusKepegawaian;
    private JTable tabelPegawai;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnUbah, btnHapus, btnBersihkan, btnCetakCSV;
    private JDateChooser dateTanggalMasuk;

    public FormPegawai() {
        setTitle("Manajemen Pegawai");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(8, 2, 10, 10));
        panelInput.add(new JLabel("ID Pegawai:"));
        txtIdPegawai = new JTextField();
        txtIdPegawai.setEditable(false);
        panelInput.add(txtIdPegawai);

        panelInput.add(new JLabel("NIP:"));
        txtNIP = new JTextField();
        panelInput.add(txtNIP);

        panelInput.add(new JLabel("Nama Lengkap:"));
        txtNamaLengkap = new JTextField();
        panelInput.add(txtNamaLengkap);

        panelInput.add(new JLabel("Departemen:"));
        cbDepartemen = new JComboBox<>();
        muatDepartemen();
        panelInput.add(cbDepartemen);

        panelInput.add(new JLabel("Jabatan:"));
        cbJabatan = new JComboBox<>(new String[]{"Staff", "Supervisor", "Manager", "Direktur"});
        panelInput.add(cbJabatan);

        panelInput.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelInput.add(txtEmail);

        panelInput.add(new JLabel("Nomor Telepon:"));
        txtNomorTelepon = new JTextField();
        panelInput.add(txtNomorTelepon);

        panelInput.add(new JLabel("Tanggal Masuk:"));
        dateTanggalMasuk = new JDateChooser();
        panelInput.add(dateTanggalMasuk);

        panelInput.add(new JLabel("Status Kepegawaian:"));
        cbStatusKepegawaian = new JComboBox<>(new String[]{"Aktif", "Non-Aktif", "Cuti"});
        panelInput.add(cbStatusKepegawaian);

        add(panelInput, BorderLayout.NORTH);

        // Tabel
        String[] kolom = {"ID", "NIP", "Nama", "Departemen", "Jabatan", "Email", "Telepon", "Tanggal Masuk", "Status"};
        modelTabel = new DefaultTableModel(kolom, 0);
        tabelPegawai = new JTable(modelTabel);
        add(new JScrollPane(tabelPegawai), BorderLayout.CENTER);

        // Panel Tombol
        JPanel panelTombol = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBersihkan = new JButton("Bersihkan");
        btnCetakCSV = new JButton("Cetak CSV");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnBersihkan);
        panelTombol.add(btnCetakCSV);
        add(panelTombol, BorderLayout.SOUTH);

        // Listener
        tabelPegawai.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelPegawai.getSelectedRow();
            if (baris != -1) {
                txtIdPegawai.setText(modelTabel.getValueAt(baris, 0).toString());
                txtNIP.setText(modelTabel.getValueAt(baris, 1).toString());
                txtNamaLengkap.setText(modelTabel.getValueAt(baris, 2).toString());
                cbDepartemen.setSelectedItem(modelTabel.getValueAt(baris, 3).toString());
                cbJabatan.setSelectedItem(modelTabel.getValueAt(baris, 4).toString());
                txtEmail.setText(modelTabel.getValueAt(baris, 5).toString());
                txtNomorTelepon.setText(modelTabel.getValueAt(baris, 6).toString());
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    dateTanggalMasuk.setDate(sdf.parse(modelTabel.getValueAt(baris, 7).toString()));
                } catch (Exception ex) {}
                cbStatusKepegawaian.setSelectedItem(modelTabel.getValueAt(baris, 8).toString());
            }
        });

        btnSimpan.addActionListener(e -> simpanPegawai());
        btnUbah.addActionListener(e -> ubahPegawai());
        btnHapus.addActionListener(e -> hapusPegawai());
        btnBersihkan.addActionListener(e -> bersihkanForm());
        btnCetakCSV.addActionListener(e -> cetakCSV());
       
        muatData();
        pack();
        setLocationRelativeTo(null);
    }
    
 private void muatData() {
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
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT nama_departemen FROM departemen")) {
            
            while (rs.next()) {
                cbDepartemen.addItem(rs.getString("nama_departemen"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Departemen: " + ex.getMessage());
        }
    }

    private void simpanPegawai() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlDepartemen = "SELECT id_departemen FROM departemen WHERE nama_departemen=?";
            PreparedStatement psDepartemen = kon.prepareStatement(sqlDepartemen);
            psDepartemen.setString(1, cbDepartemen.getSelectedItem().toString());
            ResultSet rsDepartemen = psDepartemen.executeQuery();
            
            int idDepartemen = 0;
            if (rsDepartemen.next()) {
                idDepartemen = rsDepartemen.getInt("id_departemen");
            }

            String sql = "INSERT INTO pegawai (nip, nama_lengkap, id_departemen, jabatan, email, nomor_telepon, tanggal_masuk, status_kepegawaian) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = kon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, txtNIP.getText());
            ps.setString(2, txtNamaLengkap.getText());
            ps.setInt(3, idDepartemen);
            ps.setString(4, cbJabatan.getSelectedItem().toString());
            ps.setString(5, txtEmail.getText());
            ps.setString(6, txtNomorTelepon.getText());
            ps.setDate(7, new java.sql.Date(dateTanggalMasuk.getDate().getTime()));
            ps.setString(8, cbStatusKepegawaian.getSelectedItem().toString());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Object[] data = {
                    rs.getInt(1),
                    txtNIP.getText(),
                    txtNamaLengkap.getText(),
                    cbDepartemen.getSelectedItem(),
                    cbJabatan.getSelectedItem(),
                    txtEmail.getText(),
                    txtNomorTelepon.getText(),
                    new java.sql.Date(dateTanggalMasuk.getDate().getTime()),
                    cbStatusKepegawaian.getSelectedItem()
                };
                modelTabel.addRow(data);
            }
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + ex.getMessage());
        }
    }

    private void ubahPegawai() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlDepartemen = "SELECT id_departemen FROM departemen WHERE nama_departemen=?";
            PreparedStatement psDepartemen = kon.prepareStatement(sqlDepartemen);
            psDepartemen.setString(1, cbDepartemen.getSelectedItem().toString());
            ResultSet rsDepartemen = psDepartemen.executeQuery();
            
            int idDepartemen = 0;
            if (rsDepartemen.next()) {
                idDepartemen = rsDepartemen.getInt("id_departemen");
            }

            String sql = "UPDATE pegawai SET nip=?, nama_lengkap=?, id_departemen=?, jabatan=?, email=?, nomor_telepon=?, tanggal_masuk=?, status_kepegawaian=? WHERE id_pegawai=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setString(1, txtNIP.getText());
            ps.setString(2, txtNamaLengkap.getText());
            ps.setInt(3, idDepartemen);
            ps.setString(4, cbJabatan.getSelectedItem().toString());
            ps.setString(5, txtEmail.getText());
            ps.setString(6, txtNomorTelepon.getText());
            ps.setDate(7, new java.sql.Date(dateTanggalMasuk.getDate().getTime()));
            ps.setString(8, cbStatusKepegawaian.getSelectedItem().toString());
            ps.setInt(9, Integer.parseInt(txtIdPegawai.getText()));
            ps.executeUpdate();

            int baris = tabelPegawai.getSelectedRow();
            modelTabel.setValueAt(txtNIP.getText(), baris, 1);
            modelTabel.setValueAt(txtNamaLengkap.getText(), baris, 2);
            modelTabel.setValueAt(cbDepartemen.getSelectedItem(), baris, 3);
            modelTabel.setValueAt(cbJabatan.getSelectedItem(), baris, 4);
            modelTabel.setValueAt(txtEmail.getText(), baris, 5);
            modelTabel.setValueAt(txtNomorTelepon.getText(), baris, 6);
            modelTabel.setValueAt(new java.sql.Date(dateTanggalMasuk.getDate().getTime()), baris, 7);
            modelTabel.setValueAt(cbStatusKepegawaian.getSelectedItem(), baris, 8);

            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Ubah: " + ex.getMessage());
        }
    }

    private void hapusPegawai() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "DELETE FROM pegawai WHERE id_pegawai=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdPegawai.getText()));
            ps.executeUpdate();

            int baris = tabelPegawai.getSelectedRow();
            modelTabel.removeRow(baris);
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        txtIdPegawai.setText("");
        txtNIP.setText("");
        txtNamaLengkap.setText("");
        cbDepartemen.setSelectedIndex(0);
        cbJabatan.setSelectedIndex(0);
        txtEmail.setText("");
        txtNomorTelepon.setText("");
        dateTanggalMasuk.setDate(null);
        cbStatusKepegawaian.setSelectedIndex(0);
    }

    private void cetakCSV() {
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPegawai().setVisible(true));
    }
}
