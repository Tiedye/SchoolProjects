/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.dowjones;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class StockRecord implements Comparable<StockRecord>{

    int condensedDate;
    int open;
    int high;
    int low;
    int close;
    long volume;
    int adjClose;

    public StockRecord(int condensedDate, int open, int high, int low, int close, long volume, int adjClose) {
        this.condensedDate = condensedDate;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
    }
    
    public void write(DataOutputStream out) throws IOException{
        out.writeInt(condensedDate);
        out.writeInt(open);
        out.writeInt(high);
        out.writeInt(low);
        out.writeInt(close);
        out.writeLong(volume);
        out.writeInt(adjClose);
    }
    
    private static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    
    private static String toMoney(int value) {
        return moneyFormatter.format(value / 100.0);
    }

    @Override
    public String toString() {
        // convert integer money values back to the input format and print 'em
        LocalDate date = LocalDate.of(condensedDate/10000, condensedDate/100%100, condensedDate%100);
        return "Date: " + date + ", Open: " + toMoney(open) + ", High: " + toMoney(high) + ", Low: " + toMoney(low) + ", Close: " + toMoney(close) + ", Volume: " + volume + ", Adj. Close: " + toMoney(adjClose);
    }
    
    // for sorting, implements a date based natural ordering
    @Override
    public int compareTo(StockRecord o) {
        if (condensedDate > o.condensedDate) {
            return 1;
        }else if(condensedDate < o.condensedDate) {
            return -1;
        }else{
            return 0;
        }
    }
    
}
