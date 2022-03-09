/**
 * Klasse for blaa resepter.
 */
class BlaaResept extends Resept{

    static final double RABATTFAKTOR = 0.25;

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege,
            int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge(){
        return "blaa";
    }

    @Override
    public int prisAaBetale(){
        return ( (int)(this.legemiddel.hentPris() * RABATTFAKTOR));
    }
}

