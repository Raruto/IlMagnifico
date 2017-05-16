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
public class Nave {

    public enum tiponave {

        CACCIATORPEDINIERE, SOTTOMARINO, CORAZZATA, PORTAEREI
    };

    public enum statonave {

        INDENNE, COLPITA, AFFONDATA
    };
    private int dimensione;
    private tiponave tipo;
    private statonave stato;

    public statonave GetStatoNave() {
        return this.stato;
    }

    public int GetDimensione() {
        return this.dimensione;
    }

    public String GetStatoNaveS() {//ritorna lo stato della nave ma in stringa
        if (this.stato == statonave.INDENNE) {
            return "indenne";
        }
        if (this.stato == statonave.COLPITA) {
            return "colpita";
        }
        if (this.stato == statonave.AFFONDATA) {
            return "affondata";
        } else {
            return "non c'è alcuna nave!";
        }
    }

    public Nave(tiponave tipo, int dimensione, statonave stato) {
        this.dimensione = dimensione;
        this.stato = stato;
        this.tipo = tipo;
    }

    public String GetTipoNave() {
        if (this.tipo == tiponave.CACCIATORPEDINIERE) {
            return "cacciatorpediniere";
        }
        if (this.tipo == tiponave.CORAZZATA) {
            return "corazzata";
        }
        if (this.tipo == tiponave.PORTAEREI) {
            return "portaerei";
        }
        if (this.tipo == tiponave.SOTTOMARINO) {
            return "sottomarino";
        }
        return "non si sa che nave è!!";

    }

    public void SetStatoNave(statonave stato) {
        this.stato = stato;
    }

    public void SetDim(int dimensione) {
        this.dimensione = dimensione;
    }

}
