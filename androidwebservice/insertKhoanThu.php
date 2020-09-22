<?php
require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$tenloaithu =$_POST['TenLoaiThu'];
	$sotienthu =$_POST['SoTienThu'];
	$a=$_POST['NgayThu'];
	$ngay =strtr($a,'/','-');
	$ngaythu = date("Y-m-d", strtotime($ngay));


	$query="INSERT INTO `khoanthu` (`makhoanthu`,`tendangnhap`, `tenloaithu` ,`sotienthu`, `ngaythu`) VALUES (NULL,'".$tendangnhap."','".$tenloaithu."','".$sotienthu."','".$ngaythu."')";

if (mysqli_query($connect,$query)) {
			echo "true";
}else{
	echo "false";
}
	



?>