class Stabel<T> extends Lenkeliste<T>{

  Stabel(){
    super();
  }

  @Override
  public void leggTil (T element){
    Node nyNode = new Node(element);
    // Hvis det ikke er noen Noder, legg til den nye noden som foerste
    if (antallNoder == 0){
      foersteNode = nyNode;
      // Hvis det er noder, legg til paa toppen av stabelen.
    } else {
      Node midlertidigNode = foersteNode;
      foersteNode = nyNode;
      midlertidigNode.settForrige(foersteNode);
      foersteNode.settNeste(midlertidigNode);
    }
    antallNoder ++;
  }
}
