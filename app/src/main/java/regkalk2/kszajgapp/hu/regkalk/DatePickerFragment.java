package regkalk2.kszajgapp.hu.regkalk;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Parcelable;
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
        Dialog retval = null;
        if(savedInstanceState == null) {
            // Create a new instance of DatePickerDialog and return it
            retval = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);
        }
        else {
            Kalkulacio kalkulacio = savedInstanceState.getParcelable("kalkulacio");
            SimpleDateFormat format = new SimpleDateFormat("yyyy. MMMM dd.");
            try {
                Date date = format.parse(kalkulacio.getElso_forg().toString());
                retval = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, date.getYear(), date.getMonth(), date.getDay());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        return retval;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mListener.onDateReceive(dayOfMonth,monthOfYear,year);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}