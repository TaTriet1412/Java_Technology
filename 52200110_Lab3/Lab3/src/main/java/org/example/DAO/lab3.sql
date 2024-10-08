-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 08, 2024 lúc 08:15 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `lab3`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `manufacture`
--

CREATE TABLE `manufacture` (
  `id` bigint(20) NOT NULL,
  `name` text NOT NULL CHECK (octet_length(`name`) >= 3 and octet_length(`name`) <= 128),
  `location` text NOT NULL,
  `employee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `manufacture`
--

INSERT INTO `manufacture` (`id`, `name`, `location`, `employee`) VALUES
(1, 'Sun Factory', 'United State', 34),
(3, 'Moon Factory', 'Korea', 68),
(6, 'Testla', 'US', 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phone`
--

CREATE TABLE `phone` (
  `id` bigint(20) NOT NULL,
  `name` text NOT NULL CHECK (octet_length(`name`) >= 3 and octet_length(`name`) <= 128),
  `price` int(11) NOT NULL,
  `color` text NOT NULL,
  `country` text NOT NULL,
  `quantity` int(11) NOT NULL,
  `manuId` bigint(20) NOT NULL
) ;

--
-- Đang đổ dữ liệu cho bảng `phone`
--

INSERT INTO `phone` (`id`, `name`, `price`, `color`, `country`, `quantity`, `manuId`) VALUES
(1, 'Triet', 12, 'Triet', 'Triet', 12, 1),
(4, 'Iphone 17', 20000000, 'Red', 'United State', 20, 1),
(5, 'Xiaomi 11', 20000, 'Pink', 'Triet', 200, 1),
(6, 'Oppo 13', 55000000, 'Purple', 'United Stated', 200, 3),
(7, 'Redmi 20', 20000000, 'Pink', 'VietNam', 100, 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `manufacture`
--
ALTER TABLE `manufacture`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`) USING HASH;

--
-- Chỉ mục cho bảng `phone`
--
ALTER TABLE `phone`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`) USING HASH,
  ADD KEY `manuId` (`manuId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `manufacture`
--
ALTER TABLE `manufacture`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phone`
--
ALTER TABLE `phone`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`manuId`) REFERENCES `manufacture` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
