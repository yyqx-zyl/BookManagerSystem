package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.PortableInterceptor.INACTIVE;

import pojo.Category;
import pojo.Info;
import pojo.Pager;
import service.info.BookInfoService;
import service.info.BookInfoServiceImpl;

/**
 * Servlet implementation class InfoBookController
 */
@WebServlet("/InfoBookController")
public class InfoBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookInfoService bi=new   BookInfoServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("Index".equals(op)) {
			Index(req,resp);
		}else if ("findByid".equals(op)) {
			findByid(req,resp);
		}else if ("findeByname".equals(op)) {
			findeByname(req,resp);
		}
		//后端admin 
		else if ("category".equals(op)) {
			category(req,resp);
		}else if ("Addcategory".equals(op)) {
			Addcategory(req,resp);
		}else if ("detelecategory".equals(op)) {
			detelecategory(req,resp);
		}else if ("book".equals(op)) {
			book(req,resp);
		}else if ("deleteBook".equals(op)) {
			deleteBook(req,resp);
		}else if ("findeBook".equals(op)) {
			findeBook(req,resp);
		}else if ("saveBook".equals(op)) {
			saveBook(req,resp);
		}else if ("AddBook".equals(op)) {
			AddBook(req,resp);
		}
		
	}
	//添加图书信息
	private void AddBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String bookName = null;
		String author = null;
		String categoryid = null;
		String price = null;
		String publisher = null;
		String photo=null;
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//获得文件上传的路径
		String filePath=this.getServletContext().getRealPath("/static/file");
		 //获得文件上传的请求方式
		boolean isMultipart = ServletFileUpload.isMultipartContent(req); 
		if(isMultipart) { //判断是否是二进制形式的数据上传
			FileItemFactory fac=new DiskFileItemFactory(); //创建文件上传的工厂对象
			ServletFileUpload upload=new ServletFileUpload(fac); 
			//设置上传文件的最大为10M
			upload.setFileSizeMax(10*1024*1024); 
			try { 
				List<FileItem> items =upload.parseRequest(req); 
				//遍历获得的所有数据 
				Iterator<FileItem> it=items.iterator(); 
				while(it.hasNext()) { 
					//获得表单元素 
					FileItem item=it.next();
					//判斷是否是普通文件
					if(item.isFormField()) { 
						 String name=item.getFieldName();//得到普通表單的name值ֵ
						 switch(name) { 
						 	case "bookName":
						 		bookName=item.getString("UTF-8"); 
								 break; 
						 	case "author":
						 		 author=item.getString("UTF-8"); 
								 break; 
						 	case "categoryid":
						 		categoryid=item.getString("UTF-8");
								 break; 
						 	case "price":
						 		price=item.getString("UTF-8"); 
								break; 
						 	case "publisher":
						 		publisher=item.getString("UTF-8"); 
								break; 
						 } 
					}else { 
						photo=bookName+item.getName();
					  //实现文件的上传 
					  File saveFile=new File(filePath,photo);
					  item.write(saveFile);
					  // 向客户端响应一个上传成功的标识值
					  out.write("{\"code\":0}");
					}
				} 
			} catch (FileUploadException e) {
				  out.write("{\"code\":1}");
				  e.printStackTrace(); 
			} catch (Exception e) { 
				  out.write("{\"code\":2}");
				  e.printStackTrace(); 
			} 
		}
		// 使用md5的加密方式对数据进行加密操作
	//	Info inn=new Info(bookName, author, categoryid, publisher, price,photo);
		 out.flush();
		
	}

	//修改书籍信息
	private void saveBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		String bookName=req.getParameter("bookName");
		String author=req.getParameter("author");
		int categoryid=Integer.parseInt(req.getParameter("categoryid"));
		double price=Double.parseDouble(req.getParameter("price"));
		String publisher=req.getParameter("publisher");
		Info inn=new Info(id, bookName, author, categoryid, publisher, price);
		boolean isOk=bi.saveBook(inn);
		if (isOk) {
			book(req,resp);
		}
	}

	//查找书籍信息
	private void findeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		List list=bi.findBook(id);
		if (list!=null) {
			req.getSession().setAttribute("li",list);
			resp.sendRedirect("admin/book-edit.jsp");
		}
	}

	//删除书籍信息
	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isOk=bi.deteleBook(id);
		if (isOk) {
			book(req,resp);
		}
	}

	//显示书籍的信息
	private void book(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List lists=bi.getList();
		if (lists!=null) {
			req.getSession().setAttribute("lists", lists);
			resp.sendRedirect("admin/book-mgr.jsp");
		}
	}

	//删除分类名字
	private void detelecategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isOk=bi.detelecategory(id);
		if (isOk) {
			category(req,resp);
		}
		
	}

	//添加分类信息
	private void Addcategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String 	category=req.getParameter("category");
		boolean isOk=bi.Addcategory(category);
		if (isOk) {
			category(req,resp);
		}
	}
	//显示分类信息
	private void category(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Category> cate=bi.getCateList();
		if (cate!=null) {
			req.getSession().setAttribute("Clist",cate);
			resp.sendRedirect("admin/category-mgr.jsp");
		}
	}
/***********************************************前段页面显示*************************************************************/
	//模糊查询
	private void findeByname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String condition=req.getParameter("condition");
		List lists=bi.findeByname(condition);
		Pager pg=new Pager();
		pg.setPageLists(lists);
		if (lists!=null) {
			req.getSession().setAttribute("pg", pg);
			resp.sendRedirect("user/index.jsp");
		}
	}
	//分类查询
	private void findByid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		if (id!=0) {
			Pager pg=new Pager();
			List pageLists=bi.findByid(id);
			pg.setPageLists(pageLists);
			if (pageLists!=null) {
				req.getSession().setAttribute("pg", pg);
				resp.sendRedirect("user/index.jsp");
			}
		}else {
			Index(req,resp);
		}
	}
	//主页面显示
	private void Index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获得当前页的页数
		String pageIndex=req.getParameter("pageIndex");
		String condition=req.getParameter("condition");
		//如果当前页是空的，就使用这个属性
		int currpage=1;
		//创建页数对象
		Pager pg=new Pager();
		//获得数据总条数
		int totalCount=bi.getCount(condition);
		pg.setTotalCount(totalCount);
		//控制页面的
		if (pageIndex==null || "".equals(pageIndex)) {
			currpage=1;
		}else{
			int pIndex=Integer.parseInt(pageIndex);
			if (pIndex<=0) {
				currpage=1;
			}else if (pIndex>pg.getTotalPages()) {
				currpage=pg.getTotalPages();
			}else {
				currpage=pIndex;
			}
		}
		pg.setCurrPage(currpage);
		//从哪里开始查询   如果是第一页   (1-1)*显示的数据条数   从0开始查询
		int start=(currpage-1)*pg.getPageSize();
		List pageLists=bi.getpageLists(condition,start,pg.getPageSize());
		pg.setPageLists(pageLists);
		
		List<Category> cate=bi.getCateList();
		
		if (pageLists!=null) {
			req.getSession().setAttribute("pg", pg);
			req.getSession().setAttribute("Clist",cate);
			//req.getRequestDispatcher("user/index.jsp").forward(req, resp);
			resp.sendRedirect("user/index.jsp");
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
