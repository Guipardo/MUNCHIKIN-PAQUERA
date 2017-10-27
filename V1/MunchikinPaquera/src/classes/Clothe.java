package classes;

public class Clothe extends Item {

    private final String hair;
    private final String top;
    private final String bottom;
    private final String shoes;
    private final String accessory;

    public Clothe(String name, int bonus, int price, String hair,
            String top, String bottom, String shoes, String accessory) {
        this.setName(name);
        this.setBonus(bonus);
        this.setPrice(price);
        this.hair = hair;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }

    // Mostra uma ficha completa da Roupa
    @Override
    public String show() {
        return "\n\nRoupa [ " + getName()
                + " ]\nBônus [ " + getBonus()
                + " ]\nPreço [ " + getPrice()
                + " ]\nPenteado [ " + hair
                + " ]\nSuperior [ " + top
                + " ]\nInferior [  " + bottom
                + " ]\nCalçado [ " + shoes
                + " ]\nAcessório [ " + accessory + " ]";
    }

    //Mostra uma ficha resumida da roupa
    public String showSimple() {
        return "\n[Roupa] " + getName() + " - Bônus " + getBonus();
    }

    // Getters e Setters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

}
