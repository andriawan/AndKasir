# AndKasir

AndKasir merupakan Aplikasi Kasir yang dibangun dengan teknologi Java. Dibuat simpel dan sederhana agar mudah dalam pengoperasiannya. Aplikasi ini berawal dari project sederhana saya dalam memenuhi permintaan toko ritel dalam menangani transaksi kasir sehari-hari.

Saat ini, Aplikasi ini masih dalam tahap pengembangan. Fitur-fitur yang dimiliki aplikasi ini mencakup 

- Perhitungan Standart Kasir
- Cetak Struk
- Cetak Laporan PDF

Versi Development Release Aplikasi AndKasir v0.5.7 dapat anda unduh [di sini](https://github.com/andriawan/AndKasir/releases/tag/v0.5.7)

Screenshot:

## Panel Login 
![login](https://github.com/andriawan/AndKasir/blob/development/screenshot/login.png "Panel Login")

## Panel Admin
![admin](https://github.com/andriawan/AndKasir/blob/development/screenshot/panel-admin.png "Panel Admin")

## Panel Cetak Laporan ke PDF 
![laporan](https://github.com/andriawan/AndKasir/blob/development/screenshot/Laporan-pdf.png "Laporan")

## Panel Kasir
![kasir](https://github.com/andriawan/AndKasir/blob/development/screenshot/panel-kasir.png "Panel Kasir")

## Tentang Aplikasi
![tentang](https://github.com/andriawan/AndKasir/blob/development/screenshot/about.png "Tentang Aplikasi")

# Migrasi Databases

Mulai versi 0.5.8, Management database version akan menggunakan migration tool flayway. Sebelum melakukan aktifitas pengembangan, diharapkan untuk mengaktifkan migrasi database terlebih dahulu. Caranya ialah melakukan eksekusi pada terminal. Pastikan anda berada pada folder root tempat aplikasi AndKasir Berada :

```
java -jar AndKasir.jar db-migrate
```

Untuk clean database gunakan clean atau -c directive

```
java -jar AndKasir.jar clean
```

Info lanjut tentang flyway dapat anda peroleh pada [laman resmi flyway](http://flywaydb.org)



Sebelum mencoba Versi Development Pastikan

1. cek file config.properties. Konfigurasi database ada di file ini, seperti nama database, driver database dan lain-lain
2. cek folder lib. Semua dependensi library ada disini.
3. Mysql sudah terinstall pada komputer anda beserta database yang sudah disedakan pada source code
4. Pastikan Java runtime sudah terinstall (Direkomendasikan versi 7 atau 8)


Kontribusi:
- Anda bisa melakukan kontrbusi dengan cara fork repository ini, Kemudian buat branch baru. buat improvisasi, kemudian lakukan pull request
- direkomendasikan untuk menggunakan IDE Netbeans untuk kemudahan pengembangan GUI

TODO list:
- Dokumentasi secara lengkap 
- Release Stabil
- Lisensi ( Belum begitu paham. Mohon jika Anda berpengalaman dalam hal lisensi untuk melakukan pengecekan)
