/**
 * Abstract klasse som brukes som grunnlag for ulike typer resepter.
 */
public abstract class Resept{

    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;

    protected static int sisteId;

    protected String farge;

    /**
     * KONSTRUKTOER
     */
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId,
            int reit){

        sisteId++;

        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;

        this.id = sisteId;
    }

    /**
     * Metoder som henter relevant data (Oppgave B2)
     */
    public int hentId(){
        return this.id;
    }

    public Legemiddel hentLegemiddel(){
        return this.legemiddel;
    }

    public Lege hentLege(){
        return this.utskrivendeLege;
    }

    public int hentReit(){
        return this.reit;
    }

    /**
     * Flere metoder (Oppgave B2)
     */
    public boolean bruk(){
    
        if (this.hentReit() > 0){
            this.reit --;
            return true;}
        return false;
    }

    public abstract String farge();
    public abstract int prisAaBetale();

    /**
     * Overskriving av toString-metoden
     */
    public String toString(){

        String standardInfo = "Resept " + this.hentId() + " " + 
            "(" + this.farge() + ")" + ": "
            + this.legemiddel.hentNavn() + ". "
            + "Reit: " + this.hentReit() + ". "
            + "Pris aa betale: " + this.prisAaBetale() + ".";

        if (this.hentReit() < 1){
            return "Ugyldig resept!";}

        return standardInfo;
    }
}

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
            int pasientId, int reit){

        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
}

/**
 * Klasse for Militaerresepter.
 */
class MilResept extends HvitResept{

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege,
            int pasientId){
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public int prisAaBetale(){
        return 0;
    }
}

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

/**
 * Should alternative toString-method be implemented in Superclass?
 * ... with instanceOf?
 */
