package ddwxReptile;

/**
 * Created by chenqi on 2017/7/6 0006 : 下午 5:13.
 *
 * @version : 1.0
 * @description :
 */
public class DdwxHtmlBean {
    private String tittle;//标题

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getBeforeA() {
        return beforeA;
    }

    public void setBeforeA(String beforeA) {
        this.beforeA = beforeA;
    }

    private String textBody;//正文
    private String beforeA;//上页连觉

    public DdwxHtmlBean(String tittle, String textBody, String beforeA) {
        this.tittle = tittle;
        this.textBody = textBody;
        this.beforeA = beforeA;
    }

    @Override
    public String toString() {
        return "DdwxHtmlBean{" +
                "tittle='" + tittle + '\'' +
                ", textBody='" + textBody + '\'' +
                ", beforeA='" + beforeA + '\'' +
                '}';
    }
}
