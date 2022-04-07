package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Jsonexample {
    public static void main(String[] args) throws Exception {
        final Smartphone smartphone = new Smartphone(true, 64,
                new String[] {"external", "internal"}, new Contact("999-999-999"));
        JAXBContext context = JAXBContext.newInstance(Smartphone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(smartphone, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Smartphone result = (Smartphone) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
