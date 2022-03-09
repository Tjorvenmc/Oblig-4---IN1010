class PResept extends HvitResept{
  private static int rabatt = 108;

  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
    //Trekker fra rabatt paa legemiddel.
    legemiddel.settNyPris(legemiddel.hentPris()-rabatt);
    if (legemiddel.hentPris() < 0){
      legemiddel.settNyPris(0);
    }
  }

  //Overskriver toString()
  @Override
  public String toString(){
    return "Informasjon om P-resept: \n"
    + "Resept-ID: "+ idnummer + "\n"
    + "Legemiddel: " + legemiddel.hentNavn() + "\n"
    + "Pasient-ID: " + pasientId + "\n"
    + "Reit: " + reit + "\n"
    + "Pris: " + legemiddel.hentPris() + "\n"
    + "Om utskrivende lege: " + "\n"
    + utskrivendeLege + "\n";
  }
}
