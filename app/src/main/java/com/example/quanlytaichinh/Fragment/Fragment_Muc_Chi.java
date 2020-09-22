package com.example.quanlytaichinh.Fragment;

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
import android.widget.Button;
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
import com.example.quanlytaichinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Muc_Chi extends Fragment {
    String TenDangNhap ;

    EditText edTenLoaiChi;
    Button btThemChi;
    ImageButton btReset;
    ListView lvMucChi1;
    ArrayList<MucChi> arrayLoaiChi;
    MucChiAdapter adapter;
    String urls = "http://192.168.1.13/androidwebservice/getdataLoaiChi.php";
    String urli = "http://192.168.1.13/androidwebservice/insertLoaiChi.php";
    String urld = "http://192.168.1.13/androidwebservice/deleteLoaiChi.php";
    String urlsua = "http://192.168.1.13/androidwebservice/updateLoaiChi.php";

    public Fragment_Muc_Chi() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muc_chi, container, false);
        FloatingActionButton ftb = (FloatingActionButton) view.findViewById(R.id.fabMucChi);

        Intent intent = getActivity().getIntent();
        TenDangNhap = intent.getStringExtra("TenDangNhap");


        lvMucChi1 = (ListView) view.findViewById(R.id.lvMucChi);
        arrayLoaiChi = new ArrayList<>();
        adapter = new MucChiAdapter(getActivity(),R.layout.custom_lv_muc_chi,arrayLoaiChi);
        lvMucChi1.setAdapter(adapter);

        getloaichi(urls);
//        btReset=view.findViewById(R.id.btreset);
////        btReset.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                arrayLoaiChi.clear();
////                lvMucChi1.setAdapter(adapter);
////                getloaichi(urls);
////            }
////        });

        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        lvMucChi1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MucChi mucchi = arrayLoaiChi.get(position);
                final String mlc = mucchi.getMaloaichi();
                final String tlc = mucchi.getTenloaichi();
                final String loaichi = mucchi.getTenloaichi();

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Xác nhận");
                alertDialog.setMessage("Bạn muốn thay đổi danh mục này");
               // alertDialog.setIcon(R.drawable.android);
                alertDialog.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialogsua(mlc,tlc);
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaLoaiChi(urld,loaichi);

                        dialog.dismiss();
//                        getActivity().finish();
//                        startActivity(getActivity().getIntent());
                        arrayLoaiChi.clear();
                        lvMucChi1.setAdapter(adapter);
                        getloaichi(urls);
                        adapter.notifyDataSetChanged();
//                        lvMucChi1.invalidateViews();
//                        lvMucChi1.refreshDrawableState();

                    }
                });
                alertDialog.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                alertDialog.show();
                adapter.notifyDataSetChanged();//them
            }
        });

        return view;
    }

    public void dialogsua(final String mlc,final String tlc) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_muc_chi, null);
        edTenLoaiChi = (EditText) alertLayout.findViewById(R.id.edTenLoaiChi);
        Button btThemChi = (Button) alertLayout.findViewById(R.id.btThemChi);
        edTenLoaiChi.setText(tlc);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Sửa mục chi ");
        final AlertDialog dialog = alert.create();
        dialog.show();

        btThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaLoaiChi(urlsua,mlc,edTenLoaiChi.getText().toString().trim());
                dialog.cancel();
//                getActivity().finish();
//                startActivity(getActivity().getIntent());
                arrayLoaiChi.clear();
                lvMucChi1.setAdapter(adapter);
                getloaichi(urls);
                adapter.notifyDataSetChanged();
//                lvMucChi1.invalidateViews();
//                lvMucChi1.refreshDrawableState();

            }
        });

    }

    public void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_muc_chi, null);
        edTenLoaiChi = (EditText) alertLayout.findViewById(R.id.edTenLoaiChi);
        btThemChi = (Button) alertLayout.findViewById(R.id.btThemChi);
        Button btHuy=(Button) alertLayout.findViewById(R.id.btHuy);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(alertLayout);
        alert.setTitle("Thêm mục chi ");
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_lv);

        btThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edTenLoaiChi.getText().toString().isEmpty()){
                    showToast2("Không được bỏ trống !",R.drawable.warning);
                }
                else {
                    ThemLoaiChi(urli);
                    dialog.cancel();
                }
//                getActivity().finish();
//                startActivity(getActivity().getIntent());
                arrayLoaiChi.clear();
                lvMucChi1.setAdapter(adapter);
                getloaichi(urls);
                adapter.notifyDataSetChanged();
                lvMucChi1.invalidateViews();
                lvMucChi1.refreshDrawableState();
            }
        });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    private void ThemLoaiChi(final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")){
                    showToast1("Thêm thành công");
                }else{
                    Toast.makeText(getActivity(),"Dữ liệu không hợp lệ !"+response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Xay Ra Loi",Toast.LENGTH_LONG).show();
                Log.d("AAA","loi\n"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("TenDangNhap",TenDangNhap.trim());


                params.put("TenLoaiChi",edTenLoaiChi.getText().toString().trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void getloaichi(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        arrayLoaiChi.add(new MucChi(object.getString("MaLoaiChi"),object.getString("TenLoaiChi")));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();

            }

        })
            {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());

                return params;
            }
        }
                ;


        requestQueue.add(stringRequest);
    }

    private void XoaLoaiChi(final String url, final String tenloaichi) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast1("Xóa thành công ");
                } else {
                    Toast.makeText(getActivity(), "Loi dang ky " + response, Toast.LENGTH_LONG).show();
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
                params.put("TenLoaiChi", tenloaichi);
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

    private void SuaLoaiChi(final String url,final String mlc,final  String tlcmoi){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("true")) {
                    showToast1("Sửa thành  công");
                } else {
                    Toast.makeText(getActivity(), "Lỗi dữ liệu !" + response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xảy ra Lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "loi\n" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenDangNhap", TenDangNhap.trim());
                params.put("MaLoaiChi", mlc.trim());
                params.put("TenLoaiChi", tlcmoi);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public  void  showToast1(String show){
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) getActivity().findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(show);
        Toast toast = new Toast(getActivity().getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public  void  showToast2(String show,int src){
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
