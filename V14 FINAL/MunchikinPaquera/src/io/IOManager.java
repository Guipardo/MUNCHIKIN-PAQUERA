package io;

import model.Clothe;
import model.Flirt;
import model.Gift;
import model.Lady;
import model.Style;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import munchikinpaquera.Menu;

public class IOManager {

    // SALVA O JOGO
    public static void saveGame(Flirt p) {

        try {
            String filename = p.getName();
            File f = new File(filename + ".txt");

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            oos.writeObject(p);
            oos.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            Menu.msg("Arquivo não encontrado");
        } catch (IOException ex) {
            //ex.printStackTrace();
            Menu.msg("Erro de entrada");
        }
    }

    // CARREGA UM JOGO SALVO
    public static Flirt loadGame(String name) {
        Flirt p;
        String filename = name + ".txt";
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            p = (Flirt) ois.readObject();
            ois.close();
            fis.close();
            Menu.textBox(" Jogo carregado com sucesso! ");
            return p;

        } catch (FileNotFoundException ex) {
            Menu.msg(" Nenhum Arquivo encontrado! ");
        } catch (IOException | ClassNotFoundException ex) {
            Menu.msg(" Classe não encontrada! ");
        } catch (NullPointerException ex){
            Menu.msg(" Erro Nulo");
        }
        Menu.textBox(" Nenhum arquivo encontrado! ");
        return null;
    }

    // IMPORTA OS PRESENTES DO ARQUIVO
    public static void importGifts(ArrayList<Gift> gifts, File f) {
        try {
            try (FileReader arq = new FileReader(f)) {
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = lerArq.readLine(); //Lê a Primeira Linha
                String nome = "";
                int bonus = 0;
                int preco = 0;
                int espaco = 0;

                while (!"**".equals(linha)) {
                    if ("/".equals(linha)) {
                        for (int i = 0; i < 4; i++) {
                            linha = lerArq.readLine(); // Lê uma linha
                            String line[] = linha.split(";");
                            switch (i) {
                                case 0:
                                    nome = line[1];
                                    break;
                                case 1:
                                    bonus = Integer.parseInt(line[1]);
                                    break;
                                case 2:
                                    preco = Integer.parseInt(line[1]);
                                    break;
                                case 3:
                                    espaco = Integer.parseInt(line[1]);
                                    break;
                            }
                        }
                        Gift g = new Gift(nome, bonus, preco, espaco);
                        gifts.add(g);
                        linha = lerArq.readLine(); // Lê uma linha
                    }
                }
            }
        } catch (IOException e) {
            Menu.msg("Erro na abertura do arquivo: " + e.getMessage());
        }
    }

    // IMPORTA AS ROUPAS DO ARQUIVO
    public static void importClothes(ArrayList<Clothe> clothes, File f) {
        try {
            try (FileReader arq = new FileReader(f)) {
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = lerArq.readLine(); //Lê a Primeira Linha
                String nome = "";
                int bonus = 0;
                int preco = 0;
                String penteado = "";
                String superior = "";
                String inferior = "";
                String calcado = "";
                String acessorio = "";

                while (!"**".equals(linha)) {
                    if ("/".equals(linha)) {
                        for (int i = 0; i < 8; i++) {
                            linha = lerArq.readLine(); // Lê uma linha
                            String line[] = linha.split(";");
                            switch (i) {
                                case 0:
                                    nome = line[1];
                                    break;
                                case 1:
                                    bonus = Integer.parseInt(line[1]);
                                    break;
                                case 2:
                                    preco = Integer.parseInt(line[1]);
                                    break;
                                case 3:
                                    penteado = line[1];
                                    break;
                                case 4:
                                    superior = line[1];
                                    break;
                                case 5:
                                    inferior = line[1];
                                    break;
                                case 6:
                                    calcado = line[1];
                                    break;
                                case 7:
                                    acessorio = line[1];
                                    break;
                            }
                        }
                        Clothe c = new Clothe(nome, bonus, preco, penteado, superior, inferior, calcado, acessorio);
                        clothes.add(c);
                        linha = lerArq.readLine(); // Lê uma linha
                    }
                }
            }
        } catch (IOException e) {
            Menu.msg("Erro na abertura do arquivo: " + e.getMessage());
        }
    }

    // IMPORTA AS LADYS DO ARQUIVO
    public static void importLadys(ArrayList<Lady> ladys, File f) {
        try {
            try (FileReader arq = new FileReader(f)) {
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine(); //Lê a Primeira Linha
                String nome = "";
                int defesa = 0;
                Style fazmeutipo = Style.ROMÂNTICO;
                String frase = "";
                String vitoria = "";
                String derrota = "";

                while (!"**".equals(linha)) {
                    if ("/".equals(linha)) {
                        for (int i = 0; i < 6; i++) {
                            linha = lerArq.readLine(); // Lê uma linha
                            String line[] = linha.split(";");
                            switch (i) {
                                case 0:
                                    nome = line[1];
                                    break;
                                case 1:
                                    defesa = Integer.parseInt(line[1]);
                                    break;
                                case 2:
                                    switch (line[1]) {
                                        case "r":
                                            fazmeutipo = Style.ROMÂNTICO;
                                            break;
                                        case "b":
                                            fazmeutipo = Style.BADBOY;
                                            break;
                                        case "e":
                                            fazmeutipo = Style.ENGRAÇADO;
                                            break;
                                        case "n":
                                            fazmeutipo = Style.NERD;
                                            break;
                                        case "g":
                                            fazmeutipo = Style.GALÃ;
                                    }
                                    break;
                                case 3:
                                    frase = line[1];
                                    break;
                                case 4:
                                    vitoria = line[1];
                                    break;
                                case 5:
                                    derrota = line[1];
                                    break;
                            }
                        }
                        Lady l = new Lady(nome, defesa, fazmeutipo, frase, vitoria, derrota);
                        ladys.add(l);
                        linha = lerArq.readLine(); // Lê uma linha
                    }
                }
            }
        } catch (IOException e) {
            Menu.msg("Erro na abertura do arquivo:" + e.getMessage());
        }
    }
}
