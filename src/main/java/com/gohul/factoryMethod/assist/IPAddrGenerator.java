package com.gohul.factoryMethod.assist;

import com.gohul.common.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class IPAddrGenerator implements Consumer<FluxSink<String>> {

    private static final Logger log = LoggerFactory.getLogger(IPAddrGenerator.class);

    private FluxSink<String> sink;


    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        log.info("accept() method invoked!");
            sink = stringFluxSink;
    }

    public void generate(){
        String ip = CommonUtils.ipAddress();
        log.info("Generated IP: {}", ip);
        sink.next(ip);
    }
}
