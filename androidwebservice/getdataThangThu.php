<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$fisrt_day_of_moth = date('Y-m-01');
	$query = "SELECT * FROM khoanthu WHERE ngaythu >='$fisrt_day_of_moth' AND ngaythu < '$fisrt_day_of_moth' + INTERVAL 1 MONTH AND khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu DESC";


	// $query = "SELECT * FROM khoanthu WHERE  ngaythu >= CURDATE() AND ngaythu < CURDATE() + INTERVAL 1 DAY AND khoanthu.tendangnhap='".$tendangnhap."' ";
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