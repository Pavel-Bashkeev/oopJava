package ru.bashkeev.spring.validation;

public interface ValidatorRule {
    void validate(Object obj) throws Exception;
}