<?php
require"dbConnect.php";
	
	$tendangnhap =$_POST['TenDangNhap'];

	$query = "SELECT * FROM `loaithu` WHERE loaithu.tendangnhap='".$tendangnhap."' ";
	$data = mysqli_query($connect,$query);
	
	class getdataloaithu{
		function getdataloaithu($maloaithu,$tenloaithu){
			$this->MaLoaiThu=$maloaithu;
			$this->TenLoaiThu=$tenloaithu;
		}

	}

	$arraydataloaithu = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydataloaithu, new getdataloaithu($row['maloaithu'],$row['tenloaithu']));
	}

	
	echo json_encode($arraydataloaithu);


 ?>