package ssa;

import java.text.ParseException;

public class Mainline {

	public static void main(String[] args) throws ParseException {
		Stock stock1 = new Stock("ABC", "ABC Corporation", 50, 3, "08/25/2016");
		Stock stock2 = new Stock("PG", "Proctor and Gamble", 100, 10.50, "08/31/2016");
		Stock stock3 = new Stock("PG", "Proctor and Gamble", 100, 45, "01/01/2016");
		Stock stock4 = new Stock("PG", "Proctor and Gamble", 200, 85, "03/01/2016");
		Stock stock5 = new Stock("GE", "General Electric", 500, 27.50, "03/01/2016");

		InvestmentAccount account = new InvestmentAccount();
		
		account.buy(stock1);
		account.buy(stock2);
		account.buy(stock3);
		account.buy(stock4);
		account.buy(stock5);
		
		stock3.setCurrPrice(80);
		stock4.setCurrPrice(80);
		stock5.setCurrPrice(31.2);
		
		account.sell(stock1, "10/31/2016");
		account.sell(stock3, "07/31/2016");
		
		System.out.println(account.print());
		
		System.out.println("Splitting stocks");
		System.out.println("=================================");
		
		stock4.split(5, 1);
		stock5.split(4, 3);
		
		System.out.println(account.print());
		
	}
}
