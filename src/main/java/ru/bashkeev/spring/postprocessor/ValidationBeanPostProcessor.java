package ru.bashkeev.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.bashkeev.spring.annotation.Validate;
import ru.bashkeev.spring.validation.ValidatorRule;

@Component
public class ValidationBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Validate.class)) {
            Validate annotation = beanClass.getAnnotation(Validate.class);
            String ruleBeanName = annotation.rule();

            try {
                ValidatorRule rule = context.getBean(ruleBeanName, ValidatorRule.class);
                rule.validate(bean);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка валидации бина '" + beanName + "' правилом '" + ruleBeanName + "': " + e.getMessage(), e);
            }
        }

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
