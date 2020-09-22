<?php
require"dbConnect.php";
	
	$tendangnhap = $_POST['TenDangNhap'];
	//$tendangnhap ="a";
	$query = "SELECT * FROM `loaichi` WHERE loaichi.tendangnhap='".$tendangnhap."' ";
	$data = mysqli_query($connect,$query);
	
	class getdataloaichi{
		function getdataloaichi($maloaichi,$tenloaichi){
			$this->MaLoaiChi=$maloaichi;
			$this->TenLoaiChi=$tenloaichi;
		}

	}

	$arraydataloaichi = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydataloaichi, new getdataloaichi($row['maloaichi'],$row['tenloaichi']));
	}

	
	echo json_encode($arraydataloaichi);


 ?>