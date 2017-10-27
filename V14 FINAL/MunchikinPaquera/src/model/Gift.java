package model;

import java.io.Serializable;

public class Gift extends Item implements Serializable {

    private int hand; // Espaço que ocupa em mãos

    // CONSTRUTOR 
    public Gift(String name, int bonus, int price, int hand) {
        super();
        this.name = name; //Nome
        this.bonus = bonus; //Bonus
        this.price = price; //Preço
        this.hand = hand; //Espaço em mãos
    }

    // CONSTRUTOR
    public Gift() {
    }

    // FICHA COMPLETA
    @Override
    public String show() {
        return "\n\t Presente \n\t [" + getName()
                + "]\n\t -Bônus [" + getBonus()
                + "]\n\t -Preço [" + getPrice()
                + "$$]\n\t -Espaço em mãos [" + hand + "]";
    }

    // FICHA RESUMIDA
    @Override
    public String showSimple() {
        return "\n [" + getName()
                + "] [+" + getBonus()
                + "] Preço [" + getPrice()
                + "$$] Espaço [" + getHand() + "] ";
    }

    // GETTERS E SETTERS
    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
