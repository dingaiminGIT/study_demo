package architecture.configuration.customer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 注册 PropertySource
 * 在 Spring Boot 应用启动时注册自定义的 PropertySource
 */
public class CustomPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {


    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.getPropertySources().addLast(new CustomPropertySource("customPropertySource"));
    }
}
