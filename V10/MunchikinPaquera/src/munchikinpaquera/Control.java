package munchikinpaquera;

import classes.Clothe;
import classes.Dice;
import classes.Flirt;
import classes.GamePlay;
import classes.Gift;
import classes.Lady;
import classes.Meeting;
import classes.Style;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static munchikinpaquera.Game.sort;

public class Control {

    private Control instance;

    // CONSTRUTOR VAZIO
    public Control() {
    }

    public Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    // RETORNA UM NOVO OBJETO FLIRT (JOGADOR)
    public Flirt createFlirt(Menu menu) {
        Flirt player = new Flirt();
        player.setName(null);
        // ATRIBUI O NOME AO JOGADO
        player.setName(JOptionPane.showInputDialog(" Digite seu nome "));
        if (player.getName().isEmpty()) {
            player.setName("Jogador"); //Se deixar a caixa de texto vazia, preenche com um nome genérico
        }
        int style; // 
        style = menu.options("ROMÂNTICO", "BADBOY", "ENGRAÇADO", "NERD",
                "GALÃ", " Escolha o seu estilo ", "ESTILO");
        // ATRIBUI UM ESTILO AO JOGADOR
        switch (style) {
            case 0:
                player.setStyle(Style.ROMÂNTICO);
            case 1:
                player.setStyle(Style.BADBOY);
            case 2:
                player.setStyle(Style.ENGRAÇADO);
            case 3:
                player.setStyle(Style.NERD);
            case 4:
                player.setStyle(Style.GALÃ);
        }

        // ATRIBUI OS VALORES INICIAIS DO JOGADOR
        player.setCharm(
                1); // Charme
        player.setHandSpace(
                0); // Espaço livre em mãos pra carregar presentes
        player.setWallet(
                0); // Dinheiro na carteira
        return player;
    }

// DEFINE ITENS DO JOGADOR
    public void setPlayerItems(Flirt p, ArrayList<Gift> gifts, ArrayList<Clothe> clothes) {

        // DEFINE UM PRESENTE ALEATÓRIO
        Gift beginGift = gifts.get(sort.nextInt(gifts.size())); //Sorteia
        p.setHands(p.getHands() + beginGift.getHand()); // Aumenta o espaço ocupado nas mãos do Jogador

        // DEFINE UMA ROUPA ALEATÓRIA
        Clothe beginClothe = clothes.get(sort.nextInt(clothes.size())); //Sorteia

        // ATRIBUI O PRESENTE E A ROUPA AO JOGADOR 
        p.addGift(beginGift); // Presente
        p.setClothe(beginClothe); // Roupa

        // ATUALIZA O PODER DE SEDUÇÃO DO JOGADOR
        p.updateSeduction();

        // MOSTRA OS PRESENTES QUE FORAM RECEBIDOS
        JOptionPane.showMessageDialog(null, "\n Voce ganhou\n Presente - " + beginGift.getName()
                + " \n Roupa - " + beginClothe.getName());
    }

    // RETORNA UMA GAMEPLAY
    public GamePlay setGameplay() {
        int id = sort.nextInt(1000);
        GamePlay g = new GamePlay();
        return g;
    }

    // RETORNA UM DADO
    public Dice setDice() {
        Dice d = Dice.getInstance();
        return d;
    }

    // RETORNA UMA LADY ALEATÓRIA
    public Lady setLady(ArrayList<Lady> ladys) {
        Lady l = ladys.get(sort.nextInt(ladys.size()));
        return l;
    }

    // RETORNA UM PRESENTE ALEATÓRIO
    public Gift setGift(ArrayList<Gift> gifts) {
        Gift g = gifts.get(sort.nextInt(gifts.size()));
        return g;
    }

