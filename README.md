# Aplikasi Kepegawaian
 
Proyek ini adalah sebuah aplikasi manajemen perusahaan berbasis Java Swing yang terdiri dari beberapa modul utama untuk mengelola data departemen, pegawai, dan pengajuan cuti. Aplikasi ini memungkinkan pengguna untuk menambah, mengubah, menghapus, dan melihat data dengan antarmuka grafis yang mudah digunakan. Data juga dapat diekspor ke dalam format CSV.

---

## File-File

### 1. FrameDepartemen.java

**Deskripsi:**  
File ini digunakan untuk mengelola data departemen, termasuk ID departemen, nama departemen, kode departemen, dan deskripsi departemen.

**Fitur Utama:**
- Menampilkan data departemen dalam tabel.
- Menambah, mengubah, dan menghapus data departemen.
- Membersihkan input data.
- Mengekspor data departemen ke dalam file CSV.

---

### 2. FramePegawai.java

**Deskripsi:**  
File ini digunakan untuk mengelola data pegawai, termasuk ID, NIP, nama, departemen, jabatan, email, nomor telepon, tanggal masuk, dan status kepegawaian.

**Fitur Utama:**
- Menampilkan data pegawai dalam tabel.
- Menambahkan, mengubah, dan menghapus data pegawai.
- Mengelola daftar departemen dan jabatan untuk pegawai.
- Mengekspor data pegawai ke dalam file CSV.

---

### 3. FramePengajuanCuti.java

**Deskripsi:**  
File ini digunakan untuk mengelola data pengajuan cuti pegawai, termasuk ID pengajuan, pegawai terkait, tanggal pengajuan, tanggal mulai dan selesai cuti, jenis cuti, status pengajuan, dan catatan.

**Fitur Utama:**
- Menampilkan data pengajuan cuti dalam tabel.
- Menambah, mengubah, dan menghapus data pengajuan cuti.
- Menampilkan data pegawai yang tersedia untuk cuti.
- Mengekspor data pengajuan cuti ke dalam file CSV.

---

## Cara Penggunaan

### Persiapan Database
1. Pastikan sudah tersedia database yang sesuai dengan kebutuhan aplikasi ini.
2. Buat tabel-tabel `departemen`, `pegawai`, dan `pengajuan_cuti` dengan struktur yang sesuai.

### Konfigurasi Database
- Pastikan file `KoneksiDatabase` sudah dikonfigurasi dengan benar untuk menghubungkan aplikasi ke database.

### Menjalankan Aplikasi
1. Kompilasi dan jalankan file utama dari masing-masing frame untuk membuka antarmuka pengguna.
2. Gunakan fitur-fitur yang tersedia untuk mengelola data sesuai kebutuhan.

---

## Kebutuhan Sistem

- **Java:** Versi 8 atau lebih baru.
- **Library Tambahan:**
  - `toedter.calendar` untuk komponen tanggal.
  - `javax.swing` dan `javax.swing.table` untuk antarmuka grafis.

  ---

  ## Pembuat Aplikasi
  Ahmad Dzul Fauzil Azhim - 2210010389

  ## Demo

  
