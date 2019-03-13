package com.dashboard.restservicedashboard.notification;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class NotificationManager {

    private static final String APP_KEY = "ab4g7ak7m1wj3aj97apsarcym8nwpg";
    private static final String USER_KEY = "uhw9z6ai9q47c6d5frzq27o78ifzp6";


    public void sendMessage(String message) {

        Future<HttpResponse<JsonNode>> future = Unirest.post("https://api.pushover.net/1/messages.json")
                .field("token", APP_KEY)
                .field("user", USER_KEY)
                .field("message", message)
                .asJsonAsync(new Callback<JsonNode>() {

                    public void failed(UnirestException e) {
                        System.out.println("The request has failed");
                    }

                    public void completed(HttpResponse<JsonNode> response) {
                        int code = response.getStatus();
                        //Map<String, String> headers = response.getHeaders();
                        //JsonNode body = response.getBody();
                        //InputStream rawBody = response.getRawBody();
                    }

                    public void cancelled() {
                        System.out.println("The request has been cancelled");
                    }

                });
    }
}

