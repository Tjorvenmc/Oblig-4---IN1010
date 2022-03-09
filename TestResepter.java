class TestResepter{
  public static void main(String[] args){

    //Oppretter legeobjekt
    Lege lege = new Lege("Heidi Legedottir");

    // Oppretter legemidler
    Narkotisk n1 = new Narkotisk("Fentanylum", 650, 200,950);
    Vanedannende v1 = new Vanedannende("Morfin", 480, 575 ,760);
    Vanlig vn1 = new Vanlig("Paracet", 300, 500);
    Vanlig vn2 = new Vanlig("Ibux", 250, 500);

    //Oppretter resepter
    BlaaResept blaa = new BlaaResept(n1, lege, 1, 5);
    HvitResept hvit = new HvitResept(v1, lege, 2, 5);
    MilResept milRep = new MilResept(vn1, lege, 3);
    PResept pRep = new PResept(vn2, lege, 4, 4);

    //Tester for blaa resept
    System.out.println("Tester for blaa resept: \n");
    if (sjekkId(blaa, 1)){
      System.out.println("ID: OK.");
    } else {
      System.out.println("ID: FEIL!");
    }

    if (sjekkLegemiddel(blaa, n1)){
      System.out.println("Legemiddel: OK.");
    } else {
      System.out.println("Legemiddel: FEIL!");
    }

    if (sjekkLege(blaa, lege)){
      System.out.println("Lege: OK.");
    } else {
      System.out.println("Lege: FEIL!");
    }

    if (sjekkPasientId(blaa, 1)){
      System.out.println("PasientID: OK.");
    } else {
      System.out.println("PasientID: FEIL!");
    }

    if (sjekkReit(blaa, 5)){
      System.out.println("Reit: OK.");
    } else {
      System.out.println("Reit: FEIL!");
    }

    if (testBruk(blaa, 4)){
      System.out.println("Bruk av resept: OK.");
    } else {
      System.out.println("Bruk av resept: FEIL!");
    }

    if (sjekkFarge(blaa, "Blaa")){
      System.out.println("Farge: OK.");
    } else {
      System.out.println("Farge: FEIL!");
    }

    if (sjekkPris(blaa, 163)){
      System.out.println("Pris: OK.\n");
    } else {
      System.out.println("Pris: FEIL!\n");
    }

    //Test for hvit-resept
    System.out.println("Tester for hvit resept: \n");
    if (sjekkFarge(hvit, "Hvit")){
      System.out.println("Farge: OK.");
    } else {
      System.out.println("Farge: FEIL!");
    }

    if (sjekkId(hvit, 2)){
      System.out.println("ID: OK.");
    } else{
      System.out.println("ID: FEIL!");
    }

    if (sjekkPris(hvit, 480)){
      System.out.println("Pris: OK.\n");
    } else {
      System.out.println("Pris: FEIL!\n");
    }

    //Tester for Militaer-resept
    System.out.println("Tester for militaer-resept: \n");
    if (sjekkReit(milRep, 3)){
      System.out.println("Fast-reit: OK.");
    } else {
      System.out.println("Fast-reit: FEIL!");
    }

    if (sjekkPris(milRep, 0)){
      System.out.println("Prisrabatt: OK.\n");
    } else {
      System.out.println("Prisrabatt: FEIL!\n");
    }

    //Test for P-resept
    System.out.println("Test for P-Resept: \n");
    if (sjekkPris(pRep, 142)){
      System.out.println("Prisrabatt: OK.");
    } else {
      System.out.println("Prisrabatt: FEIL!");
    }
  }

  public static boolean sjekkId(Resept resept, int forventetId){
    return (resept.hentId() == forventetId);
  }

  public static boolean sjekkLegemiddel (Resept resept, Legemiddel forventetLegemiddel){
    return (resept.hentLegemiddel() == forventetLegemiddel);
  }

  public static boolean sjekkLege(Resept resept, Lege forventetLege){
    return (resept.hentLege() == forventetLege);
  }

  public static boolean sjekkPasientId(Resept resept, int forventetPasientId){
    return (resept.hentPasientId() == forventetPasientId);
  }

  public static boolean sjekkReit(Resept resept, int forventetReit){
    return (resept.hentReit() == forventetReit);
  }

  public static boolean testBruk(Resept resept, int forventetReit){
    resept.bruk();
    return (resept.hentReit() == forventetReit);
  }

  public static boolean sjekkFarge(Resept resept, String forventetfarge){
    return (resept.farge() == forventetfarge);
  }

  public static boolean sjekkPris(Resept resept, int forventetPris){
    return (resept.prisAaBetale() == forventetPris);
  }

  /*

  abstract public String farge();

    abstract public int prisAaBetale();
  }*/

}
