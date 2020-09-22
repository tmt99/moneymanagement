<?php
	require"dbConnect.php";
	//$tendangnhap = "a";
	$tendangnhap = $_POST['TenDangNhap'];
	$ngaya =strtr($_POST['Ngaybd'],'/','-');
	$ngayb =strtr($_POST['Ngaykt'],'/','-');
	$ngaybt = date("Y-m-d", strtotime($ngaya));
	$ngaykt = date("Y-m-d", strtotime($ngayb));
	$query = "SELECT * FROM khoanthu WHERE khoanthu.ngaythu BETWEEN CAST('$ngaybt' AS DATE) AND CAST('$ngaykt' AS DATE) AND khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu DESC ";
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