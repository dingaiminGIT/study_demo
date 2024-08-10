# 自定义配置源
## 实现 PropertySource

## 注册 PropertySource

## 配置 Spring Boot 应用
在 src/main/resources/META-INF/spring.factories 文件中添加以下内容，以便 Spring Boot 在启动时加载自定义的 PropertySource：

org.springframework.context.ApplicationContextInitializer=\
architecture.configuration.customer.CustomPropertySourceInitializer
## 使用 @Value 注解