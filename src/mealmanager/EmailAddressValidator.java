/*
 */

package mealmanager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author SHAFIN
 */
public class EmailAddressValidator {
    
    /**
     * validate input email address
     * @param email
     * @return 
     */
    public boolean validation(String email){
        
        String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher regexMatcher = pattern.matcher(email);
        
        return regexMatcher.matches();
    }
}
