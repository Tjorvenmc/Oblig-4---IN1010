public class Stabel<T> extends Lenkeliste<T> implements Liste<T>{
    
    public int stoerrelse (){
        int res = super.stoerrelse();
        return res;
    }

    @Override
    public void leggTil (T x){
        
        Node nyNode = new Node(x);

        // Hvis listen er tom ...
        if (this.start == null){
            start = nyNode;
        }

        // Hvis listen ikke er tom ...
        else{
            nyNode.neste = start;
            start = nyNode;
        }

        super.antallNoder ++;
    }

    public T hent(){
        T res = super.hent();
        return res;
    }

    public T fjern(){
       T res = super.fjern();
       return res;
    }
}
