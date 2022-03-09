abstract class Resept{
  protected int idnummer;
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientId;
  protected int reit;
  private static int teller = 1;

  // Konstruktoer. Teller for aa oppdatere Id
  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientId = pasientId;
    this.reit = reit;
    idnummer = teller;
    teller++;
  }

  public int hentId(){
    return idnummer;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return utskrivendeLege;
  }

  public int hentPasientId(){
    return pasientId;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    reit = reit - 1;
    if (reit < 1){
      return false;
    } else {
      return true;
    }
  }

  abstract public String farge();

  abstract public int prisAaBetale();

  // Overskriver toString()
  @Override
  public String toString(){
    return "Resept-ID: "+ idnummer
    + " Legemiddel: " + legemiddel.hentNavn()
    + " Lege: " + utskrivendeLege
    + " Pasient-ID: " + pasientId
    + " Reit: " + reit;
  }
}
