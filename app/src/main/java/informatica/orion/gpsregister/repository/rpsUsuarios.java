package informatica.orion.gpsregister.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import informatica.orion.gpsregister.dao.daoUsuarios;
import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.database.dbGPSRegister;


public class rpsUsuarios {

    private daoUsuarios mUsuariosDAO;
    private LiveData<List<entUsuarios>> mAllUsuarios;

    public rpsUsuarios(Application application) {
        dbGPSRegister db = dbGPSRegister.getDatabase(application);

        mUsuariosDAO = db.UsuariosDao();
        mAllUsuarios = mUsuariosDAO.getAllUsuarios();
    }

    public LiveData<List<entUsuarios>> getmAllUsuarios() {
        return mAllUsuarios;
    }
    public void insert (entUsuarios usuarios) {
        new insertAsyncTask(mUsuariosDAO).execute(usuarios);
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
}
