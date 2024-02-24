package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusKey {
    static final String FILE_ACCEPTED = "file_accepted";
    static final String FILE_DIFF = "file_diff";
    static final String FILE_IN_SADD = "file_insadd";
    static final String FILE_LOADED = "file_loaded";
    static final String FILE_PROJECT = "file_project";
    static final String FILE_REGISTERED = "file_registered";
    static final String FILE_RELEASED = "file_released";
    static final String FILE_SENT = "file_sent";
    static final String FILE_SIGNED = "file_signed";

    static final String ITEM_COMPLETED = "item_completed";
    static final String ITEM_LOADED = "item_loaded";
    static final String ITEM_NOT_COMPLETED = "item_not_completed";

    static final String PACKAGE_ACCEPTED = "package_accepted";
    static final String PACKAGE_ANSWER_ERROR = "package_answer_error";
    static final String PACKAGE_REGISTERED = "package_registered";
    static final String PACKAGE_INVALID = "package_invalid";
    static final String PACKAGE_ERROR = "package_error";

    static final String REQUEST_COMPLETED = "request_completed";
    static final String REQUEST_DELIVERED = "request_delivered";
    static final String REQUEST_DELIVERY_ERROR = "request_delivery_error";
    static final String REQUEST_DRAFT = "request_draft";
    static final String REQUEST_ERROR = "request_error";
    static final String REQUEST_SIGNED = "request_signed";
    static final String REQUEST_IN_SADD = "request_insadd";
    static final String REQUEST_IN_CHECKED = "request_inchecked";
    static final String REQUEST_NOT_COMPLETED = "request_not_completed";
    static final String REQUEST_PARTIALLY_COMPLETED = "request_partially_completed";
    static final String REQUEST_PREPARED = "request_prepared";
    static final String REQUEST_REGISTERED = "request_registered";
    static final String REQUEST_SENT = "request_sent";

    static final String NOTIFICATION_NOT_SENT = "notification_not_sent";
    static final String NOTIFICATION_SENT_NOT_CONFIRMED = "notification_sent_not_confirmed";
    static final String NOTIFICATION_ERROR = "notification_error";
    static final String NOTIFICATION_CONFIRMED = "notification_confirmed";

    static final String CABINET_CREATED = "cabinet_created";
    static final String CABINET_OPEN = "cabinet_open";
    static final String CABINET_BLOCKED = "cabinet_blocked";
    static final String CABINET_CLOSED = "cabinet_closed";
}
