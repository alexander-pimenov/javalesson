package ru.pimalex1978.goodbadcode;

public class VoucherCreatorGood {
    public static Voucher createVoucher(String inputData) {
        Voucher voucher = null;
        String[] params = inputData.split(":");
        String dataType = params[0].toUpperCase().trim().replace(" ", "_");
        VoucherType type = VoucherType.valueOf(dataType);

        return new Voucher(type);
    }
}
