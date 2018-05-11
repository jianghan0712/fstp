from com.purefun.fstp.core.bo.pro import RDSStockBO_pb2
from com.purefun.fstp.core.bo.model.RDSStockBO import RDSStockBO

class RDSStockBO_OTW(object):

    def __init__(self, byteMsg = None):
        self._bo_pro = RDSStockBO_pb2.RDSStockBO()
        self._bo = RDSStockBO()

        if byteMsg is not None:
            self._bo_pro.ParseFromString(byteMsg)
            self.__setDataFromPB()
        else:
            self.__setDataFromBO()

    def __setDataFromBO(self):
        self._bo_pro.uuid = self._bo.uuid
        self._bo_pro.boid = self._bo.boid
        self._bo_pro.destination = self._bo.destination
        self._bo_pro.product_id = self._bo.product_id
        self._bo_pro.isin = self._bo.isin
        self._bo_pro.recv_time = self._bo.recv_time
        self._bo_pro.secu_name_cn = self._bo.secu_name_cn
        self._bo_pro.secu_name_en = self._bo.secu_name_en
        self._bo_pro.secu_base_id = self._bo.secu_base_id
        self._bo_pro.exch_type = self._bo.exch_type
        self._bo_pro.secu_type = self._bo.secu_type
        self._bo_pro.secu_sub_type = self._bo.secu_sub_type
        self._bo_pro.currency = self._bo.currency
        self._bo_pro.bond_par_value = self._bo.bond_par_value
        self._bo_pro.last_trade_date = self._bo.last_trade_date
        self._bo_pro.list_date = self._bo.list_date
        self._bo_pro.buy_unit = self._bo.buy_unit
        self._bo_pro.sell_unit = self._bo.sell_unit
        self._bo_pro.trade_low_limit = self._bo.trade_low_limit
        self._bo_pro.trade_high_limit = self._bo.trade_high_limit
        self._bo_pro.pre_close_price = self._bo.pre_close_price
        self._bo_pro.tick_price = self._bo.tick_price
        self._bo_pro.price_limit_type = self._bo.price_limit_type
        self._bo_pro.price_high_limit = self._bo.price_high_limit
        self._bo_pro.price_low_limit = self._bo.price_low_limit
        self._bo_pro.ex_right_ratio = self._bo.ex_right_ratio
        self._bo_pro.dividend_price = self._bo.dividend_price
        self._bo_pro.financing_flag = self._bo.financing_flag
        self._bo_pro.margin_flag = self._bo.margin_flag
        self._bo_pro.secu_status = self._bo.secu_status
        self._bo_pro.memo = self._bo.memo

    def __setDataFromPB(self):
        self._bo.uuid = self._bo_pro.uuid
        self._bo.boid = self._bo_pro.boid
        self._bo.destination = self._bo_pro.destination
        self._bo.product_id = self._bo_pro.product_id
        self._bo.isin = self._bo_pro.isin
        self._bo.recv_time = self._bo_pro.recv_time
        self._bo.secu_name_cn = self._bo_pro.secu_name_cn
        self._bo.secu_name_en = self._bo_pro.secu_name_en
        self._bo.secu_base_id = self._bo_pro.secu_base_id
        self._bo.exch_type = self._bo_pro.exch_type
        self._bo.secu_type = self._bo_pro.secu_type
        self._bo.secu_sub_type = self._bo_pro.secu_sub_type
        self._bo.currency = self._bo_pro.currency
        self._bo.bond_par_value = self._bo_pro.bond_par_value
        self._bo.last_trade_date = self._bo_pro.last_trade_date
        self._bo.list_date = self._bo_pro.list_date
        self._bo.buy_unit = self._bo_pro.buy_unit
        self._bo.sell_unit = self._bo_pro.sell_unit
        self._bo.trade_low_limit = self._bo_pro.trade_low_limit
        self._bo.trade_high_limit = self._bo_pro.trade_high_limit
        self._bo.pre_close_price = self._bo_pro.pre_close_price
        self._bo.tick_price = self._bo_pro.tick_price
        self._bo.price_limit_type = self._bo_pro.price_limit_type
        self._bo.price_high_limit = self._bo_pro.price_high_limit
        self._bo.price_low_limit = self._bo_pro.price_low_limit
        self._bo.ex_right_ratio = self._bo_pro.ex_right_ratio
        self._bo.dividend_price = self._bo_pro.dividend_price
        self._bo.financing_flag = self._bo_pro.financing_flag
        self._bo.margin_flag = self._bo_pro.margin_flag
        self._bo.secu_status = self._bo_pro.secu_status
        self._bo.memo = self._bo_pro.memo

    def getBO(self):
        return self._bo

    def getProBO(self):
        return self._bo_pro

    def getUuid(self):
        return self._bo.uuid

    def setUuid(self, uuid):
        self._bo.uuid = uuid
        self._bo_pro.uuid = uuid

    def getBoid(self):
        return self._bo.boid

    def setBoid(self, boid):
        self._bo.boid = boid
        self._bo_pro.boid = boid

    def getDestination(self):
        return self._bo.destination

    def setDestination(self, destination):
        self._bo.destination = destination
        self._bo_pro.destination = destination

    def getProduct_id(self):
        return self._bo.product_id

    def setProduct_id(self, product_id):
        self._bo.product_id = product_id
        self._bo_pro.product_id = product_id

    def getIsin(self):
        return self._bo.isin

    def setIsin(self, isin):
        self._bo.isin = isin
        self._bo_pro.isin = isin

    def getRecv_time(self):
        return self._bo.recv_time

    def setRecv_time(self, recv_time):
        self._bo.recv_time = recv_time
        self._bo_pro.recv_time = recv_time

    def getSecu_name_cn(self):
        return self._bo.secu_name_cn

    def setSecu_name_cn(self, secu_name_cn):
        self._bo.secu_name_cn = secu_name_cn
        self._bo_pro.secu_name_cn = secu_name_cn

    def getSecu_name_en(self):
        return self._bo.secu_name_en

    def setSecu_name_en(self, secu_name_en):
        self._bo.secu_name_en = secu_name_en
        self._bo_pro.secu_name_en = secu_name_en

    def getSecu_base_id(self):
        return self._bo.secu_base_id

    def setSecu_base_id(self, secu_base_id):
        self._bo.secu_base_id = secu_base_id
        self._bo_pro.secu_base_id = secu_base_id

    def getExch_type(self):
        return self._bo.exch_type

    def setExch_type(self, exch_type):
        self._bo.exch_type = exch_type
        self._bo_pro.exch_type = exch_type

    def getSecu_type(self):
        return self._bo.secu_type

    def setSecu_type(self, secu_type):
        self._bo.secu_type = secu_type
        self._bo_pro.secu_type = secu_type

    def getSecu_sub_type(self):
        return self._bo.secu_sub_type

    def setSecu_sub_type(self, secu_sub_type):
        self._bo.secu_sub_type = secu_sub_type
        self._bo_pro.secu_sub_type = secu_sub_type

    def getCurrency(self):
        return self._bo.currency

    def setCurrency(self, currency):
        self._bo.currency = currency
        self._bo_pro.currency = currency

    def getBond_par_value(self):
        return self._bo.bond_par_value

    def setBond_par_value(self, bond_par_value):
        self._bo.bond_par_value = bond_par_value
        self._bo_pro.bond_par_value = bond_par_value

    def getLast_trade_date(self):
        return self._bo.last_trade_date

    def setLast_trade_date(self, last_trade_date):
        self._bo.last_trade_date = last_trade_date
        self._bo_pro.last_trade_date = last_trade_date

    def getList_date(self):
        return self._bo.list_date

    def setList_date(self, list_date):
        self._bo.list_date = list_date
        self._bo_pro.list_date = list_date

    def getBuy_unit(self):
        return self._bo.buy_unit

    def setBuy_unit(self, buy_unit):
        self._bo.buy_unit = buy_unit
        self._bo_pro.buy_unit = buy_unit

    def getSell_unit(self):
        return self._bo.sell_unit

    def setSell_unit(self, sell_unit):
        self._bo.sell_unit = sell_unit
        self._bo_pro.sell_unit = sell_unit

    def getTrade_low_limit(self):
        return self._bo.trade_low_limit

    def setTrade_low_limit(self, trade_low_limit):
        self._bo.trade_low_limit = trade_low_limit
        self._bo_pro.trade_low_limit = trade_low_limit

    def getTrade_high_limit(self):
        return self._bo.trade_high_limit

    def setTrade_high_limit(self, trade_high_limit):
        self._bo.trade_high_limit = trade_high_limit
        self._bo_pro.trade_high_limit = trade_high_limit

    def getPre_close_price(self):
        return self._bo.pre_close_price

    def setPre_close_price(self, pre_close_price):
        self._bo.pre_close_price = pre_close_price
        self._bo_pro.pre_close_price = pre_close_price

    def getTick_price(self):
        return self._bo.tick_price

    def setTick_price(self, tick_price):
        self._bo.tick_price = tick_price
        self._bo_pro.tick_price = tick_price

    def getPrice_limit_type(self):
        return self._bo.price_limit_type

    def setPrice_limit_type(self, price_limit_type):
        self._bo.price_limit_type = price_limit_type
        self._bo_pro.price_limit_type = price_limit_type

    def getPrice_high_limit(self):
        return self._bo.price_high_limit

    def setPrice_high_limit(self, price_high_limit):
        self._bo.price_high_limit = price_high_limit
        self._bo_pro.price_high_limit = price_high_limit

    def getPrice_low_limit(self):
        return self._bo.price_low_limit

    def setPrice_low_limit(self, price_low_limit):
        self._bo.price_low_limit = price_low_limit
        self._bo_pro.price_low_limit = price_low_limit

    def getEx_right_ratio(self):
        return self._bo.ex_right_ratio

    def setEx_right_ratio(self, ex_right_ratio):
        self._bo.ex_right_ratio = ex_right_ratio
        self._bo_pro.ex_right_ratio = ex_right_ratio

    def getDividend_price(self):
        return self._bo.dividend_price

    def setDividend_price(self, dividend_price):
        self._bo.dividend_price = dividend_price
        self._bo_pro.dividend_price = dividend_price

    def getFinancing_flag(self):
        return self._bo.financing_flag

    def setFinancing_flag(self, financing_flag):
        self._bo.financing_flag = financing_flag
        self._bo_pro.financing_flag = financing_flag

    def getMargin_flag(self):
        return self._bo.margin_flag

    def setMargin_flag(self, margin_flag):
        self._bo.margin_flag = margin_flag
        self._bo_pro.margin_flag = margin_flag

    def getSecu_status(self):
        return self._bo.secu_status

    def setSecu_status(self, secu_status):
        self._bo.secu_status = secu_status
        self._bo_pro.secu_status = secu_status

    def getMemo(self):
        return self._bo.memo

    def setMemo(self, memo):
        self._bo.memo = memo
        self._bo_pro.memo = memo

