package informatica.orion.gpsregister.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.entity.entUsuarios;

public class adpUsuarios extends RecyclerView.Adapter<adpUsuarios.UsuariosViewHolder> {

    ArrayList<entUsuarios> users;

    public adpUsuarios(ArrayList<entUsuarios> users) {
        this.users = users;
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclerview_item, parent, false);
        return new UsuariosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {
        if (users != null) {
            holder.nombreItemView.setText(users.get(position).getNombre());
            holder.cedulaItemView.setText(users.get(position).getCedula());

        } else {
            // Covers the case of data not being ready yet.
            holder.nombreItemView.setText("No hay Usuarios Registrados");
        }
    }

    @Override
    public int getItemCount() {
        if (users != null)
            return users.size();
        else return 0;
    }

    public class UsuariosViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreItemView;
        public TextView cedulaItemView;

        private UsuariosViewHolder(View itemView) {
            super(itemView);
            nombreItemView = itemView.findViewById(R.id.txt_nombre);
            cedulaItemView = itemView.findViewById(R.id.txt_cedula);
        }
    }
}
