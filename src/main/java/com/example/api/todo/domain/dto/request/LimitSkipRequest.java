package com.example.api.todo.domain.dto.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Request Object For Init Limit, Skip Value for Pageable
 *
 * @author 이상진
 * @since 2022.02.21
 */
public class LimitSkipRequest implements Pageable {

    private final Integer limit;

    private final Integer skip;

    public LimitSkipRequest(int limit, int skip) {
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
        return new LimitSkipRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return hasPrevious() ? new LimitSkipRequest(getPageSize(), (int) (getOffset() - getPageSize())) : this;
    }

    @Override
    public Pageable first() {
        return new LimitSkipRequest(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new LimitSkipRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public boolean hasPrevious() {
        return skip > limit;
    }
}
