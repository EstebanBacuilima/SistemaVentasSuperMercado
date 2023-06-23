package djmeb;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class validaciones {
    
    public static boolean VerificarEmail(String correo) {

        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // Contiene el parametro y lo verifica con valor booleano
        Matcher mat = patron.matcher(correo);

        return mat.find();
    }

    // SEGUNDO
    public static void ValidarLongitud(String cad, int tam, KeyEvent ev) {
        if (cad.trim().length() == tam) {
            ev.consume();
        }
    }

    public static void ValidarLetrNum(boolean dato, KeyEvent e) {
        if (dato) {
            e.consume();
        }
    }
    
    
    Character s;
    
    public void ValidarLetrNum1(KeyEvent e) {
        
        s = e.getKeyChar();
        if (!Character.isLetter(s)) {
            e.consume();
        }
        
    }

    public static boolean Cedula(String cedula) {
        boolean estado=false;
        try{
            if(cedula.length()==10){
                if((Integer.parseInt(cedula.substring(0, 2))<=24) || Integer.parseInt(cedula.substring(0, 2))<=30){
                    int[] coeficientes={2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int digito_verificador=Integer.parseInt(cedula.substring(cedula.length()-1, cedula.length()));
                    int suma=0;
                    int multiplic=0;
                    int modulo;
                    
                    for(int i=0; i<cedula.length()-1; i++){
                        multiplic=Integer.parseInt(cedula.substring(i, i+1))*coeficientes[i];
                        multiplic=(multiplic>9) ? multiplic-=9 : multiplic;
                        suma += multiplic;
                    }
                    modulo=suma%10;
                    if((10-modulo)==digito_verificador){
                        estado=true;
                    }
                    if(modulo==0 & modulo==digito_verificador){
                        estado=true;
                    }
                }else{
                    estado=false;
                    JOptionPane.showMessageDialog(null, "Cedula no pertenece a Ecuador");
                }
            }
        }catch(NumberFormatException e){
            estado=false;
            JOptionPane.showMessageDialog(null, "Error al validar");
        }
        return estado; 
    }

}
