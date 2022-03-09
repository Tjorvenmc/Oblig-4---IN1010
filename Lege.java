public class Lege{
     
    String navn;

    /**
     * Konstruktoer
     */
    public Lege(String navn){
    
        this.navn = navn;
    }

    public String hentNavn(){
        return this.navn;
    } 

    public String toString(){
        return "Lege " + this.navn + ".";
    }
}

class Spesialist extends Lege implements Godkjenningsfritak{

    String kontrollID;

    /**
     * Konstruktoer
     */
    public Spesialist(String navn, String kontrollID){

        super(navn);
        this.kontrollID = kontrollID;
    }
        
    public String hentKontrollID(){
       return this.kontrollID;
    }

    public String toString(){
        return "Spesialist " + this.navn + " (KontrollID " + this.kontrollID
            + ").";
    }
}
    


