package munchikinpaquera;

import java.util.Random;
import model.Clothe;
import model.Flirt;
import jogo.GamePlay;
import model.Gift;
import model.Lady;
import io.IOManager;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Guilherme Rodrigues - 41523237
 * @author Marina Yumi - 31522051
 */
//Erro do botaõ cancelar
public class Game {

    // GERADOR DE NUMEROS ALEATÓRIOS
    public static Random sort = new Random();
    // OPÇÃO DO MENU PRINCIPAL
    public static int menuOption;
    // FRASES DE FUGA
    public static ArrayList<String> run = new ArrayList<>();
    // COLEÇÕES DE LADYS, ITENS e GIFTS
    public static ArrayList<Lady> ladyList = new ArrayList<>();
    public static ArrayList<Gift> giftList = new ArrayList<>();
    public static ArrayList<Clothe> clotheList = new ArrayList<>();

    // ARQUIVOS DE CONFIGURAÇÃO
    public static File GiftFile = new File("Gift.txt");
    public static File ClotheFile = new File("Clothe.txt");
    public static File LadyFile = new File("Lady.txt");

    // CONFIGURAÇÕES DE JOGO
    public static Flirt player = null; //Jogador vazio
    public static int meetNumber; //Número eo encontro (contador)
    public static GamePlay newGame = new GamePlay(); //Gameplay vazia
    public static Lady newLady = new Lady(); // Lady vazia

    // IMAGENS
    public static ImageIcon imagem = new ImageIcon(Menu.class.getResource("capa.png"));
    public static ImageIcon imagem2 = new ImageIcon(Game.class.getResource("meet.jpg"));

    // MAIN
    public static void main(String[] args) {
        
        JOptionConfigurations(); // Configurações de Aparência do JOptionPane
        importItens(); // Importação dos itens do jogo
        setRuns(); //Define as frases de fuga do personagem

        play();
        Menu.showMainMenu(); // Mostra o menu principal
        switch (menuOption) {

            // NOVO JOGO
            case 0:
                Menu.loadGame();
                try {
                    if (player.getCharm() == 10) {
                        Menu.alien();
                    }
                } catch(NullPointerException ex){
                    ex.getMessage();
                }
            case 1:
                Menu.newGame();
                meetNumber = newGame.getNumber();
                // ENQUANTO CHARME MAIOR QUE 0 E MENOR QUE 10
                while (player.getCharm() > 0 && player.getCharm() < 10) {
                    int action = Menu.gameMenu();
                    switch (action) {
                        // HISTÓRICO DE PARTIDAS
                        case 0:
                            Menu.showHistoric();
                            break;
                        // ENCONTRO
                        case 1:
                            Menu.meetMenu();
                            if (player.getCharm() == 10) { // QUANDO O JOGADOR CHEGA EM 10 DE CHARME GANHA
                                Menu.win();
                                Menu.showHistoric();
                                Menu.endGame();

                            } else if (player.getCharm() > 0) { // CONTINUA O JOGO
                                Menu.showStatus();

                            } else { // QUANDO O JOGADOR CHEGA EM 0 DE CHARME PERDE
                                Menu.lose();
                                Menu.showHistoric();
                                Menu.endGame();
                            }
                            break;

                        // LOJA
                        case 2:
                            int option = Menu.shopMenu();
                            switch (option) {
                                // PRESENTES
                                case 0:
                                    int category = Menu.category(); // CATEGORIA (COMPRAR OU VENDER)
                                    switch (category) {
                                        case 0:
                                            // COMPRAR PRESENTE
                                            Menu.buyGift();
                                            break;
                                        case 1:
                                            //VENDER PRESENTE
                                            Menu.sellGift();
                                            break;
                                    }
                                    break;
                                // ROUPA
                                case 1:
                                    // COMPRAR ROUPA
                                    Menu.buyClothe();
                                    break;
                                // VOLTAR PARA O MENU DE JOGO
                                default:
                                    break;
                            }
                            break;

                        // SALVAR JOGO
                        case 3:
                            Menu.saveGame();
                            break;

                        // SAIR DO JOGO
                        case 4:
                            Menu.endGame();
                    }
                }
                break;

            // SAIR DO JOGO
            case 2:
                Menu.endGame();
                break;
            default:
                break;
        }
    }

    public static void addRun(String text) {
        run.add(text);
    }

    public static void JOptionConfigurations() {
        // JOPtionPane - CONFGURAÇÕES DE APARÊNCIA
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);// LETRA
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 0)); //BORDA
        UIManager.put("Panel.background", new ColorUIResource(128, 0, 128)); // FUNDO
        UIManager.put("Button.background", Color.white); //FUNDO BOTÃO
        UIManager.put("Button.foreground", new ColorUIResource(128, 0, 128)); // FONTE BOTÃO
    }

    public static void importItens() {
        // IMPORTAÇÃO DOS PRESENTES
        IOManager.importGifts(giftList, GiftFile);
        IOManager.importClothes(clotheList, ClotheFile);
        IOManager.importLadys(ladyList, LadyFile);

    }

    public static void setRuns() {
        // FRASES DE FUGA
        addRun(Menu.styleBar() + " Você disse que ia ao banheiro e fugiu " + Menu.styleBar());
        addRun(Menu.styleBar() + " Ela bebeu demais e você aproveitou pra ir embora " + Menu.styleBar());
        addRun(Menu.styleBar() + " Você pediu para ela fechar os olhos e foi embora " + Menu.styleBar());
        addRun(Menu.styleBar() + " Aconteceu um assalto e você foi levado de refém " + Menu.styleBar());
        addRun(Menu.styleBar() + " Ela foi ao banheiro, você pagou a conta e foi embora " + Menu.styleBar());
        addRun(Menu.styleBar() + " Você simplesmente saiu. " + Menu.styleBar());
        addRun(Menu.styleBar() + " Você desmaiou " + Menu.styleBar());
        addRun(Menu.styleBar() + " Ela encontrou uma amiga e começou a conversar, você foi embora " + Menu.styleBar());
    }

    public static void play() {
        try {
            File soundFile = new File("soundtrack.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            clip.loop(1000);

        } catch (IOException e) {
            Menu.msg(" Erro de entrada ");
        } catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Menu.msg(ex.getMessage());
        }
    }
}
