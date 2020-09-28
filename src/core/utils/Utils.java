package core.utils;

import core.constant.Constants;
import core.exception.CustomException;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Utils {

    private static Properties props = null;

    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static String format(String msgFormat, String... args) {
        return MessageFormat.format(msgFormat, args);
    }

    public static void loadStyleResource(Scene scene) {
        try {
            URL url = Utils.class.getClassLoader().getResource("style.css");
            scene.getStylesheets().add(url.toExternalForm());
        } catch (Exception e) {
            // ignore. failed to load css style
        }
    }

    public static void loadResource() {

        // only load if not null
        if (props != null) {
            return;
        }

        try (InputStream input = Utils.class.getClassLoader()
                .getResourceAsStream(Constants.CONFIG_FILE_NAME)) {

            props = new Properties();
            props.load(input);

            format("Finished loading properties file {0}", Constants.CONFIG_FILE_NAME);

        } catch (IOException | NullPointerException ex) {
            format("Error loading properties file {0}", Constants.CONFIG_FILE_NAME);
        }

    }

    public static String getTextProp(String key) throws CustomException {

        if (props == null) {
            throw new CustomException("Empty resource file!");
        }

        return props.getProperty(key);
    }

    public static String getTextPropSilently(String key) {
        try {
            return getTextProp(key);
        } catch (CustomException e) {
            //ignore
        }
        return Constants.EMPTY;
    }

    public static int getIntProp(String key) throws CustomException {
        try {
            return Integer.parseInt(getTextProp(key));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    public static void checkAndPrint(boolean in) {
        System.out.println(format("{0} to the database!",
                in ? "Successfully inserted" : "Failed to insert"));
    }

    public static int getNumber(Scanner sc) {
        String input = getText(sc);
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            System.out.print(Utils.format(
                    "{0} is not number. Please enter only number: ", input));
            return getNumber(sc);
        }
    }

    public static String getText(Scanner sc) {
        return sc.nextLine();
    }

}
