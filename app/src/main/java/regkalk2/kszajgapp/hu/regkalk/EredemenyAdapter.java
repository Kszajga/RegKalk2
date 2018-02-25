package regkalk2.kszajgapp.hu.regkalk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Csiga on 2018. 02. 24..
 */

public class EredemenyAdapter extends BaseAdapter {
    ArrayList<Object> list;
    private LayoutInflater inflater;

    public EredemenyAdapter(Context context, ArrayList<Object> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =  inflater.inflate(R.layout.item_list_eredmeny,null);
        }

        TextView tv_EredmenyNev = (TextView) convertView.findViewById(R.id.tv_EredmenyNev);
        TextView tv_EredmenyErtek = (TextView) convertView.findViewById(R.id.tv_EredmenyErtek);

        tv_EredmenyNev.setText("Sajt");
        //tv_EredmenyErtek.setText(Integer.toString(list.get(position).));

        return convertView;
    }
}
