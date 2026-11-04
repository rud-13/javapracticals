@FunctionalInterface
public interface SwitchPolicy {
    boolean maySwitchOn(Switchable device, int hour);
}