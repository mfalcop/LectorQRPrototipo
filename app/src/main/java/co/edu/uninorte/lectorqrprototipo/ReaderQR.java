package co.edu.uninorte.lectorqrprototipo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.URLUtil;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Melanis on 13/05/2017.
 */

public class ReaderQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private String link;
    private ArrayList<String> urls= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView= new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();



    }

    @Override
    protected void onPause(){
        super.onPause();
        mScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {
        link = result.getText();

        final String[] rutas  = link.split("--"); //Obtiene las rutas por separado del texto que tiene el código
        for(int i =0; i<rutas.length;i++){
            if(i%2==0){
                urls.add("https://www.google.com/maps/d/u/1/embed?mid=1zFtc12k2dp2_R0TQUK1OjMd_77s");

            }else {
                urls.add("https://www.google.com/maps/d/u/1/embed?mid=1h6g1585A2DKord6-iuY1Qr4Lrfc");
            }
        }

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Resultado del scanner");
        alertDialog.setMessage("Escoja la opción que desee:");
        alertDialog.setPositiveButton("Abrir rutas", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if( URLUtil.isValidUrl(link)){
                    Uri uri = Uri.parse(link); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivityForResult(intent,1);
                }else{
                    Intent in = new Intent(getApplicationContext(), ListaRutas.class);
                    in.putExtra("RUTAS",rutas);
                    in.putExtra("URLS", urls);
                    startActivity(in);
                }

            }
        });

        alertDialog.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ReaderQR.this, MainActivity.class));
        finish();

    }
}

