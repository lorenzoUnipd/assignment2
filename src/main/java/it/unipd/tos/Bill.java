////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import it.unipd.tos.business.model.OrderItem;
import it.unipd.tos.business.model.User;

public class Bill implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double tot = 0;

        if (itemsOrdered.isEmpty()) {
            throw new TakeAwayBillException("errore: lista vuota");
        }

        if (itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("errore: lista inconsistente");

        }

        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("errore: ordine non può contenere più di 30 elemenit");
        }

        for (int i = 0; i < itemsOrdered.size(); i++) {
            tot = tot + itemsOrdered.get(i).getPrice();
        }

        // se #gelati >5 => sconto 50% gelato meno caro
        if ((itemsOrdered.stream().filter(ordine -> ordine.getItemType() == ItemType.GELATO).count() > 5)) {
            tot = tot - 0.5*(itemsOrdered.stream().mapToDouble(MenuItem::getPrice).min().getAsDouble());
        }

        // se totale>50 => sconto 10% sul totale
        if (tot > 50) {
            tot = tot - (0.1 * tot);
        }

        // se totale<10 => aggiunta commissione 0.5
        if (tot < 10) {
            tot = tot + 0.5;
        }

        return tot;
    }

    @Override
    public List<OrderItem> gift(List<OrderItem> orders, boolean gifting) throws TakeAwayBillException {

        if (orders.size() < 10) {
            throw new TakeAwayBillException("errore: numero ordini < 10");
        }

        if (orders.stream().filter(ordine -> ordine.getUser().getEta() >= 18).distinct().count() < 10) {
            throw new TakeAwayBillException("errore: numero utenti maggiorenni distiniti < 10");
        }

        if ((orders.stream().filter(ordine -> ordine.getDate().get(Calendar.HOUR_OF_DAY) >= 18
                && ordine.getDate().get(Calendar.HOUR_OF_DAY) <= 19).count() < 10)) {
            throw new TakeAwayBillException("errore: numero ordini che soddisfano i requisiti temporali < 10");
        }

        if (gifting) {
            Collections.shuffle(orders);
            int i = 0;
            while (i < 10) {
                orders.remove(0);
                i++;
            }
        }
        return orders;
    }
}