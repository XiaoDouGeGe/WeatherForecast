package xiaodou.weatherforecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.net.URLEncoder;

import xiaodou.weatherforecast.domain.ResponseResult;
import xiaodou.weatherforecast.utils.Result;

public class MainActivity extends Activity {

    private final String subUrl = "http://v.juhe.cn/weather/index?format=2";
    private final String key = "71efbb690b9bc9a389ad90502306c8cd";

    private EditText etCityName;
    private Button btnSearch;

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;
    private TextView tvSeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCityName = (EditText) findViewById(R.id.et_cityName);
        btnSearch = (Button) findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new MySearchListener());

        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tvThree = (TextView) findViewById(R.id.tv_three);
        tvFour = (TextView) findViewById(R.id.tv_four);
        tvFive = (TextView) findViewById(R.id.tv_five);
        tvSix = (TextView) findViewById(R.id.tv_six);
        tvSeven = (TextView) findViewById(R.id.tv_seven);

    }

    public void btn_chooseCity(View v){
        Intent intent = new Intent(MainActivity.this, CityActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        String choosedCityName = data.getStringExtra("choosedCityItem");
        etCityName.setText(choosedCityName);
    }

    public class MySearchListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String cityNameForSearch = etCityName.getText().toString().trim();

            if(cityNameForSearch.equals("")){
                Toast.makeText(MainActivity.this, "请选择城市", Toast.LENGTH_SHORT).show();
            }else{
                String url = subUrl + "&cityname=" + URLEncoder.encode(cityNameForSearch) + "&key=" + key;

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        super.onSuccess(statusCode, content);

                        Gson gson = new Gson();
                        ResponseResult responseResult = gson.fromJson(content, ResponseResult.class);
//                        String weather = responseResult.getResult().getToday().getWeather();
//                        Toast.makeText(MainActivity.this, "今天的天气是：" + weather, Toast.LENGTH_LONG).show();

                        String one = Result.getShowResult(responseResult, 0);
                        String two = Result.getShowResult(responseResult, 1);
                        String three = Result.getShowResult(responseResult, 2);
                        String four = Result.getShowResult(responseResult, 3);
                        String five = Result.getShowResult(responseResult, 4);
                        String six = Result.getShowResult(responseResult, 5);
                        String seven = Result.getShowResult(responseResult, 6);
                        tvOne.setText(one);
                        tvTwo.setText(two);
                        tvThree.setText(three);
                        tvFour.setText(four);
                        tvFive.setText(five);
                        tvSix.setText(six);
                        tvSeven.setText(seven);

//                        System.out.println(content);
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                });
            }



        }
    }


}
