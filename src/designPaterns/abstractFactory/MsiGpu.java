package designPaterns.abstractFactory;

public class MsiGpu implements Gpu{
    @Override
    public void assemble() {
        System.out.println("MSI Gpu");
    }
}
