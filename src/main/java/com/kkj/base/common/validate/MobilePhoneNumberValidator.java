package com.kkj.base.common.validate;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class MobilePhoneNumberValidator implements Predicate<String> {

    private static final Predicate<String> IS_MOBILE_PHONE_NUMBER_VALID =
            Pattern.compile(
                    "/^\\d{3}-\\d{3,4}-\\d{4}$/",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String mobilePhoneNumber) {
        return IS_MOBILE_PHONE_NUMBER_VALID.test(mobilePhoneNumber);
    }
}
