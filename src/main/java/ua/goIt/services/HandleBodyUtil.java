package ua.goIt.services;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class HandleBodyUtil {

    private static final Logger LOGGER = LogManager.getLogger(HandleBodyUtil.class);

    private static Gson jsonParser = new Gson();

    public static <T> Optional<T> getModelFromStream(InputStream in, Class<T> returnType) {
        try (InputStream inputStream = in;
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String jsonStr = scanner.nextLine();
            return Optional.of(jsonParser.fromJson(jsonStr, returnType));
        } catch (IOException e) {
            LOGGER.error("Request body getting error", e);
        }
        return Optional.empty();
    }

}
