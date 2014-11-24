package com.epam.mentor.validation;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Aliaksandr_Shynkevich on 11/23/14
 */
public class RegExpValidator {

    private Pattern pattern;

    public RegExpValidator(String reqexp) {
        this.pattern = Pattern.compile(reqexp);
    }

    public boolean validate(String value, String field, List<String> error) {
        if (pattern.matcher(value).matches()) {
            return true;
        }
        error.add(String.format("%s : has incorrect format", field));
        return false;
    }
}
