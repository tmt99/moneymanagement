<?php
	require"dbConnect.php";
	$tendangnhap =$_POST['TenDangNhap'];
	$hoten = $_POST['HoTen'];
	$diachi = $_POST['DiaChi'];

	$query = "SELECT * FROM `user` WHERE tendangnhap='$tendangnhap' AND hoten='$hoten' AND diachi='$diachi' ";
	$data = mysqli_query($connect,$query);

	class getdatauser{
		function getdatauser($tendangnhap,$matkhau){
			$this->TenDangNhap=$tendangnhap;
			$this->MatKhau = $matkhau;
		}

	}

	$arraydatauser = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydatauser, new getdatauser($row['tendangnhap'],$row['matkhau']));
	}


	echo json_encode($arraydatauser);

 ?>