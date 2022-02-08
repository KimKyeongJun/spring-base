package com.kkj.base.common.validate;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Predicate<String> {

    private static final Predicate<String> IS_PASSWORD_NUMBER_VALID =
            Pattern.compile(
                    "/^[A-Za-z0-9]{6,12}$/",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String password) {
        return IS_PASSWORD_NUMBER_VALID.test(password);
    }
}