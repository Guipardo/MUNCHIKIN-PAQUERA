package classes;

public class Meeting {

    private Lady lady;
    private Item item;

    //Construtor
    public Meeting(int number, Lady lady, Item item) {
        this.lady = lady;
        this.item = item;
    }

    // Getters e Setters

    public Lady getLady() {
        return lady;
    }

    public void setLady(Lady lady) {
        this.lady = lady;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
