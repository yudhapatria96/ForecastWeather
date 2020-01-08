package com.yp.weathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class WeatherResponse {

    @Expose
    @SerializedName("city")
    private City city;
    @Expose
    @SerializedName("list")
    private java.util.List<List> list;
    @Expose
    @SerializedName("cnt")
    private int cnt;
    @Expose
    @SerializedName("message")
    private double message;
    @Expose
    @SerializedName("cod")
    private String cod;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public  java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public static class City {
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("coord")
        private Coord coord;
        @Expose
        @SerializedName("name")
        private String name;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Coord {
        @Expose
        @SerializedName("lon")
        private double lon;
        @Expose
        @SerializedName("lat")
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class List {
        @Expose
        @SerializedName("dt_txt")
        private String dt_txt;
        @Expose
        @SerializedName("sys")
        private Sys sys;
        @Expose
        @SerializedName("rain")
        private Rain rain;
        @Expose
        @SerializedName("wind")
        private Wind wind;
        @Expose
        @SerializedName("clouds")
        private Clouds clouds;
        @Expose
        @SerializedName("weather")
        private java.util.List<Weather> weather;
        @Expose
        @SerializedName("main")
        private Main main;
        @Expose
        @SerializedName("dt")
        private int dt;

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }

        public Rain getRain() {
            return rain;
        }

        public void setRain(Rain rain) {
            this.rain = rain;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(java.util.List<Weather> weather) {
            this.weather = weather;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }
    }

    public static class Sys {
        @Expose
        @SerializedName("pod")
        private String pod;

        public String getPod() {
            return pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }
    }

    public static class Rain {
        @SerializedName("3h")
        @Expose
        private Float _3h;

        public Float get3h() {
            return _3h;
        }

        public void set3h(Float _3h) {
            this._3h = _3h;
        }

    }

    public static class Wind {
        @Expose
        @SerializedName("deg")
        private double deg;
        @Expose
        @SerializedName("speed")
        private double speed;

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }

    public static class Clouds {
        @Expose
        @SerializedName("all")
        private int all;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    public static class Weather {
        @Expose
        @SerializedName("icon")
        private String icon;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("main")
        private String main;
        @Expose
        @SerializedName("id")
        private int id;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Main {
        @Expose
        @SerializedName("temp_kf")
        private double temp_kf;
        @Expose
        @SerializedName("humidity")
        private int humidity;
        @Expose
        @SerializedName("grnd_level")
        private double grnd_level;
        @Expose
        @SerializedName("sea_level")
        private double sea_level;
        @Expose
        @SerializedName("pressure")
        private double pressure;
        @Expose
        @SerializedName("temp_max")
        private double temp_max;
        @Expose
        @SerializedName("temp_min")
        private double temp_min;
        @Expose
        @SerializedName("temp")
        private double temp;

        public double getTemp_kf() {
            return temp_kf;
        }

        public void setTemp_kf(double temp_kf) {
            this.temp_kf = temp_kf;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(double grnd_level) {
            this.grnd_level = grnd_level;
        }

        public double getSea_level() {
            return sea_level;
        }

        public void setSea_level(double sea_level) {
            this.sea_level = sea_level;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}

