class Test{
  public static void main(String[] args){
  Prioritetskoe<String>  k = new Prioritetskoe<>();

  k.leggTil("B");

  System.out.println("Etter B er lagt til er listen" + k);
  System.out.println("Etter B er lagt til er stoerrelsen " + k.stoerrelse());

  k.leggTil("D");

  System.out.println("Etter D er lagt listen " + k);
  System.out.println("Etter D er lagt til er stoerrelsen " + k.stoerrelse());

  k.leggTil("C");

  System.out.println("Etter C er lagt til er listen " + k);
  System.out.println("Etter C er lagt til er stoerrelsen " + k.stoerrelse());

  k.leggTil("A");

  System.out.println("Etter A er lagt til er listen " + k);
  System.out.println("Etter A er lagt til er stoerrelsen " + k.stoerrelse());

  System.out.println(k);
  //System.out.println("Stoerrelsen er: " + k.stoerrelse();
  	//Fasit er "Anne", "Berit", "Chris"
  }
}
