package informatica.orion.gpsregister.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.databinding.UsuarioFragmentBinding;
import informatica.orion.gpsregister.database.entity.WaypointEntity;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.model.Waypoint;
import informatica.orion.gpsregister.viewmodel.UsuarioViewModel;

import java.util.List;

public class UsuarioFragment extends Fragment {

    private static final String KEY_USER_ID = "usuario_id";

    private UsuarioFragmentBinding mBinding;

    private WaypointAdapter mWaypointAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.usuario_fragment, container, false);

        // Create and set the adapter for the RecyclerView.
        mWaypointAdapter = new WaypointAdapter(mWaypointClickCallback);
        mBinding.waypointList.setAdapter(mWaypointAdapter);
        return mBinding.getRoot();
    }

    private final WaypointClickCallback mWaypointClickCallback = new WaypointClickCallback() {
        @Override
        public void onClick(Waypoint waypoint) {
            // no-op

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UsuarioViewModel.Factory factory = new UsuarioViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_USER_ID));

        final UsuarioViewModel model = ViewModelProviders.of(this, factory)
                .get(UsuarioViewModel.class);

        mBinding.setUsuarioViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final UsuarioViewModel model) {

        // Observe product data
        model.getObservableUsuario().observe(this, new Observer<UsuarioEntity>() {
            @Override
            public void onChanged(@Nullable UsuarioEntity usuarioEntity) {
                model.setUsuario(usuarioEntity);
            }
        });

        // Observe comments
        model.getWaypoints().observe(this, new Observer<List<WaypointEntity>>() {
            @Override
            public void onChanged(@Nullable List<WaypointEntity> waypointEntities) {
                if (waypointEntities != null) {
                    mBinding.setIsLoading(false);
                    mWaypointAdapter.setWaypointList(waypointEntities);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    /** Creates product fragment for specific product ID */
    public static UsuarioFragment forUsuario(int usuarioId) {
        UsuarioFragment fragment = new UsuarioFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_USER_ID, usuarioId);
        fragment.setArguments(args);
        return fragment;
    }
}
