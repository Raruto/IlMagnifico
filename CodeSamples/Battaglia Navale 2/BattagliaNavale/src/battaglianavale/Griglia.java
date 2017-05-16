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
public class Griglia {

    private Cella[][] campo;
    private int dimensionegriglia;
    
    public Griglia(){}

    public void SetGriglia(int dimensione) {
        int i, j;
        this.dimensionegriglia = dimensione;
        this.campo = new Cella[dimensione][dimensione];
        for (i = 0; i < dimensione; i++) {
            for (j = 0; j < dimensione; j++) {
                this.campo[i][j]= new Cella();
                this.campo[i][j].SetStatoCella(Cella.statocella.LIBERA);
            }
        }
    }
    
    public String toString() {
        int i, j;
        int dimensione= this.dimensionegriglia;
        this.campo = new Cella[dimensione][dimensione];
        
        String griglia="";
        
        for (i = 0; i < dimensione; i++) {
            for (j = 0; j < dimensione; j++) {
                if(this.campo[i][j].GetStatoCella() == Cella.statocella.LIBERA){
                	griglia+="| |";
                }
                else if(this.campo[i][j].GetStatoCella() == Cella.statocella.OCCUPATA){
                	griglia+="|o|";
                }
                else if(this.campo[i][j].GetStatoCella() == Cella.statocella.COLPITA){
                	griglia+="|x|";
                }
            }
            griglia+="\n";
        }
        return griglia;
    }

    public boolean ControllaIntorno(int ascissa, int ordinata) {
        int i, j, supx, supy, infx, infy;
        supx = ascissa;
        supy = ordinata;
        infx = ascissa;
        infy = ordinata;
        if (ascissa == 0) {
            infx = ascissa + 1;
        }
        if (ascissa == this.dimensionegriglia) {
            supx = ascissa - 1;
        }
        if (ordinata == 0) {
            infy = ordinata + 1;
        }
        if (ordinata == this.dimensionegriglia) {
            supy = ordinata - 1;
        }
        for (i = infx; i <= supx; i++) {
            for (j = infy; j <= supy; j++) {
                if (this.campo[i][j].GetStatoCella() == Cella.statocella.OCCUPATA) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean ControlloTotaleNave(int ascissa, int ordinata, String direzione, int dimensione) {
        int i;
        if (direzione.equals("giù")) {
            if (ordinata < dimensione - 1) {
                return false;
            }
            for (i = 0; i < dimensione; i++) {
                if (!(ControllaIntorno(ascissa, ordinata))) {
                    return false;
                } else {
                    ordinata--;
                }
            }
            return true;
        }
        if (direzione.equals("su")) {
            if (ordinata > this.dimensionegriglia - dimensione - 1) {
                return false;
            }
            for (i = 0; i < dimensione; i++) {
                if (!(ControllaIntorno(ascissa, ordinata))) {
                    return false;
                } else {
                    ordinata++;
                }
            }
            return true;
        }
        if (direzione.equals("sinistra")) {
            if (ascissa < dimensione - 1) {
                return false;
            }
            for (i = 0; i < dimensione; i++) {
                if (!(ControllaIntorno(ascissa, ordinata))) {
                    return false;
                } else {
                    ascissa--;
                }
            }
            return true;
        }
        if (direzione.equals("destra")) {
            if (ascissa > this.dimensionegriglia - dimensione - 1) {
                return false;
            }
            for (i = 0; i < dimensione; i++) {
                if (!(ControllaIntorno(ascissa, ordinata))) {
                    return false;
                } else {
                    ascissa++;
                }
            }
            return true;
        }
        return false;
    }

    public void PosizionamentoNave(int ascissa, int ordinata, int dimensione, Nave.tiponave tipo, Nave.statonave stato) {
        this.campo[ascissa][ordinata].SetNave(tipo, dimensione, stato);
        this.campo[ascissa][ordinata].SetStatoCella(Cella.statocella.OCCUPATA);
    }

    public void EstensioneNave(int ascissa, int ordinata, String direzione, int dimensione) {
        int i;
        if (direzione.equals("giù")) {
            for (i = 1; i < dimensione; i++) {
                this.campo[ascissa][ordinata - 1].CopiaNave(this.campo[ascissa][ordinata].GetNave());
                this.campo[ascissa][ordinata - 1].SetStatoCella(Cella.statocella.OCCUPATA);
                ordinata--;
            }
        }
        if (direzione.equals("su")) {
            for (i = 1; i < dimensione; i++) {
                this.campo[ascissa][ordinata + 1].CopiaNave(this.campo[ascissa][ordinata].GetNave());
                this.campo[ascissa][ordinata + 1].SetStatoCella(Cella.statocella.OCCUPATA);
                ordinata++;
            }
        }
        if (direzione.equals("sinistra")) {
            for (i = 1; i < dimensione; i++) {
                this.campo[ascissa - 1][ordinata].CopiaNave(this.campo[ascissa][ordinata].GetNave());
                this.campo[ascissa - 1][ordinata].SetStatoCella(Cella.statocella.OCCUPATA);
                ascissa--;
            }
        }
        if (direzione.equals("destra")) {
            for (i = 1; i < dimensione; i++) {
                this.campo[ascissa + 1][ordinata].CopiaNave(this.campo[ascissa][ordinata].GetNave());
                this.campo[ascissa + 1][ordinata].SetStatoCella(Cella.statocella.OCCUPATA);
                ascissa++;
            }
        }
    }

    public String Sparo(int ascissa, int ordinata) {
        if (ascissa < 0 | ascissa > this.dimensionegriglia | ordinata < 0 | ordinata > this.dimensionegriglia) {
            return "errore";
        }
        return this.campo[ascissa][ordinata].Colpo();
    }

    public Nave GetNaveCella(int ascissa, int ordinata) {
        return this.campo[ascissa][ordinata].GetNave();
    }

    public int GetDimensioneGriglia() {
        return this.dimensionegriglia;
    }
}
