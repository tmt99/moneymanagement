package com.example.quanlytaichinh.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlytaichinh.QLThuChiActivity;
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Fragment_Chi extends Fragment {
    public String TenDangNhap;
    public String tdn;
    public boolean rong;
    ListView lvChi;
    Spinner spinnerKhoanChi;
    EditText edSoTienChi;
    ImageButton btReset;
    EditText edNgayChi;
    Button btThemChi, btHuy;
    ArrayList<Chi> arrayChi;
    ChiAdapter chiadapter;
    ArrayList<MucChi> arrayLoaiChi;

    ArrayList<String> loaithuchi;
   // ArrayList<MucChi> loaithuchi;//them
    ArrayAdapter loaithuchiadapter;

    String tenloai;
    String url =    "http://192.168.1.13/androidwebservice/getdataChi.php";
    String urls =   "http://192.168.1.13/androidwebservice/getdataLoaiChi.php";
    String urli =   "http://192.168.1.13/androidwebservice/insertkhoanChi.php";
    String urld =   "http://192.168.1.13/androidwebservice/deleteKhoanChi.php";
    String urlsua = "http://192.168.1.13/androidwebservice/updateKhoanChi.php";

   // String MaLoaiChi;//them
    //String maloaichi;//them

    public Fragment_Chi() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        TenDangNhap = intent.getStringExtra("TenDangNhap");
        //MaLoaiChi= intent.getStringExtra("MaLoaiChi");//them
        //MaLoaiChi= intent.getStringExtra("MaLoaiChi");//them
        //Toast.makeText(getActivity(),"Ten dang nhap :"+TenDangNhap,Toast.LENGTH_LONG).show();

        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        lvChi = (ListView) view.findViewById(R.id.lvChi);
        arrayChi = new ArrayList<>();
        chiadapter = new ChiAdapter(getActivity(), R.layout.custom_lv_chi, arrayChi);
        lvChi.setAdapter(chiadapter);
        getchi(url);

//        btReset=view.findViewById(R.id.btreset);
//        btReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayChi.clear();
//                lvChi.setAdapter(chiadapter);
//                getchi(url);
//            }
//        });

        arrayLoaiChi = new ArrayList<>();

        // adapter = new MucChiAdapter(getActivity(),R.layout.custom_lv_muc_chi,arrayLoaiChi);

        FloatingActionButton ftb = (FloatingActionButton) view.findViewById(R.id.fabChi);
        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });



        lvChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chi c = arrayChi.get(position);
                final int mkc = c.getMakhoanchi();
                final String tlc = c.getTenloaichi();
                final int stc = c.getSotienchi();
                final String nc = c.getNgaychi();

                final int makhoanchi = c.getMakhoanchi();


                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Xác nhận");
                alertDialog.setMessage("Bạn muốn thay đổi giao dịch này ");
                //alertDialog.setIcon(R.drawable.android);
                alertDialog.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(Fragment_Chi.this, "Bạn đã chọn YES",Toast.LENGTH_SHORT).show();
                        dialogsua(mkc, tlc, stc, nc);
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(Fragment_Chi.this, "Bạn đã chọn NO",Toast.LENGTH_SHORT).show();
                        xoachi(urld, makhoanchi);
                        dialog.dismiss();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
//                        arrayChi.clear();
//                        lvChi.setAdapter(chiadapter);
//                        getchi(url);
//                        chiadapter.notifyDataSetChanged();
//                        lvChi.invalidateViews();
//                        lvChi.refreshDrawableState();
                    }
                });
                alertDialog.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(Fragment_Chi.this, "Bạn đã chọn SKIP",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        }
        );

        return view;
    }

    public void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_them_chi, null);

        spinnerKhoanChi = (Spinner) alertLayout.findViewById(R.id.spinnerKhoanChi);
        edSoTienChi = (EditText) alertLayout.findViewById(R.id.edSoTienChi);
        edNgayChi = (EditText) alertLayout.findViewById(R.id.edNgayChi);
        btThemChi = (Button) alertLayout.findViewById(R.id.btThemChi);
        btHuy = (Button) alertLayout.findViewById(R.id.btHuy);

        loaithuchi = new ArrayList<>();

        loaithuchiadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, loaithuchi);
        spinnerKhoanChi.setAdapter(loaithuchiadapter);
        getloaichi(urls);

        spinnerKhoanChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              //  maloaichi=spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getFirstVisiblePosition()).toString();//them
                tenloai = spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
               // maloaichi=spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getFirstVisiblePosition()).toString();//them
                tenloai = spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getFirstVisiblePosition()).toString();
            }
        });

        edNgayChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Thêm khoản chi ");
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_lv);


        btThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychi = edNgayChi.getText().toString();
//                int sotien = Integer.parseInt(edSoTienChi.getText().toString());
                if (tenloai.isEmpty() || ngaychi.isEmpty() || edSoTienChi.getText().toString().isEmpty()) {
                    showToast("Không được bỏ trống !",R.drawable.warning);
                } else {
                    themkhoanchi(urli);
                    //arrayChi.add(new Chi(tenloai,Integer.parseInt(edSoTienChi.getText().toString()),edNgayChi.getText().toString()));
                    dialog.cancel();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }

