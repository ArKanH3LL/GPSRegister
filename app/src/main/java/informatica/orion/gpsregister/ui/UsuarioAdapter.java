package informatica.orion.gpsregister.ui;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import informatica.orion.gpsregister.databinding.UsuarioItemBinding;
import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.model.Usuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    List<? extends Usuario> mUsuarioList;

    @Nullable
    private final UsuarioClickCallback mUsuarioClickCallback;

    public UsuarioAdapter(@Nullable UsuarioClickCallback clickCallback){
        mUsuarioClickCallback = clickCallback;
    }

    public void setUsuarioList(final List<? extends Usuario> usuarioList){
        if (mUsuarioList == null){
            mUsuarioList = usuarioList;
            notifyItemRangeInserted(0,usuarioList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mUsuarioList.size();
                }

                @Override
                public int getNewListSize() {
                    return usuarioList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mUsuarioList.get(oldItemPosition).getId()==
                            usuarioList.get(newItemPosition).getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Usuario newUsuario = usuarioList.get(newItemPosition);
                    Usuario oldUsuario = mUsuarioList.get(oldItemPosition);
                        return newUsuario.getId()==oldUsuario.getId()
                                && Objects.equals(newUsuario.getNombre(),oldUsuario.getNombre())
                                && Objects.equals(newUsuario.getCedula(),oldUsuario.getCedula())
                                && Objects.equals(newUsuario.getUnidad(),oldUsuario.getUnidad())
                                && Objects.equals(newUsuario.getEmpresa(),oldUsuario.getEmpresa());
                }
                });
            mUsuarioList = usuarioList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        UsuarioItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.usuario_item,
                        parent,false);
        binding.setCallback(mUsuarioClickCallback);
        return new UsuarioViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position){
        holder.binding.setUsuario(mUsuarioList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount(){
        return mUsuarioList == null ? 0 : mUsuarioList.size();
    }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder{
        final UsuarioItemBinding binding;
        public UsuarioViewHolder(UsuarioItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


