/**
 * Klasse for et vanedannende legemiddel.
 */
class Vanedannende extends Legemiddel {

    private int styrke;

    // Konstruktoer
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){

        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    /**
     * Henter legemiddelets vanedannende styrke.
     * @return styrken
     */
    public int hentVanedannendeStyrke(){
        return this.styrke;
    }

    public String toString(){
        return "" + this.hentNavn()
            + " (id " + this.hentId() + ")"
            +": vanedannende legemiddel med styrke "
            + this.hentVanedannendeStyrke()
            + ".";
    }

    @Override
    public String eksportString(){
        
        String s = ","; // seperator
        String svarString = super.eksportString() + s + this.styrke;

        return svarString;
    
    }
}

