package ddwxReptile;

public class Article {
    private String id;

    private String tittle;

    private Long ctime;

    private String author;

    private String image;

    public Article(String tittle, Long ctime, String author, String image, String content) {
        this.tittle = tittle;
        this.ctime = ctime;
        this.author = author;
        this.image = image;
        this.content = content;
    }

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tittle=").append(tittle);
        sb.append(", ctime=").append(ctime);
        sb.append(", author=").append(author);
        sb.append(", image=").append(image);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}