//                arrayChi.clear();
////                lvChi.setAdapter(chiadapter);
////                getchi(url);
//                chiadapter.notifyDataSetChanged();
//                lvChi.invalidateViews();
//                lvChi.refreshDrawableState();
                //lvChi.invalidateViews();
            }
        });


        btHuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

    }

    public void datepicker() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(calendar.DATE);
        int thang = calendar.get(calendar.MONTH);
        int nam = calendar.get(calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edNgayChi.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void getchi(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = array.length()-1; i >=0; i--) {

                        JSONObject object = array.getJSONObject(i);
                        arrayChi.add(new Chi(object.getInt("MaKhoanChi"),
                                object.getString("TenLoaiChi"),
                                object.getInt("SoTienChi"),
                                object.getString("NgayChi")));
                    }
                   chiadapter.notifyDataSetChanged();
                    //lvChi.invalidateViews();
                } catch (JSONException e) {

                e.printStackTrace();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void getloaichi(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    if (array.length()<1){
                        showToast("Vui lòng nhập loại chi!",R.drawable.warning);
                        Intent i =new Intent(getActivity(),QLThuChiActivity.class);
                        i.putExtra("TenDangNhap",TenDangNhap);
                        startActivity(i);
                    }else{
//                        JSONArray array = new JSONArray(response);
////                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
//                        for (int i = array.length()-1; i >=0; i--) {
//
//                            JSONObject object = array.getJSONObject(i);
//                            arrayChi.add(new Chi(object.getInt("MaKhoanChi"),
//                                    object.getString("TenLoaiChi"),
//                                    object.getInt("SoTienChi"),
//                                    object.getString("NgayChi")));
//                        }
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
//                            loaithuchi.add(new MucChi(object.getString("MaLoaiChi"),
//                                                      object.getString("TenLoaiChi")
//                            ));//them
                            loaithuchi.add(object.getString("TenLoaiChi"));
                            //them
                            //loaithuchi.add(String.valueOf(new MucChi(object.getString("MaLoaiChi"), object.getString("TenLoaiChi"))));
                        }
                    }
                    loaithuchiadapter.notifyDataSetChanged();
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
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void themkhoanchi(final String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Thêm thành công",R.drawable.done);
                } else {
                    Toast.makeText(getActivity(), "Lỗi dữ liệu " + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "xảy ra lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                //params.put("MaLoaiChi", maloaichi.trim());//them
                params.put("TenLoaiChi", tenloai.trim());
                params.put("SoTienChi", edSoTienChi.getText().toString().trim());
                params.put("NgayChi", edNgayChi.getText().toString());
              // params.put("TenLoaiChi", tenloai.trim());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void xoachi(final String url, final int ma) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Xóa thành công",R.drawable.done);
                } else {
                    Toast.makeText(getActivity(), "lỗi csdl " + response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("MaKhoanChi", String.valueOf(ma));
                params.put("TenDangNhap", TenDangNhap.trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public void dialogsua(final int mkc, final String tlc, final int stc, final String nc) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_them_chi, null);

        spinnerKhoanChi = (Spinner) alertLayout.findViewById(R.id.spinnerKhoanChi);
        edSoTienChi = (EditText) alertLayout.findViewById(R.id.edSoTienChi);
        edNgayChi = (EditText) alertLayout.findViewById(R.id.edNgayChi);
        btThemChi = (Button) alertLayout.findViewById(R.id.btThemChi);
        btHuy = (Button) alertLayout.findViewById(R.id.btHuy);

//        Locale localeEN = new Locale("en", "EN");
//        NumberFormat en = NumberFormat.getInstance(localeEN);
//        String sotienchi = en.format(stc);

        edSoTienChi.setText(String.valueOf(stc));
        edNgayChi.setText(nc);

        loaithuchi = new ArrayList<>();
        loaithuchiadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, loaithuchi);
        spinnerKhoanChi.setAdapter(loaithuchiadapter);
        getloaichi(urls);
        spinnerKhoanChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenloai = spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
                tenloai = spinnerKhoanChi.getItemAtPosition(spinnerKhoanChi.getFirstVisiblePosition()).toString();
            }
        });


        edNgayChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
        }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Sửa khoản chi ");
        final AlertDialog dialog = alert.create();
        dialog.show();

        btThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychi = edNgayChi.getText().toString();
                int sotien = Integer.parseInt(edSoTienChi.getText().toString());
                if (tenloai.isEmpty() || ngaychi.isEmpty() || edSoTienChi.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Khong duoc bo trong", Toast.LENGTH_LONG).show();
                } else {
                    suakhoanchi(urlsua, mkc);
                    dialog.cancel();
                }
                getActivity().finish();
                startActivity(getActivity().getIntent());
//                arrayChi.clear();
//                lvChi.setAdapter(chiadapter);
//                getchi(url);
//                chiadapter.notifyDataSetChanged();
//                lvChi.invalidateViews();
//                lvChi.refreshDrawableState();
                //lvChi.invalidateViews();

//                run = new Runnable() {
////                    public void run() {
////                        //reload content
////                        arrayChi.clear();
////                        arrayChi.addAll(db.readAll());
////                        chiadapter.notifyDataSetChanged();
////                        lvChi.invalidateViews();
////                        lvChi.refreshDrawableState();
////                    }
////                };

            }
        });


        btHuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

    }

    private void suakhoanchi(final String url, final int mkc) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Sửa thành công",R.drawable.done);
                } else {
                    Toast.makeText(getActivity(), "Lỗi sửa " + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xay Ra Loi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("MaKhoanChi", String.valueOf(mkc).trim());
                params.put("TenLoaiChi", tenloai.trim());
                params.put("SoTienChi", edSoTienChi.getText().toString().trim());
                params.put("NgayChi", edNgayChi.getText().toString());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
