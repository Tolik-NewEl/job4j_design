package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "smartphone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Smartphone {

    @XmlAttribute
    private boolean available;

    @XmlAttribute
    private int memsize;

    @XmlElementWrapper(name = "memorys")
    @XmlElement(name = "memory")
    private String[] memory;
    private Contact contact;

    public Smartphone(boolean available, int memsize, String[] memory, Contact contact) {
        this.available = available;
        this.memsize = memsize;
        this.memory = memory;
        this.contact = contact;
    }

    public Smartphone() {
    }

    @Override
    public String toString() {
        return "Smartphone{"
                + "available=" + available
                + ", memsize=" + memsize
                + ", memory=" + Arrays.toString(memory)
                + ", contact=" + contact + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Smartphone smartphone = new Smartphone(true, 64,
                new String[]{"external", "internal"}, new Contact("999-999-999"));

        JAXBContext context = JAXBContext.newInstance(Smartphone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(smartphone, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
        }
    }
}
