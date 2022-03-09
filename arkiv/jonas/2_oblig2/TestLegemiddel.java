public class TestLegemiddel{
    public static void main(String[] args){
        Vanlig aspirin = new Vanlig("Aspirin", 10, 5);
        String test = aspirin.toString();
        System.out.println(test);
     
        Vanlig ibux = new Vanlig("Ibux", 10, 5);
        String test2 = ibux.toString();
        System.out.println(test2);

        Vanedannende morfium = new Vanedannende("Morfium", 100, 50, 10);
        String test3 = morfium.toString();
        System.out.println(test3);

        Narkotisk ketamin = new Narkotisk("Ketamin", 100, 50, 10);
        String test4 = ketamin.toString();
        System.out.println(test4);

        if (idTest(morfium, 3)) {
            System.out.println("ID-Test 1: Suksess");
        }

        else {
            System.out.println("ID-Test 1: FEIL!!!");
        }

        ketamin.settNyPris(8000);

        if (prisTest(ketamin, 8000)) {
            System.out.println("Pristest 1: Suksess");
        }
        else {
            System.out.println("Pristest 1: FEIL!!!");
        }

    }

    public static boolean idTest(Legemiddel legemiddel, int forventetId){

        return legemiddel.hentId() == forventetId;

    }

    public static boolean prisTest(Legemiddel legemiddel, int forventetPris){

        return legemiddel.hentPris() == forventetPris;
    }

}    

