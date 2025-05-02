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

-- Tạo bảng Xe (bao gồm NgayDen)
CREATE TABLE Xe (
    MaXe           INT PRIMARY KEY,
    TenXe          NVARCHAR(100),
    LoaiXe         NVARCHAR(50),
    DiemDi         NVARCHAR(100),
    DiemDen        NVARCHAR(100),
    NgayKhoiHanh   DATE,
    NgayDen        DATE,
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

-- Tạo bảng LichTrinhTuDong
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
('ND001', N'Nguyễn Văn A', 'chanem794@gmail.com', '1990-01-01'),
('ND002', N'Trần Thị B', 'dinhknd3@gmail.com', '1995-05-15');
GO

-- Thêm dữ liệu mẫu cho Tuyen
INSERT INTO Tuyen VALUES 
(1, N'Hải Châu - Đà Nẵng', N'Hội An - Quảng Nam', 35);
GO

-- Thêm dữ liệu mẫu cho Xe (GioDen tính tự động)
INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, NgayDen, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
VALUES 
(101, N'Hữu Định', N'Ghế ngồi 29 chỗ', N'Hải Châu - Đà Nẵng', N'Hội An - Quảng Nam', '2025-05-01', '2025-05-01', 
 DATEADD(SECOND, 7200, '07:00'), '07:00', 29, 29, 70000, 1);
GO
-- Thêm dữ liệu mẫu cho Đặt Chỗ
INSERT INTO DatCho (MaDatCho, TrangThai, NgayDat, GioDat, DiemDi, DiemDen, NgayGioKhoiHanh, SoGheDat, GiaVe, MaNguoiDung, MaXe)
VALUES
(1, N'Đã đặt', '2025-05-01', '06:00', N'Hải Châu - Đà Nẵng', N'Hội An - Quảng Nam', '2025-05-01 07:00', 2, 70000, 'ND002', 101),
(2, N'Đã đặt', '2025-05-01', '06:30', N'Thanh Khê - Đà Nẵng', N'Điện Bàn - Quảng Nam', '2025-05-01 07:30', 3, 70000, 'ND002', 101),
(3, N'Đã đặt', '2025-05-01', '07:00', N'Ngũ Hành Sơn - Đà Nẵng', N'Duy Xuyên - Quảng Nam', '2025-05-01 08:00', 4, 70000, 'ND001', 101),
(4, N'Đã đặt', '2025-05-01', '07:30', N'Liên Chiểu - Đà Nẵng', N'Tam Kỳ - Quảng Nam', '2025-05-01 08:30', 5, 70000, 'ND001', 101);
GO
-- Thêm dữ liệu mẫu cho LichTrinhTuDong (ThoiGianDuKien = 1800 giây)
INSERT INTO LichTrinhTuDong VALUES
(101, 1, N'Hải Châu - Đà Nẵng', 0),
(101, 2, N'Thanh Khê - Đà Nẵng', 1800),
(101, 3, N'Ngũ Hành Sơn - Đà Nẵng', 1800),
(101, 4, N'Điện Bàn - Quảng Nam', 1800),
(101, 5, N'Hội An - Quảng Nam', 1800);
GO

-- Thêm dữ liệu vào bảng Tuyen
-- Mỗi nhà xe có 10 tuyến (5 chiều đi, 5 chiều về)
DECLARE @MaTuyen INT = 2; -- Bắt đầu từ 2 vì tuyến 1 đã có trong dữ liệu mẫu
DECLARE @i INT = 1;
DECLARE @j INT;

WHILE @i <= 5 -- 5 nhà xe
BEGIN
    SET @j = 1;
    WHILE @j <= 5 -- 5 cặp tuyến (đi và về)
    BEGIN
        -- Thêm tuyến chiều đi
        INSERT INTO Tuyen (MaTuyen, DiemDi, DiemDen, QuangDuong)
        VALUES (@MaTuyen, 
                CASE @j 
                    WHEN 1 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 2 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Sơn Trà - Đà Nẵng'
                END,
                CASE @j 
                    WHEN 1 THEN N'Hội An - Quảng Nam'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Tam Kỳ - Quảng Nam'
                    WHEN 5 THEN N'Núi Thành - Quảng Nam'
                END,
                CASE @j WHEN 1 THEN 35 WHEN 2 THEN 25 WHEN 3 THEN 40 WHEN 4 THEN 60 WHEN 5 THEN 80 END);

        -- Thêm tuyến chiều về
        INSERT INTO Tuyen (MaTuyen, DiemDi, DiemDen, QuangDuong)
        VALUES (@MaTuyen + 1, 
                CASE @j 
                    WHEN 1 THEN N'Hội An - Quảng Nam'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Tam Kỳ - Quảng Nam'
                    WHEN 5 THEN N'Núi Thành - Quảng Nam'
                END,
                CASE @j 
                    WHEN 1 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 2 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Sơn Trà - Đà Nẵng'
                END,
                CASE @j WHEN 1 THEN 35 WHEN 2 THEN 25 WHEN 3 THEN 40 WHEN 4 THEN 60 WHEN 5 THEN 80 END);

        SET @MaTuyen = @MaTuyen + 2;
        SET @j = @j + 1;
    END
    SET @i = @i + 1;
END;
GO

-- Thêm dữ liệu vào bảng Xe
-- Mỗi tuyến có 1 chuyến đi và 1 chuyến về (GioDen tính tự động)
DECLARE @MaXe INT = 102; -- Bắt đầu từ 102 vì xe 101 đã có
DECLARE @MaTuyen INT = 2;
DECLARE @House INT = 1;
DECLARE @Route INT;

WHILE @House <= 5 -- 5 nhà xe
BEGIN
    SET @Route = 1;
    WHILE @Route <= 5 -- 5 cặp tuyến
    BEGIN
        -- Chuyến đi
        DECLARE @GioDi1 TIME = CASE @Route 
                                  WHEN 1 THEN '07:00'
                                  WHEN 2 THEN '07:30'
                                  WHEN 3 THEN '08:30'
                                  WHEN 4 THEN '09:30'
                                  WHEN 5 THEN '10:30'
                               END;
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, NgayDen, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
        VALUES (@MaXe, 
                CASE @House 
                    WHEN 1 THEN N'Hữu Định'
                    WHEN 2 THEN N'Thọ Khang'
                    WHEN 3 THEN N'Minh Huy'
                    WHEN 4 THEN N'Nguyễn Huỳnh'
                    WHEN 5 THEN N'Duy Quốc'
                END,
                N'Ghế ngồi 29 chỗ',
                (SELECT DiemDi FROM Tuyen WHERE MaTuyen = @MaTuyen),
                (SELECT DiemDen FROM Tuyen WHERE MaTuyen = @MaTuyen),
                '2025-05-01',
                '2025-05-01',
                DATEADD(SECOND, 7200, @GioDi1), -- GioDen = GioDi + 2 giờ
                @GioDi1,
                29, 29, 70000, @MaTuyen);

        -- Chuyến về
        DECLARE @GioDi2 TIME = CASE @Route 
                                  WHEN 1 THEN '14:00'
                                  WHEN 2 THEN '14:30'
                                  WHEN 3 THEN '15:30'
                                  WHEN 4 THEN '16:30'
                                  WHEN 5 THEN '17:30'
                               END;
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, NgayDen, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
        VALUES (@MaXe + 1, 
                CASE @House 
                    WHEN 1 THEN N'Hữu Định'
                    WHEN 2 THEN N'Thọ Khang'
                    WHEN 3 THEN N'Minh Huy'
                    WHEN 4 THEN N'Nguyễn Huỳnh'
                    WHEN 5 THEN N'Duy Quốc'
                END,
                N'Ghế ngồi 29 chỗ',
                (SELECT DiemDi FROM Tuyen WHERE MaTuyen = @MaTuyen + 1),
                (SELECT DiemDen FROM Tuyen WHERE MaTuyen = @MaTuyen + 1),
                '2025-05-01',
                '2025-05-01',
                DATEADD(SECOND, 7200, @GioDi2), -- GioDen = GioDi + 2 giờ
                @GioDi2,
                29, 29, 70000, @MaTuyen + 1);

        SET @MaXe = @MaXe + 2;
        SET @MaTuyen = @MaTuyen + 2;
        SET @Route = @Route + 1;
    END
    SET @House = @House + 1;
END;
GO

-- Thêm dữ liệu vào bảng LichTrinhTuDong
-- Mỗi chuyến xe có 5 điểm dừng, ThoiGianDuKien = 1800 giây (30 phút) mỗi đoạn
DECLARE @MaXe INT = 102;
DECLARE @House INT = 1;
DECLARE @Route INT;

WHILE @House <= 5 -- 5 nhà xe
BEGIN
    SET @Route = 1;
    WHILE @Route <= 5 -- 5 cặp tuyến
    BEGIN
        -- Lịch trình cho chuyến đi
        INSERT INTO LichTrinhTuDong (MaXe, ThuTu, DiaDiem, ThoiGianDuKien)
        VALUES 
            (@MaXe, 1, 
                CASE @Route 
                    WHEN 1 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 2 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Sơn Trà - Đà Nẵng'
                END, 0),
            (@MaXe, 2, 
                CASE @Route 
                    WHEN 1 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 2 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 3 THEN N'Cẩm Lệ - Đà Nẵng'
                    WHEN 4 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 5 THEN N'Liên Chiểu - Đà Nẵng'
                END, 1800),
            (@MaXe, 3, 
                CASE @Route 
                    WHEN 1 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 4 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 5 THEN N'Điện Bàn - Quảng Nam'
                END, 1800),
            (@MaXe, 4, 
                CASE @Route 
                    WHEN 1 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 2 THEN N'Hội An - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 5 THEN N'Tam Kỳ - Quảng Nam'
                END, 1800),
            (@MaXe, 5, 
                CASE @Route 
                    WHEN 1 THEN N'Hội An - Quảng Nam'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Tam Kỳ - Quảng Nam'
                    WHEN 5 THEN N'Núi Thành - Quảng Nam'
                END, 1800);

        -- Lịch trình cho chuyến về
        INSERT INTO LichTrinhTuDong (MaXe, ThuTu, DiaDiem, ThoiGianDuKien)
        VALUES 
            (@MaXe + 1, 1, 
                CASE @Route 
                    WHEN 1 THEN N'Hội An - Quảng Nam'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Tam Kỳ - Quảng Nam'
                    WHEN 5 THEN N'Núi Thành - Quảng Nam'
                END, 0),
            (@MaXe + 1, 2, 
                CASE @Route 
                    WHEN 1 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 2 THEN N'Hội An - Quảng Nam'
                    WHEN 3 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 4 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 5 THEN N'Tam Kỳ - Quảng Nam'
                END, 1800),
            (@MaXe + 1, 3, 
                CASE @Route 
                    WHEN 1 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 2 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 5 THEN N'Điện Bàn - Quảng Nam'
                END, 1800),
            (@MaXe + 1, 4, 
                CASE @Route 
                    WHEN 1 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 2 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 3 THEN N'Cẩm Lệ - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Liên Chiểu - Đà Nẵng'
                END, 1800),
            (@MaXe + 1, 5, 
                CASE @Route 
                    WHEN 1 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 2 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Sơn Trà - Đà Nẵng'
                END, 1800);

        SET @MaXe = @MaXe + 2;
        SET @Route = @Route + 1;
    END
    SET @House = @House + 1;
END;
GO