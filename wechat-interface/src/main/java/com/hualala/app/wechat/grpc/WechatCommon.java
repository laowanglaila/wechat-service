// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: wechatcommons.proto

package com.hualala.app.wechat.grpc;

public final class WechatCommon {
  private WechatCommon() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code WechatQRTypeEnum}
   */
  public enum WechatQRTypeEnum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>LOGIN = 0;</code>
     */
    LOGIN(0),
    /**
     * <code>INVOICE = 1;</code>
     */
    INVOICE(1),
    /**
     * <code>QUEUE = 2;</code>
     */
    QUEUE(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>LOGIN = 0;</code>
     */
    public static final int LOGIN_VALUE = 0;
    /**
     * <code>INVOICE = 1;</code>
     */
    public static final int INVOICE_VALUE = 1;
    /**
     * <code>QUEUE = 2;</code>
     */
    public static final int QUEUE_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static WechatQRTypeEnum valueOf(int value) {
      return forNumber(value);
    }

    public static WechatQRTypeEnum forNumber(int value) {
      switch (value) {
        case 0: return LOGIN;
        case 1: return INVOICE;
        case 2: return QUEUE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<WechatQRTypeEnum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        WechatQRTypeEnum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<WechatQRTypeEnum>() {
            public WechatQRTypeEnum findValueByNumber(int number) {
              return WechatQRTypeEnum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.hualala.app.wechat.grpc.WechatCommon.getDescriptor().getEnumTypes().get(0);
    }

    private static final WechatQRTypeEnum[] VALUES = values();

    public static WechatQRTypeEnum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private WechatQRTypeEnum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:WechatQRTypeEnum)
  }

  /**
   * Protobuf enum {@code MpTypeEnum}
   */
  public enum MpTypeEnum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DEFAULT = 0;</code>
     */
    DEFAULT(0),
    /**
     * <code>SUBSCRIPTIONS = 1;</code>
     */
    SUBSCRIPTIONS(1),
    /**
     * <code>SUBSCRIPTIONS_AUTH = 2;</code>
     */
    SUBSCRIPTIONS_AUTH(2),
    /**
     * <code>SERVICE = 3;</code>
     */
    SERVICE(3),
    /**
     * <code>SERVICE_AUTH = 4;</code>
     */
    SERVICE_AUTH(4),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>DEFAULT = 0;</code>
     */
    public static final int DEFAULT_VALUE = 0;
    /**
     * <code>SUBSCRIPTIONS = 1;</code>
     */
    public static final int SUBSCRIPTIONS_VALUE = 1;
    /**
     * <code>SUBSCRIPTIONS_AUTH = 2;</code>
     */
    public static final int SUBSCRIPTIONS_AUTH_VALUE = 2;
    /**
     * <code>SERVICE = 3;</code>
     */
    public static final int SERVICE_VALUE = 3;
    /**
     * <code>SERVICE_AUTH = 4;</code>
     */
    public static final int SERVICE_AUTH_VALUE = 4;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MpTypeEnum valueOf(int value) {
      return forNumber(value);
    }

    public static MpTypeEnum forNumber(int value) {
      switch (value) {
        case 0: return DEFAULT;
        case 1: return SUBSCRIPTIONS;
        case 2: return SUBSCRIPTIONS_AUTH;
        case 3: return SERVICE;
        case 4: return SERVICE_AUTH;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MpTypeEnum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        MpTypeEnum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MpTypeEnum>() {
            public MpTypeEnum findValueByNumber(int number) {
              return MpTypeEnum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.hualala.app.wechat.grpc.WechatCommon.getDescriptor().getEnumTypes().get(1);
    }

    private static final MpTypeEnum[] VALUES = values();

    public static MpTypeEnum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private MpTypeEnum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:MpTypeEnum)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023wechatcommons.proto*5\n\020WechatQRTypeEnu" +
      "m\022\t\n\005LOGIN\020\000\022\013\n\007INVOICE\020\001\022\t\n\005QUEUE\020\002*c\n\n" +
      "MpTypeEnum\022\013\n\007DEFAULT\020\000\022\021\n\rSUBSCRIPTIONS" +
      "\020\001\022\026\n\022SUBSCRIPTIONS_AUTH\020\002\022\013\n\007SERVICE\020\003\022" +
      "\020\n\014SERVICE_AUTH\020\004B+\n\033com.hualala.app.wec" +
      "hat.grpcB\014WechatCommonb\006proto3"
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
  }

  // @@protoc_insertion_point(outer_class_scope)
}
