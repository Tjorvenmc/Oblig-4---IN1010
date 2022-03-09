public abstract class Lenkeliste<T> implements Liste<T> {
  public Node foersteNode = null;
  public int antallNoder = 0;

  @Override
  public int stoerrelse(){
    return antallNoder;
  }

  @Override
  public void leggTil (T element){
    Node nyNode = new Node(element);
    // Hvis det ikke er noen Noder, legg til den nye noden som foerste
    if (antallNoder == 0){
      foersteNode = nyNode;
      // Hvis det er noder, grav nedover i listen og legg til (og koble opp)
      // den nye noden paa slutten av listen
    } else {
      Node midlertidigNode = foersteNode;
      while (midlertidigNode.hentNeste() != null){
        midlertidigNode = midlertidigNode.hentNeste();
      }
      midlertidigNode.settNeste(nyNode);
      nyNode.settForrige(midlertidigNode);
    }
    antallNoder ++;
  }

  @Override
  public T hent(){
    return foersteNode.hentData();

  }

  @Override
  public T fjern(){
    //Hvis
    if (antallNoder == 0){
      throw new UgyldigListeindeks(-1);
    } else {
      T returData = foersteNode.hentData();
      if (antallNoder == 1){
        foersteNode = null;
      } else {
        foersteNode = foersteNode.hentNeste();
      }
      antallNoder --;
      return returData;
    }
  }

  public class Node{
    Node neste;
    Node forrige;
    T data;

    Node(T data){
      this.data = data;
    }

    void settNeste(Node neste){
      this.neste = neste;
    }

    void settForrige(Node forrige){
      this.forrige = forrige;
    }

    Node hentNeste(){
      return neste;
    }

    Node hentForrige(){
      return forrige;
    }

    T hentData(){
      return data;
    }

    void settData(T data){
      this.data = data;
    }

    @Override
    public String toString(){
      return "Data: " + data + " ";
    }
  }

  @Override
  public String toString(){
    String returString = "";
    //Hvis det ikke er noen noder, returner den tomme stringen.
    if (antallNoder == 0){
      return returString;
    } else {
      Node node = foersteNode;
      //Gaar gjennom nodene i listen og legger til innhold til returstringen.
      while (node.hentNeste() != null){
        returString = returString + " " + node.hentData();
        node = node.hentNeste();
      }
      //for aa legge til innholdet i den foerste/siste noden som ikke fanges opp av
      //while-loekken.
      returString = returString + " " + node.hentData();
    }
    return returString;
  }

}
