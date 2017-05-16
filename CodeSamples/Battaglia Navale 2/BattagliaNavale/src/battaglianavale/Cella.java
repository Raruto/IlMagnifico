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
public class Cella {

    private Nave naveNellaCella;

    public enum statocella {

        OCCUPATA, LIBERA, COLPITA
    };
    statocella statoCellaCorrente;

    public statocella GetStatoCella() {
        return this.statoCellaCorrente;
    }

    public void SetStatoCella(statocella stato) {
        this.statoCellaCorrente = stato;
    }

    public Nave GetNave() {
        return this.naveNellaCella;
    }

    public String Colpo() {
        if (this.statoCellaCorrente == statocella.LIBERA) {
            this.statoCellaCorrente = statocella.COLPITA;
            return "acqua";
        }
        if (this.statoCellaCorrente == statocella.COLPITA) {
            return "cella già colpita";
        }
        if (this.statoCellaCorrente == statocella.OCCUPATA) {
            this.naveNellaCella.SetDim(this.naveNellaCella.GetDimensione() - 1);
            this.naveNellaCella.SetStatoNave(Nave.statonave.COLPITA);
            if (this.naveNellaCella.GetDimensione() == 0) {
                this.naveNellaCella.SetStatoNave(Nave.statonave.AFFONDATA);
                return "colpito e affondato";
            }
            return "colpito";
        } else {
            return "non esiste la cella!!";
        }
    }

    public void SetNave(Nave.tiponave tipo, int dimensione, Nave.statonave stato) {
        this.naveNellaCella = new Nave(tipo, dimensione, stato);
    }

    public void CopiaNave(Nave nave) {
        this.naveNellaCella = nave;
    }

    public String GetNaveCella() {
        return this.naveNellaCella.GetTipoNave();
    }

    public String GetStatonaveCella() {
        return this.naveNellaCella.GetStatoNaveS();
    }
}
