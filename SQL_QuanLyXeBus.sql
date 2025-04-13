use master
if exists (select name from sys.databases where name = 'QuanLyVeXeBuyt')
begin
    alter database QuanLyVeXeBuyt set single_user with rollback immediate;
    drop database QuanLyVeXeBuyt;
end;
--Tạo database
create database QuanLyVeXeBuyt
go
use QuanLyVeXeBuyt
go
create table QUOCGIA(
    ID_QUOC_GIA char(5) primary key,
    TEN_QUOC_GIA nvarchar(30) not null
)
create table TINH(
    ID_TINH char(4) primary key,
    QUOC_GIA_NO char(5) not null foreign key references QUOCGIA(ID_QUOC_GIA)
        on update cascade
        on delete cascade,
    TEN_TINH nvarchar(30) not null
)
create table QUANHUYEN(
    ID_QUAN_HUYEN char(5) primary key,
    TINH_NO char(4) not null foreign key references TINH(ID_TINH)
        on update cascade
        on delete cascade,
    TEN_QUAN_HUYEN nvarchar(30) not null
)
create table PHUONGXA(
    ID_PHUONG_XA char(5) primary key,
    QUAN_HUYEN_NO char(5) not null foreign key references QUANHUYEN(ID_QUAN_HUYEN)
        on update cascade
        on delete cascade,
    TEN_PHUONG_XA nvarchar(30) not null
)
create table TRAM(
    ID_TRAM char(4) primary key,
    PHUONG_XA_NO char(5) not null foreign key references PHUONGXA(ID_PHUONG_XA)
        on update cascade
        on delete cascade,
    TEN_TRAM nvarchar(100) not null
)
create table CHUYENDI(
    ID_CHUYEN_DI char(5) primary key,
    TRAM_NO char(4) not null foreign key references TRAM(ID_TRAM)
        on delete cascade
        on update cascade,
    TEN_CHUYEN_DI nvarchar(30) not null
)
create table TINHTRANGBOOK(
    ID_TINH_TRANG_BOOK char(5) primary key,
    TINH_TRANG nvarchar(10) not null
)
create table GHE(
    ID_GHE char(4) primary key,
    LOAI_GHE int not null
)
create table BUS(
    ID_BUS char(4) primary key,
    GHE_NO char(4) not null foreign key references GHE(ID_GHE)
        on delete cascade
        on update cascade,
    TEN_XE_BUS nvarchar(30) not null,
    MAU_XE nvarchar(10) not null,
    BIEN_SO varchar(10) not null,
    SO_CHO_NGOI int not null
)
create table NHAXEBUS(
    ID_NHA_XE_BUS char(5) primary key,
    BUS_NO char(4) not null foreign key references BUS(ID_BUS)
        on update cascade
        on delete cascade,
    DIA_CHI_NHA_XE char(5) not null foreign key references PHUONGXA(ID_PHUONG_XA)
        on update cascade
        on delete cascade,
    TEN_NHA_XE nvarchar(30) not null
)
create table KHACHHANG(
    ID_KHACH_HANG char(5) primary key,
    TEN_KHACH_HANG nvarchar(30) not null,
    EMAIL char(30) not null,
    MAT_KHAU char(30) not null,
    SO_DIEN_THOAI char(10) not null
)
create table CHUYENDI_BUS(
    CHUYEN_DI_NO char(5) not null foreign key references CHUYENDI(ID_CHUYEN_DI)
        on delete cascade
        on update cascade,
    NHA_XE_BUS_NO char(5) not null foreign key references NHAXEBUS(ID_NHA_XE_BUS)
        on delete no action,
    THOI_GIAN_XUAT_PHAT datetime not null,
    primary key (CHUYEN_DI_NO, NHA_XE_BUS_NO, THOI_GIAN_XUAT_PHAT),
    THOI_GIAN_DEN datetime not null,
    TIEN_XE money not null
)
create table BOOKXE(
    ID_BOOK_XE char(5) primary key,
    CHUYEN_DI_NO char(5) not null,
    NHA_XE_BUS_NO char(5) not null,
    THOI_GIAN_XUAT_PHAT datetime not null,
    foreign key (CHUYEN_DI_NO, NHA_XE_BUS_NO, THOI_GIAN_XUAT_PHAT)
        references CHUYENDI_BUS(CHUYEN_DI_NO, NHA_XE_BUS_NO, THOI_GIAN_XUAT_PHAT)
        on update cascade
        on delete cascade,
    KHACH_HANG_NO char(5) not null foreign key references KHACHHANG(ID_KHACH_HANG),
    TINH_TRANG_BOOK_NO char(5) not null foreign key references TINHTRANGBOOK(ID_TINH_TRANG_BOOK)
        on update cascade
        on delete cascade,
    BUS_NO char(4) not null foreign key references BUS(ID_BUS)
        on update cascade
        on delete cascade
)
--===============================================Insert dữ liệu ở đây=================================================
--Chèn dữ liệu vào bảng QUOCGIA
insert into QUOCGIA
values ('QG001', N'Việt Nam'),
    ('QG002', N'Hàn Quốc'),
    ('QG003', N'Thái Lan'),
    ('QG004', N'Nhật Bản'),
    ('QG005', N'Mỹ'),
    ('QG006', N'Lào'),
    ('QG007', N'Trung Quốc'),
    ('QG008', N'Anh'),
    ('QG009', N'Pháp'),
    ('QG010', N'Đức')

