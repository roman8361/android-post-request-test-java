package ru.kravchenko.post_request_test_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kravchenko.post_request_test_java.api.JsonPlaceHolderApi;
import ru.kravchenko.post_request_test_java.entity.Post;
import ru.kravchenko.post_request_test_java.entity.RequestMobileDto;
import ru.kravchenko.post_request_test_java.entity.ResponseMobileDto;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textDate = findViewById(R.id.dateText);
        setDate(textDate);
    }

    public void setDate (TextView view){

//        Date today = Calendar.getInstance().getTime();//getting date
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
    }

    public void onClickButton(View view) throws IOException {
        // выводим сообщение
//        sendGetRequestRetrofit();
//        sendPostRequestRetrofit();
        sendPostMobileRequest();
//        sendGetRequestSimple();
//        sendGetRequestGuest();
    }

    private void sendGetRequestGuest(){
        Toast.makeText(this, "sendGetRequestGuest()", Toast.LENGTH_SHORT).show();
        textView = findViewById(R.id.textView);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<String> call = jsonPlaceHolderApi.getGuest();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                textView.append(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void sendGetRequestSimple(){
        Toast.makeText(this, "sendGetRequestToServer()", Toast.LENGTH_SHORT).show();
        textView = findViewById(R.id.textView);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<String> call = jsonPlaceHolderApi.getSimple();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                textView.append(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void sendGetRequestRetrofit(){
        Toast.makeText(this, "sendGetRequestRetrofit()", Toast.LENGTH_SHORT).show();

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void sendPostMobileRequest(){
        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        RequestMobileDto request = new RequestMobileDto("2222", "1111");

        Call<ResponseMobileDto> call = jsonPlaceHolderApi.createPostMobile(request);
        call.enqueue(new Callback<ResponseMobileDto>() {
            @Override
            public void onResponse(Call<ResponseMobileDto> call, Response<ResponseMobileDto> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                ResponseMobileDto responseMobileDto = response.body();
                textView.setText(responseMobileDto.getMetaMessage());
                System.out.println(responseMobileDto.getGuestName());
                System.out.println(responseMobileDto.getDepartureDate());
                System.out.println(responseMobileDto.getMetaMessage());
                System.out.println(responseMobileDto.getRoomNumber());
            }

            @Override
            public void onFailure(Call<ResponseMobileDto> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void sendPostRequestRetrofit(){
        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Post post = new Post(23, "TITLE", "new TEXT");

        Call<Post> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User id: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }

    private void sendPostRequest() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/hello";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RequestMobileDto requestMobileDto = new RequestMobileDto();
        requestMobileDto.setCodeActivate("1111");
        requestMobileDto.setToken("1111");

        HttpEntity entity = new HttpEntity(objectMapper.writeValueAsString(requestMobileDto), httpHeaders);
        String result = restTemplate.postForObject(url, entity, String.class);

        ResponseMobileDto responseMobileDto = objectMapper.readValue(result, ResponseMobileDto.class);
        System.out.println(responseMobileDto.getMetaMessage());
        System.out.println(responseMobileDto.getGuestName());
    }



//    private HttpPost createJSONPostRequest(String emailAddress, String data) throws JSONException, UnsupportedEncodingException
//    {
//        JSONObject json = new JSONObject();
//        json.put("protocolVersion", "1.0");
//        json.put("emailAddress", emailAddress);
//        json.put("data", data);
//        String jsonString = json.toString();
//
//        HttpPost request = new HttpPost(serviceURLString);
//        request.setHeader("Accept", "application/json");
//        request.setHeader("Content-type", "application/json");
//        request.setEntity(new StringEntity(jsonString));
//
//        return request;
//    }

}
