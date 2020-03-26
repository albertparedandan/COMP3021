package lab8;

public class PaperBook extends Book implements PaperDocument<PaperBook>{
    private String paperSize;
    private String coverType;

    public PaperBook(String title, String author, String publisher, double price, double length, String paperSize, String coverType) {
        super(title, author, publisher, price, length);
        this.paperSize = paperSize;
        this.coverType = coverType;
    }

    @Override
    public String getCoverType() {
        return "" + this.coverType;
    }

    @Override
    public String getPaperSize() {
        return "" + this.paperSize;
    }

    @Override
    public String toString() {
        String result = "Paper book | title: " + this.getTitle() + " | authors: " +  this.getAuthor() + " | publisher: " + this.getPublisher() + " | price: %.2f"+ " | length: %.2f" + "k words | cover type: " + this.getCoverType() + " | paper size: " + this.getPaperSize();
        result = String.format(result, this.getPrice(), this.getLength());
        return result;
    }
}
