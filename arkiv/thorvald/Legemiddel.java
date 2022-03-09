abstract class Legemiddel{
  protected int idnummer;
  protected String navn;
  protected int pris;
  protected double virkestoff;
  private static int teller = 1;

// Konstruktoer
  public Legemiddel(String navn, int pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    idnummer = teller;
    teller ++;
  }

  public int hentId(){
    return idnummer;
  }

  public String hentNavn(){
    return navn;
  }

  public int hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }

  public void settNyPris(int pris){
    this.pris = pris;
  }

  // Overskriver toString()
  @Override
  public String toString(){
    return "Info om legemiddel: \n"
    + "Navn: "+ navn + "\n"
    + "ID: " + idnummer + "\n"
    + "Pris: " + pris + "\n"
    + "Virkestoff: " + virkestoff + "\n";
  }
}
