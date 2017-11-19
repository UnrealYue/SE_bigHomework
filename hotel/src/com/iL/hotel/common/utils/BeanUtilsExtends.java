package com.iL.hotel.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;

/**
 *@author duhw
 *@date:2015年9月27日 下午5:30:46
 *
 */
public class BeanUtilsExtends extends BeanUtils {
	static {
       ConvertUtils.register(new DateConvert(), java.util.Date.class);
       ConvertUtils.register(new DateConvert(), java.sql.Date.class);
   }
	public static void copyProperties(Object dest, Object orig) {
       try {
           BeanUtils.copyProperties(dest, orig);
       } catch (IllegalAccessException ex) {
           ex.printStackTrace();
       } catch (InvocationTargetException ex) {
           ex.printStackTrace();
       }
   }
}
