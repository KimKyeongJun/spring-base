package com.kkj.base.common.validate;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class CreditCardValidator implements Predicate<String> {

    private static final Predicate<String> IS_CREDIT_CARD_VALID =
            Pattern.compile(
                    "(5[1-5]\\d{14})|(4\\d{12})(\\d{3}?)|3[47]\\d{13}|(6011\\d{12})",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String creditCardNo) {
        return IS_CREDIT_CARD_VALID.test(creditCardNo);
    }
}
