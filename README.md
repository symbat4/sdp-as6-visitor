# Visitor Pattern — Cloud Infrastructure (Java)

**Topic:** a cloud account contains resources (VMs, storage buckets, managed databases).
Operations such as cost estimation, security audit and inventory are added as visitors
without touching the resource classes.

## Structure
| Role | Class |
|---|---|
| Element interface | `CloudResource` |
| Concrete elements | `VirtualMachine`, `StorageBucket`, `ManagedDatabase` |
| Visitor interface | `CloudResourceVisitor` |
| Concrete visitors | `MonthlyCostVisitor`, `SecurityAuditVisitor`, `ResourceInventoryVisitor` |
| Object structure | `CloudInfrastructure` |

## Double dispatch
`infrastructure.accept(visitor)` -> `resource.accept(visitor)` (1st dispatch: resource type)
-> `visitor.visitVirtualMachine(this)` (2nd dispatch: visitor type, with the exact element type known).

## Clean Code principles
1. **Meaningful names** – `CloudResource` (element) vs `...Visitor` (operation); `visitVirtualMachine` states what it handles.
2. **No instanceof / type-check chains** – double dispatch replaces them.
3. **Single Responsibility** – each visitor implements exactly one operation across all element types.
4. **Open/Closed** – `ResourceInventoryVisitor` was added with zero changes to elements or other visitors.
5. **Encapsulation trade-off** – fields stay `private final`; visitors read them via read-only getters only (no setters, immutable elements). Trade-off: getters expose some state, but only what operations genuinely need.
6. **No magic numbers** – prices are named constants.
7. **Small methods** – each `visitX` does one thing; printing is extracted to a helper.

## Run
```
mkdir out && javac -d out src/cloud/*.java && java -cp out cloud.Main
```
