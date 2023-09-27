package com.l20291012.myfirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.l20291012.myfirstapp.usuarios.UserDashboardActivity;
import com.l20291012.myfirstapp.usuarios.repository.UsersRepository;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //private HashMap <String, String> registeredusers;
    private UsersRepository ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //registeredusers = new HashMap<String, String>();
        ur = UsersRepository.getInstance();
        //registeredusers.put("Christopher", "velez");
        //registeredusers.put("Erick", "colon");
        //registeredusers.put("Richard", "camacho");
    }

    public void onBtnIngresarClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Enlazando elementos del layout
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPass = findViewById(R.id.etPass);
        //Extrayendo valores ingresados por usuario
        String user, pass;
        user = etUsuario.getText().toString();
        pass = etPass.getText().toString();

        //Preguntando si el usuario esta registrado
        if (! ur.getRegisteredUsers().containsKey(user)) {
            builder.setTitle("Atención");
            builder.setMessage("Usuario no registrado");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        //Preguntando si la contraseña no es correcta
        if( ! ur.getRegisteredUsers().get(user).containsKey(pass)) {
            builder.setTitle("Atención");
            builder.setMessage("Contraseña incorrecta");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        Toast.makeText(this, "Felicidades, has iniciado sesión exitosamente", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, UserDashboardActivity.class);
        intent.putExtra("usuario",user);
        intent.putExtra("pass", pass);
        startActivity(intent);

    }
}