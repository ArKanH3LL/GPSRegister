package informatica.orion.gpsregister.repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import informatica.orion.gpsregister.dao.daoWaypoints;
import informatica.orion.gpsregister.entity.entWaypoints;
import informatica.orion.gpsregister.database.dbGPSRegister;


public class rpsWaypoints {

    private daoWaypoints mWaypointsDAO;
    private LiveData<List<entWaypoints>> mAllWaypoints;


    public rpsWaypoints(Application application) {
        dbGPSRegister db = dbGPSRegister.getDatabase(application);

        mWaypointsDAO = db.WaypointsDao();
        mAllWaypoints = mWaypointsDAO.getAllWaypoints();
    }

    public LiveData<List<entWaypoints>> getmAllWaypoints() {
        return mAllWaypoints;
    }
    public void insert (entWaypoints waypoints) {
        new insertAsyncTask(mWaypointsDAO).execute(waypoints);
    }

    private static class insertAsyncTask extends AsyncTask<entWaypoints, Void, Void> {

        private daoWaypoints mAsyncTaskDaow;

        insertAsyncTask(daoWaypoints daow) {
            mAsyncTaskDaow = daow;
        }

        @Override
        protected Void doInBackground(final entWaypoints... waypoints) {
            mAsyncTaskDaow.insert(waypoints[0]);
            return null;
        }
    }
}
