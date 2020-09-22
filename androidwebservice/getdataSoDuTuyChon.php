<?php

 	require"dbConnect.php";
	$tendangnhap = $_POST['TenDangNhap'];
    $ngaya =strtr($_POST['Ngaybd'],'/','-');
    $ngayb =strtr($_POST['Ngaykt'],'/','-');
    // $tendangnhap ="a";
    // $ngaya ="01/07/2020";
	// $ngayb ="07/07/2020";
	$ngaybt = date("Y-m-d", strtotime($ngaya));
	$ngaykt = date("Y-m-d", strtotime($ngayb));
	$tongthu=0;
	$tongchi=0;

	$query1 = "SELECT sotienchi FROM khoanchi WHERE khoanchi.ngaychi BETWEEN CAST('$ngaybt' AS DATE) AND CAST('$ngaykt' AS DATE) AND khoanchi.tendangnhap='".$tendangnhap."' ";
	$data1 = mysqli_query($connect,$query1);
	//echo $data1;
    
	$query = "SELECT sotienthu FROM khoanthu WHERE khoanthu.ngaythu BETWEEN CAST('$ngaybt' AS DATE) AND CAST('$ngaykt' AS DATE) AND khoanthu.tendangnhap='".$tendangnhap."'";
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
	array_push($arraydatauser,new getdatauser($sodu));

	echo json_encode($arraydatauser);
       ?>