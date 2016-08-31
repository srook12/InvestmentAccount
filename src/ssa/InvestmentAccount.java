package ssa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InvestmentAccount extends Account {
	private List<Stock> portfolio = new ArrayList<Stock>();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(Stock.DATE_FORMAT);
	
	public static final String STOCK_LINE_FORMAT = "%-3s   %-20s   %-6d   $%6s   $%6s   $%10s   $%10s   %-10s   %-10s\n";
	
	public void buy(Stock stock) {
		portfolio.add(stock);
	}
	
	public void sell(Stock stock, String sellDate) throws ParseException {
		stock.setDateSold(sdf.parse(sellDate));
	}
	
	@Override
	public String print() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(String.format("%-3s   ", "Sym"));
		sb.append(String.format("%-20s   ", "Name"));
		sb.append(String.format("%-6s   ", "Shares"));
		sb.append(String.format("%-6s    ", "Cost"));
		sb.append(String.format("%-6s     ", "Price"));
		sb.append(String.format("%10s    ", "Value"));
		sb.append(String.format("%10s   ", "Gain/Loss"));
		sb.append(String.format("%-10s   ", "Purchased"));
		sb.append(String.format("%-10s", "Sold"));		
		sb.append("\n");
		
		for(Stock stock : portfolio) {
			sb.append(stock.print()).append("\n");
		}
		
		return sb.toString();
	}
}
