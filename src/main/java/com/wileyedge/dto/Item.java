package com.wileyedge.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
	
	private String name;
    private int quantity;
    private BigDecimal price;
    
    public Item(String name, BigDecimal price, int quantity) {
	
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	@Override
    public int hashCode() {
        int hash = 4;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.price);
        hash = 47 * hash + Objects.hashCode(this.quantity);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }

        return true;
    }


	@Override
	public String toString() {
		return "Item [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
    
    

}