    // REALIZA ENCONTRO
    /*
    Mostra a ficha da Lady e do Player, e mostra as opções de Paquerar ou fugir
    Se escolher Paquerar, ele compara o charme + bonus do player com a defesa da Lady
    Se for maior, ele ganha 1 de charme e recebe um item e a recompensa
    Se for menor, ele perde 1 de charme e volta para o menu de Jogador
     */
    /**
     *
     * @param m
     * @param p
     * @param dice
     * @param newGame
     * @param menu
     */
    public void doMeet(Meeting m, Flirt p, Dice dice, GamePlay newGame, Menu menu) {

        // COMBAT DEFINE SE O ENCONTRO FOI BEM SUCEDIDO OU NÃO
        boolean combat;

        // CONFERE O TIPO DO FLIRT E O GOSTO DA LADY
        // SE FOREM IGUAIS, O FLIRT RECEBE +1 NESTA JOGADA
        // SENÃO, NÃO RECEBE NENHUM BÔNUS DE SEDUÇÃO
        if (p.getStyle().equals(m.getLady().getItsMyType())) {
            // BÔNUS DE +1 NA JOGADA
            combat = (p.getSeduction() + 1) >= m.getLady().getDefense();
            menu.textBox(" Ela gosta de " + m.getLady().getItsMyType()
                    + "\n Voce é " + p.getStyle()
                    + "\n Ganha um bônus nesse encontro! ");
        } else {
            combat = (p.getSeduction()) >= m.getLady().getDefense();
        }

        // MOSTRA O MENU DO ENCONTRO
        String text = " [ Encontro " + m.getNumber() + " ]\n" + m.getLady().show() + "\n\n" + p.showSimple();
        int answer = menu.options("PAQUERAR", "FUGIR", text, "ENCONTRO" + m.getNumber());

        switch (answer) {
            // ACEITAR O ENCONTRO
            case 0:
                if (combat) {
                    // +1 PONTO DE CHARME
                    menu.textBox(" Ela adorou o encontro! \n Seu charme aumentou em 1! ");
                    p.setCharm(p.getCharm() + 1);
                    p.updateSeduction();

                    // GANHA UM ITEM SE TIVER ESPAÇO NAS MÃOS
                    // SENÃO O ITEM É VENDIDO
                    // ESPAÇO LIVRE NAS MÃOS == 1 E PRESENTE OCUPA 1 DE ESPAÇO
                    // GANHA O ITEM
                    if (p.getHands() == 1 && m.getGift().getHand() == 1) {
                        // GANHA O ITEM
                        menu.textBox(" Voce ganhou " + m.getGift().showSimple());
                        p.addGift(m.getGift());
                        //AUMENTA O ESPAÇO EM MÃOS DO JOGADOR
                        p.setHands(p.getHands() + m.getGift().getHand());

                        // DUAS MÃOS LIVRES
                        // GANHA O ITEM
                    } else if (p.getHands() == 0 && m.getGift().getHand() <= 2) {
                        menu.textBox(" Voce ganhou " + m.getGift().showSimple());
                        p.addGift(m.getGift());
                        p.setHands(p.getHands() + m.getGift().getHand());

                        // SEM ESPAÇO LIVRE NAS MÃOS
                        // ITEM É CONVERTIDO EM DINHEIRO
                    } else {
                        p.setWallet(p.getWallet() + m.getGift().getPrice());
                        menu.textBox(" Você achou " + m.getGift().getPrice() + "$$ ");
                    }

                    // ADICIONA O ENCONTRO NA GAMEPLAY
                    if (combat == true) {
                        m.setStatus(" Mandou bem! ");
                    }
                    newGame.addMeet(m);
                } else {
                    // TOMOU FORA - PERDE 1 DE CHARME
                    menu.textBox(" Você tomou um fora! ");
                    p.setCharm(p.getCharm() - 1);
                    p.updateSeduction();
                }
                break;

            // FUGIR    
            case 1:
                // ROLA O DADO
                dice.roll();
                // TIRANDO 5 ou 6 ESCAPA ILESO
                if (dice.getValue() >= 5) {
                    menu.textBox(" Você disse que ia ao banheiro e foi embora! ");
                    m.setStatus(" Fugiu do Encontro ");
                    newGame.addMeet(m);
                    // SE TIRAR MENOS QUE 5 LEVA UM FORA
                } else {
                    menu.textBox(" Ela levanta e deixa a conta pra você! ");
                    m.setStatus(" Tomou um fora ");
                    newGame.addMeet(m);
                    p.setCharm(p.getCharm() - 1);
                    p.updateSeduction();
                }
                break;
        }
    }

