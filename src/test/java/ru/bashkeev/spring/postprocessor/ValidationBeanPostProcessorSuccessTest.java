package ru.bashkeev.spring.postprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bashkeev.spring.annotation.Validate;
import ru.bashkeev.spring.validation.ValidatorRule;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationBeanPostProcessorSuccessTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testValidationWithRuleName_Success() {
        ValidBean validBean = context.getBean(ValidBean.class);

        assertNotNull(validBean);
        assertEquals("valid", validBean.getData());
    }

    @Test
    void testBeanWithoutValidation_ShouldCreateNormally() {
        Object simpleBean = context.getBean("simpleBean", Object.class);

        assertNotNull(simpleBean);
    }

    @Configuration
    @Import(ValidationBeanPostProcessor.class)
    static class TestConfig {

        @Bean
        public ValidatorRule notEmptyRule() {
            return obj -> {
                if (obj instanceof ValidBean) {
                    ValidBean bean = (ValidBean) obj;
                    if (bean.getData() == null || bean.getData().trim().isEmpty()) {
                        throw new Exception("Data cannot be empty");
                    }
                }
            };
        }

        @Bean
        public ValidBean validBean() {
            return new ValidBean();
        }

        @Bean
        public Object simpleBean() {
            return new Object();
        }
    }

    @Validate(rule = "notEmptyRule")
    static class ValidBean {
        private String data = "valid";

        public String getData() {
            return data;
        }
    }
}