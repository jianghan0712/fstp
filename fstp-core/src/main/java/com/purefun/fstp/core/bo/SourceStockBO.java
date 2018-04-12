package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

public class SourceStockBO  implements BaseBO {
	private String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = 1501L;					//bo的唯一标识，key
		
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
}
