package auth.services.barcodeservices;

public class BarcodeGeneratorExecuter {

    public static BarcodeGeneratorStrategy barcodeGeneratorStrategy(String generatorStrategy){
        switch (generatorStrategy){
            case "zxing":
                return new ZxingBarcodeGeneratorImpl();
            default:
                throw new IllegalArgumentException("Invalid Barcode generation strategy : " + generatorStrategy);
        }
    }
}
