package co.edu.uninorte.lectorqrprototipo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Melanis on 13/05/2017.
 */

public class ListAdapter extends BaseAdapter {

    public String[] data;
    private Context context;

    public ListAdapter(String[] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final String text = data[position];
        if (convertView == null) {
            LayoutInflater T = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = T.inflate(R.layout.row, null);

        }

        TextView nombre = (TextView) convertView.findViewById(R.id.nombreRutaTV);
        nombre.setText(text);


        return convertView;
    }
}
