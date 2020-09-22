<?php
  //       $dinh_dang_cu = "30-3-2019";  
		// $dinh_dang_moi = date("Y-m-d", strtotime($dinh_dang_cu));  
		// echo "Định dạng cũ: " .$dinh_dang_cu."<br>";
		// echo "Định dạng mới: " .$dinh_dang_moi."<br>";

 	require"dbConnect.php"; 
    $tendangnhap = $_POST['TenDangNhap'];
    //$tendangnhap ="a";
	$tongthu=0;
	$tongchi=0;
	//$fisrt_day_of_moth = date('Y-m-01');
	$query1 = "SELECT sotienchi FROM khoanchi WHERE ngaychi =curdate()   AND khoanchi.tendangnhap='".$tendangnhap."'";
	$data1 = mysqli_query($connect,$query1);

	$query = "SELECT sotienthu FROM khoanthu WHERE ngaythu =curdate()  AND khoanthu.tendangnhap='".$tendangnhap."'";
	$data = mysqli_query($connect,$query);
	class getdatauser{
		function getdatauser($sodu){
			$this->SoDu= $sodu;

		}

	}

	$arraydatauser = array();
	while ($row1 = mysqli_fetch_assoc($data1)) {
		$tongchi=$tongchi+$row1['sotienchi'];
	}
	while ($row = mysqli_fetch_assoc($data)) {
		$tongthu=$tongthu+$row['sotienthu'];
	}
    $sodu=$tongthu-$tongchi;
    //echo $tongchi;
  
   // echo $tongthu;
	array_push($arraydatauser,new getdatauser($sodu));

	echo json_encode($arraydatauser);
?>