package com.example.spring.metrics.gettingstarted.hello;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private HelloService helloService;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public HelloController(HelloService helloService)
    {
        this.helloService = helloService;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    @GetMapping(path = "/hello",
                produces = MediaType.APPLICATION_JSON_VALUE)
    // @Timed  // Required only, if 'management.metrics.web.server.request.autotime.enabled=false'
    public Hello get() throws Exception
    {
        return this.helloService.getHello();
    }

    @PostMapping(path = "/hello",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed  // Required only, if 'management.metrics.web.server.request.autotime.enabled=false'
    public ResponseEntity<Void> post(@RequestBody Hello hello)
    {
        this.helloService.postHello(hello);

        return ResponseEntity.noContent().build();
    }
}
