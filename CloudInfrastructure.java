package cloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Object structure: holds the resources and lets a visitor traverse them. */
public class CloudInfrastructure {
    private final List<CloudResource> resources = new ArrayList<>();

    public void add(CloudResource resource) {
        resources.add(resource);
    }

    public List<CloudResource> getResources() {
        return Collections.unmodifiableList(resources);
    }

    public void accept(CloudResourceVisitor visitor) {
        for (CloudResource resource : resources) {
            resource.accept(visitor);
        }
    }
}
