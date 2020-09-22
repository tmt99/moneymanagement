<?php
require"dbConnect.php";

	require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$maloaichi=$_POST['MaLoaiChi'];
	$tenloaichi =$_POST['TenLoaiChi'];

	$query="UPDATE `loaichi` SET `tenloaichi` = '$tenloaichi' WHERE `loaichi`.`maloaichi` = '$maloaichi' AND `loaichi`.`tendangnhap`='$tendangnhap' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}

?>