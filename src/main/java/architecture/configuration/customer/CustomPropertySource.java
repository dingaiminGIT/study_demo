package architecture.configuration.customer;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * 实现一个自定义的 PropertySource，从第三方配置中心加载配置：
 *
 */
public class CustomPropertySource extends EnumerablePropertySource {

    public CustomPropertySource(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        // 返回所有的配置项名称
        return new String[0];
    }

    @Override
    public Object getProperty(String name) {
        // 从第三方配置中心获取配置值
        if ("my.config.value".equals(name)) {
            return "Hello from Custom Config";
        }
        return null;
    }
}
