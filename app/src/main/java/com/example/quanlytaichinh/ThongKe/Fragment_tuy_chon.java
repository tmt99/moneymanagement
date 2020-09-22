package com.example.quanlytaichinh.ThongKe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlytaichinh.ChartActivity;
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Fragment_tuy_chon extends Fragment {
    String url="http://192.168.1.13/androidwebservice/getdataNgayChiTuyChon.php";
    String urls ="http://192.168.1.13/androidwebservice/getdataNgayThuTuyChon.php";
    String urlsd ="http://192.168.1.13/androidwebservice/getdataSoDuTuyChon.php";
    public String TenDangNhap;
    private TextView tvTongChi,tvTongThu, tvSoDu;
    public EditText edNgaybd,edNgaykt;
    public Button btXem;
    public ImageButton btChart;
    public int tongchi,tongthu,sodu ;
    ArrayList<ttChi> arrayChi;
    ArrayList<ttThu> arrayThu;
    TTThuAdapter thuadapter;
    TTChiAdapter chiadapter;

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tuy_chon,null);

        final Intent intent = getActivity().getIntent();
        TenDangNhap = intent.getStringExtra("TenDangNhap");
        //Toast.makeText(getActivity(),"Ten dang nhap :"+TenDangNhap,Toast.LENGTH_LONG).show();
//        Log.e("AAAAAA", TenDangNhap);

        ListView lvChi = (ListView) view.findViewById(R.id.lv1);
        ListView lvThu = (ListView) view.findViewById(R.id.lv2);
        final EditText edNgaybd = (EditText) view.findViewById(R.id.edNgaybd);
        final EditText edNgaykt = (EditText) view.findViewById(R.id.edNgaykt);
        btXem = (Button) view.findViewById(R.id.btXem);
        btChart= (ImageButton) view.findViewById(R.id.btChart);

        arrayChi = new ArrayList<>();
        arrayThu = new ArrayList<>();
        chiadapter = new TTChiAdapter(getActivity(),R.layout.custom_lv_thong_ke,arrayChi);
        thuadapter = new TTThuAdapter(getActivity(),R.layout.custom_lv_thongke_thu,arrayThu);
        lvChi.setAdapter(chiadapter);
        lvThu.setAdapter(thuadapter);

        tvTongChi=view.findViewById(R.id.tvTongChi);
        tvTongThu=view.findViewById(R.id.tvTongThu);
        tvSoDu=view.findViewById(R.id.tvSoDu);

        edNgaybd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(calendar.DATE);
                int thang = calendar.get(calendar.MONTH);
                int nam = calendar.get(calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edNgaybd.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        edNgaykt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(calendar.DATE);
                int thang = calendar.get(calendar.MONTH);
                int nam = calendar.get(calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edNgaykt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        btChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichart= new Intent(getActivity(), ChartActivity.class);
                ichart.putExtra("TongThu",tongthu);
                ichart.putExtra("TongChi",tongchi);
                startActivity(ichart);
            }
        });


        btXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq;
                String ngaybd= edNgaybd.getText().toString().trim();
                String ngaykt= edNgaykt.getText().toString().trim();
                if (ngaybd.isEmpty()||ngaykt.isEmpty()){
                    showToast(" Không được để trống !",R.drawable.warning);
                }else {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date d1 = format.parse(ngaybd);
                        Date d2= format.parse(ngaykt);
                        long diff = d2.getTime() - d1.getTime();
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        kq=(int) diffDays;
                        if (kq>=0)
                        {
                            arrayChi.clear();
                            arrayThu.clear();
                            getchi(url,ngaybd,ngaykt);
                            getthu(urls,ngaybd,ngaykt);
                            getsodu(urlsd, ngaybd,ngaykt);
                        }else {
                            showToast(" Ngay bắt đầu không được lớn hơn ngay kết thúc !",R.drawable.warning);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        return view;
    }
    private void getsodu(String url, final String ngaybd, final String ngaykt) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        sodu=sodu+object.getInt("SoDu");

                    }
                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);
                    String strSodu = en.format(sodu);

                    tvSoDu.setText("Số dư : "+strSodu+" đ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi ket noi", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("Ngaybd", ngaybd.trim());
                params.put("Ngaykt", ngaykt.trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getchi(String url,final String ngaybd,final  String ngaykt) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        tongchi=tongchi+object.getInt("SoTienChi");
                        arrayChi.add(new ttChi(object.getString("TenLoaiChi"), object.getInt("SoTienChi")));
                    }

                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);
                    String strTongchi = en.format(tongchi);
                    //Toast.makeText(getActivity(), str1, Toast.LENGTH_SHORT).show();

                    tvTongChi.setText("Tổng Chi : "+strTongchi+" đ");
                    chiadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi ket noi", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("Ngaybd", ngaybd.trim());
                params.put("Ngaykt", ngaykt.trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void getthu(String url,final String ngaybd,final  String ngaykt) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        tongthu=tongthu+object.getInt("SoTienThu");
                        arrayThu.add(new ttThu(object.getString("TenLoaiThu"), object.getInt("SoTienThu")));
                    }

                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);
                    String strTongthu = en.format(tongthu);
                    tvTongThu.setText("Tổng Thu : "+strTongthu+" đ");

                    chiadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loi ket noi", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("Ngaybd", ngaybd.trim());
                params.put("Ngaykt", ngaykt.trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public  void  showToast(String show,int src){
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) getActivity().findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        ImageView imvToast = (ImageView) layout.findViewById(R.id.imvToast);
        text.setText(show);
        imvToast.setImageResource(src);
        Toast toast = new Toast(getActivity().getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}