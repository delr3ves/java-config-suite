package io.delr3ves.config.parser;

import com.google.inject.Guice;
import io.delr3ves.config.parser.dummy.DummyConfig;
import io.delr3ves.config.provider.exception.InvalidConfigException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class JacksonConfigParserTest {

    private ConfigParser parser;

    @Before
    public void setUp() throws Exception {
        parser = Guice.createInjector(
                new ParserGuiceModule()).getInstance(ConfigParser.class);
    }

    @Test
    public void parsedValidConfigJsonShouldPopulateProperObject() throws Exception {
        loadConfigByType("json", DummyConfig.class);
    }

    @Test
    public void parsedValidConfigShouldPopulateProperObject() throws Exception {
        loadConfigByType("config", DummyConfig.class);
    }

    @Test
    public void parsedValidConfigYmlShouldPopulateProperObject() throws Exception {
        loadConfigByType("yml", DummyConfig.class);
    }

    @Test
    public void parsedValidConfigYamlShouldPopulateProperObject() throws Exception {
        loadConfigByType("yaml", DummyConfig.class);
    }

    @Test
    public void parsedValidConfigXmlShouldPopulateProperObject() throws Exception {
        loadConfigByType("xml", DummyConfig.class);
    }

    @Test(expected = InvalidConfigException.class)
    public void parsedInValidConfigXmlShouldPopulateProperObject() throws Exception {
        loadConfigByType("xml", String.class);
    }

    private void loadConfigByType(String type, Class clazz) throws InvalidConfigException {
        InputStream stream = getClass().getResourceAsStream("/validConfig." + type);
        DummyConfig parsedConfig = (DummyConfig)parser.parse(type, stream, clazz);
        assertConfig(parsedConfig);
    }

    private void assertConfig(DummyConfig config) {
        MatcherAssert.assertThat(config.getAnyString(), CoreMatchers.equalTo("irrelevantString"));
        MatcherAssert.assertThat(config.getAnyList().size(), CoreMatchers.equalTo(2));
        MatcherAssert.assertThat(config.getAnyNumber(), CoreMatchers.equalTo(4));
        MatcherAssert.assertThat(config.getAnyBoolean(), CoreMatchers.equalTo(true));
    }
}
