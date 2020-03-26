package lab8;

import java.util.ArrayList;
import java.util.Collections;

public class Lab8 {
    public static void main(String[] args){

        ArrayList<Book> books = new ArrayList<>();
        books.add(new EBook("the moon and sixpence", "William Somerset Maugham", "William Heinemann", 20.56, 45.3, "PDF", 4.5));
        books.add(new EBook("War and Peace", "Leo Tolstoy", "HarperCollins ", 31.4, 50.6, "MOBI", 5.8));
        books.add(new PaperBook("The Rescue", "STEVEN KONKOLY", "THOMAS & MERCER", 12.3, 13.4, "B5", "hard cover"));
        books.add(new PaperBook("Wolf Pack", "C. J. BOX", "G.P. PUTNAM'S SONS", 15.3, 16.4, "A4", "paper back"));

        Collections.sort(books);
        for (Book book: books){
            System.out.println(book);
        }

    }
}
