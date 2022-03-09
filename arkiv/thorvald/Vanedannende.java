class Vanedannende extends Legemiddel{
  private int styrke;

  public Vanedannende(String navn, int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public int hentVanedannendeStyrke(){
    return styrke;
  }

  @Override
  public String toString(){
    return "Info om Vanedannende legemiddel: \n"
    + "Navn: "+ navn + "\n"
    + "ID: " + idnummer + "\n"
    + "Pris: " + pris + "\n"
    + "Virkestoff: " + virkestoff + "\n"
    + "Vanedannende styrke: " + styrke + "\n";
  }
}
