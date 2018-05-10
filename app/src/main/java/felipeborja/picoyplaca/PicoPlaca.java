package felipeborja.picoyplaca;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;


/**
 * Creado por Felipe Borja Pérez el 8/5/2018 a las 15H14
 */
public class PicoPlaca extends AppCompatActivity {
    EditText ingresoPlaca, ingresoHora, ingresoMinuto, ingresoDia;
    TextView resultado;
    static Calendar calendario;
    String diaSemana;
    int dia,mes,año,hora,minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pico_placa);
        instanciarCampos();
        fechaActual();
        setOnListeners();
    }


    private void instanciarCampos() {
        ingresoPlaca = findViewById(R.id.editText);
        ingresoPlaca.setEnabled(false);
        resultado = findViewById(R.id.resultado);
        ingresoHora = findViewById(R.id.ingresarHora);
        ingresoMinuto = findViewById(R.id.ingresarMinuto);
        ingresoDia = findViewById(R.id.ingresarFecha);
    }

     private void fechaActual(){
        calendario = Calendar.getInstance();
        calendario.getTime();
        ingresoHora.setText(String.valueOf((Calendar.getInstance().get(Calendar.HOUR_OF_DAY))));
        ingresoMinuto.setText(String.valueOf((Calendar.getInstance().get(Calendar.MINUTE))));
        dia=(calendario.get(Calendar.DAY_OF_MONTH));
        mes=(calendario.get(Calendar.MONTH)+1);
        año=(calendario.get(Calendar.YEAR));
        hora=(calendario.get(Calendar.HOUR));
        minuto=(calendario.get(Calendar.MINUTE));
        ingresoDia.setText(("Fecha: "+dia+" / "+mes+" / "+año));
    }

    private void setOnListeners(){
        ingresoDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dialogoFecha = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                        hora = Integer.valueOf(ingresoHora.getText().toString());
                        minuto = Integer.valueOf(ingresoMinuto.getText().toString());
                        Calendar calendarNuevo=calendario;
                        calendarNuevo.set(año, mes - 1, dia, hora, minuto);
                        diaSemana = getDiaSemana(calendarNuevo);
                        ingresoDia.setText((diaSemana + "  " + dia + " / " + (mes + 1) + " / " + año));
                        ingresoPlaca.setEnabled(true);
                    }
                });
                dialogoFecha.show(getSupportFragmentManager(), "datePicker");
            }
        });

        ingresoPlaca.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        hora=Integer.valueOf(ingresoHora.getText().toString());
                        minuto=Integer.valueOf(ingresoMinuto.getText().toString());
                        Placa placaNueva = new Placa(String.valueOf(ingresoPlaca.getText()),hora,minuto);
                        if (placaNueva.tieneMulta(calendario,placaNueva.getUltimoDigito())){
                            resultado.setText("TIENE MULTA");
                        }else{
                            resultado.setText("SIN MULTA");
                        }
                        if (!placaNueva.isPico())
                            resultado.setText("FUERA DE HORA PICO");
                    } catch (NumberFormatException nfe){
                        resultado.setText("DATOS INCORRECTOS");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private String getDiaSemana(Calendar calendario){
        //Siendo el primer día de la semana martes (1)
        switch (calendario.get(Calendar.DAY_OF_WEEK)){
            case 7: return "Lunes";
            case 1: return "Martes";
            case 2: return "Miercoles";
            case 3: return "Jueves";
            case 4: return "Viernes";
            case 5: return "sábado";
            case 6: return "Domingo";
        }
        return null;
    }

    //Snippet original de https://developer.android.com/guide/topics/ui/controls/pickers
    @SuppressLint("ValidFragment")
    public static class DatePickerFragment extends DialogFragment{
        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }
    }
}
