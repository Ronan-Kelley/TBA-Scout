package TBAScout;

import com.google.gson.Gson;

import scoutPojo.*;

public class FinalJsonHandler {
    Gson gson = new Gson();

    // "/status" path
    public StatusPojo handleStatusJson(String json) throws ConnectionException {
        StatusPojo statusPojo = null;
        if (!json.equals("json unavailable") && !json.equals("error")) {
            statusPojo = gson.fromJson(json, StatusPojo.class);
        } else {
            statusPojo = null;
        }

        if (new TBAGetRequest().getJson().equals("error") || statusPojo == null) {
            throw new ConnectionException("couldn't reach TBA!");
        }

        // System.out.println(statusPojo.getCurrent_season());
        return statusPojo;
    }

    // "/team/{team_key}/simple" path
    public SimpleTeamPojo handleTeamJson(String json) throws ConnectionException {
        SimpleTeamPojo simpleTeamPojo = null;
        if (!json.equals("json unavailable") && !json.equals("error")) {
            simpleTeamPojo = gson.fromJson(json, SimpleTeamPojo.class);
        } else {
            simpleTeamPojo = null;
        }

        if (new TBAGetRequest().getJson().equals("error") || simpleTeamPojo == null) {
            throw new ConnectionException("couldn't reach TBA!");
        }

        return simpleTeamPojo;
    }

    // "/team/{team_key}/events"
    public EventsPojo[] handleEventsPojo(String json) throws ConnectionException {
        EventsPojo[] eventsPojo = null;
        if (!json.equals("json unavailable") && !json.equals("error")) {
            eventsPojo = gson.fromJson(json, EventsPojo[].class);
        } else {
            eventsPojo = null;
        }

        if (new TBAGetRequest().getJson().equals("error") || eventsPojo == null) {
            throw new ConnectionException("couldn't reach TBA!");
        }

        return eventsPojo;
    }

    public SimpleMatches[] handleMatchesPojo(String json) throws ConnectionException {
        SimpleMatches[] simpleMatches = null;

        if (!json.equals("json unavailable") && !json.equals("error")) {
            simpleMatches = gson.fromJson(json, SimpleMatches[].class);
        } else {
            simpleMatches = null;
        }

        if (new TBAGetRequest().getJson().equals("error") || simpleMatches == null) {
            throw new ConnectionException("couldn't reach TBA!");
        }

        return simpleMatches;
    }

}