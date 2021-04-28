package ee.fizzdev.application.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ee.fizzdev.application.views.main.MainView;

@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./views/helloworld/hello-world-view.css")
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private TextField name2;
    private Button sayHello;

    public HelloWorldView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        name2 = new TextField("Output");
        name.setId("name_input");
        sayHello = new Button("Say hello!!");
        sayHello.setId("hellobtn");
        var btn = new Button("AnotherButton 2");
        btn.setId("anotherBtn");
        add(name, sayHello, btn, name2);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        name.addInputListener(e->{
            //System.out.println(name.getValue());
            name2.setValue(name.getValue() + "!");
        });


        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue(), 30_000,
                    Notification.Position.MIDDLE).setId("hello_note");
        });
    }

    public void sayHelloInTerminal(){
        System.out.println("hello, terminal");
    }

}
