package regkalk2.kszajgapp.hu.regkalk;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RegKalkEredmeny extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_kalk_eredmeny);

        TextView tv_Jarmutipus = (TextView) findViewById(R.id.tv_Jarmutipus);
        TextView tv_JarmutipusErtek = (TextView) findViewById(R.id.tv_JarmutipusErtek);

        Kalkulacio kalkulacio = (Kalkulacio) getIntent().getParcelableExtra("kalkulacio");

        tv_JarmutipusErtek.setText(Integer.toString(kalkulacio.getJarmutipus()));

    }
}
