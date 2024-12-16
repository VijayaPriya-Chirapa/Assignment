package com.Application1.in.DTO;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;


public class JAXBConversion {
	public static void main(String[] args) throws Exception {
        // Create and populate the POJO
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("priya");
        user.setEmail("priya@gamil.com");
 
        // Create MOXy-specific JAXB Context
        Map<String, Object> properties = new HashMap<>();
        properties.put("eclipselink.media-type", "application/json");
        properties.put("eclipselink.json.include-root", false);
        JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{UserDTO.class}, properties);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
        // Convert POJO to JSON
        marshaller.marshal(user, System.out);
    }
}
