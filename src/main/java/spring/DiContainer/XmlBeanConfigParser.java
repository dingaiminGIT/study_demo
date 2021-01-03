package spring.DiContainer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = null;
        // TODO
        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContext) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // TODO
        return beanDefinitions;
    }
}
