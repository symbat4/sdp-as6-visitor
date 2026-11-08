package cloud;

public class Main {
    public static void main(String[] args) {
        CloudInfrastructure infrastructure = new CloudInfrastructure();
        infrastructure.add(new VirtualMachine("web-server-1", 4, 16, 720, true, false));
        infrastructure.add(new VirtualMachine("batch-worker", 8, 32, 200, false, true));
        infrastructure.add(new StorageBucket("user-uploads", 500, true, false));
        infrastructure.add(new StorageBucket("backups-archive", 2000, false, true));
        infrastructure.add(new ManagedDatabase("orders-db", 250, true, true));
        infrastructure.add(new ManagedDatabase("analytics-db", 800, false, false));

        System.out.println("=== Monthly cost estimate ===");
        MonthlyCostVisitor costVisitor = new MonthlyCostVisitor();
        infrastructure.accept(costVisitor);
        System.out.printf("  %-18s $%8.2f%n%n", "TOTAL", costVisitor.getTotal());

        System.out.println("=== Security audit ===");
        SecurityAuditVisitor auditVisitor = new SecurityAuditVisitor();
        infrastructure.accept(auditVisitor);
        auditVisitor.getFindings().forEach(f -> System.out.println("  " + f));

        System.out.println("\n=== Inventory (3rd visitor, added without touching elements) ===");
        ResourceInventoryVisitor inventoryVisitor = new ResourceInventoryVisitor();
        infrastructure.accept(inventoryVisitor);
        System.out.println(inventoryVisitor.summary());
    }
}
