package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

public class SourceStockBO  implements BaseBO {
	public String uuid = UUID.createUuid();					
	public long boid = 1501L;							
	public String destination = "fstp.ace.rds.source.stock";	
	/*  business */	
	public String secu_id;
	public String isin;
	public String record_update_time;
	public String secu_chinese_name;
	public String secu_english_name;
	public String secu_base_id;
	public String exch_type;
	public String secu_type;
	public String secu_sub_type;
	public String currency;
	public double bond_par_value;
	public String not_list_stkqty;
	public String last_trade_date;
	public String list_date;
	public String product_set_id;
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
}
