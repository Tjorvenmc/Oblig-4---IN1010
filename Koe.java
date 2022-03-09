public class Koe<T> extends Lenkeliste<T> {
    
    // hent-Metoden skal returnere det foerste elementet i koen.
    @Override
    public T hent() throws UgyldigListeindeks{

        // Hvis listen er tom ...
        if (this.start == null){
            throw new UgyldigListeindeks(-1);
        }

        // Hvis ikke returnerer vi dataen til den foerste noden i koen.
        else {
            T res = start.data;
            return res;
        }
    }
}
