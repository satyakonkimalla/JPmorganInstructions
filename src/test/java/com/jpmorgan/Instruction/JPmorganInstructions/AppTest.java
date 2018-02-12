package com.jpmorgan.Instruction.JPmorganInstructions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jpmorgan.Instruction.JPmorganInstructions.TestDao.TestTrade;
import com.jpmorgan.Instruction.JPmorganInstructions.TestUtil.TestSettlementDate;
import com.jpmorgan.Instruction.JPmorganInstructions.util.TradeHelper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestTrade.class, TestSettlementDate.class,
	 })
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
