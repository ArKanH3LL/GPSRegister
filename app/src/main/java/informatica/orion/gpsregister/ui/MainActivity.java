package informatica.orion.gpsregister.ui;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import informatica.orion.gpsregister.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnUsuarios = findViewById(R.id.btn_usuarios);
        btnUsuarios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UsuariosShow.class);
                startActivity(intent);
            }
        });
    }

    //Check External
    public boolean isExternalStorageWritable(){
        String state= Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    //Save to File
    public File getPublicWPStorageDir(String waypoints) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), waypoints);
        if (!file.mkdirs()){
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
}
