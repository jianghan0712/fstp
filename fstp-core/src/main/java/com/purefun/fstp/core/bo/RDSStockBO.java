package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="RDS_STOCK") 
public class RDSStockBO  implements BaseBO {
	private static final long serialVersionUID = 1001L;	
	@Id
	private String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = serialVersionUID;					//bo的唯一标识，key
		
	public String destination = "fstp.ace.rds.server.stock";
	
	/*  business */	
	public String product_id;
	public String isin;
	public String recv_time;
	public String secu_name_cn;
	public String secu_name_en;
	public String secu_base_id;
	public String exch_type;
	public String secu_type;
	public String secu_sub_type;
	public String currency;
	public double bond_par_value;
	public String last_trade_date;
	public String list_date;
	public int buy_unit;
	public int sell_unit;
	public int trade_low_limit;
	public int trade_high_limit;
	public double pre_close_price;
	public double tick_price;
	public String price_limit_type;
	public double price_high_limit;
	public double price_low_limit;
	public double ex_right_ratio;
	public double dividend_price;
	public String financing_flag;
	public String margin_flag;
	public String secu_status;
	public String memo;
	
	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}
		
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getRecv_time() {
		return recv_time;
	}

	public void setRecv_time(String recv_time) {
		this.recv_time = recv_time;
	}

	public String getSecu_name_cn() {
		return secu_name_cn;
	}

	public void setSecu_name_cn(String secu_name_cn) {
		this.secu_name_cn = secu_name_cn;
	}

	public String getSecu_name_en() {
		return secu_name_en;
	}

	public void setSecu_name_en(String secu_name_en) {
		this.secu_name_en = secu_name_en;
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

	public double getBond_par_value() {
		return bond_par_value;
	}

	public void setBond_par_value(double bond_par_value) {
		this.bond_par_value = bond_par_value;
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
		return "RDSStockBO [uuid=" + uuid + ", boid=" + boid + ", destination=" + destination + ", product_id="
				+ product_id + ", isin=" + isin + ", recv_time=" + recv_time + ", secu_name_cn=" + secu_name_cn
				+ ", secu_name_en=" + secu_name_en + ", secu_base_id=" + secu_base_id + ", exch_type=" + exch_type
				+ ", secu_type=" + secu_type + ", secu_sub_type=" + secu_sub_type + ", currency=" + currency
				+ ", bond_par_value=" + bond_par_value + ", last_trade_date=" + last_trade_date + ", list_date="
				+ list_date + ", buy_unit=" + buy_unit + ", sell_unit=" + sell_unit + ", trade_low_limit="
				+ trade_low_limit + ", trade_high_limit=" + trade_high_limit + ", pre_close_price=" + pre_close_price
				+ ", tick_price=" + tick_price + ", price_limit_type=" + price_limit_type + ", price_high_limit="
				+ price_high_limit + ", price_low_limit=" + price_low_limit + ", ex_right_ratio=" + ex_right_ratio
				+ ", dividend_price=" + dividend_price + ", financing_flag=" + financing_flag + ", margin_flag="
				+ margin_flag + ", secu_status=" + secu_status + ", memo=" + memo + "]";
	}

	
}
