package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import informatica.orion.gpsregister.dao.daoUsuarios;
import informatica.orion.gpsregister.dao.daoWaypoints;
import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.entity.entWaypoints;
import informatica.orion.gpsregister.database.dbGPSRegister;


public class rprGPSRegister {

    private daoUsuarios mUsuariosDAO;
    private LiveData<List<entUsuarios>> mAllUsuarios;

    private daoWaypoints mWaypointsDAO;
    private LiveData<List<entWaypoints>> mAllWaypoints;


    rprGPSRegister(Application application) {
        dbGPSRegister db = dbGPSRegister.getDatabase(application);

        mUsuariosDAO = db.UsuariosDao();
        mAllUsuarios = mUsuariosDAO.getAllUsuarios();

        mWaypointsDAO = db.WaypointsDao();
        mAllWaypoints = mWaypointsDAO.getAllWaypoints();
    }

    LiveData<List<entUsuarios>> getAllUsuarios() {
        return mAllUsuarios;
    }
    public void insert (entUsuarios usuarios) {
        new insertAsyncTask(mUsuariosDAO).execute(usuarios);
    }

    LiveData<List<entWaypoints>> getmAllWaypoints() {
        return mAllWaypoints;
    }
    public void insert (entWaypoints waypoints) {
        new insertAsyncTaskw(mWaypointsDAO).execute(waypoints);
    }

    private static class insertAsyncTask extends AsyncTask<entUsuarios, Void, Void> {

        private daoUsuarios mAsyncTaskDao;

        insertAsyncTask(daoUsuarios dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final entUsuarios... usuarios) {
            mAsyncTaskDao.insert(usuarios[0]);
            return null;
        }
    }

    private static class insertAsyncTaskw extends AsyncTask<entWaypoints, Void, Void> {

        private daoWaypoints mAsyncTaskDaow;

        insertAsyncTaskw(daoWaypoints daow) {
            mAsyncTaskDaow = daow;
        }

        @Override
        protected Void doInBackground(final entWaypoints... waypoints) {
            mAsyncTaskDaow.insert(waypoints[0]);
            return null;
        }
    }
}
