/**
 * Create By Du
 * Date : 2017年4月20日
 */
package com.iL.hotel.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
* @ClassName: Grouper
* @ClassNameExplain: 类名说明
* @Description: TODO(这里描述这个类的作用)
* @author 杜海文
* @date 2017年4月20日 下午2:22:56
*/

public class Grouper {
	
	 private List<String> groupList = new ArrayList<String>();
	 
	 private String groupByString = " group by ";
	 
	 public void addGroup(String group){
		 this.groupList.add(group);
	 }
	 
	 public String getGroupHql(){
		 for (String ite : groupList){
			 groupByString += ite + " ";
		 }
		 return groupByString;
	 }

}
