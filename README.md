# Spring Boot metrics

This example show different implementations of metrics.

### VisualVM

The example enables JMX metrics. Use the tool [VisualVM](https://visualvm.github.io/) to show the JMX metrics. The Spring Boot Actuator *JMX Metrics* will be exposed to the <code>metrics</code> JMX domain.

Please install the VisualVM plugin **VisualVM-MBeans** to see the respective **MBeans** tab. This tab will show the metrics.

If VisualVM and the Spring Boot App are running on the same host under the same user, and the JMX metrics are enabled in <code>application.properties</code>, nothing has to be configured. The Metrics will be displayed in the *MBeans* tab of VisualVM.

If you start VisualVM under a different user as the Spring Boot App or remote on a different host, the following settings has to be specified to the Java VM. The port may vary.

```
-Dcom.sun.management.jmxremote.port=8090
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
```

### See also

* [Spring Boot Actuator: Metrics](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics)
* [Baeldung: Metrics for your Spring REST API](https://www.baeldung.com/spring-rest-api-metrics)
* [Baeldung: Quick Guide to Micrometer](https://www.baeldung.com/micrometer)
