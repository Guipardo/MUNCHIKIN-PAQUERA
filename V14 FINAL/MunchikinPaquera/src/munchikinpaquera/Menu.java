package munchikinpaquera;

import model.Clothe;
import model.Gift;
import model.Lady;
import model.Style;
import io.IOManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jogo.Meeting;
import static munchikinpaquera.Game.clotheList;
import static munchikinpaquera.Game.giftList;
import static munchikinpaquera.Game.imagem;
import static munchikinpaquera.Game.ladyList;
import static munchikinpaquera.Game.meetNumber;
import static munchikinpaquera.Game.menuOption;
import static munchikinpaquera.Game.newGame;
import static munchikinpaquera.Game.player;

public class Menu {

    // __________________________________________________________________________________
    // INTERFACE GRÁFICA
    // MENU PRINCIPAL
    public static void showMainMenu() {
        String intro = Menu.styleBar()
                + "\n Um jogo feito por : \n Marina Yumi e Guilherme Rodrigues " + Menu.styleBar();
        menuOption = Menu.options("CARREGAR JOGO", "NOVO JOGO", "SAIR", intro, "MUNCHIKIN PAQUERA - VERSÃO 14.0", imagem);
    }

    //CARREGAR PARTIDAS ANTERIORES
    public static void loadGame() {
        try {
            String arquivo = Menu.inputBox(Menu.styleBar()
                    + " Digite o nome do personagem "
                    + Menu.styleBar());
            player = IOManager.loadGame(arquivo);
        } catch (NullPointerException ex) {
            Menu.textBox(Menu.styleBar() + " Nenhum arquivo de jogo encontrado! "
                    + Menu.styleBar());
        }
    }

    // MENU DE SALVE
    public static void saveGame() {
        IOManager.saveGame(player);
        Menu.textBox(Menu.styleBar() + " Seu jogo foi salvo! "
                + Menu.styleBar());
    }

    // NOVO JOGO
    // CRIA UM JOGADOR
    public static void newGame() {
        do{
            // CRIA UM NOVO JOGADOR
            player = Control.createFlirt();
            // INTRODUÇÃO DO JOGO
            // O JOGADOR RECEBE UMA ROUPA ALEATÓRIA E UM PRESENTE ALEATÓRIO
            Control.setPlayerItems(player, giftList, clotheList);
            // CONTADOR DE ROUNDS COMEÇA ZERADO
            newGame.setNumber(0);
        }while (player == null); 
    }

    // MENU DE JOGO
    public static int gameMenu() {
        // MENU DO JOGO
        int action = Menu.options("HISTÓRICO", "ENCONTRO", "LOJA",
                "SALVAR", "SAIR", Menu.styleBar() + player.show() + Menu.styleBar(), "MUNCHIKIN PAQUERA");
        return action;
    }

    // MENU DE ENCONTRO
    public static void meetMenu() {
        // O CONTROLE ESCOLHE UMA LADY ALEATÓRIA
        Control.setLady(ladyList);
        // O CONTROLE ESCOLHE UM PRESENTE ALEATÓRIO
        Gift newGift = Control.setGift(giftList); //Escolhe um presente

        // INCREMENTA O CONTADOR DE ROUND
        meetNumber += 1;
        // CRIA UM NOVO ENCONTRO
        Meeting M = new Meeting(meetNumber, Game.newLady, newGift);

        // O CONTROLE REALIZA UM ENCONTRO
        // DEPOIS DO ENCONTRO, VOLTA PARA O MENU DE JOGO
        Control.doMeet(M, player, newGame);
    }

    // VITÓRIA
    public static void win() {
        Menu.textBox(Menu.styleBar()
                + " Seu charme chegou à " + player.getCharm()
                + "\n Carregue seu save de novo para enfrentar o desafio final!"
                + Menu.styleBar());
        player.setGameplay(newGame);
        Menu.saveGame();
    }

    // DERROTA
    public static void lose() {
        Menu.textBox(Menu.styleBar()
                + " Seu charme chegou à " + player.getCharm()
                + "\n Você foi rejeitado. "
                + Menu.styleBar());
        player.setGameplay(newGame);
    }

    // FIM
    public static void tryAgain() {
        Menu.textBox(Menu.styleBar()
                + "\n Você foi rejeitado. "
                + Menu.styleBar());
        endGame();

    }

    // MOSTRA HISTÓRICO
    public static void showHistoric() {
        try {
            Menu.textBox(Menu.styleBar() + " Histórico de Encontros \n"
                    + player.getGameplay().show()
                    + Menu.styleBar());
        } catch (NullPointerException ex) {
            Menu.textBox(Menu.styleBar() + " Sem histórico de encontros! "
                    + Menu.styleBar());
        }
    }

    // MENU DE STATUS
    public static void showStatus() {
        Menu.textBox(Menu.styleBar()
                + " Você está com " + player.getCharm()
                + " de charme. " + Menu.styleBar());
    }

