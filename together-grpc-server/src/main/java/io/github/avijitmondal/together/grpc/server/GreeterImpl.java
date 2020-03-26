package io.github.avijitmondal.together.grpc.server;

import io.github.avijitmondal.together.grpc.GreeterGrpc;
import io.github.avijitmondal.together.grpc.HelloReply;
import io.github.avijitmondal.together.grpc.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    private static final Logger logger = Logger.getLogger(GreeterImpl.class.getName());

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        logger.info("hello " + req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
