package com.coffee.coffeemall.model.bean;

/**
 * Created by Administrator on 2016/6/30.
 */
public class RecommendBean {
    private int id;
    private String title;
    private Item cpOne;
    private Item cpTwo;
    private Item cpThree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item getCpOne() {
        return cpOne;
    }

    public void setCpOne(Item cpOne) {
        this.cpOne = cpOne;
    }

    public Item getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(Item cpTwo) {
        this.cpTwo = cpTwo;
    }

    public Item getCpThree() {
        return cpThree;
    }

    public void setCpThree(Item cpThree) {
        this.cpThree = cpThree;
    }

    public class Item{
        private int id;
        private String imgUrl;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RecommendBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cpOne=" + cpOne +
                ", cpTwo=" + cpTwo +
                ", cpThree=" + cpThree +
                '}';
    }
}
