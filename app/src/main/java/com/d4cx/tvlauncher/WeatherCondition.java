package com.d4cx.tvlauncher;
final class WeatherCondition {
 final String label,kind;final int intensity;
 WeatherCondition(String label,String kind,int intensity){this.label=label;this.kind=kind;this.intensity=intensity;}
 static WeatherCondition from(int c,boolean day){switch(c){
 case 0:return new WeatherCondition(day?"Clear sky":"Clear night","clear",0);
 case 1:return new WeatherCondition("Mostly clear","partly",0);
 case 2:return new WeatherCondition("Partly cloudy","partly",0);
 case 3:return new WeatherCondition("Overcast","cloud",0);
 case 45:return new WeatherCondition("Fog","fog",0);
 case 48:return new WeatherCondition("Freezing fog","fog",1);
 case 51:return new WeatherCondition("Light drizzle","drizzle",1);
 case 53:return new WeatherCondition("Drizzle","drizzle",2);
 case 55:return new WeatherCondition("Dense drizzle","drizzle",3);
 case 56:return new WeatherCondition("Freezing drizzle","ice",1);
 case 57:return new WeatherCondition("Heavy freezing drizzle","ice",3);
 case 61:case 80:return new WeatherCondition("Light rain","rain",1);
 case 63:case 81:return new WeatherCondition("Rain","rain",2);
 case 65:case 82:return new WeatherCondition("Heavy rain","rain",3);
 case 66:return new WeatherCondition("Freezing rain","ice",1);
 case 67:return new WeatherCondition("Heavy freezing rain","ice",3);
 case 71:case 85:return new WeatherCondition("Light snow","snow",1);
 case 73:return new WeatherCondition("Snow","snow",2);
 case 75:case 86:return new WeatherCondition("Heavy snow","snow",3);
 case 77:return new WeatherCondition("Snow grains","snow",1);
 case 95:return new WeatherCondition("Thunderstorm","storm",1);
 case 97:return new WeatherCondition("Heavy thunderstorm","storm",3);
 case 96:return new WeatherCondition("Thunder & hail","hail",1);
 case 99:return new WeatherCondition("Heavy thunder & hail","hail",3);
 default:return new WeatherCondition("Weather unavailable","unknown",0);
 }}
}
