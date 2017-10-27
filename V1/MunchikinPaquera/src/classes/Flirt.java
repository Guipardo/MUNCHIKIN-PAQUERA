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
    private int itemBonus;
    private int hands;

    //Construtor
    public Flirt(String name, int charm, int wallet) {
        this.charm = charm;
        this.name = name;
        this.wallet = wallet;
        this.hands = 0;
    }

    public Flirt() {
        this.name = "";
    }

    //Mostra uma ficha completa do jogador
    public String show() {
        return "[ " + name + " ] Paquerador - Charme [ " + charm
                + " ]\nCarteira : " + wallet
                + " ¢ - Estilo : " + style + showItems();
    }

    //Mostra uma ficha resumida do jogador
    public String showSimple() {
        return "[ " + name + " ] "
                + "\nCharme : " + charm
                + " / Poder de Sedução : " + (getItemBonus() + getCharm())
                + "\nCarteira : " + wallet
                + " ¢ / Estilo : " + style
                + showItems();
    }

    //Mostra as Gameplays de um jogador
    public void showGameplays() {
        for (int i = 0; i < gameplays.size(); i++) {
            System.out.println(gameplays.get(i).show());
        }
    }

    //Retorna uma String com os itens de um jogador
    public String showItems() {
        String text = "\n\nLista de Itens:\n";
        for (int i = 0; i < gifts.size(); i++) {
            text += gifts.get(i).show();
        }
        return text;
    }

    //Adciona um novo Item ao Jogador
    public void addGift(Gift item) {
        gifts.add(item);
        for (int i = 0; i < gifts.size(); i++) {
            this.itemBonus += gifts.get(i).getBonus();
        }
    }

    //Remove um Item do Jogador
    public void removeGift(int index) {
        gifts.remove(index);
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

    public int getItemBonus() {
        return itemBonus;
    }

    public void setItemBonus(int itemBonus) {
        this.itemBonus = itemBonus;
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
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

}
