package model;

import jogo.GamePlay;
import java.io.Serializable;
import java.util.ArrayList;

public class Flirt implements Serializable {

    private int charm; // Charme
    private String name; // Nome
    private int wallet = 50; // Carteira
    GamePlay gameplay; // Histórico de gameplays
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

    // FICHA COMPLETA
    public String show() {
        return " Paquerador \n [" + name
                + "] - Carteira [ " + wallet
                + "$$]\n Charme [" + charm
                + "] - Poder de Sedução [" + getSeduction()
                + "]\n Espaço Ocupado : " + getHands()
                + "\n Estilo  " + style
                + "\n" + showItems();
    }

    // FICHA RESUMIDA
    public String showSimple() {
        return "[ " + name + " ]\n Poder [ " + getSeduction()
                + " ]\n Estilo [ " + style + " ]";
    }

    // MOSTRA GAMEPLAYS
    public void showGameplay() {
        System.out.println(gameplay.show());
    }

    // MOSTRA OS ITENS
    public String showItems() {
        String text = "\n [Lista de Itens Disponíveis] ";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        text += getClothe().show();
        return text;
    }

    // ATUALIZA O PODER DE SEDUÇÃO
    public void updateSeduction() {
        int total = 0;
        for (Gift sum : gifts) {
            total += sum.bonus;
        }
        total += clothe.bonus;
        this.setSeduction(total);
    }

    // MOSTRA OS PRESENTES
    public String showGifts() {
        String text = "\n\nLista de Itens Disponíveis:";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        return text;
    }

    // ADICIONA PRESENTE
    public void addGift(Gift item) {
        gifts.add(item);
        for (int i = 0; i < gifts.size(); i++) {
            this.seduction += gifts.get(i).getBonus();
        }
    }

    // REMOVE PRESENTE
    public void removeGift(int i) {
        gifts.remove(i);
    }

    // ADICIONA GAMEPLAY
    public void setGameplay(GamePlay g) {
        this.gameplay = g;
    }

    // GETTERS E SETTERS
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

    public GamePlay getGameplay() {
        return gameplay;
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
