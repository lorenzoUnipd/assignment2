////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import it.unipd.tos.business.model.OrderItem;
import it.unipd.tos.business.model.User;

public class BillTest {

    Bill bill = new Bill();
    List<MenuItem> ordine;
    List<OrderItem> listaOrdini;
    User usr = new User(1, "Lorenzo", "Perinello", 20);
    double amount;
    int nelementi;

    private final MenuItem banana_split = new MenuItem(ItemType.GELATO, "Banana split", 5.0);
    private final MenuItem coppa_nafta = new MenuItem(ItemType.GELATO, "Coppa nafta", 10.0);
    private final MenuItem biancaneve = new MenuItem(ItemType.BUDINO, "Biancaneve", 6.0);
    private final MenuItem pinguino = new MenuItem(ItemType.BUDINO, "Pinguino", 5.0);
    private final MenuItem coca = new MenuItem(ItemType.BEVANDA, "Coca", 3.5);
    private final MenuItem acqua = new MenuItem(ItemType.BEVANDA, "Acqua", 2.0);
    private final MenuItem coppa_oro = new MenuItem(ItemType.GELATO, "Coppa oro", 100.0);

    @Before
    public void clear() {
        ordine = new ArrayList<>();
        listaOrdini = new ArrayList<>();
        amount = 0.0;
        nelementi = 0;

    }

    @Test(expected = TakeAwayBillException.class)
    public void testCalcoloPrezzoTotaleConOrdineVuoto() throws TakeAwayBillException {
        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(0.0, amount, 0.0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testConListaContenenteValoriNulli() throws TakeAwayBillException {
        ordine.add(biancaneve);
        ordine.add(coca);
        ordine.add(null);

        amount = bill.getOrderPrice(ordine, usr);
    }

    @Test
    public void testCalcoloPrezzoTotale() throws TakeAwayBillException {
        ordine.add(banana_split);
        ordine.add(biancaneve);
        ordine.add(coca);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(14.5, amount, 14.5);
    }

    @Test
    public void testScontoPiuCinqueGelati() throws TakeAwayBillException {
        ordine.add(banana_split);
        ordine.add(banana_split);
        ordine.add(banana_split);
        ordine.add(banana_split);
        ordine.add(banana_split);
        ordine.add(banana_split);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(27.5, amount, 0.0);
    }

    @Test
    public void testNoScontoConPiuCinqueElementiNonGelati() throws TakeAwayBillException {
        ordine.add(pinguino);
        ordine.add(acqua);
        ordine.add(acqua);
        ordine.add(acqua);
        ordine.add(acqua);
        ordine.add(acqua);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(15.0, amount, 0.0);
    }

    @Test
    public void testScontoSuTotaleSopraCinquanta() throws TakeAwayBillException {
        ordine.add(coppa_oro);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(90.0, amount, 0.0);
    }

    @Test
    public void testTuttiGliScontiApplicabiliAttivati() throws TakeAwayBillException {
        ordine.add(coppa_nafta);
        ordine.add(coppa_nafta);
        ordine.add(coppa_nafta);
        ordine.add(coppa_nafta);
        ordine.add(coppa_nafta);
        ordine.add(coppa_nafta);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(49.5, amount, 0.0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testOrdineConPiuDiTrenaElementi() throws TakeAwayBillException {
        for (int i = 0; i < 33; i++) {
            ordine.add(pinguino);
        }

        amount = bill.getOrderPrice(ordine, usr);
    }

    @Test
    public void testCommissioneOrdineTotaleSottoDieci() throws TakeAwayBillException {
        ordine.add(acqua);

        amount = bill.getOrderPrice(ordine, usr);
        Assert.assertEquals(2.5, amount, 0.0);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testGiftNumeroOrdiniMinoriDieci() throws TakeAwayBillException {
        Random n = new Random();
        boolean gifting = n.nextBoolean();
        listaOrdini = bill.gift(listaOrdini, gifting);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testGiftNumeroMaggiorenniMinoriDieci() throws TakeAwayBillException {
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 100));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 550));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 777));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 100));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 696));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 100));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 420));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 100));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 160));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 190));
        listaOrdini.add(
                new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Perinello", 20), Calendar.getInstance(), 100));
        Random n = new Random();
        boolean gifting = n.nextBoolean();
        listaOrdini = bill.gift(listaOrdini, gifting);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testGiftNumeroOrdiniSenzaRequisitiTemporaliMinoriDieci() throws TakeAwayBillException {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, 2020);
        c.set(Calendar.MONTH, 12);
        c.set(Calendar.DAY_OF_MONTH, 4);
        c.set(Calendar.HOUR_OF_DAY, 16);

        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Rosso", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(2, "Lorenzo", "Arancione", 20), c, 550));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(3, "Lorenzo", "Giallo", 20), c, 777));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(4, "Lorenzo", "Verde", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(5, "Lorenzo", "Blu", 20), c, 696));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(6, "Lorenzo", "Bianco", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(7, "Lorenzo", "Rosa", 20), c, 420));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(8, "Lorenzo", "Azzurro", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(9, "Lorenzo", "Marrone", 20), c, 160));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(10, "Lorenzo", "Nero", 20), c, 190));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(11, "Lorenzo", "Grigio", 20), c, 100));
        Random n = new Random();
        boolean gifting = n.nextBoolean();
        listaOrdini = bill.gift(listaOrdini, gifting);
    }

    @Test
    public void testGiftDieciOrdini() throws TakeAwayBillException {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, 2020);
        c.set(Calendar.MONTH, 12);
        c.set(Calendar.DAY_OF_MONTH, 4);
        c.set(Calendar.HOUR_OF_DAY, 18);

        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(1, "Lorenzo", "Rosso", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(2, "Lorenzo", "Arancione", 20), c, 550));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(3, "Lorenzo", "Giallo", 20), c, 777));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(4, "Lorenzo", "Verde", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(5, "Lorenzo", "Blu", 20), c, 696));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(6, "Lorenzo", "Bianco", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(7, "Lorenzo", "Rosa", 20), c, 420));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(8, "Lorenzo", "Azzurro", 20), c, 100));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(9, "Lorenzo", "Marrone", 20), c, 160));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(10, "Lorenzo", "Nero", 20), c, 190));
        listaOrdini.add(new OrderItem(new ArrayList<>(), new User(11, "Lorenzo", "Grigio", 20), c, 100));

        Random n = new Random();
        boolean gifting = n.nextBoolean();
        int nelementi_before = listaOrdini.size();
        listaOrdini = bill.gift(listaOrdini, gifting);
        nelementi = listaOrdini.size();

        int n_elementi_expected = gifting ? nelementi_before - 10 : nelementi_before;

        Assert.assertEquals(n_elementi_expected, nelementi, 0);
    }

}