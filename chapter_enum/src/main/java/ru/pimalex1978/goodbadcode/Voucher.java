package ru.pimalex1978.goodbadcode;

public class Voucher {
    private int id;
    private VoucherType type;

    public Voucher(VoucherType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VoucherType getType() {
        return type;
    }

    public void setType(VoucherType type) {
        this.type = type;
    }
}
