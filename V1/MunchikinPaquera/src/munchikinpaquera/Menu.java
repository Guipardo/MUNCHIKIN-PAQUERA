package munchikinpaquera;

import javax.swing.JOptionPane;

/*
    O menu serve para exibir as caixas de mensagem e de opção
 */
public class Menu {

    //Construtor
    public Menu() {
    }

    //Mostra uma caixa de texto com uma mensagem
    public void showText(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    //Mostra um menu com opções e retorna um inteiro com o valor da opção
    public int showOption(String a, String b, String text, String name) {
        Object[] options = {a, b};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }
}
