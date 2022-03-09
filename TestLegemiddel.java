class TestLegemiddel{
  public static void main(String[] args){
    Narkotisk n1 = new Narkotisk("Fentanylum", 650, 200,950);
    Vanedannende v1 = new Vanedannende("Morfin", 480, 575 ,760);
    Vanlig vn1 = new Vanlig("Paracet", 500, 200);

// Sjekker Narkotisk-objekt.
    System.out.println("Tester for Narkotisk-objekt:");
    if (sjekkId(n1, 1)){
      System.out.println("ID: OK");
    } else{
      System.out.println("ID: FEIL!");
    }

    if (sjekkNavn(n1,"Fentanylum")){
      System.out.println("Navn: OK");
    } else {
      System.out.println("Navn: FEIL!");
    }

    if (sjekkPris(n1,650)){
      System.out.println("Pris: OK");
    } else {
      System.out.println("Pris: FEIL!");
    }

    if (sjekkVirkestoff(n1,200)){
      System.out.println("Virkestoff: OK");
    } else {
      System.out.println("Virkestoff: FEIL!");
    }

    if (sjekkNyPris(n1,700)){
      System.out.println("Endring av pris: OK");
    } else {
      System.out.println("Endring av pris: FEIL!");
    }

    if (sjekkNarkotiskStyrke(n1,950)){
      System.out.println("Narkotisk Styrke: OK");
    } else {
      System.out.println("Narkotisk styrke: FEIL!");
    }

// Sjekker Vanedannende-objekt
    System.out.println("\nTester for Vanedannende-objekt:");
    if (sjekkId(v1,2)){
      System.out.println("ID: OK");
    } else{
      System.out.println("ID: FEIL!");
    }

    if (sjekkNavn(v1,"Morfin")){
      System.out.println("Navn: OK");
    } else{
      System.out.println("Navn: FEIL!");
    }

    if (sjekkPris(v1,480)){
      System.out.println("Pris: OK");
    } else{
      System.out.println("Pris: FEIL!");
    }

    if (sjekkVirkestoff(v1,575)){
      System.out.println("Virkestoff: OK");
    } else{
      System.out.println("Virkestoff: FEIL!");
    }

    if (sjekkNyPris(v1,500)){
      System.out.println("Endring av pris: OK");
    } else{
      System.out.println("Endring av pris: FEIL!");
    }

    if (sjekkVanedannendeStyrke(v1,760)){
      System.out.println("Vanedannende styrke OK");
    } else{
      System.out.println("Vanedannende styrke: FEIL!");
    }

// Sjekker Vanlig-objekt.
    System.out.println("\nTester for Vanlig-objekt:");
    if (sjekkId(vn1,3)){
      System.out.println("ID: OK");
    } else{
      System.out.println("ID: FEIL!");
    }

    if (sjekkNavn(vn1,"Paracet")){
      System.out.println("Navn: OK");
    } else{
      System.out.println("Navn: FEIL!");
    }

    if (sjekkPris(vn1,500)){
      System.out.println("Pris: OK");
    } else{
      System.out.println("Pris: FEIL!");
    }

    if (sjekkVirkestoff(vn1,200)){
      System.out.println("Virkestoff: OK");
    } else{
      System.out.println("Virkestoff: FEIL!");
    }

    if (sjekkNyPris(vn1,175)){
      System.out.println("Endring av pris: OK \n");
    } else{
      System.out.println("Endring av pris: OK \n");
    }

    System.out.println(n1);
    System.out.println(v1);
    System.out.println(vn1);
  }

//Metoder for aa sjekke objekter.
  public static boolean sjekkId(Legemiddel legemiddel, int forventetId){
    return (legemiddel.hentId() == forventetId);
  }

  public static boolean sjekkNavn(Legemiddel legemiddel, String forventetNavn){
    return legemiddel.hentNavn().equals(forventetNavn);
  }

  public static boolean sjekkPris(Legemiddel legemiddel, int forventetPris){
    return (legemiddel.hentPris() == forventetPris);
  }

  public static boolean sjekkVirkestoff(Legemiddel legemiddel, double forventetVirkestoff){
    return (legemiddel.hentVirkestoff() == forventetVirkestoff);
  }

  public static boolean sjekkNyPris(Legemiddel legemiddel, int forventetNyPris){
    legemiddel.settNyPris(forventetNyPris);
    return (legemiddel.hentPris() == forventetNyPris);
  }

  public static boolean sjekkNarkotiskStyrke(Narkotisk legemiddel, int forventetStyrke){
      return legemiddel.hentNarkotiskStyrke() == forventetStyrke;
  }

  public static boolean sjekkVanedannendeStyrke(Vanedannende legemiddel, int forventetStyrke){
    return legemiddel.hentVanedannendeStyrke() == forventetStyrke;
  }
}