    // SAIR DO JOGO
    public static void endGame() {
        Menu.textBox(Menu.styleBar() + " Obrigado por Jogar MUNCHIKIN PAQUERA! "
                + Menu.styleBar());
        System.exit(0);
    }

    // MENU DA LOJA
    public static int shopMenu() {
        // MENU DA LOJA
        int option = Menu.options("PRESENTES", "ROUPA", "VOLTAR", styleBar(),
                "LOJA");
        return option;
    }

    // MENU DE PRESENTE
    public static int category() {
        int category = Menu.options("COMPRAR", "VENDER", styleBar(), "LOJA");
        return category;
    }

    // MENU DE VENDA DO PRESENTE
    public static void sellGift() {
        if (player.getGifts().isEmpty()) {
            Menu.textBox(Menu.styleBar()
                    + " Você não possui itens para vender! " + Menu.styleBar());
        } else {
            Control.sellGift(player, giftList);
        }
    }

    // MENU DE COMPRA DO PRESENTE
    public static void buyGift() {
        Control.buyGift(player, giftList);
    }

    //MENU DE COMPRA DA ROUPA
    public static void buyClothe() {
        Control.buyClothe(player, clotheList);
    }

    // ___________________________________________________________________________________
    // CAIXAS DE TEXTO
    public static void msg(String text) {
        System.out.println(text);
    }

    public static String styleBar() {
        return "\n = = = = = = = = = = = = \n";
    }

    public static void textBox(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // OPÇÃO DE TEXTO
    public static String inputBox(String text) {
        String answer = JOptionPane.showInputDialog(text);
        return answer;
    }

    // MENU DE 2 OPÇÕES
    public static int options(String a, String b, String text, String name) {
        Object[] options = {a, b};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 3 OPÇÕES
    public static int options(String a, String b, String c, String text, String name) {
        Object[] options = {a, b, c};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 4 OPÇÕES
    public static int options(String a, String b, String c, String d, String text, String name) {
        Object[] options = {a, b, c, d};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 5 OPÇÕES
    public static int options(String a, String b, String c, String d, String e, String text, String name) {
        Object[] options = {a, b, c, d, e};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // MENU DE 6 OPÇÕES
    public static int options(String a, String b, String c, String d, String e, String f, String text, String name) {
        Object[] options = {a, b, c, d, e, f};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }

    // LOJA DE PRESENTES
    public static String showGifts(ArrayList<Gift> gifts) {
        String text = "";
        for (Gift gift : gifts) {
            text += gift.showSimple();
            text += "\n";
        }
        return text;
    }

    // LOJA DE ROUPAS
    public static String showClothes(ArrayList<Clothe> clothes) {
        String text = "";
        for (Clothe clothe : clothes) {
            text += clothe.showSimple();
            text += "\n";
        }
        return text;
    }

    // MENU COM IMAGEM
    public static int options(String a, String b, String c, String text, String name, ImageIcon imagem) {
        Object[] options = {a, b, c};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                imagem, options, options[0]);
        return answer;
    }

    // MENU COM IMAGEM
    public static int options(String a, String b, String text, String name, ImageIcon imagem) {
        Object[] options = {a, b};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                imagem, options, options[0]);
        return answer;
    }

    public static void alien() {
        Lady alien = new Lady("Aisha", 999, Style.ROMÂNTICO,
                "Eu sou Aisha, venho de planeta em plantea buscado pelo ser"
                + " mais charmoso que possa existir. "
                + "\nEu já passei por vários planetas e nenhum deles "
                + " conseguiu me conquistar, você pode tentar a sorte, terráqueo.",
                " Aposto que você seria um sucesso no meu planeta. Vem comigo? ",
                " Você não tem charme o suficiente pra mim. Volte quando tiver os melhores itens ");
        boolean combat = false;
        do {
            String text = " [ ENCONTRO FINAL ]\n" + alien.show() + "\n\n" + player.showSimple();
            int answer = Menu.options("PAQUERAR", "FUGIR", text, " O último encontro ", Game.imagem2);
            switch (answer) {
                // ACEITAR O ENCONTRO
                case 0:
                    if (player.getSeduction() == 15) {
                        textBox(styleBar() + alien.getWin() + styleBar());
                        textBox(styleBar() + player.getName()
                                + " ficou interessado no planeta de " + alien.getName()
                                + " e decidiu ir pra lá tentar a sorte. " + styleBar());
                        endGame();
                    } else {
                        // TOMOU FORA 
                        textBox(styleBar() + alien.getLose() + styleBar());
                        tryAgain();
                    }
                    break;
                // FUGIR    
                case 1:
                    textBox(" Ah Ah, você não pode fugir de mim! ");
                    break;
            }
        } while (combat != true);

    }

}
