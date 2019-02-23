package controllers;

/**
 *      class for checking the values for nulll or empty
 */

public class CheckObjects {

    /**
     *  method return true if one of entries objects is null
     *
     * @param objects any objecs
     * @param <T>
     * @return true if one of objecs is null, in other way false
     */
    public static  <T> boolean isOneOfObjectsIsNull(T... objects){
        for(T obj : objects){
            if(obj == null)
                return true;
        }
        return false;
    }

    /**
     *  method return true if one of entries strings is empty
     *
     * @param strings
     * @return true if one of strings is empty, in other way false
     */

    public static boolean isOneOfStringsIsEmpty(String... strings){
        for(String str : strings){
            if(str.isEmpty())
                return true;
        }
        return false;
    }

    public static boolean isStringsNullOrEmpty(String... strings){
        if(isOneOfObjectsIsNull(strings) || isOneOfStringsIsEmpty(strings)){
            return true;
        }
        return false;
    }


}
