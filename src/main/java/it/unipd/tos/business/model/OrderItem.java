////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.model;

import java.util.Calendar;
import java.util.List;

public class OrderItem {

    List<MenuItem> order;
    User user;
    Calendar date;
    double amount;

    public OrderItem(List<MenuItem> order, User user, Calendar date, double amount) {
        this.order = order;
        this.user = user;
        this.date = date;
        this.amount = amount;
    }

    public List<MenuItem> getOrder() {
        return order;
    }

    public User getUser() {
        return user;
    }


    public Calendar getDate() {
        return date;
    }


    public double getAmount() {
        return amount;
    }

}