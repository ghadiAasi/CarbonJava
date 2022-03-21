package com.example.carbonjava;

public class Item {
    private String description;
    private String key; //image id to be loaded
    private int level;
    private String pic;

    public Item(String pic,String gameName,String key,int level){
        this.pic =pic;
        this.description = gameName;
        this.key = key;
        this.level = level;
    }

    public Item(){}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", key='" + key + '\'' +
                ", level=" + level +
                ", pic='" + pic + '\'' +
                '}';
    }
}

