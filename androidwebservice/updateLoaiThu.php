<?php
require"dbConnect.php";

	require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$maloaithu=$_POST['MaLoaiThu'];
	$tenloaithu =$_POST['TenLoaiThu'];

	$query="UPDATE `loaithu` SET `tenloaithu` = '$tenloaithu' WHERE `loaithu`.`maloaithu` = '$maloaithu' AND `loaithu`.`tendangnhap`='$tendangnhap' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}

?>