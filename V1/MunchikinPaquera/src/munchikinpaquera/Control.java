package munchikinpaquera;

import classes.Clothe;
import classes.Dice;
import classes.Flirt;
import classes.GamePlay;
import classes.Gift;
import classes.Lady;
import classes.Meeting;
import classes.Round;
import classes.Style;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static munchikinpaquera.Jogo.sort;

public class Control {

    //Construtor
    public Control() {
    }

    //Retorna um Objeto Flirt
    /*
    1. O jogador insere o nome do personagem
    2. O jogador clica escolhe o estilo do personagem
     */
    public Flirt setFlirt() {
        String name = JOptionPane.showInputDialog("Digite seu nome\n");
        int style;
        do {
            Object[] options = {"ROMÂNTICO", "BADBOY", "ENGRAÇADO", "NERD", "GALÃ"};
            style = JOptionPane.showOptionDialog(null, "Escolha um estilo \n\n", "ESTILO", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            Flirt player = new Flirt(name, 1, 0); //Cria o Objeto Flirt
            switch (style) { //Define o estilo dele
                case 0:
                    player.setStyle(Style.ROMÂNTICO);
                    return player;
                case 1:
                    player.setStyle(Style.BADBOY);
                    return player;
                case 2:
                    player.setStyle(Style.ENGRAÇADO);
                    return player;
                case 3:
                    player.setStyle(Style.NERD);
                    return player;
                case 4:
                    player.setStyle(Style.GALÃ);
                    return player;
            }
        } while (style < 1 || style > 5);
        return null;
    }

    //Define os itens do Jogador
    public void setPlayerItems(Flirt p, ArrayList<Gift> gifts, ArrayList<Clothe> clothes) {

        // Gera os dois itens iniciais do jogador e adiciona na lista dele
        Gift beginGift = gifts.get(sort.nextInt(gifts.size())); //Sorteia
        p.setHands(p.getHands() + beginGift.getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
        Clothe beginClothe = clothes.get(sort.nextInt(clothes.size())); //Sorteia
        p.addGift(beginGift); //Adiciona presente
        p.setClothe(beginClothe); //Define roupa 
        JOptionPane.showMessageDialog(null, "Voce ganhou " + beginGift.showSimple()
                + beginClothe.showSimple()); //Mostra mensagem com os itens que recebeu
    }

    //Retorna uma Gameplay
    public GamePlay setGameplay() {
        int id = sort.nextInt(1000);
        GamePlay g = new GamePlay();
        return g;
    }

    //Retorna um Dado
    public Dice setDice() {
        Dice d = new Dice();
        return d;
    }

    //Retorna uma Lady
    public Lady setLady(ArrayList<Lady> ladys) {
        Lady l = ladys.get(sort.nextInt(ladys.size()));
        return l;
    }

    //Retorna um Presente
    public Gift setGift(ArrayList<Gift> gifts) {
        Gift g = gifts.get(sort.nextInt(gifts.size()));
        return g;
    }

    //Retorna um Round
    public Round setRound(Dice d) {
        Round r = new Round(1, d);
        return r;
    }

    //Retorna um Encontro
    public Meeting setMeeting(Lady l, Gift g) {
        Meeting m = new Meeting(1, l, g);
        return m;
    }

    // Executa um encontro
    /*
    Mostra a ficha da Lady e do Player, e mostra as opções de Paquerar ou fugir
    Se escolher Paquerar, ele compara o charme + bonus do player com a defesa da Lady
    Se for maior, ele ganha 1 de charme e recebe um item e a recompensa
    Se for menor, ele perde 1 de charme e volta para o menu de Jogador
     */
    public void doMeet(Meeting m, Flirt p, Menu menu, Lady l, Round r, Dice d,
            GamePlay newGame, Gift newGift) {
        String text = "[Encontro]\n\n" + l.showSimple() + "\n" + p.showSimple() + "\n";
        int answer = menu.showOption("PAQUERAR", "FUGIR", text, "ENCONTRO");
        if (answer == 0) { //ENCONTRO
            if ((p.getCharm() + p.getItemBonus()) > l.getDefense()) { // Compara os charmes
                menu.showText("Você conquistou a Dama!\n Seu charme aumentou em 1");
                p.setCharm(p.getCharm() + 1); //Adciona +1 ponto de charme
                if (p.getHands() == 1 && newGift.getHand() == 1) { //Uma mão livre
                    menu.showText("Voce ganhou " + newGift.showSimple());
                    p.addGift(newGift);
                } else if (p.getHands() == 0 && newGift.getHand() <= 2) { //Duas Mãos livres
                    menu.showText("Voce ganhou " + newGift.showSimple());
                    p.addGift(newGift);
                } else { //Nenhum espaço na mão
                    int op = menu.showOption("SIM", "NÃO", "Você está com as mãos cheias,"
                            + " deseja retirar " + p.getGifts().get(0).getName()
                            + " de sua mão", "SEM ESPAÇO");
                    switch (op) {
                        case 0: // Descarta o item
                            p.removeGift(0);
                            menu.showText("Voce ganhou " + newGift.showSimple());
                            p.addGift(newGift);
                            break;
                        case 1: //Segue com o jogo
                            break;
                    }
                }
                r.setStatus("Mandou bem!");
                newGame.addRound(r); //Adciona o round na gameplay
            }
        } else if (answer == 1) { //FUGIR
            d.roll(); //Rola o dado
            if (d.getValue() >= 5) { //Se tirar 5 ou 6 escapa ileso
                menu.showText("Você fugiu do encontro!");
                r.setStatus("Fugiu do Encontro");
                newGame.addRound(r);
            } else { //Se tirar menos que 5 Perde 1 de charme
                menu.showText("Você não conseguiu escapar dela!");
                r.setStatus("Tomou um fora");
                newGame.addRound(r);
                p.setCharm(p.getCharm() - 1);
            }
        }
    }
}
