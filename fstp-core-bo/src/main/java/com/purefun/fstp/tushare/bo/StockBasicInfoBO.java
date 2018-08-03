package com.purefun.fstp.tushare.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.tool.UUID;
import com.purefun.fstp.core.tool.fstpbo;

@Entity  
@Table(name="StockBasicInfo") 
@fstpbo(boid = 2001L, destination = "fstp.ts.stockbasicinfo")
public class StockBasicInfoBO extends BaseBO {
	@QuerySqlField
	public String stockcode = "";
	@QuerySqlField
	public String stockname = "";	
	@QuerySqlField
	public String industry = "";
	@QuerySqlField
	public String area = "";
	@QuerySqlField
	public double pe = 0.0;
	@QuerySqlField
	public double offer_shares = 0.0;//在外发行股本，亿股
	@QuerySqlField
	public double total_shares = 0.0;//总股本，亿股
	@QuerySqlField
	public double totalAssets = 0.0;//总资产,万
	@QuerySqlField
	public double liquidAssets = 0.0;//流动资产,万
	@QuerySqlField
	public double fixedAssets = 0.0;//固定资产,万
	@QuerySqlField
	public double reserved = 0.0;//资本公积金，万
	@QuerySqlField
	public double reservedPerShare = 0.0;//每股公积金
	@QuerySqlField
	public double esp = 0.0;//每股收益
	@QuerySqlField
	public double bvps = 0.0;//每股净资
	@QuerySqlField
	public double pb = 0.0;//市净率
	@QuerySqlField
	public String list_date = null;//在外发行股本，亿股
	@QuerySqlField
	public double no_dividend = 0.0;//未分配利润
	@QuerySqlField
	public double perundp = 0.0;//每股未分配利润
	@QuerySqlField
	public double rev_per = 0.0;//收入同比%
	@QuerySqlField
	public double profit = 0.0;//利润同比(%)
	@QuerySqlField
	public double gross_profit = 0.0;//毛利率%
	@QuerySqlField
	public double net_profit = 0.0;//净利润率%
	@QuerySqlField
	public int holder = 0;//股东总人数
}