--Chèn dữ liệu cho bảng TINH
insert into TINH
values ('T001', 'QG001', N'Đà Nẵng'),
    ('T002', 'QG001', N'Quảng Nam'),
    ('T003', 'QG001', N'Hà Nội'),
    ('T004', 'QG001', N'Hồ Chí Minh'),
    ('T005', 'QG001', N'Khánh Hòa'),
    ('T006', 'QG001', N'Bình Định'),
    ('T007', 'QG001', N'Quảng Ngãi'),
    ('T008', 'QG001', N'Lâm Đồng'),
    ('T009', 'QG001', N'Nghệ An'),
    ('T010', 'QG001', N'Thừa Thiên Huế')

--Chèn dữ liệu cho bảng QUANHUYEN
insert into QUANHUYEN
values ('QH001', 'T001', N'Hải Châu'),
    ('QH002', 'T001', N'Thanh Khê'),
    ('QH003', 'T002', N'Phú Ninh'),
    ('QH004', 'T002', N'Điện Bàn'),
    ('QH005', 'T003', N'Ba Đình'),
    ('QH006', 'T001', N'Liên Chiểu'),
    ('QH007', 'T005', N'Nha Trang'),
    ('QH008', 'T006', N'Quy Nhơn'),
    ('QH009', 'T007', N'Đức Phổ'),
    ('QH010', 'T008', N'Đà Lạt')

--Chèn dữ liệu cho bảng PHUONGXA
insert into PHUONGXA
values ('PX001', 'QH003', N'Tam Thành'),
    ('PX002', 'QH001', N'Thanh Bình'),
    ('PX003', 'QH003', N'Tam An'),
    ('PX004', 'QH002', N'Thạc Gián'),
    ('PX005', 'QH004', N'Vĩnh Điện'),
    ('PX006', 'QH005', N'Điện Biên'),
    ('PX007', 'QH006', N'Hòa Minh'),
    ('PX008', 'QH007', N'Vạn Thắng'),
    ('PX009', 'QH008', N'Ngô Mây'),
    ('PX010', 'QH010', N'Phước Lộc')

--Chèn dữ liệu cho bảng TRAM
insert into TRAM
values ('TR01', 'PX001', N'Trạm Tt1'),
    ('TR02', 'PX007', N'Bến Xe Trung Tâm Đà Nẵng'),
    ('TR03', 'PX004', N'Công Viên 29/3'),
    ('TR04', 'PX003', N'Trạm Tam Thuận'),
    ('TR05', 'PX003', N'Trạm An Mỹ'),
    ('TR06', 'PX005', N'Trạm Ngã 3 Vĩnh Điện'),
    ('TR07', 'PX006', N'Trạm Điện Biên'),
    ('TR08', 'PX007', N'Trạm Bến Nghé'),
    ('TR09', 'PX008', N'Trạm Vạn Thắng'),
    ('TR10', 'PX009', N'Trạm Ngô Mây')

