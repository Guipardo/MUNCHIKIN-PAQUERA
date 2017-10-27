package classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
    Classe que gerencia as compras no jogo
 */
public class Shop {

    //DOis ArrayLists com Presentes e Roupas 
    private ArrayList<Gift> gifts = new ArrayList<>(6);
    private ArrayList<Clothe> clothes = new ArrayList<>(6);

    //Construtor
    public Shop() {
    }

    //Retorna uma String com todos os presentes da Loja
    public String showGifts() {
        String text = "";
        for (Gift gift : gifts) {
            text += gift.show();
            text += "\n";
        }
        return text;
    }

    //Retorna uma String com todas as roupas da Loja
    public String showClothes() {
        String text = "";
        for (Clothe clothe : clothes) {
            text += clothe.showSimple();
            text += "\n";
        }
        return text;
    }

    //Mostra o menu da Loja (ver se consegue colocar na classe Menu)
    public int showShop(String a, String b, String c, String d, String e, String text, String name) {
        Object[] options = {a, b, c, d, e};
        int answer = JOptionPane.showOptionDialog(null, text, name, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        return answer;
    }
    
    //Retorna um presente quando clica no botão e compra
    public Gift buyGift(Flirt p) {
        int answer = showShop(getGifts().get(0).getName(),
                getGifts().get(1).getName(),
                getGifts().get(2).getName(),
                getGifts().get(3).getName(),
                getGifts().get(4).getName(),
                "Escolha seu presente \n"
                +" Carteira :" + p.getWallet()
                + "$$\n" +showGifts(), "PRESENTES");
        return gifts.get(answer);
    }
    
    //Retorna um presente quando clica no botão e vende
    public Gift sellGift(Flirt p) {
        int answer = showShop(getGifts().get(0).getName(),
                getGifts().get(1).getName(),
                getGifts().get(2).getName(),
                getGifts().get(3).getName(),
                getGifts().get(4).getName(),
                "Escolha seu presente \n"
                + p.showItems(), "PRESENTES");
        return gifts.get(answer);
    }

    //Retorna uma roupa quando clica no botão
    public Clothe menuClothe(Flirt p) {
        int answer = showShop(getClothes().get(0).getName(),
                getClothes().get(1).getName(),
                getClothes().get(2).getName(),
                getClothes().get(3).getName(),
                getClothes().get(4).getName(),
                "Escolha sua Roupa \n"
                +" Carteira :" + p.getWallet()
                + "$$\n" + showClothes(), "PRESENTES");
        return clothes.get(answer);
    }

    //Getters e Setters
    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<Clothe> getClothes() {
        return clothes;
    }

    public void setClothes(ArrayList<Clothe> clothes) {
        this.clothes = clothes;
    }
}
