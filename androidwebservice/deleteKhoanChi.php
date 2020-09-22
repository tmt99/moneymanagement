<?php
require"dbConnect.php";

	$makhoanchi =$_POST['MaKhoanChi'];
	$tendangnhap =$_POST['TenDangNhap'];

	$query="DELETE FROM `khoanchi` WHERE `khoanchi`.`tendangnhap`='$tendangnhap' AND `khoanchi`.`makhoanchi` = '".$makhoanchi."'";
	

	if (mysqli_query($connect,$query)) {
				echo "true";
	}else{
		echo "false";
	}
		

?>