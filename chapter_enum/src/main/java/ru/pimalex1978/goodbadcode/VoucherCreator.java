package ru.pimalex1978.goodbadcode;

public class VoucherCreator {
    public static Voucher createVoucher(String inputData) {
        Voucher voucher = null;
        String[] params = inputData.split(":");
        VoucherType type;
        switch (params[0].toUpperCase().trim().replace(" ", "_")) {
            case "CAMPING":
                type = VoucherType.CAMPING;
                break;
            case "SEA_TOUR":
                type = VoucherType.SEA_TOUR;
                break;
            case "SHOPPING":
                type = VoucherType.SHOPPING;
                break;
            default: {
                throw new IllegalArgumentException();
            }
        }
        return new Voucher(type);
    }
}
