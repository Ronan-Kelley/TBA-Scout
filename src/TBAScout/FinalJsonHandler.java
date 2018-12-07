package TBAScout;

import com.google.gson.Gson;

import scoutPojo.*;

public class FinalJsonHandler {
    Gson gson = new Gson();

    public StatusPojo handleStatusJson(String json) {
        StatusPojo statusPojo = gson.fromJson(json, StatusPojo.class);
        System.out.println(statusPojo.getCurrent_season());
        return statusPojo;
    }
}