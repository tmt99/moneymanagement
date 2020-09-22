<?php
require"dbConnect.php";

	require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$makhoanthu=$_POST['MaKhoanThu'];
	$tenloaithu =$_POST['TenLoaiThu'];
	$sotienthu =$_POST['SoTienThu'];
	
	$a=$_POST['NgayThu'];
	$ngay =strtr($a,'/','-');
	$ngaythu = date("Y-m-d", strtotime($ngay));

	$query="UPDATE `khoanthu` SET `tenloaithu` = '$tenloaithu', `sotienthu` = '$sotienthu', `ngaythu` = '$ngaythu' WHERE `khoanthu`.`tendangnhap`='$tendangnhap' AND `khoanthu`.`makhoanthu` ='$makhoanthu'";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}

?>