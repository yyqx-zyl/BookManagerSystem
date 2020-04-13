package pojo;

import java.util.Date;

public class Orders {
	private String oid;// 订单编号
	private int bid;// 图书编号
	private int count;//图书数量
	private double curPrice;// 小计
	private Date date;//购买时间
	private String userId;
	private Info bookInfo;
	
	public Info getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(Info bookInfo) {
		this.bookInfo = bookInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Orders(String oid, int bid, int count, double curPrice, Date date, String userId) {
		super();
		this.oid = oid;
		this.bid = bid;
		this.count = count;
		this.curPrice = curPrice;
		this.date = date;
		this.userId = userId;
	}
	public Orders() {
		super();
	}
	
}
