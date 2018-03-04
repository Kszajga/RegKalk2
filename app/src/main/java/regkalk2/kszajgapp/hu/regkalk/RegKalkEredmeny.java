package regkalk2.kszajgapp.hu.regkalk;

import android.app.ActionBar;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
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

        TableRow tr_Uzemanyag = (TableRow) findViewById(R.id.tr_Uzemanyag);
        TextView tv_Uzemanyagtipus = (TextView) findViewById(R.id.tv_Uzemanyagtipus);
        TextView tv_UzemanyagtipusErtek = (TextView) findViewById(R.id.tv_UzemanyagtipusErtek);

        TextView tv_RegisztraciosAdo = (TextView) findViewById(R.id.tv_RegisztraciosAdo);
        TextView tv_RegisztraciosAdoErtek = (TextView) findViewById(R.id.tv_RegisztraciosAdoErtek);

        TextView tv_VagyonszerzesiIlletek = (TextView) findViewById(R.id.tv_VagyonszerzesiIlletek);
        TextView tv_VagyonszerzesiIlletekErtek = (TextView) findViewById(R.id.tv_VagyonszerzesiIlletekErtek);

        TextView tv_ForgalmiEngedely = (TextView) findViewById(R.id.tv_ForgalmiEngedely);
        TextView tv_ForgalmiEngedelyErtek = (TextView) findViewById(R.id.tv_ForgalmiEngedelyErtek);

        TextView tv_Torzskonyv = (TextView) findViewById(R.id.tv_Torzskonyv);
        TextView tv_TorzskonyvErtek = (TextView) findViewById(R.id.tv_TorzskonyvErtek);

        TextView tv_Rendszam = (TextView) findViewById(R.id.tv_Rendszam);
        TextView tv_RendszamErtek = (TextView) findViewById(R.id.tv_RendszamErtek);

        Kalkulacio kalkulacio = (Kalkulacio) getIntent().getParcelableExtra("kalkulacio");
        tv_Jarmutipus.setText("Jármű típus: ");
        tv_JarmutipusErtek.setText(kalkulacio.getJarmutipusnev());

        tv_Uzemanyagtipus.setText("Üzemanyag: ");


        tv_RegisztraciosAdoErtek.setText(Integer.toString(kalkulacio.getRegado()) + " Ft");

        if (kalkulacio.getVagyonszerzesiIlletek() == 0) {
            tv_VagyonszerzesiIlletek.setVisibility(View.GONE);
            tv_VagyonszerzesiIlletekErtek.setVisibility(View.GONE);
        }
        else {
            tv_VagyonszerzesiIlletekErtek.setText(Integer.toString(kalkulacio.getVagyonszerzesiIlletek()) + " Ft");
        }

        tv_ForgalmiEngedelyErtek.setText(Integer.toString(Constants.FORGALMI) + " Ft");

        tv_TorzskonyvErtek.setText(Integer.toString(Constants.TORZSKONYV) + " Ft");

        if (kalkulacio.getJarmutipus() == 2) {//Ha motor
            tr_Uzemanyag.setVisibility(View.GONE);
            tv_RendszamErtek.setText(Integer.toString(Constants.RENDSZAMMOTOR) + " Ft");
        }
        else
        {
            tv_RendszamErtek.setText(Integer.toString(Constants.RENDSZAM) + " Ft");
            tv_UzemanyagtipusErtek.setText(kalkulacio.getUzemanyagnev());
        }

    }
}
