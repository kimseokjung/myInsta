package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControllerAction", urlPatterns = { "*.do"})
public class ControllerAction extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
	Map map = new HashMap();

	public void init(ServletConfig config) throws ServletException {
		
		
		String props = config.getServletContext().getRealPath("/WEB-INF/commandPro.properties");

		FileInputStream fin = null;
		Properties properties = new Properties();

		try {
			fin = new FileInputStream(props);
			properties.load(fin);
//			System.out.println("properties="+properties);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Iterator it = properties.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			//System.out.println("key="+key);

			String className = properties.getProperty(key);
		//	System.out.println("className="+className);

			try {
				Class classType = Class.forName(className);
				Object ob = classType.newInstance();

				map.put(key, ob);// 맵에 저장
				
		//		System.out.println("map-------------");

				Iterator<Integer> keys = map.keySet().iterator();
				while(keys.hasNext()){
					Object key_sample = keys.next();
				    //System.out.println("[Key]:" + key_sample + " [Value]:" +  map.get(key_sample));
				}
			//	System.out.println("-----------------");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} // while
	}// init()


    //get방식의 서비스 메소드
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    	System.out.println("get");
        requestPro(request, response);
    }

    //post방식의 서비스 메소드
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	System.out.println("post");
        requestPro(request, response);
    }

    //시용자의 요청을 분석해서 해당 작업을 처리
    private void requestPro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	//요청
		System.out.println("test : " + request.getRequestURI());
		
		String category = request.getServletPath().substring(1);
		System.out.println("category = " + category);
			
		CommandAction command = (CommandAction) map.get(category);
		System.out.println("command = " + command);
		
		String view=null;
		try {
			view = command.requestPro_action(request, response);   //이때 command로 정해진 클래스 안에는 commandAction_sample의 상속을 받은 requestPro_action이 정의되어있음,, 이거 한참찾음
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("view = " + view);
		
		//응답

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//context의 경로를 생략한채 써주어야한다. 이 메소드를 쓰면 기본적으로 /insta 라는 절대경로가 박혀있따고 생각하자 . 그래서 리턴으로 번지받아올떄 앞에 context root를 안받아오는이유
		dispatcher.forward(request, response);
    }   
}
