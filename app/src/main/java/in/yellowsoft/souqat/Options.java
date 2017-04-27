package in.yellowsoft.souqat;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by Chinni on 11-05-2016.
 */
public class Options implements Serializable {
    public String title;
    public String title_ar;
    public String price;

    public Options(String title, String title_ar, String price) {
        this.title = title;
        this.title_ar = title_ar;
        this.price = price;
    }
    public String getTitle(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
        return title_ar;
        else
            return title;
    }
}

