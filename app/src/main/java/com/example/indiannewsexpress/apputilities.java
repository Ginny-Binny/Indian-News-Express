package com.example.indiannewsexpress;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apputilities {
    public static Retrofit retrofit=null;

    public static Appinterface getAppinterface()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(Appinterface.base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(Appinterface.class);
    }

}
