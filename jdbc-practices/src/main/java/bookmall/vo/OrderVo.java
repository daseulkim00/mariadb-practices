package bookmall.vo;

public class OrderVo {
	private Long no;
	private Long orderNo;
	private Long payAmt;
	private String ship;
	private Long memberNo;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}
	public String getShip() {
		return ship;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	
	@Override
	public String toString() {
		return "Order [no=" + no + ", orderNo=" + orderNo + ", payAmt=" + payAmt + ", ship=" + ship + ", memberNo="
				+ memberNo + "]";
	}
	
	
	
	
	
}
