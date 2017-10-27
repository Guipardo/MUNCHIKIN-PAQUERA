package munchikinpaquera;

import java.util.Random;
import classes.Clothe;
import classes.Dice;
import classes.Flirt;
import classes.GamePlay;
import classes.Gift;
import classes.Lady;
import classes.Meeting;
import classes.Style;
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
 * @author Marina Yumi - 31522051 /
*/


// Arrumar
// Fazer importação das ladys, presentes e itens
// Carregar Jogo
// Salvar Jogo

public class Game {

    // GERADOR DE NUMEROS ALEATÓRIOS
    public static Random sort = new Random();
    // OPÇÃO DO MENU PRINCIPAL
    public static int menuOption;

    // MAIN
    public static void main(String[] args) throws MalformedURLException {
        // JOPtionPane - CONFGURAÇÕES DE APARÊNCIA
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Georgia", Font.PLAIN, 15)));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.BOLD, 15)));
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 102));
        UIManager.put("Panel.background", new ColorUIResource(100, 0, 202));

        // CLASSE CONTROLE - REALIZA AS OPERAÇÕES NO JOGO
        Control control = new Control();
        Menu menu = new Menu();

        // COLEÇÕES DE LADYS, ITENS e GIFTS
        // Trocar por importação de arquivo txt 
        ArrayList<Lady> ladyList = new ArrayList<>();
        ArrayList<Gift> giftList = new ArrayList<>();
        ArrayList<Clothe> clotheList = new ArrayList<>();

        // SUBSTITUIR POR FACTORS
        // CRIANDO PRESENTES NOVOS
        // Adiciona na coleção de presentes
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

        // CRIANDO NOVAS LADYS
        // Adiciona na coleção de ladys
        Lady l1 = new Lady("Cecília", 1, g1, Style.ENGRAÇADO);
        ladyList.add(l1);
        Lady l2 = new Lady("Heloísa", 2, g2, Style.ROMÂNTICO);
        ladyList.add(l2);
        Lady l3 = new Lady("Paula", 3, g3, Style.BADBOY);
        ladyList.add(l3);
        Lady l4 = new Lady("Mariana", 4, g4, Style.NERD);
        ladyList.add(l4);
        Lady l5 = new Lady("Rita", 5, g5, Style.GALÃ);
        ladyList.add(l5);

        // CRIANDO NOVAS ROUPAS
        // Adiciona na coleção de roupas 
        Clothe c1 = new Clothe("Social", 3, 250, "Corte Social", "Terno",
                "Calça Social", "Sapatos Sociais", "Gravata");
        clotheList.add(c1);
        Clothe c2 = new Clothe("Casual", 1, 50, "Topete", "Camisa Leve",
                "Shorts", "Chinelo", "Mochila");
        clotheList.add(c2);
        Clothe c3 = new Clothe("Praia", 2, 150, "Cabelo Molhado", "Sem Camisa",
                "Shorts Cargo", "Sandálias", "Prancha de Surf");
        clotheList.add(c3);
        Clothe c4 = new Clothe("Skatista", 1, 150, "Cabelo Colorido", "Camisa de Banda",
                "Shorts Marrom", "Sapatênis", "Skate");
        clotheList.add(c4);
        Clothe c5 = new Clothe("Inverno", 2, 150, "Touca", "Blusa",
                "Calça de Moletom", "Sapatos", "Cachecol");
        clotheList.add(c5);

        // MENU PRINCIPAL
        String intro = "\n  Onde o objetivo é a conquista  "
                + "\n\n Um jogo feito por: \n[ Marina Yumi ]\n[ Guilherme Rodrigues ] ";
        menuOption = menu.options("NOVO JOGO", "CARREGAR JOGO", "SAIR", intro, "MUNCHIKIN PAQUERA V10");

        // ESCOLHE A OPÇÃO PELO RETORNO DO BOTÃO 
        // FECHA O JOGO QUANDO SELECIONADO O BOTÃO SAIR
        switch (menuOption) {
            // NOVO JOGO
            case 0:
                // CRIA UM JOGADOR
                Flirt player = control.createFlirt(menu);

                // INTRODUÇÃO DO JOGO
                menu.textBox(" Hey Bro, você já chegou no hotel? Eu sei que ta ansioso pra "
                        + "\n testar suas habilidades de paquera , mas antes eu vim te dar umas "
                        + "\n dicas pra tu se dar bem nos encontros. ");
                menu.textBox(" Pra conquistar uma dama você precisa ter charme, cada uma \n"
                        + " delas é diferente, então presta atenção no estilo de cara que ela \n"
                        + " gosta, as roupas e os presentes aumentam suas chances de conseguir \n"
                        + " um bom encontro, então compre sempre do bom e do melhor. ");
                menu.textBox(" Eu deixei uma lista com o número de várias gatas, agora você \n "
                        + " só precisa chamar pra sair.\nVou deixar uns presentes contigo, boa sorte. ");

                // O JOGADOR RECEBE UMA ROUPA ALEATÓRIA E UM PRESENTE ALEATÓRIO
                control.setPlayerItems(player, giftList, clotheList);

                // CRIA UMA GAMEPLAY
                GamePlay newGame = control.setGameplay();

                // CRIA UM DADO
                Dice dice = control.setDice(); //Cria um dado vazio

                // CONTADOR DE ROUNDS
                int meetNumber = 0;

                // ENQUANTO CHARME MAIOR QUE 0 E MENOR QUE 10
                while (player.getCharm() > 0 && player.getCharm() < 10) {
                    // MENU DO JOGO
                    int action = menu.options("IR AO ENCONTRO", "LOJA", "SALVAR", "SAIR", player.show(), "JOGO");
                    switch (action) {
                        // ENCONTRO
                        case 0:
                            // O CONTROLE ESCOLHE UMA LADY ALEATÓRIA E UM PRESENTE ALEATÓRIO
                            Lady newLady = control.setLady(ladyList); //Escolhe uma Lady aleatória
                            Gift newGift = control.setGift(giftList); //Escolhe um presente

                            // INCREMENTA O CONTADOR DE ROUND
                            meetNumber += 1;
                            // CRIA UM NOVO ENCONTRO
                            Meeting M = new Meeting(meetNumber, newLady, newGift, dice);
                            // DEFINE O NÚMERO DO ENCONTRO
                            M.setNumber(meetNumber);

                            // O CONTROLE REALIZA UM ENCONTRO
                            // DEPOIS DO ENCONTRO, VOLTA PARA O MENU DE JOGO
                            control.doMeet(M, player, dice, newGame, menu);
                            //Quando o Jogador chega ao nível 10 de charme
                            if (player.getCharm() == 10) {
                                menu.textBox(
                                        " Seu charme chegou à " + player.getCharm() + "\n Você é o maior paquerador ");
                                player.addGameplays(newGame);
                                player.showGameplays();
                                //Quando o Jogador chega ao nível 0 de charme
                            } else if (player.getCharm() > 0) {
                                menu.textBox(
                                        " Você está com  " + player.getCharm() + " de charme. ");
                            } else {
                                menu.textBox(
                                        " Seu charme chegou à " + player.getCharm() + "\n Você foi rejeitado. ");
                                player.addGameplays(newGame);
                                player.showGameplays();
                            }
                            break;
                        // LOJA
                        case 1:
                            // MENU DA LOJA
                            int option = menu.options("COMPRAR", "VENDER", "VOLTAR", "Bem vindo a Loja ", "LOJA");
                            // CATEGORIA ( COMPRAR OU VENDER )
                            int category;
                            switch (option) {
                                // COMPRAR
                                case 0:
                                    category = menu.options("PRESENTE", "ROUPA", " Escolha a Categoria ", "LOJA");
                                    // MENU LOJA
                                    switch (category) {
                                        case 0:
                                            // COMPRAR PRESENTE
                                            control.buyGift(player, giftList, menu);
                                            break;
                                        case 1:
                                            // COMPRAR ROUPA
                                            control.buyClothe(player, clotheList, menu);
                                            break;
                                    }
                                    break;
                                // VENDER PRESENTE
                                case 1:
                                    if (player.getGifts().isEmpty()) {
                                        menu.textBox(" Você não possui itens para vender! ");
                                    } else {
                                        control.sellGift(player, giftList, menu);
                                    }
                                    break;
                                // VOLTAR PARA O MENU DE JOGO
                                default:
                                    break;
                            }
                            break;
                        case 2:
                            menu.textBox(" Seu jogo foi salvo! ");
                            break;
                        case 3:
                            menu.textBox(" Obrigado por Jogar MUNCHIKIN PAQUERA! ");
                            System.exit(0);
                    }
                }
                break;
            case 1:
                //CARREGA PARTIDAS ANTERIORES
                menu.textBox(" CARREGANDO ");
                break;
            case 2:
                menu.textBox(" Obrigado por Jogar MUNCHIKIN PAQUERA! ");
                break;
        }
    }
}
