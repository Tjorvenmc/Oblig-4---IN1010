abstract class Lenkeliste<T> implements Liste<T> {

    class Node {
        Node neste = null;
        T data;

        Node(T x){
            data = x;
        }

    }

    public Node start = null;

    public int antallNoder = 0;

    public int stoerrelse(){
        return antallNoder;
    }


    /**
     * Denne metoden legger til et nytt element sist i listen.
     */
    public void leggTil(T x){

        Node nyNode = new Node(x);

        // Hvis listen er tom blir den nye noden starten paa listen.
        if (this.start == null){
            start = nyNode;
        }

        // Hvis ikke gaar vi gjennom listen til vi finner siste noden (den som
        // ikke har en "neste") og legger den nye noden til etter denne.
        else {
            Node aktuellNode = start;
            while (aktuellNode.neste != null){
                aktuellNode = aktuellNode.neste;
            }
            aktuellNode.neste = nyNode;
        }

        // Til slutt inkrementer vi antallNoder.
        antallNoder ++;
    }

    /**
     * Denne metoden returnerer det foerste elementet i listen.
     */
    public T hent(){

        if (this.start == null){
            throw new UgyldigListeindeks(-1);
        }
        
        else{
            T res = start.data;
            return res;
        }
    }

    /**
     * Denne metoden fjerner det foerste elementet i listen og returnerer
     * det.
     */
    public T fjern() throws UgyldigListeindeks{
        
        // Hvis listen er tom...
        if (this.start == null){
            throw new UgyldigListeindeks(0);
            //return null; // temp
        }

        // Hvis ikke listen er tom...
        else{
            T res = start.data; // det er dette vi vil hente ut
            start = start.neste; // den andre noden i listen blir nye "start"
            antallNoder --; // antallNoder -1
            return res;
        }
    }

    /**
     * Denne metoden bygger opp en svarstreng av elementene i listen.
     */
    public String toString(){

        String res = "";
        int indeks = 0;

        if (this.start == null){
            res = "(Tom liste.)";
        }

        else {
            Node aktuellNode = this.start;
            res += "Indeks " + indeks + ": " + aktuellNode.data + "\n";
            while (aktuellNode.neste != null){
                aktuellNode = aktuellNode.neste;
                indeks ++;
                res += "Indeks " + indeks + ": " + aktuellNode.data + "\n";
            }
        }

        return res;
    }
        
    /**
     * Dette er en hjelpemetode som sjekker om argumentet pos er en 
     * gyldig indeks gitt metoden som skal utfoeres og listens aktuelle tilstand.
     */
    public boolean gyldigIndeks(int indeks, String metode){

        if (metode == "leggTil"){
            return (0 <= indeks && indeks <= stoerrelse());
        }

        else{
            return (0 <= indeks && indeks < stoerrelse());
        }
    }

    /**
     * Dette er en hjelpemetode som returnerer noden paa angitt posisjon
     * (ikke bare dens data.)
     */
    public Node hentNode(int pos){

        // Begynner paa start og gaar oppover
        Node aktuellNode = this.start;
        int aktuellPos = 0;

        while (aktuellPos < pos){
            aktuellNode = aktuellNode.neste;
            aktuellPos ++;
        }

        return aktuellNode;
    }

}
