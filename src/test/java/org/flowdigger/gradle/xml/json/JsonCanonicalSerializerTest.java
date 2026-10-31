<<<<<<< HEAD:src/test/java/org/flowdigger/gradle/xml/json/JsonCanonicalSerializerTest.java
/* (C)2026 */
package org.flowdigger.gradle.xml.json;
=======
package org.flowdigger.gradle.xml.json;

import java.io.StringWriter;
import org.flowdigger.xir.XirSerializer;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;
>>>>>>> 7a2c54f (refactor: migrate package prefix to org.flowdigger):src/test/java/name/jurgenei/gradle/xml/json/JsonCanonicalSerializerTest.java

import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import org.flowdigger.xir.XirSerializer;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

public class JsonCanonicalSerializerTest {

    @Test
    public void usesLocalNameWhenQNameMissingAndSkipsBlankText() throws Exception {
        StringWriter writer = new StringWriter();
        JsonCanonicalSerializer serializer = new JsonCanonicalSerializer(writer);
        AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "id", "", "CDATA", "42");

        serializer.startDocument();
        serializer.startElement("", "root", "", attrs);
        serializer.characters("   ".toCharArray(), 0, 3);
        serializer.startElement("", "child", "", new AttributesImpl());
        serializer.characters("value".toCharArray(), 0, 5);
        serializer.endElement("", "child", "");
        serializer.endElement("", "root", "");
        serializer.endDocument();

        String output = writer.toString();
        assertTrue(output.startsWith("{\"type\":\"element\""));
        assertTrue(output.contains("\"name\":\"root\""));
        assertTrue(output.contains("\"id\":\"42\""));
        assertTrue(output.contains("\"name\":\"child\""));
        assertTrue(output.contains("\"value\":\"value\""));
    }

    @Test
    public void writesBeautifiedOutputWhenRequested() throws Exception {
        StringWriter writer = new StringWriter();
        JsonCanonicalSerializer serializer =
                new JsonCanonicalSerializer(writer, XirSerializer.OutputFormat.BEAUTIFIED);

        serializer.startDocument();
        serializer.startElement("", "root", "root", new AttributesImpl());
        serializer.endElement("", "root", "root");
        serializer.endDocument();

        String output = writer.toString();
        assertTrue(output.contains(System.lineSeparator()));
        assertTrue(output.contains("\"type\" : \"element\""));
    }

    @Test
    public void fallsBackToCompactWhenFormatNull() throws Exception {
        StringWriter writer = new StringWriter();
        JsonCanonicalSerializer serializer = new JsonCanonicalSerializer(writer, null);

        serializer.startDocument();
        serializer.startElement("", "root", "root", new AttributesImpl());
        serializer.endElement("", "root", "root");
        serializer.endDocument();

        assertTrue(writer.toString().startsWith("{\"type\":\"element\""));
    }
}
