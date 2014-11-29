package io.delr3ves.config.loader;

import io.delr3ves.config.provider.ConfigProviderFactory;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class AllClassLoaderIntegrationTest {

    private AllClassesLoader allClassesLoader;

    @Before
    public void setUp() throws Exception {
        allClassesLoader = new AllClassesLoader(ReflectionsFactory.getInstance());
    }

    @Test
    public void findAllConfigProviderFactoryImplementationsShouldOnlyFindOne() throws Exception {
        assertThat(allClassesLoader.findClassesOf(ConfigProviderFactory.class).size(),
                equalTo(1));
    }

}
