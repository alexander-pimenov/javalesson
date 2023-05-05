package ru.pimalex1978.utils.util.some;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<FIRST, SECOND> {
    private final FIRST first;
    private final SECOND second;

    public static <FIRST, SECOND> Pair<FIRST, SECOND> of(final FIRST first, final SECOND second) {
        return new Pair<>(first, second);
    }
}
