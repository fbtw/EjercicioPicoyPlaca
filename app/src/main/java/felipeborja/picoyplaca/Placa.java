package felipeborja.picoyplaca;

import java.util.Calendar;

/**
 * Creado por Felipe Borja Pérez el 8/5/2018 a las 22H54
 */
public class Placa {
    private String codigoPlaca;
    private short ultimoDigito;
    private boolean pico;

    public Placa(String codigoPlaca,int hora, int minuto) {
        this.codigoPlaca = codigoPlaca;
        setPico(hora,minuto);
        ultimoDigito=setUltimoDigito(codigoPlaca);
    }

    public String getCodigoPlaca() {
        return codigoPlaca;
    }

    public void setCodigoPlaca(String codigoPlaca) {
        this.codigoPlaca = codigoPlaca;
    }

    public short getUltimoDigito() {
        return ultimoDigito;
    }

    private short setUltimoDigito(String placa){
        return Short.parseShort(String.valueOf(placa.charAt(placa.length()-1)));
    }

    public boolean isPico() {
        return pico;
    }

    //minutosTotales: 420=07H00, 570=09H30, 960=16H00, 1170=19H30
    private void setPico(int hora, int minuto) {
        int minutosTotales = hora * 60 + minuto;
        pico = (minutosTotales > 420) && (minutosTotales < 570) || (minutosTotales > 960) && (minutosTotales < 1170);
    }

    public Boolean tieneMulta(Calendar calendario, Short ultimoDigito){
        switch (calendario.get(Calendar.DAY_OF_WEEK)){
            case 7: if ((ultimoDigito==1)||(ultimoDigito==2))return true;
                break;
            case 1: if ((ultimoDigito==3)||(ultimoDigito==4))return true;
                break;
            case 2: if ((ultimoDigito==5)||(ultimoDigito==6))return true;
                break;
            case 3: if ((ultimoDigito==7)||(ultimoDigito==8))return true;
                break;
            case 4: if ((ultimoDigito==9)||(ultimoDigito==0))return true;
                break;
        }
        return false;
    }

}
