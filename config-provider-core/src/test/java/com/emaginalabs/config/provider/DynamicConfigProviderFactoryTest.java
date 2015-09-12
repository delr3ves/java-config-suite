package com.emaginalabs.config.provider;

import com.emaginalabs.config.exception.NoConfigProviderFoundException;
import com.emaginalabs.config.dummy.CanLoadConfigOnHelloExcactlyWithHighPriority;
import com.emaginalabs.config.dummy.CanLoadConfigOnHelloIncludedWithLowPriority;
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
