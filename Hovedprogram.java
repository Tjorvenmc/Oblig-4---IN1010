import java.io.FileNotFoundException;

public class Hovedprogram{

    public static void main(String[] args){

        try{
        Legesystem ls = new Legesystem("legedata_test.txt");
        }

        catch (FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }
     

    }

}    

