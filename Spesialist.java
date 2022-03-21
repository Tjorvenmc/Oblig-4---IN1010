/**
 * Klasse for Spesialister
 */
class Spesialist extends Lege implements Godkjenningsfritak{

    String kontrollID;

    /**
     * Konstruktoer
     */
    public Spesialist(String navn, String kontrollID){

        super(navn);
        this.kontrollID = kontrollID;
    }
        
    public String hentKontrollID(){
       return this.kontrollID;
    }

    public String toString(){
        return "Spesialist " + this.navn + " (KontrollID " + this.kontrollID
            + ").";
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    @Override
    public String eksportString(){
    
        String s = ","; // seperator
        String svarString = this.navn + "," + this.kontrollID;

        return svarString;
    }
}
