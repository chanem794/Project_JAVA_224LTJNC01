-- Phần code gốc giữ nguyên từ đầu đến trước đoạn INSERT vào bảng Xe
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

-- Tạo bảng Xe với ràng buộc NOT NULL cho TenXe
CREATE TABLE Xe (
    MaXe           INT PRIMARY KEY,
    TenXe          NVARCHAR(100) NOT NULL, -- Thêm NOT NULL
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
DECLARE @MaTuyen INT = 2;
DECLARE @i INT = 1;
DECLARE @j INT;

WHILE @i <= 5
BEGIN
    SET @j = 1;
    WHILE @j <= 5
    BEGIN
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

-- Xóa các bản ghi từ MaXe 102 trở đi để đảm bảo không có dữ liệu cũ
DELETE FROM Xe WHERE MaXe >= 102;
GO

-- Thêm dữ liệu vào bảng Xe với đảm bảo TenXe không NULL
DECLARE @MaXe INT = 102;
DECLARE @MaTuyen INT = 2;
DECLARE @House INT = 1;
DECLARE @Route INT;
DECLARE @RandomGiaVe INT;
DECLARE @RandomGioDi TIME;
DECLARE @RandomGioDen TIME;
DECLARE @RandomSoGhe INT;
DECLARE @RandomGheConTrong INT;
DECLARE @RandomTenXe NVARCHAR(100);

WHILE @House <= 5
BEGIN
    SET @Route = 1;
    WHILE @Route <= 5
    BEGIN
        -- Tạo giá vé ngẫu nhiên từ 70000 đến 200000, bội của 10000
        DECLARE @PricePoints INT = FLOOR(RAND() * 14);
        SET @RandomGiaVe = 70000 + (@PricePoints * 10000);
        
        -- Tạo số chỗ ngồi ngẫu nhiên từ 15 đến 35
        SET @RandomSoGhe = FLOOR(15 + (RAND() * (35 - 15 + 1)));
        
        -- Tạo số chỗ còn trống ngẫu nhiên từ 5 đến @RandomSoGhe
        SET @RandomGheConTrong = FLOOR(5 + (RAND() * (@RandomSoGhe - 5 + 1)));
        
        -- Tạo tên xe ngẫu nhiên, đảm bảo không NULL
        SET @RandomTenXe = CASE FLOOR(RAND() * 5)
            WHEN 0 THEN N'Hoàng Long'
            WHEN 1 THEN N'Thành Công'
            WHEN 2 THEN N'Phú Quý'
            WHEN 3 THEN N'Nam Phát'
            WHEN 4 THEN N'Hải Âu'
            ELSE N'Việt Nhật' -- Giá trị dự phòng để đảm bảo không NULL
        END;

        -- Tạo thời gian đi ngẫu nhiên từ 6:30 đến 23:30, cách nhau 30 phút
        DECLARE @StartSlots INT = 0;
        DECLARE @EndSlots INT = 34;
        DECLARE @RandomSlot INT = FLOOR(RAND() * (@EndSlots + 1));
        SET @RandomGioDi = DATEADD(MINUTE, 390 + (@RandomSlot * 30), '00:00:00');
        
        -- Tạo thời gian đến ngẫu nhiên, sau thời gian đi ít nhất 1 giờ
        DECLARE @MinSlotDen INT = @RandomSlot + 2;
        DECLARE @MaxSlotDen INT = CASE WHEN @RandomSlot + 6 <= @EndSlots THEN @RandomSlot + 6 ELSE @EndSlots END;
        DECLARE @RandomDenSlot INT = @MinSlotDen + FLOOR(RAND() * (@MaxSlotDen - @MinSlotDen + 1));
        SET @RandomGioDen = DATEADD(MINUTE, 390 + (@RandomDenSlot * 30), '00:00:00');

        -- Chuyến đi
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
        VALUES (@MaXe, 
                @RandomTenXe,
                N'Ghế ngồi ' + CAST(@RandomSoGhe AS NVARCHAR) + N' chỗ',
                (SELECT DiemDi FROM Tuyen WHERE MaTuyen = @MaTuyen),
                (SELECT DiemDen FROM Tuyen WHERE MaTuyen = @MaTuyen),
                '2025-05-01',
                @RandomGioDen,
                @RandomGioDi,
                @RandomSoGhe,
                @RandomGheConTrong,
                @RandomGiaVe,
                @MaTuyen);

        -- Tạo giá vé ngẫu nhiên cho chuyến về
        SET @PricePoints = FLOOR(RAND() * 14);
        SET @RandomGiaVe = 70000 + (@PricePoints * 10000);
        
        -- Tạo số chỗ ngồi ngẫu nhiên cho chuyến về
        SET @RandomSoGhe = FLOOR(15 + (RAND() * (35 - 15 + 1)));
        
        -- Tạo số chỗ còn trống ngẫu nhiên cho chuyến về
        SET @RandomGheConTrong = FLOOR(5 + (RAND() * (@RandomSoGhe - 5 + 1)));
        
        -- Tạo tên xe ngẫu nhiên cho chuyến về, đảm bảo không NULL
        SET @RandomTenXe = CASE FLOOR(RAND() * 5)
            WHEN 0 THEN N'Hoàng Long'
            WHEN 1 THEN N'Thành Công'
            WHEN 2 THEN N'Phú Quý'
            WHEN 3 THEN N'Nam Phát'
            WHEN 4 THEN N'Hải Âu'
            ELSE N'Việt Nhật' -- Giá trị dự phòng
        END;

        -- Tạo thời gian đi ngẫu nhiên cho chuyến về
        SET @RandomSlot = FLOOR(RAND() * (@EndSlots + 1));
        SET @RandomGioDi = DATEADD(MINUTE, 390 + (@RandomSlot * 30), '00:00:00');
        
        -- Tạo thời gian đến ngẫu nhiên cho chuyến về
        SET @MinSlotDen = @RandomSlot + 2;
        SET @MaxSlotDen = CASE WHEN @RandomSlot + 6 <= @EndSlots THEN @RandomSlot + 6 ELSE @EndSlots END;
        SET @RandomDenSlot = @MinSlotDen + FLOOR(RAND() * (@MaxSlotDen - @MinSlotDen + 1));
        SET @RandomGioDen = DATEADD(MINUTE, 390 + (@RandomDenSlot * 30), '00:00:00');

        -- Chuyến về
        INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen)
        VALUES (@MaXe + 1, 
                @RandomTenXe,
                N'Ghế ngồi ' + CAST(@RandomSoGhe AS NVARCHAR) + N' chỗ',
                (SELECT DiemDi FROM Tuyen WHERE MaTuyen = @MaTuyen + 1),
                (SELECT DiemDen FROM Tuyen WHERE MaTuyen = @MaTuyen + 1),
                '2025-05-01',
                @RandomGioDen,
                @RandomGioDi,
                @RandomSoGhe,
                @RandomGheConTrong,
                @RandomGiaVe,
                @MaTuyen + 1);

        SET @MaXe = @MaXe + 2;
        SET @MaTuyen = @MaTuyen + 2;
        SET @Route = @Route + 1;
    END
    SET @House = @House + 1;
END;
GO

-- Thêm dữ liệu vào bảng LichTrinhTuDong
DECLARE @MaXe INT = 102;
DECLARE @House INT = 1;
DECLARE @Route INT;

WHILE @House <= 5
BEGIN
    SET @Route = 1;
    WHILE @Route <= 5
    BEGIN
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