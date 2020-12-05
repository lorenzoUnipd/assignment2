////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.OrderItem;
import it.unipd.tos.business.model.User;


public class OrderItemTest {
    
    OrderItem order;
    User usr;
    Calendar date;
    double amount;
    
    @Before
    public void setUpUsr() {
        usr = new User(1, "Lorenzo", "Perinello", 20);
        date = new GregorianCalendar();
        date.set(Calendar.YEAR, 2020);
        date.set(Calendar.MONTH, 12);
        date.set(Calendar.DAY_OF_MONTH, 4);
        date.set(Calendar.HOUR_OF_DAY, 16);
        amount = 0;
        order = new OrderItem(null, usr, date, 0);
        
    }
    
    @Test
    public void testMenuItemGetOrder() throws TakeAwayBillException {
        Assert.assertEquals(null, order.getOrder());
    }

    @Test
    public void testMenuItemGetUser() throws TakeAwayBillException {
        Assert.assertEquals(usr, order.getUser());
    }
    
    @Test
    public void testMenuItemGetDate() throws TakeAwayBillException {
        Assert.assertEquals(date, order.getDate());
    }
    
    @Test
    public void testMenuItemGetAmount() throws TakeAwayBillException {
        Assert.assertEquals(0, order.getAmount(),0);
    }
}