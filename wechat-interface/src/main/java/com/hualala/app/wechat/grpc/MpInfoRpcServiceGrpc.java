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
    comments = "Source: mpinforpcservice.proto")
public class MpInfoRpcServiceGrpc {

  private MpInfoRpcServiceGrpc() {}

  public static final String SERVICE_NAME = "MpInfoRpcService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData,
      com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData> METHOD_QUERY_MP_INFO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MpInfoRpcService", "queryMpInfo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MpInfoRpcServiceStub newStub(io.grpc.Channel channel) {
    return new MpInfoRpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MpInfoRpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MpInfoRpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MpInfoRpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MpInfoRpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MpInfoRpcServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *获取公众信息
     * </pre>
     */
    public void queryMpInfo(com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_QUERY_MP_INFO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_QUERY_MP_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData,
                com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData>(
                  this, METHODID_QUERY_MP_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class MpInfoRpcServiceStub extends io.grpc.stub.AbstractStub<MpInfoRpcServiceStub> {
    private MpInfoRpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MpInfoRpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MpInfoRpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MpInfoRpcServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取公众信息
     * </pre>
     */
    public void queryMpInfo(com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_QUERY_MP_INFO, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MpInfoRpcServiceBlockingStub extends io.grpc.stub.AbstractStub<MpInfoRpcServiceBlockingStub> {
    private MpInfoRpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MpInfoRpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MpInfoRpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MpInfoRpcServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取公众信息
     * </pre>
     */
    public com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData queryMpInfo(com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData request) {
      return blockingUnaryCall(
          getChannel(), METHOD_QUERY_MP_INFO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MpInfoRpcServiceFutureStub extends io.grpc.stub.AbstractStub<MpInfoRpcServiceFutureStub> {
    private MpInfoRpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MpInfoRpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MpInfoRpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MpInfoRpcServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取公众信息
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData> queryMpInfo(
        com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_QUERY_MP_INFO, getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_MP_INFO = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MpInfoRpcServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(MpInfoRpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_MP_INFO:
          serviceImpl.queryMpInfo((com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryReqData) request,
              (io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MpInfoRpcData.MpInfoQueryResData>) responseObserver);
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
        METHOD_QUERY_MP_INFO);
  }

}
