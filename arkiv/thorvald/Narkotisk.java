class Narkotisk extends Legemiddel{
  private int styrke;

  public Narkotisk(String navn, int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public int hentNarkotiskStyrke(){
    return styrke;
  }

  //Overskirver toString()
  @Override
  public String toString(){
    return "Info om narkotisk legemiddel: \n"
    + "Navn: "+ navn + "\n"
    + "ID: " + idnummer + "\n"
    + "Pris: " + pris + "\n"
    + "Virkestoff: " + virkestoff + "\n"
    + "Narkotisk styrke: " + styrke + "\n";
  }
}
