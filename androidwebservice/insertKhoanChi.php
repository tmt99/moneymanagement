<?php
require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	//$maloaichi= $_POST['MaLoaiChi'];
	$tenloaichi =$_POST['TenLoaiChi'];
	$sotienchi =$_POST['SoTienChi'];
	$a=$_POST['NgayChi'];
	$ngay =strtr($a,'/','-');
	$ngaychi = date("Y-m-d", strtotime($ngay));
	
	//$maloaichi =$_POST['MaLoaiChi'];//them


	$query="INSERT INTO `khoanchi` (`makhoanchi`,`tendangnhap`, `tenloaichi` ,`sotienchi`, `ngaychi`) VALUES (NULL,'".$tendangnhap."','".$tenloaichi."','".$sotienchi."','".$ngaychi."')";
	//$query="INSERT INTO `khoanchi` (`makhoanchi`,`tendangnhap`,`maloaichi`, `tenloaichi` ,`sotienchi`, `ngaychi`) VALUES (NULL,'".$tendangnhap."','".$maloaichi."',null,'".$sotienchi."','".$ngaychi."')";
	
	if (mysqli_query($connect,$query)) {
				echo "true";
	}else{
		echo "false";
	}
		



?>