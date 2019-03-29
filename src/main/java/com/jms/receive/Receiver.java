package com.jms.receive;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.jms.dm.SheepData;


@Component
public class Receiver {
	
	@JmsListener(destination = "databox", containerFactory = "myFactory")
	public void receiveMessage(SheepData data) {
        System.out.println("Received <" + data + ">");
        
        
        
        
    }
	
	
	
}
