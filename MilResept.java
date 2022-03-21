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
    public String eksportString(){
        
        String s = ","; // seperator
        String svarString = s + super.eksportString() + s + "militaer";

        return svarString;
    
    }
}
