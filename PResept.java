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

    @Override
    public String eksportString(){
        
        String s = ","; // seperator
        String svarString = s + super.eksportString() + s + "p"+ s +
            this.reit;

        return svarString;
    
    }
}

