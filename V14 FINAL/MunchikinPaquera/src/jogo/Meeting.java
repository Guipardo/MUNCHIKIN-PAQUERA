package jogo;

import model.Gift;
import model.Lady;
import java.io.Serializable;

public class Meeting implements Serializable {

    private int number;
    private Lady lady;
    private Gift gift;
    private String status;

    // CONSTRUTOR
    public Meeting(int number, Lady lady, Gift gift) {
        this.number = number;
        this.lady = lady;
        this.gift = gift;
    }

    public Meeting(Lady lady) {
        this.lady = lady;
    }

    // FICHA
    public String show() {
        String text = getNumber() + " " + getStatus();
        return text;
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
