-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 22, 2020 lúc 05:08 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qltc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoanchi`
--

CREATE TABLE `khoanchi` (
  `makhoanchi` int(11) NOT NULL,
  `tendangnhap` varchar(100) DEFAULT NULL,
  `maloaichi` int(11) DEFAULT NULL,
  `tenloaichi` varchar(255) DEFAULT NULL,
  `sotienchi` double NOT NULL,
  `ngaychi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khoanchi`
--

INSERT INTO `khoanchi` (`makhoanchi`, `tendangnhap`, `maloaichi`, `tenloaichi`, `sotienchi`, `ngaychi`) VALUES
(68, 'a', 2, 'Giải trí', 129, '2020-07-03'),
(69, 'a', NULL, 'Ăn uống', 130, '2020-07-03'),
(72, 'a', NULL, 'Ăn uống', 133, '2020-07-03'),
(73, 'a', NULL, 'Ăn uống', 134, '2020-07-03'),
(74, 'a', NULL, 'Học Tập', 1235, '2020-07-03'),
(75, 'a', NULL, 'Học Tập', 126, '2020-07-03'),
(76, 'a', NULL, 'Ăn uống', 127, '2020-07-03'),
(77, 'a', NULL, 'Ăn uống', 28, '2020-07-03'),
(78, 'a', NULL, 'Ăn uống', 29, '2020-07-03'),
(79, 'a', NULL, 'Ăn uống', 31, '2020-07-03'),
(80, 'a', NULL, 'Ăn uống', 32, '2020-07-03'),
(81, 'a', NULL, 'Học Tập', 25, '2020-07-03'),
(82, 'a', NULL, 'Ăn uống', 13000, '2020-07-04'),
(86, 'a', NULL, 'Ăn uống', 14, '2020-07-04'),
(87, 'a', NULL, 'Ăn uống', 15, '2020-07-04'),
(88, 'a', NULL, 'Ăn uống', 16, '2020-07-04'),
(89, 'a', NULL, 'Ăn uống', 17, '2020-07-04'),
(90, 'a', NULL, 'Ăn uống', 10, '2020-07-04'),
(91, 'a', NULL, 'Ăn uống', 11, '2020-07-04'),
(92, 'a', NULL, 'Ăn uống', 12, '2020-07-04'),
(93, 'a', NULL, 'Ăn uống', 13, '2020-07-04'),
(94, 'a', NULL, 'Ăn uống', 14, '2020-07-04'),
(95, 'a', NULL, 'Ăn uống', 15, '2020-07-04'),
(97, 'a', NULL, 'Ăn uống', 1711, '2020-07-04'),
(98, 'a', NULL, 'Ăn uống', 16, '2020-07-05'),
(99, 'a', NULL, 'Ăn uống', 17, '2020-07-05'),
(100, 'a', NULL, 'Ăn uống', 18, '2020-07-05'),
(101, 'a', NULL, 'Ăn uống', 19, '2020-07-05'),
(102, 'a', NULL, 'Ăn uống', 20, '2020-07-07'),
(104, 'a', NULL, 'Ăn uống', 22, '2020-07-02'),
(105, 'a', NULL, 'Ăn uống', 100, '2020-07-07'),
(106, 'a', NULL, 'Ăn uống', 1234, '2020-07-07'),
(107, 'a', NULL, 'Ăn uống', 123, '2020-07-08'),
(108, 'a', NULL, 'Ăn uống', 124, '2020-07-08'),
(110, 'a', NULL, 'Ăn uống', 1000000, '2020-07-08');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoanthu`
--

