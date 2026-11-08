package cloud;

/** Concrete element: a compute instance. Exposes read-only getters for visitors. */
public class VirtualMachine implements CloudResource {
    private final String name;
    private final int vCpus;
    private final int ramGb;
    private final int hoursPerMonth;
    private final boolean publicIp;
    private final boolean osPatched;

    public VirtualMachine(String name, int vCpus, int ramGb, int hoursPerMonth,
                          boolean publicIp, boolean osPatched) {
        this.name = name;
        this.vCpus = vCpus;
        this.ramGb = ramGb;
        this.hoursPerMonth = hoursPerMonth;
        this.publicIp = publicIp;
        this.osPatched = osPatched;
    }

    @Override
    public void accept(CloudResourceVisitor visitor) {
        visitor.visitVirtualMachine(this); // double dispatch
    }

    public String getName()          { return name; }
    public int getVCpus()            { return vCpus; }
    public int getRamGb()            { return ramGb; }
    public int getHoursPerMonth()    { return hoursPerMonth; }
    public boolean hasPublicIp()     { return publicIp; }
    public boolean isOsPatched()     { return osPatched; }
}
