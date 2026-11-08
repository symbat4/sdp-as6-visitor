package cloud;

/** Concrete element: an object-storage bucket. */
public class StorageBucket implements CloudResource {
    private final String name;
    private final int sizeGb;
    private final boolean publiclyReadable;
    private final boolean encrypted;

    public StorageBucket(String name, int sizeGb, boolean publiclyReadable, boolean encrypted) {
        this.name = name;
        this.sizeGb = sizeGb;
        this.publiclyReadable = publiclyReadable;
        this.encrypted = encrypted;
    }

    @Override
    public void accept(CloudResourceVisitor visitor) {
        visitor.visitStorageBucket(this); // double dispatch
    }

    public String getName()              { return name; }
    public int getSizeGb()               { return sizeGb; }
    public boolean isPubliclyReadable()  { return publiclyReadable; }
    public boolean isEncrypted()         { return encrypted; }
}
