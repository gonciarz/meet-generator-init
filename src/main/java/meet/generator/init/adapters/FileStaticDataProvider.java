package meet.generator.init.adapters;

import lombok.Getter;
import meet.generator.init.config.Countries;
import meet.generator.init.config.FirstNames;
import meet.generator.init.config.LastNames;
import meet.generator.init.ports.StaticDataProvider;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Service
@Getter
public class FileStaticDataProvider implements StaticDataProvider {

    private final FirstNames firstNames;
    private final LastNames lastNames;
    private final Countries countries;

    public FileStaticDataProvider() {
        firstNames = loadYaml("data/first_names.yaml", FirstNames.class);
        lastNames = loadYaml("data/last_names.yaml", LastNames.class);
        countries = loadYaml("data/countries_cities.yaml", Countries.class);
    }

    private <T> T loadYaml(String resourcePath, Class<T> aClass) {
        Yaml yaml = new Yaml(new Constructor(aClass));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(resourcePath);
        return yaml.load(inputStream);
    }

}