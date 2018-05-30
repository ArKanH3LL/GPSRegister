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
import informatica.orion.gpsregister.model.adpUsuarios;
import informatica.orion.gpsregister.model.vmGPSRegister;

public class Usuarios extends AppCompatActivity {

    private vmGPSRegister mVmGPSRegister;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new adpUsuarios();

        recyclerView.setAdapter(adapter);

        mVmGPSRegister = ViewModelProviders.of(this).get(vmGPSRegister.class);

        mVmGPSRegister.getAllUsuarios().observe(this, new Observer<List<entUsuarios>>() {
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
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);;
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            entUsuarios entUsuarios = new entUsuarios(data.getStringExtra(nuevoUsuario.EXTRA_REPLY));
            mVmGPSRegister.insert(entUsuarios);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
