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

public class FormPengajuanCuti extends JFrame {
    private JTextField txtIdPengajuan;
    private JComboBox<String> cbPegawai, cbJenisCuti, cbStatusPengajuan;
    private JDateChooser datePengajuan, dateMulaiCuti, dateSelesaiCuti;
    private JTextArea txtCatatan;
    private JTable tabelPengajuanCuti;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnUbah, btnHapus, btnBersihkan, btnCetakCSV;

    public FormPengajuanCuti() {
        setTitle("Pengajuan Cuti");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(7, 2, 10, 10));
        panelInput.add(new JLabel("ID Pengajuan:"));
        txtIdPengajuan = new JTextField();
        txtIdPengajuan.setEditable(false);
        panelInput.add(txtIdPengajuan);

        panelInput.add(new JLabel("Pegawai:"));
        cbPegawai = new JComboBox<>();
        muatPegawai();
        panelInput.add(cbPegawai);

        panelInput.add(new JLabel("Tanggal Pengajuan:"));
        datePengajuan = new JDateChooser();
        panelInput.add(datePengajuan);

        panelInput.add(new JLabel("Tanggal Mulai Cuti:"));
        dateMulaiCuti = new JDateChooser();
        panelInput.add(dateMulaiCuti);

        panelInput.add(new JLabel("Tanggal Selesai Cuti:"));
        dateSelesaiCuti = new JDateChooser();
        panelInput.add(dateSelesaiCuti);

        panelInput.add(new JLabel("Jenis Cuti:"));
        cbJenisCuti = new JComboBox<>(new String[]{"Tahunan", "Besar", "Melahirkan", "Alasan Penting"});
        panelInput.add(cbJenisCuti);

        panelInput.add(new JLabel("Status Pengajuan:"));
        cbStatusPengajuan = new JComboBox<>(new String[]{"Diproses", "Disetujui", "Ditolak"});
        panelInput.add(cbStatusPengajuan);

        panelInput.add(new JLabel("Catatan:"));
        txtCatatan = new JTextArea(3, 20);
        panelInput.add(new JScrollPane(txtCatatan));

        add(panelInput, BorderLayout.NORTH);

        // Tabel
        String[] kolom = {"ID", "Pegawai", "Tgl Pengajuan", "Tgl Mulai", "Tgl Selesai", "Jenis Cuti", "Status", "Catatan"};
        modelTabel = new DefaultTableModel(kolom, 0);
        tabelPengajuanCuti = new JTable(modelTabel);
        add(new JScrollPane(tabelPengajuanCuti), BorderLayout.CENTER);

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

