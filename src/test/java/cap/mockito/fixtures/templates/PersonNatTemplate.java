package cap.mockito.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import cap.mockito.fixtures.apresentacao.Person;

public class PersonNatTemplate implements TemplateLoader {
    public static final String PATH_LOADERR = "cap.mockito.fixtures.templates";
    public static final String Natalia = "nat";
    public static final String ex = "ex";

    @Override
    public void load() {
        Fixture.of(Person.class).addTemplate(Natalia, new Rule() {{
            add("name", "Nathalia");
            add("age", 18);
        }});

        Fixture.of(Person.class).addTemplate("test").inherits(Natalia, new Rule() {{
            add("name", "test");
            add("phones", regex("000.111"));
        }});

        Fixture.of(Person.class).addTemplate(ex, new Rule() {{
            add("name", "ex");
            add("age", random(Integer.class, range(0, 100)));
        }});
    }

    public static Person gimmeNatalia() {
        return Fixture.from(Person.class).gimme(Natalia);
    }
}
