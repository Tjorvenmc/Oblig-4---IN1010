/**
 * Klasse for et narkotisk legemiddel.
 */
class Narkotisk extends Legemiddel {

    private int styrke;

    // Konstruktoer
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){

        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    /**
     * Henter legemiddelets narkotiske styrke.
     * @return styrken
     */
    public int hentNarkotiskStyrke(){

        return this.styrke;
    }

    public String toString(){

        return "" + this.hentNavn()
            + " (id " + this.hentId() + ")"
            +": narkotisk legemiddel med styrke "
            + this.hentNarkotiskStyrke()
            + ".";
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    public String eksportString(){
        
        String s = ","; // seperator
        String svarString = super.eksportString() + s + this.styrke;

        return svarString;
    
    }
}

