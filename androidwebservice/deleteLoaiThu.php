<?php
require"dbConnect.php";

	$tenloaithu =$_POST['TenLoaiThu'];
	$tendangnhap =$_POST['TenDangNhap'];

	$query="DELETE FROM `loaithu` WHERE loaithu.tendangnhap='$tendangnhap' AND `loaithu`.`tenloaithu` = '".$tenloaithu."'";
	

	if (mysqli_query($connect,$query)) {
				echo "true";
	}else{
		echo "false";
	}
		

?>