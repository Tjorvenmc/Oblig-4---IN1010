class IndeksertListe <T> extends Lenkeliste<T> {

  public IndeksertListe(){
    super();
  }

  public void leggTil(int pos, T x){
    //sjekker gyldig indeks
    if (0 <= pos && pos <= stoerrelse()){
      Node nyNode = new Node(x);
      //hvis listen er tom
      if (stoerrelse() == 0){
        foersteNode = nyNode;
      } else{
        //hvis ikke-tom
        Node midlertidigNode = foersteNode;

        //hvis noden skal foerst i listen
        if (pos == 0){
          nyNode.settNeste(midlertidigNode);
          midlertidigNode.settForrige(nyNode);
          foersteNode = nyNode;

          //hvis noden skal sist i listen
        } else if (pos == stoerrelse()){
            while (midlertidigNode.hentNeste() != null){
            midlertidigNode = midlertidigNode.hentNeste();
          }
          nyNode.settForrige(midlertidigNode);
          midlertidigNode.settNeste(nyNode);

          //hvis noden skal mellom to andre noder
        } else {
          for (int i = 0; i < pos; i++){
            midlertidigNode = midlertidigNode.hentNeste();
          }
          nyNode.settForrige(midlertidigNode.hentForrige());
          nyNode.settNeste(midlertidigNode);
          midlertidigNode.hentForrige().settNeste(nyNode);
          midlertidigNode.settForrige(nyNode);
        }
      }
    } else{
      throw new UgyldigListeindeks(pos);
    }
    antallNoder++;
  }


  public void sett(int pos, T x){
    if (0 <= pos && pos < stoerrelse()){
      Node midlertidigNode = foersteNode;
      for (int i = 0; i < pos; i++){
        midlertidigNode = midlertidigNode.hentNeste();
      }
      midlertidigNode.settData(x);
    } else{
      throw new UgyldigListeindeks(pos);
    }
  }


  public T hent (int pos){
    if (0 <= pos && pos < stoerrelse()){
      Node midlertidigNode = foersteNode;
      for (int i = 0; i < pos; i++){
        midlertidigNode = midlertidigNode.hentNeste();
      }
      return midlertidigNode.hentData();
    } else {
      throw new UgyldigListeindeks(pos);
    }
  }

  public T fjern (int pos) {
    if (0 <= pos && pos < stoerrelse()){
      Node midlertidigNode = foersteNode;
      //finn frem til riktig indeks
      for (int i = 0; i < pos; i++){
        midlertidigNode = midlertidigNode.hentNeste();
      }
      //hent data som skal returneres
      T retur = midlertidigNode.hentData();
      //hvis det kun er ett element
      if (pos == 0 && stoerrelse() == 1){
        foersteNode = null;
        //hvis det er foerste element av flere elementer.
      } else if (pos == 0 && stoerrelse() > 1){
        foersteNode = midlertidigNode.hentNeste();
        //hvis det er siste element i listen
      } else if (pos == stoerrelse()-1){
        midlertidigNode.hentForrige().settNeste(null);
        //hvis det er et element mellom to andre elementer.
      } else {
        midlertidigNode.hentForrige().settNeste(midlertidigNode.hentNeste());
        midlertidigNode.hentNeste().settForrige(midlertidigNode.hentForrige());
      }
      antallNoder--;
      return retur;
    } else{
      throw new UgyldigListeindeks(pos);
    }
  }
}
