/**
 * Klasse for pasienter
 */
public class Pasient{

    // Jeg bruker en static variabel for aa holde styr paa idnumre.
    protected static int sisteId;

    // Instansvariabler
    int id;

    String navn;
    String fnr;

    IndeksertListe<Resept> resepter;

    /**
     * Konstruktoer
     */
    public Pasient(String navn, String fnr){

        sisteId++;

        this.navn = navn;
        this.fnr = fnr;
        this.id = sisteId;

        this.resepter = new IndeksertListe<Resept>();

    }

    /**
     * Metode for aa legge til resepter.
     */
    public void leggTilResept(Resept resept){

        this.resepter.leggTil(resept);

    }

    public int hentId(){

        return this.id;
    }

    public IndeksertListe<Resept> hentResepter() {

        return resepter;
    }

    @Override
    public String toString(){
    
        String svarString = "Pasient: " + this.navn + " (fnr: " 
            + this.fnr + ")";

        return svarString;
    }
  
     public String hentNavn(){
      return this.navn;
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    public String eksportString(){
    
        String svarString = this.navn + "," + this.fnr;

        return svarString;
    }    
}
