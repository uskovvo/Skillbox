public class IndividualBusinessman extends Client {
    @Override
    public void put(double amountToPut) {
        super.put(amountToPut < 1000 ? amountToPut - (amountToPut * 0.01) : amountToPut - (amountToPut * 0.005));
    }
}
