package com.jpmorgan.Instruction.JPmorganInstructions.util;

import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;

public class AppHelper {
	
	/*
	 * This method calculates total trade amount in USD
	 * 
	 * @param trade
	 * 
	 * @return double
	 */
	public static double calcuateTotalTradeAmt(Trade trade) {
		double totalTradeAmt = 0;
		if(trade.getPrice() > 0 && trade.getAgreedFx() > 0 && trade.getUnit() > 0){
			totalTradeAmt = trade.getPrice() * trade.getAgreedFx()
					* trade.getUnit();
		}
		return totalTradeAmt;
	}

}
