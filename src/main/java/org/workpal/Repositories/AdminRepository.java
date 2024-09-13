package org.workpal.Repositories;

import org.workpal.Repositories.RepositoryInterfaces.AdminRepositoryInterface;

public class AdminRepository<T> extends UserRepository  implements AdminRepositoryInterface {
    public AdminRepository(Class<T> type) {
        super(type);
    }

}
