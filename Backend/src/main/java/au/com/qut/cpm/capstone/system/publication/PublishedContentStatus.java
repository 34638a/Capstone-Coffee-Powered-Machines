package au.com.qut.cpm.capstone.system.publication;

public enum PublishedContentStatus {

    /**
     * This is the current content.
     */
    LIVE,
    /**
     * This is not the current content. It is loaded under the live content.
     */
    UPDATED,
    /**
     * This content cannot be loaded by anyone other than an administrator.
     */
    RETRACTED,
    /**
     * This content can be loaded by anyone but will not appear in searches.
     */
    HIDDEN
}
