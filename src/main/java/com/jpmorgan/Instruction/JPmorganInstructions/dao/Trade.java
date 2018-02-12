package com.jpmorgan.Instruction.JPmorganInstructions.dao;

import java.time.LocalDate;

public class Trade {
	
	private String entity;
	private String transaction;
	private Double agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private long unit;
	private Double price;
	private Double tradeAmount	;
	
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	/*Initialising all the properties of the trade during creation
	 * 
	 * */
	public Trade (){
		
	}
	public Trade (String entity,String transaction,
			      LocalDate settlementDate, String  currency,
			      Double price,long unit,
			      Double agreedFx,
			      LocalDate instDate){
		this.entity = entity;
		this.transaction = transaction;
		this.price =  price;
		this.settlementDate = settlementDate ;
		this.currency = currency;
		this.unit = unit;
		this.agreedFx  = agreedFx;
		this.instructionDate  = instDate;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public Double getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	public long getUnit() {
		return unit;
	}
	public void setUnit(long unit) {
		this.unit = unit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Trade [entity=" + entity + ", transaction=" + transaction + ", agreedFx=" + agreedFx + ", currency="
				+ currency + ", instructionDate=" + instructionDate + ", settlementDate=" + settlementDate + ", unit="
				+ unit + ", price=" + price + ", tradeAmount=" + tradeAmount + "]";
	}
	

}
