package com.jpmorgan.Instruction.JPmorganInstructions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.Instruction.JPmorganInstructions.Report.Report;
import com.jpmorgan.Instruction.JPmorganInstructions.dao.Trade;
import com.jpmorgan.Instruction.JPmorganInstructions.util.TradeHelper;

/**
 * Hello world!
 *
 */
public class App 
{
	public static List<Trade> tradeList = new ArrayList<Trade>();
    public static void main( String[] args )
    {
    	App app = new App();
    	try {
    		Report report;
    		TradeHelper helper = new TradeHelper();
    		 report =helper.loadFile("trade.txt");
    		  report=helper.validate(report);
		report.settlementIncomingReport();
		report.settlementOutgoingReport();
		report.displayReport(report);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        System.out.println( "Hello World!" );
    }
    
}
