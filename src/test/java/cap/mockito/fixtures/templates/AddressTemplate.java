package cap.mockito.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import cap.mockito.fixtures.exercises.domain.Address;

public class AddressTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Address.class).addTemplate("valido", new Rule(){{
            add("street", random("Paulista Avenue", "Ibirapuera Avenue"));
            add("city", "São Paulo");
            add("state", "${city}");
            add("country", "Brasil");
        }});
    }
}
