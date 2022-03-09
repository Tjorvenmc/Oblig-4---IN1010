public class PrioritetsKoe<T extends Comparable<T>> extends Lenkeliste<T>{


    public int stoerrelse(){
        int res = super.stoerrelse();
        return res;
    }

    @Override
    public void leggTil(T x){

        // ...
        if (this.start == null){
            super.leggTil(x);
            return;
        }

        // Opprett nyNode
        Node nyNode = new Node(x);

        // ...
        boolean stoerreFunnet = false;
        int stoerreFunnetIndeks = 0;
        // Gaa gjennom nodene
        while (!stoerreFunnet){
            for (int i=0; i < this.stoerrelse(); i++){
            
                Node aktuellNode = super.hentNode(i);

                if (aktuellNode.data >= nyNode.data){
                    stoerreFunnetIndeks = i;
                    stoerreFunnet = true;
                }
            }
        }
        if (!stoerreFunnet){
        
            Node sisteNode = super.hentNode(this.stoerrelse() - 1);
            sisteNode.neste = nyNode;
        }

        else {
        
            if (stoerreFunnetIndeks == 0){
                nyNode.neste = this.start;
                this.start = nyNode;
            }

            else {
                
                Node forrigeNode = super.hentNode(stoerreFunnetIndeks - 1);
                Node nesteNode = super.hentNode(stoerreFunnetIndeks);
                forrigeNode.neste = nyNode; 
                nyNode.neste = nesteNode;
            }
        }

        super.antallNoder++;
    }

    @Override
    public T hent(){
        return null;
    }

    @Override
    public T fjern(){
        return null;
    }

}
