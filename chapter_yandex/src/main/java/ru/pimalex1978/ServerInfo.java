package ru.pimalex1978;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ServerInfo {
    private int serverNameLikeNumber;
    private boolean enabled;
}
