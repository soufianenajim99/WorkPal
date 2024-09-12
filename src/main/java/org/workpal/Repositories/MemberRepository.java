package org.workpal.Repositories;

import org.workpal.Repositories.RepositoryInterfaces.MemberRepositoryInterface;

public class MemberRepository<T> extends UserRepository implements MemberRepositoryInterface {
    public MemberRepository(Class<T> type) {
        super(type);
    }
}
