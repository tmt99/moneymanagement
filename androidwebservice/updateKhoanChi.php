<?php
require"dbConnect.php";

	require"dbConnect.php";

	$tendangnhap =$_POST['TenDangNhap'];
	$makhoanchi=$_POST['MaKhoanChi'];
	$tenloaichi =$_POST['TenLoaiChi'];
	$sotienchi =$_POST['SoTienChi'];
	
	$a=$_POST['NgayChi'];
	$ngay =strtr($a,'/','-');
	$ngaychi = date("Y-m-d", strtotime($ngay));

	$query="UPDATE `khoanchi` SET `tenloaichi` = '$tenloaichi', `sotienchi` = '$sotienchi', `ngaychi` = '$ngaychi' WHERE `khoanchi`.`tendangnhap`='$tendangnhap' AND `khoanchi`.`makhoanchi` ='$makhoanchi'";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}

?>