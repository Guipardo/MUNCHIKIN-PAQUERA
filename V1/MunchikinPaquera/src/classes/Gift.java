package classes;

public class Gift extends Item {

    private int hand;

    public Gift(String name, int bonus, int price, int hand) {
        this.name = name;
        this.bonus = bonus;
        this.price = price;
        this.hand = hand;
    }

    //Mostra uma ficha completa do Presente
    @Override
    public String show() {
        return "\n[Presente] " + getName() + " - Bônus : " + getBonus()
                + "\nPreço : " + getPrice() + " ¢ \nEspaço nas mãos : " + hand;
    }

    //Mostra uma ficha resumida do Presente
    public String showSimple() {
        return "\n[Presente] " + getName() + " - Bônus : " + getBonus()
                + "\nPreço : " + getPrice() + " ¢ ";
    }

    // Getters e Setters
    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

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
