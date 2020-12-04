package it.unipd.tos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import it.unipd.tos.business.model.OrderItem;
import it.unipd.tos.business.model.User;

public class App implements TakeAwayBill {
	
	@Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException{
		
		if(itemsOrdered == null){
			throw new TakeAwayBillException("errore: ordine vuoto");
		}
		
		if(itemsOrdered.size() > 30) {
			throw new TakeAwayBillException("errore: ordine non può contenere più di 30 elemenit");
		}
		
		//calcolo totale
		double tot=0;
		for(int i=0; i<itemsOrdered.size(); i++) {
			tot = tot + itemsOrdered.get(i).getPrice();
		}
		
		int nGelati=0;
		int indexMenoCaro=0;
		double minPrice=0;
		//se #gelati >5	=> sconto 50% gelato meno caro
		for(int i=0; i<itemsOrdered.size(); i++) {
			if(itemsOrdered.get(i).getItemType() == ItemType.GELATO) {
				nGelati++;
				if(itemsOrdered.get(i).getPrice()<minPrice){
					indexMenoCaro=i;
					minPrice=itemsOrdered.get(i).getPrice();
				}
			}
		}
		if(nGelati > 5) {
			tot = tot - (0.5 * itemsOrdered.get(indexMenoCaro).getPrice());
		}
		
		//se totale>50 	=> sconto 10% sul totale
		if(tot > 50) {
			tot = tot - (0.1 * tot);
		}
		
		//se totale<10	=> aggiunta commissione 0.5
		if(tot < 10) {
			tot = tot + 0.5;
		}		
		
		return tot;
	}
	
	@Override
	public boolean gift(List<OrderItem> orders){
		
		//calcolo 10 utenti minorenni differenti con ordine dalle 18:00 alle 19:00
		int n=0;
		List<User> utenti = new ArrayList<User>();
		List<Integer> selezionati = new ArrayList<Integer>();
		for(int i=0; i<orders.size(); i++){
			OrderItem ordine = orders.get(i);
			
			if(ordine.getUser().getEta()>=18 
					&& !utenti.contains(ordine.getUser())
					&& (ordine.getDate().HOUR_OF_DAY >= 18 && ordine.getDate().HOUR_OF_DAY <=19)){
				
				utenti.add(ordine.getUser());
				selezionati.add(i);
				n++;
			}
		}
		
		//seleziono 10 utenti random tra quelli con i prerequisiti e azzero il totale del loro order
		if(n>=10){
			Random rand = new Random();
			for(int i=0; i<10; i++) {
				int r = rand.nextInt(selezionati.size()+1);
				orders.get(selezionati.get(r).intValue()).setAmount(0);
				selezionati.remove(r);
			}
			return true;
		}else{
			return false;
		}
	}
}