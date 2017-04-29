package in.yellowsoft.souqat;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chinni on 11-05-2016.
 */
public class Group implements Serializable {
    public String title;
    public String title_ar;
    public String min;
    public String max;
    public ArrayList<Options> options;

    public Group(String title, String title_ar, String min, String max) {
        this.title = title;
        this.title_ar = title_ar;
        this.min = min;
        this.max = max;
        options = new ArrayList<>();
    }
    public String getTitle(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
        return title_ar;
        else
            return title;
    }
}

