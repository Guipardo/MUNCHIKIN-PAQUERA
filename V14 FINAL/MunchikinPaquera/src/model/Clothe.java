package model;

import java.io.Serializable;

public class Clothe extends Item implements Serializable {

    private final String hair; // Cabelo
    private final String top; // Roupa de cima
    private final String bottom; // Roupa de baixo
    private final String shoes; // Sapatos
    private final String accessory; // Acessório

    // CONSTRUTOR
    public Clothe(String name, int bonus, int price, String hair,
            String top, String bottom, String shoes, String accessory) {
        this.name = name;
        this.bonus = bonus;
        this.price = price;
        this.hair = hair;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }

    // Mostra uma ficha completa da Roupa
    @Override
    public String show() {
        return "\n\n\t [Roupa " + getName()
                + " ]\n\t -Bônus [" + getBonus()
                + "]\n\t -Preço [ " + getPrice()
                + "$$ ]\n\t [Penteado] " + hair
                + "\n\t [Superior] " + top
                + "\n\t [Inferior] " + bottom
                + "\n\t [Calçado] " + shoes
                + "\n\t [Acessório] " + accessory;
    }

    //Mostra uma ficha resumida da roupa
    @Override
    public String showSimple() {
        return "\n [" + getName() + "] [+" + getBonus()
                + "] Preço [" + getPrice() + "$$] ";
    }

    // Getters e Setters
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
