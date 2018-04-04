package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

public class SourceStockBO  implements BaseBO {
	private static final long serialVersionUID = 1501L;	

	private String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = serialVersionUID;					//bo的唯一标识，key
		
	public String destination = "fstp.ace.rds.source.stock";
	
	/*  business */	
	private String secu_id;
	private String isin;
	private String record_update_time;
	private String secu_chinese_name;
	private String secu_english_name;
	private String secu_base_id;
	private String exch_type;
	private String secu_type;
	private String secu_sub_type;
	private String currency;
	private double bond_par_value;
	private String not_list_stkqty;
	private String last_trade_date;
	private String list_date;
	private String product_set_id;
	private int buy_unit;
	private int sell_unit;
	private int trade_low_limit;
	private int trade_high_limit;
	private double pre_close_price;
	private double tick_price;
	private String price_limit_type;
	private double price_high_limit;
	private double price_low_limit;
	private double ex_right_ratio;
	private double dividend_price;
	private String financing_flag;
	private String margin_flag;
	private String secu_status;
	private String memo;
	
	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	public String getSecu_id() {
		return secu_id;
	}

	public void setSecu_id(String secu_id) {
		this.secu_id = secu_id;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getRecord_update_time() {
		return record_update_time;
	}

	public void setRecord_update_time(String record_update_time) {
		this.record_update_time = record_update_time;
	}

	public String getSecu_chinese_name() {
		return secu_chinese_name;
	}

	public void setSecu_chinese_name(String secu_chinese_name) {
		this.secu_chinese_name = secu_chinese_name;
	}

	public String getSecu_english_name() {
		return secu_english_name;
	}

	public void setSecu_english_name(String secu_english_name) {
		this.secu_english_name = secu_english_name;
	}

	public String getSecu_base_id() {
		return secu_base_id;
	}

	public void setSecu_base_id(String secu_base_id) {
		this.secu_base_id = secu_base_id;
	}

	public String getExch_type() {
		return exch_type;
	}

	public void setExch_type(String exch_type) {
		this.exch_type = exch_type;
	}

	public String getSecu_type() {
		return secu_type;
	}

	public void setSecu_type(String secu_type) {
		this.secu_type = secu_type;
	}

	public String getSecu_sub_type() {
		return secu_sub_type;
	}

	public void setSecu_sub_type(String secu_sub_type) {
		this.secu_sub_type = secu_sub_type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNot_list_stkqty() {
		return not_list_stkqty;
	}

	public void setNot_list_stkqty(String not_list_stkqty) {
		this.not_list_stkqty = not_list_stkqty;
	}

	public String getLast_trade_date() {
		return last_trade_date;
	}

	public void setLast_trade_date(String last_trade_date) {
		this.last_trade_date = last_trade_date;
	}

	public String getList_date() {
		return list_date;
	}

	public void setList_date(String list_date) {
		this.list_date = list_date;
	}

	public String getProduct_set_id() {
		return product_set_id;
	}

	public void setProduct_set_id(String product_set_id) {
		this.product_set_id = product_set_id;
	}

	public String getPrice_limit_type() {
		return price_limit_type;
	}

	public void setPrice_limit_type(String price_limit_type) {
		this.price_limit_type = price_limit_type;
	}

	public String getFinancing_flag() {
		return financing_flag;
	}

	public void setFinancing_flag(String financing_flag) {
		this.financing_flag = financing_flag;
	}

	public String getMargin_flag() {
		return margin_flag;
	}

	public void setMargin_flag(String margin_flag) {
		this.margin_flag = margin_flag;
	}

	public String getSecu_status() {
		return secu_status;
	}

	public void setSecu_status(String secu_status) {
		this.secu_status = secu_status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getBond_par_value() {
		return bond_par_value;
	}

	public void setBond_par_value(double bond_par_value) {
		this.bond_par_value = bond_par_value;
	}

	public int getBuy_unit() {
		return buy_unit;
	}

	public void setBuy_unit(int buy_unit) {
		this.buy_unit = buy_unit;
	}

	public int getSell_unit() {
		return sell_unit;
	}

	public void setSell_unit(int sell_unit) {
		this.sell_unit = sell_unit;
	}

	public int getTrade_low_limit() {
		return trade_low_limit;
	}

	public void setTrade_low_limit(int trade_low_limit) {
		this.trade_low_limit = trade_low_limit;
	}

	public int getTrade_high_limit() {
		return trade_high_limit;
	}

	public void setTrade_high_limit(int trade_high_limit) {
		this.trade_high_limit = trade_high_limit;
	}

	public double getPre_close_price() {
		return pre_close_price;
	}

	public void setPre_close_price(double pre_close_price) {
		this.pre_close_price = pre_close_price;
	}

	public double getTick_price() {
		return tick_price;
	}

	public void setTick_price(double tick_price) {
		this.tick_price = tick_price;
	}

	public double getPrice_high_limit() {
		return price_high_limit;
	}

	public void setPrice_high_limit(double price_high_limit) {
		this.price_high_limit = price_high_limit;
	}

	public double getPrice_low_limit() {
		return price_low_limit;
	}

	public void setPrice_low_limit(double price_low_limit) {
		this.price_low_limit = price_low_limit;
	}

	public double getEx_right_ratio() {
		return ex_right_ratio;
	}

	public void setEx_right_ratio(double ex_right_ratio) {
		this.ex_right_ratio = ex_right_ratio;
	}

	public double getDividend_price() {
		return dividend_price;
	}

	public void setDividend_price(double dividend_price) {
		this.dividend_price = dividend_price;
	}

	@Override
	public String toString() {
		return "SourceStockBO [uuid=" + uuid + ", boid=" + boid + ", destination=" + destination + ", secu_id="
				+ secu_id + ", isin=" + isin + ", record_update_time=" + record_update_time + ", secu_chinese_name="
				+ secu_chinese_name + ", secu_english_name=" + secu_english_name + ", secu_base_id=" + secu_base_id
				+ ", exch_type=" + exch_type + ", secu_type=" + secu_type + ", secu_sub_type=" + secu_sub_type
				+ ", currency=" + currency + ", bond_par_value=" + bond_par_value + ", not_list_stkqty="
				+ not_list_stkqty + ", last_trade_date=" + last_trade_date + ", list_date=" + list_date
				+ ", product_set_id=" + product_set_id + ", buy_unit=" + buy_unit + ", sell_unit=" + sell_unit
				+ ", trade_low_limit=" + trade_low_limit + ", trade_high_limit=" + trade_high_limit
				+ ", pre_close_price=" + pre_close_price + ", tick_price=" + tick_price + ", price_limit_type="
				+ price_limit_type + ", price_high_limit=" + price_high_limit + ", price_low_limit=" + price_low_limit
				+ ", ex_right_ratio=" + ex_right_ratio + ", dividend_price=" + dividend_price + ", financing_flag="
				+ financing_flag + ", margin_flag=" + margin_flag + ", secu_status=" + secu_status + ", memo=" + memo
				+ "]";
	}	
	
}
