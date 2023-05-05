package ru.pimalex1978.utils.util.some;

import java.sql.Timestamp;
import java.util.Date;

public class Now extends Timestamp {

    public Now() {
        super(new Date().getTime());
    }
}
