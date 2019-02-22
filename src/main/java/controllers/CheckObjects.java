package controllers;

public class CheckObjects {

    public static  <T> boolean isOneOfObjectsIsNull(T... objects){
        for(T obj : objects){
            if(obj == null)
                return true;
        }
        return false;
    }

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
