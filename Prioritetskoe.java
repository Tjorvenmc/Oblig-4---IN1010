public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x){

        // Hvis listen er tom kan vi bruke den "vanlige" leggTil-metoden.
        if (this.start == null){
            super.leggTil(x);
            return;
        }

        // Hvis ikke listen er tom ...
       
        // Oppretter nyNode.
        Node nyNode = new Node(x);

        // Sjekker om det finnes en "stoerre" node.
        boolean stoerreFunnet = false;
        int stoerreFunnetIndeks = 0;

        for (int i=0; i < this.stoerrelse(); i++){
        
            Node aktuellNode = super.hentNode(i);

            if (aktuellNode.compareTo(nyNode) >= 0){
                stoerreFunnetIndeks = i;
                stoerreFunnet = true;
                break;
            }
        }
            
        // Hvis ingen stoerre node ble funnet, blir den nye den siste i listen.
        if (!stoerreFunnet){
        
            Node sisteNode = super.hentNode(this.stoerrelse() - 1);
            sisteNode.neste = nyNode;
        }

        // Hvis det finnes "stoerre" noder ble den nye noden enten lagt til
        // foerst i listen eller mellom to andre noder.
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

        // Til slutt inkrementerer vi antallNoder.
        super.antallNoder++;
    }

    @Override
    public T hent() throws UgyldigListeindeks{

        
        if (!super.gyldigIndeks(0, "hent")){
            throw new UgyldigListeindeks(0);
        }

        else {
            T res = this.start.data;
            return res;
        }
    }

    @Override
    public T fjern(){

        if (!super.gyldigIndeks(0, "fjern")){
            throw new UgyldigListeindeks(0);
        }

        else {

            T res = this.start.data;

            // Hvis det ligger mer enn ett element i listen, maa dens "start"
            // tilpasses.
            if (this.stoerrelse() >= 2){
                this.start = this.start.neste;
            }

            // Hvis ikke setter vi bare "start" til null.
            else { 
                this.start = null;
            }

            // Til slutt tilpasser vi antallNoder.
            super.antallNoder--;
            return res;
        }
    }

}
