package cloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Concrete visitor #2: single operation = collect security findings. */
public class SecurityAuditVisitor implements CloudResourceVisitor {
    private final List<String> findings = new ArrayList<>();

    @Override
    public void visitVirtualMachine(VirtualMachine vm) {
        if (vm.hasPublicIp() && !vm.isOsPatched()) {
            findings.add("[HIGH]   VM '" + vm.getName() + "' has a public IP and an unpatched OS");
        } else if (!vm.isOsPatched()) {
            findings.add("[MEDIUM] VM '" + vm.getName() + "' has an unpatched OS");
        }
    }

    @Override
    public void visitStorageBucket(StorageBucket bucket) {
        if (bucket.isPubliclyReadable()) {
            findings.add("[HIGH]   Bucket '" + bucket.getName() + "' is publicly readable");
        }
        if (!bucket.isEncrypted()) {
            findings.add("[MEDIUM] Bucket '" + bucket.getName() + "' is not encrypted");
        }
    }

    @Override
    public void visitManagedDatabase(ManagedDatabase database) {
        if (!database.isEncrypted()) {
            findings.add("[HIGH]   Database '" + database.getName() + "' is not encrypted");
        }
        if (!database.hasBackupsEnabled()) {
            findings.add("[MEDIUM] Database '" + database.getName() + "' has no backups");
        }
    }

    public List<String> getFindings() {
        return Collections.unmodifiableList(findings);
    }
}
