class HvitResept extends Resept{

  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public String farge(){
    return "Hvit";
  }

  public int prisAaBetale(){
    return legemiddel.hentPris();
  }

  //Overskriver toString()
  @Override
  public String toString(){
    return "Informasjon om hvit resept: \n"
    + "Resept-ID: "+ idnummer + "\n"
    + "Legemiddel: " + legemiddel.hentNavn() + "\n"
    + "Pasient-ID: " + pasientId + "\n"
    + "Reit: " + reit + "\n"
    + "Pris: " + legemiddel.hentPris() + "\n"
    + "Om utskrivende lege: " + "\n"
    + utskrivendeLege + "\n";
  }
}
