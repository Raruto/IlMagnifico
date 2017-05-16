/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

/**
 *
 * @author Carlo Castelnuovo
 */
public class Giocatore {

    private String nome;
    private int naviadisposizione;
    private Nave[] Navi;

    public void SetGiocatore(String nome, int qtanavi) {
        this.nome = nome;
        this.naviadisposizione = qtanavi;
        this.Navi = new Nave[qtanavi];
    }

    public String GetNome() {
        return this.nome;
    }

    public int GetNumeroNavi() {
        return this.naviadisposizione;
    }

    public Nave NaveGiocatore(int numero) {
        return this.Navi[numero];
    }

    public void SetNaveGiocatore(int numero, Nave nave) {
        this.Navi[numero] = nave;
    }

    public String GetTipoNaveGiocatore(int numero) {
        return this.Navi[numero].GetTipoNave();
    }

    public int GetDimensioneNaveGiocatore(int numero) {
        return this.Navi[numero].GetDimensione();
    }
}
