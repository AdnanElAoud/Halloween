package com.app.mob.halloween;


import com.app.mob.halloween.Remote.APIServise;
import com.app.mob.halloween.Remote.FCMRetrofitClient;

/**
 * Created by adnan on 2/27/2018.
 */

public class Common {
   // public static User currentUser;

    public static String topicHalloween = "halloween";


    public static final String UPDATE ="Update";
    public static final String DELETE ="Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String fcmUrl ="https://fcm.googleapis.com/";

    public static APIServise getFCMClient(){
        return FCMRetrofitClient.getClient(fcmUrl).create(APIServise.class);

    }

}
