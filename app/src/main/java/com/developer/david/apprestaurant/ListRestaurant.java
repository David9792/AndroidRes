package com.developer.david.apprestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.david.apprestaurant.ui.adapter.AdapterRestaurant;
import com.developer.david.apprestaurant.ui.adapter.Item;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ListRestaurant extends AppCompatActivity {
    private Activity root = this;

    RecyclerView rec;
    LinearLayoutManager lnm;
    AdapterRestaurant adp;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);
        /*load();*/
       // listRest();

    }

    private void listRest() {
        AsyncHttpClient client = new AsyncHttpClient();
        TextView Nombre = root.findViewById(R.id.Nombre_home);
        TextView Nit = root.findViewById(R.id.Nit_home);
        TextView Propietario = root.findViewById(R.id.Propietario_home);
        TextView Calle = root.findViewById(R.id.Calle_home);
        TextView Telefono = root.findViewById(R.id.Telefono_home);

        RequestParams params=new RequestParams();


        params.add("Nombre", Nombre.getText().toString());
        params.add("Nit", Nit.getText().toString() );
        params.add("Propietario", Propietario.getText().toString() );
        params.add("Calle", Calle.getText().toString() );
        params.add("Telefono", Telefono.getText().toString() );

        client.get(EndPoinds.ip+"restaurant", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    Toast.makeText(ListRestaurant.this,""+response.getString("token"),Toast.LENGTH_LONG).show();
                }catch (Exception e){

                }
            }
        });

    }
    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_list_restaurant, container, false);

        rec=root.findViewById(R.id.rest_recycler);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);


        return root;
    }*/
   /* private void load() {

        rec=root.findViewById(R.id.rest_recycler);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(EndPoinds.ip+"/restaurant", null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                for (int i=0; i<response.length();i++){
                    try {
                        JSONObject ob=response.getJSONObject(i);
                        Item item = new Item(ob.getString("_id"),ob.getString("Nombre"),
                                ob.getString("Nit"),ob.getString("Propietario"),
                                ob.getString("Calle"),ob.getString("Telefono"),1);
                        adp.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }*/




}