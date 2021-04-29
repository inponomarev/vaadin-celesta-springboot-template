package ee.fizzdev.application;

import ee.fizzdev.application.dao.NamesDao;
import ee.fizzdev.application.views.helloworld.HelloWorldView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnitTest {
    @Test
    void megaTest() {
        new HelloWorldView(Mockito.mock(NamesDao.class))
                .sayHelloInTerminal();
    }
}
