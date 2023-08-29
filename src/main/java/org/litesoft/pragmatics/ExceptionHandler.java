package org.litesoft.pragmatics;

@SuppressWarnings("unused")
public interface ExceptionHandler {
    void handle(Exception e);

    static ExceptionHandler exitWithMessage() {
        return Exceptions::exitWithMessage;
    }

    static ExceptionHandler exitWithStacktrace() {
        return Exceptions::exitWithStacktrace;
    }

    static ExceptionHandler propagate() {
        return Exceptions::propagate;
    }
}
