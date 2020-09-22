<?php
require"dbConnect.php";

	$makhoanthu =$_POST['MaKhoanThu'];
	$tendangnhap =$_POST['TenDangNhap'];

	$query="DELETE FROM `khoanthu` WHERE `khoanthu`.`tendangnhap`='$tendangnhap' AND `khoanthu`.`makhoanthu` = '".$makhoanthu."'";
	

	if (mysqli_query($connect,$query)) {
				echo "true";
	}else{
		echo "false";
	}
		

?>