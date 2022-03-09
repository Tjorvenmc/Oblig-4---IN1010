public class Koe<T> extends Lenkeliste<T> implements Liste<T>{
    
    public int stoerrelse (){
        int res = super.stoerrelse();
        return res;
    }

    // leggTil-funksjoner funker som i superklassen.
    public void leggTil (T x){
        
        super.leggTil(x);

    }

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

    public T fjern() throws UgyldigListeindeks{
        // Hvis listen er tom ...
        if (this.start == null){
            throw new UgyldigListeindeks(-1);
        }
        else{
            T res = super.fjern();
            return res;
        }
    }
}
    //T hent ();
    //T fjern ();
