# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: StockBasicInfoBO.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='StockBasicInfoBO.proto',
  package='com.purefun.fstp.tushare.bo',
  syntax='proto2',
  serialized_pb=_b('\n\x16StockBasicInfoBO.proto\x12\x1b\x63om.purefun.fstp.tushare.bo\"\xe8\x03\n\x10StockBasicInfoBO\x12\x0c\n\x04uuid\x18\x01 \x01(\t\x12\x0c\n\x04\x62oid\x18\x02 \x01(\x12\x12\x13\n\x0b\x64\x65stination\x18\x03 \x01(\t\x12\x11\n\tstockcode\x18\x04 \x01(\t\x12\x11\n\tstockname\x18\x05 \x01(\t\x12\x10\n\x08industry\x18\x06 \x01(\t\x12\x0c\n\x04\x61rea\x18\x07 \x01(\t\x12\n\n\x02pe\x18\x08 \x01(\x01\x12\x14\n\x0coffer_shares\x18\t \x01(\x01\x12\x14\n\x0ctotal_shares\x18\n \x01(\x01\x12\x13\n\x0btotalAssets\x18\x0b \x01(\x01\x12\x14\n\x0cliquidAssets\x18\x0c \x01(\x01\x12\x13\n\x0b\x66ixedAssets\x18\r \x01(\x01\x12\x10\n\x08reserved\x18\x0e \x01(\x01\x12\x18\n\x10reservedPerShare\x18\x0f \x01(\x01\x12\x0b\n\x03\x65sp\x18\x10 \x01(\x01\x12\x0c\n\x04\x62vps\x18\x11 \x01(\x01\x12\n\n\x02pb\x18\x12 \x01(\x01\x12\x11\n\tlist_date\x18\x13 \x01(\t\x12\x13\n\x0bno_dividend\x18\x14 \x01(\x01\x12\x0f\n\x07perundp\x18\x15 \x01(\x01\x12\x0f\n\x07rev_per\x18\x16 \x01(\x01\x12\x0e\n\x06profit\x18\x17 \x01(\x01\x12\x14\n\x0cgross_profit\x18\x18 \x01(\x01\x12\x12\n\nnet_profit\x18\x19 \x01(\x01\x12\x0e\n\x06holder\x18\x1a \x01(\x05')
)




_STOCKBASICINFOBO = _descriptor.Descriptor(
  name='StockBasicInfoBO',
  full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='uuid', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.uuid', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='boid', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.boid', index=1,
      number=2, type=18, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destination', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.destination', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='stockcode', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.stockcode', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='stockname', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.stockname', index=4,
      number=5, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='industry', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.industry', index=5,
      number=6, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='area', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.area', index=6,
      number=7, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='pe', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.pe', index=7,
      number=8, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='offer_shares', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.offer_shares', index=8,
      number=9, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='total_shares', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.total_shares', index=9,
      number=10, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='totalAssets', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.totalAssets', index=10,
      number=11, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='liquidAssets', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.liquidAssets', index=11,
      number=12, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='fixedAssets', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.fixedAssets', index=12,
      number=13, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='reserved', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.reserved', index=13,
      number=14, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='reservedPerShare', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.reservedPerShare', index=14,
      number=15, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='esp', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.esp', index=15,
      number=16, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='bvps', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.bvps', index=16,
      number=17, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='pb', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.pb', index=17,
      number=18, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='list_date', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.list_date', index=18,
      number=19, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='no_dividend', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.no_dividend', index=19,
      number=20, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='perundp', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.perundp', index=20,
      number=21, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='rev_per', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.rev_per', index=21,
      number=22, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='profit', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.profit', index=22,
      number=23, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='gross_profit', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.gross_profit', index=23,
      number=24, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='net_profit', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.net_profit', index=24,
      number=25, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='holder', full_name='com.purefun.fstp.tushare.bo.StockBasicInfoBO.holder', index=25,
      number=26, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto2',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=56,
  serialized_end=544,
)

DESCRIPTOR.message_types_by_name['StockBasicInfoBO'] = _STOCKBASICINFOBO
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

StockBasicInfoBO = _reflection.GeneratedProtocolMessageType('StockBasicInfoBO', (_message.Message,), dict(
  DESCRIPTOR = _STOCKBASICINFOBO,
  __module__ = 'StockBasicInfoBO_pb2'
  # @@protoc_insertion_point(class_scope:com.purefun.fstp.tushare.bo.StockBasicInfoBO)
  ))
_sym_db.RegisterMessage(StockBasicInfoBO)


# @@protoc_insertion_point(module_scope)
