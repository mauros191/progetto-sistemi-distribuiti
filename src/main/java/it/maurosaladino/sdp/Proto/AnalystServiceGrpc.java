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
    comments = "Source: AnalystService.proto")
public final class AnalystServiceGrpc {

  private AnalystServiceGrpc() {}

  public static final String SERVICE_NAME = "it.maurosaladino.sdp.Proto.AnalystService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification,
      it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> getSendPushMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendPush",
      requestType = it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification.class,
      responseType = it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification,
      it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> getSendPushMethod() {
    io.grpc.MethodDescriptor<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification, it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> getSendPushMethod;
    if ((getSendPushMethod = AnalystServiceGrpc.getSendPushMethod) == null) {
      synchronized (AnalystServiceGrpc.class) {
        if ((getSendPushMethod = AnalystServiceGrpc.getSendPushMethod) == null) {
          AnalystServiceGrpc.getSendPushMethod = getSendPushMethod =
              io.grpc.MethodDescriptor.<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification, it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendPush"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AnalystServiceMethodDescriptorSupplier("sendPush"))
              .build();
        }
      }
    }
    return getSendPushMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AnalystServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalystServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalystServiceStub>() {
        @java.lang.Override
        public AnalystServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalystServiceStub(channel, callOptions);
        }
      };
    return AnalystServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AnalystServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalystServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalystServiceBlockingStub>() {
        @java.lang.Override
        public AnalystServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalystServiceBlockingStub(channel, callOptions);
        }
      };
    return AnalystServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AnalystServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnalystServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnalystServiceFutureStub>() {
        @java.lang.Override
        public AnalystServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnalystServiceFutureStub(channel, callOptions);
        }
      };
    return AnalystServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AnalystServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendPush(it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendPushMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendPushMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification,
                it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse>(
                  this, METHODID_SEND_PUSH)))
          .build();
    }
  }

  /**
   */
  public static final class AnalystServiceStub extends io.grpc.stub.AbstractAsyncStub<AnalystServiceStub> {
    private AnalystServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalystServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalystServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendPush(it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification request,
        io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendPushMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AnalystServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AnalystServiceBlockingStub> {
    private AnalystServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalystServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalystServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse sendPush(it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification request) {
      return blockingUnaryCall(
          getChannel(), getSendPushMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AnalystServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AnalystServiceFutureStub> {
    private AnalystServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnalystServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnalystServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse> sendPush(
        it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification request) {
      return futureUnaryCall(
          getChannel().newCall(getSendPushMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_PUSH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AnalystServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AnalystServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_PUSH:
          serviceImpl.sendPush((it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushNotification) request,
              (io.grpc.stub.StreamObserver<it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.PushResponse>) responseObserver);
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

  private static abstract class AnalystServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AnalystServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return it.maurosaladino.sdp.Proto.AnalystServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AnalystService");
    }
  }

  private static final class AnalystServiceFileDescriptorSupplier
      extends AnalystServiceBaseDescriptorSupplier {
    AnalystServiceFileDescriptorSupplier() {}
  }

  private static final class AnalystServiceMethodDescriptorSupplier
      extends AnalystServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AnalystServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AnalystServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AnalystServiceFileDescriptorSupplier())
              .addMethod(getSendPushMethod())
              .build();
        }
      }
    }
    return result;
  }
}
