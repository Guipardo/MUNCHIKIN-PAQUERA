package munchikinpaquera;

import model.Clothe;
import jogo.Dice;
import model.Flirt;
import jogo.GamePlay;
import model.Gift;
import model.Lady;
import jogo.Meeting;
import model.Style;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static munchikinpaquera.Game.player;
import static munchikinpaquera.Game.run;
import static munchikinpaquera.Game.sort;

public class Control {

    // RETORNA UM NOVO OBJETO FLIRT (JOGADOR)
    public static Flirt createFlirt() {
        Flirt player = new Flirt();
        player.setName(null);
        // ATRIBUI O NOME AO JOGADOR
        try {
            player.setName(JOptionPane.showInputDialog(Menu.styleBar()
                    + " Novo Nome " + Menu.styleBar()));
            if (player.getName().isEmpty()) {
                player.setName("Jogador"); //Se deixar a caixa de texto vazia, preenche com um nome genérico
            }
        } catch (NullPointerException ex) {
            System.exit(0);
        }

        int style; // 
        style = Menu.options("ROMÂNTICO", "BADBOY", "ENGRAÇADO", "NERD",
                "GALÃ", Menu.styleBar() + " Escolha o seu estilo " + Menu.styleBar(), "ESTILO");
        // ATRIBUI UM ESTILO AO JOGADOR
        switch (style) {
            case 0:
                player.setStyle(Style.ROMÂNTICO);
                break;
            case 1:
                player.setStyle(Style.BADBOY);
                break;
            case 2:
                player.setStyle(Style.ENGRAÇADO);
                break;
            case 3:
                player.setStyle(Style.NERD);
                break;
            case 4:
                player.setStyle(Style.GALÃ);
                break;
        }

        // ATRIBUI OS VALORES INICIAIS DO JOGADOR
        player.setCharm(
                1); // Charme
        player.setHandSpace(
                0); // Espaço livre em mãos pra carregar presentes
        return player;
    }

// DEFINE ITENS DO JOGADOR
    public static void setPlayerItems(Flirt p, ArrayList<Gift> gifts, ArrayList<Clothe> clothes) {

        // DEFINE UM PRESENTE ALEATÓRIO
        Gift beginGift;
        do {
            beginGift = gifts.get(Game.sort.nextInt(gifts.size())); //Sorteia
        } while (beginGift.getBonus() >= 4);
        p.setHands(p.getHands() + beginGift.getHand()); // Aumenta o espaço ocupado nas mãos do Jogador

        // DEFINE UMA ROUPA ALEATÓRIA
        Clothe beginClothe;
        do {
            beginClothe = clothes.get(Game.sort.nextInt(clothes.size())); //Sorteia
        } while (beginClothe.getBonus() >= 4);

        // ATRIBUI O PRESENTE E A ROUPA AO JOGADOR 
        p.addGift(beginGift); // Presente
        p.setClothe(beginClothe); // Roupa

        // ATUALIZA O PODER DE SEDUÇÃO DO JOGADOR
        p.updateSeduction();

        // MOSTRA OS PRESENTES QUE FORAM RECEBIDOS
        JOptionPane.showMessageDialog(null, Menu.styleBar() + " Voce ganhou\n Presente - " + beginGift.getName()
                + " \n Roupa - " + beginClothe.getName() + Menu.styleBar());
    }

    // RETORNA UMA LADY ALEATÓRIA
    public static void setLady(ArrayList<Lady> ladys) {
        // DIFICULDADE DAS LADYS
        if (player.getCharm() <= 3) {
            do {
                Game.newLady = ladys.get(Game.sort.nextInt(ladys.size()));
            } while (Game.newLady.getDefense() > 4);
        } else if (player.getCharm() > 3 && player.getCharm() <= 6) {
            do {
                Game.newLady = ladys.get(Game.sort.nextInt(ladys.size()));
            } while (Game.newLady.getDefense() < 6 || Game.newLady.getDefense() > 8);
        } else {
            do {
                Game.newLady = ladys.get(Game.sort.nextInt(ladys.size()));
            } while (Game.newLady.getDefense() < 8);
        }
        
        
    }

