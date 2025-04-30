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
INSERT INTO Tuyen (MaTuyen, DiemDi, DiemDen, QuangDuong) VALUES
(1, N'Bến xe Miền Đông', N'Bến xe Miền Tây', 15),
(2, N'Bến xe Miền Đông', N'Bến xe An Sương', 10),
(3, N'Bến xe An Sương', N'Bến xe Củ Chi', 30),
(4, N'Bến xe Miền Tây', N'Bến xe Chợ Lớn', 8),
(5, N'Bến xe Củ Chi', N'Bến xe An Sương', 30);
GO
INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen) VALUES
(1, N'Xe Sài Gòn 1', N'Xe buýt', N'Bến xe Miền Đông', N'Bến xe Miền Tây', '2025-05-01', '08:00:00', '07:00:00', 40, 40, 10000, 1),
(2, N'Xe Sài Gòn 2', N'Xe buýt', N'Bến xe Miền Đông', N'Bến xe An Sương', '2025-05-01', '09:00:00', '08:00:00', 35, 35, 8000, 2),
(3, N'Xe Củ Chi 1', N'Xe buýt', N'Bến xe An Sương', N'Bến xe Củ Chi', '2025-05-01', '10:30:00', '09:30:00', 50, 50, 15000, 3),
(4, N'Xe Miền Tây 1', N'Xe buýt', N'Bến xe Miền Tây', N'Bến xe Chợ Lớn', '2025-05-01', '07:45:00', '07:00:00', 30, 30, 7000, 4),
(5, N'Xe Củ Chi 2', N'Xe buýt', N'Bến xe Củ Chi', N'Bến xe An Sương', '2025-05-01', '11:00:00', '10:00:00', 45, 45, 14000, 5);
GO
INSERT INTO LichTrinhTuDong (MaXe, ThuTu, DiaDiem, ThoiGianDuKien) VALUES
(1, 1, N'Bến xe Miền Đông', 0),
(1, 2, N'Cầu Bình Triệu', 600),
(1, 3, N'Bến xe Miền Tây', 900),
(2, 1, N'Bến xe Miền Đông', 0),
(2, 2, N'Bến xe An Sương', 1200);
GO
