////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

//TakeAwayBill eredita dalla classe Base Exception
public class TakeAwayBillException extends Exception {
    // costruisce la variabile message di Exception con il valore passato per
    // parametro
    public TakeAwayBillException(String message) {
        super(message);
    }
}