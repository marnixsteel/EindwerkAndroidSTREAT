package steel.marnix.eindwerkandroidstreats.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import steel.marnix.eindwerkandroidstreats.model.StreetArt;

/**
 * Created by mazze on 19/03/2018.
 */

@Dao
public interface StreetArtDAO {

    @Insert
    void insert (StreetArt streetArt);

    @Query("SELECT * FROM StreetArt")
    List<StreetArt> getAllStreetArt();

    @Update
    void update (StreetArt streetArt);
}
