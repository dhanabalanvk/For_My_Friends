/**
 * 
 */
package com.dhana.uthir;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DhanabalanV
 *
 */
public class FilterUtil {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FilterUtil util = new FilterUtil();
		// Preparing test data
		List<EffectiveTermVO> testData = util.prepareTestData();
		System.out.println("Result : " + util.processRequest(testData));
	}

	/**
	 * This method is to process given list and identifies the required object.
	 * 
	 * @param testData
	 * @return requiredObj - output
	 */
	public EffectiveTermVO processRequest(List<EffectiveTermVO> testData) {
		FilterUtil util = new FilterUtil();
		// Current date
		Date today = new Date();

		/*
		 * To check whether at-least one active record is there. (Make it true
		 * once found)
		 */
		boolean isActive = false;

		EffectiveTermVO requiredObj = null;

		int lastNearestEffDays = 0;
		int lastNearestTermDays = 0;

		boolean firstTimeActive = true;
		boolean firstTimeInActive = true;

		for (EffectiveTermVO data : testData) {
			Date effDt = util.getDateFormat(data.getEffDt());
			Date termDt = util.getDateFormat(data.getTermDt());
			if (!isActive && termDt.after(today) && effDt.before(today)) {
				isActive = true;
			}
			// If Active record is found then finding the nearest effective date
			// object
			if (termDt.after(today) && effDt.before(today)) {
				// Taking number of days between current date and effective date
				int diffInDays = util.diffNumOfDays(today, effDt);

				/*
				 * Consider first object is the required object for the first
				 * time.
				 */
				if (firstTimeActive) {
					lastNearestEffDays = diffInDays;
					requiredObj = data;
					firstTimeActive = false;
				}
				/*
				 * If the effective date is nearer to today than previous one
				 * then assign this as required object
				 */
				if (diffInDays < lastNearestEffDays) {
					lastNearestEffDays = diffInDays;
					requiredObj = data;
				}
			} else if (!isActive && termDt.before(today)) {
				// Taking number of days between current date and term date
				int diffInDays = util.diffNumOfDays(today, termDt);

				/*
				 * Consider first object is the required object for the first
				 * time.
				 */
				if (firstTimeInActive) {
					lastNearestTermDays = diffInDays;
					requiredObj = data;
					firstTimeInActive = false;
				}
				/*
				 * If the term date is nearer to today than previous one then
				 * assign this as required object
				 */
				if (lastNearestTermDays > diffInDays) {
					lastNearestTermDays = diffInDays;
					requiredObj = data;
				}
			}
		}
		return requiredObj;
	}

	/**
	 * This method will calculate the number of days between given two dates
	 * 
	 * @param first
	 * @param second
	 * @return number of days
	 */
	public int diffNumOfDays(Date first, Date second) {
		if (first != null && second != null) {
			return (int) ((first.getTime() - second.getTime()) / (1000 * 60 * 60 * 24));

		} else {
			return 0;
		}
	}

	/**
	 * This method will format the given string into respective date <br>
	 * If input is null it will assign default date as <b>9999-12-31</b> <br>
	 * Date format is <b> yyyy-MM-dd </b>
	 * 
	 * @param input
	 *            - Input date
	 * @return - Formated date
	 */
	public Date getDateFormat(String input) {
		if (null == input) {
			input = "9999-12-31";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(input);
		} catch (ParseException e) {
			System.err.println(e);
			return null;
		}
	}

	/**
	 * This method is to prepare test data
	 * 
	 * @return
	 */
	public List<EffectiveTermVO> prepareTestData() {

		List<EffectiveTermVO> testData = new ArrayList<EffectiveTermVO>();

		EffectiveTermVO testObj = new EffectiveTermVO();

		// Test Data - 1
		testObj.setEffDt("2018-7-28");
		testObj.setTermDt("2019-7-24");
		testData.add(testObj);

		// Test Data - 2
		testObj = new EffectiveTermVO();
		testObj.setEffDt("2012-7-27");
		testObj.setTermDt("2019-8-25");
		testData.add(testObj);
		
		// Test Data - 3
		testObj = new EffectiveTermVO();
		testObj.setEffDt("2012-8-27");
		testObj.setTermDt("2013-5-25");
		testData.add(testObj);
		/*
		// Test Data - 4
		testObj = new EffectiveTermVO();
		testObj.setEffDt("2011-7-27");
		testObj.setTermDt("2018-5-25");
		testData.add(testObj);
*/
		/*
		 * testObj.setEffDt("2014-6-14"); testObj.setTermDt("2016-9-25");
		 * testData.add(testObj);
		 */

		return testData;
	}

}
