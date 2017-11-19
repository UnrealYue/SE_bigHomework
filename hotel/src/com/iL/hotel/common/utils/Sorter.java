package com.iL.hotel.common.utils;

import org.hibernate.criterion.Order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Sorter {
	public static final String DESC = "desc";

	public static final String ASC = "asc";

	private Map sortmap;

	public Sorter() {
		this.sortmap = new HashMap<String,Object>();
	}

	public void setSort(String key,Object value){
		this.sortmap.put(key,value);
	}

	/**
	 * @return the sortmap
	 */
	public Map getSortmap() {
		return sortmap;
	}

	/**
	 * @param sortmap
	 *            the sortmap to set
	 */
	public void setSortmap(Map sortmap) {
		this.sortmap = sortmap;
	}

	public Order[] getOrder() {
		if (DetectionUtils.isEmpty(sortmap))
			return null;
		Order[] orders = new Order[sortmap.size()];
		Iterator iter = sortmap.keySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			String sortName = (String) iter.next();
			String sortType = (String) sortmap.get(sortName);
			if (DESC.equalsIgnoreCase(sortType))
				orders[i] = Order.desc(sortName);
			else
				orders[i] = Order.asc(sortName);
			i++;
		}

		return orders;
	}

	public String getOrderHqlString() {
        String returnHql = " order by ";
        String split = ",";
		if (DetectionUtils.isEmpty(sortmap))
			return null;
		Iterator iter = sortmap.keySet().iterator();
		while (iter.hasNext()) {
			String sortName = (String) iter.next();
			String sortType = (String) sortmap.get(sortName);
			if (DESC.equalsIgnoreCase(sortType))
                returnHql += sortName + " " + this.DESC;
			else
                returnHql += sortName + " " + this.ASC;
            if (iter.hasNext()) returnHql += split;
		}

		return returnHql;
	}

}
