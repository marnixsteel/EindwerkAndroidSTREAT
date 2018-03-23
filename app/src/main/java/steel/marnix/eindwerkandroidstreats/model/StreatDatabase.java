package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import steel.marnix.eindwerkandroidstreats.dao.FoodTruckDAO;
import steel.marnix.eindwerkandroidstreats.dao.StreetArtDAO;

/**
 * Created by mazze on 21/03/2018.
 */

@Database(entities = {FoodTruck.class, StreetArt.class}, version = 1, exportSchema = false)
public abstract class StreatDatabase extends RoomDatabase {

    private static StreatDatabase instance;
    private static SharedPreferences sp;

    public static StreatDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, StreatDatabase.class, "foodtruck.db").allowMainThreadQueries().build();

            sp = PreferenceManager.getDefaultSharedPreferences(context);
            if (sp.getBoolean("foodtruck_not_loaded", true)) {
                createFoodData(context);
                createArtData(context); 
            }
        }
        return instance;
    }

    private static void createFoodData(final Context context) {
        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=food-trucks&rows=18")
                            .get()
                            .build();
                    Response response = client.newCall(request).execute();

                    String foodTruckData = response.body().string();
                    JSONObject rootObject = new JSONObject(foodTruckData);

                    JSONArray jsonArray = rootObject.getJSONArray("records");

                    int arraySize = jsonArray.length();

                    for (int i = 0; i < arraySize; i++) {
                        JSONObject foodTruck = jsonArray.getJSONObject(i);
                        String foodTruckId = foodTruck.getString("recordid");
                        JSONObject fields = foodTruck.getJSONObject("fields");
                        String locatie = fields.getString("locatie");
                        JSONArray coordinates = fields.getJSONArray("coordonnees_wgs84");
                        String name =
                                (fields.has("monday")) ? fields.getString("monday") :
                                        (fields.has("tuesday")) ? fields.getString("tuesday") :
                                                (fields.has("wednesday")) ? fields.getString("wednesday") :
                                                        (fields.has("thursday")) ? fields.getString("thursday") :
                                                                (fields.has("friday")) ? fields.getString("friday") :
                                                                        (fields.has("saturday")) ? fields.getString("saturday") :
                                                                                (fields.has("sunday")) ? fields.getString("sunday") : locatie;

                        FoodTruck mFoodTruck = new FoodTruck(
                                foodTruckId,
                                name,
                                locatie, coordinates.getDouble(0),
                                coordinates.getDouble(1)
                        );
                        instance.getFoodDAO().insert(mFoodTruck);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("klaar");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                sp.edit().putBoolean("foodtruck_not_loaded", false).commit();

            }

        });
        backGroundThread.start();
    }

    private static void createArtData(final Context context) {
        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=street-art0&rows=23")
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

                        instance.getArtDAO().insert(streetArt);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("klaar");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                sp.edit().putBoolean("streetart_not_loaded", false).commit();
            }
        });
        backGroundThread.start();
    }
    public abstract FoodTruckDAO getFoodDAO();
    public abstract StreetArtDAO getArtDAO();
}



