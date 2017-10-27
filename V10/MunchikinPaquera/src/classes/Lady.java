package classes;

public class Lady {

    private int defense; // Defesa 
    private String name; // Nome
    private Gift reward; // Recompensa
    private Style itsMyType; // Faz meu tipo

    public Lady(String name, int defense, Gift reward, Style itsMyType) {
        this.defense = defense;
        this.name = name;
        this.reward = reward;
        this.itsMyType = itsMyType;
    }

    // Mostra a ficha resumida da Lady
    public String show() {
        return "\n[ " + getName() + " ] Defesa [ " + getDefense()
                + " ]\n- Faz meu Tipo [ " + getItsMyType() + " ]";
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public Gift getReward() {
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

    public void setReward(Gift reward) {
        this.reward = reward;
    }

    public void setItsMyType(Style itsMyType) {
        this.itsMyType = itsMyType;
    }

    public void giveGift(Flirt p){
        p.addGift(reward);
    }
}
