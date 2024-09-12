package org.workpal.Repositories;

import org.workpal.Repositories.RepositoryInterfaces.ManagerRepositoryInterface;

public class ManagerRepository<T> extends UserRepository implements ManagerRepositoryInterface {
    public ManagerRepository(Class<T> type) {
        super(type);
    }
}
