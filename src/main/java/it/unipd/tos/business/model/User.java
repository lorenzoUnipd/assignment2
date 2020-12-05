////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.model;

public class User {

    int id;
    int eta;
    String name;
    String surname;

    public User(int id, String name, String surname, int eta) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.eta = eta;
    }

    public int getEta() {
        return this.eta;
    }
}