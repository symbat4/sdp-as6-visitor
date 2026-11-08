package cloud;

/**
 * Concrete visitor #3: added later to prove extensibility.
 * No element class and no existing visitor was modified to add it.
 */
public class ResourceInventoryVisitor implements CloudResourceVisitor {
    private int vmCount;
    private int bucketCount;
    private int databaseCount;
    private int totalStorageGb;

    @Override
    public void visitVirtualMachine(VirtualMachine vm) {
        vmCount++;
    }

    @Override
    public void visitStorageBucket(StorageBucket bucket) {
        bucketCount++;
        totalStorageGb += bucket.getSizeGb();
    }

    @Override
    public void visitManagedDatabase(ManagedDatabase database) {
        databaseCount++;
        totalStorageGb += database.getStorageGb();
    }

    public String summary() {
        return String.format("  VMs: %d | Buckets: %d | Databases: %d | Total storage: %d GB",
                vmCount, bucketCount, databaseCount, totalStorageGb);
    }
}
