package classes;

public class Gift extends Item {

    private int hand;

    public Gift(String name, int bonus, int price, int hand) {
        super();
        this.name = name;
        this.bonus = bonus;
        this.price = price;
        this.hand = hand;
    }

    //Mostra uma ficha completa do Presente
    @Override
    public String show() {
        return "\n Presente \n [" + getName() + " ]\n -Bônus [" + getBonus()
                + "]\n -Preço [" + getPrice() + "$$]\n -Espaço em mãos ["
                + hand + "]";
    }

    //Mostra uma ficha resumida do Presente
    public String showSimple() {
        return "\n [Presente] " + getName()
                + " - Bônus [" + getBonus()
                + "] - Preço " + getPrice()
                + "$$";
    }

    // Getters e Setters
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
