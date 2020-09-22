<?php
	require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$matkhau =$_POST['MatKhauCu'];
	$matkhaumoi = $_POST['MatKhauMoi'];


	$query="UPDATE `user` SET `matkhau` = '$matkhaumoi' WHERE `user`.`tendangnhap` = '$tendangnhap' AND `user`.`matkhau`='$matkhau' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}
	
 ?>