package regkalk2.kszajgapp.hu.regkalk;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegKalkMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "RegKalkMain";

    public Spinner spn_JarmuTipus, spn_KornyOszt, spn_Uzemanyag, spn_Loketterfogat, spn_Vizsga;
    private EditText et_Teljesitmeny;
    private Button btn_Szamol, btn_ElsoForgDatum;
    private AssetDatabaseHelper dbHelper;

    private List<UIElementState> uiElementStateList = new ArrayList<>();

    public Kalkulacio kalkulacio = new Kalkulacio();

    //private String[] KornyOszt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_kalk_main);

        initUI();
        uploadDefaultItems();

        // Adatbázis inicializálása
        dbHelper = new AssetDatabaseHelper(getBaseContext(), "regkalk.db");
        try {
            dbHelper.importIfNotExist();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*tv_ElsoForgDatum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                spn_JarmuTipus.setVisibility(View.VISIBLE);
            }
        });*/
    }

    // Fills the spinner with the corresponding data
    private void fillSpinner(Spinner spinner, String[] dataArray) {
        final List<String> dataArrayList = new ArrayList<>(Arrays.asList(dataArray));
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dataArrayList){
            @Override
            public boolean isEnabled(int position) {
                if(position==0) return false;
                else return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =  super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) tv.setTextColor(Color.GRAY);
                else tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void uploadDefaultItems() {
        // Jármű típusainak beállítása
        fillSpinner(spn_JarmuTipus, getResources().getStringArray(R.array.JarmuTipus));
        spn_JarmuTipus.setOnItemSelectedListener(this);

        // Környezetvédelmi osztályok beállítása
        fillSpinner(spn_KornyOszt, getResources().getStringArray(R.array.KornyOszt));

        // Üzemanyagok típusainak beállítása
        fillSpinner(spn_Uzemanyag, getResources().getStringArray(R.array.Uzemanyag));
        spn_Uzemanyag.setOnItemSelectedListener(this);
        // Vizsga típusa
        fillSpinner(spn_Vizsga, getResources().getStringArray(R.array.Muszaki));

        // Alapértelmezett események beállítása
        spn_Loketterfogat.setOnItemSelectedListener(this);
        spn_KornyOszt.setOnItemSelectedListener(this);
        spn_Vizsga.setOnItemSelectedListener(this);

        btn_Szamol.setOnClickListener(this);

    }

    public void initUI() {
        spn_JarmuTipus = (Spinner) findViewById(R.id.spn_JarmuTipus);
        spn_KornyOszt = (Spinner) findViewById(R.id.spn_KornyOszt);
        spn_Uzemanyag = (Spinner) findViewById(R.id.spn_Uzemanyag);
        spn_Loketterfogat = (Spinner) findViewById(R.id.spn_Loketterfogat);
        spn_Vizsga = (Spinner) findViewById(R.id.spn_Vizsga);
        et_Teljesitmeny = (EditText) findViewById(R.id.et_Teljesitmeny);
        btn_Szamol = (Button) findViewById(R.id.btn_Szamol);
        btn_ElsoForgDatum = (Button) findViewById(R.id.btn_ElsoForgDatum);

        uiElementStateList.add(new UIElementState(R.id.btn_ElsoForgDatum, 0));
        uiElementStateList.add(new UIElementState(R.id.spn_JarmuTipus, 0));
        uiElementStateList.add(new UIElementState(R.id.spn_Uzemanyag,0));
        uiElementStateList.add(new UIElementState(R.id.spn_Loketterfogat,0));
        uiElementStateList.add(new UIElementState(R.id.spn_KornyOszt,0));
        uiElementStateList.add(new UIElementState(R.id.spn_Vizsga,0));
        uiElementStateList.add(new UIElementState(R.id.et_Teljesitmeny,0));
        uiElementStateList.add(new UIElementState(R.id.btn_Szamol,0));
    }

    // Első forgalombahelyezés datePicker nyitó
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "ElsoForg");
    }

    // Spinnerek választási eseménye után történő megjelenítések
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_JarmuTipus:
                kalkulacio.setJarmutipus((int)parent.getItemIdAtPosition(position));
                switch ((int)parent.getItemIdAtPosition(position)) {
                    case 1:
                        // TODO: Személyautó számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Személyautó");
                        // Olyan kellene, hogy ShowNextStep()
                        spn_Uzemanyag.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        // TODO: Motor számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Motor");
                        spn_Uzemanyag.setVisibility(View.GONE);
                        break;
                    case 3:
                        // TODO: Teherautó számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Teherautó");
                        spn_Uzemanyag.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.spn_Uzemanyag:
                switch ((int)parent.getItemIdAtPosition(position)) {
                    case 1:
                        // TODO: Benzin számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Benzin");
                        // Olyan kellene, hogy ShowNextStep()
                        fillSpinner(spn_Loketterfogat, getResources().getStringArray(R.array.BenzinLoketterfogat));
                        spn_Loketterfogat.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        // TODO: Gázolaj számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Gázolaj");
                        fillSpinner(spn_Loketterfogat, getResources().getStringArray(R.array.GazolajLoketterfogat));
                        spn_Loketterfogat.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        // TODO: Hibrid számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Hibrid");
                        spn_Loketterfogat.setVisibility(View.GONE);
                        break;
                    case 4:
                        // TODO: Elektromos / Plug-In Hybrid számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Elektromos / Plug-In Hybrid");
                        spn_Loketterfogat.setVisibility(View.GONE);
                        break;
                }
                break;
            case R.id.spn_Loketterfogat:
                kalkulacio.setLoketterforgat((int)parent.getItemIdAtPosition(position));
                spn_KornyOszt.setVisibility(View.VISIBLE);
                break;

            case R.id.spn_KornyOszt:
                kalkulacio.setKornyoszt((int)parent.getItemIdAtPosition(position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Gombokon történő kattintásra történő események vezérlése
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ElsoForgDatum:
                break;
            case R.id.btn_Szamol:
                btn_Szamol();
                break;
        }
    }

    public void btn_Szamol() {
        if(spn_JarmuTipus.getId() != 0 && spn_Loketterfogat.getId() != 0 && spn_KornyOszt.getId() != 0) {
            //int alapdij = Integer.parseInt(dbHelper.getRegAdoAlapdij(kalkulacio.getLoketterforgat(), kalkulacio.getJarmutipus(), kalkulacio.getKornyoszt()));
            List<CsokkentesMerteke> csokkentesMertekeList = dbHelper.getCsokkentesMerteke(136);
            //Log.d(TAG, Float.toString((float) csokkentesMertekeList.get(0).getCsokkMerteke()));
            double K;
            double k;

        }
    }
}