CREATE TABLE `khoanthu` (
  `makhoanthu` int(11) NOT NULL,
  `tendangnhap` varchar(100) DEFAULT NULL,
  `tenloaithu` varchar(200) DEFAULT NULL,
  `sotienthu` double NOT NULL,
  `ngaythu` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khoanthu`
--

INSERT INTO `khoanthu` (`makhoanthu`, `tendangnhap`, `tenloaithu`, `sotienthu`, `ngaythu`) VALUES
(15, 'a', '0', 100000, '2020-06-28'),
(16, 'a', '0', 10000, '2020-06-24'),
(17, 'a', '0', 53000, '2020-07-02'),
(21, 'a', 'Lương', 1122, '2020-07-03'),
(22, 'a', 'Gia đình 1', 1323, '2020-07-03'),
(23, 'a', 'Gia đình 1', 13, '2020-07-04'),
(24, 'a', 'Gia đình', 1450, '2020-07-04'),
(26, 'a', 'Gia đình', 1461, '2020-07-04'),
(28, 'a', 'Gia đình', 212, '2020-07-07'),
(29, 'a', 'Gia đình', 100, '2020-07-08'),
(30, 'a', 'Gia đình', 1000000, '2020-07-08'),
(31, 'a', 'Gia đình', 250, '2020-07-09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaichi`
--

CREATE TABLE `loaichi` (
  `maloaichi` int(11) NOT NULL,
  `tenloaichi` varchar(200) NOT NULL,
  `tendangnhap` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaichi`
--

INSERT INTO `loaichi` (`maloaichi`, `tenloaichi`, `tendangnhap`) VALUES
(2, 'Ăn uống', 'a'),
(26, 'ten', 'v'),
(27, 'cho cho', 'hanh'),
(29, 'Học Tập', 'a'),
(30, 'an uong', 'minh'),
(31, '0111', 'minh'),
(32, 'd', 'minh'),
(33, 'Sức khỏe', 'a'),
(35, 'Giải trí', 'a'),
(36, 'Khác', 'a');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaithu`
--

CREATE TABLE `loaithu` (
  `maloaithu` int(11) NOT NULL,
  `tenloaithu` varchar(200) NOT NULL,
  `tendangnhap` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaithu`
--

INSERT INTO `loaithu` (`maloaithu`, `tenloaithu`, `tendangnhap`) VALUES
(1, 'Gia đình', 'a'),
(2, 'Lương', 'a'),
(7, 'nn', 'nv'),
(8, 'an uong', 'minh'),
(14, 'gia dinh', 'minh'),
(19, 'Thưởng', 'a'),
(29, 'Khác', 'a'),
(30, 'Lãi tiết kiệm', 'a');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `tendangnhap` varchar(100) NOT NULL,
  `matkhau` varchar(50) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `diachi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`tendangnhap`, `matkhau`, `hoten`, `diachi`) VALUES
('a', '2', 'nguyen  van a', 'nguyena@gmail.com'),
('a1', '1', 'Nguyen van b', 'nguyenb@gmail.com'),
('a2', '1', 'nguyen van a2', 'nguyenab@gmail.com'),
('add', '1', 'dhhfhsfv', 'dhhfhsfv'),
('admin', 'admin', 'admin', 'admin'),
('hanh', '1', 'nguyen van a', 'nguyen van a'),
('minh', '1', 'quang minh', 'quang minh'),
('nv', '2', 'nguyen van v', 'nguyen van v'),
('v', '2', 'nguyen van a', 'nguyen van a');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `khoanchi`
--
ALTER TABLE `khoanchi`
  ADD PRIMARY KEY (`makhoanchi`),
  ADD KEY `kc` (`tendangnhap`);

--
-- Chỉ mục cho bảng `khoanthu`
--
ALTER TABLE `khoanthu`
  ADD PRIMARY KEY (`makhoanthu`),
  ADD KEY `kt1` (`tendangnhap`);

--
-- Chỉ mục cho bảng `loaichi`
--
ALTER TABLE `loaichi`
  ADD PRIMARY KEY (`maloaichi`),
  ADD KEY `lc` (`tendangnhap`);

--
-- Chỉ mục cho bảng `loaithu`
--
ALTER TABLE `loaithu`
  ADD PRIMARY KEY (`maloaithu`),
  ADD KEY `lt` (`tendangnhap`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`tendangnhap`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `khoanchi`
--
ALTER TABLE `khoanchi`
  MODIFY `makhoanchi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT cho bảng `khoanthu`
--
ALTER TABLE `khoanthu`
  MODIFY `makhoanthu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `loaichi`
--
ALTER TABLE `loaichi`
  MODIFY `maloaichi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `loaithu`
--
ALTER TABLE `loaithu`
  MODIFY `maloaithu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `khoanchi`
--
ALTER TABLE `khoanchi`
  ADD CONSTRAINT `kc` FOREIGN KEY (`tendangnhap`) REFERENCES `user` (`tendangnhap`),
  ADD CONSTRAINT `mlc` FOREIGN KEY (`maloaichi`) REFERENCES `loaichi` (`maloaichi`);

--
-- Các ràng buộc cho bảng `khoanthu`
--
ALTER TABLE `khoanthu`
  ADD CONSTRAINT `kt1` FOREIGN KEY (`tendangnhap`) REFERENCES `user` (`tendangnhap`);

--
-- Các ràng buộc cho bảng `loaichi`
--
ALTER TABLE `loaichi`
  ADD CONSTRAINT `lc` FOREIGN KEY (`tendangnhap`) REFERENCES `user` (`tendangnhap`);

--
-- Các ràng buộc cho bảng `loaithu`
--
ALTER TABLE `loaithu`
  ADD CONSTRAINT `lt` FOREIGN KEY (`tendangnhap`) REFERENCES `user` (`tendangnhap`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
