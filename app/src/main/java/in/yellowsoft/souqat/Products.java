package in.yellowsoft.souqat;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chinni on 05-05-2016.
 */
public class Products implements Serializable {
    String res_id,title,title_ar,price,about,about_ar,description,description_ar,stock,option_title,option_title_ar;
    ArrayList<Images> images;
    ArrayList<Groups> groups;
    Products(JSONObject jsonObject){
        try {
            this.res_id=jsonObject.getString("id");
            this.title=jsonObject.getString("title");;
            this.title_ar=jsonObject.getString("title_ar");;
            this.price=jsonObject.getString("price");;
            this.stock=jsonObject.getString("stock");;
            this.about=jsonObject.getString("about");;
            this.about_ar=jsonObject.getString("about_ar");;
            this.description=jsonObject.getString("description");;
            this.description_ar=jsonObject.getString("description_ar");;
//            this.option_title=jsonObject.getString("options_title");;
//            this.option_title_ar=jsonObject.getString("options_title_ar");
            this.groups = new ArrayList<>();
            this.images = new ArrayList<>();
            for(int i = 0;i<jsonObject.getJSONArray("group").length();i++){
                try {
                    Groups temp = new Groups(jsonObject.getJSONArray("group").getJSONObject(i));
                    this.groups.add(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            for(int i = 0;i<jsonObject.getJSONArray("images").length();i++){
                try {
                    Images temp = new Images(jsonObject.getJSONArray("images").getJSONObject(i));
                    this.images.add(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public  String getTitle(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
            return title_ar;
        else
            return  title;
    }
    public  String getAbout(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
            return about_ar;
        else
            return  about;
    }

    public String getdescription(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
            return description_ar;
        else
            return  description;
    }
    public String getoption_title(Context context) {
        if(Settings.get_user_language(context).equals("ar"))
            return option_title_ar;
        else
            return  option_title;
    }

    public class Groups implements Serializable{
        ArrayList<Options> addons;
        String group,group_ar,min,max;
        Groups(JSONObject jsonObject) {
            try {
                this.group = jsonObject.getString("group");
                this.group_ar = jsonObject.getString("group_ar");
                this.min = jsonObject.getString("minimum");
                this.max = jsonObject.getString("maximum");
                addons = new ArrayList<>();
                for(int i = 0;i<jsonObject.getJSONArray("options").length();i++){

                    try {
                        Options temp = new Options(jsonObject.getJSONArray("options").getJSONObject(i));
                        this.addons.add(temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public  String get_group(Context context) {
            if(Settings.get_user_language(context).equals("ar"))
                return group_ar;
            else
                return  group;
        }

        public class Options implements Serializable{
            String option,option_ar,price,option_id;
            Boolean isselected = false;
            Options(JSONObject jsonObject){
                try {
                    this.option_id=jsonObject.getString("option_id");
                    this.option=jsonObject.getString("option");
                    this.option_ar=jsonObject.getString("option_ar");
                    this.price=jsonObject.getString("price");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            public String get_option(Context context) {
                if (Settings.get_user_language(context).equals("ar"))
                    return option_ar;
                else
                    return option;
            }

        }
    }
    public class Images implements Serializable{
        String img,thumb;
        Images(JSONObject jsonObject){
            try {
                this.img=jsonObject.getString("image");
//                this.thumb=jsonObject.getString("thumb");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
}
}
