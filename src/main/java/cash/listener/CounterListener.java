package cash.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cash.service.CounterService;

@WebListener
public class CounterListener implements HttpSessionListener {
	private CounterService counterService;

    public void sessionCreated(HttpSessionEvent se)  { 
    	// 현재 접속자 수 +1 -> application.attribute
    	ServletContext application = se.getSession().getServletContext();
    	int currentCounter = 0;
    	if(application.getAttribute("currentCounter") != null) {
    		currentCounter = (Integer)(application.getAttribute("currentCounter"));
    	}		
    	application.setAttribute("currentCounter", currentCounter+1);
    	
    	// 오늘 접속자 수 +1 -> DB
    	this.counterService = new CounterService();
    	// 오늘 접속자 수 
    	int counter = counterService.getCounter();
    	if(counter == 0) {
    		counterService.addCounter();
    	}else {
    		counterService.modifyCounter();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// 현재 접속자 수 -1 -> application.attribute
    	ServletContext application = se.getSession().getServletContext();
    	int currentCounter = (Integer)(application.getAttribute("currentCounter"));
    	application.setAttribute("currentCounter", currentCounter-1); 
    }
	
}