    // COMPRAR PRESENTE
    public void buyGift(Flirt p, ArrayList<Gift> giftList, Menu menu) {
        int answer = menu.options(giftList.get(0).getName(),
                giftList.get(1).getName().toUpperCase(),
                giftList.get(2).getName().toUpperCase(),
                giftList.get(3).getName().toUpperCase(),
                giftList.get(4).getName().toUpperCase(),
                "CANCELAR",
                "Escolha seu presente \n"
                + " Carteira :" + p.getWallet()
                + "$$\n" + menu.showGifts(giftList), "PRESENTES");
        if (answer != 5) {
            // CONFERE SE TEM ESPAÇO NAS MÃOS
            // 1 ESPAÇO LIVRE NA MÃO E ITEM OCUPA 1 DE ESPAÇO
            if (p.getHands() == 1 && giftList.get(answer).getHand() == 1 && p.getWallet() > giftList.get(answer).getPrice()) {
                menu.textBox(" Voce comprou " + giftList.get(answer).getName());
                p.addGift(giftList.get(answer)); // Adciona o presente
                p.setHands(p.getHands() + giftList.get(answer).getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
                p.setWallet(p.getWallet() - giftList.get(answer).getPrice()); //Desconta o valor do presente
                p.updateSeduction(); // Atualiza o o poder de sedução

                // 2 ESPAÇOS LIVRES NAS MÃOS
            } else if (p.getHands() == 0 && giftList.get(answer).getHand() <= 2 && p.getWallet() > giftList.get(answer).getPrice()) { //Duas Mãos livres
                menu.textBox(" Voce comprou " + giftList.get(answer).getName());
                p.addGift(giftList.get(answer));
                p.setHands(p.getHands() + giftList.get(answer).getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
                p.setWallet(p.getWallet() - giftList.get(answer).getPrice()); //Desconta o valor do presente
                p.updateSeduction(); // Atualiza o o poder de sedução

            } else {
                menu.textBox(" Você não possui espaço em mãos! ");
            }

            if (p.getWallet() < giftList.get(answer).getPrice()) {
                menu.textBox(" Você não tem dinheiro suficiente! ");
            }
        }
    }
    // VENDER PRESENTE

    public void sellGift(Flirt p, ArrayList<Gift> giftList, Menu menu) {
        int answer = menu.options(giftList.get(0).getName(),
                giftList.get(1).getName().toUpperCase(),
                giftList.get(2).getName().toUpperCase(),
                giftList.get(3).getName().toUpperCase(),
                giftList.get(4).getName().toUpperCase(),
                "CANCELAR",
                "Escolha seu presente \n"
                + " Carteira :" + p.getWallet()
                + "$$\n" + p.showGifts(), "PRESENTES");
        if (answer != 5) {
            for (int i = 0; i < p.getGifts().size(); i++) {
                if (p.getGifts().contains(giftList.get(answer))) {
                    p.removeGift(i);
                    p.setWallet(p.getWallet() + giftList.get(answer).getPrice());
                    p.setHands(p.getHands() - giftList.get(answer).getHand());
                    menu.textBox(" Você vendeu "
                            + giftList.get(answer).getName() + " por " + giftList.get(answer).getPrice() + "$$ ");
                    p.updateSeduction(); // Atualiza o poder de sedução do jogador
                } else {
                    menu.textBox(" Você não tem esse presente! ");
                }
            }
        }
    }

// COMPRAR ROUPA
    public void buyClothe(Flirt p, ArrayList<Clothe> clotheList, Menu menu) {
        int answer = menu.options(clotheList.get(0).getName(),
                clotheList.get(1).getName().toUpperCase(),
                clotheList.get(2).getName().toUpperCase(),
                clotheList.get(3).getName().toUpperCase(),
                clotheList.get(4).getName().toUpperCase(),
                "CANCELAR",
                "Escolha sua roupa \n"
                + " Carteira :" + p.getWallet()
                + "$$\n" + menu.showClothes(clotheList), "ROUPAS");
        if (answer != 5) {
            // CONFERE SE TEM DINHEIRO
            if (p.getWallet() > clotheList.get(answer).getPrice()) {
                menu.textBox(" Voce comprou " + clotheList.get(answer).getName());
                p.setClothe(clotheList.get(answer));
                p.setWallet(p.getWallet() - clotheList.get(answer).getPrice()); //Desconta o valor da roupa
                p.updateSeduction(); // Atualiza o o poder de sedução
                // SEM DINHEIRO    
            } else {
                menu.textBox(" Você não tem dinheiro suficiente! ");
            }
        }
    }
}
