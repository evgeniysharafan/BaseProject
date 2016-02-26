package change.me.util;

import com.evgeniysharafan.utils.PrefUtils;

public final class Prefs {

    private static final String EXAMPLE = "example";

    private Prefs() {
    }

    public static String getExample() {
        return PrefUtils.getString(EXAMPLE, "");
    }

    public static void setExample(String exam) {
        PrefUtils.put(EXAMPLE, exam);
    }

}
