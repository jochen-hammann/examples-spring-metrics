package com.example.spring.metrics.gettingstarted.hello;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HelloRepository
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private List<Hello> helloCollection;

    private Timer timerGetHello;
    private Timer timerPostHello;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public HelloRepository(MeterRegistry meterRegistry)
    {
        this.timerGetHello = meterRegistry.timer(HelloRepository.class.getName() + "_getHello");
        this.timerPostHello = meterRegistry.timer(HelloRepository.class.getName() + "_postHello");

        this.helloCollection = meterRegistry
                .gaugeCollectionSize(HelloRepository.class.getName() + "_helloCollection", Tags.empty(), new ArrayList<>());
    }

    // ============================== [Spring Beans] ==============================

    // -------------------- [Public Spring Beans] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public Hello getHello()
    {
        Timer.Sample sample = Timer.start();

        Hello hello = null;

        if (this.helloCollection.isEmpty())
            hello = new Hello("Hello, GET!");
        else
            hello = this.helloCollection.get(this.helloCollection.size() - 1);

        sample.stop(this.timerGetHello);

        return hello;
    }

    public void postHello(Hello hello)
    {
        Timer.Sample sample = Timer.start();

        this.helloCollection.add(hello);

        sample.stop(this.timerPostHello);
    }
}
