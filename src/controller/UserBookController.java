package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import com.mysql.jdbc.StringUtils;

import pojo.Info;
import pojo.User;
import service.user.BookUserService;
import service.user.BookUserServiceImpl;

/**
 * Servlet implementation class UserBookController
 */
@WebServlet("/UserBookController")
public class UserBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookUserService bs=new BookUserServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("add".equals(op)) {
			addBookUser(req,resp);
		}else if ("login".equals(op)) {
			login(req,resp);
		}
	}



	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String msg=req.getParameter("msg");
		String userName=req.getParameter("userName");
		String userPsw=req.getParameter("userPsw");
		String Name=userName;
		//对密码进行加密
		userPsw = DigestUtils.md5Hex(userPsw);
		System.out.println(msg);
		boolean isOk=bs.loginValidate(userName, userPsw);
		User user=bs.getName(userName);
		
		if (isOk) {
			req.getSession().setAttribute("userName",Name);
			req.getSession().setAttribute("user",user);
			if ("admin".equals(msg)) {
				resp.sendRedirect("admin/admin-home.jsp");
			}else {
				resp.sendRedirect("InfoBookController?op=Index");
			}
		}else {
			if ("admin".equals(msg)) {
				resp.sendRedirect("admin/admin-login.jsp");
			}else {
				resp.sendRedirect("user/user-login.jsp");
			}
		}
	}

	private void addBookUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
				String userId=req.getParameter("userId");
				String userPsw=req.getParameter("userPsw");
				String reLoginPsw=req.getParameter("reLoginPsw");
				String userName=req.getParameter("userName");
				String code=req.getParameter("code");
				String m=null;
				if (StringUtils.isNullOrEmpty(userId) || StringUtils.isNullOrEmpty(userPsw)){
					m="用户信息不完整！！！";
					req.getSession().setAttribute("m",m);
					resp.sendRedirect("user/user-regist.jsp");
				}
				
				//对用户和密码进行加密
				userId=DigestUtils.md5Hex(userId);
				userPsw = DigestUtils.md5Hex(reLoginPsw);
				User user=new User(userId,userPsw,userName,1);
				boolean isOk=bs.saveUser(user);
				if (isOk) {
						resp.sendRedirect("user/user-login.jsp");
					
				}else {
					resp.sendRedirect("user/user-regist.jsp");
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
