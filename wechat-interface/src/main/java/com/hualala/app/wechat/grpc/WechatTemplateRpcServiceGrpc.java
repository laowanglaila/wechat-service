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
    comments = "Source: wechattemplaterpcservice.proto")
public class WechatTemplateRpcServiceGrpc {

  private WechatTemplateRpcServiceGrpc() {}

  public static final String SERVICE_NAME = "WechatTemplateRpcService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData,
      com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData> METHOD_SENT_WECHAT_TEMPLATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "WechatTemplateRpcService", "sentWechatTemplate"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WechatTemplateRpcServiceStub newStub(io.grpc.Channel channel) {
    return new WechatTemplateRpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WechatTemplateRpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WechatTemplateRpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static WechatTemplateRpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WechatTemplateRpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WechatTemplateRpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sentWechatTemplate(com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SENT_WECHAT_TEMPLATE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SENT_WECHAT_TEMPLATE,
            asyncUnaryCall(
              new MethodHandlers<
                com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData,
                com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData>(
                  this, METHODID_SENT_WECHAT_TEMPLATE)))
          .build();
    }
  }

  /**
   */
  public static final class WechatTemplateRpcServiceStub extends io.grpc.stub.AbstractStub<WechatTemplateRpcServiceStub> {
    private WechatTemplateRpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatTemplateRpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatTemplateRpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatTemplateRpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void sentWechatTemplate(com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SENT_WECHAT_TEMPLATE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WechatTemplateRpcServiceBlockingStub extends io.grpc.stub.AbstractStub<WechatTemplateRpcServiceBlockingStub> {
    private WechatTemplateRpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatTemplateRpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatTemplateRpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatTemplateRpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData sentWechatTemplate(com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SENT_WECHAT_TEMPLATE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WechatTemplateRpcServiceFutureStub extends io.grpc.stub.AbstractStub<WechatTemplateRpcServiceFutureStub> {
    private WechatTemplateRpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WechatTemplateRpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WechatTemplateRpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WechatTemplateRpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData> sentWechatTemplate(
        com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SENT_WECHAT_TEMPLATE, getCallOptions()), request);
    }
  }

  private static final int METHODID_SENT_WECHAT_TEMPLATE = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WechatTemplateRpcServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(WechatTemplateRpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SENT_WECHAT_TEMPLATE:
          serviceImpl.sentWechatTemplate((com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcReqData) request,
              (io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.WechatTemplateRpcData.WechatTemplateRpcResData>) responseObserver);
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
        METHOD_SENT_WECHAT_TEMPLATE);
  }

}
