public class Pasient{

    // Jeg bruker en static variabel for aa holde styr paa idnumre.
    protected static int sisteId;

    // Instansvariabler
    int id;

    String navn;
    String fnr;

    IndeksertListe<Resept> resepter;

    /**
     * Konstruktoer
     */
    public Pasient(String navn, String fnr){

        sisteId++;

        this.navn = navn;
        this.fnr = fnr;
        this.id = sisteId;

        this.resepter = new IndeksertListe<Resept>();

    }

    /**
     * Metode for aa legge til resepter.
     * OBS: Spiller posisjonen en rolle? I saa fall maa dette endres.
     */
    public void leggTilResept(Resept resept){

        this.resepter.leggTil(resept);

    }

    public int hentId(){

        return this.id;
    }

    public String hentNavn(){
      return this.navn;
    }
    /**
     * TO DO: toString-metode
     */
    @Override
    public String toString(){

        String svarString = "Pasient " + this.navn + " (" + this.fnr + ")";
        svarString += "\nResepter:";

        for (Resept r:this.resepter){

            svarString += "\n";
            svarString += r.toString();
        }


        return svarString;
    }

}
