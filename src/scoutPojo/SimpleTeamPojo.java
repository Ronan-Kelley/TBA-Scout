package scoutPojo;

/**
 * file generated by pojo.sodhanalibrary.com
 * 
 * file intended to contain info from bluealliance APIV3's /team/{team_key}/simple path
 */

public class SimpleTeamPojo
{
    private String state_prov;

    private String team_number;

    private String nickname;

    private String name;

    private String key;

    private String country;

    private String city;

    public String getState_prov ()
    {
        return state_prov;
    }

    public void setState_prov (String state_prov)
    {
        this.state_prov = state_prov;
    }

    public String getTeam_number ()
    {
        return team_number;
    }

    public void setTeam_number (String team_number)
    {
        this.team_number = team_number;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [state_prov = "+state_prov+", team_number = "+team_number+", nickname = "+nickname+", name = "+name+", key = "+key+", country = "+country+", city = "+city+"]";
    }
}