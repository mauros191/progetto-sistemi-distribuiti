mvn protobuf:compile
mvn protobuf:compile-custom
cp ./target/generated-sources/protobuf/grpc-java/it/maurosaladino/sdp/proto/P2PServiceGrpc.java ./src/main/java/it/maurosaladino/sdp/proto
cp ./target/generated-sources/protobuf/grpc-java/it/maurosaladino/sdp/proto/TokenServiceGrpc.java ./src/main/java/it/maurosaladino/sdp/proto
cp ./target/generated-sources/protobuf/grpc-java/it/maurosaladino/sdp/proto/AnalystServiceGrpc.java ./src/main/java/it/maurosaladino/sdp/proto 
cp ./target/generated-sources/protobuf/java/it/maurosaladino/sdp/proto/P2PServiceOuterClass.java ./src/main/java/it/maurosaladino/sdp/proto
cp ./target/generated-sources/protobuf/java/it/maurosaladino/sdp/proto/TokenServiceOuterClass.java ./src/main/java/it/maurosaladino/sdp/proto
cp ./target/generated-sources/protobuf/java/it/maurosaladino/sdp/proto/AnalystServiceOuterClass.java ./src/main/java/it/maurosaladino/sdp/proto