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
import java.util.ArrayList;

/**
 *
 * @author Guilherme Rodrigues
 * @author Marina Yumi
 *
 * [Arrumar]
 * Loja - Compra e Venda
 * Carregamento do Jogo
 * Ladys, Presentes e Roupas pré-definidas via txt
 * Mão em excessos, liberar itens da mão quando cheia
 * Tratamento do nome do Personagem
 * Aumento do dinheiro do Jogador - Jogador não ganha dinheiro quando vence a Lady
 */
public class Jogo {

    public static Random sort = new Random(); // Gerador de numeros aleatórios
    public static int answer; // Variável que pega as respostas dos menus

    //MAIN
    public static void main(String[] args) {

        // GERANDO O JOGADOR E OS OUTROS ITENS DO JOGO
        Menu mainMenu = new Menu(); //Menu principal
        Menu gameMenu = new Menu(); //Menu do Jogo
        Menu shopMenu = new Menu(); //Menu da Loja
        Control c = new Control(); //Classe Controle - Faz a maioria das operações

        /* Cria as Ladys, Itens e Gifts -- Trocar depois por importação por texto */
        ArrayList<Lady> ladyList = new ArrayList<>();
        ArrayList<Gift> giftList = new ArrayList<>(2);
        ArrayList<Clothe> clotheList = new ArrayList<>(1);

        //Adcionando Ladys novas
        Lady l1 = new Lady("Cecília", 0, 100, Style.ENGRAÇADO);
        ladyList.add(l1);
        Lady l2 = new Lady("Heloísa", 1, 200, Style.ROMÂNTICO);
        ladyList.add(l2);
        Lady l3 = new Lady("Paula", 2, 150, Style.BADBOY);
        ladyList.add(l3);
        Lady l4 = new Lady("Mariana", 3, 500, Style.NERD);
        ladyList.add(l4);
        Lady l5 = new Lady("Rita", 4, 250, Style.GALÃ);
        ladyList.add(l5);

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
        answer = mainMenu.showOption("NOVO JOGO", "CARREGAR JOGO", "Selecione uma opção", "MUNCHIKIN PAQUERA V5");
        switch (answer) {
            case 0:
                Flirt player = c.setFlirt(); //Cria o Jogador
                c.setPlayerItems(player, giftList, clotheList); //Define os itens iniciais
                GamePlay newGame = c.setGameplay(); //Cria uma gameplay
                Dice d = c.setDice(); //Cria um dado vazio

                // Enquanto charme maior que 0 e menor que 10
                while (player.getCharm() != 0 && player.getCharm() < 10) {
                    //Mostra o menu do jogo
                    int action = gameMenu.showOption("IR AO ENCONTRO", "LOJA", player.show(), "JOGO");
                    switch (action) {
                        case 0: // ENCONTRO
                            Lady newLady = c.setLady(ladyList); //Escolhe uma Lady aleatória
                            Gift newGift = c.setGift(giftList); //Escolhe um presente
                            Round r = c.setRound(d); // Cria um novo round
                            Meeting M = c.setMeeting(newLady, newGift); //Cria um novo encontro

                            /* Faz um encontro usando como parâmetros o objeto encontro
                    o Jogador, o Menu de Jogo, uma Lady, um Round, Um Dado e uma Gameplay
                             */
                            c.doMeet(M, player, gameMenu, newLady, r, d, newGame, newGift);
                            break;
                        case 1: // LOJA
                            //Mostra a Loja
                            int option = shopMenu.showOption("COMPRAR", "VENDER", "Bem vindo a Loja", "LOJA");
                            switch (option) {
                                case 0: //COMPRAR
                                    int category = shopMenu.showOption("PRESENTE", "ROUPA", "Escolha a Categoria", "LOJA");
                                    switch (category) {
                                        case 0:
                                            Gift nG = s.buyGift();
                                        //ARRUMAR ESSA PARTE
                                        case 1:
                                            Clothe nC = s.buyClothe();
                                            break;
                                    }
                                    break;
                                case 1: //VENDER
                                    break;
                            }
                            break;
                    }
                }
                if (player.getCharm() == 10) { //Quando o Jogador chega ao nível 10 de charme
                    gameMenu.showText(
                            "Seu charme chegou à " + player.getCharm() + "\n Você é o maior paquerador");
                    player.addGameplays(newGame);
                    player.showGameplays();
                } else {
                    gameMenu.showText( //Quando o Jogador chega ao nível 0 de charme
                            "Seu charme chegou à " + player.getCharm() + "\n Você foi rejeitado");
                    player.addGameplays(newGame);
                    player.showGameplays();
                }

                break;
            case 1:
                //CARREGA PARTIDAS ANTERIORES
                mainMenu.showText("CARREGANDO"); 
                break;
        }
    }
}
