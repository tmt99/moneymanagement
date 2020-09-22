package com.example.quanlytaichinh.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import com.example.quanlytaichinh.QLThuActivity;
import com.example.quanlytaichinh.QLThuChiActivity;
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Fragment_Thu extends Fragment {
    public String TenDangNhap;
    Spinner spinnerKhoanThu;
    EditText edSoTienThu;
    EditText edNgayThu;
    ImageButton btreset;
    ListView lvThu;
    Button btThemThu, btHuy;
    ArrayList<Thu> arrayThu;
    ThuAdapter thuadapter;
    ArrayList<MucThu> arrayLoaiThu;

    ArrayList<String> loaithu;
    ArrayAdapter loaithudapter;

    String tenloai;

    String urls = "http://192.168.1.13/androidwebservice/getdataLoaiThu.php";
    String url = "http://192.168.1.13/androidwebservice/getdataThu.php";
    String urli = "http://192.168.1.13/androidwebservice/insertKhoanThu.php";
    String urld = "http://192.168.1.13/androidwebservice/deleteKhoanThu.php";
    String urlsua = "http://192.168.1.13/androidwebservice/updateKhoanThu.php";

    public Fragment_Thu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        lvThu = (ListView) view.findViewById(R.id.lvThu);

        Intent intent = getActivity().getIntent();
        TenDangNhap = intent.getStringExtra("TenDangNhap");
        // Toast.makeText(getActivity(), TenDangNhap, Toast.LENGTH_SHORT).show();

        arrayThu = new ArrayList<>();
        thuadapter = new ThuAdapter(getActivity(), R.layout.custom_lv_thu, arrayThu);
        lvThu.setAdapter(thuadapter);
        getthu(url);

//        btreset = view.findViewById(R.id.btreset);
//        btreset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayThu.clear();
//                lvThu.setAdapter(thuadapter);
//                getthu(url);
//            }
//        });

        arrayLoaiThu = new ArrayList<>();

        FloatingActionButton ftb = (FloatingActionButton) view.findViewById(R.id.fabThu);
        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

      /*  lvThu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Thu mc = arrayThu.get(position);
                final int makhoanthu = mc.getMakhoanthu();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xóa khoản thu");
                builder.setMessage("Bạn chắc chắn muốn xóa không ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        xoakhoanthu(urld, makhoanthu);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });*/

        lvThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Thu mt = arrayThu.get(position);
                final int mkt = mt.getMakhoanthu();
                final String tlt = mt.getTenloaithu();
                final int stt = mt.getSotienthu();
                final String nt = mt.getNgaythu();
                final int makhoanthu = mt.getMakhoanthu();
               /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sửa khoản thu");
                builder.setMessage("Bạn chắc chắn muốn sửa không ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialogsua(mkt, tlt, stt, nt);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();*/

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Xác nhận");
                alertDialog.setMessage("Bạn muốn thay đổi giao dịch này");
                // alertDialog.setIcon(R.drawable.android);
                alertDialog.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogsua(mkt, tlt, stt, nt);
                        dialog.dismiss();
//                        arrayThu.clear();
//                        lvThu.setAdapter(thuadapter);
//                        getthu(url);
//                        thuadapter.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        xoakhoanthu(urld, makhoanthu);
                        dialog.dismiss();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
//                        arrayThu.clear();
//                        lvThu.setAdapter(thuadapter);
//                        getthu(url);
//                        thuadapter.notifyDataSetChanged();
//                        lvThu.invalidateViews();
//                        lvThu.refreshDrawableState();

                    }
                });
                alertDialog.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                alertDialog.show();
            }
        });
        return view;
    }


    public void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_them_thu, null);
        spinnerKhoanThu = (Spinner) alertLayout.findViewById(R.id.spinnerKhoanThu);
        edSoTienThu = (EditText) alertLayout.findViewById(R.id.edSoTienThu);
        edNgayThu = (EditText) alertLayout.findViewById(R.id.edNgayThu);
        btThemThu = (Button) alertLayout.findViewById(R.id.btThemThu);
        btHuy = (Button) alertLayout.findViewById(R.id.btHuy);

        loaithu = new ArrayList<>();

        loaithudapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, loaithu);
        spinnerKhoanThu.setAdapter(loaithudapter);

        getloaithu(urls);
        spinnerKhoanThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenloai = spinnerKhoanThu.getItemAtPosition(spinnerKhoanThu.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
                tenloai = spinnerKhoanThu.getItemAtPosition(spinnerKhoanThu.getFirstVisiblePosition()).toString();
            }
        });
        edNgayThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Thêm khoản thu ");
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_lv);
        btThemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaythu = edNgayThu.getText().toString();
