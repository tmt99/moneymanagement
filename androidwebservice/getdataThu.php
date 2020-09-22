<?php
	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
	$query = "SELECT makhoanthu,tendangnhap,tenloaithu,sotienthu,ngaythu FROM khoanthu WHERE khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu ";
	//$query = "SELECT makhoanthu,tendangnhap,tenloaithu,sotienthu,ngaythu FROM khoanthu k, loaichi l WHERE k.maloaichi=l.maloaichi and khoanthu.tendangnhap='".$tendangnhap."'ORDER BY ngaythu ";
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
		$ngaythu = date("d-m-Y", strtotime($row['ngaythu']));
		array_push($arraydatauser,new getdatauser($row['makhoanthu'],$row['tenloaithu'],$row['sotienthu'],$ngaythu));
	}

	
	echo json_encode($arraydatauser);

 ?>