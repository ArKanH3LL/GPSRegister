package informatica.orion.gpsregister.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import informatica.orion.gpsregister.R;

public class adpUsuarios extends RecyclerView.Adapter<adpUsuarios.UsuariosViewHolder> {

    ArrayList<String> users;

    public adpUsuarios(ArrayList<String> users) {
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
            holder.nombreItemView.setText(users.get(position));

        } else {
            // Covers the case of data not being ready yet.
            holder.nombreItemView.setText("Sin Nombres");
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

        private UsuariosViewHolder(View itemView) {
            super(itemView);
            nombreItemView = itemView.findViewById(R.id.nombreTV);
        }
    }
}
