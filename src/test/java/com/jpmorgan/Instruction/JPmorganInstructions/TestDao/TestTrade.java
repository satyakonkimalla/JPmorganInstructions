package com.jpmorgan.Instruction.JPmorganInstructions.TestDao;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;
import com.jpmorgan.Instruction.JPmorganInstructions.util.AppHelper;
import com.jpmorgan.Instruction.JPmorganInstructions.util.Validator;

public class TestTrade {
	public final ExpectedException exception = ExpectedException.none();
	/*
	 * Testing trade POJO, we should be able to create new Trade class
	 * 
	 */
	@Test
	public void testCreateTrade() {
		Trade trade = new Trade("foo", "B", LocalDate.parse("2018-02-10"), "SAR", Double.valueOf(4), Integer.valueOf(1),
				 Double.valueOf(10), LocalDate.parse("2018-02-11"));

		Assert.assertNotNull(trade);
		Assert.assertEquals("foo", trade.getEntity());

	}
	
	/*
	 * Testing trade POJO, Trade shouldnt be null\
	 * no negative prices , quantity
	 * 
	 */
	@Test (expected = Exception.class)
	
	public void testTradeValidations() throws Exception  {
		
		Trade trade=null;
		Validator validator = new Validator(trade);
		validator.validate();
		Assert.assertNull(trade);
		exception.expect(NullPointerException.class);
		
		Trade priceException = new Trade("foo", "B", LocalDate.parse("2018-02-10"), "SAR", 
				Double.valueOf(-4), Integer.valueOf(1),
				 Double.valueOf(10), LocalDate.parse("2018-02-11"));
		//checking for price
		Validator priceValidator = new Validator(trade);
		priceValidator.validate();
		Assert.assertNotNull(priceException);
		exception.expect(NumberFormatException.class);
		exception.expectMessage("Trade  Price is  less than 0");
		//checking for agreedFx
		Trade fxException = new Trade("foo", "B", LocalDate.parse("2018-02-10"), "SAR", 
				Double.valueOf(4), Integer.valueOf(0),
				 Double.valueOf(10), LocalDate.parse("2018-02-11"));
		
		Validator fxValidator = new Validator(fxException);
		fxValidator.validate();
		Assert.assertNotNull(fxException);
		exception.expect(NumberFormatException.class);
		exception.expectMessage("Trade Agreed FX is  less than 0");
		//checking for unit
		Trade unitTrade = new Trade("foo", "B", LocalDate.parse("2018-02-10"), "SAR", 
				Double.valueOf(-4), Integer.valueOf(0),
				 Double.valueOf(10), LocalDate.parse("2018-02-11"));
		
		Validator unitValidator = new Validator(unitTrade);
		unitValidator.validate();
		Assert.assertNotNull(unitValidator);
		exception.expect(NumberFormatException.class);
		exception.expectMessage("Number of  units in trade are 0 or less");
	}

	/*
	 * Testing trade Amount, tradeamount = Price per unit * Units * Agreed Fx
	 * String entity,String transaction,
			      LocalDate settlementDate, String  currency,
			      Double price,long unit,
			      Double fxRate,Double agreedFx,
			      LocalDate instDate
	 */
	@Test
	public void testTradeAmount() {
		Trade trade = new Trade("foo", "B", LocalDate.parse("2018-02-10"), "SAR", Double.valueOf(4), Integer.valueOf(1),
				 Double.valueOf(10), LocalDate.parse("2018-02-11"));
trade.setTradeAmount(AppHelper.calcuateTotalTradeAmt(trade));
		Assert.assertNotNull(trade);
		Assert.assertEquals(Double.valueOf(40), trade.getTradeAmount());

	}

}
