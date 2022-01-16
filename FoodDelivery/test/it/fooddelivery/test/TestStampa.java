package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.RiderImpl;

class TestStampa {
	
	List<City> cities = Arrays.asList(City.values());	
	Rider r = new RiderImpl("Bill", cities);
	Rider r2 = new RiderImpl("Toby", cities);
	Menu m1 = new MenuImpl("Menu1", 50.00, 20);
	Menu m2 = new MenuImpl("Menu2", 10.00, 40);
	Order o = new OrderImpl(01, City.CAGLI, "Strada", null);
	Map<String, Rider> riders = new HashMap<>();
	
	
	@Test
	void testStampa() {		
		riders.put("Bill", r);
		riders.put("Toby", r2);
		Manager m = new Manager(riders, null);
		o.addMenu(m1);
		o.addMenu(m2);
		System.out.println(riders.size()+" "+riders.keySet().toString());
		System.out.println(m.assignOrder(o));
		System.out.println(m.getRiderWithLastOrder().get().getName());
		System.out.println(r2.showBagInfo());
	
		
	}


}
