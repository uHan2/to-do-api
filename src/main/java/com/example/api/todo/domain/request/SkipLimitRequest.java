package com.example.api.todo.domain.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SkipLimitRequest implements Pageable {

    private final Integer limit;

    private final Integer skip;

    public SkipLimitRequest(int limit, int skip) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (skip < 0) {
            throw new IllegalArgumentException("Skip index must not be less than zero!");
        }
        this.limit = limit;
        this.skip = skip;
    }

    @Override
    public int getPageNumber() {
        return skip / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return skip;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return new SkipLimitRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return hasPrevious() ? new SkipLimitRequest(getPageSize(), (int) (getOffset() - getPageSize())) : this;
    }

    @Override
    public Pageable first() {
        return new SkipLimitRequest(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new SkipLimitRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public boolean hasPrevious() {
        return skip > limit;
    }
}
