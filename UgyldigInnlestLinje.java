/**
 * Denne feilen brukes naar en linje som et objekt skal opprettes fra
 * (f. eks. ved innlesing fra fil) er feilformatert.
 */
class UgyldigInnlestLinje extends RuntimeException{

    UgyldigInnlestLinje(String linje){
        System.out.println("Linjen er feilformatert: " + linje);
    }
}
