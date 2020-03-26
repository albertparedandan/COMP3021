package lab8;

public class EBook extends Book implements ElectronicDocument<EBook>{
    private String format;
    private double fileSize;
    public EBook(String title, String author, String publisher, double price, double length, String format, double fileSize) {
        super(title, author, publisher, price, length);
        this.format = format;
        this.fileSize = fileSize;
    }

    @Override
    public String getFormat() {
        return "" + this.format;
    }

    @Override
    public double getFileSize() {
        return 0.0 + this.fileSize;
    }

    @Override
    public String toString() {
        String result = "eBook | title: " + this.getTitle() + " | authors: " +  this.getAuthor() + " | publisher: " + this.getPublisher() + " | price: %.2f"+ " | length: %.2f" + "k words | cover type: " + this.getFormat() + " | File size: " + this.getFileSize();
        result = String.format(result, this.getPrice(), this.getLength());
        return result;
    }
}