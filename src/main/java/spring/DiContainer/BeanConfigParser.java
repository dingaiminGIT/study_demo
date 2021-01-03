package spring.DiContainer;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {

    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContext);
}
