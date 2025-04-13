Use master
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'QuanLyVeXeBuyt')
BEGIN
    ALTER DATABASE QuanLyVeXeBuyt SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QuanLyVeXeBuyt;
END;
--Tạo database
create database QuanLyVeXeBuyt
go
use QuanLyVeXeBuyt
go
create table QuocGia(
	ID_QuocGia char(3) primary key,
	TenQuocGia nvarchar(30) Not null,
)
create table Tinh(
	ID_Tinh char(3) primary key,
	quocGiaNo char(3) not null foreign key references QuocGia(ID_QuocGia)
	on update cascade
	on delete cascade,
	tenTinh nvarchar(30) not null,
)
create table QuanHuyen(
	ID_QuanHuyen char(3) primary key,
	tinhNo char(3) not null foreign key references Tinh(ID_Tinh)
	on update cascade
	on delete cascade,
	tenQuanHuyen nvarchar(30) not null,
)
create table PhuongXa(
	ID_PhuongXa char(3) primary key,
	quanHuyenNo char(3) not null foreign key references QuanHuyen(ID_QuanHuyen)
	on update cascade
	on delete cascade,
	tenPhuongXa nvarchar(30) not null,
)
create table Tram(
	ID_Tram char(3) primary key,
	phuongXaNo char(3) not null foreign key references PhuongXa(ID_PhuongXa)
	on update cascade
	on delete cascade,
	tenTram nvarchar(50) not null,
)
create table ChuyenDi(
	ID_ChuyenDi char(3) primary key,
	tramNo char(3) not null foreign key references Tram(ID_Tram)
	on delete cascade
	on update cascade,
	tenChuyenDi nvarchar(30) not null,
)
create table TinhTrangBook(
	ID_TinhTrangBook char(3) primary key,
	tinhTrang nvarchar(10) not null,
)
create table Ghe(
	ID_Ghe char(3) primary key,
	loaiGhe int not null,
)
create table Bus(
	ID_Bus char(3) primary key,
	gheNo char(3) not null foreign key references Ghe(ID_Ghe)
	on delete cascade 
	on update cascade,
	tenXeBus nvarchar(30) not null,
	mauXe nvarchar(10) not null,
	bienSo varchar(10) not null,
	soChoNgoi int not null,
)
create table NhaXeBus(
	ID_NhaXeBus char(3) primary key,
	busNo char(3) not null foreign key references Bus(ID_Bus)
	on update cascade 
	on delete cascade,
	diaChiNhaXe char(3) not null foreign key references PhuongXa(ID_PhuongXa)
	on update cascade
	on delete cascade,
	tenNhaXe nvarchar(30) not null,
)
create table KhachHang(
	ID_KhachHang char(3) primary key,
	tenKhachHang nvarchar(30) not null,
	email char(30) not null,
	matKhau char(30) not null,
	soDienThoai char(10) not null,
)
create table ChuyenDi_Bus(
	chuyenDiNo char(3) not null foreign key references ChuyenDi(ID_ChuyenDi)	
	on delete cascade
	on update cascade,
	nhaXeBusNo char(3) not null foreign key references NhaXeBus(ID_NhaXeBus)
	on delete no action,
	thoiGianXuatPhat datetime not null,
	primary key (chuyenDiNo, nhaXeBusNo, thoiGianXuatPhat),
	thoaGianDen datetime not null,
	tienXe money not null,
)
create table  BookXe(
	ID_BookXe char(3) primary key,
	chuyenDiNo char(3) not null,
	nhaXeBusNo char(3) not null,
	thoiGianXuatPhat Datetime not null,
	foreign key (chuyenDiNo, nhaXeBusNo, thoiGianXuatPhat)
		references ChuyenDi_Bus(chuyenDiNo, nhaXeBusNo, thoiGianXuatPhat)
		on update cascade
		on delete cascade,
	khachHangNo char(3) not null foreign key references KhachHang(ID_KhachHang),
	tinhTrangBookNo char(3) not null foreign key references TinhTrangBook(ID_TinhTrangBook)
	on update cascade
	on delete cascade,
	busNo char(3) not null foreign key references Bus(ID_Bus)
	on update cascade
	on delete cascade,
)
--===============================================ràng buộc ở đây=================================================
alter table KhachHang
	add
		constraint UQ_KhachHang_Email 
			unique (email),
		constraint CK_KhachHang_Email 
			check  (email like '[A-Za-z]%@gmail.com'),
		constraint UQ_KhachHang_SDT 
			unique (soDienThoai),
		constraint CK_KhachHang_soDienThoai
			check (soDienThoai like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
alter table ChuyenDi_Bus
	add 
		constraint CK_ChuyenDiBus_tienXe
			check (tienXe >= 0)		