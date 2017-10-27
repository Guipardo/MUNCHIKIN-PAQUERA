package classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Flirt {

    private int charm; // Charme
    private String name; // Nome
    private int wallet; // Carteira
    ArrayList<GamePlay> gameplays = new ArrayList<>(); // Histórico de gameplays
    ArrayList<Gift> gifts = new ArrayList<>(); // Lista de  presentes
    Clothe clothe; // Roupa
    private Style style; // Estilo
    private int seduction; // Poder de sedução
    private int handSpace; // Espaço ocupado em mãos

    // CONSTRUTOR VAZIO
    public Flirt() {
    }

    // CONSTRUTOR 
    public Flirt(int charm, String name, int wallet, Clothe clothe, Style style, int seduction, int handSpace) {
        this.charm = charm;
        this.name = name;
        this.wallet = wallet;
        this.clothe = clothe;
        this.style = style;
        this.seduction = seduction;
        this.handSpace = handSpace;
    }

    //Mostra uma ficha completa do jogador
    public String show() {
        return " Paquerador \n [" + name
                + "] - Carteira [ " + wallet
                + "$$]\n Charme [" + charm
                + "] - Poder de Sedução [" + getSeduction()
                + "]\n Espaço Ocupado : " + getHands()
                + "\n Estilo  " + style
                + showItems();
    }

    //Mostra uma ficha resumida do jogador
    public String showSimple() {
        return "[ " + name + " ]\n Poder de Sedução [ " + getSeduction()
                + " ]\n- Estilo [ " + style + " ]";
    }

    //Mostra as Gameplays de um jogador
    public void showGameplays() {
        for (int i = 0; i < gameplays.size(); i++) {
            System.out.println(gameplays.get(i).show());
        }
    }

    //Retorna uma String com todos os itens de um jogador
    public String showItems() {
        String text = "\n\nLista de Itens Disponíveis:";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        text += getClothe().show();
        return text;
    }

    //Retorna uma String com os presentes de um jogador
    public String showGifts() {
        String text = "\n\nLista de Itens Disponíveis:";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        return text;
    }

    //Adciona um novo Item ao Jogador
    public void addGift(Gift item) {
        gifts.add(item);
        for (int i = 0; i < gifts.size(); i++) {
            this.seduction += gifts.get(i).getBonus();
        }
    }

    //Remove um Item do Jogador
    public void removeGift(int i) {
        gifts.remove(i);
    }

    //Adciona uma Gameplay ao Jogador
    public void addGameplays(GamePlay g) {
        gameplays.add(g);
    }

    // Getters e Setters
    public void setStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    public int getCharm() {
        return charm;
    }

    public void setCharm(int charm) {
        this.charm = charm;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    public int getSeduction() {
        return seduction;
    }

    public void setSeduction(int seduction) {
        this.seduction = seduction;
    }

    public void updateSeduction() {
        int total = 0;
        for (Gift sum : gifts) {
            total += sum.bonus;
        }
        total += clothe.bonus;
        total += charm;
        this.setSeduction(total);
    }

    public ArrayList<GamePlay> getGameplays() {
        return gameplays;
    }

    public void setGameplays(ArrayList<GamePlay> gameplays) {
        this.gameplays = gameplays;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public Clothe getClothe() {
        return clothe;
    }

    public void setClothe(Clothe clothe) {
        this.clothe = clothe;
    }

    public int getHands() {
        return handSpace;
    }

    public void setHands(int hands) {
        this.handSpace = hands;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHandSpace() {
        return handSpace;
    }

    public void setHandSpace(int handSpace) {
        this.handSpace = handSpace;
    }
}
