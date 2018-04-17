// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ExampleBO.proto

package com.purefun.fstp.example.bo.pro;

public final class ExampleBO_PRO {
  private ExampleBO_PRO() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ExampleBOOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ExampleBO)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string uuid = 1;</code>
     */
    boolean hasUuid();
    /**
     * <code>required string uuid = 1;</code>
     */
    java.lang.String getUuid();
    /**
     * <code>required string uuid = 1;</code>
     */
    com.google.protobuf.ByteString
        getUuidBytes();

    /**
     * <code>required sint64 boid = 2;</code>
     */
    boolean hasBoid();
    /**
     * <code>required sint64 boid = 2;</code>
     */
    long getBoid();

    /**
     * <code>required string destination = 3;</code>
     */
    boolean hasDestination();
    /**
     * <code>required string destination = 3;</code>
     */
    java.lang.String getDestination();
    /**
     * <code>required string destination = 3;</code>
     */
    com.google.protobuf.ByteString
        getDestinationBytes();

    /**
     * <code>optional string name = 4;</code>
     */
    boolean hasName();
    /**
     * <code>optional string name = 4;</code>
     */
    java.lang.String getName();
    /**
     * <code>optional string name = 4;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>optional int32 age = 5;</code>
     */
    boolean hasAge();
    /**
     * <code>optional int32 age = 5;</code>
     */
    int getAge();
  }
  /**
   * Protobuf type {@code ExampleBO}
   */
  public  static final class ExampleBO extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ExampleBO)
      ExampleBOOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ExampleBO.newBuilder() to construct.
    private ExampleBO(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ExampleBO() {
      uuid_ = "";
      boid_ = 0L;
      destination_ = "";
      name_ = "";
      age_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ExampleBO(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              uuid_ = bs;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              boid_ = input.readSInt64();
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              destination_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              name_ = bs;
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              age_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.internal_static_ExampleBO_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.internal_static_ExampleBO_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.class, com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.Builder.class);
    }

    private int bitField0_;
    public static final int UUID_FIELD_NUMBER = 1;
    private volatile java.lang.Object uuid_;
    /**
     * <code>required string uuid = 1;</code>
     */
    public boolean hasUuid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string uuid = 1;</code>
     */
    public java.lang.String getUuid() {
      java.lang.Object ref = uuid_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uuid_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string uuid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUuidBytes() {
      java.lang.Object ref = uuid_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uuid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BOID_FIELD_NUMBER = 2;
    private long boid_;
    /**
     * <code>required sint64 boid = 2;</code>
     */
    public boolean hasBoid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required sint64 boid = 2;</code>
     */
    public long getBoid() {
      return boid_;
    }

    public static final int DESTINATION_FIELD_NUMBER = 3;
    private volatile java.lang.Object destination_;
    /**
     * <code>required string destination = 3;</code>
     */
    public boolean hasDestination() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string destination = 3;</code>
     */
    public java.lang.String getDestination() {
      java.lang.Object ref = destination_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          destination_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string destination = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDestinationBytes() {
      java.lang.Object ref = destination_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        destination_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NAME_FIELD_NUMBER = 4;
    private volatile java.lang.Object name_;
    /**
     * <code>optional string name = 4;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string name = 4;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string name = 4;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AGE_FIELD_NUMBER = 5;
    private int age_;
    /**
     * <code>optional int32 age = 5;</code>
     */
    public boolean hasAge() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 age = 5;</code>
     */
    public int getAge() {
      return age_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUuid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasBoid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasDestination()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, uuid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeSInt64(2, boid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, destination_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, name_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, age_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, uuid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeSInt64Size(2, boid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, destination_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, name_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, age_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO)) {
        return super.equals(obj);
      }
      com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO other = (com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO) obj;

      boolean result = true;
      result = result && (hasUuid() == other.hasUuid());
      if (hasUuid()) {
        result = result && getUuid()
            .equals(other.getUuid());
      }
      result = result && (hasBoid() == other.hasBoid());
      if (hasBoid()) {
        result = result && (getBoid()
            == other.getBoid());
      }
      result = result && (hasDestination() == other.hasDestination());
      if (hasDestination()) {
        result = result && getDestination()
            .equals(other.getDestination());
      }
      result = result && (hasName() == other.hasName());
      if (hasName()) {
        result = result && getName()
            .equals(other.getName());
      }
      result = result && (hasAge() == other.hasAge());
      if (hasAge()) {
        result = result && (getAge()
            == other.getAge());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasUuid()) {
        hash = (37 * hash) + UUID_FIELD_NUMBER;
        hash = (53 * hash) + getUuid().hashCode();
      }
      if (hasBoid()) {
        hash = (37 * hash) + BOID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getBoid());
      }
      if (hasDestination()) {
        hash = (37 * hash) + DESTINATION_FIELD_NUMBER;
        hash = (53 * hash) + getDestination().hashCode();
      }
      if (hasName()) {
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
      }
      if (hasAge()) {
        hash = (37 * hash) + AGE_FIELD_NUMBER;
        hash = (53 * hash) + getAge();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ExampleBO}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ExampleBO)
        com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBOOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.internal_static_ExampleBO_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.internal_static_ExampleBO_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.class, com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.Builder.class);
      }

      // Construct using com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        uuid_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        boid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        destination_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        age_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.internal_static_ExampleBO_descriptor;
      }

      public com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO getDefaultInstanceForType() {
        return com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.getDefaultInstance();
      }

      public com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO build() {
        com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO buildPartial() {
        com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO result = new com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uuid_ = uuid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.boid_ = boid_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.destination_ = destination_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.age_ = age_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO) {
          return mergeFrom((com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO other) {
        if (other == com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO.getDefaultInstance()) return this;
        if (other.hasUuid()) {
          bitField0_ |= 0x00000001;
          uuid_ = other.uuid_;
          onChanged();
        }
        if (other.hasBoid()) {
          setBoid(other.getBoid());
        }
        if (other.hasDestination()) {
          bitField0_ |= 0x00000004;
          destination_ = other.destination_;
          onChanged();
        }
        if (other.hasName()) {
          bitField0_ |= 0x00000008;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasAge()) {
          setAge(other.getAge());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUuid()) {
          return false;
        }
        if (!hasBoid()) {
          return false;
        }
        if (!hasDestination()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object uuid_ = "";
      /**
       * <code>required string uuid = 1;</code>
       */
      public boolean hasUuid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string uuid = 1;</code>
       */
      public java.lang.String getUuid() {
        java.lang.Object ref = uuid_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            uuid_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string uuid = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUuidBytes() {
        java.lang.Object ref = uuid_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          uuid_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string uuid = 1;</code>
       */
      public Builder setUuid(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        uuid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string uuid = 1;</code>
       */
      public Builder clearUuid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        uuid_ = getDefaultInstance().getUuid();
        onChanged();
        return this;
      }
      /**
       * <code>required string uuid = 1;</code>
       */
      public Builder setUuidBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        uuid_ = value;
        onChanged();
        return this;
      }

      private long boid_ ;
      /**
       * <code>required sint64 boid = 2;</code>
       */
      public boolean hasBoid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required sint64 boid = 2;</code>
       */
      public long getBoid() {
        return boid_;
      }
      /**
       * <code>required sint64 boid = 2;</code>
       */
      public Builder setBoid(long value) {
        bitField0_ |= 0x00000002;
        boid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required sint64 boid = 2;</code>
       */
      public Builder clearBoid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        boid_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object destination_ = "";
      /**
       * <code>required string destination = 3;</code>
       */
      public boolean hasDestination() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string destination = 3;</code>
       */
      public java.lang.String getDestination() {
        java.lang.Object ref = destination_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            destination_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string destination = 3;</code>
       */
      public com.google.protobuf.ByteString
          getDestinationBytes() {
        java.lang.Object ref = destination_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          destination_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string destination = 3;</code>
       */
      public Builder setDestination(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        destination_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string destination = 3;</code>
       */
      public Builder clearDestination() {
        bitField0_ = (bitField0_ & ~0x00000004);
        destination_ = getDefaultInstance().getDestination();
        onChanged();
        return this;
      }
      /**
       * <code>required string destination = 3;</code>
       */
      public Builder setDestinationBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        destination_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>optional string name = 4;</code>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string name = 4;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            name_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string name = 4;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string name = 4;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 4;</code>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000008);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 4;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        name_ = value;
        onChanged();
        return this;
      }

      private int age_ ;
      /**
       * <code>optional int32 age = 5;</code>
       */
      public boolean hasAge() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 age = 5;</code>
       */
      public int getAge() {
        return age_;
      }
      /**
       * <code>optional int32 age = 5;</code>
       */
      public Builder setAge(int value) {
        bitField0_ |= 0x00000010;
        age_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 age = 5;</code>
       */
      public Builder clearAge() {
        bitField0_ = (bitField0_ & ~0x00000010);
        age_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ExampleBO)
    }

    // @@protoc_insertion_point(class_scope:ExampleBO)
    private static final com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO();
    }

    public static com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<ExampleBO>
        PARSER = new com.google.protobuf.AbstractParser<ExampleBO>() {
      public ExampleBO parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ExampleBO(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ExampleBO> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ExampleBO> getParserForType() {
      return PARSER;
    }

    public com.purefun.fstp.example.bo.pro.ExampleBO_PRO.ExampleBO getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ExampleBO_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ExampleBO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017ExampleBO.proto\"W\n\tExampleBO\022\014\n\004uuid\030\001" +
      " \002(\t\022\014\n\004boid\030\002 \002(\022\022\023\n\013destination\030\003 \002(\t\022" +
      "\014\n\004name\030\004 \001(\t\022\013\n\003age\030\005 \001(\005B0\n\037com.purefu" +
      "n.fstp.example.bo.proB\rExampleBO_PRO"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ExampleBO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ExampleBO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ExampleBO_descriptor,
        new java.lang.String[] { "Uuid", "Boid", "Destination", "Name", "Age", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
