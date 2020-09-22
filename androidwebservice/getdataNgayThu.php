<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$query = "SELECT * FROM khoanthu WHERE  ngaythu >= CURDATE() AND ngaythu < CURDATE() + INTERVAL 1 DAY AND khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu DESC ";
	$data = mysqli_query($connect,$query);
	
	class getdatauser{
		function getdatauser($makhoanthu,$tenloaithu,$sotienthu,$ngaythu){
			$this->MaKhoanThu=$makhoanthu;
			$this->TenLoaiThu= $tenloaithu;
			$this->SoTienThu= $sotienthu;
			$this->NgayThu =$ngaythu;

		}

	}

	$arraydatauser = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydatauser,new getdatauser($row['makhoanthu'],$row['tenloaithu'],$row['sotienthu'],$row['ngaythu']));
	}

	
	echo json_encode($arraydatauser);

 ?>