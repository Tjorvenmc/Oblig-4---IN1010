/**
 * Klasse for hvite resepter.
 */
class HvitResept extends Resept{

    @Override
    public String farge(){
        return "hvit";
    }

    @Override
    public int prisAaBetale(){
        return 0;
    }

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege,
            Pasient pasient, int reit){

        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    public String eksportString(){
        
        String s = ","; // seperator
        String svarString = this.legemiddel.hentId() + s + 
            this.utskrivendeLege.hentNavn() + s + 
            this.pasient.hentId() + s +
            this.farge() + s +
            this.reit;

        return svarString;
    
    }
}
