# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: ExampleQnsBO.proto

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
  name='ExampleQnsBO.proto',
  package='com.purefun.fstp.example.bo',
  syntax='proto2',
  serialized_pb=_b('\n\x12\x45xampleQnsBO.proto\x12\x1b\x63om.purefun.fstp.example.bo\"k\n\x0c\x45xampleQnsBO\x12\x0c\n\x04uuid\x18\x01 \x01(\t\x12\x0c\n\x04\x62oid\x18\x02 \x01(\x12\x12\x13\n\x0b\x64\x65stination\x18\x03 \x01(\t\x12\x0f\n\x07\x63ompany\x18\x04 \x01(\t\x12\x0c\n\x04name\x18\x05 \x01(\t\x12\x0b\n\x03\x61ge\x18\x06 \x01(\x05')
)




_EXAMPLEQNSBO = _descriptor.Descriptor(
  name='ExampleQnsBO',
  full_name='com.purefun.fstp.example.bo.ExampleQnsBO',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='uuid', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.uuid', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='boid', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.boid', index=1,
      number=2, type=18, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destination', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.destination', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='company', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.company', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='name', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.name', index=4,
      number=5, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='age', full_name='com.purefun.fstp.example.bo.ExampleQnsBO.age', index=5,
      number=6, type=5, cpp_type=1, label=1,
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
  serialized_start=51,
  serialized_end=158,
)

DESCRIPTOR.message_types_by_name['ExampleQnsBO'] = _EXAMPLEQNSBO
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

ExampleQnsBO = _reflection.GeneratedProtocolMessageType('ExampleQnsBO', (_message.Message,), dict(
  DESCRIPTOR = _EXAMPLEQNSBO,
  __module__ = 'ExampleQnsBO_pb2'
  # @@protoc_insertion_point(class_scope:com.purefun.fstp.example.bo.ExampleQnsBO)
  ))
_sym_db.RegisterMessage(ExampleQnsBO)


# @@protoc_insertion_point(module_scope)
