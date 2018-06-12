package informatica.orion.gpsregister;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import informatica.orion.gpsregister.database.GPSRegisterDataBase;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.database.entity.WaypointEntity;

public class DataRepository  {

    private static DataRepository sInstance;

    private final GPSRegisterDataBase mDatabase;

    private MediatorLiveData<List<UsuarioEntity>> mObservableUsuarios;

    private DataRepository(final GPSRegisterDataBase database){
        mDatabase = database;
        mObservableUsuarios = new MediatorLiveData<>();

        mObservableUsuarios.addSource(mDatabase.usuariosDAO().loadAllUsuarios(),
                usuarioEntities -> {
                if (mDatabase.getDatabaseCreated().getValue()!=null){
                    mObservableUsuarios.postValue(usuarioEntities);
                }
            });
    }

    public static DataRepository getInstance(final GPSRegisterDataBase database){
        if (sInstance==null){
            synchronized (DataRepository.class){
                if (sInstance==null){
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<UsuarioEntity>> getUsuarios(){
        return mObservableUsuarios;
    }

    public LiveData<UsuarioEntity> loadUsuario(final int usuarioID){
        return mDatabase.usuariosDAO().loadUsuario(usuarioID);
    }

    public LiveData<List<WaypointEntity>> loadWaypoints(final int usuarioID){
        return mDatabase.waypointsDAO().loadWaypoints(usuarioID);
    }



}
