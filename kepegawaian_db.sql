-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Jan 2025 pada 03.55
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kepegawaian_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `departemen`
--

CREATE TABLE `departemen` (
  `id_departemen` int(11) NOT NULL,
  `nama_departemen` varchar(100) DEFAULT NULL,
  `kode_departemen` varchar(20) DEFAULT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `departemen`
--

INSERT INTO `departemen` (`id_departemen`, `nama_departemen`, `kode_departemen`, `deskripsi`) VALUES
(2, 'PT Ahmad Dzul', '3', 'Sangat Luas'),
(4, 'PT Fauzil Azhim', '4', 'Sederhana'),
(6, 'PT Yani', '5', 'Mewah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` int(11) NOT NULL,
  `nip` varchar(50) DEFAULT NULL,
  `nama_lengkap` varchar(200) DEFAULT NULL,
  `id_departemen` int(11) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nomor_telepon` varchar(20) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `status_kepegawaian` enum('Aktif','Non-Aktif','Cuti') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nip`, `nama_lengkap`, `id_departemen`, `jabatan`, `email`, `nomor_telepon`, `tanggal_masuk`, `status_kepegawaian`) VALUES
(2, '1145324', 'Ahmad Dzul Fauzil Azhim', 2, 'Direktur', 'Ahmad@gmail.com', '081212345678', '2025-01-01', 'Aktif'),
(3, '1131213', 'Fauzil Azhim', 4, 'Manager', 'Fauzil@gmail.com', '081324343321', '2025-01-15', 'Cuti'),
(5, '113213123', 'Borneo', 2, 'Staff', 'borneo@gmail.com', '08313213535', '2025-01-04', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengajuan_cuti`
--

CREATE TABLE `pengajuan_cuti` (
  `id_pengajuan` int(11) NOT NULL,
  `id_pegawai` int(11) DEFAULT NULL,
  `tanggal_pengajuan` date DEFAULT NULL,
  `tanggal_mulai_cuti` date DEFAULT NULL,
  `tanggal_selesai_cuti` date DEFAULT NULL,
  `jenis_cuti` enum('Tahunan','Besar','Melahirkan','Alasan Penting') NOT NULL,
  `status_pengajuan` enum('Diproses','Disetujui','Ditolak') NOT NULL,
  `catatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengajuan_cuti`
--

INSERT INTO `pengajuan_cuti` (`id_pengajuan`, `id_pegawai`, `tanggal_pengajuan`, `tanggal_mulai_cuti`, `tanggal_selesai_cuti`, `jenis_cuti`, `status_pengajuan`, `catatan`) VALUES
(2, 2, '2025-01-01', '2025-01-02', '2025-01-05', 'Alasan Penting', 'Diproses', 'Proses'),
(3, 3, '2025-01-08', '2025-01-10', '2025-01-18', 'Tahunan', 'Disetujui', 'Cuti');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `departemen`
--
ALTER TABLE `departemen`
  ADD PRIMARY KEY (`id_departemen`),
  ADD UNIQUE KEY `kode_departemen` (`kode_departemen`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD UNIQUE KEY `nip` (`nip`),
  ADD KEY `id_departemen` (`id_departemen`);

--
-- Indeks untuk tabel `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  ADD PRIMARY KEY (`id_pengajuan`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `departemen`
--
ALTER TABLE `departemen`
  MODIFY `id_departemen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id_pegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  MODIFY `id_pengajuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `id_departemen` FOREIGN KEY (`id_departemen`) REFERENCES `departemen` (`id_departemen`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pengajuan_cuti`
--
ALTER TABLE `pengajuan_cuti`
  ADD CONSTRAINT `id_pegawai` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
