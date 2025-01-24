/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahmaddzulfauzilazhim.pkg2210010389.tb;

/**
 *
 * @author Ahmad Dzul Fauzil Azhim
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.io.FileWriter;

public class FormDepartemen extends JFrame {
    private JTextField txtIdDepartemen, txtNamaDepartemen, txtKodeDepartemen;
    private JTextArea txtDeskripsi;
    private JTable tabelDepartemen;
    private DefaultTableModel modelTabel;
    private JButton btnSimpan, btnUbah, btnHapus, btnBersihkan, btnCetakCSV;

    public FormDepartemen() {
        setTitle("Manajemen Departemen");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInput.add(new JLabel("ID Departemen:"));
        txtIdDepartemen = new JTextField();
        txtIdDepartemen.setEditable(false);
        panelInput.add(txtIdDepartemen);

        panelInput.add(new JLabel("Nama Departemen:"));
        txtNamaDepartemen = new JTextField();
        panelInput.add(txtNamaDepartemen);

        panelInput.add(new JLabel("Kode Departemen:"));
        txtKodeDepartemen = new JTextField();
        panelInput.add(txtKodeDepartemen);

        panelInput.add(new JLabel("Deskripsi:"));
        txtDeskripsi = new JTextArea(3, 20);
        panelInput.add(new JScrollPane(txtDeskripsi));

        add(panelInput, BorderLayout.NORTH);

        // Tabel
        String[] kolom = {"ID", "Nama Departemen", "Kode Departemen", "Deskripsi"};
        modelTabel = new DefaultTableModel(kolom, 0);
        tabelDepartemen = new JTable(modelTabel);
        add(new JScrollPane(tabelDepartemen), BorderLayout.CENTER);

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
        tabelDepartemen.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelDepartemen.getSelectedRow();
            if (baris != -1) {
                txtIdDepartemen.setText(modelTabel.getValueAt(baris, 0).toString());
                txtNamaDepartemen.setText(modelTabel.getValueAt(baris, 1).toString());
                txtKodeDepartemen.setText(modelTabel.getValueAt(baris, 2).toString());
                txtDeskripsi.setText(modelTabel.getValueAt(baris, 3).toString());
            }
        });

        btnSimpan.addActionListener(e -> simpanDepartemen());
        btnUbah.addActionListener(e -> ubahDepartemen());
        btnHapus.addActionListener(e -> hapusDepartemen());
        btnBersihkan.addActionListener(e -> bersihkanForm());
        btnCetakCSV.addActionListener(e -> cetakCSV());

        muatData();
        pack();
        setLocationRelativeTo(null);
    }

    private void simpanDepartemen() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "INSERT INTO departemen (nama_departemen, kode_departemen, deskripsi) VALUES (?, ?, ?)";
            PreparedStatement ps = kon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, txtNamaDepartemen.getText());
            ps.setString(2, txtKodeDepartemen.getText());
            ps.setString(3, txtDeskripsi.getText());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Object[] data = {
                    rs.getInt(1), 
                    txtNamaDepartemen.getText(), 
                    txtKodeDepartemen.getText(), 
                    txtDeskripsi.getText()
                };
                modelTabel.addRow(data);
            }
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + ex.getMessage());
        }
    }

    private void ubahDepartemen() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "UPDATE departemen SET nama_departemen=?, kode_departemen=?, deskripsi=? WHERE id_departemen=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setString(1, txtNamaDepartemen.getText());
            ps.setString(2, txtKodeDepartemen.getText());
            ps.setString(3, txtDeskripsi.getText());
            ps.setInt(4, Integer.parseInt(txtIdDepartemen.getText()));
            ps.executeUpdate();

            int baris = tabelDepartemen.getSelectedRow();
            modelTabel.setValueAt(txtNamaDepartemen.getText(), baris, 1);
            modelTabel.setValueAt(txtKodeDepartemen.getText(), baris, 2);
            modelTabel.setValueAt(txtDeskripsi.getText(), baris, 3);
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Ubah: " + ex.getMessage());
        }
    }

    private void hapusDepartemen() {
        try (Connection kon = KoneksiDatabase.sambungkan()) {
            String sql = "DELETE FROM departemen WHERE id_departemen=?";
            PreparedStatement ps = kon.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdDepartemen.getText()));
            ps.executeUpdate();

            int baris = tabelDepartemen.getSelectedRow();
            modelTabel.removeRow(baris);
            bersihkanForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + ex.getMessage());
        }
    }

    private void muatData() {
        try (Connection kon = KoneksiDatabase.sambungkan();
             Statement st = kon.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM departemen")) {
            
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_departemen"),
                    rs.getString("nama_departemen"),
                    rs.getString("kode_departemen"),
                    rs.getString("deskripsi")
                };
                modelTabel.addRow(data);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal Muat Data: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        txtIdDepartemen.setText("");
        txtNamaDepartemen.setText("");
        txtKodeDepartemen.setText("");
        txtDeskripsi.setText("");
    }

    private void cetakCSV() {
        try (FileWriter csv = new FileWriter("departemen.csv")) {
            csv.append("ID,Nama Departemen,Kode Departemen,Deskripsi\n");
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
        SwingUtilities.invokeLater(() -> new FormDepartemen().setVisible(true));
    }
}