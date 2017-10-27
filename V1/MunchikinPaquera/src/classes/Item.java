package classes;

/*
    Bonus - Valor adcionado no charme do paquerador em um encontro

 */
public abstract class Item {

    protected String name;
    protected int bonus;
    protected int price;

    // Método para mostrar o objeto
    public abstract String show();

    //Getters e Setters 
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
