<?php
	require"dbConnect.php";

	$query = "SELECT * FROM `user` ";
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