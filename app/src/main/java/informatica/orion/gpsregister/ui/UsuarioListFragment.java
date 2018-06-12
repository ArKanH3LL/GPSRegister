package informatica.orion.gpsregister.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.databinding.ListFragmentBinding;
import informatica.orion.gpsregister.model.Usuario;
import informatica.orion.gpsregister.viewmodel.UsuarioListViewModel;

public class UsuarioListFragment extends Fragment {

    public static final String TAG = "UsuarioListViewModel";

    private UsuarioAdapter mUsuarioAdapter;

    private ListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstancesState){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mUsuarioAdapter = new UsuarioAdapter(mUsuarioClickCallback);
        mBinding.usuariosList.setAdapter(mUsuarioAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final UsuarioListViewModel viewModel = ViewModelProviders.of(this).get(UsuarioListViewModel.class);

        subscribeUi(viewModel);
    }

    public void subscribeUi(UsuarioListViewModel viewModel){
        viewModel.getUsuarios().observe(this, new Observer<List<UsuarioEntity>>() {
            @Override
            public void onChanged(@Nullable List<UsuarioEntity> myUsuarios) {
                if (myUsuarios != null){
                    mBinding.setIsLoading(false);
                    mUsuarioAdapter.setUsuarioList(myUsuarios);
                }else {
                    mBinding.setIsLoading(true);
                }
                mBinding.executePendingBindings();
            }
        });
    }

    private final UsuarioClickCallback mUsuarioClickCallback = new UsuarioClickCallback() {
        @Override
        public void onClick(Usuario usuario) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((UsuariosShow)getActivity()).show(usuario);
            }
        }
    };

}
