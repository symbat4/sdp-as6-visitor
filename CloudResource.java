package cloud;

/** Element: anything that lives in the cloud account and can be visited. */
public interface CloudResource {
    void accept(CloudResourceVisitor visitor);
}
