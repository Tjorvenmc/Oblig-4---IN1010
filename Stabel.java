public class Stabel<T> extends Lenkeliste<T> {
    
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
}
