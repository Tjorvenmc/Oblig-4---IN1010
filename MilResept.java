/**
 * Klasse for Militaerresepter.
 */
class MilResept extends HvitResept{

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege,
            Pasient pasient){

        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale(){

        return 0;
    }

    @Override
    public String farge(){
        
        return "militaer";
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
