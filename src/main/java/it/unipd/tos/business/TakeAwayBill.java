package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.MenuItem;
import it.unipd.tos.business.model.OrderItem;
import it.unipd.tos.business.model.User;


public interface TakeAwayBill {
	  double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException;
	  boolean gift(List<OrderItem> orders) throws TakeAwayBillException;

}