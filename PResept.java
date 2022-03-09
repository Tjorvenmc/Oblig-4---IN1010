/**
 * Klasse for P-Resepter.
 */
class PResept extends HvitResept{

    static final int RABATT = 108;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege,
            int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public int prisAaBetale(){
        
        int justertPris = this.legemiddel.hentPris() - RABATT; 
        if (justertPris > 0){
            return justertPris;
        }
        return 0;
    }
}

