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
    MaNguoiDung     VARCHAR(10) PRIMARY KEY,
    TenNguoiDung    NVARCHAR(50) NOT NULL,
    Email           VARCHAR(100) NOT NULL UNIQUE,
    NgaySinh        DATE,
    OtpCode         VARCHAR(6),
    Otp_taoThoiGian DATETIME
);
GO

-- Tạo bảng Tuyen
CREATE TABLE Tuyen (
    MaTuyen     INT PRIMARY KEY,
    DiemDi      NVARCHAR(100),
    DiemDen     NVARCHAR(100),
    QuangDuong  INT
);
GO

-- Tạo bảng Xe (không có TenTaiXe)
CREATE TABLE Xe (
    MaXe           INT PRIMARY KEY,
    TenXe          NVARCHAR(100),
    LoaiXe         NVARCHAR(50),
    DiemDi         NVARCHAR(100),
    DiemDen        NVARCHAR(100),
    NgayKhoiHanh   DATE,
    GioDen         TIME,
    GioDi          TIME,
    SoGhe          INT,
    GheConTrong    INT,
    GiaVe          INT,
    MaTuyen        INT,
    CONSTRAINT FK_Xe_Tuyen FOREIGN KEY (MaTuyen) REFERENCES Tuyen(MaTuyen)
);
GO

-- Tạo bảng DatCho
CREATE TABLE DatCho (
    MaDatCho         INT PRIMARY KEY,
    TrangThai        NVARCHAR(50),
    NgayDat          DATE,
    GioDat           TIME,
    DiemDi           NVARCHAR(100),
    DiemDen          NVARCHAR(100),
    NgayGioKhoiHanh  DATETIME,
    SoGheDat         INT,
    GiaVe            INT,
    MaNguoiDung      VARCHAR(10),
    MaXe             INT,
    CONSTRAINT FK_DatCho_NguoiDung FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    CONSTRAINT FK_DatCho_Xe FOREIGN KEY (MaXe) REFERENCES Xe(MaXe)
);
GO

-- ✅ Tạo bảng LichTrinhTuDong (chỉ còn MaXe, ThuTu, DiaDiem, ThoiGianDuKien)
CREATE TABLE LichTrinhTuDong (
    MaLichTrinhTuDong INT IDENTITY PRIMARY KEY,
    MaXe              INT,
    ThuTu             INT, -- Thứ tự điểm dừng
    DiaDiem           NVARCHAR(255), -- Tên địa điểm
    ThoiGianDuKien    INT, -- Thời gian di chuyển từ điểm trước (giây)
    CONSTRAINT FK_LichTrinhTuDong_Xe FOREIGN KEY (MaXe) REFERENCES Xe(MaXe)
);
GO

-- Thêm dữ liệu mẫu cho NguoiDung
INSERT INTO NguoiDung (MaNguoiDung, TenNguoiDung, Email, NgaySinh) VALUES
('ND001', N'Nguyễn Văn A', 'user1@example.com', '1990-01-01'),
('ND002', N'Trần Thị B', 'dinhknd3@gmail.com', '1995-05-15');
GO
