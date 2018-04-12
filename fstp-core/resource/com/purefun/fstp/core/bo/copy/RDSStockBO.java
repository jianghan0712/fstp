package com.purefun.fstp.core.bo.copy;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="RDS_STOCK") 
public class RDSStockBO  implements BaseBO {	
	@Id
	public String uuid = UUID.createUuid();					
	public long boid = 1001L;							
	public String destination = "fstp.ace.rds.server.stock";	
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
	
}
