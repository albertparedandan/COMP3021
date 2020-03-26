package lab8;

import java.lang.Comparable;

public abstract class Book implements Comparable<Book>{

    private String title;
    private double price;
    private double length;
    private String author;
    private String publisher;

    public Book(String title, String author, String publisher, double price, double length){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.length = length;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    public String getPublisher(){
        return publisher;
    }

    public double getPrice(){
        return price;
    }

    public double getLength(){
        return length;
    }

    @Override
    public int compareTo(Book book){
        //TODO finish the implementation of compareTo(), compare using the length attribute of the two books
//        if (this.getLength() > book.getLength()) {
//            return 1;
//        }
//
//        else if (this.getLength() < book.getLength()) {
//            return -1;
//        }
//        else {
//            return 0;
//        }
        return 10000;
    }

    public abstract String toString();
}
