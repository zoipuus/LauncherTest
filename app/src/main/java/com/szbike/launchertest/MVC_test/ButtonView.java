package com.szbike.launchertest.MVC_test;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.szbike.launchertest.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 * 按钮界面
 */
public class ButtonView implements View.OnClickListener {
    private static final String TAG = "ButtonView";
    private Toast tips;
    private EditText username;
    private EditText password;

    public RelativeLayout getContentView() {
        return contentView;
    }

    private RelativeLayout contentView;

    public ButtonView(Context context) {
        setUpViews(context);
    }

    private void setUpViews(Context context) {
        Log.e(TAG + "->", "setUpViews()");
        contentView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.activity_mvc, null, false);
        username = (EditText) contentView.findViewById(R.id.username);
        password = (EditText) contentView.findViewById(R.id.password);

        Button button = (Button) contentView.findViewById(R.id.button);
        Button button2 = (Button) contentView.findViewById(R.id.button2);
        Button button3 = (Button) contentView.findViewById(R.id.button3);
        Button button4 = (Button) contentView.findViewById(R.id.button4);
        Button button5 = (Button) contentView.findViewById(R.id.button5);
        Button button6 = (Button) contentView.findViewById(R.id.button6);
        Button login = (Button) contentView.findViewById(R.id.login);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                showTips("Button one");
                break;
            case R.id.button2:
                showTips("Button tow");
                break;
            case R.id.button3:
                showTips("Button three");
                break;
            case R.id.button4:
                showTips("Button four");
                break;
            case R.id.button5:
                showTips("Button five");
                break;
            case R.id.button6:
                showTips("Button six");
                break;
            case R.id.login:
                new AsyncViewTask().execute();
                break;
        }
    }

    private boolean onLoginAction() {
        String uriAPI = "http://10.0.0.54/servlet/LoginAction";  //声明网址字符串
        HttpPost httpRequest = new HttpPost(uriAPI);   //建立HTTP POST联机
        List<NameValuePair> params = new ArrayList<>();   //Post运作传送变量必须用NameValuePair[]数组储存
        params.add(new BasicNameValuePair("username", username.getText().toString()));
        params.add(new BasicNameValuePair("pswd", password.getText().toString()));
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));   //发出http请求
            HttpResponse httpResponse;   //取得http响应
            try {
                httpResponse = new DefaultHttpClient().execute(httpRequest);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    String strResult = EntityUtils.toString(httpResponse.getEntity());   //获取字符串
                    Log.e(TAG + "->", strResult);
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showTips(String msg) {
        Log.e(TAG + "->", "showTips()");
        if (tips == null) {
            tips = Toast.makeText(contentView.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            tips.setText(msg);
        }
        tips.show();
    }

    private class AsyncViewTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... views) {
            return onLoginAction();
        }

        protected void onPostExecute(Boolean isLogin) {
            if (isLogin) {
                Log.e(TAG + "->", "Login Success");
            } else {
                Log.e(TAG + "->", "Login Failed");
            }
        }

    }
}
