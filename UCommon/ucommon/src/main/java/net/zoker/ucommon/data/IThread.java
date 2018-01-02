package net.zoker.ucommon.data;

import android.os.AsyncTask;

/**
 * Created by zoker on 2017/12/12.
 */

public class IThread{

    public void loadOnMain(){

    }

    public void run(){

    }

    public class ThreadLoad extends AsyncTask<Integer,Integer,Object>{

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Integer... integers) {
            return null;
        }
    }
}
