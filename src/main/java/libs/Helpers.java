package libs;

import javafx.css.Styleable;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.Normalizer;
import java.time.format.DateTimeFormatter;

/**
 * @author Robin
 * @date 14/06/2017
 */
public class Helpers
{
    public static String camelCase (String s)
    {
        String res = "";

        String[] split = s.split("_");

        res += split[0];
        for (int i = 1; i < split.length; i++) {
            res += split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
        }

        return res;
    }

    public static String snakeCase (String s)
    {
        if (s.isEmpty()) {
            return "";
        }

        String res = s.substring(0, 1).toLowerCase();

        for (int i = 1; i < s.length(); i++) {
            String substr      = s.substring(i, i + 1);
            String substrLower = substr.toLowerCase();

            if (!substr.equals(substrLower)) {
                res += "_";
            }

            res += substrLower;
        }

        return res;
    }

    public static String plural (String str)
    {
        if (str.endsWith("y")) {
            str = str.substring(0, str.length() - 1) + "ies";
        }
        else {
            str += "s";
        }

        return str;
    }

    public static String getManagerOfModel (String modelName) // path/of/the/class
    {
        String modelsPackage  = modelName.substring(0, modelName.lastIndexOf('.'));
        String modelClassName = modelName.substring(modelName.lastIndexOf('.') + 1);

        modelClassName = plural(modelClassName);
        String managerClassname = modelClassName + "Manager";

        String managerPath = modelsPackage + ".managers." + managerClassname;

        return managerPath;
    }

    public static String getAddingMethodName (String str)
    {
        str = "add_" + str;
        str = camelCase(str);

        if (str.endsWith("ies")) {
            String beg = str.split("ies")[0];
            return beg + "y";
        }

        return str.substring(0, str.length() - 1);
    }

    public static void printListOfCssProperties (Styleable styleable)
    {
        styleable.getCssMetaData().forEach(cssMetaData -> System.out.println(cssMetaData.getProperty()));
    }

    public static void stop ()
    {
        System.exit(-1);
    }

    public static String stripAccents (String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static String getDecodedAbsolutePath (String path) throws UnsupportedEncodingException, URISyntaxException
    {
        URL resource = Helpers.class.getResource(path);
        path = resource.toString();
        path = new File(new URI(path)).toString();
        return path;
    }

    public static DateTimeFormatter getDateFormatter ()
    {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static DateTimeFormatter getTimeFormatter ()
    {
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    public static DateTimeFormatter getDatetimeFormatter ()
    {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}