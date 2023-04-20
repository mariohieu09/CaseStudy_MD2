package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final String CHOICE_VALIDATE = "^[123]$";
    private static final String USER_VALIDATE = "^[123450]$";

    private static final String CILENT_VALIDATE = "^[1230]$";
    private static final String LIST_SELLER_VALIDATE = "^[12340]$";
    private static final String LIST_SORTING_VALIDATE = "^[12]$";
    public static boolean choiceValidate(String choice){
        Pattern pattern = Pattern.compile(CHOICE_VALIDATE);
        boolean isMatch = pattern.matcher(choice).matches();
        return isMatch;
    }
    public static boolean UserValidate(String choice){
        Pattern pattern = Pattern.compile(USER_VALIDATE);
        boolean isMatch = pattern.matcher(choice).matches();
        return isMatch;
    }
    public static boolean ListSellerValidate(String choice){
        Pattern pattern = Pattern.compile(LIST_SELLER_VALIDATE);
        boolean isMatch = pattern.matcher(choice).matches();
        return isMatch;
    }
    public static boolean ListSortingValidate(String choice){
        Pattern pattern = Pattern.compile(LIST_SORTING_VALIDATE);
        boolean isMatch = pattern.matcher(choice).matches();
        return isMatch;
    }
    public static  boolean CilentValidate(String choice){
        Pattern pattern = Pattern.compile(CILENT_VALIDATE);
        boolean isMatch = pattern.matcher(choice).matches();
        return isMatch;
    }

}
