package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Orders;
import service.orders.BookOrdersService;
import service.orders.BookOrdersServiceImpl;


/**
 * Servlet implementation class OrderBookController
 */
@WebServlet("/OrderBookController")
public class OrderBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookOrdersService bs=new BookOrdersServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("orderAdd".equals(op)) {
			orderAdd(req,resp);
		}else if ("showCar".equals(op)) {
			showCar(req,resp);
		}else if ("update".equals(op)) {
			update(req,resp);
		}
	}
	//更新订单的价钱
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//设置响应给客户端的数据
		resp.setContentType("application/json;charset=UTF-8");
		
		String oid = req.getParameter("oid");
		int count = Integer.parseInt(req.getParameter("count"));
		double price = Double.parseDouble(req.getParameter("price"));
		double curPrice = price*count;
		//调用方法
		boolean isOk=bs.update(oid,count,curPrice);
		PrintWriter pw=resp.getWriter();
		if (isOk) {
			pw.write("{\"result\":\"true\"}");
		}else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
		pw.close();
	}

	//显示订单
	private void showCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userId=req.getParameter("userId");
		System.out.println(userId);
		List<Orders> list=bs.findOrders(userId);
		req.getSession().setAttribute("list",list);
		resp.sendRedirect("user/cart.jsp");
	}

	//添加订单
	private void orderAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		//设置响应给客户端的数据
		resp.setContentType("application/json;charset=UTF-8");
		System.out.println("的回复is获得fish");
		//获得id值
		int id=Integer.parseInt(req.getParameter("id"));
		double price=Double.parseDouble(req.getParameter("price"));
		//生成订单编号 当前时间+6位随机数
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMddHHmmss");
		String orderId=sdf.format(date);
		int rand=(int) (Math.random()*(999999-100000+1)+100000);
		orderId +=rand;
		System.out.println("orderId:"+orderId);
		String userId = (String) req.getSession().getAttribute("userName");
		int count=1;
		Orders orders=new Orders(orderId, id, count, price, date, userId);
		boolean isOk=bs.addOrders(orders);
		PrintWriter pw=resp.getWriter();
		if (isOk) {
			pw.write("{\"result\":\"true\"}");
		}else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
