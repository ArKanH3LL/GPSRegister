package informatica.orion.gpsregister.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.entity.entUsuarios;

public class adpUsuarios extends RecyclerView.Adapter<adpUsuarios.UsuariosViewHolder> {

    private final LayoutInflater mInflater;
    private List<entUsuarios> mUsuarios; // Cached copy of entUsuarios

    public adpUsuarios(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.reciclerview_item, parent, false);
        return new UsuariosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {
        if (mUsuarios != null) {
            entUsuarios current = mUsuarios.get(position);
            holder.usuariosItemView.setText(current.getNombre());
        } else {
            // Covers the case of data not being ready yet.
            holder.usuariosItemView.setText("No Word");
        }
    }

    public void setUsuarios(List<entUsuarios> entUsuarios){
        mUsuarios = entUsuarios;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mUsuarios != null)
            return mUsuarios.size();
        else return 0;
    }

    class UsuariosViewHolder extends RecyclerView.ViewHolder {
        private final TextView usuariosItemView;

        private UsuariosViewHolder(View itemView) {
            super(itemView);
            usuariosItemView = itemView.findViewById(R.id.textView);
        }
    }
}
