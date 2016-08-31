package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ssa.Stock;

public class TestStockMethods {
	Stock stock1;
	Stock stock2;
	
	SimpleDateFormat sdf = new SimpleDateFormat(Stock.DATE_FORMAT);
	
	@Before
	public void setUp() throws Exception {
		stock1 = new Stock("ABC", "ABC Corporation", 50, 3, "08/25/2016");
		stock2 = new Stock("PG", "Proctor and Gamble", 100, 10.50, "08/31/2016");
	}
	
	@Test
	public void testStock() {		
		assertEquals("PG", stock2.getSymbol());
		assertEquals("Proctor and Gamble", stock2.getName());
		assertEquals(100, stock2.getNumShares());
		assertEquals(10.50, stock2.getPurchasePrice(), 0.005);
		assertEquals(10.50, stock2.getCurrPrice(), 0.005);
		assertEquals("08/31/2016", stock2.dateAsString(stock2.getDateBought()));
	}

	@Test
	public void testSetSymbol() {
		stock1.setSymbol("X");
		assertEquals("X", stock1.getSymbol());
	}

	@Test
	public void testSetNumShares() {
		stock2.setNumShares(-5);
		assertEquals(100, stock2.getNumShares());
		
		stock2.setNumShares(400);
		assertEquals(400, stock2.getNumShares());
	}

	@Test
	public void testSetPurchasePrice() {
		stock1.setPurchasePrice(-100);
		assertEquals(3, stock1.getPurchasePrice(), 0.005);
		
		stock1.setPurchasePrice(9.50);
		assertEquals(9.50, stock1.getPurchasePrice(), 0.005);
	}
	
	@Test
	public void testSetCurrentPrice() {
		stock2.setPurchasePrice(-0.01);
		assertEquals(10.50, stock2.getPurchasePrice(), 0.005);
		
		stock2.setPurchasePrice(1);
		assertEquals(1, stock2.getPurchasePrice(), 0.005);
	}

	@Test
	public void testSetDateBought() throws ParseException {		
		stock1.setDateBought(sdf.parse("10/31/2016"));
		Date date = sdf.parse("10/31/2016");
		assertEquals(date, stock1.getDateBought());
	}

	@Test
	public void testSetDateSold() throws ParseException {
		stock2.setDateSold(sdf.parse("12/12/2016"));
		Date date = sdf.parse("12/12/2016");
		assertEquals(date, stock2.getDateSold());
	}

	@Test
	public void testCalcNumDaysStockHeld() throws ParseException {
		Date boughtDate = sdf.parse("08/25/2016");
		stock1.setDateBought(boughtDate);
		Date now = sdf.parse("08/30/2016");
		assertEquals(5, stock1.calcNumDaysStockHeld(now));
		
		boughtDate = sdf.parse("09/01/2016");
		stock2.setDateBought(boughtDate);
		now = sdf.parse("10/01/2016");
		assertEquals(30, stock2.calcNumDaysStockHeld(now));
	}

	@Test
	public void testCalcStockValue() {
		assertEquals(150, stock1.calcStockValue(), 0.005);
		assertEquals(1050, stock2.calcStockValue(), 0.005);
	}
	
	@Test
	public void testNet() {
		assertEquals(0, stock1.calcNet(), 0.005);
		
		stock1.setCurrPrice(5);
		assertEquals(100, stock1.calcNet(), 0.005);
		
		stock2.setCurrPrice(1);
		assertEquals(-950, stock2.calcNet(), 0.005);
	}

	@Test
	public void testSplit() {
		stock1.split(2, 1);
		assertEquals(100, stock1.getNumShares());
		assertEquals(1.50, stock1.getCurrPrice(), 0.005);
		
		stock2.setCurrPrice(10);
		stock2.split(5, 2);
		assertEquals(250, stock2.getNumShares());
		assertEquals(4, stock2.getCurrPrice(), 0.005);
		
		stock2.setCurrPrice(100);
		stock2.setNumShares(100);
		stock2.split(4, 5);
		assertEquals(80, stock2.getNumShares());
		assertEquals(125, stock2.getCurrPrice(), 0.005);
	}

	@Test
	public void testPrintReport() throws ParseException {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(Stock.STOCK_LINE_FORMAT, 
			"ABC", "ABC Corporation", 50, "3.00", "3.00", "150.00", "0.00", "08/25/2016", " "));
		
		assertEquals(sb.toString(), stock1.print());
	}

}
