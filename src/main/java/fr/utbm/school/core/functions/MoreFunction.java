package fr.utbm.school.core.functions;

/**
 * author Neil Farmer / Ruiqing Zhu
 */
public class MoreFunction {

    /**
     * equivalent of nvl function in sql
     * @param nullableString
     * @param replaceString
     * @return
     */
    public static String nvl(String nullableString, String replaceString){
        return nullableString != null ? nullableString : replaceString;
    }

    /**
     * Return a string in function of a condition given
     *
     * @param stringTrue return if true
     * @param stringFalse return if false
     * @param condition to test
     * @return one of the string given
     */
    public static String conditionnalString(String stringTrue, String stringFalse, boolean condition){
        return condition ? stringTrue : stringFalse;
    }
}
