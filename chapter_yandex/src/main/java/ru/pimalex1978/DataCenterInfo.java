package ru.pimalex1978;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class DataCenterInfo {
    private int dataCenterNameLikeNumber;
    private int numberOfServers;

    private int numberOfRestarts; //число перезапусков - R
    private int numberOfWorkingServers; //число работающих серверов - A
    private int multiplicationRandA;
    private List<ServerInfo> serversList = new ArrayList<>();

    public int getMultiplicationRandA() {
        return getNumberOfRestarts() * getNumberOfWorkingServers();
    }

    public int getNumberOfWorkingServers() {
        int res = 0;
        if (!serversList.isEmpty()) {
            for (ServerInfo server : serversList) {
                if (server.isEnabled()) {
                    res++;
                }
            }
        }
        return res;
    }

    public DataCenterInfo(int dataCenterNameLikeNumber, int numberOfServers) {
        this.dataCenterNameLikeNumber = dataCenterNameLikeNumber;
        this.numberOfServers = numberOfServers;
        this.serversList = createServersList(numberOfServers);
    }

    private List<ServerInfo> createServersList(int numberOfServers) {
        List<ServerInfo> result = new ArrayList<>();
        for (int i = 0; i < numberOfServers; i++) {
            result.add(new ServerInfo(i + 1, true));
        }
        return result;
    }

    public void setServersToOn() {
        for (ServerInfo server : getServersList()) {
            server.setEnabled(true);
        }
    }
}
