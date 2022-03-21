/**
 * Abstract klasse som brukes som grunnlag for ulike typer resepter.
 */
public abstract class Resept{

    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    protected static int sisteId;

    protected String farge;

    /**
     * KONSTRUKTOER
     */
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient,
            int reit){

        sisteId++;

        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
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

    public Pasient hentPasient(){
      return this.pasient;
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
    public abstract String eksportString();

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
