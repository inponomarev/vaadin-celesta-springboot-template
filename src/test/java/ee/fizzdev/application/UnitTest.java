package ee.fizzdev.application;

import ee.fizzdev.application.views.helloworld.HelloWorldView;
import org.junit.jupiter.api.Test;

public class UnitTest {
    @Test
    void megaTest() {
        new HelloWorldView().sayHelloInTerminal();
    }
}
