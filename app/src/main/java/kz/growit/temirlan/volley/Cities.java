package kz.growit.temirlan.volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Temirlan on 20.11.2015.
 */
public class Cities {
    int Id;
    String Name, State, Latitude, Longitude, LastUpdate, CreatedDate;
    boolean Enabled;

    public Cities(JSONObject jsonObject){
        try {
            Id = jsonObject.getInt("Id");
            Name = jsonObject.getString("Name");
            State = jsonObject.getString("State");
            Latitude = jsonObject.getString("Latitude");
            Longitude = jsonObject.getString("Longitude");
            LastUpdate = jsonObject.getString("LastUpdate");
            CreatedDate = jsonObject.getString("CreatedDate");
            Enabled = jsonObject.getBoolean("Enabled");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getState() {
        return State;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public boolean isEnabled() {
        return Enabled;
    }
}
