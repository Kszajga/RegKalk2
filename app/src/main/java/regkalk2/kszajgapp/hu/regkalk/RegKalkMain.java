package regkalk2.kszajgapp.hu.regkalk;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegKalkMain extends AppCompatActivity {

    private Spinner spn_KornyOszt, spn_Uzemanyag, spn_Loketterfogat, spn_Vizsga;
    private EditText et_Teljesitmeny;
    private Button btn_Szamol, btn_ElsoForgDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_kalk_main);

        initUI();
    }

    public void initUI() {
        spn_KornyOszt = (Spinner) findViewById(R.id.spn_KornyOszt);
        spn_Uzemanyag = (Spinner) findViewById(R.id.spn_Uzemanyag);
        spn_Loketterfogat = (Spinner) findViewById(R.id.spn_Loketterfogat);
        spn_Vizsga = (Spinner) findViewById(R.id.spn_Vizsga);
        et_Teljesitmeny = (EditText) findViewById(R.id.et_Teljesitmeny);
        btn_Szamol = (Button) findViewById(R.id.btn_Szamol);
        btn_ElsoForgDatum = (Button) findViewById(R.id.btn_ElsoForgDatum);
    }


    // Első forgalombahelyezés datePicker nyitó
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "ElsoForg");
    }
}
