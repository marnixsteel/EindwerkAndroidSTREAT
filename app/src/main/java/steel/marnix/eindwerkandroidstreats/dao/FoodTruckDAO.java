package steel.marnix.eindwerkandroidstreats.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import steel.marnix.eindwerkandroidstreats.model.FoodTruck;

/**
 * Created by mazze on 21/03/2018.
 */


@Dao
public interface FoodTruckDAO {

    @Insert
    void insert(FoodTruck foodTruck);

    @Query("SELECT * FROM FoodTruck")
    List<FoodTruck> getAllFoodTrucks();

    @Update
    void update (FoodTruck foodTruck);
}


