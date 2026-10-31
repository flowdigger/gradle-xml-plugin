<<<<<<< HEAD:src/main/java/org/flowdigger/gradle/xml/validation/XsdEngine.java
/* (C)2026 */
=======
>>>>>>> 7a2c54f (refactor: migrate package prefix to org.flowdigger):src/main/java/name/jurgenei/gradle/xml/validation/XsdEngine.java
package org.flowdigger.gradle.xml.validation;

/**
 * Validation engine options for XSD validation.
 */
public enum XsdEngine {
    /** Automatically choose Saxon schema-aware when available, otherwise JAXP. */
    AUTO,
    /** Force Saxon schema-aware validation (requires PE/EE). */
    SAXON,
    /** Force JAXP/Xerces validation. */
    JAXP
}