--Chèn dữ liệu cho bảng CHUYENDI
insert into CHUYENDI
values ('CD001', 'TR02', N'Đà Nẵng - Hà Nội'),
    ('CD002', 'TR02', N'Đà Nẵng - Tp.hcm'),
    ('CD003', 'TR02', N'Đà Nẵng - Tam Kỳ'),
    ('CD004', 'TR03', N'Đà Nẵng - Huế'),
    ('CD005', 'TR04', N'Đà Nẵng - Hội An'),
    ('CD006', 'TR01', N'Tam Thành - Đà Nẵng'),
    ('CD007', 'TR06', N'Điện Bàn - Tam Kỳ'),
    ('CD008', 'TR02', N'Liên Chiểu - Ngũ Hành Sơn'),
    ('CD009', 'TR09', N'Vạn Thắng - Sài Gòn'),
    ('CD010', 'TR10', N'Ngô Mây - Huế')

--Chèn dữ liệu cho bảng TINHTRANGBOOK
insert into TINHTRANGBOOK
values ('TT001', N'Đã Đặt'),
    ('TT002', N'Hủy'),
    ('TT003', N'Hoàn Thành'),
    ('TT004', N'Chưa Đặt')

--Chèn dữ liệu cho bảng GHE
insert into GHE
values ('GH01', 1),
    ('GH02', 1),
    ('GH03', 1),
    ('GH04', 1),
    ('GH05', 1),
    ('GH06', 2),
    ('GH07', 2),
    ('GH08', 2),
    ('GH09', 2),
    ('GH10', 2)

--Chèn dữ liệu cho bảng BUS
insert into BUS
values ('BS01', 'GH01', N'Xe Buýt Số 1', N'Xanh', '92L-00123', 30),
    ('BS02', 'GH02', N'Xe Buýt Số 2', N'Đỏ', '75A-00456', 40),
    ('BS03', 'GH03', N'Xe Buýt Số 3', N'Vàng', '43B-00678', 35),
    ('BS04', 'GH04', N'Xe Buýt Số 4', N'Trắng', '81C-00999', 45),
    ('BS05', 'GH05', N'Xe Buýt Số 5', N'Trắng', '43D-00001', 25),
    ('BS06', 'GH06', N'Xe Buýt Số 6', N'Đen', '43E-01010', 30),
    ('BS07', 'GH07', N'Xe Buýt Số 7', N'Đỏ', '43F-01111', 33),
    ('BS08', 'GH08', N'Xe Buýt Số 8', N'Xanh', '43G-01222', 37),
    ('BS09', 'GH09', N'Xe Buýt Số 9', N'Vàng', '43H-01333', 29),
    ('BS10', 'GH10', N'Xe Buýt Số 10', N'Đỏ', '24I-01444', 38)

--Chèn dữ liệu cho bảng NHAXEBUS
insert into NHAXEBUS
values ('NX001', 'BS01', 'PX001', N'Nhà Xe Phú Ninh Express'),
    ('NX002', 'BS02', 'PX003', N'Nhà Xe Huế Travel'),
    ('NX003', 'BS03', 'PX005', N'Nhà Xe Đà Nẵng Line'),
    ('NX004', 'BS04', 'PX006', N'Nhà Xe Gia Lai'),
    ('NX005', 'BS05', 'PX007', N'Nhà Xe Miền Trung'),
    ('NX006', 'BS06', 'PX008', N'Nhà Xe Tokyo Bus'),
    ('NX007', 'BS07', 'PX009', N'Nhà Xe La Tour'),
    ('NX008', 'BS08', 'PX010', N'Nhà Xe Paris Bus'),
    ('NX009', 'BS09', 'PX002', N'Nhà Xe Bến Xe Trung Tâm'),
    ('NX010', 'BS10', 'PX004', N'Nhà Xe Hoàn Kiếm')

