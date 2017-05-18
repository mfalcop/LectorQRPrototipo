package co.edu.uninorte.lectorqrprototipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Melanis on 13/05/2017.
 */

public class ListaRutas extends AppCompatActivity {

    ListView lista;
    ListAdapter listAdapter;
private String[] mrutas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutas_lista_activity);

        Intent intent = getIntent();
        mrutas=intent.getStringArrayExtra("RUTAS");

        listAdapter = new ListAdapter(mrutas,this);
        lista = (ListView) findViewById(R.id.listaRutasLV);

        listAdapter.data = mrutas;
        lista.setAdapter(listAdapter);

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ListaRutas.this, MainActivity.class));
        finish();

    }

    public void onClickOK(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
