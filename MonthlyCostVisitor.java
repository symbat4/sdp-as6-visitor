package cloud;

/** Concrete visitor #1: single operation = estimate the monthly bill. */
public class MonthlyCostVisitor implements CloudResourceVisitor {
    private static final double PRICE_PER_VCPU_HOUR = 0.04;
    private static final double PRICE_PER_RAM_GB_HOUR = 0.005;
    private static final double PRICE_PER_BUCKET_GB = 0.023;
    private static final double PRICE_PER_DB_GB = 0.115;
    private static final double BACKUP_SURCHARGE = 0.20; // +20% when backups are on

    private double total;

    @Override
    public void visitVirtualMachine(VirtualMachine vm) {
        double cost = vm.getHoursPerMonth()
                * (vm.getVCpus() * PRICE_PER_VCPU_HOUR + vm.getRamGb() * PRICE_PER_RAM_GB_HOUR);
        print(vm.getName(), cost);
    }

    @Override
    public void visitStorageBucket(StorageBucket bucket) {
        print(bucket.getName(), bucket.getSizeGb() * PRICE_PER_BUCKET_GB);
    }

    @Override
    public void visitManagedDatabase(ManagedDatabase database) {
        double cost = database.getStorageGb() * PRICE_PER_DB_GB;
        if (database.hasBackupsEnabled()) {
            cost *= 1 + BACKUP_SURCHARGE;
        }
        print(database.getName(), cost);
    }

    private void print(String name, double cost) {
        total += cost;
        System.out.printf("  %-18s $%8.2f%n", name, cost);
    }

    public double getTotal() {
        return total;
    }
}
