package Main.Pages.ProductObject;

public class ProductObject {
    private String type;
    private String name;
    private int commonPrice;
    private int priceWithGuarantee;

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    private String guarantee;
    public ProductObject(String type, String name, String guarantee) {
        this.type = type;
        this.name = name;
        this.commonPrice = commonPrice;
        this.priceWithGuarantee = priceWithGuarantee;
        this.guarantee = guarantee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(int commonPrice) {
        this.commonPrice = commonPrice;
    }

    public int getPriceWithGuarantee() {
        return priceWithGuarantee;
    }

    public void setPriceWithGuarantee(int priceWithGuarantee) {
        this.priceWithGuarantee = priceWithGuarantee;
    }
}
