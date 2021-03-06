package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistroUsuario extends AppCompatActivity {

    private Activity root = this;
    Button Registrar;
    TextView Nombre, Telefono, Direccion,Email, Pass, Pass2,Roles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        getSupportActionBar().hide();

        Nombre = findViewById(R.id.UserName);
        Telefono = findViewById(R.id.UserPhone);
        Direccion = findViewById(R.id.UserStreet);
        Email = findViewById(R.id.UserEmail);
        Pass = findViewById(R.id.UserPass);
        //Roles = findViewById(R.id.UserPassw);
        Registrar = findViewById(R.id.restaurRegistro);

        //Pass2 = findViewById(R.id.UserPassw);






        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroUsuario.this, MainActivity.class);
                senResgistrar();
                startActivity(intent);
            }
        });
    }

    public void senResgistrar(){
        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams req = new RequestParams();
        req.put("Nombre", Nombre.getText().toString());
        req.put("Telefono", Telefono.getText().toString());
        req.put("Direccion", Direccion.getText().toString());
        req.put("Email", Email.getText().toString());
        req.put("Password", Pass.getText().toString());
        //req.put("Roles", Roles.getText().toString());

        client.post(EndPoinds.CLIENT+"api/1.0/clientes", req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                        String msn = response.getString("msn");
                        Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}