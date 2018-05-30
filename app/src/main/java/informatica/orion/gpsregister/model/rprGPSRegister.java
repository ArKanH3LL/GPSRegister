package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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
        mAllUsuarios = mUsuariosDAO.getAll();

        mWaypointsDAO = db.WaypointsDao();
        mAllWaypoints = mWaypointsDAO.getAll();
    }

    LiveData<List<entUsuarios>> getAllUsuarios() {
        return mAllUsuarios;
    }
    public void insert (entUsuarios entUsuarios) {
        new insertAsyncTask(mUsuariosDAO).execute(entUsuarios);
    }

    LiveData<List<entWaypoints>> getmAllWaypoints() {
        return mAllWaypoints;
    }
    public void insert (entWaypoints entWaypoints) {new insertAsyncTaskw(mWaypointsDAO).execute(entWaypoints);
    }

    private static class insertAsyncTask extends AsyncTask<entUsuarios, Void, Void> {

        private daoUsuarios mAsyncTaskDao;

        insertAsyncTask(daoUsuarios dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final entUsuarios... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskw extends AsyncTask<entWaypoints, Void, Void> {

        private daoWaypoints mAsyncTaskDaow;

        insertAsyncTaskw(daoWaypoints dao) {
            mAsyncTaskDaow = dao;
        }

        @Override
        protected Void doInBackground(final entWaypoints... params) {
            mAsyncTaskDaow.insert(params[0]);
            return null;
        }
    }
}
