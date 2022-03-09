class MilResept extends HvitResept{

  public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
    //Tar inn tallet 3 (for 3 reit), i stedet for reit som parameter i konstruktoer og argument i super.
    super(legemiddel, utskrivendeLege, pasientId, 3);
    //Setter prisen til 0 for militaerresepter
    legemiddel.settNyPris(0);
  }

  //Overskriver toString() metoden
  @Override
  public String toString(){
    return "Informasjon om militaer-resept: \n"
    + "Resept-ID: "+ idnummer + "\n"
    + "Legemiddel: " + legemiddel.hentNavn() + "\n"
    + "Pasient-ID: " + pasientId + "\n"
    + "Reit: " + reit + "\n"
    + "Pris: " + legemiddel.hentPris() + "\n"
    + "Om utskrivende lege: " + "\n"
    + utskrivendeLege + "\n";
  }
}
