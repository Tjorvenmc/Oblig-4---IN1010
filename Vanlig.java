/**
 * Klasse for et 'vanlig' legemiddel.
 */
class Vanlig extends Legemiddel {


    public Vanlig(String navn, int pris, double virkestoff){
    
        super(navn, pris, virkestoff);
    }

    public String toString(){
        return ""+ this.hentNavn()
            + " (id " + this.hentId() + ")"
            +": vanlig legemiddel.";
    }
    
}

