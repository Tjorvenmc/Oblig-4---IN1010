public class MinTest{
    public static void main(String[] args){

        Stabel<String> testStabel = new Stabel<String>();
        testStabel.leggTil("Test");
        testStabel.leggTil("Test 2");
        testStabel.leggTil("Test 3");
        System.out.println(testStabel.toString());



        IndeksertListe<Integer> minListe = new IndeksertListe<>();

        minListe.leggTil(0, 1);
        minListe.leggTil(0, 1);

        System.out.println("Sjekker stoerrelse...");
        System.out.println(minListe.stoerrelse());

        
        System.out.println("Legger til tallet 5 paa indeks 1 ");
        minListe.leggTil(1, 5);
        System.out.println("Sjekker stoerrelse...");
        System.out.println(minListe.stoerrelse());

        System.out.println(minListe.toString());
     
        System.out.println("Legger til tallet 7 paa indeks 0 ");
        minListe.leggTil(0, 7);
        System.out.println(minListe.toString());

        minListe.sett(2, 10);
        System.out.println("Tallet paa indeks 2 buerde naa vaere 10.");
        System.out.println(minListe.toString());


        System.out.println("Sletter tallet paa indeks 1...");
        int tall = minListe.fjern(1);
        System.out.println("Tallet var " + tall);
        System.out.println(minListe.toString());

        minListe = new IndeksertListe<>();
        // working until here
        minListe.leggTil(0, 0);
        minListe.leggTil(12);
        System.out.println(minListe.toString());


    }


}    

