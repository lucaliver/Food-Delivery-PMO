/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.fooddelivery.model.City;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;

class TestOrderImpl {

	@Test
	void testOrderImpl() {
		OrderImpl o = new OrderImpl("8ft9", City.CAGLI, null, null);
		assertNotNull(o);
		assertEquals("8ft9", o.getIdOrder());
		assertEquals(City.CAGLI, o.getDestination());
		assertTrue(o.menusList().isEmpty());
	}

	@Test
	void testMenusList() {
		OrderImpl o = new OrderImpl("88ty", City.FOSSOMBRONE, null, null);
		assertTrue(o.menusList().isEmpty());		
		o.addMenu(new MenuImpl("M1", 0.40, 55));
		assertNotNull(o.menusList());
				
	}

	@Test
	void testAddMenu() {
		OrderImpl o = new OrderImpl("oo9i8", City.FOSSOMBRONE, null, null);
		assertTrue(o.menusList().isEmpty()); 
		MenuImpl m = new MenuImpl("Name", 0.20, 3);
		o.addMenu(m);
		assertTrue(!o.menusList().isEmpty());
		assertEquals(1, o.menusList().size());
	}

	@Test
	void testRemoveMenu() {
		OrderImpl o = new OrderImpl("abcde", City.CAGLI, null, null);
		MenuImpl m1 = new MenuImpl("M1", 42.10, 12);
		MenuImpl m2 = new MenuImpl("M2", 21.00, 7);
		o.addMenu(m1);
		o.addMenu(m2);
		o.removeMenu(m1);
		assertTrue(!o.menusList().isEmpty());
		assertEquals(1, o.menusList().size());
		o.removeMenu(m2);
		assertTrue(o.menusList().isEmpty());
	}
	
	@Test
	void testRemoveAll() {
		OrderImpl o = new OrderImpl("abcde", City.CAGLI, null, null);
		o.addMenu(new MenuImpl("a", 2.6, 5));
		o.addMenu(new MenuImpl("b", 2.7, 12));
		o.addMenu(new MenuImpl("c", 7.4, 7));
		o.addMenu(new MenuImpl("d", 5.0, 51));
		o.removeAllMenu();
		assertTrue(o.menusList().isEmpty());
	}

	@Test
	void testGetSize() {
		OrderImpl o = new OrderImpl("88ty", City.FOSSOMBRONE, null, null);
		o.addMenu(new MenuImpl("Name0", 11.00, 7)); 
		o.addMenu(new MenuImpl("Name1", 19.60, 14)); 
		o.addMenu(new MenuImpl("Name2", 1.99, 23)); 
		o.addMenu(new MenuImpl("Name3", 15.30, 8));
		int sizeTot = 7+14+23+8; 
		assertEquals(sizeTot, o.getSize());
	}

	@Test
	void testTotalPrice() {
		OrderImpl o = new OrderImpl("45lk", City.URBANIA, null, null);
		o.addMenu(new MenuImpl("Name0", 12.60, 7)); 
		o.addMenu(new MenuImpl("Name1", 17.23, 14)); 
		o.addMenu(new MenuImpl("Name2", 1.47, 23)); 
		o.addMenu(new MenuImpl("Name3", 133.61, 8));
		double priceTot = 12.60+17.23+1.47+133.61;
		assertEquals(priceTot, o.totalPrice());
	}

	@Test
	void testGetIdOrder() {
		OrderImpl o1 = new OrderImpl("ll31q", City.URBANIA, null, null);
		OrderImpl o2 = new OrderImpl("xxx56t", City.TAVULLIA, null, null);
		OrderImpl o3 = new OrderImpl("khj67q", City.URBANIA, null, null);
		assertEquals("ll31q", o1.getIdOrder());
		assertEquals("xxx56t", o2.getIdOrder());
		assertEquals("khj67q", o3.getIdOrder());
	}

	@Test
	void testGetDestination() {
		OrderImpl o1 = new OrderImpl("sei", City.FERMIGNANO, null, null);
		OrderImpl o2 = new OrderImpl("ste", City.URBANIA, null, null);
		assertEquals(City.FERMIGNANO, o1.getDestination());
		assertEquals(City.URBANIA, o2.getDestination());
	}

}
