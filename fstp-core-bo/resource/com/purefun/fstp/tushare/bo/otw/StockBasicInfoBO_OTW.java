package com.purefun.fstp.tushare.bo.otw;

import com.purefun.fstp.tushare.bo.StockBasicInfoBO;
import com.purefun.fstp.tushare.bo.pro.StockBasicInfoBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fstp.core.bo.commom.ICommom_OTW;
import com.google.protobuf.Any;

public class StockBasicInfoBO_OTW implements ICommom_OTW {
    StockBasicInfoBO_PRO.StockBasicInfoBO.Builder builder = null;
    StockBasicInfoBO bo = null;

    public StockBasicInfoBO_OTW() {
        builder = StockBasicInfoBO_PRO.StockBasicInfoBO.newBuilder();
        bo= new StockBasicInfoBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public StockBasicInfoBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = StockBasicInfoBO_PRO.StockBasicInfoBO.newBuilder();
        bo= new StockBasicInfoBO();
        StockBasicInfoBO_PRO.StockBasicInfoBO receive = StockBasicInfoBO_PRO.StockBasicInfoBO.parseFrom(message);
        setStockcode(receive.getStockcode());
        setStockname(receive.getStockname());
        setIndustry(receive.getIndustry());
        setArea(receive.getArea());
        setPe(receive.getPe());
        setOffer_shares(receive.getOfferShares());
        setTotal_shares(receive.getTotalShares());
        setTotalAssets(receive.getTotalAssets());
        setLiquidAssets(receive.getLiquidAssets());
        setFixedAssets(receive.getFixedAssets());
        setReserved(receive.getReserved());
        setReservedPerShare(receive.getReservedPerShare());
        setEsp(receive.getEsp());
        setBvps(receive.getBvps());
        setPb(receive.getPb());
        setList_date(receive.getListDate());
        setNo_dividend(receive.getNoDividend());
        setPerundp(receive.getPerundp());
        setRev_per(receive.getRevPer());
        setProfit(receive.getProfit());
        setGross_profit(receive.getGrossProfit());
        setNet_profit(receive.getNetProfit());
        setHolder(receive.getHolder());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public StockBasicInfoBO_OTW(StockBasicInfoBO bofrom){
        builder = StockBasicInfoBO_PRO.StockBasicInfoBO.newBuilder();
        bo= new StockBasicInfoBO();
        setStockcode(bofrom.stockcode);
        setStockname(bofrom.stockname);
        setIndustry(bofrom.industry);
        setArea(bofrom.area);
        setPe(bofrom.pe);
        setOffer_shares(bofrom.offer_shares);
        setTotal_shares(bofrom.total_shares);
        setTotalAssets(bofrom.totalAssets);
        setLiquidAssets(bofrom.liquidAssets);
        setFixedAssets(bofrom.fixedAssets);
        setReserved(bofrom.reserved);
        setReservedPerShare(bofrom.reservedPerShare);
        setEsp(bofrom.esp);
        setBvps(bofrom.bvps);
        setPb(bofrom.pb);
        setList_date(bofrom.list_date);
        setNo_dividend(bofrom.no_dividend);
        setPerundp(bofrom.perundp);
        setRev_per(bofrom.rev_per);
        setProfit(bofrom.profit);
        setGross_profit(bofrom.gross_profit);
        setNet_profit(bofrom.net_profit);
        setHolder(bofrom.holder);
        setUuid(bofrom.uuid);
        setBoid(bofrom.boid);
        setDestination(bofrom.destination);
    }

    public byte[] serial() {
        return builder.build().toByteArray();
    }

    @Override
    public com.google.protobuf.GeneratedMessageV3.Builder getBuilder() { 
        return builder;
    }

    @Override
    public StockBasicInfoBO getBo() { 
        return bo;
    }

    public java.lang.String getStockcode() {
        return builder.getStockcode();
    }

    public void setStockcode(java.lang.String stockcode) {
        bo.stockcode = stockcode;
        builder.setStockcode(stockcode);
    }

    public java.lang.String getStockname() {
        return builder.getStockname();
    }

    public void setStockname(java.lang.String stockname) {
        bo.stockname = stockname;
        builder.setStockname(stockname);
    }

    public java.lang.String getIndustry() {
        return builder.getIndustry();
    }

    public void setIndustry(java.lang.String industry) {
        bo.industry = industry;
        builder.setIndustry(industry);
    }

    public java.lang.String getArea() {
        return builder.getArea();
    }

    public void setArea(java.lang.String area) {
        bo.area = area;
        builder.setArea(area);
    }

    public double getPe() {
        return builder.getPe();
    }

    public void setPe(double pe) {
        bo.pe = pe;
        builder.setPe(pe);
    }

    public double getOffer_shares() {
        return builder.getOfferShares();
    }

    public void setOffer_shares(double offer_shares) {
        bo.offer_shares = offer_shares;
        builder.setOfferShares(offer_shares);
    }

    public double getTotal_shares() {
        return builder.getTotalShares();
    }

    public void setTotal_shares(double total_shares) {
        bo.total_shares = total_shares;
        builder.setTotalShares(total_shares);
    }

    public double getTotalAssets() {
        return builder.getTotalAssets();
    }

    public void setTotalAssets(double totalAssets) {
        bo.totalAssets = totalAssets;
        builder.setTotalAssets(totalAssets);
    }

    public double getLiquidAssets() {
        return builder.getLiquidAssets();
    }

    public void setLiquidAssets(double liquidAssets) {
        bo.liquidAssets = liquidAssets;
        builder.setLiquidAssets(liquidAssets);
    }

    public double getFixedAssets() {
        return builder.getFixedAssets();
    }

    public void setFixedAssets(double fixedAssets) {
        bo.fixedAssets = fixedAssets;
        builder.setFixedAssets(fixedAssets);
    }

    public double getReserved() {
        return builder.getReserved();
    }

    public void setReserved(double reserved) {
        bo.reserved = reserved;
        builder.setReserved(reserved);
    }

    public double getReservedPerShare() {
        return builder.getReservedPerShare();
    }

    public void setReservedPerShare(double reservedPerShare) {
        bo.reservedPerShare = reservedPerShare;
        builder.setReservedPerShare(reservedPerShare);
    }

    public double getEsp() {
        return builder.getEsp();
    }

    public void setEsp(double esp) {
        bo.esp = esp;
        builder.setEsp(esp);
    }

    public double getBvps() {
        return builder.getBvps();
    }

    public void setBvps(double bvps) {
        bo.bvps = bvps;
        builder.setBvps(bvps);
    }

    public double getPb() {
        return builder.getPb();
    }

    public void setPb(double pb) {
        bo.pb = pb;
        builder.setPb(pb);
    }

    public java.lang.String getList_date() {
        return builder.getListDate();
    }

    public void setList_date(java.lang.String list_date) {
        bo.list_date = list_date;
        builder.setListDate(list_date);
    }

    public double getNo_dividend() {
        return builder.getNoDividend();
    }

    public void setNo_dividend(double no_dividend) {
        bo.no_dividend = no_dividend;
        builder.setNoDividend(no_dividend);
    }

    public double getPerundp() {
        return builder.getPerundp();
    }

    public void setPerundp(double perundp) {
        bo.perundp = perundp;
        builder.setPerundp(perundp);
    }

    public double getRev_per() {
        return builder.getRevPer();
    }

    public void setRev_per(double rev_per) {
        bo.rev_per = rev_per;
        builder.setRevPer(rev_per);
    }

    public double getProfit() {
        return builder.getProfit();
    }

    public void setProfit(double profit) {
        bo.profit = profit;
        builder.setProfit(profit);
    }

    public double getGross_profit() {
        return builder.getGrossProfit();
    }

    public void setGross_profit(double gross_profit) {
        bo.gross_profit = gross_profit;
        builder.setGrossProfit(gross_profit);
    }

    public double getNet_profit() {
        return builder.getNetProfit();
    }

    public void setNet_profit(double net_profit) {
        bo.net_profit = net_profit;
        builder.setNetProfit(net_profit);
    }

    public int getHolder() {
        return builder.getHolder();
    }

    public void setHolder(int holder) {
        bo.holder = holder;
        builder.setHolder(holder);
    }

    public java.lang.String getUuid() {
        return builder.getUuid();
    }

    public void setUuid(java.lang.String uuid) {
        bo.uuid = uuid;
        builder.setUuid(uuid);
    }

    public long getBoid() {
        return builder.getBoid();
    }

    public void setBoid(long boid) {
        bo.boid = boid;
        builder.setBoid(boid);
    }

    public java.lang.String getDestination() {
        return builder.getDestination();
    }

    public void setDestination(java.lang.String destination) {
        bo.destination = destination;
        builder.setDestination(destination);
    }

    public String toString() {
        return "StockBasicInfoBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "stockcode = " + getStockcode() +"," +
            "stockname = " + getStockname() +"," +
            "industry = " + getIndustry() +"," +
            "area = " + getArea() +"," +
            "pe = " + getPe() +"," +
            "offer_shares = " + getOffer_shares() +"," +
            "total_shares = " + getTotal_shares() +"," +
            "totalAssets = " + getTotalAssets() +"," +
            "liquidAssets = " + getLiquidAssets() +"," +
            "fixedAssets = " + getFixedAssets() +"," +
            "reserved = " + getReserved() +"," +
            "reservedPerShare = " + getReservedPerShare() +"," +
            "esp = " + getEsp() +"," +
            "bvps = " + getBvps() +"," +
            "pb = " + getPb() +"," +
            "list_date = " + getList_date() +"," +
            "no_dividend = " + getNo_dividend() +"," +
            "perundp = " + getPerundp() +"," +
            "rev_per = " + getRev_per() +"," +
            "profit = " + getProfit() +"," +
            "gross_profit = " + getGross_profit() +"," +
            "net_profit = " + getNet_profit() +"," +
            "holder = " + getHolder() +"," +
         "]";
    }
}
