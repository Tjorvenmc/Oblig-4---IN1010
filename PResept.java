/**
 * Klasse for P-Resepter.
 */
class PResept extends HvitResept{

    static final int RABATT = 108;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege,
            Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale(){

        int justertPris = this.legemiddel.hentPris() - RABATT;
        if (justertPris > 0){
            return justertPris;
        }
        return 0;
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
