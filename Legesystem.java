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

        String modus = null;

        // Gaa gjennom linjene i filene
        while (sc.hasNextLine()){
            
            String linje = sc.nextLine();

            // Linjen begynner med #
            if linje.charAt(0).equals("#"){
                String[] biter = linje.split(" ");
                modus = biter[1];
            }

            // Linjen begynner ikke med #
            else{
                String[] biter = linje.split(",");

                // Pasienter ...
                if (modus.equals("Pasienter")){
                    Pasient p = new Pasient(biter[0], biter[1]);
                    pasienter.leggTil(p);
                }

                // Legemidler ...
                else if (modus.equals("Legemidler")){

                    if (biter[1].equals("vanlig")){
                        Vanlig v = new Vanlig(biter[0], biter[2], biter[3]);
                        legemidler.feggTil(v);
                    }

                    else if (biter[1].equals("narkotisk")){
                        Narkotisk n = new Narkotisk(biter[0], biter[2], biter[3],
                                biter[4]);
                        legemidler.leggTil(n);
                    }

                    else if (biter[1].equals("vanedannende")){
                        Vanedannende v = new Vanedannende(biter[0], biter[2], biter[3],
                                biter[4]);
                        legemidler.leggTil(v);
                    }

                    else {
                        ;
                    }
                }

                // Leger ...
                else if (modus.equals("Leger")){

                    // Ikke spesialist
                    if (biter[1].equals("0")){

                        Lege l = new Lege(biter[0]);
                        leger.leggTil(l);
                    }

                    // Spesialist
                    else {
                        Spesialist s = new Spesialist(biter[0], biter[1]);
                        leger.leggTil(s);
                    }
                }

                else if (modus.equals("Resepter")){
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

                    // Skriv resept
                }

                else {
                    System.out.println("Error");
                }
                
            }



        }

    
    }
}

