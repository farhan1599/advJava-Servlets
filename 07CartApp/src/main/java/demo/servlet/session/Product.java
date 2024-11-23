package demo.servlet.session;

public class Product {
	private String productId;
	private String productName;
	private double price;
	
	public Product() {
		
	}
	public Product(String productId,String productName,double price) {
		this.productId=productId;
		this.productName=productName;
		this.price=price;
	}
	
	public void setProductId(String productId) {
		this.productId=productId;
	}
	public String getProductId() {
		return productId;
	}
    public void setProductName(String productName) {
    	this.productName= productName;
    }
    public String getProductName() {
    	return productName;
    }
    public void setPrice(double price) {
    	this.price=price;
    }
    public double getPrice() {
    	return price;
    }
}
