import java.io.FileNotFoundException;
import java.io.IOException;

public class Hovedprogram{

    public static void main(String[] args){

        Legesystem ls = new Legesystem();

        try {
            ls.lesInnFraFil("legedata_test.txt");
        }

        catch (FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }

        try {
            ls.skrivTilFil();
        }

        catch (IOException e) {
            System.out.println(e);
        }

    }

}
