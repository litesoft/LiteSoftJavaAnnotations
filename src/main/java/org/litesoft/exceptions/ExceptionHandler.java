package org.litesoft.exceptions;

@SuppressWarnings("unused")
public interface ExceptionHandler {
    void handle(Exception e);

    static ExceptionHandler propagate() {
        return e -> {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        };
    }

    static ExceptionHandler exitWithMessage() {
        return e -> {
            System.err.println("\n" + ("*".repeat(64)) + "\n" + e.getMessage());
            System.exit(1);
        };
    }
}
