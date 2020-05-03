package org.com.projectname.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String getName() {
		String gereratedString = RandomStringUtils.randomAlphabetic(2);
		return("Siva"+gereratedString);
	}
	
	public static String getType() {
		String gereratedString = RandomStringUtils.randomAlphabetic(2);
		return(gereratedString);
	}
	
	public static String getPrice() {
		String gereratedString = RandomStringUtils.randomAlphabetic(2);
		return(gereratedString);
	}
	
	public static String getAge() {
		String empage = RandomStringUtils.randomNumeric(5);
		return(empage);
	}
	public static String getSalary() {
		String empsalary = RandomStringUtils.randomNumeric(4);
		return(empsalary);
	}
}
