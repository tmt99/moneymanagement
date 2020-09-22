<?php
	
	require"dbConnect.php";
	$tendangnhap =$_POST['TenDangNhap'];
	$query = "SELECT makhoanchi,tendangnhap,tenloaichi,sotienchi,ngaychi FROM khoanchi WHERE  khoanchi.tendangnhap='".$tendangnhap."'ORDER BY ngaychi ";
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
		$ngaychii = date("d-m-Y", strtotime($row['ngaychi']));
		array_push($arraydatauser,new getdatauser($row['makhoanchi'],$row['tenloaichi'],$row['sotienchi'],$ngaychii));
	}

	
	echo json_encode($arraydatauser);

 ?>