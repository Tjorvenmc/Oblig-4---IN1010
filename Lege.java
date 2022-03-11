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

    @Override
    public int compareTo(Lege lege) {
        return navn.compareTo(lege.hentNavn());
    }

    //Returnerer listeobjektene??
    public IndeksertListe hentResepter() {
        return utskrevneResepter;
    }

   
    //har foreloepig brukt reseptId som posisjon i leggTil-metoden. Usikker paa om dette har noe for seg, men lar det
    //staa intil videre. 
    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }
        
        HvitResept hvitResept = new HvitResept(legemiddel, selvReferanse, pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        return hvitResept;

    }

    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }
        
        MilResept milResept = new MilResept(legemiddel, selvReferanse, pasient);
        utskrevneResepter.leggTil(milResept);
        return milResept;

    }
    
    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(selvReferanse, legemiddel);
        }
        
        PResept pResept = new PResept(legemiddel, selvReferanse, pasient, reit);
        utskrevneResepter.leggTil(pResept);
        return pResept;

    }

    
    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlaaResept blaaResept;

        if (legemiddel instanceof Narkotisk) {
            if (!(selvReferanse instanceof Spesialist)) {
                throw new UlovligUtskrift(selvReferanse, legemiddel);
            }
        }
    
        //Maa sjekke om testen virkelig stopper etter det throwes
        blaaResept = new BlaaResept(legemiddel, selvReferanse, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);            
    
        return blaaResept;
    }
}