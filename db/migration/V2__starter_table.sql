-- FIRST MIGRATION IS STARTED HERE

--  TABEL BARANG
DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `id_barang` int(7) NOT NULL AUTO_INCREMENT,
  `kode_barang` varchar(5) DEFAULT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `harga` int(7) DEFAULT NULL,
  `stok` int(7) unsigned DEFAULT NULL,
  `tgl_input` datetime NOT NULL,
  `jumlah_barang_masuk` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_barang`),
  KEY `kode` (`kode_barang`) USING BTREE
);

--  BARANG KELUAR
DROP TABLE IF EXISTS `barang_keluar`;
CREATE TABLE `barang_keluar` (
  `id_barang_keluar` int(7) NOT NULL AUTO_INCREMENT,
  `id_barang` int(7) NOT NULL,
  `tgl_keluar` datetime NOT NULL,
  `jumlah_barang_keluar` int(7) DEFAULT NULL,
  PRIMARY KEY (`id_barang_keluar`),
  KEY `fk_barang_keluar_1_idx` (`id_barang`),
  CONSTRAINT `fk_barang_keluar_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- TABEL BARANG MASUK
DROP TABLE IF EXISTS `barang_masuk`;
CREATE TABLE `barang_masuk` (
  `id_barang_masuk` int(7) NOT NULL AUTO_INCREMENT,
  `id_barang` int(7) NOT NULL,
  `tgl_masuk` datetime NOT NULL,
  `jumlah_barang_masuk` int(9) DEFAULT NULL,
  PRIMARY KEY (`id_barang_masuk`),
  KEY `fk_barang_masuk_1_idx` (`id_barang`),
  CONSTRAINT `fk_barang_masuk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- TABEL TRANSASKI
DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `tgl_transaksi` datetime NOT NULL,
  `total_item` int(11) NOT NULL,
  `total_harga` bigint(20) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  PRIMARY KEY (`id_transaksi`)
);

-- TABEL DETAIL_TRANSAKSI
DROP TABLE IF EXISTS `detail_transaksi`;
CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL AUTO_INCREMENT,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `fk_detail_transaksi_1_idx` (`id_transaksi`),
  KEY `fk_id_barang_idx` (`id_barang`),
  CONSTRAINT `fk_detail_transaksi_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_barang` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- TABEL KATEGORI
DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `idkategori` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idkategori`)
);

-- TABEl USERAPP
DROP TABLE IF EXISTS `userapp`;
CREATE TABLE `userapp` (
  `kode_user` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nama_asli` varchar(50) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode_user`,`username`),
  UNIQUE KEY `kode_user` (`kode_user`)
);

-- CREATE VIEW
CREATE 
VIEW `full_transaksi` AS
    SELECT 
       `transaksi`.`id_transaksi` AS `id_transaksi`,
       `transaksi`.`tgl_transaksi` AS `tgl_transaksi`,
       `transaksi`.`id_petugas` AS `id_petugas`,
       `barang`.`id_barang` AS `id_barang`,
       `barang`.`nama_barang` AS `nama_barang`,
       `detail_transaksi`.`harga` AS `harga`,
       `detail_transaksi`.`jumlah` AS `jumlah`,
       `userapp`.`username` AS `username`,
       `userapp`.`nama_asli` AS `nama_asli`,
       `userapp`.`status` AS `status`,
       `barang`.`jumlah_barang_masuk` AS `barang_masuk`
    FROM
        (((`detail_transaksi`
        JOIN `transaksi` ON ((`detail_transaksi`.`id_transaksi` = `transaksi`.`id_transaksi`)))
        JOIN `barang` ON ((`detail_transaksi`.`id_barang` = `barang`.`id_barang`)))
        JOIN `userapp` ON ((`detail_transaksi`.`id_petugas` = `userapp`.`kode_user`)));