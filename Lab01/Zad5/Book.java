package Zad5;
public class Book {
    public Author author;
    public String name;
    public double price;
    public int qty = 0;
    
    Book(Author author, String name, double price, int qty ){
        this.author = author;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    Book(Author author, String name, double price){
        this.author = author;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String toString() {
         return "Book [name=" + name + ", author=" + author + ", price=" + price + ", qty=" + qty + "]";
    }
}

