package spring.DiContainer;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) {
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }

        for (BeanDefinition beanDefinition : beanDefinitionList) {
            if (beanDefinition.isLazyInit() == false && beanDefinition.isSingleton()) {

            }
        }
    }

    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("Bean id not defined: " + beanId);
        }
        return beanDefinitions.get(beanId);
    }

    @VisibleForTesting
    protected Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }

        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();
            if (args.isEmpty()) {
                bean = beanClass.newInstance();
                Class[] argClasses = new Class[args.size()];
                Object[] argObject = new Object[args.size()];
                for (int i = 0; i < args.size(); i++) {
                    BeanDefinition.ConstructorArg arg = args.get(i);
                    if (!arg.isRef()) {
                        argClasses[i] = arg.getType();
                        argObject[i] = arg.getArg();
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.getArg());
                        if (refBeanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.getArg());
                        }
                        argClasses[i] = Class.forName(refBeanDefinition.getClassName());
                        argObject[i] = createBean(refBeanDefinition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObject);
            }
        } catch (ClassNotFoundException  | InstantiationException | IllegalAccessException |InvocationTargetException | NoSuchMethodException e) {
            throw new BeanCreationException("", e);
        }

        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.put(beanDefinition.getId(), bean);
            return singletonObjects.get(beanDefinition.getId());
        }
        return bean;
    }
}
