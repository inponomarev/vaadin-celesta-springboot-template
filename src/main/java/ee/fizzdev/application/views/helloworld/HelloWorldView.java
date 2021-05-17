package ee.fizzdev.application.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ee.fizzdev.application.dao.NamesDao;
import ee.fizzdev.application.dto.Person;
import ee.fizzdev.application.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curs.celesta.SystemCallContext;

import java.util.List;

@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./views/helloworld/hello-world-view.css")

public class HelloWorldView extends HorizontalLayout {

    private final NamesDao namesDao;

    private TextField name;
    private Div names;
    private Button addName;

    public HelloWorldView(@Autowired NamesDao namesDao) {
        this.namesDao = namesDao;

        names = new Div();
        names.setId("names");
        add(names);
        refreshNames();

        //
        name = new TextField("New name");
        name.setId("name_input");
        addName = new Button("Add name");
        addName.setId("addname_btn");
        add(name, addName);
        setVerticalComponentAlignment(Alignment.END, name, addName);
        addName.addClickListener(e -> {
            this.namesDao.addPerson(name.getValue(), new SystemCallContext());
            refreshNames();
        });
    }

    void refreshNames() {
        List<Person> people = namesDao.getNames(new SystemCallContext());
        names.removeAll();
        for (Person name : people) {
            Div div = new Div();
            div.setText(
                    String.format("%d-%s", name.getId(), name.getName()));
            names.add(div);
        }
    }

    public void sayHelloInTerminal() {
        System.out.println("hello, terminal");
    }

}
