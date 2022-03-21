import java.io.FileNotFoundException;

public class TestFilUtskriftMetoder{

    public static void main(String[] args){

        Legesystem ls = new Legesystem();

        try {
            ls.lesInnFraFil("legedata_test.txt");
        }

        catch (FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }

        for (Pasient p:ls.pasienter){
        
            System.out.println(p.eksportString());
        }

        for (Legemiddel lm:ls.legemidler){
        
            System.out.println(lm.eksportString());
        }

        for (Lege l:ls.leger){
        
            System.out.println(l.eksportString());
        }

        for (Resept r:ls.resepter){
        
            System.out.println(r.eksportString());
        }
     

    }

}    

