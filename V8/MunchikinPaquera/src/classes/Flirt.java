package classes;

import java.util.ArrayList;

/*
    Charme - Nível de jogador
    Carteira - Dinheiro que ele possui
    Estilo - Traço especial dele que pode destacá-lo em um encontro
    Poder de Sedução - Soma dos bonus de cada item do jogador
 */
public class Flirt {

    private int charm;
    private final String name;
    private int wallet;
    ArrayList<GamePlay> gameplays = new ArrayList<>();
    ArrayList<Gift> gifts = new ArrayList<>();
    Clothe clothe;
    private Style style;
    private int seduction;
    private int handSpace;

    //Construtor
    public Flirt(String name, int charm, int wallet) {
        this.charm = charm;
        this.name = name;
        this.wallet = wallet;
        this.handSpace = 0;
        this.seduction = 0;
    }

    public Flirt() {
        this.name = "";
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

    //Retorna uma String com os itens de um jogador
    public String showItems() {
        String text = "\n\nLista de Itens Disponíveis:";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        text += getClothe().show();
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
        for (Gift sum : gifts){
            total += sum.bonus;
        }
        total += clothe.bonus;
        total += charm;
        setSeduction(total);
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

}
