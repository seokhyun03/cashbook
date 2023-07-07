package cash.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {
	// 톰캣 실행 시 실행되는 메소드
    public void contextInitialized(ServletContextEvent sce) { 
    	// 메모리에 MariaDB 드라이버 로딩
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MariaDB 드라이버 로딩 실패");
			e.printStackTrace();
		}
    	System.out.println("MariaDB 드라이버 로딩 성공");
    }
}
