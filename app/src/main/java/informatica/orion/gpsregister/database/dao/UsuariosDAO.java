package informatica.orion.gpsregister.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import informatica.orion.gpsregister.database.entity.UsuarioEntity;

@Dao
public interface UsuariosDAO {
    @Query("SELECT * FROM usuarios")
    LiveData<List<UsuarioEntity>> loadAllUsuarios();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertALL(List<UsuarioEntity> usuarios);

    @Query("SELECT * FROM usuarios WHERE id= :usuarioId")
    LiveData<UsuarioEntity> loadUsuario(int usuarioId);

    @Query("SELECT * FROM usuarios WHERE cedula LIKE :usuarioId")
    UsuarioEntity loadUsuarioSync(int usuarioId);

    @Query("SELECT * FROM usuarios WHERE cedula LIKE :cedula")
    LiveData<UsuarioEntity> findByCedula(String cedula);

    @Update
    void update(UsuarioEntity... usuarios);

    @Delete
    void delete(UsuarioEntity... usuario);
}