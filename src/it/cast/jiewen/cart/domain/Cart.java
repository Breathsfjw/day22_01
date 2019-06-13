package it.cast.jiewen.cart.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, Cartitem> map = new LinkedHashMap<String, Cartitem>();

	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		for (Cartitem cartitem : map.values()) {
			BigDecimal subtotal = new BigDecimal(cartitem.getSubtotal() + "");
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}

	public void add(Cartitem cartitem) {
		if (map.containsKey(cartitem.getBook().getBid())) {
			Cartitem _cartitem = map.get(cartitem.getBook().getBid());
			_cartitem.setCount(_cartitem.getCount() + cartitem.getCount());
			map.put(_cartitem.getBook().getBid(), _cartitem);
		} else {
			map.put(cartitem.getBook().getBid(), cartitem);
		}
	}

	public void clearAll() {
		map.clear();
	}

	public void clearBybid(String bid) {
		map.remove(bid);
	}
}
