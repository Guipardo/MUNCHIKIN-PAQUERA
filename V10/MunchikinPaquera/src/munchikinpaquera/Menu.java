package munchikinpaquera;

import classes.Clothe;
import classes.Gift;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Menu {

    private Menu instance;

    public Menu() {
    }

    public Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    // __________________________________________________________________________________
    // INTERFACE GRÁFICA
    // CAIXA DE TEXTO
    public void textBox(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // MENU DE 2 OPÇÕES
    public int options(String a, String b, String text, String name) {
        Object[] options = {a, b};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 3 OPÇÕES
    public int options(String a, String b, String c, String text, String name) {
        Object[] options = {a, b, c};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 4 OPÇÕES
    public int options(String a, String b, String c, String d, String text, String name) {
        Object[] options = {a, b, c, d};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 5 OPÇÕES
    public int options(String a, String b, String c, String d, String e, String text, String name) {
        Object[] options = {a, b, c, d, e};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }
    
    // MENU DE 6 OPÇÕES
    public int options(String a, String b, String c, String d, String e,String f, String text, String name) {
        Object[] options = {a, b, c, d, e,f};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // LOJA DE PRESENTES
    public String showGifts(ArrayList<Gift> gifts) {
        String text = "";
        for (Gift gift : gifts) {
            text += gift.show();
            text += "\n";
        }
        return text;
    }

    // LOJA DE ROUPAS
    public String showClothes(ArrayList<Clothe> clothes) {
        String text = "";
        for (Clothe clothe : clothes) {
            text += clothe.showSimple();
            text += "\n";
        }
        return text;
    }

}
