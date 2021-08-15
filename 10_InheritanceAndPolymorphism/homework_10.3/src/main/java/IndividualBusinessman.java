public class IndividualBusinessman extends Client {
    private final double PERCENT_MORE_THOUSAND = 0.005;
    private final double PERCENT_LESS_THOUSAND = 0.01;

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut < 1000 ? amountToPut - (amountToPut * PERCENT_LESS_THOUSAND) :
                                        amountToPut - (amountToPut * PERCENT_MORE_THOUSAND));
    }
}
