class UgyldigInnlestLinje extends RuntimeException{
    UgyldigInnlestLinje(String linje){
        System.out.println("Linjen er feilformatert: " + linje);
    }
}
