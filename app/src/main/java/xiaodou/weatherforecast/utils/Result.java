package xiaodou.weatherforecast.utils;

import xiaodou.weatherforecast.domain.ResponseResult;

/**
 * Created by DOU on 2016/8/11.
 */
public class Result {

    public static String getShowResult(ResponseResult responseResult, int i){

//        Gson gson = new Gson();
//        ResponseResult responseResult = gson.fromJson(content, ResponseResult.class);

//        ArrayList<ResponseResult.ResultBean.FutureBean> futureBeen = responseResult.getResult().getFuture();
        String week = responseResult.getResult().getFuture().get(i).getWeek();
        String date = responseResult.getResult().getFuture().get(i).getDate();
        String temperature = responseResult.getResult().getFuture().get(i).getTemperature();
        String weather = responseResult.getResult().getFuture().get(i).getWeather();
        String wind = responseResult.getResult().getFuture().get(i).getWind();
        String result = week + "  " + date + "  " + temperature + "  " + weather + "  " + wind;
        return result;
    }


}
