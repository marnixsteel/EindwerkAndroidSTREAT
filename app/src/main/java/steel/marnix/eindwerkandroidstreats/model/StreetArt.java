package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mazze on 19/03/2018.
 */

@Entity
public class StreetArt implements Serializable {

    @PrimaryKey @NonNull
    private String id;
    private String artistName, description, imageUrl, category;
    private double latitude, longitude;

    public StreetArt(){
    }

    @Ignore
    public StreetArt(@NonNull String id, String artistName, String description, String imageID, String category, double latitude, double longitude) {
        this.id = id;
        this.artistName = artistName;
        this.description = description;
        this.imageUrl = "https://opendata.brussel.be/explore/dataset/streetart/files/" + imageID +"/300/";
        this.category = category;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
