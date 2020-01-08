package com.yp.weathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yp.weathermap.R;
import com.yp.weathermap.adapter.WeatherAdapter;
import com.yp.weathermap.model.WeatherResponse;
import com.yp.weathermap.service.GetDataWeatherService;
import com.yp.weathermap.service.RetrofitInstance;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.address)
    TextView tvAddress;
    @BindView(R.id.updated_at)
    TextView tvUpdated_at;
    @BindView(R.id.status)
    TextView tvStatus;

    @BindView(R.id.temp)
    TextView tvTemp;
    @BindView(R.id.hiName)
    TextView tvHiName;
    @BindView(R.id.tvWind)
    TextView tvWind;
    @BindView(R.id.tvClouds)
    TextView tvClouds;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvRain)
    TextView tvRain;
    @BindView(R.id.tvPressure)
    TextView tvPressure;

    @BindView(R.id.imgWeather)
    ImageView imgWeather;
    @BindView(R.id.ivCloud)
    ImageView ivCloud;
    @BindView(R.id.ivHumidity)
    ImageView ivHumidity;
    @BindView(R.id.ivMyProfile)
    ImageView ivMyProfile;
    @BindView(R.id.ivPressure)
     ImageView ivPressure;
    @BindView(R.id.ivRain)
    ImageView ivRain;
    @BindView(R.id.ivWind)
    ImageView ivWind;
    @BindView(R.id.rvNextDay)
    RecyclerView rvNextDay;
    @BindView(R.id.mainAllMenu)
    ConstraintLayout mainAllMenu;
    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.errorText)
    TextView errorText;
    String appId= "de06e3296a1cf567e2cb90d65805b328";
    String ImageUrl = "http://openweathermap.org/img/w/";
    String zipCode = "";
    String greeting= "";
    String fullname = "";
    private WeatherAdapter weatherAdapter;
    private ArrayList<WeatherResponse.List> weatherResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        zipCode = getIntent().getStringExtra("zipcode");
        fullname = getIntent().getStringExtra("fullname");
        getMovies();


    }

    private void getMovies() {
        loader.setVisibility(View.VISIBLE);
        GetDataWeatherService getDataWeatherService = RetrofitInstance.getService();
        Call<WeatherResponse>  call = getDataWeatherService.getWeather(zipCode, appId);

call.enqueue(new Callback<WeatherResponse>() {
    @Override
    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
            WeatherResponse weatherResponse =response.body();

            if(weatherResponse != null && weatherResponse.getList() != null){
                weatherResponses = (ArrayList<WeatherResponse.List>) weatherResponse.getList();
                for(WeatherResponse.List r : weatherResponses) {
                    Log.d("berhasil hore", r.getWeather().get(0).getDescription().toString());
                }
                tvAddress.setText(weatherResponse.getCity().getName()+", " + weatherResponse.getCity().getCountry());
                Calendar c = Calendar.getInstance();

                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

                if(timeOfDay >= 0 && timeOfDay < 12){
                    greeting = "Selamat Pagi";
                }else if(timeOfDay >= 12 && timeOfDay < 16){
                    greeting = "Selamat Pagi";
                }else if(timeOfDay >= 16 && timeOfDay < 19){
                    greeting = "Selamat Sore";
                }else if(timeOfDay >= 19 && timeOfDay < 24){
                    greeting = "Selamat Malam";

                }
                String dateTime = weatherResponses.get(0).getDt_txt();
                Date newDateTime = null;
                SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try{
                    newDateTime= format.parse(dateTime);
                }catch (Exception e){
                    e.printStackTrace();
                }
                String updatedAtText = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(newDateTime);
                tvUpdated_at.setText(updatedAtText);
                tvStatus.setText(weatherResponse.getList().get(0).getWeather().get(0).getDescription().toUpperCase());
                DecimalFormat decimalFormat = new DecimalFormat("#.##");

                Double temp = weatherResponse.getList().get(0).getMain().getTemp();
                temp = temp - 273.15;
                tvTemp.setText(String.valueOf(decimalFormat.format(temp)+"°C"));
                setToGlide(ImageUrl + weatherResponse.getList().get(0).getWeather().get(0).getIcon()+".png", imgWeather );
                tvWind.setText(String.valueOf(weatherResponse.getList().get(0).getWind().getSpeed()));
                tvClouds.setText(String.valueOf(weatherResponses.get(0).getClouds().getAll()));
                tvPressure.setText(String.valueOf(weatherResponses.get(0).getMain().getPressure()));
                tvHumidity.setText(String.valueOf(weatherResponses.get(0).getMain().getHumidity()));
                if(weatherResponses.get(0).getRain().get3h() != null) {
                    tvRain.setText(String.valueOf(weatherResponses.get(0).getRain().get3h()));
                }else{
                    tvRain.setText("0");
                }

                tvHiName.setText(greeting+", "+ fullname);

                viewData();
                loader.setVisibility(View.GONE);
                mainAllMenu.setVisibility(View.VISIBLE);
            }
    }


    private void setToGlide(String img, ImageView imgView) {
        Glide.with(MainActivity.this)
                .applyDefaultRequestOptions(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .dontTransform()
                        .encodeFormat(Bitmap.CompressFormat.PNG)
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH)
                        .placeholder(R.drawable.ic_ac_unit_blue_24dp))
                .load(img)
                .into(imgView);
    }
    @Override
    public void onFailure(Call<WeatherResponse> call, Throwable t) {
        loader.setVisibility(View.GONE);
        mainAllMenu.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.VISIBLE);
    }
});
    }

    private void viewData() {
        weatherAdapter = new WeatherAdapter(weatherResponses, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvNextDay.setLayoutManager(layoutManager);
        rvNextDay.setAdapter(weatherAdapter);
    }
}
