public class Lege implements Comparable<Lege> {

    String navn;
    IndeksertListe<Resept> utskrevneResepter;
    Lege selvReferanse = this;

    /**
     * Konstruktoer
     */
    public Lege(String navn){

        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();

    }

    public String hentNavn(){

        return this.navn;

    }

    public String toString(){

        return "Lege " + this.navn + ".";

    }

    // compare-To metode
    @Override
    public int compareTo(Lege lege) {

        return navn.compareTo(lege.hentNavn());

    }

    // Returnerer listeobjektene
    public IndeksertListe<Resept> hentResepter() {

        return utskrevneResepter;

    }


    // SKRIV RESEPT METODER
    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient,
            int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk) {

            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }

        HvitResept hvitResept = new HvitResept(legemiddel, selvReferanse, 
                pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        pasient.leggTilResept(hvitResept);

        return hvitResept;

    }

    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) 
            throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk) {

            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }

        MilResept milResept = new MilResept(legemiddel, selvReferanse, pasient);
        utskrevneResepter.leggTil(milResept);
        pasient.leggTilResept(milResept);

        return milResept;

    }

    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, 
            int reit) throws UlovligUtskrift {

        if (legemiddel instanceof Narkotisk) {

            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }

        PResept pResept = new PResept(legemiddel, selvReferanse, pasient, reit);
        utskrevneResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);

        return pResept;

    }


    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, 
            int reit) throws UlovligUtskrift {

        BlaaResept blaaResept;

        if (legemiddel instanceof Narkotisk) {

            if (!(selvReferanse instanceof Spesialist)) {

                throw new UlovligUtskrift(selvReferanse, legemiddel);
            }
        }

        blaaResept = new BlaaResept(legemiddel, selvReferanse, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);

        return blaaResept;
    }

    // Denne metoden genererer en korrekt formatert linje for utskrift
    // til fil.
    public String eksportString(){

        String s = ","; // seperator
        String svarString = this.navn + "," + "0";

        return svarString;
    }
}
