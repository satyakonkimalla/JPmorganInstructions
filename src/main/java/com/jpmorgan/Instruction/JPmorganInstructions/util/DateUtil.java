package com.jpmorgan.Instruction.JPmorganInstructions.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class DateUtil {

	
	private static final List<String> currenciesList = Arrays.asList("SAR", "AED");
	
	private String currency;
	

	private LocalDate date;
	
	public DateUtil() {
	}
	
	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}

	/*
	 * 
	 * @param settlementDate to give the settlement date
	 * @param currency
	 * 
	 * @return localdate settlementDate based on the currency check and weekend check
	 */
	
	public LocalDate getSettlemenDateForCurrency(String settlementDate, String currency){
		
		  LocalDate localDate= LocalDate.parse(settlementDate);
        DayOfWeek day = localDate.getDayOfWeek();
		int  daysToAdd = 0;
		
		if(isSettlementDay(localDate, currency)){
			return localDate;
		}
		
		if(currenciesList.contains(currency)){
			 daysToAdd  =  DayOfWeek.THURSDAY.equals(day) ?  3 : DayOfWeek.FRIDAY.equals(day)?2: DayOfWeek.SATURDAY.equals(day)?1:0 ;
		}else{
			daysToAdd  = DayOfWeek.FRIDAY.equals(day) ?  3 : DayOfWeek.SATURDAY.equals(day)?2: DayOfWeek.SUNDAY.equals(day)?1:0  ;
		}
		
		return localDate.plusDays(daysToAdd);
	}
	

	/*
	 * 
	 * @param settlementDate for date
	 * @param currency to verify 
	 * 
	 * @return boolean
	 */
	public boolean isSettlementDay(LocalDate date,String currency) {
		
    	
    	//Fetch the day of the week from the settlement date object
        String dayOFWeek = date.getDayOfWeek().name();
        //Checking dayofweek falls on the Friday and  Saturday based on currencies
        if(currenciesList.contains(currency)){
        	if ((dayOFWeek.equalsIgnoreCase(Constants.FRIDAY_WEEKEND)) || (dayOFWeek.equalsIgnoreCase(Constants.SATURDAY_WEEKEND))) {
	            return false;
	        }
        }else{
	        if ((dayOFWeek.equalsIgnoreCase(Constants.SATURDAY_WEEKEND)) || (dayOFWeek.equalsIgnoreCase(Constants.SUNDAY_WEEKEND))) {
	            return false;
	        }
        }
        return true;
     }

}
