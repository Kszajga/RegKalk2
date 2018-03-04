package regkalk2.kszajgapp.hu.regkalk;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class RegKalkMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerFragment.OnDateReceiveCallBack, RewardedVideoAdListener {

    private static final String TAG = "RegKalkMain";

    public Spinner spn_JarmuTipus, spn_KornyOszt, spn_Uzemanyag, spn_Vizsga; // törölve: spn_Loketterfogat
    private EditText et_Loketterfogat, et_Teljesitmeny;
    private Button btn_Szamol, btn_ElsoForgDatum;
    private AssetDatabaseHelper dbHelper;

    // For AdMob
    private AdView adView;
    private RewardedVideoAd rewardedVideoAd;
    AdRequest adRequest = new AdRequest.Builder()
            //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

    public Kalkulacio kalkulacio = new Kalkulacio();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_kalk_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        initUI();

        uploadDefaultItems();

        kalkulacio.setContext(getBaseContext());

        // AdMob reklám inicializálás
        //AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        // Adatbázis inicializálása
        dbHelper = new AssetDatabaseHelper(getBaseContext(), "regkalk.db");
        try {
            dbHelper.importIfNotExist();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        // spn_Loketterfogat.setOnItemSelectedListener(this); törölve
        et_Loketterfogat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(getBaseContext(), "before Text Changed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getBaseContext(), "on Text Changed.", Toast.LENGTH_SHORT).show();
                if(s.length() > 0){
                    kalkulacio.setLoketterforgat(Integer.parseInt(et_Loketterfogat.getText().toString()));
                    Toast.makeText(getBaseContext(), "after Text Changed.", Toast.LENGTH_SHORT).show();

                    // Motor esetén nincs környezetvédelmi osztály
                    if(kalkulacio.getJarmutipus() !=2){
                        spn_KornyOszt.setVisibility(View.VISIBLE);
                    }
                    else {
                        spn_KornyOszt.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spn_KornyOszt.setOnItemSelectedListener(this);
        spn_Vizsga.setOnItemSelectedListener(this);
        spn_Vizsga.setOnItemSelectedListener(this);

        btn_Szamol.setOnClickListener(this);

    }

    public void initUI() {
        adView = (AdView) findViewById(R.id.adView);
        spn_JarmuTipus = (Spinner) findViewById(R.id.spn_JarmuTipus);
        spn_KornyOszt = (Spinner) findViewById(R.id.spn_KornyOszt);
        spn_Uzemanyag = (Spinner) findViewById(R.id.spn_Uzemanyag);
        //spn_Loketterfogat = (Spinner) findViewById(R.id.spn_Loketterfogat); törölve
        spn_Vizsga = (Spinner) findViewById(R.id.spn_Vizsga);
        et_Loketterfogat = (EditText) findViewById(R.id.et_Loketterfogat);
        et_Teljesitmeny = (EditText) findViewById(R.id.et_Teljesitmeny);
        btn_Szamol = (Button) findViewById(R.id.btn_Szamol);
        btn_ElsoForgDatum = (Button) findViewById(R.id.btn_ElsoForgDatum);
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
                        kalkulacio.setJarmutipusnev("Személyautó");
                        // Olyan kellene, hogy ShowNextStep()
                        spn_Uzemanyag.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        // TODO: Motor számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Motor");
                        kalkulacio.setJarmutipusnev("Motor");
                        spn_Uzemanyag.setVisibility(View.GONE);
                        spn_KornyOszt.setVisibility(View.GONE);

                        /*fillSpinner(spn_Loketterfogat, getResources().getStringArray(R.array.MotorLoketterfogat)); törölve
                        spn_Loketterfogat.setVisibility(View.VISIBLE);*/
                        break;
                    case 3:
                        // TODO: Teherautó számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Teherautó");
                        kalkulacio.setJarmutipusnev("Teherautó");
                        spn_Uzemanyag.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.spn_Uzemanyag:
                kalkulacio.setUzemanyag((int)parent.getItemIdAtPosition(position));
                switch ((int)parent.getItemIdAtPosition(position)) {
                    case 1:
                        // TODO: Benzin számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Benzin");
                        kalkulacio.setUzemanyagnev("Benzin");
                        // Olyan kellene, hogy ShowNextStep()
                        /*fillSpinner(spn_Loketterfogat, getResources().getStringArray(R.array.BenzinLoketterfogat)); törölve
                        spn_Loketterfogat.setVisibility(View.VISIBLE);*/
                        et_Loketterfogat.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        // TODO: Gázolaj számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Gázolaj");
                        kalkulacio.setUzemanyagnev("Gázolaj");
                        /*fillSpinner(spn_Loketterfogat, getResources().getStringArray(R.array.GazolajLoketterfogat)); törölve
                        spn_Loketterfogat.setVisibility(View.VISIBLE);*/
                        et_Loketterfogat.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        // TODO: Hibrid számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Hibrid");
                        kalkulacio.setUzemanyagnev("Hibrid");
                        //spn_Loketterfogat.setVisibility(View.GONE); törölve
                        et_Loketterfogat.setVisibility(View.GONE);
                        break;
                    case 4:
                        // TODO: Elektromos / Plug-In Hybrid számításához szükséges vizuális elemek kihelyezése
                        Log.d(TAG, "Elektromos / Plug-In Hybrid");
                        kalkulacio.setUzemanyagnev("Elektromos / Plug-In Hybrid");
                        //spn_Loketterfogat.setVisibility(View.GONE); törölve
                        et_Loketterfogat.setVisibility(View.GONE);
                        break;
                }
                break;
            /*case R.id.spn_Loketterfogat:
                kalkulacio.setLoketterforgat((int)parent.getItemIdAtPosition(position));
                if(kalkulacio.getJarmutipus() == 2) { //ha motor
                    spn_KornyOszt.setVisibility(View.GONE);
                }
                else if (kalkulacio.getJarmutipus() != 2) {
                    spn_KornyOszt.setVisibility(View.VISIBLE);
                }
                break;*/

            case R.id.spn_KornyOszt:
                kalkulacio.setKornyoszt((int)parent.getItemIdAtPosition(position));
                break;

            case R.id.spn_Vizsga:
                kalkulacio.setErvenyesMuszaki(String.valueOf(parent.getItemIdAtPosition(position)));
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
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }
                break;
        }
    }

    public void btn_Szamol() {
        if(kalkulacio.getJarmutipus() != 0 && kalkulacio.getLoketterforgat() > 0) {
            int F = kalkulacio.getRegAdo();
            kalkulacio.setRegado(F);

            // Teljesítmény beállítása 0-ra, így nem lesz hiba, nem írnak be semmit.
            kalkulacio.setTeljesitmeny(0);
            if (!et_Teljesitmeny.getText().toString().equals("")){
                kalkulacio.setTeljesitmeny(Integer.parseInt(et_Teljesitmeny.getText().toString()));
            }
            int vagyonszerzesiilletek = kalkulacio.getVagyonszerzesiIlletekDb(kalkulacio.getElteltEvek(), kalkulacio.getTeljesitmeny());
            kalkulacio.setVagyonszerzesiIlletek(vagyonszerzesiilletek);

            Intent intentEredmeny = new Intent(RegKalkMain.this, RegKalkEredmeny.class);
            intentEredmeny.putExtra("kalkulacio", kalkulacio);
            startActivity(intentEredmeny);
        }
    }

    @Override
    public void onDateReceive(int dd, int mm, int yy) {
        final Calendar c = Calendar.getInstance();
        int today_year = c.get(Calendar.YEAR);
        int today_month = c.get(Calendar.MONTH);
        int today_day = c.get(Calendar.DAY_OF_MONTH);

        int diffYear = today_year - yy;
        int diffMonth = diffYear*12 + today_month - mm;

        Locale current = getResources().getConfiguration().locale;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MMMM dd.", current);
        btn_ElsoForgDatum.setText(dateFormat.format(new Date(yy-1900, mm, dd)));
        kalkulacio.setElteltHonapok(diffMonth);
        kalkulacio.setElteltEvek(diffYear);
        Log.d(TAG, "Eltelt hónapok: " + Integer.toString(diffMonth));
        spn_JarmuTipus.setVisibility(View.VISIBLE);
    }

    // Reklámok betöltéséért felelős rész
    private void loadRewardedVideoAd() {
        if(!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd(Constants.ADMOBREWARDED, adRequest);
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(getBaseContext(), "Ad loaded.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(getBaseContext(), "Ad opened.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(getBaseContext(), "Ad started.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(getBaseContext(), "Ad closed.", Toast.LENGTH_SHORT).show();

        if(kalkulacio.getReward() > 0)
        {
            btn_Szamol();
            kalkulacio.setReward(0);
        }

        // Load the next rewarded video ad.
        loadRewardedVideoAd();


    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Toast.makeText(getBaseContext(), "User rewarded.", Toast.LENGTH_SHORT).show();
        if(rewardItem.getAmount() > 0)
        {
            kalkulacio.setReward(rewardItem.getAmount());
        }

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(getBaseContext(), "Ad left application.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(getBaseContext(), "Ad failed to load.", Toast.LENGTH_SHORT).show();
    }
}
