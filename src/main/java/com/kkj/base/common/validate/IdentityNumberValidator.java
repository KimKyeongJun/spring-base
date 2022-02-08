package com.kkj.base.common.validate;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class IdentityNumberValidator implements Predicate<String> {

    private static final Predicate<String> IS_IDENTITY_NUMBER_VALID =
            Pattern.compile(
                    "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String identityNumber) {
        return IS_IDENTITY_NUMBER_VALID.test(identityNumber);
    }
}
