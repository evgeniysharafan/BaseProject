package change.me.util;

import android.text.TextUtils;

import com.evgeniysharafan.utils.PrefUtils;

public final class Prefs {

    private static final String EXAMPLE = "example";

    // cache for frequent usage
    private static String example;

    private Prefs() {
    }

    public static String getExample() {
        if (TextUtils.isEmpty(example)) {
            example = PrefUtils.getString(EXAMPLE, "");
        }

        return example;
    }

    public static void setExample(String exam) {
        example = exam;
        PrefUtils.put(EXAMPLE, exam);
    }

}
