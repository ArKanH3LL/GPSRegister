package informatica.orion.gpsregister.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import informatica.orion.gpsregister.GPSRegisterApp;
import informatica.orion.gpsregister.DataRepository;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.database.entity.WaypointEntity;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {

    private final LiveData<UsuarioEntity> mObservableUsuario;

    public ObservableField<UsuarioEntity> usuario = new ObservableField<>();

    private final int mUsuarioId;

    private final LiveData<List<WaypointEntity>> mObservableWaypoints;

    public UsuarioViewModel(@NonNull Application application, DataRepository repository,
                            final int usuarioId) {
        super(application);
        mUsuarioId = usuarioId;

        mObservableWaypoints = repository.loadWaypoints(mUsuarioId);
        mObservableUsuario = repository.loadUsuario(mUsuarioId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<List<WaypointEntity>> getWaypoints() {
        return mObservableWaypoints;
    }

    public LiveData<UsuarioEntity> getObservableUsuario() {
        return mObservableUsuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario.set(usuario);
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mUsuarioId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int usuarioId) {
            mApplication = application;
            mUsuarioId = usuarioId;
            mRepository = ((GPSRegisterApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new UsuarioViewModel(mApplication, mRepository, mUsuarioId);
        }
    }
}
