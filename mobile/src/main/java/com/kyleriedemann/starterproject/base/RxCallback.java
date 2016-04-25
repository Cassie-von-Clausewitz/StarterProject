package com.kyleriedemann.starterproject.base;

/**
 * Created by kyle on 4/8/16.
 *
 * Interface to handle the results of an rx call
 *
 * @author kylealanr
 */
public interface RxCallback<T> {
    void onDataReady(T data);
    void onDataError(Throwable e);
}
