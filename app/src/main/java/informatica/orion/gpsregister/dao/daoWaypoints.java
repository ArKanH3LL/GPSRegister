package informatica.orion.gpsregister.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import informatica.orion.gpsregister.entity.entWaypoints;

@Dao
public interface daoWaypoints {
    @Query("SELECT * FROM entWaypoints")
    LiveData<List<entWaypoints>> getAllWaypoints();

    @Query("SELECT * FROM entWaypoints WHERE idwp IN (:wpid)")
    List<entWaypoints> getAllWaypointsById(int[] wpid);

    @Query("SELECT * FROM entWaypoints WHERE nis LIKE :nis")
    List<entWaypoints> findWaypointByNis(String nis);

    @Insert
    void insert(entWaypoints... waypoints);

    @Update
    void update(entWaypoints... waypoints);

    @Delete
    void delete(entWaypoints waypoint);
}
