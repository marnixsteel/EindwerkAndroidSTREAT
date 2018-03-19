package steel.marnix.eindwerkandroidstreats.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mazze on 19/03/2018.
 */

public class StreetArt {

    private String id, name, artistName, description, category;
    private LatLng artCoord;

    public StreetArt(String id, String name, String artistName, String description, String category, LatLng artCoord) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.description = description;
        this.category = category;
        this.artCoord = artCoord;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LatLng getArtCoord() {
        return artCoord;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
