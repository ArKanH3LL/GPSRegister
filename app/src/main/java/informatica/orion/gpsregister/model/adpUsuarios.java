package informatica.orion.gpsregister.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.entity.entUsuarios;

public class adpUsuarios extends RecyclerView.Adapter<adpUsuarios.UsuariosViewHolder> {

    class UsuariosViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombreItemView;
        private final TextView cedulaItemView;
        private final TextView areaItemView;
        private final TextView empresaItemView;

        private UsuariosViewHolder(View itemView) {
            super(itemView);
            nombreItemView = itemView.findViewById(R.id.txt_nombre);
            cedulaItemView = itemView.findViewById(R.id.txt_cedula);
            areaItemView = itemView.findViewById(R.id.txt_area);
            empresaItemView = itemView.findViewById(R.id.txt_empresa);

        }
    }

    private final LayoutInflater mInFlater;
    private ArrayList<entUsuarios> users;

    public adpUsuarios(Context context){mInFlater = LayoutInflater.from(context);}

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInFlater.inflate(R.layout.reciclerview_item, parent, false);
        return new UsuariosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {
        if (users != null) {
            holder.nombreItemView.setText(users.get(position).getNombre());
            holder.cedulaItemView.setText(users.get(position).getCedula());
            holder.areaItemView.setText(users.get(position).getArea());
            holder.empresaItemView.setText(users.get(position).getEmpresa());

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

    void setUsuarios(ArrayList<entUsuarios> usuarios){
        users = usuarios;
        notifyDataSetChanged();
    }


}
