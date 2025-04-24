USE master;
GO

-- Kiểm tra và xóa database cũ nếu tồn tại
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'QuanLyVeXeBuyt')
BEGIN
    ALTER DATABASE QuanLyVeXeBuyt SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QuanLyVeXeBuyt;
END;
GO

-- Tạo database mới
CREATE DATABASE QuanLyVeXeBuyt;
GO

USE QuanLyVeXeBuyt;
GO

-- Tạo bảng NguoiDung
CREATE TABLE NguoiDung (
    MaNguoiDung     VARCHAR(10)     PRIMARY KEY,
    TenNguoiDung    NVARCHAR(50)    NOT NULL,
    Email           VARCHAR(100)    NOT NULL UNIQUE,
    NgaySinh        DATE,
    OtpCode         VARCHAR(6),
    Otp_taoThoiGian DATETIME
);
GO

-- Tạo bảng Tuyen
CREATE TABLE Tuyen (
    MaTuyen INT PRIMARY KEY,
    DiemDi VARCHAR(100),
    DiemDen VARCHAR(100),
    QuangDuong INT
);
GO

-- Tạo bảng Xe
CREATE TABLE Xe (
    MaXe INT PRIMARY KEY,
    TenXe VARCHAR(100),
    TenTaiXe VARCHAR(100),
    LoaiXe VARCHAR(50),
    DiemDi VARCHAR(100),
    DiemDen VARCHAR(100),
    NgayKhoiHanh DATE,
    GioDen TIME,
    GioDi TIME,
    SoGhe INT,
    GheConTrong INT,
    GiaVe INT,
    MaTuyen INT,
    CONSTRAINT FK_Xe_Tuyen FOREIGN KEY (MaTuyen) REFERENCES Tuyen(MaTuyen)
);
GO

-- Tạo bảng DatCho
CREATE TABLE DatCho (
    MaDatCho INT PRIMARY KEY,
    TrangThai VARCHAR(50),
    NgayDat DATE,
    GioDat TIME,
    DiemDi VARCHAR(100),
    DiemDen VARCHAR(100),
    NgayGioKhoiHanh DATETIME,
    SoGheDat INT,
    GiaVe INT,
    MaNguoiDung VARCHAR(10), -- Sửa INT thành VARCHAR(10) để khớp với NguoiDung
    MaXe INT,
    CONSTRAINT FK_DatCho_NguoiDung FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    CONSTRAINT FK_DatCho_Xe FOREIGN KEY (MaXe) REFERENCES Xe(MaXe)
);
GO

-- Thêm dữ liệu mẫu
INSERT INTO NguoiDung (MaNguoiDung, TenNguoiDung, Email, NgaySinh) VALUES
('ND001', N'Nguyễn Văn A', 'user1@example.com', '1990-01-01'),
('ND002', N'Trần Thị B', 'dinhknd3@gmail.com', '1995-05-15');
