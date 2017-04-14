package com.hualala.app.wechat.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: wechatqrcoderpcserivce.proto")
public class WechatQRCodeRpcSerivceGrpc {

  private WechatQRCodeRpcSerivceGrpc() {}

  public static final String SERVICE_NAME = "WechatQRCodeRpcSerivce";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq,
      com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes> METHOD_CREATE_QRCODE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "WechatQRCodeRpcSerivce", "createQRCode"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WechatQRCodeRpcSerivceStub newStub(io.grpc.Channel channel) {
    return new WechatQRCodeRpcSerivceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WechatQRCodeRpcSerivceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WechatQRCodeRpcSerivceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static WechatQRCodeRpcSerivceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WechatQRCodeRpcSerivceFutureStub(channel);
  }

  /**
   */
  public static abstract class WechatQRCodeRpcSerivceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createQRCode(com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_QRCODE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CREATE_QRCODE,
            asyncUnaryCall(
              new MethodHandlers<
                com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq,
                com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes>(
                  this, METHODID_CREATE_QRCODE)))
          .build();
    }
  }

  /**
   */
  public static final class WechatQRCodeRpcSerivceStub extends io.grpc.stub.AbstractStub<WechatQRCodeRpcSerivceStub> {
    private WechatQRCodeRpcSerivceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatQRCodeRpcSerivceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatQRCodeRpcSerivceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatQRCodeRpcSerivceStub(channel, callOptions);
    }

    /**
     */
    public void createQRCode(com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_QRCODE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WechatQRCodeRpcSerivceBlockingStub extends io.grpc.stub.AbstractStub<WechatQRCodeRpcSerivceBlockingStub> {
    private WechatQRCodeRpcSerivceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatQRCodeRpcSerivceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatQRCodeRpcSerivceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatQRCodeRpcSerivceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes createQRCode(com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_QRCODE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WechatQRCodeRpcSerivceFutureStub extends io.grpc.stub.AbstractStub<WechatQRCodeRpcSerivceFutureStub> {
    private WechatQRCodeRpcSerivceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatQRCodeRpcSerivceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatQRCodeRpcSerivceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatQRCodeRpcSerivceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes> createQRCode(
        com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_QRCODE, getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_QRCODE = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WechatQRCodeRpcSerivceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(WechatQRCodeRpcSerivceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_QRCODE:
          serviceImpl.createQRCode((com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeReq) request,
              (io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatQRCodeRpcSerivceData.WechatQRCodeRes>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CREATE_QRCODE);
  }

}
