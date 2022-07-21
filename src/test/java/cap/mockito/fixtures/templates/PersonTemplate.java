package cap.mockito.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import cap.mockito.fixtures.apresentacao.Person;

public class PersonTemplate implements TemplateLoader {
    public static final String PATH_LOADER = "cap.mockito.fixtures.templates";
    public static final String ANDREOLI = "andreoli";
    public static final String XPTO = "xpto";

    @Override
    public void load() {
        Fixture.of(Person.class).addTemplate(ANDREOLI, new Rule() {{
            add("name", "Andreoli");
            add("age", 18);
        }});

        Fixture.of(Person.class).addTemplate("test").inherits(ANDREOLI, new Rule() {{
            add("name", "test");
            add("phones", regex("(\\d{2})-(\\d{4})-(\\d{4})"));
        }});

        Fixture.of(Person.class).addTemplate(XPTO, new Rule() {{
            add("name", "xpto");
            add("age", random(Integer.class, range(0, 100)));
        }});
    }

    public static Person gimmeAndreoli() {
        return Fixture.from(Person.class).gimme(ANDREOLI);
    }
}
