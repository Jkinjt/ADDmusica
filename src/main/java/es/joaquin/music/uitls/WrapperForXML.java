package es.joaquin.music.uitls;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="servers")
public class WrapperForXML {
	
	@XmlElement(name="server")
	
	
	
	
	
	public static void saveFile(ServerConnection sc) {
			
			try {
				JAXBContext j=JAXBContext.newInstance(ServerConnection.class);
				Marshaller m=j.createMarshaller();
				m.setProperty(m.JAXB_FORMATTED_OUTPUT,true);
				m.marshal(sc,new File("servers.xml"));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		public static ServerConnection loadFile() {
			ServerConnection result=null;
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(ServerConnection.class);
			    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			     
			    result = (ServerConnection) jaxbUnmarshaller.unmarshal(new File( "servers.xml" ));
			    
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
	

}
