////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;


public class MenuItemTest {
    
    private final MenuItem banana_split = new MenuItem(ItemType.GELATO, "Banana split", 5.0);
    double amount;
    ItemType type;
    
    @Before
    public void setupMenuItemTest() {
        amount=0;
        type=null;
    }
    
    @Test
    public void testMenuItemGetPrice() throws TakeAwayBillException {
        amount = banana_split.getPrice();
        Assert.assertEquals(5.0, amount, 0.0);
    }

    @Test
    public void testMenuItemGetItemType() throws TakeAwayBillException {
        type = banana_split.getItemType();
        Assert.assertEquals("GELATO", type.name());
    }
}