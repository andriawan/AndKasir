-- Initial database schema for AndKasir Desktop v2.0
-- Migration V1__Create_initial_schema.sql

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) NOT NULL DEFAULT 'KASIR',
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    level_permission INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,
    keterangan VARCHAR(500),
    is_deleted BOOLEAN DEFAULT FALSE
);

-- Create barang table
CREATE TABLE barang (
    id BIGSERIAL PRIMARY KEY,
    kode_barang VARCHAR(50) UNIQUE NOT NULL,
    nama_barang VARCHAR(100) NOT NULL,
    harga DECIMAL(10,2) NOT NULL,
    stok INTEGER NOT NULL DEFAULT 0,
    jumlah_barang_masuk INTEGER,
    jumlah_barang_keluar INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- Create transaksi table
CREATE TABLE transaksi (
    id BIGSERIAL PRIMARY KEY,
    nomor_transaksi VARCHAR(20) UNIQUE NOT NULL,
    total_item INTEGER NOT NULL,
    total_harga DECIMAL(12,2) NOT NULL,
    diskon DECIMAL(5,2) DEFAULT 0.00,
    pajak DECIMAL(5,2) DEFAULT 0.00,
    grand_total DECIMAL(12,2) NOT NULL,
    bayar DECIMAL(12,2) NOT NULL,
    kembalian DECIMAL(12,2) NOT NULL,
    id_kasir BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    keterangan VARCHAR(500),
    status_transaksi VARCHAR(20) NOT NULL DEFAULT 'SELESAI',
    detail_transaksi JSON,
    CONSTRAINT fk_transaksi_kasir FOREIGN KEY (id_kasir) REFERENCES users(id)
);

-- Create detail_transaksi table
CREATE TABLE detail_transaksi (
    id BIGSERIAL PRIMARY KEY,
    id_transaksi BIGINT NOT NULL,
    id_barang BIGINT NOT NULL,
    kode_barang VARCHAR(50) NOT NULL,
    nama_barang VARCHAR(100) NOT NULL,
    jumlah INTEGER NOT NULL,
    harga_satuan DECIMAL(10,2) NOT NULL,
    diskon_item DECIMAL(5,2) DEFAULT 0.00,
    subtotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_detail_transaksi_transaksi FOREIGN KEY (id_transaksi) REFERENCES transaksi(id) ON DELETE CASCADE,
    CONSTRAINT fk_detail_transaksi_barang FOREIGN KEY (id_barang) REFERENCES barang(id)
);

-- Create indexes for performance
CREATE INDEX idx_users_username ON users(username) WHERE NOT is_deleted;
CREATE INDEX idx_users_email ON users(email) WHERE NOT is_deleted;
CREATE INDEX idx_users_role ON users(role) WHERE NOT is_deleted;
CREATE INDEX idx_users_status ON users(status) WHERE NOT is_deleted;

CREATE INDEX idx_barang_kode ON barang(kode_barang) WHERE NOT is_deleted;
CREATE INDEX idx_barang_nama ON barang(nama_barang) WHERE NOT is_deleted;
CREATE INDEX idx_barang_stok ON barang(stok) WHERE NOT is_deleted;
CREATE INDEX idx_barang_created ON barang(created_at) WHERE NOT is_deleted;

CREATE INDEX idx_transaksi_nomor ON transaksi(nomor_transaksi);
CREATE INDEX idx_transaksi_kasir ON transaksi(id_kasir);
CREATE INDEX idx_transaksi_status ON transaksi(status_transaksi);
CREATE INDEX idx_transaksi_created ON transaksi(created_at);
CREATE INDEX idx_transaksi_date_range ON transaksi(created_at, status_transaksi);

CREATE INDEX idx_detail_transaksi_id ON detail_transaksi(id_transaksi);
CREATE INDEX idx_detail_transaksi_barang ON detail_transaksi(id_barang);
CREATE INDEX idx_detail_transaksi_kode ON detail_transaksi(kode_barang);

-- Insert default admin user
INSERT INTO users (username, password, nama, email, role, status) 
VALUES ('admin', '$2a$12$9L6A2q8Q8q7K6A9r4n6.O/xK6A9r4n6O/xK6A9r4n6', 'Administrator', 'admin@andkasir.com', 'ADMIN', 'ACTIVE');

-- Insert default kasir user
INSERT INTO users (username, password, nama, email, role, status) 
VALUES ('kasir', '$2a$12$9L6A2q8Q8q7K6A9r4n6.O/xK6A9r4n6O/xK6A9r4n6', 'Kasir Default', 'kasir@andkasir.com', 'KASIR', 'ACTIVE');

-- Insert sample products
INSERT INTO barang (kode_barang, nama_barang, harga, stok) VALUES
('BRG001', 'Minyak Goreng', 25000, 100),
('BRG002', 'Gula Pasir', 15000, 150),
('BRG003', 'Beras 5kg', 75000, 50),
('BRG004', 'Tepung Terigu', 12000, 200),
('BRG005', 'Telur 1kg', 28000, 80);