class Lege{
  protected String navn;

  public Lege(String navn){
    this.navn = navn;
  }

  public String hentNavn(){
    return navn;
  }
  // Overskriver toString()
  @Override
    public String toString(){
      return "Lege \n"
      + "Navn: " + navn + "\n";
    }
}
