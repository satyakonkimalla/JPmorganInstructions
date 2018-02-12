package com.jpmorgan.Instruction.JPmorganInstructions.util;

import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;

public class Validator {
	
	private Trade trade;
	
public Validator (Trade trade) {
	this.trade=trade;
//	validate();
	
}

public void validate () throws Exception{
	if (this.trade == null)
		throw new NullPointerException("Trade  is null");
	;

	if (this.trade.getAgreedFx() != null && this.trade.getAgreedFx().isNaN() && this.trade.getAgreedFx() < 0) {
		throw new NumberFormatException(
				"Trade Agreed FX is  less than 0");
	}

	if (this.trade.getPrice() != null && this.trade.getPrice().isNaN() && this.trade.getPrice() < 0) {
		throw new NumberFormatException(
				"Trade  Price is  less than 0");
	}

	if (this.trade.getUnit()  < 0) {
		throw new NumberFormatException(
				"Number of  units in trade are 0 or less");
	}

}

}
