<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$fisrt_day_of_year = date('Y-01-01');
	$query = "SELECT * FROM khoanthu WHERE ngaythu >='$fisrt_day_of_year' AND ngaythu < '$fisrt_day_of_year' + INTERVAL 1 YEAR AND khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu DESC";

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