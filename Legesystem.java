import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Legesystem{

    Koe<Pasient> pasienter = new Koe<>();
    IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    Prioritetskoe<Lege> leger = new Prioritetskoe<>();
    Stabel<Resept> resepter = new Stabel<>();

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

            String linje = sc.nextLine().stripTrailing();
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

                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus,
                                linje);
                    }

                }

                // Legemidler ...
                else if (innlesningsModus.equals("Legemidler")){

                    try{

                        lesInnLegemiddel(linje);

                    }

                    catch (Exception e){

                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus,
                                linje);
                    }

                }

                // Leger ...
                else if (innlesningsModus.equals("Leger")){

                    try{

                        lesInnLege(linje);

                    }

                    catch (Exception e){

                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus,
                                linje);
                    }

                }

                // Skriv resept
                else if (innlesningsModus.equals("Resepter")){

                    try{

                        lesInnResept(linje);

                    }

                    catch (Exception e){

                        skrivUtFeilmelding(linjeNummer, e, innlesningsModus,
                                linje);
                    }
                }

            }

        }


    }

    public void lesInnPasient(String linje) throws UgyldigInnlestLinje{

        System.out.print("Forsoeker aa lese inn pasient i systemet...");
        // Oppdeling av linjen
        String[] biter = linje.split(",");

        if (biter.length != 2){
            throw new UgyldigInnlestLinje(linje);
        }

        String navn = biter[0];
        String fnr = biter[1];

        if (!(fnr.length() == 11)) {
            throw new UgyldigInnlestLinje(linje);
        }

        //Sjekker om fnr er tall
        try {
            double nummer = Double.parseDouble(fnr);
        }
        catch (NumberFormatException e){
            throw new UgyldigInnlestLinje(linje);
        }

        Pasient p = new Pasient(navn, fnr);
        pasienter.leggTil(p);
        System.out.println("...Pasienten er lagt til systemet");
    }

    public void lesInnLegemiddel(String linje)throws UgyldigInnlestLinje{
        System.out.print("Forsoeker aa lese inn legemiddel i systemet...");

        // Oppdeling av linjen
        String[] biter = linje.split(",");

        if (biter.length < 4 || biter.length > 5){
            throw new UgyldigInnlestLinje(linje);
        }

        String navn = biter[0];
        String type = biter[1];

        if (!(type.equals("vanlig") || type.equals("narkotisk")
                    || type.equals("vanedannende")))
            {
            throw new UgyldigInnlestLinje(linje);
            }

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

        System.out.println("...Legemiddelet er lagt til systemet");
    }

    public void lesInnLege(String linje){
        System.out.print("Forsoeker aa lese inn lege i systemet...");

        // Oppdeling av linjen
        String[] biter = linje.split(",");

        if (biter.length != 2){
            throw new UgyldigInnlestLinje(linje);
        }

        String navn = biter[0];
        String kontrollid = biter[1];

        try{
            int nummer = Integer.parseInt(kontrollid);
        }
        catch (NumberFormatException e){
            throw new UgyldigInnlestLinje(linje);
        }

        // Oppretter riktig type  lege ut i fra kontrollid
        if (kontrollid.equals("0")){
            Lege l = new Lege(navn);
            leger.leggTil(l);
        }

        else {
            Spesialist s = new Spesialist(navn, kontrollid);
            leger.leggTil(s);
        }

        System.out.println("...Legen er lagt til systemet");
    }


    public void lesInnResept(String linje)throws UgyldigInnlestLinje{
        System.out.print("Forsoeker aa lese inn resept i systemet...");

        // Oppdeling av linjen
        String[] biter = linje.split(",");

        if (biter.length < 4 || biter.length > 5){
            throw new UgyldigInnlestLinje(linje);
        }

        int legemiddelNummer = Integer.parseInt(biter[0]);
        String legeNavn = biter[1];
        int pasientID = Integer.parseInt(biter[2]);
        String type = biter[3];

        if (!(type.equals("hvit") || type.equals("blaa")
                    || type.equals("militaer") ||
            type.equals("p"))){
                throw new UgyldigInnlestLinje(linje);
            }

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
                    Resept r = utskrivendeLege.skrivMilResept(
                            legemiddelPaaResept, pasientPaaResept);
                    resepter.leggTil(r);
                }

                catch (Exception e){

                    System.out.println(e); // TEMP
                }
            }

            else if (type.equals("hvit")){

                try{
                  Resept r = utskrivendeLege.skrivHvitResept(
                          legemiddelPaaResept, pasientPaaResept, reit);
                  resepter.leggTil(r);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

            else if (type.equals("blaa")){

                try{
                  Resept r = utskrivendeLege.skrivBlaaResept(
                          legemiddelPaaResept, pasientPaaResept, reit);
                  resepter.leggTil(r);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

            else if (type.equals("p")){

                try{
                  Resept r = utskrivendeLege.skrivPResept(legemiddelPaaResept,
                          pasientPaaResept, reit);
                  resepter.leggTil(r);
                }

                catch (Exception e){

                    System.out.println(e);
                }
            }

        }

        System.out.println("...Resepten er lagt til systemet");
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

    public void skrivUtFeilmelding(int linjeNummer, Exception e,
            String innlesningsModus, String linje){

        System.out.println("Linje " + linjeNummer + ": Feil linjeformat for "
                + innlesningsModus + ":");
        System.out.println("Linjen \"" + linje + "\" produserte feilmelding:");
        System.out.println("\"" + e + "\"");
        System.out.println(" --> Hopper over linje " + linjeNummer + ".");
    }

    public String nyScannerInput(){
        System.out.print("\nValg: ");
        Scanner inn = new Scanner(System.in);
        String input;

        input = inn.nextLine();

        return input;
    }

    public void hovedmeny() {
        boolean aktivMeny = true;


        while (aktivMeny) {
            System.out.println("\nMeny:");
            System.out.println("\nTast (1) Skriv ut oversikt"+
                                "\nTast (2) Opprett og legg til i system"+
                                "\nTast (3) Bruk resept"+
                                "\nTast (4) Skriv ut statistikk"+
                                "\nTast (5) Skriv alle data til fil\n"+
                                "\nTast (0) Avslutt");

            String brukerInput = nyScannerInput();

            //Utskrift av hele systemet
            if (brukerInput.equals("1")) {
                skrivUtElementer();
            }

            //Opprette og legge til i legesystem
            else if (brukerInput.equals("2")) {
                aktivMeny = false;
                menyLeggTil();
            }

            //Bruke resept
            else if (brukerInput.equals("3")) {
                aktivMeny = false;
                menyBrukResept();
            }

            //Skrive ut statistikk
            else if (brukerInput.equals("4")) {
                skrivUtStatistikk();
            }

            //Skrive data til fil
            else if (brukerInput.equals("5")) {
                try {
                    skrivTilFil();
                }

                catch (IOException e) {
                    System.out.println(e);
                }
            }

            //Avslutte programmet
            else if (brukerInput.equals("0")) {
                aktivMeny = false;
                System.out.println("\nProgrammet avsluttes.");
            }

            else {
                System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
                System.out.println("_________________________________________");
            }
        }
    }

    //Undermeny for aa bruke resept
    public void menyBrukResept() {

        System.out.println("\nHvilken pasient vil du se resepter for:\n");
        //Liste over pasienter
        int indeks = 1;
        for (Pasient p: pasienter) {
            System.out.println("Tast (" + (String.valueOf(indeks)) + ") "
                    + p.toString());
            indeks ++;
        }
        System.out.println("\nTast (0) Hovedmeny");

        String brukerInput = nyScannerInput();

        //sjekker om input er tall.
        try{
            int nummer = Integer.parseInt(brukerInput);
        }
        catch (NumberFormatException e){

            System.out.println("Input maa vaere et tall.");
            menyBrukResept();
            return;
        }

        if (Integer.parseInt(brukerInput) < indeks
                && Integer.parseInt(brukerInput) > 0) {

            for (Pasient p: pasienter) {

                if ((p.hentId() == Integer.parseInt(brukerInput)
                            && (p.hentResepter().stoerrelse() != 0))) {
                    //Liste over resepter
                    System.out.println("\nHvilken resept vil du bruke: \n");

                    indeks = 1;

                    for (Resept r: p.hentResepter()) {
                        System.out.println("Tast (" + (String.valueOf(indeks))
                                + ") " + r.hentLegemiddel().hentNavn()
                                + ". Reit igjen: "+r.hentReit());
                        indeks ++;
                    }
                    System.out.println("\nTast (0) Hovedmeny");

                    brukerInput = nyScannerInput();

                    //sjekker om input er tall.
                    try{
                        int nummer = Integer.parseInt(brukerInput);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Input maa vaere et tall.");
                        menyBrukResept();
                        return;
                    }

                    //Bruker resept
                    if (Integer.parseInt(brukerInput) < indeks
                            && Integer.parseInt(brukerInput) > 0) {
                        for (Resept r: p.hentResepter()) {
                            if (r.hentId() == Integer.parseInt(brukerInput)) {
                                if (r.bruk()){
                                    System.out.println("\nBrukte resept paa "
                                            + r.hentLegemiddel().hentNavn()
                                            + ". Antall gjenvaerende reit: "
                                            + r.hentReit());
                                    hovedmeny();
                                }
                                else{
                                    System.out.println("Resept tom. Ingen reit igjen.");
                                    menyBrukResept();
                                }
                            }
                        }
                    }

                    else if (Integer.parseInt(brukerInput) == 0) {
                        hovedmeny();
                    }
                }

                else if ((p.hentId() == Integer.parseInt(brukerInput)
                            && p.hentResepter().stoerrelse() == 0)){
                    System.out.println("Ingen resepter for gjeldende pasient"
                            + p + ". Gaar tilbake til hovedmeny.");
                    hovedmeny();
                }
            }
        }
        else if (Integer.parseInt(brukerInput) == 0){
            hovedmeny();
        }
        else {
            System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
            System.out.println("____________________________________________");
            menyBrukResept();
        }
    }

    //Undermeny for aa legge til i legesystem
    public void menyLeggTil() {
        System.out.println("\nVelg hva du onsker aa legge til i systemet:");
        System.out.println("\nTast (1) Pasient"+
                            "\nTast (2) Lege"+
                            "\nTast (3) Legemiddel"+
                            "\nTast (4) Resept\n"+
                            "\nTast (0) Tilbake til hovedmeny");

        String input = nyScannerInput();

        //Legge til pasient
        if (input.equals("1")) {
            System.out.println("\nSkriv inn pasientens navn:");
            String navn = nyScannerInput();

            System.out.println("\nSkriv inn pasientens fodselsnummer (11 siffer):");
            String fnr = nyScannerInput();

            if (!(fnr.length() == 11)) {
                System.out.println("\nUgyldig fodselsnummer. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
            }

            String linje = (navn + "," + fnr);
            try{
                lesInnPasient(linje);
            }
            catch (UgyldigInnlestLinje e){
                System.out.println("Ugyldig input");
            }

        }

        //Legge til lege
        else if (input.equals("2")) {
            System.out.println("\nSkriv inn legens etternavn: ");
            String navn = nyScannerInput();

            //Sjekk om lege har kontrollID, hvis ikke kontrollID = 0
            System.out.println("\nEr legen spesialist: (ja/nei)");
            input = nyScannerInput().toLowerCase();
            String kontrollID = "0";

            //Kontrollerer at brukeren enten svarer ja eller nei.
            if (input.equals("ja")) {
                System.out.println("\nSkriv inn kontrollID: ");
                kontrollID = nyScannerInput();
            }

            else if (!(input.equals("nei"))) {
                System.out.println("\nUgyldig svar. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
                return;
            }

            String linje = ("Dr. " + navn + "," + kontrollID);
            try{
                lesInnLege(linje);
            }
            catch (UgyldigInnlestLinje e){
                System.out.println("Feil i input."
                        + "Eksempel for riktig format: Fransen,12345");
            }
        }

        //Legge til legemiddel
        else if (input.equals("3")) {
            String styrke = "";
            System.out.println("\nSkriv inn legemiddelets navn: ");
            String navn = nyScannerInput();

            //Sjekker om vanedannende eller narkotisk, faar da parameter med styrke
            System.out.println("\nEr legemiddelet vanedannende (tast v) "
                   +"eller narkotisk (tast n):"+
                                "\nHvis ikke, tast en valgfri tast:");
            input = nyScannerInput().toLowerCase();
            String type = "vanlig";

            if (input.equals("v")) {
                type = "vanedannende";

                System.out.println("\nSkriv inn legemiddelets styrke: ");
                styrke = nyScannerInput();
            }

            else if (input.equals("n")) {
                type = "narkotisk";

                System.out.println("\nSkriv inn legemiddelets styrke: ");
                styrke = nyScannerInput();
            }

            System.out.println("\nSkriv inn legemiddelets pris: ");
            String pris = nyScannerInput();

            System.out.println("\nSkriv inn legemiddelets virkestoff: ");
            String virkestoff = nyScannerInput();

            String linje = (navn + "," + type + "," + pris + "," + virkestoff);

            if (type.equals("vanedannende" ) || type.equals("narkotisk")){
                linje = (navn + "," + type + "," + pris + "," + virkestoff
                        + "," + styrke);
            }

            else {
                linje = (navn + "," + type + "," + pris + "," + virkestoff);
            }
            System.out.println(linje);
            lesInnLegemiddel(linje);
        }

        else if (input.equals("4")) {
            String linje;

            /*
            **
            Her burde vi kanskje ha legemiddel-navn i stedet. Hvordan loeser vi dette evt...
            **
            */
            System.out.println("\nSkriv inn oensket legemiddel-nummer: ");

            for (Legemiddel lm: legemidler){

                System.out.println(lm.hentId() + " - "+ lm.hentNavn());

            }
            String legemiddelNr = nyScannerInput();

            System.out.println("\nSkriv inn legens etternavn: ");
            String navn = nyScannerInput();

            System.out.println("\nSkriv inn pasientID: ");
            String pasientID = nyScannerInput();

            System.out.println("\nVelg type resept:");
            System.out.println("\nTast (p) PResept"+
                                "\nTast (m) Militaer"+
                                "\nTast (b) Blaa"+
                                "\nTast (h) Hvit\n"+
                                "\nTast (q) Tilbake til hovedmeny");

            String type = nyScannerInput().toLowerCase();

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
                return;
            }

            else {
                System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
                System.out.println("____________________________________________");
                menyLeggTil();
                return;
            }

            //Unngaar at militaer faar argumentet reit.
            if (!type.equals("militaer")) {
                System.out.println("\nSkriv inn onsket reit:");
                String reit = nyScannerInput();
                linje = (legemiddelNr + ",Dr. " + navn + "," + pasientID
                        + "," + type + "," + reit);
            }

            else {
                linje = (legemiddelNr + ",Dr." + navn + "," + pasientID
                        + "," + type);
            }
            System.out.println(linje);
            lesInnResept(linje);
        }

        else if (input.equals("0")) {
            hovedmeny();
            return;
        }

        else {
            System.out.println("\nUgyldig input. Vennligst prov paa nytt.");
            System.out.println("____________________________________________");
            menyLeggTil();
            return;
        }
        hovedmeny();
    }

    public void skrivUtStatistikk(){
        int tellerV = 0;
        int tellerN = 0;
        for (Resept r: resepter){
            if (r.hentLegemiddel().getClass().getName().equals("Vanedannende")){
                tellerV ++;
            }
            else if (r.hentLegemiddel().getClass().getName().equals("Narkotisk")){
                tellerN ++;
            }
        }
        System.out.println("Totalt antall utskrevne resepter med vanedannende legemidler: "
                + tellerV
                + "\nTotalt antall utskrevne resepter med narkotiske legemidler: "
                + tellerN);

        Prioritetskoe<String> legeListeNarkotiskeResepter = new Prioritetskoe<>();

        System.out.println("\nFoelgende leger har skrevet ut resepter med narkotiske legemidler: \n");
        for (Lege l : leger){
            int legensTellerN = 0;
            for (Resept r: l.hentResepter()){
                if (r.hentLegemiddel().getClass().getName().equals("Narkotisk")){
                    legensTellerN ++;
                }
            }

            if (legensTellerN != 0){
                String legeAntallN = Integer.toString(legensTellerN);
                String legeInfo = l.hentNavn() + ": " + legeAntallN;
                legeListeNarkotiskeResepter.leggTil(legeInfo);
            }
        }

        for (String s : legeListeNarkotiskeResepter){
            System.out.println("\t- " + s);
        }

        Prioritetskoe<String> pasientStringer = new Prioritetskoe<>();

        System.out.println("\nFoelgende pasienter har gyldig resept paa narkotiske legemidler: \n");
        for (Pasient p : pasienter){
            int pasientensTellerN = 0;
            for (Resept r: p.hentResepter()){
                if (r.hentLegemiddel().getClass().getName().equals("Narkotisk")){
                    pasientensTellerN ++;
                }
            }

            if (pasientensTellerN != 0){
                String pasientAntallN = Integer.toString(pasientensTellerN);
                String pasientInfo = p.hentNavn() + ": " + pasientAntallN;
                pasientStringer.leggTil(pasientInfo);
            }
        }

        for (String s : pasientStringer){
            System.out.println("\t- " + s);
        }
    }

    public void skrivTilFil() throws IOException{
      // Legg til Exception-haandtering
        FileWriter fw = new FileWriter("utskrift.txt");

        fw.write("# Pasienter (navn, fnr)");

        for (Pasient p:pasienter){
            fw.write("\n" + p.eksportString());
        }

        fw.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");

        for (Legemiddel l : legemidler){
            fw.write("\n" + l.eksportString());
        }

        fw.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");

        for (Lege l : leger){
          fw.write("\n" + l.eksportString());
        }

        fw.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");

          for (Resept r : resepter){
            fw.write("\n" + r.eksportString());
        }

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
                System.out.println("\tTil pasient: " + r.hentPasient().hentNavn());
                System.out.println("\t" + r);
                System.out.println("\tLegemiddelet i resepten er av type: " +
                r.hentLegemiddel().getClass().getName() + "\n");
            }
        }

        //Skriver ut pasienter
        System.out.println("Pasienter i systemet: \n");
        for (Pasient p: pasienter){
            System.out.println(p);
        }

        //skriver ut legemidler
        System.out.println("\nLegemidler i systemet: \n");
        for (Legemiddel l: legemidler){
            System.out.println(l);
        }
    }
}
