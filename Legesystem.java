import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem{

    IndeksertListe<Pasient> pasienter = new IndeksertListe<Pasient>();
    IndeksertListe<Legemiddel> legemidler = new IndeksertListe<Legemiddel>();
    Prioritetskoe<Lege> leger = new Prioritetskoe<Lege>();

    /**
     * LES IN FRA FIL
     */
    public void lesInnFraFil(String filnavn) throws FileNotFoundException{

        // Opprette scanner-objekt
        File innlesningsFil = new File(filnavn);
        Scanner sc = new Scanner(innlesningsFil);

        String innlesningsModus = null;

        // Gaa gjennom linjene i filene
        while (sc.hasNextLine()){

            String linje = sc.nextLine();

            // Linjen begynner med #
            // --> setter innlesningsModus
            if (linje.charAt(0) == '#'){
                String[] biter = linje.split(" ");
                innlesningsModus = biter[1];
            }

            // Linjen begynner ikke med #
            else{

                // Pasienter ...
                if (innlesningsModus.equals("Pasienter")){

                    lesInnPasient(linje);

                }

                // Legemidler ...
                else if (innlesningsModus.equals("Legemidler")){

                    lesInnLegemiddel(linje);

                }

                // Leger ...
                else if (innlesningsModus.equals("Leger")){

                    lesInnLege(linje);

                }

                // Skriv resept
                else if (innlesningsModus.equals("Resepter")){

                    lesInnResept(linje);
                }

                else {

                    System.out.println("Error");
                }

            }

        }


    }

    public void lesInnPasient(String linje){

        // Oppdeling av linjen
        String[] biter = linje.split(",");
        String navn = biter[0];
        String fnr = biter[1];

        Pasient p = new Pasient(navn, fnr);
        pasienter.leggTil(p);
    }

    public void lesInnLegemiddel(String linje){

        // Oppdeling av linjen
        String[] biter = linje.split(",");
        String navn = biter[0];
        String type = biter[1];
        int pris = Integer.parseInt(biter[2]);
        double virkestoff = Double.parseDouble(biter[3]);
        int styrke = 0;

        if (biter.length == 5){
             styrke = Integer.parseInt(biter[4]);
        }

        if (type.equals("vanlig")){

            Vanlig v = new Vanlig(navn, pris, virkestoff);
            legemidler.leggTil(v);
        }

        else if (type.equals("narkotisk")){

            Narkotisk n = new Narkotisk(navn, pris, virkestoff, styrke);
            legemidler.leggTil(n);
        }

        else if (type.equals("vanedannende")){

            Vanedannende v = new Vanedannende(navn, pris, virkestoff, styrke);
            legemidler.leggTil(v);
        }
    }

    public void lesInnLege(String linje){

        // Oppdeling av linjen
        String[] biter = linje.split(",");
        String navn = biter[0];
        String kontrollid = biter[1];


        // Oppretter riktig type  lege ut i fra kontrollid
        if (kontrollid.equals("0")){
            Lege l = new Lege(navn);
            leger.leggTil(l);
          } else {
            Spesialist s = new Spesialist(navn, kontrollid);
            leger.leggTil(s);
          }
        }

    public void lesInnResept(String linje){

        // Oppdeling av linjen
        String[] biter = linje.split(",");
        int legemiddelNummer = Integer.parseInt(biter[0]);
        String legeNavn = biter[1];
        int pasientID = Integer.parseInt(biter[2]);
        String type = biter[3];
        int reit = 0;

        if (!type.equals("militaer")){
            reit = Integer.parseInt(biter[4]);
        }


        // Finn lege
        Lege utskrivendeLege = finnLege(legeNavn);


        // Hvis ikke lege funnet ...
        if (utskrivendeLege == null){
            System.out.println("Lege ikke funnet"); // TEMP
        }

        // Lege funnet
        // --> skriv resept
        else {

            // Finner legemiddel
            Legemiddel legemiddelPaaResept = finnLegemiddel(legemiddelNummer);

            // Finner pasient
            Pasient pasientPaaResept = finnPasient(pasientID);

            // Skriver resept
            if (type.equals("militaer")){

                try{
                    utskrivendeLege.skrivMilResept(legemiddelPaaResept, pasientPaaResept);
                }

                catch (Exception e){

                    System.out.println(e); // TEMP
                }
            }

            else if (type.equals("hvit")){

                try{
                utskrivendeLege.skrivHvitResept(legemiddelPaaResept, pasientPaaResept,
                         reit);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

            else if (type.equals("blaa")){

                try{
                utskrivendeLege.skrivBlaaResept(legemiddelPaaResept, pasientPaaResept,
                        reit);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

            else if (type.equals("p")){

                try{
                utskrivendeLege.skrivPResept(legemiddelPaaResept, pasientPaaResept,
                        reit);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

        }
    }

    public Lege finnLege(String navn){

        boolean legeFunnet = false;
        Lege aktuellLege = null;
        int i = 0;

        while (!legeFunnet && i < leger.stoerrelse()){
            for (Lege l: leger){

                i++;
                if (navn.equals(l.hentNavn())){

                    legeFunnet = true;
                    aktuellLege = l;
                }
            }
        }

        if (!legeFunnet){
            return null;
        }

        return aktuellLege;
    }

    public Legemiddel finnLegemiddel(int id){

        boolean legemiddelFunnet = false;
        Legemiddel aktuellLegemiddel = null;
        int i = 0;

        while (!legemiddelFunnet && i < legemidler.stoerrelse()){
            for (Legemiddel l: legemidler){

                i++;
                if (id == l.hentId()){

                    legemiddelFunnet = true;
                    aktuellLegemiddel = l;
                }
            }
        }

        if (!legemiddelFunnet){
            return null;
        }

        return aktuellLegemiddel;

    }

    public Pasient finnPasient(int id){

        boolean pasientFunnet = false;
        Pasient aktuellPasient = null;
        int i = 0;

        while (!pasientFunnet && i < pasienter.stoerrelse()){
            for (Pasient p: pasienter){

                i++;
                if (id == p.hentId()){

                    pasientFunnet = true;
                    aktuellPasient = p;
                }
            }
        }

        if (!pasientFunnet){
            return null;
        }

        return aktuellPasient;

    }
    //skriver ut alle elementer i systemet (Oppg E3)
    public void skrivUtElementer(){

      //skriver ut leger
      System.out.println("Leger i systemet: \n");

      for (Lege l: leger){
        System.out.println(l);
        IndeksertListe<Resept> resepter = l.hentResepter();
        System.out.println(l.hentNavn() + " har skrevet ut " +
        "foelgende resepter: \n");
        if(resepter.stoerrelse() == 0){
          System.out.println("Ingen resepter er skrevet ut.\n");
        }
        for (Resept r: resepter){
          System.out.println("Til pasient: " + r.hentPasient().hentNavn());
          System.out.println(r);
          System.out.println("Legemiddelet i resepten er av type: " +
          r.hentLegemiddel().getClass().getName() + "\n");
        }
      }

      //Skriver ut pasienter
      System.out.println("Pasienter i systemet: \n");
      for (Pasient p: pasienter){
        System.out.println(p + "\n");
      }

      //skriver ut legemidler
      System.out.println("Legemidler i systemet: \n");
      for (Legemiddel l: legemidler){
        System.out.println(l + "\n");
      }
    }
}
