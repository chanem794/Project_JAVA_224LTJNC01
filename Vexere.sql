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

-- Tạo bảng Xe
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
('ND001', N'Nguyễn Văn A', 'user1@example.com', '1990-01-01'),
('ND002', N'Trần Thị B', 'dinhknd3@gmail.com', '1995-05-15');
GO

-- Thêm dữ liệu mẫu cho Tuyen
INSERT INTO Tuyen VALUES 
(1, N'Hải Châu - Đà Nẵng', N'Hội An - Quảng Nam', 35);
GO

-- Thêm dữ liệu mẫu cho Xe
INSERT INTO Xe VALUES 
(101, N'Hữu Định', N'Ghế ngồi 29 chỗ', N'Hải Châu - Đà Nẵng', N'Hội An - Quảng Nam', '2025-05-01', '08:30', '07:00', 29, 29, 70000, 1);
GO

-- Thêm dữ liệu mẫu cho LichTrinhTuDong
INSERT INTO LichTrinhTuDong VALUES
(101, 1, N'Hải Châu - Đà Nẵng', 0),
(101, 2, N'Thanh Khê - Đà Nẵng', 600),
(101, 3, N'Ngũ Hành Sơn - Đà Nẵng', 900),
(101, 4, N'Điện Bàn - Quảng Nam', 1200),
(101, 5, N'Hội An - Quảng Nam', 900);
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
-- Mỗi tuyến có 1 chuyến đi và 1 chuyến về
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
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
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
                CASE @Route WHEN 1 THEN '08:30' WHEN 2 THEN '09:00' WHEN 3 THEN '10:00' WHEN 4 THEN '11:30' WHEN 5 THEN '12:30' END,
                CASE @Route WHEN 1 THEN '07:00' WHEN 2 THEN '07:30' WHEN 3 THEN '08:30' WHEN 4 THEN '09:30' WHEN 5 THEN '10:30' END,
                29, 29, 70000, @MaTuyen);

        -- Chuyến về
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
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
                CASE @Route WHEN 1 THEN '15:30' WHEN 2 THEN '16:00' WHEN 3 THEN '17:00' WHEN 4 THEN '18:30' WHEN 5 THEN '19:30' END,
                CASE @Route WHEN 1 THEN '14:00' WHEN 2 THEN '14:30' WHEN 3 THEN '15:30' WHEN 4 THEN '16:30' WHEN 5 THEN '17:30' END,
                29, 29, 70000, @MaTuyen + 1);

        SET @MaXe = @MaXe + 2;
        SET @MaTuyen = @MaTuyen + 2;
        SET @Route = @Route + 1;
    END
    SET @House = @House + 1;
END;
GO

-- Thêm dữ liệu vào bảng LichTrinhTuDong
-- Mỗi chuyến xe có 4–5 điểm dừng
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
                END, 
                CASE @Route WHEN 1 THEN 600 WHEN 2 THEN 500 WHEN 3 THEN 700 WHEN 4 THEN 800 WHEN 5 THEN 900 END),
            (@MaXe, 3, 
                CASE @Route 
                    WHEN 1 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 4 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 5 THEN N'Điện Bàn - Quảng Nam'
                END, 
                CASE @Route WHEN 1 THEN 900 WHEN 2 THEN 1200 WHEN 3 THEN 1000 WHEN 4 THEN 1100 WHEN 5 THEN 1300 END),
            (@MaXe, 4, 
                CASE @Route 
                    WHEN 1 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 2 THEN N'Hội An - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 5 THEN N'Tam Kỳ - Quảng Nam'
                END, 
                CASE @Route WHEN 1 THEN 1200 WHEN 2 THEN 900 WHEN 3 THEN 1000 WHEN 4 THEN 1500 WHEN 5 THEN 1800 END),
            (@MaXe, 5, 
                CASE @Route 
                    WHEN 1 THEN N'Hội An - Quảng Nam'
                    WHEN 2 THEN N'Điện Bàn - Quảng Nam'
                    WHEN 3 THEN N'Duy Xuyên - Quảng Nam'
                    WHEN 4 THEN N'Tam Kỳ - Quảng Nam'
                    WHEN 5 THEN N'Núi Thành - Quảng Nam'
                END, 
                CASE @Route WHEN 1 THEN 900 WHEN 2 THEN 0 WHEN 3 THEN 0 WHEN 4 THEN 1200 WHEN 5 THEN 1500 END);

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
                END, 
                CASE @Route WHEN 1 THEN 900 WHEN 2 THEN 900 WHEN 3 THEN 1000 WHEN 4 THEN 1200 WHEN 5 THEN 1500 END),
            (@MaXe + 1, 3, 
                CASE @Route 
                    WHEN 1 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 2 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 5 THEN N'Điện Bàn - Quảng Nam'
                END, 
                CASE @Route WHEN 1 THEN 1200 WHEN 2 THEN 1100 WHEN 3 THEN 1000 WHEN 4 THEN 1500 WHEN 5 THEN 1800 END),
            (@MaXe + 1, 4, 
                CASE @Route 
                    WHEN 1 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 2 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 3 THEN N'Cẩm Lệ - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Liên Chiểu - Đà Nẵng'
                END, 
                CASE @Route WHEN 1 THEN 600 WHEN 2 THEN 500 WHEN 3 THEN 700 WHEN 4 THEN 800 WHEN 5 THEN 900 END),
            (@MaXe + 1, 5, 
                CASE @Route 
                    WHEN 1 THEN N'Hải Châu - Đà Nẵng'
                    WHEN 2 THEN N'Thanh Khê - Đà Nẵng'
                    WHEN 3 THEN N'Ngũ Hành Sơn - Đà Nẵng'
                    WHEN 4 THEN N'Liên Chiểu - Đà Nẵng'
                    WHEN 5 THEN N'Sơn Trà - Đà Nẵng'
                END, 
                CASE @Route WHEN 1 THEN 600 WHEN 2 THEN 500 WHEN 3 THEN 700 WHEN 4 THEN 800 WHEN 5 THEN 900 END);

        SET @MaXe = @MaXe + 2;
        SET @Route = @Route + 1;
    END
    SET @House = @House + 1;
END;
GO