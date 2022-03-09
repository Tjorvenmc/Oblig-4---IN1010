public class Hovedprogram {
  public static void main(String[] args){

    Lege lege = new Lege("Heidi Legedottir");
    Spesialist spesialist = new Spesialist("Ylva Oeyelege", "Ylvas kontrollID");

    System.out.println(lege);
    System.out.println(spesialist);

    Narkotisk n1 = new Narkotisk("Fentanylum", 650, 200,950);
    Vanedannende v1 = new Vanedannende("Morfin", 480, 575 ,760);
    Vanlig vn1 = new Vanlig("Paracet", 300, 500);
    Vanlig vn2 = new Vanlig("Ibux", 250, 500);

    System.out.println(vn1);
    System.out.println(vn2);
    System.out.println(v1);
    System.out.println(n1);

    BlaaResept blaa = new BlaaResept(n1, spesialist, 1, 5);
    HvitResept hvit = new HvitResept(v1, lege, 2, 5);
    MilResept milRep = new MilResept(vn1, lege, 3);
    PResept pRep = new PResept(vn2, lege, 4, 4);

    System.out.println(blaa);
    System.out.println(hvit);
    System.out.println(milRep);
    System.out.println(pRep);


  }
}
