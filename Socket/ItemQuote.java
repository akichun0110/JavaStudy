// メッセージのやり取り

public class ItemQuote {

    public long itemNumber;            // 商品番号
    public String itemDescription;    // 商品説明
    public int quantity;                     // 見積もりの商品の数（1以上）
    public int unitPrice;                    // 単価
    public boolean discounted;         // 値引きの反映の有無
    public boolean inStock;              // 在庫

    public ItemQuote (long itemNumber, String itemDescription, int quantity, int unitPrice, boolean discounted, boolean inStock) {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.unitPrice= unitPrice;
        this.discounted = discounted;
        this.inStock = inStock;
    }

    public String toString() {
        final String EOLN = java.lang.System.getProperty("line.separator");
        String value = "Item#=" + itemNumber + EOLN + 
                            "Description=" + itemDescription + EOLN + 
                            "Quantity=" + quantity + EOLN + 
                            "Price(each)=" + unitPrice + EOLN + 
                            "Total=" + (quantity * unitPrice);

        if (discounted)
            value += " (discounted)";
        if (inStock)
            value += EOLN + "In Stock" + EOLN;
        else 
            value += EOLN + "Out of Stock" + EOLN;
        return value;
    }
}