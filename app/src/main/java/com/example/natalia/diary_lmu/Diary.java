package com.example.natalia.diary_lmu;

public class Diary {
    private final int summary;
    private final int imageResource;
    public String imageUrl;

    public Diary(int summary, int imageResource, String imageUrl) {
        this.summary = summary;
        this.imageResource = imageResource;
        this.imageUrl = imageUrl;
    }


    public int getSummary() {
        return summary;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String url){this.imageUrl = url;}
}
