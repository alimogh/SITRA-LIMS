package com.example.myapplication;import android.app.Application;import android.text.TextUtils;import android.util.Log;import android.widget.ArrayAdapter;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.toolbox.ImageLoader;import com.android.volley.toolbox.Volley;import java.util.ArrayList;public class AppController {    ArrayList<String> Testname;    String inwardno,inward_date,inward_status,no_of_samples,last_publish_date;    public void setTestname(ArrayList<String> testname) {        Log.i("setdata",testname.get(2));        Testname = testname;    }    public ArrayList<String> getTestname() {//        Log.i("getdata","123"+Testname.get(3));        return Testname;    }    public String getInwardno() {        return inwardno;    }    public void setInwardno(String inwardno) {        Log.i("inward",inwardno);        this.inwardno = inwardno;    }    public String getInward_date() {        return inward_date;    }    public void setInward_date(String inward_date) {        this.inward_date = inward_date;    }    public String getInward_status() {        return inward_status;    }    public void setInward_status(String inward_status) {        this.inward_status = inward_status;    }    public String getNo_of_samples() {        return no_of_samples;    }    public void setNo_of_samples(String no_of_samples) {        this.no_of_samples = no_of_samples;    }    public String getLast_publish_date() {        return last_publish_date;    }    public void setLast_publish_date(String last_publish_date) {        this.last_publish_date = last_publish_date;    }}