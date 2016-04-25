package com.kyleriedemann.starterproject.base;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by kyle on 4/8/16.
 *
 * Extension of Subscriber that handles returning results to an implementation of RxCallback.
 * This class handles logging of errors and unsubscribing oncompleted
 *
 * Logging makes use of Timber, and will log inner exceptions as well
 *
 * @author kylealanr
 */
public class RxSubscriber<T> extends Subscriber<T> {

    RxCallback<T> callback;

    public RxSubscriber(RxCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onCompleted() {
        unsubscribe();
    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e.getClass() + ": " + e.getMessage());

        Throwable cause = e.getCause();

        if (cause != null)
            Timber.e(cause.getClass() + ": " + cause.getMessage());

        callback.onDataError(e);
    }

    @Override
    public void onNext(T data) {
        callback.onDataReady(data);
    }
}
