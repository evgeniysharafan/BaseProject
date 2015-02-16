package change.me.util.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public final class ContactUtils {

    private static final String STARRED_ORDER = Contacts.DISPLAY_NAME + " COLLATE NOCASE ASC";

    public final static int CONTACT_ID = 0;
    public final static int DISPLAY_NAME = 1;
    public final static int STARRED = 2;
    public final static int PHOTO_URI = 3;
    public final static int LOOKUP_KEY = 4;
    public final static int CONTACT_PRESENCE = 5;
    public final static int CONTACT_STATUS = 6;
    // Only used for StrequentPhoneOnlyLoader
    public final static int PHONE_NUMBER = 5;
    public final static int PHONE_NUMBER_TYPE = 6;
    public final static int PHONE_NUMBER_LABEL = 7;
    public final static int IS_DEFAULT_NUMBER = 8;
    public final static int PINNED = 9;

    // The _ID field returned for strequent items actually contains data._id instead of
    // contacts._id because the query is performed on the data table. In order to obtain the
    // contact id for strequent items, we thus have to use Phone.contact_id instead.
    public final static int CONTACT_ID_FOR_DATA = 10;

    private static final String[] COLUMNS = new String[]{
            Contacts._ID, // ..........................................0
            Contacts.DISPLAY_NAME, // .................................1
            Contacts.STARRED, // ......................................2
            Contacts.PHOTO_URI, // ....................................3
            Contacts.LOOKUP_KEY, // ...................................4
            Contacts.CONTACT_PRESENCE, // .............................5
            Contacts.CONTACT_STATUS, // ...............................6
    };

    /**
     * Projection used for the {@link android.provider.ContactsContract.Contacts#CONTENT_STREQUENT_URI}
     * query when {@link android.provider.ContactsContract#STREQUENT_PHONE_ONLY} flag
     * is set to true. The main difference is the lack of presence
     * and status data and the addition of phone number and label.
     */
    public static final String[] COLUMNS_PHONE_ONLY = new String[]{
            Contacts._ID, // ..........................................0
            Contacts.DISPLAY_NAME, // .................................1
            Contacts.STARRED, // ......................................2
            Contacts.PHOTO_URI, // ....................................3
            Contacts.LOOKUP_KEY, // ...................................4
            Phone.NUMBER, // ..........................................5
            Phone.TYPE, // ............................................6
            Phone.LABEL, // ...........................................7
            Phone.IS_SUPER_PRIMARY, //.................................8
            Contacts.PINNED, // .......................................9
            Phone.CONTACT_ID //........................................10
    };

    private static int sThumbnailSize = -1;

    private ContactUtils() {
    }

    public static CursorLoader createStrequentLoader(Context context) {
        return new CursorLoader(context, Contacts.CONTENT_STREQUENT_URI, COLUMNS, null, null,
                STARRED_ORDER);
    }

    public static CursorLoader createStarredLoader(Context context) {
        return new CursorLoader(context, Contacts.CONTENT_URI, COLUMNS, Contacts.STARRED + "=?",
                new String[]{"1"}, STARRED_ORDER);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static CursorLoader createStrequentPhoneOnlyLoader(Context context) {
        Uri uri = Contacts.CONTENT_STREQUENT_URI.buildUpon()
                .appendQueryParameter(ContactsContract.STREQUENT_PHONE_ONLY, "true").build();
        return new CursorLoader(context, uri, COLUMNS_PHONE_ONLY, null, null, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static CursorLoader createFrequentLoader(Context context) {
        return new CursorLoader(context, Contacts.CONTENT_FREQUENT_URI, COLUMNS,
                Contacts.STARRED + "=?", new String[]{"0"}, null);
    }

    public static int getThumbnailSize(Context context) {
        if (sThumbnailSize == -1) {
            final Cursor c = context.getContentResolver().query(
                    ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI,
                    new String[]{ContactsContract.DisplayPhoto.THUMBNAIL_MAX_DIM}, null, null, null);
            try {
                c.moveToFirst();
                sThumbnailSize = c.getInt(0);
            } finally {
                c.close();
            }
        }

        return sThumbnailSize;
    }

}
