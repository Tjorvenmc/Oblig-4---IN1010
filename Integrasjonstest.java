public class Integrasjonstest{
    public static void main(String[] args){
     
        /**
         * OPPRETTER LEGEMIDLER
         */
        Vanlig aspirin = new Vanlig("Aspirin", 49, 500);
        Vanedannende morfium = new Vanedannende("Morfium", 4000, 50, 10);
        Narkotisk ketamin = new Narkotisk("Ketamin", 500, 50, 10);

        /**
         * OPPRETTER LEGE OG SPESIALIST
         */
        Lege minLege = new Lege("Dr. Acula");
        Spesialist dinLege = new Spesialist("Dr. Stefan Frank", "007");

        /**
         * OPPRETTER RESEPTER
         */

        MilResept resept1 = new MilResept(aspirin, minLege, 666);
        PResept resept2 = new PResept(ketamin, minLege, 123, 5);
        BlaaResept resept3 = new BlaaResept(morfium, minLege, 123, 1);

        /**
         * SKRIVER UT INFORMASJON OM OBJEKTENE
         */
        System.out.println(aspirin);
        System.out.println(morfium);
        System.out.println(ketamin);
        
        System.out.println(minLege);
        System.out.println(dinLege);

        System.out.println(resept1);
        System.out.println(resept2);
        System.out.println(resept3);
    }

}    

