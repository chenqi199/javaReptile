package IPModel;

/**
 * Created by paranoid on 17-4-21.
 */
public class DatabaseMessage {
    private long id;
    private String IPAddress;
    private String IPPort;
    private String ServerAddress;
    private String IPType;
    private String IPSpeed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getIPPort() {
        return IPPort;
    }

    public void setIPPort(String IPPort) {
        this.IPPort = IPPort;
    }

    public String getServerAddress() {
        return ServerAddress;
    }

    public void setServerAddress(String serverAddress) {
        ServerAddress = serverAddress;
    }

    public String getIPType() {
        return IPType;
    }

    public void setIPType(String IPType) {
        this.IPType = IPType;
    }

    public String getIPSpeed() {
        return IPSpeed;
    }

    public void setIPSpeed(String IPSpeed) {
        this.IPSpeed = IPSpeed;
    }
}
