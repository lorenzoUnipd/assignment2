////////////////////////////////////////////////////////////////////
// [LORENZO] [PERINELLO] [1193553]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.User;

public class UserTest {

    User usr;
    int eta;
    
    @Before
    public void setupUserTest() {
        usr = new User(1,"Lorenzo","Perinello",20);
        eta = 0;
    }
    
    @Test
    public void testUserGetEta() throws TakeAwayBillException {

        eta = usr.getEta();
        Assert.assertEquals(20, eta, 0);
    }
}
