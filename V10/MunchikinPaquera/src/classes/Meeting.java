package classes;

public class Meeting {

    private int number;
    private Lady lady;
    private Gift gift;
    private Dice dice;
    private String status;

    // CONSTRUTOR
    public Meeting(int number, Lady lady, Gift gift, Dice dice) {
        this.number = number;
        this.lady = lady;
        this.gift = gift;
        this.dice = dice;
    }

    // FICHA DO ENCONTRO
    public String show(){
        return getStatus();
    }
    
    // GETTERS E SETTERS
    public Lady getLady() {
        return lady;
    }

    public void setLady(Lady lady) {
        this.lady = lady;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
