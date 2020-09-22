<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$fisrt_day_of_moth = date('Y-m-01');
	$query = "SELECT * FROM khoanchi WHERE ngaychi >='$fisrt_day_of_moth' AND ngaychi < '$fisrt_day_of_moth' + INTERVAL 1 MONTH AND khoanchi.tendangnhap='".$tendangnhap."'ORDER BY ngaychi DESC";


	// $query = "SELECT * FROM khoanthu WHERE  ngaythu >= CURDATE() AND ngaythu < CURDATE() + INTERVAL 1 DAY AND khoanthu.tendangnhap='".$tendangnhap."' ";
	$data = mysqli_query($connect,$query);
	
	class getdatauser{
		function getdatauser($makhoanchi,$tenloaichi,$sotienchi,$ngaychi){
			$this->MaKhoanChi=$makhoanchi;
			$this->TenLoaiChi= $tenloaichi;
			$this->SoTienChi= $sotienchi;
			$this->NgayChi =$ngaychi;

		}

	}

	$arraydatauser = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraydatauser,new getdatauser($row['makhoanchi'],$row['tenloaichi'],$row['sotienchi'],$row['ngaychi']));
	}

	
	echo json_encode($arraydatauser);

 ?>