package com.supermanager;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.sm.http.ManagerLoader;
import com.sm.model.Manager;

import java.io.IOException;

/**
 * Created by yago on 12/10/16.
 */
public class ManagerLoaderTask extends AsyncTaskLoader<Manager> {

    private String user;
    private String pass;

    public ManagerLoaderTask(Context context) {
        super(context);
    }

    public ManagerLoaderTask(Context context, String user, String pass) {
        super(context);
        this.user = user;
        this.pass = pass;
    }


    @Override
    public Manager loadInBackground() {
        return getManager();
    }

    private Manager getManager() {

        Manager manager = null;

        try {
            ManagerLoader scraper = new ManagerLoader();
            manager = scraper.load(user, pass);
            Log.i("ManagerLoader", manager.getTeams().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return manager;
    }


}
