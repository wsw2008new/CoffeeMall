package com.coffee.coffeemall.model.http;




import com.coffee.coffeemall.model.bean.HeaderBean;
import com.coffee.coffeemall.model.bean.RecommendBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface HttpService {
//banner/query?type=1
    //    public static final String BASE_URL="http://112.124.22.238:8081/course_api/";
    @GET("banner/query")
    Call<List<HeaderBean>> getHeaderData(@Query("type") String type);
    @GET("campaign/recommend")
    Call<List<RecommendBean>> getRecommendData();
}
