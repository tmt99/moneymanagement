<?php
	require"dbConnect.php";
	
	$tendangnhap =$_POST['TenDangNhap'];
	$matkhau =$_POST['MatKhau'];
	$hoten = $_POST['HoTen'];
	$diachi = $_POST['DiaChi'];

	$query="INSERT INTO `user` (`tendangnhap`, `matkhau`, `hoten`, `diachi`) VALUES ('$tendangnhap', '$matkhau', '$hoten', '$diachi')";

	$query1 = "SELECT tendangnhap FROM user WHERE tendangnhap='".$tendangnhap."'";
	$result =mysqli_query($connect,$query1);

	// if(mysqli_query($connect,$query)){
	// 		echo "true";
	// 	}else{
	// 		echo "false";
	// 	}
	
	if(mysqli_fetch_row($result)>0){
		echo "false";
		
	}else{
		if (mysqli_query($connect,$query)) {
			echo "true";
		}
	}
	
 ?>