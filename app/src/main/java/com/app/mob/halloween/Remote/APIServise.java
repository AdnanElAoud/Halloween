package com.app.mob.halloween.Remote;


import com.app.mob.halloween.Notification.MyResponse;
import com.app.mob.halloween.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by adnan on 3/20/2018.
 */

public interface APIServise {
    @Headers(

            {
                    "Content-Type:application/json",
                    "authorization:key=AAAAZalmjMk:APA91bFHbA-rPDvuZlb2l7cvZWVY_tvdJfnLO0CzWS6cvbJkUv_3c-sgC5R32ie0BFI6bvdPGGkpzT97whcmTi3ZF9MukOOja8_3SPoLLaCDc68VZe34PyYUoJOe8LR6Pafpf70iVvJL"
            }
    )
    @POST("fcm/send")
    Call<MyResponse>sendNotification(@Body Sender body);
}


































































