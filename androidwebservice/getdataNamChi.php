<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$fisrt_day_of_year = date('Y-01-01');
	$query = "SELECT * FROM khoanchi WHERE ngaychi >='$fisrt_day_of_year' AND ngaychi < '$fisrt_day_of_year' + INTERVAL 1 YEAR AND khoanchi.tendangnhap='".$tendangnhap."'ORDER BY ngaychi DESC";

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