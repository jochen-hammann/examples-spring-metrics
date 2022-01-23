package com.example.spring.metrics.gettingstarted.hello;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private HelloRepository helloRepository;

    private Timer timerGetHello;
    private Timer timerPostHello;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public HelloService(HelloRepository helloRepository, MeterRegistry meterRegistry)
    {
        this.helloRepository = helloRepository;

        this.timerGetHello = meterRegistry.timer(HelloService.class.getName() + "_getHello");
        this.timerPostHello = meterRegistry.timer(HelloService.class.getName() + "_postHello");
    }

    // ============================== [Spring Beans] ==============================

    // -------------------- [Public Spring Beans] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public Hello getHello() throws Exception
    {
        return this.timerGetHello.recordCallable(() -> this.helloRepository.getHello());
    }

    public void postHello(Hello hello)
    {
        this.timerPostHello.record(() -> this.helloRepository.postHello(hello));
    }
}
