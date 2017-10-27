package munchikinpaquera;

import java.util.Random;
import classes.Clothe;
import classes.Dice;
import classes.Flirt;
import classes.GamePlay;
import classes.Gift;
import classes.Lady;
import classes.Meeting;
import classes.Round;
import classes.Shop;
import classes.Style;
import java.applet.AudioClip;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Guilherme Rodrigues - 41523237
 * @author Marina Yumi - 31522051
 *
 * [Arrumar]
 *
 * Tratamento do nome do jogador Carregar Arquivo salvo Aparência dos menus
 * Bonus do faz meu tipo
 * Poder de sedução aumentando demais
 */
public class Jogo {

    public static Random sort = new Random(); // Gerador de numeros aleatórios
    public static int menuOption; //Opção do menu principal
    private static AudioClip music;
    //MAIN
    public static void main(String[] args) throws MalformedURLException {
        // JOPtionPane Aparência
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Georgia", Font.PLAIN, 15)));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.BOLD, 15)));
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 102));
        UIManager.put("Panel.background", new ColorUIResource(100, 0, 202));

        // GERANDO O JOGADOR E OS OUTROS ITENS DO JOGO
        Menu mainMenu = new Menu(); //Menu principal
        Menu gameMenu = new Menu(); //Menu do Jogo
        Menu shopMenu = new Menu(); //Menu da Loja
        Control c = new Control(); //Classe Controle - Faz a maioria das operações

        /* Cria as Ladys, Itens e Gifts -- Trocar depois por importação por texto */
        ArrayList<Lady> ladyList = new ArrayList<>();
        ArrayList<Gift> giftList = new ArrayList<>();
        ArrayList<Clothe> clotheList = new ArrayList<>();

        //Adcionando presentes novos
        Gift g1 = new Gift("Flores", 1, 100, 2);
        giftList.add(g1);
        Gift g2 = new Gift("Bombom", 1, 50, 1);
        giftList.add(g2);
        Gift g3 = new Gift("Anel", 2, 150, 1);
        giftList.add(g3);
        Gift g4 = new Gift("Diamante", 5, 350, 1);
        giftList.add(g4);
        Gift g5 = new Gift("Viagem", 3, 200, 2);
        giftList.add(g5);

        //Adcionando Ladys novas
        Lady l1 = new Lady("Cecília", 0, g1, Style.ENGRAÇADO);
        ladyList.add(l1);
        Lady l2 = new Lady("Heloísa", 1, g2, Style.ROMÂNTICO);
        ladyList.add(l2);
        Lady l3 = new Lady("Paula", 2, g3, Style.BADBOY);
        ladyList.add(l3);
        Lady l4 = new Lady("Mariana", 3, g4, Style.NERD);
        ladyList.add(l4);
        Lady l5 = new Lady("Rita", 4, g5, Style.GALÃ);
        ladyList.add(l5);

        //Adcionando roupas novas
        Clothe c1 = new Clothe("Social", 3, 250, "Corte Social", "Terno",
                "Calça Social", "Sapatos Sociais", "Gravata");
        clotheList.add(c1);
        Clothe c2 = new Clothe("Casual", 1, 50, "Topete", "Camisa Leve",
                "Shorts", "Chinelo", "Mochila");
        clotheList.add(c2);
        Clothe c3 = new Clothe("Praia", 1, 150, "Cabelo Molhado", "Sem Camisa",
                "Shorts Cargo", "Sandálias", "Prancha de Surf");
        clotheList.add(c3);
        Clothe c4 = new Clothe("Skatista", 1, 150, "Cabelo Colorido", "Camisa de Banda",
                "Shorts Marrom", "Sapatênis", "Skate");
        clotheList.add(c4);
        Clothe c5 = new Clothe("Inverno", 1, 150, "Touca", "Blusa",
                "Calça de Moletom", "Sapatos", "Cachecol");
        clotheList.add(c5);

        //Cria a loja com os itens
        Shop s = new Shop(); // Cria a Loja
        s.setGifts(giftList); // Adiciona os presentes na Loja
        s.setClothes(clotheList); // Adiciona as roupas na loja

        //Mostra o menu principal
        String intro = "\n  Onde o objetivo é a conquista  "
                + "\n\n Um jogo feito por: \n[ Marina Yumi ]\n[ Guilherme Rodrigues ] ";
        menuOption = mainMenu.showOption("NOVO JOGO", "CARREGAR JOGO", "SAIR", intro, "MUNCHIKIN PAQUERA V8");
        while (menuOption != 2) {
            switch (menuOption) {
                case 0:
                    Flirt player = c.setFlirt(); //Cria o Jogador
                    System.out.println(player.getSeduction());
                    mainMenu.showText(" Hey Bro, você já chegou no hotel? Eu sei que ta ansioso pra "
                            + "\n testar suas habilidades de paquera , mas antes eu vim te dar umas "
                            + "\n dicas pra tu se dar bem nos encontros. ");
                    mainMenu.showText(" Pra conquistar uma dama você precisa ter charme, cada uma \n"
                            + " delas é diferente, então presta atenção no estilo de cara que ela \n"
                            + " gosta, as roupas e os presentes aumentam suas chances de conseguir \n"
                            + " um bom encontro, então compre sempre do bom e do melhor. ");
                    mainMenu.showText(" Eu deixei uma lista com o número de várias gatas, agora você \n "
                            + " só precisa chamar pra sair.\nVou deixar uns presentes contigo, boa sorte. ");
                    c.setPlayerItems(player, giftList, clotheList); //Define os itens iniciais
                    System.out.println(player.getSeduction());
                    GamePlay newGame = c.setGameplay(); //Cria uma gameplay
                    Dice d = c.setDice(); //Cria um dado vazio
                    // Enquanto charme maior que 0 e menor que 10
                    int roundNumber = 0;
                    while (player.getCharm() != 0 && player.getCharm() < 10) {
                        //Mostra o menu do jogo
                        int action = gameMenu.showOption("IR AO ENCONTRO", "LOJA", player.show(), "JOGO");
                        switch (action) {
                            case 0: // ENCONTRO
                                Lady newLady = c.setLady(ladyList); //Escolhe uma Lady aleatória
                                Gift newGift = c.setGift(giftList); //Escolhe um presente
                                Round r = c.setRound(d); // Cria um novo round
                                roundNumber += 1;
                                r.setNumber(roundNumber);
                                Meeting M = c.setMeeting(newLady, newGift); //Cria um novo encontro
                                /* Faz um encontro usando como parâmetros o objeto encontro
                    o Jogador, o Menu de Jogo, uma Lady, um Round, Um Dado e uma Gameplay
                                 */
                                c.doMeet(M, player, gameMenu, newLady, r, d, newGame, newGift);
                                break;
                            case 1: // LOJA
                                //Mostra a Loja
                                int option = shopMenu.showOption("COMPRAR", "VENDER", " Bem vindo a Loja ", "LOJA");
                                int category;
                                switch (option) {
                                    case 0: //COMPRAR
                                        category = shopMenu.showOption("PRESENTE", "ROUPA", " Escolha a Categoria ", "LOJA");
                                        switch (category) {
                                            case 0:
                                                Gift nG = s.buyGift(player);
                                                if (player.getWallet() < nG.getPrice()) { //Confere se tem dinheiro
                                                    shopMenu.showText(" Você não tem dinheiro suficiente! ");
                                                } else { //COnfere se tem espaço nas mãos
                                                    if (player.getHands() == 1 && nG.getHand() == 1 && player.getWallet() > nG.getPrice()) { //Uma mão livre
                                                        shopMenu.showText(" Voce comprou " + nG.getName());
                                                        player.addGift(nG);
                                                        player.setHands(player.getHands() + nG.getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
                                                        player.setWallet(player.getWallet() - nG.getPrice()); //Desconta o valor do presente
                                                    } else if (player.getHands() == 0 && nG.getHand() <= 2 && player.getWallet() > nG.getPrice()) { //Duas Mãos livres
                                                        shopMenu.showText(" Voce comprou " + nG.getName());
                                                        player.addGift(nG);
                                                        player.setHands(player.getHands() + nG.getHand()); // Aumenta o espaço ocupado nas mãos do Jogador
                                                        player.setWallet(player.getWallet() - nG.getPrice()); //Desconta o valor do presente
                                                    } else { //Nenhum espaço na mão
                                                        shopMenu.showText(" Você não tem espaço suficiente nas mãos ");
                                                    }
                                                }
                                                break;
                                            case 1:
                                                Clothe nC = s.menuClothe(player);
                                                if (player.getWallet() > nC.getPrice()) { //Confere se tem dinhheiro
                                                    shopMenu.showText(" Voce comprou " + nC.getName());
                                                    player.setClothe(nC);
                                                    player.setWallet(player.getWallet() - nC.getPrice()); //Desconta o valor da roupa
                                                } else { //Não tem dinheiro para comprar a roupa
                                                    shopMenu.showText(" Você não tem dinheiro suficiente! ");
                                                }
                                                break;
                                        }
                                        break;
                                    case 1: //VENDER
                                        if (player.getGifts().isEmpty()) {
                                            shopMenu.showText(" Você não possui itens para vender! ");
                                        } else {
                                            Gift nG = s.sellGift(player);
                                            for (int i = 0; i < player.getGifts().size(); i++) {
                                                if (player.getGifts().contains(nG)) {
                                                    player.removeGift(i);
                                                    player.setWallet(player.getWallet() + nG.getPrice());
                                                    player.setHands(player.getHands() - nG.getHand());
                                                    shopMenu.showText(" Você vendeu "
                                                            + nG.getName() + " por " + nG.getPrice() + "$$ ");
                                                } else {
                                                    shopMenu.showText(" Você não tem esse presente! ");
                                                }
                                            }
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                    if (player.getCharm() == 10) { //Quando o Jogador chega ao nível 10 de charme
                        gameMenu.showText(
                                " Seu charme chegou à " + player.getCharm() + "\n Você é o maior paquerador ");
                        player.addGameplays(newGame);
                        player.showGameplays();
                    } else {
                        gameMenu.showText( //Quando o Jogador chega ao nível 0 de charme
                                " Seu charme chegou à " + player.getCharm() + "\n Você foi rejeitado. ");
                        player.addGameplays(newGame);
                        player.showGameplays();
                    }
                    menuOption = 2;
                    break;
                case 1:
                    //CARREGA PARTIDAS ANTERIORES
                    mainMenu.showText("CARREGANDO");
                    break;
            }
        }
    }
}
