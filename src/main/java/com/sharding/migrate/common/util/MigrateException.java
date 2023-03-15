package com.sharding.migrate.common.util;


import java.io.PrintWriter;
import java.io.StringWriter;

public class MigrateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public MigrateException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private MigrateException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static MigrateException asMigrateException(ErrorCode errorCode, String message) {
        return new MigrateException(errorCode, message);
    }

    public static MigrateException asMigrateException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof MigrateException) {
            return (MigrateException) cause;
        }
        return new MigrateException(errorCode, message, cause);
    }

    public static MigrateException asMigrateException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof MigrateException) {
            return (MigrateException) cause;
        }
        return new MigrateException(errorCode, getMessage(cause), cause);
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            StringWriter str = new StringWriter();
            PrintWriter pw = new PrintWriter(str);
            ((Throwable) obj).printStackTrace(pw);
            return str.toString();
            // return ((Throwable) obj).getMessage();
        } else {
            return obj.toString();
        }
    }
}
