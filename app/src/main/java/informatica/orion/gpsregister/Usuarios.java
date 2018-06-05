package informatica.orion.gpsregister;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.adapters.adpUsuarios;
import informatica.orion.gpsregister.model.vmUsuarios;

public class Usuarios extends AppCompatActivity {

    vmUsuarios mVmUsuarios;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final adpUsuarios adapter = new adpUsuarios(this);

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mVmUsuarios = ViewModelProviders.of(this).get(vmUsuarios.class);

        mVmUsuarios.getAllUsuarios().observe(this, new Observer<List<entUsuarios>>() {
            @Override
            public void onChanged(@Nullable final List<entUsuarios> entUsuarios) {
                // Update the cached copy of the words in the adapter.
                adapter.setUsuarios(entUsuarios);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Usuarios.this, nuevoUsuario.class);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] valoresu = data.getStringArrayExtra(nuevoUsuario.EXTRA_REPLY);
            entUsuarios usuarios = new entUsuarios();
            usuarios.setNombre(String.valueOf(valoresu[0]));
            usuarios.setCedula(String.valueOf(valoresu[1]));
            usuarios.setUnidad(String.valueOf(valoresu[2]));
            usuarios.setEmpresa(String.valueOf(valoresu[3]));
            mVmUsuarios.insert(usuarios);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
