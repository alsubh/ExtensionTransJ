package umjdt.util;

import java.lang.annotation.*;

/**
 * Indicates that the annotated method 
 * will perform Read/Write operations.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited 
public @interface UpdateAccess { }
