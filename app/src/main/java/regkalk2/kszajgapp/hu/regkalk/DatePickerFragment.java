package regkalk2.kszajgapp.hu.regkalk;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Csiga on 2017. 12. 26..
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private DatePickerFragment.OnDateReceiveCallBack mListener;
    private Context context;

    public interface OnDateReceiveCallBack {
        public void onDateReceive(int dd ,int mm, int yy);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        try {
            mListener = (DatePickerFragment.OnDateReceiveCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDateSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(context, this, year, month, day);
    }

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mListener.onDateReceive(dayOfMonth,monthOfYear,year);
    }

    /*public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView btn_ElsoForgDatum = (Button) getActivity().findViewById(R.id.btn_ElsoForgDatum);
        Spinner spn_JarmuTipus = (Spinner) getActivity().findViewById(R.id.spn_JarmuTipus);
        // Do something with the date chosen by the user
        Locale current = getResources().getConfiguration().locale;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MMMM dd.", current);
        btn_ElsoForgDatum.setText(dateFormat.format(new Date(year-1900, month, day)));
        spn_JarmuTipus.setVisibility(View.VISIBLE);
    }*/
}