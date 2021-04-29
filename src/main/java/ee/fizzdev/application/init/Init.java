package ee.fizzdev.application.init;

import ee.fizzdev.application.dao.NamesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.curs.celesta.SystemCallContext;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class Init implements ApplicationListener<ApplicationReadyEvent> {
    private final NamesDao namesDao;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        IntStream.range(0, 3)
                .mapToObj(i -> String.format("name%d", i))
                .forEach(s -> namesDao.addPerson(s, new SystemCallContext()));
    }
}
