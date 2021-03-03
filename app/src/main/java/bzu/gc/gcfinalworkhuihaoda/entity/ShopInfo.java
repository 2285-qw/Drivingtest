package bzu.gc.gcfinalworkhuihaoda.entity;

public class ShopInfo {
    private String img;
    private String tittle;
    private String src;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public ShopInfo() {
    }

    public ShopInfo(String img, String tittle, String src) {
        this.img = img;
        this.tittle = tittle;
        this.src = src;
    }
}
