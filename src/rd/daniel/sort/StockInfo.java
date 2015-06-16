/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.sort;

/**
 *
 * @author Daniel
 */
public class StockInfo implements Comparable<StockInfo>{

    long price;
    long date;
    long dividend;
    long earnings;
    long cpi;
    long interestRate;
    long peRatio;

    public StockInfo(long price, long date, long dividend, long earnings, long cpi, long interestRate, long peRatio) {
        this.price = price;
        this.date = date;
        this.dividend = dividend;
        this.earnings = earnings;
        this.cpi = cpi;
        this.interestRate = interestRate;
        this.peRatio = peRatio;
    }
    
    @Override
    public int compareTo(StockInfo o) {
        return Long.compare(date, o.date);
    }

    @Override
    public String toString() {
        return price/1e2 + "," + date/1e2 + "," + dividend/1e2 + "," + earnings/1e2 + "," + cpi/1e2 + "," + interestRate/1e2 + "," + peRatio/1e2 + ",";
    }
    
}