//                int sotien = Integer.parseInt(edSoTienThu.getText().toString());
                if (tenloai.isEmpty() || ngaythu.isEmpty() || edSoTienThu.getText().toString().isEmpty()) {
                    showToast("Không được bỏ trống ! ",R.drawable.warning);
                } else {
                    themkhoanthu(urli);
                    //-------------------------------------

                    //-------------------------------------
                    dialog.cancel();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
//                    Toast.makeText(getActivity(), tenloai + " : " + ngay
//                getthu(url);thu + sotien, Toast.LENGTH_LONG).show();
                }

             //   finish();
              //  startActivity(getActivity());
//                arrayThu.clear();
//                lvThu.setAdapter(thuadapter);
//                thuadapter.notifyDataSetChanged();
//                lvThu.invalidateViews();
//                lvThu.refreshDrawableState();

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
                edNgayThu.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void getthu(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                    for (int i = array.length()-1; i >=0; i--) {

                        JSONObject object = array.getJSONObject(i);
                        arrayThu.add(new Thu(object.getInt("MaKhoanThu"),
                                object.getString("TenLoaiThu"),
                                object.getInt("SoTienThu"),
                                object.getString("NgayThu")));
                    }
                    thuadapter.notifyDataSetChanged();
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

    private void getloaithu(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    if (array.length() < 1) {
                        showToast("Vui lòng nhập loại thu!", R.drawable.warning);
                        Intent i = new Intent(getActivity(), QLThuActivity.class);
                        i.putExtra("TenDangNhap", TenDangNhap);
                        startActivity(i);
                    } else {
//                        Toast.makeText(getActivity(), array.toString(), Toast.LENGTH_LONG).show();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            loaithu.add(object.getString("TenLoaiThu"));
                        }
                    }
                    loaithudapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Lỗi kết nối ", Toast.LENGTH_SHORT).show();
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

    private void themkhoanthu(final String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Thêm thành công", R.drawable.done);
                } else {
                    Toast.makeText(getActivity(), "Lỗi dữ liệu ! " + response, Toast.LENGTH_LONG).show();
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
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("TenLoaiThu", tenloai.trim());
                params.put("SoTienThu", edSoTienThu.getText().toString().trim());
                params.put("NgayThu", edNgayThu.getText().toString());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void xoakhoanthu(final String url, final int ma) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Xóa thành công", R.drawable.done);
                } else {
                    Toast.makeText(getActivity(), "Lỗi dữ liệu " + response, Toast.LENGTH_LONG).show();
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
                params.put("MaKhoanThu", String.valueOf(ma));
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

    public void dialogsua(final int mkt, final String tlt, final int stt, final String nt) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_them_thu, null);

        spinnerKhoanThu = (Spinner) alertLayout.findViewById(R.id.spinnerKhoanThu);
        edSoTienThu = (EditText) alertLayout.findViewById(R.id.edSoTienThu);
        edNgayThu = (EditText) alertLayout.findViewById(R.id.edNgayThu);
        btThemThu = (Button) alertLayout.findViewById(R.id.btThemThu);
        btHuy = (Button) alertLayout.findViewById(R.id.btHuy);

//        Locale localeEN = new Locale("en", "EN");
//        NumberFormat en = NumberFormat.getInstance(localeEN);
//        String sotienthu = en.format(stt);
//        edSoTienThu.setText(sotienthu);

        edSoTienThu.setText(String.valueOf(stt));
        edNgayThu.setText(nt);

        loaithu = new ArrayList<>();
        loaithudapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, loaithu);
        spinnerKhoanThu.setAdapter(loaithudapter);


        getloaithu(urls);
        spinnerKhoanThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenloai = spinnerKhoanThu.getItemAtPosition(spinnerKhoanThu.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
                tenloai = spinnerKhoanThu.getItemAtPosition(spinnerKhoanThu.getFirstVisiblePosition()).toString();
            }
        });

        edNgayThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Sửa khoản thu ");
        final AlertDialog dialog = alert.create();
        dialog.show();

        btThemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaythu = edNgayThu.getText().toString();
                int sotien = Integer.parseInt(edSoTienThu.getText().toString());
                if (tenloai.isEmpty() || ngaythu.isEmpty() || edSoTienThu.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Không được bỏ trống !", Toast.LENGTH_LONG).show();
                } else {
                    suakhoanthu(urlsua, mkt);
                    dialog.cancel();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }

//                arrayThu.clear();
//                lvThu.setAdapter(thuadapter);
//                getthu(url);
//                thuadapter.notifyDataSetChanged();
//                lvThu.invalidateViews();
//                lvThu.refreshDrawableState();

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

    private void suakhoanthu(final String url, final int mkt) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast("Sửa thành công ", R.drawable.done);
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
                params.put("MaKhoanThu", String.valueOf(mkt).trim());
                params.put("TenLoaiThu", tenloai.trim());
                params.put("SoTienThu", edSoTienThu.getText().toString().trim());
                params.put("NgayThu", edNgayThu.getText().toString());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public void showToast(String show, int src) {
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) getActivity().findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(show);
        ImageView imvToast = (ImageView) layout.findViewById(R.id.imvToast);
        Toast toast = new Toast(getActivity().getApplicationContext());
        imvToast.setImageResource(src);
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
