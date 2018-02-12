package com.jpmorgan.Instruction.JPmorganInstructions.Report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;

public class Report {

	private Map<String, Double> buySettlementDateMap;
	private Map<String, Double> sellSettlementDateMap;
	private Map<String, Double> buyEntityMap;
	private Map<String, Double> sellEntityMap	;
	
	private List<Trade> dataListBuy = new ArrayList<Trade>();
	private    List<Trade> dataListSell = new ArrayList<Trade>();
	
	public List<Trade> getDataListBuy() {
		return dataListBuy;
	}
	public void setDataListBuy(List<Trade> dataListBuy) {
		this.dataListBuy = dataListBuy;
	}
	public List<Trade> getDataListSell() {
		return dataListSell;
	}
	public void setDataListSell(List<Trade> dataListSell) {
		this.dataListSell = dataListSell;
	}
	public Map<String, Double> getBuySettlementDateMap() {
		return buySettlementDateMap;
	}
	public void setBuySettlementDateMap(Map<String, Double> buySettlementDateMap) {
		this.buySettlementDateMap = buySettlementDateMap;
	}
	public Map<String, Double> getSellSettlementDateMap() {
		return sellSettlementDateMap;
	}
	public void setSellSettlementDateMap(Map<String, Double> sellSettlementDateMap) {
		this.sellSettlementDateMap = sellSettlementDateMap;
	}
	public Map<String, Double> getBuyEntityMap() {
		return buyEntityMap;
	}
	public void setBuyEntityMap(Map<String, Double> buyEntityMap) {
		this.buyEntityMap = buyEntityMap;
	}
	public Map<String, Double> getSellEntityMap() {
		return sellEntityMap;
	}
	public void setSellEntityMap(Map<String, Double> sellEntityMap) {
		this.sellEntityMap = sellEntityMap;
	}
	  // Generate incoming settlement report
	  public void settlementIncomingReport() {
		   System.out.println(" INCOMING USD SETTLEMENT PER DAY ");
		    Map <LocalDate, Double> sumIncomingByDate = this.getDataListSell().stream()
		    		.sorted(Comparator.comparing(Trade::getSettlementDate, Comparator.reverseOrder())).	collect(
		                                                                                   Collectors.groupingBy(Trade::getSettlementDate, 
		                                                                                		   Collectors.summingDouble(Trade::getTradeAmount)));
	    
if(sumIncomingByDate!=null && sumIncomingByDate.size() > 0){
			
			System.out.println("Everyday  Settlement Amount for SELL Trades based on settlement date");
			
			//Looping map to display the value
			sumIncomingByDate.forEach((k, v) -> System.out
					.println("Settlement Date: " + k + ", TotalAMount: " + v
							+ "USD"));
		}


	  }
	
	  public void settlementOutgoingReport() {
		    System.out.println("OUTGOING USD SETTLEMENT PER DAY");
		    Map <LocalDate, Double> sumOutgoingByDate = this.getDataListBuy().stream().collect(
		                                                                                  Collectors.groupingBy(Trade::getSettlementDate, 
		                                                                                                        Collectors.summingDouble(Trade::getTradeAmount)));
			if(sumOutgoingByDate!=null && sumOutgoingByDate.size() > 0){
				
				System.out.println("Everyday Total Settlement Amount for Buy Trades based on settlement date");
				
				sumOutgoingByDate.forEach((k, v) -> System.out
						.println("Settlement Date: " + k + ", TotalAMount: " + v
								+ "USD"));
			}
		    
		  }
		  
	  
	  public static void displayReport(Report reportBean) {
			
			
			Map<String, Double> buyEntityMap = reportBean.getBuyEntityMap();
			Map<String, Double> sellEntityMap = reportBean.getSellEntityMap();
			
			if(buyEntityMap!=null && buyEntityMap.size() > 0){
				
				buyEntityMap =	buyEntityMap
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(
						Collectors.toMap(Map.Entry::getKey,
								Map.Entry::getValue, (a, b) -> a,
								LinkedHashMap::new));
				
				System.out
				.println("BUY Amount for  Trades Based On Entity ");
				
				buyEntityMap.forEach((k, v) -> System.out.println(" Name: " + k
				+ ", AMount: " + v + "USD"));
			}
			
			if(sellEntityMap!=null && sellEntityMap.size() > 0){
				
				//calling sort method for incoming entities based on the totaltradeAmount in descending
				sellEntityMap =	sellEntityMap
						.entrySet()
						.stream()
						.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
						.collect(
								Collectors.toMap(Map.Entry::getKey,
										Map.Entry::getValue, (a, b) -> a,
										LinkedHashMap::new));
				
				System.out
						.println("SELL  Amount for Trades Based On Entity ");
				
				sellEntityMap.forEach((k, v) -> System.out.println(" Name: " + k
						+ ", Total: " + v + "USD"));
			}
		}
	  
	  

}
