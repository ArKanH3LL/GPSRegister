package informatica.orion.gpsregister.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import informatica.orion.gpsregister.dao.daoUsuarios;
import informatica.orion.gpsregister.dao.daoWaypoints;
import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.entity.entWaypoints;

@Database(version = 1, entities = {entUsuarios.class,entWaypoints.class})

public abstract class dbGPSRegister extends RoomDatabase {

    public abstract daoUsuarios UsuariosDao();
    public abstract daoWaypoints WaypointsDao();

    private static dbGPSRegister INSTANCE;

    public static dbGPSRegister getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (dbGPSRegister.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            dbGPSRegister.class, "dbGPSRegister")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}







