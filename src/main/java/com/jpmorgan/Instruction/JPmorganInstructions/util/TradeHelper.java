package com.jpmorgan.Instruction.JPmorganInstructions.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.Instruction.JPmorganInstructions.Report.Report;
import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;

public class TradeHelper {
	public static List<Trade> tradeList = new ArrayList<Trade>();
	 Map<String, Double> buyNameMap = new HashMap <String, Double>();
	 Map<String, Double> sellNameMap = new HashMap <String, Double>();
	private Report report;
	
	 public Report loadFile(String file) throws IOException, ParseException{
	    	report = new Report();
	        String delimeter = ",";
	        String line = "";
	        String[] lineValue = new String[8];
	        Trade trade = null;
	        
	        List<Trade> dataListForBuy = new ArrayList<Trade>();
	        List<Trade> dataListForSell = new ArrayList<Trade>();
	       

	        DateUtil dateutil = new DateUtil();
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        while ((line = br.readLine()) != null) {
	          lineValue = line.split(delimeter);
	          DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MMM yyyy");
	  		
	          LocalDate settlementdate= LocalDate.parse(lineValue[5],formatter);
	          settlementdate= dateutil.getSettlemenDateForCurrency(settlementdate.toString(), lineValue[3]);
	          LocalDate instrdate= LocalDate.parse(lineValue[4],formatter);
	          if (lineValue[1].equalsIgnoreCase("B")){
	            trade = new Trade();
	            trade.setEntity(lineValue[0]);
	            trade.setTransaction(lineValue[1]);
	            trade.setAgreedFx(Double.parseDouble(lineValue[2]));
	            trade.setCurrency(lineValue[3]);
	            trade.setInstructionDate(instrdate);
	            trade.setSettlementDate(settlementdate);
	            trade.setUnit(Integer.parseInt(lineValue[6]));
	            trade.setPrice(Double.parseDouble(lineValue[7]));
	            
	            trade.setTradeAmount(AppHelper.calcuateTotalTradeAmt(trade));
	            
	            dataListForBuy.add(trade);
	            tradeList.add(trade);
	          } else {
	            trade = new Trade();
	            trade.setEntity(lineValue[0]);
	            trade.setTransaction(lineValue[1]);
	            trade.setAgreedFx(Double.parseDouble(lineValue[2]));
	            trade.setCurrency(lineValue[3]);
	            trade.setInstructionDate(instrdate);
	            trade.setSettlementDate(settlementdate);
	            trade.setUnit(Integer.parseInt(lineValue[6]));
	            trade.setPrice(Double.parseDouble(lineValue[7]));
	            trade.setTradeAmount(AppHelper.calcuateTotalTradeAmt(trade));
	            
	            dataListForSell.add(trade);
	            tradeList.add(trade);
	          }
	          
	        }
	        
	        report.setDataListBuy(dataListForBuy);
	        report.setDataListSell(dataListForSell);
	       
	        return report;
	      }
	    
	 public Report validate(Report report){
		 
		 
		  // test the data load
	        Validator validate ;
	        for (Trade i : report.getDataListBuy()){
	        	validate = new Validator(i);
	        	try {
					validate.validate();
					String entity = i.getEntity();
					Double totalEntityAmt = i.getTradeAmount();
					buyNameMap = generateMap(entity, totalEntityAmt, buyNameMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          System.out.println("buy list--"+i);
	         
	        }
	        report.setBuyEntityMap(buyNameMap);
	        for (Trade i : report.getDataListSell()){
	        	validate = new Validator(i);
	        	try {
					validate.validate();
					String entity = i.getEntity();
					Double totalEntityAmt = i.getTradeAmount();
					sellNameMap = generateMap(entity, totalEntityAmt, sellNameMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	          System.out.println("sell list--"+i);
	        }
	        report.setSellEntityMap(sellNameMap);
	        
	        return report;
	 }
	 
	    private static Map<String, Double> generateMap(String key,
	    		double tradeAmount, Map<String, Double> entityMap) {
	    	if (entityMap.size() > 0) {
	    		if (entityMap.get(key) != null) {
	    			tradeAmount = tradeAmount + entityMap.get(key);
	    		}
	    	}
	    	entityMap.put(key, tradeAmount);
	    	return entityMap;
	    }
}
