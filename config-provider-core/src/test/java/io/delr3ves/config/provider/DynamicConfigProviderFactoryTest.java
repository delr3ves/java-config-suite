package io.delr3ves.config.provider;

import io.delr3ves.config.dummy.CanLoadConfigOnHelloExcactlyWithHighPriority;
import io.delr3ves.config.dummy.CanLoadConfigOnHelloIncludedWithLowPriority;
import io.delr3ves.config.exception.NoConfigProviderFoundException;
import io.delr3ves.config.provider.ConfigProvider;
import io.delr3ves.config.provider.ConfigProviderComparator;
import io.delr3ves.config.provider.ConfigProviderFactory;
import io.delr3ves.config.provider.DynamicConfigProviderFactory;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class DynamicConfigProviderFactoryTest {

    public static final String NON_PROCESSABLE_ID = "NonProcessableId";
    public static final String PROCESSABLE_ID_FOR_BOTH_PROVIDERS = "Hello";
    private ConfigProviderFactory configProviderFactory;

    @Before
    public void setUp() throws Exception {
        configProviderFactory = new DynamicConfigProviderFactory(
                Arrays.asList(
                        new CanLoadConfigOnHelloExcactlyWithHighPriority(),
                        new CanLoadConfigOnHelloIncludedWithLowPriority()),
                new ConfigProviderComparator()
        );
    }

    @Test(expected = NoConfigProviderFoundException.class)
    public void whenNoProvidersFoundShouldThrowError() throws Exception{
        configProviderFactory.getInstance(NON_PROCESSABLE_ID);
    }

    @Test
    public void whenOnlyOneProviderIsFoundShouldReturnIt() throws Exception{
        String configId = PROCESSABLE_ID_FOR_BOTH_PROVIDERS  + "irrelevantString";
        ConfigProvider configProvider = configProviderFactory.getInstance(configId);
        TestCase.assertTrue(configProvider instanceof CanLoadConfigOnHelloIncludedWithLowPriority);
    }

    @Test
    public void whenOnlyTwoProvidersAreFoundShouldReturnTheOneWithHighestPriofiry() throws Exception{
        ConfigProvider configProvider = configProviderFactory.getInstance(PROCESSABLE_ID_FOR_BOTH_PROVIDERS);
        TestCase.assertTrue(configProvider instanceof CanLoadConfigOnHelloExcactlyWithHighPriority);
    }

}
