package designPaterns.abstractFactory;

public abstract class Company {
    public abstract Gpu createGpu();

    public abstract Monitor createMonitor();

    public static void main(String[] args) {
        Company msi = new MsiManufacturer();
        Gpu msiGpu = msi.createGpu();
        Monitor msiMonitor = msi.createMonitor();
        msiGpu.assemble();
        msiMonitor.assemble();

        Company asus = new AsusManufacturer();
        Gpu asusGpu = asus.createGpu();;
        Monitor asusMonitor = asus.createMonitor();
        asusGpu.assemble();
        asusMonitor.assemble();

    }
}
