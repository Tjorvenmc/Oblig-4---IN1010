import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem{

    IndeksertListe<Pasient> pasienter;
    IndeksertListe<Legemiddel> legemidler;
    IndeksertListe<Lege> leger;

    /**
     * KONSTRUKTOER
     */
    public Legesystem(String filnavn) throws FileNotFoundException{
         
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
        int styrke = Integer.parseInt(biter[4]);

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


        // Ikke spesialist
        if (kontrollid.equals("0")){

            Lege l = new Lege(navn);
            leger.leggTil(l);
        }

        // Spesialist
        else {
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
        int reit = Integer.parseInt(biter[4]); // OBS finnes ikke alltid

        // Finn lege

        boolean legeFunnet = false;
        Lege aktuellLege = null;

        while (!legeFunnet){
            for (Lege l: leger){
                if (biter[1].equals(l.hentNavn())){
                
                    legeFunnet = true;
                    aktuellLege = l;

                }
            }
        }
    }

}

