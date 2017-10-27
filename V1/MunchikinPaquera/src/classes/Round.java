package classes;

public class Round {

    private Dice dice;
    private Meeting date;
    private String status;

    //Construtor
    public Round(int number, Dice dice) {
        this.dice = dice;
    }

    // Mostra a rodada e o status da rodada
    public String show() {
        return "[Rodada]" + getStatus();
    }

    // Getters e Setters
    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Meeting getDate() {
        return date;
    }

    public void setDate(Meeting date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
