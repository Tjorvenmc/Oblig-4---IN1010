import java.io.FileNotFoundException;
import java.io.IOException;

public class testSystem{

    public static void main(String[] args){

        Legesystem ls = new Legesystem();

        try {
            ls.lesInnFraFil("legedata.txt");
        }

        catch (FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }
        ls.hovedmeny();
    }
}
