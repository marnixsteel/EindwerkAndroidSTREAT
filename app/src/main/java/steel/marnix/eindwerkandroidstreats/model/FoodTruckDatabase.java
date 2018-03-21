package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import steel.marnix.eindwerkandroidstreats.dao.FoodTruckDAO;

/**
 * Created by mazze on 21/03/2018.
 */

@Database(entities = {FoodTruck.class}, version = 1)
public abstract class FoodTruckDatabase extends RoomDatabase {

    private static FoodTruckDatabase instance;
    private static SharedPreferences sp;

    public static FoodTruckDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, FoodTruckDatabase.class, "foodtruck.db").allowMainThreadQueries().build();

            sp = PreferenceManager.getDefaultSharedPreferences(context);
            if (sp.getBoolean("foodtruck_not_loaded", true))
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
                        String name = (fields.has("monday")) ? fields.getString("monday") : (fields.has("tuesday")) ? fields.getString("tuesday") : "";

                        FoodTruck mFoodTruck = new FoodTruck(
                                foodTruckId,
                                name,
                                locatie, coordinates.getDouble(0),
                                coordinates.getDouble(1)
                        );
                        instance.getDAO().insert(mFoodTruck);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sp.edit().putBoolean("foodtruck_not_loaded", false).commit();

            }
        });
        backGroundThread.start();
    }

    public abstract FoodTruckDAO getDAO();

}



