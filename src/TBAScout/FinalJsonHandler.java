package TBAScout;

import com.google.gson.Gson;

import scoutPojo.*;

public class FinalJsonHandler {
    Gson gson = new Gson();

    // "/status" path
    public StatusPojo handleStatusJson(String json) {
        StatusPojo statusPojo = null;
        if (!json.equals("json unavailable") && !json.equals("error")) {
            statusPojo = gson.fromJson(json, StatusPojo.class);
        } else {
            statusPojo = null;
        }
        // System.out.println(statusPojo.getCurrent_season());
        return statusPojo;
    }

    // "/team/{team_key}/simple" path
    public SimpleTeamPojo handleTeamJson(String json) {
        SimpleTeamPojo simpleTeamPojo = null;
        if (!json.equals("json unavailable") && !json.equals("error")) {
            simpleTeamPojo = gson.fromJson(json, SimpleTeamPojo.class);
        } else {
            simpleTeamPojo = null;
        }
        return simpleTeamPojo;
    }

}