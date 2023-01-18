package model.stock;

import util.ID;

import java.util.Date;

public class StockList {
    private ID id;
    private ID userID;
    private Stock stock;
    private int number;
    private Date buyDate;
    private Date sellDate;

    public StockList(ID userID, Stock stock, int number) {
        this.id = new ID();
        this.userID = userID;
        this.stock = stock;
        this.number = number;
        this.buyDate = new Date();
        this.sellDate = null;
    }
    public String toString() {
        return id + " " + userID + " " + stock.getName() + " " + number + " " + buyDate + " " + (sellDate == null ? " " : sellDate);
    }

    public ID getUserID() {
        return userID;
    }

    public void setUserID(ID userID) {
        this.userID = userID;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }
}
