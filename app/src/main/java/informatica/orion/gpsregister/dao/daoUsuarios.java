package informatica.orion.gpsregister.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;

@Dao
public interface daoUsuarios {
    @Query("SELECT * FROM entUsuarios")
    LiveData<List<entUsuarios>> getAll();

    @Query("SELECT * FROM entUsuarios WHERE uid IN (:userId)")
    List<entUsuarios> getAllById(int[] userId);

    @Query("SELECT * FROM entUsuarios WHERE cedula LIKE :cedula")
    List<entUsuarios> findByCedula(String cedula);

    @Insert
    void insert(entUsuarios entUsuarios);

    @Update
    void update(entUsuarios entUsuarios);

    @Delete
    void delete(entUsuarios usuario);
}