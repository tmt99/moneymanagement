<?php
	require"dbConnect.php";

	$tendangnhap = $_POST['TenDangNhap'];
	$tenloaichi = $_POST['TenLoaiChi'];

	$query="INSERT INTO `loaichi` (`maloaichi`, `tenloaichi`, `tendangnhap`) VALUES (NULL, '".$tenloaichi."', '".$tendangnhap."');";

	$query1 = "SELECT tenloaichi FROM loaichi WHERE tendangnhap='$tendangnhap' AND tenloaichi='$tenloaichi'";
	$result =mysqli_query($connect,$query1);

	// if(mysqli_query($connect,$query)){
	// 		echo "true";
	// 	}else{
	// 		echo "false";
	// 	}
	
	if(mysqli_fetch_row($result)>0){
		echo "false";
		
	}else{
		if (mysqli_query($connect,$query)) {
			echo "true";
		}
	}


?>