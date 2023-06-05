package com.iss.ua.lark.client;

import com.lark.oapi.Client;

public class LarkClient {

    public static void main(String arg[]) throws Exception {
        Client client = Client.newBuilder("appId", "appSecret").build();

    }




}