        // Listener Tabel
        tabelPengajuanCuti.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelPengajuanCuti.getSelectedRow();
            if (baris != -1) {
                txtIdPengajuan.setText(modelTabel.getValueAt(baris, 0).toString());
                cbPegawai.setSelectedItem(modelTabel.getValueAt(baris, 1).toString());
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    datePengajuan.setDate(sdf.parse(modelTabel.getValueAt(baris, 2).toString()));
                    dateMulaiCuti.setDate(sdf.parse(modelTabel.getValueAt(baris, 3).toString()));
                    dateSelesaiCuti.setDate(sdf.parse(modelTabel.getValueAt(baris, 4).toString()));
                } catch (Exception ex) {}
                cbJenisCuti.setSelectedItem(modelTabel.getValueAt(baris, 5).toString());
                cbStatusPengajuan.setSelectedItem(modelTabel.getValueAt(baris, 6).toString());
                txtCatatan.setText(modelTabel.getValueAt(baris, 7).toString());
            }
        });

        // Listener Tombol
        btnSimpan.addActionListener(e -> simpanPengajuanCuti());
        btnUbah.addActionListener(e -> ubahPengajuanCuti());
        btnHapus.addActionListener(e -> hapusPengajuanCuti());
        btnBersihkan.addActionListener(e -> bersihkanForm());
        btnCetakCSV.addActionListener(e -> cetakCSV());

        muatData();
        pack();
        setLocationRelativeTo(null);
    }

    private void muatPegawai() {
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT nama_lengkap FROM pegawai")) {
            
            while (rs.next()) {
                cbPegawai.addItem(rs.getString("nama_lengkap"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Pegawai: " + ex.getMessage());
        }
    }

    private void muatData() {
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT pc.*, p.nama_lengkap FROM pengajuan_cuti pc JOIN pegawai p ON pc.id_pegawai = p.id_pegawai")) {
            
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_pengajuan"),
                    rs.getString("nama_lengkap"),
                    rs.getDate("tanggal_pengajuan"),
                    rs.getDate("tanggal_mulai_cuti"),
                    rs.getDate("tanggal_selesai_cuti"),
                    rs.getString("jenis_cuti"),
                    rs.getString("status_pengajuan"),
                    rs.getString("catatan")
                };
                modelTabel.addRow(data);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Data: " + ex.getMessage());
        }
    }

    private void simpanPengajuanCuti() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlPegawai = "SELECT id_pegawai FROM pegawai WHERE nama_lengkap=?";
            PreparedStatement psPegawai = kon.prepareStatement(sqlPegawai);
            psPegawai.setString(1, cbPegawai.getSelectedItem().toString());
            ResultSet rsPegawai = psPegawai.executeQuery();
            
            int idPegawai = 0;
            if (rsPegawai.next()) {
                idPegawai = rsPegawai.getInt("id_pegawai");
            }

            String sql = "INSERT INTO pengajuan_cuti (id_pegawai, tanggal_pengajuan, tanggal_mulai_cuti, tanggal_selesai_cuti, jenis_cuti, status_pengajuan, catatan) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = kon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idPegawai);
            ps.setDate(2, new java.sql.Date(datePengajuan.getDate().getTime()));
            ps.setDate(3, new java.sql.Date(dateMulaiCuti.getDate().getTime()));
            ps.setDate(4, new java.sql.Date(dateSelesaiCuti.getDate().getTime()));
            ps.setString(5, cbJenisCuti.getSelectedItem().toString());
            ps.setString(6, cbStatusPengajuan.getSelectedItem().toString());
            ps.setString(7, txtCatatan.getText());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Object[] data = {
                    rs.getInt(1),
                    cbPegawai.getSelectedItem(),
                    new java.sql.Date(datePengajuan.getDate().getTime()),
                    new java.sql.Date(dateMulaiCuti.getDate().getTime()),
                    new java.sql.Date(dateSelesaiCuti.getDate().getTime()),
                    cbJenisCuti.getSelectedItem(),
                    cbStatusPengajuan.getSelectedItem(),
                    txtCatatan.getText()
                };
                modelTabel.addRow(data);
            }
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + ex.getMessage());
        }
    }

    private void ubahPengajuanCuti() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sqlPegawai = "SELECT id_pegawai FROM pegawai WHERE nama_lengkap=?";
            PreparedStatement psPegawai = kon.prepareStatement(sqlPegawai);
            psPegawai.setString(1, cbPegawai.getSelectedItem().toString());
            ResultSet rsPegawai = psPegawai.executeQuery();
            
            int idPegawai = 0;
            if (rsPegawai.next()) {
                idPegawai = rsPegawai.getInt("id_pegawai");
            }

            String sql = "UPDATE pengajuan_cuti SET id_pegawai=?, tanggal_pengajuan=?, tanggal_mulai_cuti=?, tanggal_selesai_cuti=?, jenis_cuti=?, status_pengajuan=?, catatan=? WHERE id_pengajuan=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, idPegawai);
            ps.setDate(2, new java.sql.Date(datePengajuan.getDate().getTime()));
            ps.setDate(3, new java.sql.Date(dateMulaiCuti.getDate().getTime()));
            ps.setDate(4, new java.sql.Date(dateSelesaiCuti.getDate().getTime()));
            ps.setString(5, cbJenisCuti.getSelectedItem().toString());
            ps.setString(6, cbStatusPengajuan.getSelectedItem().toString());
            ps.setString(7, txtCatatan.getText());
            ps.setInt(8, Integer.parseInt(txtIdPengajuan.getText()));
            ps.executeUpdate();

            int baris = tabelPengajuanCuti.getSelectedRow();
            modelTabel.setValueAt(cbPegawai.getSelectedItem(), baris, 1);
            modelTabel.setValueAt(new java.sql.Date(datePengajuan.getDate().getTime()), baris, 2);
            modelTabel.setValueAt(new java.sql.Date(dateMulaiCuti.getDate().getTime()), baris, 3);
            modelTabel.setValueAt(new java.sql.Date(dateSelesaiCuti.getDate().getTime()), baris, 4);
            modelTabel.setValueAt(cbJenisCuti.getSelectedItem(), baris, 5);
            modelTabel.setValueAt(cbStatusPengajuan.getSelectedItem(), baris, 6);
            modelTabel.setValueAt(txtCatatan.getText(), baris, 7);

            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Ubah: " + ex.getMessage());
        }
    }

    private void hapusPengajuanCuti() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "DELETE FROM pengajuan_cuti WHERE id_pengajuan=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdPengajuan.getText()));
            ps.executeUpdate();

            int baris = tabelPengajuanCuti.getSelectedRow();
            modelTabel.removeRow(baris);
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        txtIdPengajuan.setText("");
        cbPegawai.setSelectedIndex(0);
        datePengajuan.setDate(null);
        dateMulaiCuti.setDate(null);
        dateSelesaiCuti.setDate(null);
        cbJenisCuti.setSelectedIndex(0);
        cbStatusPengajuan.setSelectedIndex(0);
        txtCatatan.setText("");
    }

    private void cetakCSV() {
        try (FileWriter csv = new FileWriter("pengajuan_cuti.csv")) {
            csv.append("ID,Pegawai,Tgl Pengajuan,Tgl Mulai,Tgl Selesai,Jenis Cuti,Status,Catatan\n");
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
        SwingUtilities.invokeLater(() -> new FormPengajuanCuti().setVisible(true));
    }
}
