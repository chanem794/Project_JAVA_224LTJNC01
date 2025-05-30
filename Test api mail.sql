use master
if exists (select name from sys.databases where name = 'QuanLyVeXeBuyt')
begin
    alter database QuanLyVeXeBuyt set single_user with rollback immediate;
    drop database QuanLyVeXeBuyt;
end;

CREATE DATABASE QuanLyVeXeBuyt;
GO

USE QuanLyVeXeBuyt;
GO

CREATE TABLE users (
    email VARCHAR(100) PRIMARY KEY,
    full_name NVARCHAR(100),
    birth_date DATE,
    otp_code VARCHAR(6),
    otp_created_at DATETIME
);
GO

INSERT INTO users (email, full_name, birth_date) VALUES
('dinhknd3@gmail.com', N'Nguyễn Văn Định', '1990-01-01'),
('chanem794@gmail.com', N'Trần Thị Bình', '1995-05-15');
GO