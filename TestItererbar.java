public class TestItererbar{
    public static void main(String[] args){
     
        IndeksertListe<String> minListe = new IndeksertListe<>();

        minListe.leggTil("Test 1");
        minListe.leggTil("Test 2");
        minListe.leggTil("Test 3");
        minListe.leggTil("Test 4");

        for (String s:minListe) {
        
            System.out.println(s);
        }

    }

}    

