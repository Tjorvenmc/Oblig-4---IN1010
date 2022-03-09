public class TestResepter{
    public static void main(String[] args){
     
        /**
         * OPPRETTER LEGEMIDLER
         */
        Vanlig aspirin = new Vanlig("Aspirin", 49, 500);
        Vanlig ibux = new Vanlig("Ibux", 79, 1000);
        Vanedannende morfium = new Vanedannende("Morfium", 4000, 50, 10);
        Narkotisk ketamin = new Narkotisk("Ketamin", 500, 50, 10);

        /**
         * OPPRETTER LEGE
         */
        Lege minLege = new Lege("Dr. Acula");

        /**
         * OPPRETTER RESEPTER
         */

        MilResept resept1 = new MilResept(aspirin, minLege, 666);
        PResept resept2 = new PResept(ibux, minLege, 123, 5);
        BlaaResept resept3 = new BlaaResept(morfium, minLege, 007, 1);

        /**
         * KJOERER NOEN TESTER
         */

        System.out.println(resept1.toString());
        System.out.println(resept2.toString());
        System.out.println(resept3.toString());
        resept3.bruk(); // resept 3 (morfium boer naa ha Reit: 0)
        System.out.println(resept2.prisAaBetale()); // burde vaere 0
        System.out.println(resept3.toString()); // burde vaere ugyldig
    }

}    

