/**
 * En abstract klasse som fungerer som grunnlag for legemiddel-subclasses.
 */
public abstract class Legemiddel{
        
    protected String navn; // navnet paa legemidellet
    protected int id; // unik identifikasjonsnummer (pos. heltall)
    // OBS: STATIC variable for "lastid"? Increment counter in 
    // constructor
    protected int pris; // legemiddelets pris (heltall) 
    protected double virkestoff; // menge virkestoff i mg (flyttall)

    /**
     * Jeg bruker en static variabel for aa holde styr paa idnumre.
     */
    protected static int sisteId;

    public int hentId(){

        return this.id;
    }

    public String hentNavn(){

        return this.navn;
    }

    /** Metode for aa sette en ny pris for legemiddelet.
     * @param nyPris Leggemiddelets nye pris.
     */
    public void settNyPris(int nyPris){

        this.pris = nyPris;
    }

    /**
     * Metode for aa hente ut prisen til legemiddelet. 
     */
    public int hentPris(){

        return this.pris;
    }

    /**
     * Metode for aa hente ut virkestoffet til legemiddelet. 
     */
    public double hentVirkestoff(){

        return virkestoff;
    }

    /**
     * KONSTRUKTOER.
     * Naar et nytt legemiddel blir opprettet blir sisteId
     * inkrementert, slik at vi faar en ny unik id som kan gis til
     * legemiddelet.
     */
    public Legemiddel(String navn, int pris, double virkestoff) {
    
        sisteId++;

        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = sisteId;
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    public String eksportString(){

        String s = ","; // seperator
        String type = null;
        
        if (this instanceof Vanlig){
        
            type = "vanlig";
        }
        else if (this instanceof Narkotisk){
        
            type = "narkotisk";
        }
        else if (this instanceof Vanedannende){
        
            type = "vanedannende";
        }

        String svarString = this.navn + s + type + s + this.pris + s
            + this.virkestoff;

        return svarString;

    }
}    





