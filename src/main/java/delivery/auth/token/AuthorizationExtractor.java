package delivery.auth.token;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class AuthorizationExtractor {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_TYPE = "bearer";
    private static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";
    private static final String DELIMITER = ",";

    private AuthorizationExtractor() {}

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (isBearerType(value)) {
                request.setAttribute(ACCESS_TOKEN_TYPE, BEARER_TYPE);
                return extractAuthHeader(value);
            }
        }

        return null;
    }

    private static String extractAuthHeader(String value) {
        String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
        return (authHeaderValue.contains(DELIMITER))
                ? authHeaderValue.split(DELIMITER)[0]
                : authHeaderValue;
    }

    private static boolean isBearerType(String value) {
        return value.toLowerCase().startsWith(BEARER_TYPE);
    }
}
