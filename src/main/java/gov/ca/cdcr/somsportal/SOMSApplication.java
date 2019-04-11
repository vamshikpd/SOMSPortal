package gov.ca.cdcr.somsportal;

import java.util.List;

public class SOMSApplication implements Comparable<SOMSApplication>
{
    private List<String> entitledGroups;
    private String image;
    private String url;
    private String caption;
    private int index;


    public SOMSApplication() { }

    public SOMSApplication(List<String> entitledGroups, String image, String url, String caption) {
        this.entitledGroups = entitledGroups;
        this.image = image;
        this.url = url;
        this.caption = caption;
    }

    public List<String> getEntitledGroups() {
        return entitledGroups;
    }

    public void setEntitledGroups(List<String> entitledGroups) {
        this.entitledGroups = entitledGroups;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return "SOMSApplication{" +
                "caption='" + caption + '\'' +
                ", entitledGroups=" + entitledGroups +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(SOMSApplication o) {
        return this.getIndex() - o.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SOMSApplication that = (SOMSApplication) o;

        return caption.equals(that.caption);
    }

    @Override
    public int hashCode() {
        return caption.hashCode();
    }
}
