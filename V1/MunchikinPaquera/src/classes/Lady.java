package classes;

public class Lady {

    private int defense;
    private String name;
    private int reward;
    private Style itsMyType;

    public Lady(String name, int defense, int reward, Style itsMyType) {
        this.defense = defense;
        this.name = name;
        this.reward = reward;
        this.itsMyType = itsMyType;
    }

    //Mostra uma ficha simplificada da dama
    public String showSimple() {
        return "[ " + getName() + " ] \n Defesa : " + getDefense()
                + " / Faz meu Tipo [ " + getItsMyType() + " ]";
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public int getReward() {
        return reward;
    }

    public Style getItsMyType() {
        return itsMyType;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setItsMyType(Style itsMyType) {
        this.itsMyType = itsMyType;
    }

}
