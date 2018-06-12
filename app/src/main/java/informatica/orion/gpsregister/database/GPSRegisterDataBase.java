package informatica.orion.gpsregister.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import informatica.orion.gpsregister.AppExecutors;
import informatica.orion.gpsregister.database.converter.DateConverter;
import informatica.orion.gpsregister.database.dao.UsuariosDAO;
import informatica.orion.gpsregister.database.dao.WaypointsDAO;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.database.entity.WaypointEntity;

import java.util.List;

@Database(entities = {UsuarioEntity.class,WaypointEntity.class},version = 1)
@TypeConverters(DateConverter.class)

public abstract class GPSRegisterDataBase extends RoomDatabase {

    private static GPSRegisterDataBase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "GPSRegister";

    public abstract UsuariosDAO usuariosDAO();
    public abstract WaypointsDAO waypointsDAO();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static GPSRegisterDataBase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (GPSRegisterDataBase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static GPSRegisterDataBase buildDatabase(final Context appContext,
             final AppExecutors executors){
        return Room.databaseBuilder(appContext, GPSRegisterDataBase.class, DATABASE_NAME)
                .addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db){
                super.onCreate(db);
                executors.diskIO().execute(() -> {
                    addDelay();
                    GPSRegisterDataBase dataBase = GPSRegisterDataBase.getInstance(appContext, executors);
                    List<UsuarioEntity> usuarios = DataGenerator.generateUsuarios();
                    List<WaypointEntity> waypoints = DataGenerator.generateWaypointsForUsuarios(usuarios);
                    insertData(dataBase, usuarios, waypoints);
                    dataBase.setDatabaseCreated();
                });
            }
        }).build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final GPSRegisterDataBase database, final List<UsuarioEntity> usuarios,
                                   final List<WaypointEntity> waypoints) {
        database.runInTransaction(() -> {
            database.usuariosDAO().insertALL(usuarios);
            database.waypointsDAO().insertALL(waypoints);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
    }









