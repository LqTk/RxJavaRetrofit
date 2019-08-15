package com.example.weathernet;

public class WeatherResult {
    public String nameen;
    public String cityname;
    public String city;
    public String temp;
    public String tempf;
    public String WD;
    public String wde;
    public String WS;
    public String wse;
    public String SD;
    public String time;
    public String weather;
    public String weathere;
    public String weathercode;
    public String qy;
    public String njd;
    public String sd;
    public String rain;
    public String rain24h;
    public String aqi;
    public String limitnumber;
    public String aqi_pm25;
    public String date;

    @Override
    public String toString() {
        return "WeatherResult{" +
                "nameen='" + nameen + '\'' +
                ", cityname='" + cityname + '\'' +
                ", city='" + city + '\'' +
                ", temp='" + temp + '\'' +
                ", tempf='" + tempf + '\'' +
                ", WD='" + WD + '\'' +
                ", wde='" + wde + '\'' +
                ", WS='" + WS + '\'' +
                ", wse='" + wse + '\'' +
                ", SD='" + SD + '\'' +
                ", time='" + time + '\'' +
                ", weather='" + weather + '\'' +
                ", weathere='" + weathere + '\'' +
                ", weathercode='" + weathercode + '\'' +
                ", qy='" + qy + '\'' +
                ", njd='" + njd + '\'' +
                ", sd='" + sd + '\'' +
                ", rain='" + rain + '\'' +
                ", rain24h='" + rain24h + '\'' +
                ", aqi='" + aqi + '\'' +
                ", limitnumber='" + limitnumber + '\'' +
                ", aqi_pm25='" + aqi_pm25 + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
