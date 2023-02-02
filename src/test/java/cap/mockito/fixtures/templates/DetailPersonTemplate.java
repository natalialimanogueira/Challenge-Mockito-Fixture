package cap.mockito.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;

public class DetailPersonTemplate implements TemplateLoader {
    public static final String PATH_LOADER = "cap.mockito.fixtures.templates";
    public static final String ANDREOLI = "andreoli";

    public static final String Natalia = "nat";

    public static final String XPTO = "xpto";

    @Override
    public void load() {
        Fixture.of(DetailedPerson.class).addTemplate(ANDREOLI, new Rule() {{
            add("name", "Andreoli");
            add("age", 18);
        }});

        Fixture.of(DetailedPerson.class).addTemplate("test").inherits(ANDREOLI, new Rule() {{
            add("name", "test");
            add("phones", regex("[(]\\d{2}[)] (9\\d{4})-(\\d{4})"));
        }});

        Fixture.of(DetailedPerson.class).addTemplate(XPTO, new Rule() {{
            add("name", "xpto");
            add("age", random(Integer.class, range(0, 100)));
        }});

        Fixture.of(DetailedPerson.class).addTemplate(Natalia, new Rule() {{
            add("name", "Nathalia");
            add("age", 16);
        }});
    }

    public static DetailedPerson gimmeAndreoli() {
        return Fixture.from(DetailedPerson.class).gimme(ANDREOLI);
    }

    public static DetailedPerson gimmeNatalia() {
        return Fixture.from(DetailedPerson.class).gimme(Natalia);
    }
}
