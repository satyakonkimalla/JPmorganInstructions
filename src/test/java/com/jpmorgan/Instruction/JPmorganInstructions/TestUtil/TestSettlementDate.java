package com.jpmorgan.Instruction.JPmorganInstructions.TestUtil;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.Instruction.JPmorganInstructions.util.DateUtil;

public class TestSettlementDate {
	
	
	/*testing to check if day falls on weekday or weekend 
	based on currency*/

	@Test
	public void testIsWorkingDate() {
		DateUtil dateUtil =  new  DateUtil();
		
		Assert.assertTrue(dateUtil.isSettlementDay(LocalDate.parse("2018-02-09"),"GBP"));
		Assert.assertFalse(dateUtil.isSettlementDay(LocalDate.parse("2018-02-10"),"SAR"));
		Assert.assertTrue(dateUtil.isSettlementDay(LocalDate.parse("2018-02-11"),"AED"));
		Assert.assertTrue(dateUtil.isSettlementDay(LocalDate.parse("2017-11-24"),"SGP"));
	}

	/*testing to check If an instructed settlement date falls on a weekend, 
	 * then the settlement date should be changed to the next working day
	 * based on currency*/
	
	@Test
	public void testGetSettlemenDateForCurrency() {
		DateUtil dateUtil =  new  DateUtil();
		LocalDate  sunday =  LocalDate.parse("2018-02-18");
		
		Assert.assertEquals(sunday, dateUtil.getSettlemenDateForCurrency("2018-02-18", "AED"));
		Assert.assertEquals(LocalDate.parse("2018-02-21"), dateUtil.getSettlemenDateForCurrency("2018-02-21", "AED"));
		Assert.assertEquals(LocalDate.parse("2018-02-22"), dateUtil.getSettlemenDateForCurrency("2018-02-22", "AED"));
		Assert.assertEquals(LocalDate.parse("2018-02-25"), dateUtil.getSettlemenDateForCurrency("2018-02-23", "AED"));
		Assert.assertEquals(sunday, dateUtil.getSettlemenDateForCurrency("2018-02-18", "SAR"));
		Assert.assertEquals(LocalDate.parse("2018-02-21"), dateUtil.getSettlemenDateForCurrency("2018-02-21", "SAR"));
		Assert.assertEquals(LocalDate.parse("2018-02-22"), dateUtil.getSettlemenDateForCurrency("2018-02-22", "SAR"));
		Assert.assertEquals(LocalDate.parse("2018-02-25"), dateUtil.getSettlemenDateForCurrency("2018-02-23", "SAR"));
		// non currency list in array
		Assert.assertEquals(LocalDate.parse("2018-02-19"), dateUtil.getSettlemenDateForCurrency("2018-02-18", "GBP"));
		Assert.assertEquals(LocalDate.parse("2018-02-21"), dateUtil.getSettlemenDateForCurrency("2018-02-21", "GBP"));
		Assert.assertEquals(LocalDate.parse("2018-02-23"), dateUtil.getSettlemenDateForCurrency("2018-02-23", "GBP"));
		Assert.assertEquals(LocalDate.parse("2018-02-26"), dateUtil.getSettlemenDateForCurrency("2018-02-24", "GBP"));
	}
	



}
