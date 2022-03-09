import java.lang.Math;

class BlaaResept extends Resept{

  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
    //Setter 75% prisrabatt
    int nyPris = (int) Math.round(legemiddel.hentPris()*0.25);
    legemiddel.settNyPris(nyPris);

  }

  public String farge(){
    return "Blaa";
  }

  public int prisAaBetale(){
    return legemiddel.hentPris();
  }

  //Overskriver toString()
  @Override
  public String toString(){
    return "Informasjon om blaa resept: \n"
    + "Resept-ID: "+ idnummer + "\n"
    + "Legemiddel: " + legemiddel.hentNavn() + "\n"
    + "Pasient-ID: " + pasientId + "\n"
    + "Reit: " + reit + "\n"
    + "Pris: " + legemiddel.hentPris() + "\n"
    + "Om utskrivende lege: " + "\n"
    + utskrivendeLege + "\n";
  }
}
