package ru.job4j.ood.lsp.storage;

import java.util.List;

public class AvailableStorage {
    private List<Storage> storageList;

    public AvailableStorage(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public List<Storage> getStorageList() {
        return storageList;
    }
}
