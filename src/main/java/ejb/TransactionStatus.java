package ejb;

/**
 * Indicates the status of a transaction
 */
public enum TransactionStatus {
    /**
     * When a user requests money from a different user
     */
    REQUEST_SENT,
    /**
     * When a user has made a transfer and awaiting confirmation
     */
    PENDING,
    /**
     * When a transaction has concluded and the user received money
     */
    RECEIVED,
    /**
     * When a transaction has concluded and the user sent money
     */
    SENT,
    /**
     * When another user has requested from this user money
     */
    REQUEST
}
