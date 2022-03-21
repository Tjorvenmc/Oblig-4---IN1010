import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Legesystem{

    Koe<Pasient> pasienter = new Koe<>();
    IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    Prioritetskoe<Lege> leger = new Prioritetskoe<>();

    /**
     * LES IN FRA FIL
     */
    public void lesInnFraFil(String filnavn) throws FileNotFoundException{

        // Opprette scanner-objekt
        File innlesningsFil = new File(filnavn);
        Scanner sc = new Scanner(innlesningsFil);

        String innlesningsModus = null;

        // Gaa gjennom linjene i filene
        int linjeNummer = 0; // Hjelper med feilretting i inputfiler

        while (sc.hasNextLine()){

            String linje = sc.nextLine();
            linjeNummer++;

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

                    try{

                        lesInnPasient(linje);

                    }

                    catch (Exception e){
                    
                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus, linje);
                    }

                }

                // Legemidler ...
                else if (innlesningsModus.equals("Legemidler")){

                    try{

                        lesInnLegemiddel(linje);

                    }

                    catch (Exception e){
                    
                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus, linje);
                    }

                }

                // Leger ...
                else if (innlesningsModus.equals("Leger")){

                    try{

                        lesInnLege(linje);

                    }

                    catch (Exception e){
                    
                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus, linje);
                    }

                }

                // Skriv resept
                else if (innlesningsModus.equals("Resepter")){

                    try{

                        lesInnResept(linje);

                    }

                    catch (Exception e){
                    
                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus, linje);
                    }
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

    public void skrivUtFeilmelding(int linjeNummer, Exception e, String innlesningsModus, String linje){
    
        System.out.println("Linje " + linjeNummer + ": Feil linjeformat for " + innlesningsModus + ":");
        System.out.println("Linjen \"" + linje + "\" produserte feilmelding:");
        System.out.println("\"" + e + "\"");
        System.out.println(" --> Hopper over linje " + linjeNummer + ".");
    }

    public void hovedmeny() {
        boolean svar = false;
        Scanner inn = new Scanner(System.in);
        String input;

        while (!svar) {
            System.out.println("\nMeny:");
            System.out.println("\nTast (1) Skriv ut oversikt"+
                                "\nTast (2) Opprett og legg til i system"+
                                "\nTast (3) Bruk resept"+
                                "\nTast (4) Skriv ut statistikk"+
                                "\nTast (5) Skriv alle data til fil\n"+
                                "\nTast (0) Avslutt");
                                                
            input = inn.nextLine();
        
            //Utskrift av hele systemet
            if (input.equals("1")) {
                //skrivUtElementer();
            }

            //Opprette og legge til i legesystem
            else if (input.equals("2")) {
                menyLeggTil();
            }

            //Bruke resept
            else if (input.equals("3")) {
                menyBrukResept();
            }

            //Skrive ut statistikk
            else if (input.equals("4")) {
                //Skrive ut forskjellige former for statistikk (Oppgave E6)
            }

            //Skrive data til fil
            else if (input.equals("5")) {
                //Skrive ut forskjellige former for statistikk (Oppgave E7)
            }

            //Avslutte programmet
            else if (input.equals("0")) {
                svar = true;
                System.out.println("\nProgrammet avsluttes.");
            }

            else {
                System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
            }
        }

        //Avslutter scanner.
        inn.close();
    
    }

    //Undermeny for aa bruke resept
    public void menyBrukResept() {
        Scanner inn = new Scanner(System.in);
        String input;

        System.out.println("\nHvilken pasient vil du se resepter for:\n");
        
        //Liste over pasienter
        int indeks = 1;
        for (Pasient p: pasienter) {
            System.out.println("Tast (" + (String.valueOf(indeks)) + ") " +p.toString());
            indeks ++;
        }
        
        input = inn.nextLine();

        if (Integer.parseInt(input) < indeks && Integer.parseInt(input) > 0) {
            for (Pasient p: pasienter) {
                if (p.hentId() == Integer.parseInt(input)) {

                    //Liste over resepter
                    System.out.println("\nHvilken resept vil du bruke: \n");

                    indeks = 1;
                    for (Resept r: p.hentResepter()) {
                        System.out.println("Tast (" + (String.valueOf(indeks)) + ") " + r.hentLegemiddel().hentNavn() + ". Reit igjen: "+r.hentReit());
                        indeks ++;
                    }

                    input = inn.nextLine();

                    //Bruker resept
                    if (Integer.parseInt(input) < indeks && Integer.parseInt(input) > 0) {
                        for (Resept r: p.hentResepter()) {
                            if (r.hentId() == Integer.parseInt(input)) {
                                r.bruk();
                                System.out.println("\nBrukte resept paa "+ r.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: "+ r.hentReit());
                                hovedmeny();
                            }
                        }
                    }
                }
            }
        }

        else {
            System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
            System.out.println("____________________________________________");
            menyBrukResept();
        }

        //Avslutter scanner
        inn.close();
    }

    //Undermeny for aa legge til i legesystem
    public void menyLeggTil() {
        Scanner inn = new Scanner(System.in);
        String input;
        
        System.out.println("\nVelg hva du onsker aa legge til i systemet:");
        System.out.println("\nTast (1) Pasient"+
                            "\nTast (2) Lege"+
                            "\nTast (3) Legemiddel"+
                            "\nTast (4) Resept\n"+
                            "\nTast (0) Tilbake til hovedmeny");
        
        input = inn.nextLine();
        
        //Legge til pasient
        if (input.equals("1")) {
            System.out.println("\nSkriv inn pasientens navn:");
            String navn = inn.nextLine();
            
            System.out.println("\nSkriv inn pasientens fodselsnummer (11 siffer):");
            String fnr = inn.nextLine();

            if (!(fnr.length() == 11)) {
                System.out.println("\nUgyldig fodselsnummer. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
            }

            String linje = (navn + "," + fnr);
            lesInnPasient(linje);
        }

        //Legge til lege
        else if (input.equals("2")) {
            System.out.println("\nSkriv inn legens etternavn: ");
            String navn = inn.nextLine();
            
            //Sjekk om lege har kontrollID, hvis ikke kontrollID = 0
            System.out.println("\nEr legen spesialist: (ja/nei)");
            input = inn.nextLine().toLowerCase();
            String kontrollID = "0";
            
            //Kontrollerer at brukeren enten svarer ja eller nei.
            if (input.equals("ja")) {
                System.out.println("\nSkriv inn kontrollID: ");
                kontrollID = inn.nextLine();
            }

            else if (!(input.equals("nei"))) {
                System.out.println("\nUgyldig svar. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
            }
    
            String linje = ("Dr. " + navn + "," + kontrollID);
            lesInnLege(linje);
        }

        //Legge til legemiddel
        else if (input.equals("3")) {
            String styrke = "";
            System.out.println("\nSkriv inn legemiddelets navn: ");
            String navn = inn.nextLine();

            //Sjekker om vanedannende eller narkotisk, faar da parameter med styrke
            System.out.println("\nEr legemiddelet vanedannende (tast v) eller narkotisk (tast n):"+
                                "\nHvis ikke, tast en valgfri tast:");
            input = inn.nextLine().toLowerCase();
            String type = "vanlig";

            if (input.equals("v")) {
                type = "vanedannende";
                
                System.out.println("\nSkriv inn legemiddelets styrke: ");
                styrke = inn.nextLine();
            }

            else if (input.equals("n")) {
                type = "narkotisk";

                System.out.println("\nSkriv inn legemiddelets styrke: ");
                styrke = inn.nextLine();
            }

            System.out.println("\nSkriv inn legemiddelets pris: ");
            String pris = inn.nextLine();

            System.out.println("\nSkriv inn legemiddelets virkestoff: ");
            String virkestoff = inn.nextLine();

            String linje = (navn + "," + type + "," + pris + "," + virkestoff);
            
            if (type.equals("vanedannende" ) || type.equals("narkotisk")){
                linje = (navn + "," + type + "," + pris + "," + virkestoff + "," + styrke);
            }

            else {
                linje = (navn + "," + type + "," + pris + "," + virkestoff);
            }
            lesInnLegemiddel(linje);
        }

        else if (input.equals("4")) {
            String linje;

            /*
            **
            Her burde vi kanskje ha legemiddel-navn i stedet. Hvordan loeser vi dette evt...
            **
            */
            System.out.println("\nSkriv inn onsket legemiddel-nummer: ");
            String legemiddelNr = inn.nextLine();

            System.out.println("\nSkriv inn legens etternavn: ");
            String navn = inn.nextLine();

            System.out.println("\nSkriv inn pasientID: ");
            String pasientID = inn.nextLine();

            System.out.println("\nVelg type resept:");
            System.out.println("\nTast (p) PResept"+
                                "\nTast (m) Militaer"+
                                "\nTast (b) Blaa"+
                                "\nTast (h) Hvit\n"+
                                "\nTast (q) Tilbake til hovedmeny");
            
            String type = inn.nextLine().toLowerCase();

            if (type.equals("p")) {
                type = "p";
            }

            else if (type.equals("m")) {
                type = "militaer";
            }

            else if (type.equals("b")) {
                type = "blaa";
            }

            else if (type.equals("h")) {
                type = "hvit";
            }

            else if (type.equals("q")) {
                hovedmeny();
            }

            else {
                System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
            }

            //Unngaar at militaer faar argumentet reit.
            if (!type.equals("militaer")) {
                System.out.println("\nSkriv inn onsket reit:");
                String reit = inn.nextLine();
                linje = (legemiddelNr + ",Dr." + navn + "," + pasientID + "," + type + "," + reit);
            }

            else {
                linje = (legemiddelNr + ",Dr." + navn + "," + pasientID + "," + type);
            }

            lesInnResept(linje);
        }

        else if (input.equals("0")) {
            hovedmeny();
        }

        else {
            System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
            System.out.println("____________________________________________");
            menyLeggTil();
        }
        
        //Avslutter scanner.
        inn.close();
        hovedmeny();
    } 
}
    public void skrivTilFil() throws IOException{

        FileWriter fw = new FileWriter("utskrift.txt");

        fw.write("# Pasienter (navn, fnr)");

        for (Pasient p:pasienter){
            fw.write("\n" + p);
        }
        
        fw.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");

        fw.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");

        fw.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");

        fw.close();

    }

    //skriver ut alle elementer i systemet (Oppg E3)
    public void skrivUtElementer(){

      //skriver ut leger
      System.out.println("Leger i systemet: \n");

      for (Lege l: leger){
        System.out.println(l);
        IndeksertListe<Resept> resepter = l.hentResepter();
        System.out.println(l.hentNavn() + " har skrevet ut " +
        "foelgende resepter: ");
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
