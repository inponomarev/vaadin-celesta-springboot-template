package ee.fizzdev.application.dao;

import ee.fizzdev.application.dto.Person;
import ee.fizzdev.application.foo.NamesCursor;
import org.springframework.stereotype.Service;
import ru.curs.celesta.CallContext;
import ru.curs.celesta.transaction.CelestaTransaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class NamesDao {
    @CelestaTransaction
    public List<Person> getNames(CallContext ctx) {
        List<Person> result = new ArrayList<>();
        NamesCursor cursor = new NamesCursor(ctx);
        for (var c : cursor) {
            result.add(new Person(c.getId(), c.getName()));
        }
        return result;
    }

    @CelestaTransaction
    public void addPerson(String name, CallContext ctx) {
        NamesCursor cursor = new NamesCursor(ctx);
        cursor.setName(name);
        cursor.insert();
    }

}
