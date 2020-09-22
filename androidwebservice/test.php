<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	
	$ngaya =strtr($_POST['Ngaybt'],'/','-');
	$ngayb =strtr($_POST['Ngaykt'],'/','-');
	$ngaybt = date("Y-m-d", strtotime($ngaya));
	$ngaykt = date("Y-m-d", strtotime($ngayb));

	
	$query = "SELECT * FROM khoanchi WHERE khoanchi.ngaychi BETWEEN CAST('$ngaybt' AS DATE) AND CAST('$ngaykt' AS DATE) AND khoanchi.tendangnhap='".$tendangnhap."' ";


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