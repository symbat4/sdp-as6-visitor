package cloud;

/** Visitor: one visit method per concrete element type. */
public interface CloudResourceVisitor {
    void visitVirtualMachine(VirtualMachine vm);
    void visitStorageBucket(StorageBucket bucket);
    void visitManagedDatabase(ManagedDatabase database);
}
