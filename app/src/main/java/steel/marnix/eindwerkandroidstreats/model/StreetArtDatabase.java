package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import steel.marnix.eindwerkandroidstreats.dao.StreetArtDAO;

/**
 * Created by mazze on 19/03/2018.
 */

@Database(entities = {StreetArt.class}, version = 1)
public abstract class StreetArtDatabase extends RoomDatabase {

    private static StreetArtDatabase instance;
    private static SharedPreferences sp;

    public static StreetArtDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, StreetArtDatabase.class, "streetart.db").allowMainThreadQueries().build();

            sp = PreferenceManager.getDefaultSharedPreferences(context);
            if (sp.getBoolean("first_time", true))
                createData();
        }
        return instance;
    }

    private static void createData() {
        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=street-art0")
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();

                    String streetArtData = response.body().string();
                    JSONObject rootObject = new JSONObject(streetArtData);

                    JSONArray jsonArray = rootObject.getJSONArray("records");

                    int arraySize = jsonArray.length();

                    for (int i = 0; i < arraySize; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject fields = jsonObject.getJSONObject("fields");
                        JSONArray coordinates = fields.getJSONArray("geocoordinates");

                        String naam = fields.getString("name_of_the_artist");
                        String verduidelijking = (fields.has("verduidelijking")) ? fields.getString("verduidelijking") : "";

                        StreetArt streetArt = new StreetArt(
                                jsonObject.getString("recordid"),
                                naam,
                                verduidelijking,
                                "street art",
                                coordinates.getDouble(0),
                                coordinates.getDouble(1)
                        );

                        instance.getDAO().insert(streetArt);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                sp.edit().putBoolean("first_time", false).commit();
            }
        });
        backGroundThread.start();


    }

    public abstract StreetArtDAO getDAO();

}


