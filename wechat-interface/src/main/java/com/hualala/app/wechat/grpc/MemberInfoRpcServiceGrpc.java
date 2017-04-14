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
    comments = "Source: memberinforpcservice.proto")
public class MemberInfoRpcServiceGrpc {

  private MemberInfoRpcServiceGrpc() {}

  public static final String SERVICE_NAME = "MemberInfoRpcService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData,
      com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData> METHOD_QUERY_MEMBER_INFO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MemberInfoRpcService", "queryMemberInfo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MemberInfoRpcServiceStub newStub(io.grpc.Channel channel) {
    return new MemberInfoRpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MemberInfoRpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MemberInfoRpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MemberInfoRpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MemberInfoRpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MemberInfoRpcServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *获取单条会员信息
     * </pre>
     */
    public void queryMemberInfo(com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_QUERY_MEMBER_INFO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_QUERY_MEMBER_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData,
                com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData>(
                  this, METHODID_QUERY_MEMBER_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class MemberInfoRpcServiceStub extends io.grpc.stub.AbstractStub<MemberInfoRpcServiceStub> {
    private MemberInfoRpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberInfoRpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberInfoRpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberInfoRpcServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取单条会员信息
     * </pre>
     */
    public void queryMemberInfo(com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData request,
        io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_QUERY_MEMBER_INFO, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MemberInfoRpcServiceBlockingStub extends io.grpc.stub.AbstractStub<MemberInfoRpcServiceBlockingStub> {
    private MemberInfoRpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberInfoRpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberInfoRpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberInfoRpcServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取单条会员信息
     * </pre>
     */
    public com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData queryMemberInfo(com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData request) {
      return blockingUnaryCall(
          getChannel(), METHOD_QUERY_MEMBER_INFO, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MemberInfoRpcServiceFutureStub extends io.grpc.stub.AbstractStub<MemberInfoRpcServiceFutureStub> {
    private MemberInfoRpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberInfoRpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberInfoRpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberInfoRpcServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *获取单条会员信息
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData> queryMemberInfo(
        com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_QUERY_MEMBER_INFO, getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_MEMBER_INFO = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MemberInfoRpcServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(MemberInfoRpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_MEMBER_INFO:
          serviceImpl.queryMemberInfo((com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoQueryReqData) request,
              (io.grpc.stub.StreamObserver<com.hualala.app.wechat.grpc.MemberInfoRpcData.MemberInfoResData>) responseObserver);
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
        METHOD_QUERY_MEMBER_INFO);
  }

}
