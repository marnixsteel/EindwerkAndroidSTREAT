package steel.marnix.eindwerkandroidstreats.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.LatLng;

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
            if(sp.getBoolean("first_time", true))
                createFakeData();
        }
        return instance;
    }

    private static void createFakeData() {
        StreetArt mannekenPeace = new StreetArt("fa518a1373a3c87687f34bb5992bb08facd8bdcf", "Manneken Peace", "H.M.I./Collectif CNN", "Op de puntgevel aan de zijkant van de Eikstraat 19", "STREETART", 50.8440974, 4.3488076);LatLng artCoord2 = new LatLng(50.839982, 4.36068);
        StreetArt koolKoor = new StreetArt("d0bb70f35c72f7bdc4af4f3cd8900f88f53f5ab3", "Untitled", "Kool Koor", "Naamsestraat 64", "STREETART", 50.839982, 4.36068);
        StreetArt parole = new StreetArt("a5bfde5812fef348c35981529559c6e68ff71ee0", "Untitled", "Parole", "Kruispunt Naamsestraat x BaudetStraat", "STREETART", 50.839964, 4.360147);

        instance.getDAO().insert(mannekenPeace);
        instance.getDAO().insert(koolKoor);
        instance.getDAO().insert(parole);

        sp.edit().putBoolean("first_time", false).commit();
    }

    public abstract StreetArtDAO getDAO();
}