    // RETORNA UM PRESENTE ALEATÓRIO
    public static Gift setGift(ArrayList<Gift> gifts) {
        Gift g = new Gift();
        if (player.getCharm() <= 3) {
            do {
                g = gifts.get(Game.sort.nextInt(gifts.size()));
            } while (g.getBonus() > 2);
        } else if (player.getCharm() > 3 && player.getCharm() <= 6) {
            do {
                g = gifts.get(Game.sort.nextInt(gifts.size()));
            } while (g.getBonus() > 3);
        } else {
            do {
                g = gifts.get(Game.sort.nextInt(gifts.size()));
            } while (g.getBonus() > 4);
        }
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
     * @param newGame
     */
    public static void doMeet(Meeting m, Flirt p, GamePlay newGame) {

        // COMBAT DEFINE SE O ENCONTRO FOI BEM SUCEDIDO OU NÃO
        boolean combat;

        // CONFERE O TIPO DO FLIRT E O GOSTO DA LADY
        // SE FOREM IGUAIS, O FLIRT RECEBE +1 NESTA JOGADA
        // SENÃO, NÃO RECEBE NENHUM BÔNUS DE SEDUÇÃO
        if (p.getStyle().equals(m.getLady().getItsMyType())) {
            // BÔNUS DE +1 NA JOGADA
            combat = (p.getSeduction() + 1) >= m.getLady().getDefense();
            Menu.textBox(Menu.styleBar() + " Ela gosta de " + m.getLady().getItsMyType()
                    + "\n Voce é " + p.getStyle()
                    + "\n Ganha um bônus nesse encontro! " + Menu.styleBar());
        } else {
            combat = (p.getSeduction()) >= m.getLady().getDefense();
        }

        // MOSTRA O MENU DO ENCONTRO
        String text = " [ ENCONTRO " + m.getNumber() + " ]\n" + m.getLady().show() + "\n\n" + p.showSimple();

        int answer = Menu.options("PAQUERAR", "FUGIR", text, "ENCONTRO" + m.getNumber(), Game.imagem2);
        switch (answer) {
            // ACEITAR O ENCONTRO
            case 0:
                if (combat) {
                    // +1 PONTO DE CHARME
                    Menu.textBox(Menu.styleBar() + "[" + m.getLady().getName()
                            + "] - " + m.getLady().getWin() + Menu.styleBar());
                    p.setCharm(p.getCharm() + 1);
                    p.updateSeduction();
                    m.setStatus(" Mandou bem! ");

                    // GANHA UM ITEM SE TIVER ESPAÇO NAS MÃOS
                    // SENÃO O ITEM É VENDIDO
                    // ESPAÇO LIVRE NAS MÃOS == 1 E PRESENTE OCUPA 1 DE ESPAÇO
                    // GANHA O ITEM
                    if (p.getHands() == 1 && m.getGift().getHand() == 1) {
                        // GANHA O ITEM
                        Menu.textBox(Menu.styleBar() + " Voce ganhou "
                                + m.getGift().showSimple() + Menu.styleBar());
                        p.addGift(m.getGift());
                        //AUMENTA O ESPAÇO EM MÃOS DO JOGADOR
                        p.setHands(p.getHands() + m.getGift().getHand());

                        // DUAS MÃOS LIVRES
                        // GANHA O ITEM
                    } else if (p.getHands() == 0 && m.getGift().getHand() <= 2) {
                        Menu.textBox(Menu.styleBar() + " Voce ganhou "
                                + m.getGift().showSimple() + Menu.styleBar());
                        p.addGift(m.getGift());
                        p.setHands(p.getHands() + m.getGift().getHand());

                        // SEM ESPAÇO LIVRE NAS MÃOS
                        // ITEM É CONVERTIDO EM DINHEIRO
                    } else {
                        p.setWallet(p.getWallet() + 100);
                        Menu.textBox(Menu.styleBar() + " Você ganhou 100 $$ "
                                + Menu.styleBar());
                    }

                    // ADCIONA ENCONTRO NA GAMEPLAY
                    newGame.addMeet(m);
                    p.updateSeduction();
                    p.setGameplay(newGame);

                } else {
                    // TOMOU FORA - PERDE 1 DE CHARME
                    Menu.textBox(Menu.styleBar() + "[" + m.getLady().getName()
                            + "] - " + m.getLady().getLose() + Menu.styleBar());
                    p.setCharm(p.getCharm() - 1);
                    m.setStatus(" Tomou um fora! ");
                    newGame.addMeet(m);
                    p.updateSeduction();
                    p.setGameplay(newGame);
                }
                break;

            // FUGIR    
            case 1:
                // ROLA O DADO
                Dice.roll();
                Menu.textBox(Menu.styleBar() + " Você rolou " + Dice.selectedFace
                        + " no dado! " + Menu.styleBar());
                // TIRANDO MAIS QUE 1 ESCAPA ILESO
                if (p.getCharm() <= 3 && Dice.selectedFace > 1) {
                    Menu.textBox(sortRun());
                    m.setStatus(" Fugiu do Encontro ");
                    newGame.addMeet(m);
                    p.updateSeduction();
                    p.setGameplay(newGame);
                    // TIRANDO MAIS QUE 3 ESCAPA ILESO
                } else if ((p.getCharm() > 3 && p.getCharm() <= 6) && Dice.selectedFace > 3) {
                    Menu.textBox(sortRun());
                    m.setStatus(" Fugiu do Encontro ");
                    newGame.addMeet(m);
                    p.updateSeduction();
                    p.setGameplay(newGame);
                    // TIRANDO 4 OU 5 ESCAPA ILESO
                } else if (Dice.selectedFace >= 5) {
                    Menu.textBox(sortRun());
                    m.setStatus(" Fugiu do Encontro ");
                    newGame.addMeet(m);
                    p.updateSeduction();
                    p.setGameplay(newGame);
                    // TOMOU UM FORA
                } else {
                    Menu.textBox(Menu.styleBar() + "[" + m.getLady().getName()
                            + "] - " + m.getLady().getLose() + Menu.styleBar());
                    m.setStatus(" Tomou um fora ");
                    newGame.addMeet(m);
                    p.setCharm(p.getCharm() - 1);
                    p.updateSeduction();
                    p.setGameplay(newGame);
                }
                break;
        }
    }

    // COMPRAR PRESENTE
    public static void buyGift(Flirt p, ArrayList<Gift> giftList) {
        int answer;
        do {
            answer = Menu.options(giftList.get(0).getName().toUpperCase(),
                    giftList.get(1).getName().toUpperCase(),
                    giftList.get(2).getName().toUpperCase(),
                    giftList.get(3).getName().toUpperCase(),
                    giftList.get(4).getName().toUpperCase(),
                    "VOLTAR",
                    Menu.styleBar() + " Escolha seu presente \n"
                    + " CARTEIRA :" + p.getWallet()
                    + "$$\n ESPAÇO OCUPADO :" + p.getHandSpace()
                    + Menu.showGifts(giftList) + Menu.styleBar(), "PRESENTES");
            if (answer != 5) {
                boolean haveMoney = false; // Tem dinheiro
                boolean haveSpace = false; // Tem espaço em mãos

                // CONFERE SE TEM DINHEIRO
                if (p.getWallet() >= giftList.get(answer).getPrice()) {
                    haveMoney = true;
                } else {
                    Menu.textBox(Menu.styleBar() + " Você não tem dinheiro suficiente! "
                            + Menu.styleBar());
                }

                // CONFERE SE TEM ESPAÇO EM MÃOS
                if ((p.getHands() == 1 && giftList.get(answer).getHand() == 1)
                        || (p.getHands() == 0 && giftList.get(answer).getHand() <= 2)) {
                    haveSpace = true;
                } else {
                    Menu.textBox(Menu.styleBar() + " Você não possui espaço em mãos! "
                            + Menu.styleBar());
                }

                if (haveSpace == true && haveMoney == true) {
                    Menu.textBox(Menu.styleBar() + " Voce comprou "
                            + giftList.get(answer).getName() + Menu.styleBar());
                    p.addGift(giftList.get(answer)); // Adciona o presente
                    p.setHands(p.getHands() + giftList.get(answer).getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
                    p.setWallet(p.getWallet() - giftList.get(answer).getPrice()); //Desconta o valor do presente
                    p.updateSeduction(); // Atualiza o o poder de sedução
                }
            }

        } while (answer != 5);
    }

    // VENDER PRESENTE
    public static void sellGift(Flirt p, ArrayList<Gift> giftList) {
        int answer;
        do {
            answer = Menu.options(giftList.get(0).getName().toUpperCase(),
                    giftList.get(1).getName().toUpperCase(),
                    giftList.get(2).getName().toUpperCase(),
                    giftList.get(3).getName().toUpperCase(),
                    giftList.get(4).getName().toUpperCase(),
                    "VOLTAR",
                    Menu.styleBar() + p.showGifts() + Menu.styleBar(), "PRESENTES");
            if (answer != 5) {

                if (p.getGifts().isEmpty()) {
                    Menu.textBox(Menu.styleBar()
                            + " Você não possui itens para vender! " + Menu.styleBar());
                } else {
                    for (int i = 0; i < p.getGifts().size(); i++) {
                        if (p.getGifts().get(i).getName().equals(giftList.get(answer).getName())) {
                            p.removeGift(i); // Remove o presente da lista
                            p.setWallet(p.getWallet() + giftList.get(answer).getPrice()); // Aumenta o dinheiro na carteira
                            p.setHands(p.getHands() - giftList.get(answer).getHand()); // Diminui o espaço em mãos
                            Menu.textBox(Menu.styleBar() + " Você vendeu "
                                    + giftList.get(answer).getName() + " por "
                                    + giftList.get(answer).getPrice() + "$$ "
                                    + Menu.styleBar());
                            p.updateSeduction(); // Atualiza o poder de sedução do jogador
                        } else {
                            Menu.textBox(Menu.styleBar() + " Você não tem esse presente! "
                                    + Menu.styleBar());
                        }
                    }
                }
            }
        } while (answer != 5);
    }

// COMPRAR ROUPA
    public static void buyClothe(Flirt p, ArrayList<Clothe> clotheList) {
        int answer;
        do {
            answer = Menu.options(clotheList.get(0).getName().toUpperCase(),
                    clotheList.get(1).getName().toUpperCase(),
                    clotheList.get(2).getName().toUpperCase(),
                    clotheList.get(3).getName().toUpperCase(),
                    clotheList.get(4).getName().toUpperCase(),
                    "VOLTAR",
                    Menu.styleBar() + " Escolha sua roupa \n"
                    + " CARTEIRA :" + p.getWallet()
                    + "$$\n" + Menu.showClothes(clotheList) + Menu.styleBar(), "ROUPAS");
            if (answer != 5) {
                // CONFERE SE TEM DINHEIRO
                if (p.getWallet() >= clotheList.get(answer).getPrice()) {
                    Menu.textBox(Menu.styleBar() + " Voce comprou "
                            + clotheList.get(answer).getName()
                            + Menu.styleBar());
                    p.setClothe(clotheList.get(answer)); // Define a nova roupa
                    p.setWallet(p.getWallet() - clotheList.get(answer).getPrice()); // Desconta o valor da roupa
                    p.updateSeduction(); // Atualiza o o poder de sedução
                    // SEM DINHEIRO    
                } else {
                    Menu.textBox(Menu.styleBar() + " Você não tem dinheiro suficiente! "
                            + Menu.styleBar());
                }
            }
        } while (answer != 5);
    }

    public static String sortRun() {
        String runText;
        runText = run.get(sort.nextInt(run.size()));
        return runText;
    }
}
