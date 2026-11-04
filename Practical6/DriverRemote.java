public class DriverRemote {
    public static void main(String[] args) {
        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling Devices:");

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchPolicy policy1 = new SwitchPolicy() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        SwitchPolicy policy2 = (device, hour) ->
            hour >= 8 && hour <= 20;

        int hour = 19;

        System.out.println("\nUsing Anonymous Class:");
        System.out.println(policy1.maySwitchOn(devices[0], hour));

        System.out.println("\nUsing Lambda:");
        System.out.println(policy2.maySwitchOn(devices[1], hour));
    }
}