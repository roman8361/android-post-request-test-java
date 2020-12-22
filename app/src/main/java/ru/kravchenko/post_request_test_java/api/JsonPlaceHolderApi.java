package ru.kravchenko.post_request_test_java.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.kravchenko.post_request_test_java.entity.Post;
import ru.kravchenko.post_request_test_java.entity.RequestMobileDto;
import ru.kravchenko.post_request_test_java.entity.ResponseMobileDto;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/1")
    Call<Post> getPost();

    @GET("simple")
    Call<String> getSimple();

    @GET("guest")
    Call<String> getGuest();

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @POST("hello")
    Call<ResponseMobileDto> createPostMobile(@Body RequestMobileDto requestMobileDto);

    @POST("activateCodeUrl")
    Call<ResponseMobileDto> createPostMobileActivateCode(@Body RequestMobileDto requestMobileDto);

}
