package informatica.orion.gpsregister.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;

@Dao
public interface daoUsuarios {
    @Query("SELECT * FROM entUsuarios")
    LiveData<List<entUsuarios>> getAllUsuarios();

    @Query("SELECT * FROM entUsuarios WHERE uid IN (:userId)")
    List<entUsuarios> getAllById(int[] userId);

    @Query("SELECT * FROM entUsuarios WHERE cedula LIKE :cedula")
    List<entUsuarios> findByCedula(String cedula);

    @Insert
    void insert(entUsuarios... usuarios);

    @Update
    void update(entUsuarios... usuarios);

    @Delete
    void delete(entUsuarios usuario);
}