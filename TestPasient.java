public class TestPasient{

    public static void main(String[] args){

        Pasient minPasient = new Pasient("Ola Nordmann", "13108812345");
        
        Lege minLege = new Lege("Dr. Acula");

        Vanlig ibux = new Vanlig("Ibux", 39, 50);

        HvitResept mittResept = new HvitResept(ibux, minLege, minPasient, 3);
        
        minPasient.leggTilResept(mittResept);
        
        System.out.println(minPasient);
        

    }

}    

