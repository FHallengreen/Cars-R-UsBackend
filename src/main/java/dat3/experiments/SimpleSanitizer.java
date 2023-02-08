package dat3.experiments;

public class SimpleSanitizer {

    public static String simpleSanitize(String s){

        return s.replace("<b>","").replace("</b>","");
//        return s.replaceAll("[</b>]","");
//        return s.replaceAll("<[^>]*>","");

        }



}
