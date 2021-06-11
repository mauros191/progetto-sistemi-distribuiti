package it.maurosaladino.sdp.Proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.1)",
    comments = "Source: P2PService.proto")
public final class P2PServiceGrpc {

  private P2PServiceGrpc() {}

  public static final String SERVICE_NAME = "it.maurosaladino.sdp.Proto.P2PService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyNext",
      requestType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest.class,
      responseType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextMethod() {
    io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextMethod;
    if ((getNotifyNextMethod = P2PServiceGrpc.getNotifyNextMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getNotifyNextMethod = P2PServiceGrpc.getNotifyNextMethod) == null) {
          P2PServiceGrpc.getNotifyNextMethod = getNotifyNextMethod =
              io.grpc.MethodDescriptor.<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "notifyNext"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("notifyNext"))
              .build();
        }
      }
    }
    return getNotifyNextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyPrevious",
      requestType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest.class,
      responseType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousMethod() {
    io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousMethod;
    if ((getNotifyPreviousMethod = P2PServiceGrpc.getNotifyPreviousMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getNotifyPreviousMethod = P2PServiceGrpc.getNotifyPreviousMethod) == null) {
          P2PServiceGrpc.getNotifyPreviousMethod = getNotifyPreviousMethod =
              io.grpc.MethodDescriptor.<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "notifyPrevious"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("notifyPrevious"))
              .build();
        }
      }
    }
    return getNotifyPreviousMethod;
  }

  private static volatile io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyNextDelete",
      requestType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete.class,
      responseType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextDeleteMethod() {
    io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyNextDeleteMethod;
    if ((getNotifyNextDeleteMethod = P2PServiceGrpc.getNotifyNextDeleteMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getNotifyNextDeleteMethod = P2PServiceGrpc.getNotifyNextDeleteMethod) == null) {
          P2PServiceGrpc.getNotifyNextDeleteMethod = getNotifyNextDeleteMethod =
              io.grpc.MethodDescriptor.<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "notifyNextDelete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("notifyNextDelete"))
              .build();
        }
      }
    }
    return getNotifyNextDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyPreviousDelete",
      requestType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete.class,
      responseType = it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
      it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousDeleteMethod() {
    io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> getNotifyPreviousDeleteMethod;
    if ((getNotifyPreviousDeleteMethod = P2PServiceGrpc.getNotifyPreviousDeleteMethod) == null) {
      synchronized (P2PServiceGrpc.class) {
        if ((getNotifyPreviousDeleteMethod = P2PServiceGrpc.getNotifyPreviousDeleteMethod) == null) {
          P2PServiceGrpc.getNotifyPreviousDeleteMethod = getNotifyPreviousDeleteMethod =
              io.grpc.MethodDescriptor.<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete, it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "notifyPreviousDelete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new P2PServiceMethodDescriptorSupplier("notifyPreviousDelete"))
              .build();
        }
      }
    }
    return getNotifyPreviousDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static P2PServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceStub>() {
        @java.lang.Override
        public P2PServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceStub(channel, callOptions);
        }
      };
    return P2PServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static P2PServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceBlockingStub>() {
        @java.lang.Override
        public P2PServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceBlockingStub(channel, callOptions);
        }
      };
    return P2PServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static P2PServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PServiceFutureStub>() {
        @java.lang.Override
        public P2PServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PServiceFutureStub(channel, callOptions);
        }
      };
    return P2PServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class P2PServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void notifyNext(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyNextMethod(), responseObserver);
    }

    /**
     */
    public void notifyPrevious(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyPreviousMethod(), responseObserver);
    }

    /**
     */
    public void notifyNextDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyNextDeleteMethod(), responseObserver);
    }

    /**
     */
    public void notifyPreviousDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyPreviousDeleteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNotifyNextMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>(
                  this, METHODID_NOTIFY_NEXT)))
          .addMethod(
            getNotifyPreviousMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest,
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>(
                  this, METHODID_NOTIFY_PREVIOUS)))
          .addMethod(
            getNotifyNextDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>(
                  this, METHODID_NOTIFY_NEXT_DELETE)))
          .addMethod(
            getNotifyPreviousDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete,
                it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>(
                  this, METHODID_NOTIFY_PREVIOUS_DELETE)))
          .build();
    }
  }

  /**
   */
  public static final class P2PServiceStub extends io.grpc.stub.AbstractAsyncStub<P2PServiceStub> {
    private P2PServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceStub(channel, callOptions);
    }

    /**
     */
    public void notifyNext(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyNextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void notifyPrevious(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyPreviousMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void notifyNextDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyNextDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void notifyPreviousDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyPreviousDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class P2PServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<P2PServiceBlockingStub> {
    private P2PServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse notifyNext(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request) {
      return blockingUnaryCall(
          getChannel(), getNotifyNextMethod(), getCallOptions(), request);
    }

    /**
     */
    public it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse notifyPrevious(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request) {
      return blockingUnaryCall(
          getChannel(), getNotifyPreviousMethod(), getCallOptions(), request);
    }

    /**
     */
    public it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse notifyNextDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request) {
      return blockingUnaryCall(
          getChannel(), getNotifyNextDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse notifyPreviousDelete(it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request) {
      return blockingUnaryCall(
          getChannel(), getNotifyPreviousDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class P2PServiceFutureStub extends io.grpc.stub.AbstractFutureStub<P2PServiceFutureStub> {
    private P2PServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> notifyNext(
        it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyNextMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> notifyPrevious(
        it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyPreviousMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> notifyNextDelete(
        it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyNextDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse> notifyPreviousDelete(
        it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyPreviousDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NOTIFY_NEXT = 0;
  private static final int METHODID_NOTIFY_PREVIOUS = 1;
  private static final int METHODID_NOTIFY_NEXT_DELETE = 2;
  private static final int METHODID_NOTIFY_PREVIOUS_DELETE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final P2PServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(P2PServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NOTIFY_NEXT:
          serviceImpl.notifyNext((it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest) request,
              (io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>) responseObserver);
          break;
        case METHODID_NOTIFY_PREVIOUS:
          serviceImpl.notifyPrevious((it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest) request,
              (io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>) responseObserver);
          break;
        case METHODID_NOTIFY_NEXT_DELETE:
          serviceImpl.notifyNextDelete((it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete) request,
              (io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>) responseObserver);
          break;
        case METHODID_NOTIFY_PREVIOUS_DELETE:
          serviceImpl.notifyPreviousDelete((it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete) request,
              (io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse>) responseObserver);
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

  private static abstract class P2PServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    P2PServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.maurosaladino.sdp.Proto.P2PServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("P2PService");
    }
  }

  private static final class P2PServiceFileDescriptorSupplier
      extends P2PServiceBaseDescriptorSupplier {
    P2PServiceFileDescriptorSupplier() {}
  }

  private static final class P2PServiceMethodDescriptorSupplier
      extends P2PServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    P2PServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (P2PServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new P2PServiceFileDescriptorSupplier())
              .addMethod(getNotifyNextMethod())
              .addMethod(getNotifyPreviousMethod())
              .addMethod(getNotifyNextDeleteMethod())
              .addMethod(getNotifyPreviousDeleteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
