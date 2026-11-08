package cloud;

/** Concrete element: a managed database service. */
public class ManagedDatabase implements CloudResource {
    private final String name;
    private final int storageGb;
    private final boolean backupsEnabled;
    private final boolean encrypted;

    public ManagedDatabase(String name, int storageGb, boolean backupsEnabled, boolean encrypted) {
        this.name = name;
        this.storageGb = storageGb;
        this.backupsEnabled = backupsEnabled;
        this.encrypted = encrypted;
    }

    @Override
    public void accept(CloudResourceVisitor visitor) {
        visitor.visitManagedDatabase(this); // double dispatch
    }

    public String getName()            { return name; }
    public int getStorageGb()          { return storageGb; }
    public boolean hasBackupsEnabled() { return backupsEnabled; }
    public boolean isEncrypted()       { return encrypted; }
}