--Chèn dữ liệu cho bảng KHACHHANG
insert into KHACHHANG
values ('KH001', N'Phạm Minh Huy', 'huy@gmail.com', 'ahihi', '0901000001'),
    ('KH002', N'Trần Văn Thọ Khang', 'khang@gmail.com', 'KhangTran', '0901000002'),
    ('KH003', N'Nguyễn Hữu Định', 'dinh@gmail.com', N'Định Đen', '0901000003'),
    ('KH004', N'Nguyễn Huỳnh', 'huynh@gmail.com', N'Sư Huynh', '0901000004'),
    ('KH005', N'Lê Duy Quốc', 'quoc@gmail.com', 'Quoc Le', '0901000005'),
    ('KH006', N'Đỗ Thị Xuân Sang', 'sang@gmail.com', '123', '0901000006'),
    ('KH007', N'Ngô Văn Gia Tự', 'tu@gmail.com', 'abc', '0901000007'),
    ('KH008', N'Tống Thị Quách Thúy', 'thuy@gmail.com', 'mk', '0901000008'),
    ('KH009', N'Võ Văn Quân', 'quan@gmail.com', 'quanig', '0901000009'),
    ('KH010', N'Nguyễn Duy Linh', 'linh@gmail.com', 'Linh linh', '0901000010')

--Chèn dữ liệu cho bảng CHUYENDI_BUS
insert into CHUYENDI_BUS
values ('CD001', 'NX001', '2025-04-15 08:00:00', '2025-04-15 20:00:00', 350000),
    ('CD002', 'NX001', '2025-04-16 07:00:00', '2025-04-16 09:30:00', 150000),
    ('CD003', 'NX002', '2025-04-17 09:00:00', '2025-04-17 13:30:00', 180000),
    ('CD004', 'NX003', '2025-04-18 10:00:00', '2025-04-18 22:00:00', 370000),
    ('CD005', 'NX004', '2025-04-19 06:00:00', '2025-04-19 18:00:00', 400000),
    ('CD006', 'NX005', '2025-04-20 08:00:00', '2025-04-20 12:00:00', 420000),
    ('CD007', 'NX006', '2025-04-21 09:00:00', '2025-04-21 19:00:00', 450000),
    ('CD008', 'NX007', '2025-04-22 07:30:00', '2025-04-22 15:00:00', 470000),
    ('CD009', 'NX008', '2025-04-23 10:00:00', '2025-04-23 21:00:00', 500000),
    ('CD010', 'NX009', '2025-04-24 11:00:00', '2025-04-24 17:00:00', 360000)

--Chèn dữ liệu cho bảng BOOKXE
insert into BOOKXE
values ('BK001', 'CD001', 'NX001', '2025-04-15 08:00:00', 'KH001', 'TT001', 'BS01'),
    ('BK002', 'CD002', 'NX001', '2025-04-16 07:00:00', 'KH002', 'TT003', 'BS01'),
    ('BK003', 'CD003', 'NX002', '2025-04-17 09:00:00', 'KH003', 'TT001', 'BS02'),
    ('BK004', 'CD004', 'NX003', '2025-04-18 10:00:00', 'KH004', 'TT004', 'BS03'),
    ('BK005', 'CD005', 'NX004', '2025-04-19 06:00:00', 'KH005', 'TT001', 'BS04'),
    ('BK006', 'CD006', 'NX005', '2025-04-20 08:00:00', 'KH006', 'TT001', 'BS05'),
    ('BK007', 'CD007', 'NX006', '2025-04-21 09:00:00', 'KH007', 'TT003', 'BS06'),
    ('BK008', 'CD008', 'NX007', '2025-04-22 07:30:00', 'KH008', 'TT002', 'BS07'),
    ('BK009', 'CD009', 'NX008', '2025-04-23 10:00:00', 'KH009', 'TT001', 'BS08'),
    ('BK010', 'CD010', 'NX009', '2025-04-24 11:00:00', 'KH010', 'TT003', 'BS09')
