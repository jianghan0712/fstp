import uuid

class RDSStockBO(object):

    def __init__(self):
        self.uuid = str(uuid.uuid1())
        self.boid = 1001L
        self.destination = "fstp.ace.rds.server.stock"
        self.product_id = ''
        self.isin = ''
        self.recv_time = ''
        self.secu_name_cn = ''
        self.secu_name_en = ''
        self.secu_base_id = ''
        self.exch_type = ''
        self.secu_type = ''
        self.secu_sub_type = ''
        self.currency = ''
        self.bond_par_value = 0
        self.last_trade_date = ''
        self.list_date = ''
        self.buy_unit = 0
        self.sell_unit = 0
        self.trade_low_limit = 0
        self.trade_high_limit = 0
        self.pre_close_price = 0
        self.tick_price = 0
        self.price_limit_type = ''
        self.price_high_limit = 0
        self.price_low_limit = 0
        self.ex_right_ratio = 0
        self.dividend_price = 0
        self.financing_flag = ''
        self.margin_flag = ''
        self.secu_status = ''
        self.memo = ''
