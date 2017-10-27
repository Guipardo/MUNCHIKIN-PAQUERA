package classes;

public class Clothe extends Item {

    private final String hair; // Cabelo
    private final String top; // Roupa de cima
    private final String bottom; // Roupa de baixo
    private final String shoes; // Sapatos
    private final String accessory; // Acessório

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
        return "\n\n[Roupa " + getName()
                + " ]\n-Bônus [" + getBonus()
                + "]\n-Preço [ " + getPrice()
                + "$$ ]\n[Penteado] " + hair
                + "\n[Superior] " + top
                + "\n[Inferior] " + bottom
                + "\n[Calçado] " + shoes
                + "\n[Acessório] " + accessory;
    }

    //Mostra uma ficha resumida da roupa
    public String showSimple() {
        return "\n [Roupa] " + getName()
                + " - Bônus: " + getBonus()
                + " - Preço: " + getPrice()
                + "$$";
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
