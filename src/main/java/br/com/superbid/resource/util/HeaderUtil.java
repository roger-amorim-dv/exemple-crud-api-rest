package br.com.superbid.resource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "BlogApp";

    public static final String JUSTIFICATIVA_DELETED_FAILED = "deletedFailed";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-BlogApp-alert", message);
        headers.add("X-BlogApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-BlogApp-error", "error." + errorKey);
        headers.add("X-BlogApp-params", entityName);
        return headers;
    }

    public static HttpHeaders createFailureAlertDefault(String entityName, String errorKey, String param, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-BlogApp-error", APPLICATION_NAME+"."+entityName +"."+ errorKey);
        headers.add("X-BlogApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityAlert(String key, String param) {
        return createAlert(APPLICATION_NAME + "." + key, param);
    }

    public static HttpHeaders createEntityAlert(String key) {
        return createEntityAlert(key, "");
    }

    public static HttpHeaders sentEmailAlert(String param) {
        return createAlert(APPLICATION_NAME + ".messages.sentTo", param);
    }
}
