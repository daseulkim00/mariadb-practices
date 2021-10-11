package bookmall.vo;

public class OrderBookVo {
	private Long count;
	private Long price;
	private Long bookNo;
	private Long orderNo;
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public String toString() {
		return "OrderBook [count=" + count + ", price=" + price + ", bookNo=" + bookNo + ", orderNo=" + orderNo + "]";
	}
	
	
	
}
