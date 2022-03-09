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


