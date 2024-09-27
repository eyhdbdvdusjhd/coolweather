package com.coolweather.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.coolweather.android.databinding.ChooseAreaBinding;
import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//遍历省市县的数据碎片

public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CITY=1;
    public static final int LEVEL_COUNTY=2;

//    当前选中级别，因为是一级级选择器，所以初始化为0
    private int currentLevel=LEVEL_PROVINCE;//会导致选城市有重复操作

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

//  省、市、县、列表
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

//    当前选中省
    private Province selectedProvince;
//    当前选中市
    private City selectedCity;

    private ProgressDialog progressDialog;
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String COUNTY = "county";

    /*
    在 Java 代码中，ChooseAreaBinding 这样的声明通常与数据绑定（Data Binding）有关，特别是在 Android 开发中。
    这里 binding 变量的作用是为了持有通过数据绑定生成的视图对象。具体来说：
数据绑定机制：
   ChooseAreaBinding 类是由编译器自动生成的类，它包含了对布局文件中的所有视图（如 TextView, Button 等）的引用。
简化视图操作：
    通过 binding 变量可以直接访问和操作布局文件中的各个组件，无需通过 findViewById 方法查找视图，从而简化了代码并减少了出错的机会。
支持表达式语言：数据绑定还允许在布局文件中使用表达式语言来直接绑定数据模型到视图上，增强了 UI 的灵活性。

    * */
    ChooseAreaBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        自动加载布局完成绑定相应操作，后续直接binding.listView.setAdapter(adapter)等操作，省略findviewById操作
        binding=ChooseAreaBinding.inflate(inflater,container,false);
        adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,dataList);
        binding.listView.setAdapter(adapter);
        return binding.getRoot();
//        正常是 return view； 注意！！！
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.listView.setOnItemClickListener((adapterView, view, position, id)->{
            if (currentLevel == LEVEL_PROVINCE){
                selectedProvince = provinceList.get(position);
                queryCities();
            }else if (currentLevel == LEVEL_CITY){
                selectedCity=cityList.get(position);
                queryCounties();
            }else if ( currentLevel == LEVEL_COUNTY){
//                从这里使用显示Intent可以直接跳转到天气显示界面
                Intent intent=new Intent(getActivity(),WeatherActivity.class);
                County county=countyList.get(position);
                intent.putExtra("weather_id",county.getWeatherId());
                intent.putExtra("current_province",selectedProvince.getProvinceName());
                intent.putExtra("current_city",selectedCity.getCityName());
                intent.putExtra("current_county",county.getCountyName());
                startActivity(intent);

            }
        });
        binding.backButton.setOnClickListener(v->{
            if (currentLevel==LEVEL_COUNTY){
                queryCities();
            }else if (currentLevel==LEVEL_CITY){
                queryProvinces();
            }
        });
        queryProvinces();
    }

    private void queryProvinces(){
        binding.titleText.setText("中国(China)");
        binding.backButton.setVisibility(View.GONE);

        provinceList = LitePal.findAll(Province.class);

        if (provinceList.size()>0){
            dataList.clear();
            for (Province province : provinceList){
                dataList.add(province.getProvinceName());
                province.save();
            }

            adapter.notifyDataSetChanged();
            binding.listView.setSelection(0);
            currentLevel=LEVEL_PROVINCE;

        }else {
            String address="http://guolin.tech/api/china";
            queryFromServer(address, PROVINCE);
        }
    }

    private void queryCities(){
        binding.titleText.setText(selectedProvince.getProvinceName());
        binding.backButton.setVisibility(View.VISIBLE);

        cityList = LitePal.where("provinceId=?",String.valueOf(selectedProvince.getProvinceCode()))
                .find(City.class);
        if (cityList.size()>0){
            dataList.clear();
            for (City city : cityList){
                dataList.add(city.getCityName());
                city.save();
            }
            adapter.notifyDataSetChanged();
            binding.listView.setSelection(0);
            currentLevel=LEVEL_CITY;
        }else {
            int provinceCode=selectedProvince.getProvinceCode();
            String address="http://guolin.tech/api/china/"+provinceCode;
            queryFromServer(address, CITY);
        }
    }

    private void queryCounties(){
        binding.titleText.setText(selectedProvince.getProvinceName()+"|"+selectedCity.getCityName());
        binding.backButton.setVisibility(View.VISIBLE);

        countyList = LitePal.where("cityId=?",String.valueOf(selectedCity.getId()))
                .find(County.class);
        if (countyList.size()>0){
            dataList.clear();
            for (County county : countyList){
                dataList.add(county.getCountyName());
                county.save();
            }
            adapter.notifyDataSetChanged();
            binding.listView.setSelection(0);
            currentLevel=LEVEL_COUNTY;
        }else {
            int provinceCode=selectedProvince.getProvinceCode();
            int cityCode=selectedCity.getCityCode();
            String address="http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
            queryFromServer(address, COUNTY);
        }
    }
//根据地址和类型从服务器上查询省市县数据
    private void queryFromServer(String address, String type) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                使用runOnUiThread确保在主线程执行，以显示提示信息。
                getActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),"访问网络，获取相关数据失败！", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText=response.body().string();
                boolean result=false;
                if (type.equals(PROVINCE)){
                    result= Utility.handleProvinceResp(responseText);
                }else if (type.equals(CITY)){
                    result=Utility.handleCityResp(responseText,selectedProvince.getProvinceCode());
                }else if (type.equals(COUNTY)){
                    result=Utility.handleCountyResp(responseText,selectedCity.getId());
                }
                if (result){
                    getActivity().runOnUiThread(()->{
                        closeProgressDialog();
                        if (PROVINCE.equals(type)){
                            queryProvinces();
                        }else if (CITY.equals(type)){
                            queryCities();
                        }else if (COUNTY.equals(type)){
                            queryCounties();
                        }
                    });
                }
            }
        });
    }

    private void closeProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    private void showProgressDialog(){
        if (progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }
}
