/**
 * Klasse for blaa resepter.
 */
class BlaaResept extends Resept{

    static final double RABATTFAKTOR = 0.25;

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege,
            Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge(){
        return "blaa";
    }

    @Override
    public int prisAaBetale(){
        return ( (int)(this.legemiddel.hentPris() * RABATTFAKTOR));
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

