package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mazze on 21/03/2018.
 */

@Entity
public class FoodTruck implements Serializable{

    @PrimaryKey @NonNull
    private String id;
    private String name, location;
    private double latitude, longitude;


    public FoodTruck(){
    }

    @Ignore
    public FoodTruck(@NonNull String id, String name, String location, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.location = location + "\n" +"Quisque molestie posuere arcu non interdum. Quisque at sem vitae ante consectetur venenatis. Duis tincidunt orci ligula, convallis pellentesque sem sollicitudin sed. Fusce sit amet quam vitae lorem imperdiet dapibus. Integer venenatis vestibulum lacus, at molestie tortor tincidunt sit amet. Donec feugiat, nisl id tincidunt posuere, velit justo efficitur est, non hendrerit ante mauris eget erat. Suspendisse id ullamcorper diam, non convallis dolor. Quisque nibh libero, ultricies at lacinia sit amet, viverra id sem. Suspendisse eget ornare sem. Vivamus in congue augue, eu efficitur magna. Nullam fermentum quis nisi et gravida.";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
