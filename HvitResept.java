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
}
