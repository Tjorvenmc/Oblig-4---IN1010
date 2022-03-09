public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T>{

  public Prioritetskoe(){
    super();
  }

  @Override
  public void leggTil (T x){
    Node nyNode = new Node(x);
    //hvis liste er tom;
    if (antallNoder < 1){
      foersteNode = nyNode;
      antallNoder ++;
      //hvis det er én node
    } else if (antallNoder == 1){
      Node midlertidigNode = foersteNode;
      //og den skal settes foran den ene noden.
      if (nyNode.hentData().compareTo(midlertidigNode.hentData()) < 1){
        midlertidigNode.settForrige(nyNode);
        nyNode.settNeste(midlertidigNode);
        foersteNode = nyNode;
        antallNoder ++;
        //eller den skal settes bak den ene noden.
      } else {
        midlertidigNode.settNeste(nyNode);
        nyNode.settForrige(midlertidigNode);
        antallNoder ++;
      }
      //hvis det er mer enn én node.
    } else {
      Node midlertidigNode = foersteNode;
      boolean sattInn = false;
      int teller = 0;
      while (sattInn == false){
        int sammenlign = nyNode.hentData().compareTo(midlertidigNode.hentData());
        //hvis den skal settes foran den foerste noden.
        if (sammenlign < 1 && teller == 0){
          nyNode.settNeste(midlertidigNode);
          midlertidigNode.settForrige(nyNode);
          foersteNode = nyNode;
          sattInn = true;
          antallNoder ++;
          //hvis den skal settes mellom to noder.
        } else if (sammenlign < 1){
          nyNode.settForrige(midlertidigNode.hentForrige());
          nyNode.settNeste(midlertidigNode);
          midlertidigNode.hentForrige().settNeste(nyNode);
          midlertidigNode.settForrige(nyNode);
          sattInn = true;
          antallNoder ++;
        } else if (sammenlign > 0 && midlertidigNode.hentNeste() == null){
          nyNode.settForrige(midlertidigNode);
          midlertidigNode.settNeste(nyNode);
          sattInn = true;
          antallNoder ++;
          //Hvis den ikke skal settes inn foran den gjeldene noden starter
          //loekken paa nytt for neste node.
        } else {
          midlertidigNode = midlertidigNode.hentNeste();
          teller ++;
        }
      }
    }
  }

}
