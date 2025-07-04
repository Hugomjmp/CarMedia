package pt.hugo.carmedia.models.data.system;

import java.lang.management.OperatingSystemMXBean;

/*TODO*/
public class SystemData {
    private String operationSystem;
    private String version;
    private String architecture;
    private String javaVersion;


    public SystemData() {
        this.operationSystem = System.getProperty("os.name");
        this.version = System.getProperty("os.version");
        this.architecture = System.getProperty("os.arch");
        this.javaVersion = System.getProperty("java.version");
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public String getVersion() {
        return version;
    }

    public String getArchitecture() {
        return architecture;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    @Override
    public String toString() {
        return "SystemData{" +
                "operationSystem='" + operationSystem + '\'' +
                ", version='" + version + '\'' +
                ", architecture='" + architecture + '\'' +
                ", javaVersion='" + javaVersion + '\'' +
                '}';
    }
}
