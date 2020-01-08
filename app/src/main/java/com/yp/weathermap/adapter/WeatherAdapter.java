package com.yp.weathermap.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yp.weathermap.R;
import com.yp.weathermap.model.WeatherResponse;
import com.yp.weathermap.view.MainActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
public ArrayList<WeatherResponse.List> weatherList;
Activity mActivity;
String ImageUrl = "http://openweathermap.org/img/w/";

    public WeatherAdapter(ArrayList<WeatherResponse.List> weatherList, Activity mActivity){
        this.weatherList = weatherList;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        String dateTime = weatherList.get(position).getDt_txt();
        String updatedAtTextBefore = "";
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDateTimeBefore = null;
        if(position != 0) {
            String dateTimeBefore = weatherList.get(position - 1).getDt_txt();
            try{
                newDateTimeBefore = format.parse(dateTimeBefore);
            }catch (Exception e){
                e.printStackTrace();
            }
            updatedAtTextBefore = new SimpleDateFormat("E, dd", Locale.ENGLISH).format(newDateTimeBefore);


        Date newDateTime = null;

        try{
            newDateTime= format.parse(dateTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        String updatedAtText = new SimpleDateFormat("E, dd", Locale.ENGLISH).format(newDateTime);

        if(!updatedAtText.equals(updatedAtTextBefore) ) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            holder.day.setText(updatedAtText);
            Double temp = weatherList.get(position).getMain().getTemp() - 273.15;
            holder.tempDay.setText(decimalFormat.format(temp) + "°C");
            holder.weatherDesc.setText(weatherList.get(position).getWeather().get(0).getMain());
            setToGlide(ImageUrl + weatherList.get(position).getWeather().get(0).getIcon() + ".png", holder.imgWeatherDay);
        }else{
            holder.day.setVisibility(View.GONE);
            holder.tempDay.setVisibility(View.GONE);
            holder.imgWeatherDay.setVisibility(View.GONE);
            holder.weatherDesc.setVisibility(View.GONE);

        }
        }else{
            holder.day.setVisibility(View.GONE);
            holder.tempDay.setVisibility(View.GONE);
            holder.imgWeatherDay.setVisibility(View.GONE);
            holder.weatherDesc.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {

            return weatherList.size();

    }
    private void setToGlide(String img, ImageView imgView) {
        Glide.with(mActivity)
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
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.day)
        TextView day;
        @BindView(R.id.imgWeatherDay)
        ImageView imgWeatherDay;
        @BindView(R.id.tempDay)
        TextView tempDay;
        @BindView(R.id.weatherDesc)
        TextView weatherDesc;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
