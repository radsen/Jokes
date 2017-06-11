package com.udacity.gradle.builditbigger.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.udacity.gradle.builditbigger.R;
import com.udacity.radsen.jokes.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by radsen on 6/10/17.
 */

public class AsyncTaskEndPoint extends AsyncTask<Void, Void, String> {

    private final Context context;
    private final EndPointListener listener;

    public interface EndPointListener{
        void onBeforeStart();
        void onSuccess(String value);
        void onError();
    }

    private MyApi mApiService;

    public AsyncTaskEndPoint(Context context, EndPointListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute(){
        listener.onBeforeStart();
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(mApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.end_point))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mApiService = builder.build();
        }

        String strJoke = null;
        try {
            strJoke = mApiService.jokes().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strJoke;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!TextUtils.isEmpty(s)) {
            listener.onSuccess(s);
        } else {
            listener.onError();
        }
    }
}
