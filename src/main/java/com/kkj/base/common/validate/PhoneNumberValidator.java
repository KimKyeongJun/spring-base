package com.kkj.base.common.validate;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements Predicate<String> {

    private static final Predicate<String> IS_PHONE_NUMBER_NUMBER_VALID =
            Pattern.compile(
                    "/^\\d{2,3}-\\d{3,4}-\\d{4}$/",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String phoneNumber) {
        return IS_PHONE_NUMBER_NUMBER_VALID.test(phoneNumber);
    }
}