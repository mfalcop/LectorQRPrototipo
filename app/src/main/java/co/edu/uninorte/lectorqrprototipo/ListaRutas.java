package co.edu.uninorte.lectorqrprototipo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Melanis on 13/05/2017.
 */

public class ListaRutas extends AppCompatActivity {

    ListView lista;
    ListAdapter listAdapter;
private String[] mrutas;
    private ArrayList<String> murls= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutas_lista_activity);

        Intent intent = getIntent();
        mrutas=intent.getStringArrayExtra("RUTAS");
        murls= intent.getStringArrayListExtra("URLS");

        listAdapter = new ListAdapter(mrutas,this);
        lista = (ListView) findViewById(R.id.listaRutasLV);

        listAdapter.data = mrutas;
        lista.setAdapter(listAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if( URLUtil.isValidUrl(murls.get(position))){
                    Uri uri = Uri.parse(murls.get(position)); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivityForResult(intent,1);
                }else{
                    Toast.makeText(ListaRutas.this,"URL Invalida",Toast.LENGTH_LONG);
                }
            }
        });

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
